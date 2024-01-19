
import {NextFunction, Request, Response} from 'express';
import {handleLogin, handleRegister} from "../controllers/authenticateController";
import passport from "passport";
import {authErrorHandler} from "../error/authErrorhandler";
const express = require('express');
const router = express.Router();

router.post('/login',  handleLogin)

router.post('/register', handleRegister)
router.get('/test',  (req: Request, res: Response, next: NextFunction) => {
    return res.status(200).json(["hello", "something", "thing is fine", "new item", "new item 2"])
});

export default  router;



