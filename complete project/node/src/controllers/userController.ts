import {Request, Response, NextFunction} from 'express';

const createUser = async (req: Request, res: Response, next: NextFunction) => {
    // Introduce zod
    try {
        const {email, password} = req.body.user;
    } catch (err) {
        next(err);
    }
};
const getUser = async (req: Request, res: Response, next: NextFunction) => {};

export {createUser, getUser};
