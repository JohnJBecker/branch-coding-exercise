package com.exercise.accounts.exception;


import com.exercise.accounts.user.InvalidUserNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidUserNameException.class)
    public ResponseEntity<ErrorMessage> invalidUserNameExceptionHandler(Exception ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundExceptionHandler(Exception ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceUnavailableErrorException.class)
    public ResponseEntity<ErrorMessage> serviceUnavailableErrorExceptionHandler(Exception ex) {
        logger.error(ex.getMessage());

        ErrorMessage message = new ErrorMessage("service unavailable");

        return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex) {
        logger.error(ex.getMessage());

        ErrorMessage message = new ErrorMessage("unexpected error");

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
