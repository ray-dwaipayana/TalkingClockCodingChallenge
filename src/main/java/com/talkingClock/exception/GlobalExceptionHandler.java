package com.talkingClock.exception;

import com.talkingClock.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

        /***
         * Exception Handler for Invalid Time Format exception
         * @param ex
         * @return
         */
        @ExceptionHandler(InvalidTimeException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<ErrorResponse> handleInvalidTimeFormatException(InvalidTimeException ex) {
            ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<ErrorResponse> handleServletException(Exception ex) {
            ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
}


