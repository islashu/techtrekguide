
import {NextFunction, Request, Response} from 'express';
import {handleLogin, handleRegister} from "../controllers/authenticateController";
import passport from "passport";
import {authErrorHandler} from "../error/authErrorhandler";
const express = require('express');
const router = express.Router();

router.post('/login',  handleLogin)

router.post('/register', handleRegister)
router.post('/test', [passport.authenticate('jwt', {session: false, failWithError: true}), authErrorHandler], (req: Request, res: Response, next: NextFunction) => {
    return res.status(200).json({message: "protected route accessed!!"})
});

export default  router;



