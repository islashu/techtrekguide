import allowedOrigins from "./allowedOrigins";
/*
 * Configuration for cors
 * */
const corsOptions = {
    origin: (origin: any, callback: any) => {
             console.log("incoming", allowedOrigins, origin)
        if (allowedOrigins.indexOf(origin) !== -1 || !origin) {
            console.log("allowed")
            callback(null, true);
        } else {
            console.log("not allowed")
            callback(new Error('Not allowed by CORS'));
        }
    },
    optionsSuccessStatus: 200
};

export default corsOptions
