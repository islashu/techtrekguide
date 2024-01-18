import {Request, Response, NextFunction} from 'express';
export const authErrorHandler = (err: Error, req: Request, res: Response, next: NextFunction) => {
    const status = 403;
    console.log(err)
    const errorObj = {
        message: err.message || 'Jwt Authentication Error'
    };

    res.status(status).json(errorObj);
};
