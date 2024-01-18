package com.example.complete.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoPolicyFoundResponse {
    private String message;
    private Throwable cause;
    private HttpStatus status;
    private Date timestamp;
}
