package com.fernandobouchet.book_exchange.controller;

import com.fernandobouchet.book_exchange.dto.ApiErrorResponse;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        log.error("Caught exception", ex);
        ApiErrorResponse error = ApiErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred")
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(Exception ex) {
        log.error("Caught exception", ex);
        ApiErrorResponse error = ApiErrorResponse
                .builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Incorrect username or password")
                .build();

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiErrorResponse> handleJwtException(Exception ex) {
        log.error("Caught exception", ex);
        ApiErrorResponse error = ApiErrorResponse
                .builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Invalid or expired token")
                .build();

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
