// Include this if you want to use .env variables
import globalErrorHandler from "./error/globalErrorHandler";

require('dotenv').config();
import cors from "cors";
import express from "express";
const corsOptions = require('./config/cors/corsOptions');
import passport from "passport";
const cookieParser = require('cookie-parser');
const credentials = require('./config/cors/credentials.middleware'); // middleware for cors
// initialise configuration of passport strategy class
import authenticationRoutes from "./routes/authenticationRoutes";
import userRoutes from './routes/userRoutes';
import { logger } from "./config/winston logger/logger";


const PORT = process.env.PORT;
const app = express();

// Standard middlewares
app.use(cookieParser());
app.use(credentials);
app.use(cors(corsOptions));
app.use(express.json());
// Create the passport strategy object
require('./config/passport strategy/passportJWT')(passport)
app.use(passport.initialize()); // Create the passport strategy object

app.use('/auth', authenticationRoutes);
app.use('/user', userRoutes);

logger().info('Server is starting up');
// Global error handler
app.use(globalErrorHandler)
app.listen(PORT, () => console.log(`listening to port ${PORT} `)); // This port should be in env
