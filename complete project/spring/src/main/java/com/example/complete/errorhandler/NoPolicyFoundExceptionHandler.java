package com.example.complete.errorhandler;


import com.example.complete.exception.GlobalExceptionResponse;
import com.example.complete.exception.NoPolicyFoundException;
import com.example.complete.exception.NoPolicyFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
// Make this instance of class placed directly before sending response to client
@ControllerAdvice
public class NoPolicyFoundExceptionHandler {

        // This handler handles all exceptions
    // This handler handles NoPolicyFoundException only
    @ExceptionHandler(NoPolicyFoundException.class)
    public ResponseEntity<Object> handleNoPolicyFoundException(NoPolicyFoundException exception) {
        NoPolicyFoundResponse response = new NoPolicyFoundResponse(
                exception.getMessage(),
                exception.getCause(), // This should not be passed as there is server sensitive information inside
                HttpStatus.BAD_REQUEST,
                new Date()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Sequence of handlers matters if the above does not handle the exception, this will
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        GlobalExceptionResponse response = new GlobalExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                new Date()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
