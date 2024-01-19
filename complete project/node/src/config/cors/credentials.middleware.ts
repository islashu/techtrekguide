const allowedOrigins = require('./allowedOrigins');
import {NextFunction, Request, Response} from 'express';

/* Middleware for cors to be place before calling cors*/
const credentialsMiddleware = (req: Request, res: Response, next: NextFunction) => {
    // Sometimes there will be an issue where the origin is undefined, this is due to the browser not sending the origin
    // because of this the cors just reject it, even though it has already been whitelisted so the easiest way is to console.log
    // the entire path
    const origin = req.headers.origin;
    if (allowedOrigins.default.includes(origin)) {
        console.log("allowed")
    }
    // You can add additional headers here before going to the next cors middleware
    // res.header('Access-Control-Allow-Credentials', 'true');
    // res.header('Access-Control-Allow-Origin', '*');
    // res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
    next();
};

module.exports = credentialsMiddleware;
