const {createLogger, format, transports} = require('winston');
const {combine, timestamp, label, printf, json} = format;
import  *  as  winston  from  'winston';
import  'winston-daily-rotate-file';

export const logger = () => {
     let dailyRotateCustomTransport = new winston.transports.DailyRotateFile({
            level: 'info',
            filename: 'logs/application-%DATE%.log',
            datePattern: 'YYYY-MM-DD-HH',
            zippedArchive: true,
            maxSize: '20m',
            maxFiles: '14d'
        })

    return createLogger({
        level: 'debug',
        // format: winston.format.simple(),

        format: combine(timestamp(), json()),

        defaultMeta: {service: 'user-service'},
        // Locations to log
        transports: [
            // Print to console
            new transports.Console(),
            // Save to errors.log as well
            new transports.File({
                filename: 'logs/errors.log'
            }),
            dailyRotateCustomTransport
        ]
    });
};
