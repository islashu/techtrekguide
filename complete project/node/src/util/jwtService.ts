import {User} from '@prisma/client';

require('dotenv').config();
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
/*Payload*/
const accessTokenExpiry = process.env.ACCESS_TOKEN_EXPIRY;

/*
 * Generate a hashed password based on the HS256 algorithmn, note that the same password may generate a different hash
 * each time due to a variable salt added to each generate. Please only use bcrypt compare to check the password
 * */
const genHashPassword = async (password: string): Promise<string> => {
    return await bcrypt.hash(password, 10);
};

const validateUsername = async (username: string, usernameFromDB: string): Promise<boolean> => {
    return (await usernameFromDB) == username;
};

/*
 * Note that for bcrypt.compare to work, it has to accept the plain text as the first param
 * and hashedPassword as the second, exclusively in that order to work
 * Hash password is the hashed password stored in the DB
 * */
const validatePassword = async (plainTextPassword: string, hashedPassword: string): Promise<boolean> => {
    return await bcrypt.compare(plainTextPassword, hashedPassword);
};

/*
 * When signing token, you will need the following information:
 * 1. information to pass usually account details
 * 2. secret key
 * 3. expiryIn (Do not change the key value)
 *
 * // Store the following settings properly
 * */
const issueAccessToken = (foundUser: User): string => {
    const payload = {
        userEmail: foundUser.userEmail,
        age: foundUser.age,
        firstName: foundUser.firstName,
        lastName: foundUser.lastName
    };
    return jwt.sign(
        payload,
        process.env.ACCESS_TOKEN_SECRET,
        // Important this is correct else, there will be not authenticate error even with the same secret
        {expiresIn: accessTokenExpiry}
    );
};

/*
 * This will generate a isRefresh token that is provided to the auth and also stored in the DB at the time of login
 * When the first access token expires, the auth will provide the isRefresh token and the server will compare the isRefresh token in the DB
 * if both token are the same, a new access token with a longer expiry date is provided.
 * */
const issueRefreshToken = (foundUser: User, expiresIn?: number) => {
    const payload = {
        email: foundUser.userEmail,
        age: foundUser.age,
        firstName: foundUser.firstName,
        lastName: foundUser.lastName
    };
    return jwt.sign(payload, process.env.REFRESH_TOKEN_SECRET, expiresIn ? {expiresIn: expiresIn} : {expiresIn: 60 * 15});
};

export {genHashPassword, validatePassword, issueAccessToken, issueRefreshToken, validateUsername};
