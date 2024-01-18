/*
 * This serves several purpose which is to catch and convert every known and unknown error so that the internal mechanism of the backend is shown
 * to the public. This also allow us to no longer write try catch editorContent everywhere.
 *
 * This will be the centralise errorhandling middlewares which will process and convert error message thrown from every other middlewares in nodejs
 * We are essentially creating modularity where we can come in and direct certain errors to different handlers in the future
 *
 * We are likely to expand on this error handler to censor out certain information and grab the key things to return to the front end.
 *
 * We may also use error codes to help streamline the errors sent to the frontend.
 * */
import {Request, Response, NextFunction} from 'express';

const globalErrorHandler= (err: any, req: Request, res: Response, next: NextFunction) => {
    // This is how you get the message from the error
    const message = err.message
    const status = err.status || 500;
    const errorObj = {
        message: 'Internal Server Error 500!'
    };

    res.status(status).json(errorObj);
};

export default  globalErrorHandler;
