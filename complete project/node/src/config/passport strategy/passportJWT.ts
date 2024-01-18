import {findUser} from '../../repository/UserRepository';
import {UserSchema} from '../../controllers/authenticateController';
import {User} from '@prisma/client';
import {z} from 'zod';

require('dotenv').config();
// If you every have an error recognising this in your IDE, npm i @types/passportJWT-jwt --D
const JwtStrategy = require('passport-jwt').Strategy,
    ExtractJwt = require('passport-jwt').ExtractJwt;

/*
 * Passport documentation is not good. Don't refer to that. This file's purpose is to configure/transform the passportObj
 * to a specific mode (JWT/local/FB/Google etc) when the server starts.
 *
 * This is done by creating the base passport strategy object in app.js
 * -- const passport strategy = require('passport strategy');
 *
 * Then call the following in app.js
 * -- require('./config/passport strategy')(passport strategy);
 *
 * By doing this, you are passing the passportObj into the function below.
 * What this does is that it will set the settings in "passport strategy" to the jwtOptions
 * such as allowing it to take the jwttoken from the authorization header and allow it to decode the jwt token based on a certain algo
 *
 * Afterwards, the passport strategy requires the auth object to compare the jwttoken that it received from the request
 * It will perform an internal comparison and return a true or false with res.status
 *
 * To findAllTabs the value of this comparison, use the middlewares, passport strategy.authenticate('jwt', {session: false})
 * put this statement before the routes to perform the authentication
 *
 * The main reason why this is automatically triggered for all request is that passport strategy wants to give the developer flexibility
 * Not all routes require authentication, if this is triggered for all routes, people will have to create an account just to view anything and
 * the resources is gated behind an account.
 *
 * Note that passport strategy does not issue a JWT, or isRefresh token, create your auth for you, it does not even attached the jwt token to your header.
 * You have to do this yourself. The passport strategy only authenticates existing tokens which means that the first login you will have to issue a JWT token before you can run the passport strategy authenticate middlewares
 * This is all define by the developer and their own ways. passport strategy just authenticates based on a certain requirement and simply return yes or no to proceed forward.
 *
 * */
// These options will be pass to the strategy
const jwtOptions = {
    // Must include these 2 keys
    jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(), // A built in function to extract the jwt token directly from the header
    secretOrKey: process.env.ACCESS_TOKEN_SECRET,
    algorithm: 'HS256'
    // --------------------------
};

/*
 * Creating a new strategy
 * parameter 1: options
 * parameter 2: Jwt will perform run some functions and pass in the information into the (payload, next) as arguments
 *
 * What goes on in JWT Strategy is that it performs JWT checking by comparing the signed version with the incoming JWT token
 * Which if not for this lib, we will have to manually do it,
 * */
const payloadSchema = z.object({
    userEmail: z.string(),
    firstName: z.string(),
    lastName: z.string(),
    age: z.number(),
    iat: z.number(),
    exp: z.number()
});

type Payload = z.infer<typeof payloadSchema>;

const strategy = new JwtStrategy(jwtOptions, async (decodedPayload: Payload, next: any) => {
    /*
     IMPT!!
     The structure of the verify function must be the same.
     The return object (isError, obj)
     The first parameter check if there is an error with the auth process
     The second parameter is to return the obj for attachment, if false, means there is an error

     You have to add your own verification based on the payload.
     Passport only does the decoding of the jwtToken but the verification of the contents of the jwtToken is up to the developer
    */

    //  ----------------------------------------------- Add custom verification here -----------------------------------------------
    // Find the structure of the decoded payload
    const email: string = decodedPayload.userEmail;
    const userFound = await findUser(email);
    let isAuthenticated = true;
    isAuthenticated = isAuthenticated && userFound.firstName == decodedPayload.firstName;
    isAuthenticated = isAuthenticated && userFound.lastName == decodedPayload.lastName;
    isAuthenticated = isAuthenticated && decodedPayload.exp > Date.now()/1000

    // Verify if the token is valid
    if (!isAuthenticated) {
        return next(null, false);
    } else {
        return next(null, userFound);
    }
    //  ----------------------------------------------- Add custom verification here -----------------------------------------------
});

module.exports = (passport: any) => {
    passport.use(strategy);
};
