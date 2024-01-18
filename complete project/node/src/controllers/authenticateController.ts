import {Request, Response, NextFunction} from 'express';
import {z} from 'zod';
import {createUser, findUser, updateUserWithAccessToken} from '../repository/UserRepository';
import {genHashPassword, issueAccessToken, validatePassword, validateUsername} from '../util/jwtService';
import {logger} from "../config/winston logger/logger";


export const UserSchema = z.object({
    userEmail: z.string().email(),
    password: z.string().min(1).max(100),
    firstName: z.string().min(1).max(100),
    lastName: z.string().min(1).max(100),
    age: z.number().min(1).max(100)
});

export const UserLoginSchema = z.object({
    userEmail: z.string().email(),
    password: z.string().min(1).max(100)
});

type User = z.infer<typeof UserSchema>;
type UserLogin = z.infer<typeof UserLoginSchema>;
const handleLogin = async (req: Request, res: Response, next: NextFunction) => {
    try {
        console.log(req.body)
        const userLogin: UserLogin = UserLoginSchema.parse(req.body);
        //Please remember the await else if there is a prisma error it will not catch it
        const user = await findUser(userLogin.userEmail);
        const isPasswordValid = await validatePassword(userLogin.password, user.password);
        const isUsernameValid = await validateUsername(user.userEmail, userLogin.userEmail)
        if (!isPasswordValid) throw new Error('Invalid password');
        // User is valid, return JWT token
        const accessToken = issueAccessToken(user)
        await updateUserWithAccessToken(user.userEmail, accessToken)
        res.status(200).json({accessToken: accessToken})
    } catch (e) {
        console.log(e)
        next(e)
    }
};
const handleRegister = async (req: Request, res: Response, next: NextFunction) => {
    try {
        // If the req is missing anything, it will throw an error
        const user: User = UserSchema.parse(req.body);
        const createdUser = await createUser(user.firstName, user.lastName, user.age, user.userEmail, await genHashPassword(user.password));
        res.status(201).json();
    } catch (e) {
        // Pass on to global error handler
        console.log(e)
        next(e);
    }
};

export {handleLogin, handleRegister};
