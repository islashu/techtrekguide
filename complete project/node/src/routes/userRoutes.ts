import passport from 'passport';
import {createUser, getUser} from '../controllers/userController';
const express = require('express');
const router = express.Router();
import {Request, Response, NextFunction} from 'express';


router.get('/getUser', getUser)

router.post('/createUser', createUser)

export default  router;
