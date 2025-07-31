package com.hk.user.service.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApplicationErrors> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        ApplicationErrors applicationErrors = new ApplicationErrors(userNotFoundException.getMessage(), "404");
        applicationErrors.setDate(new Date());
        return new ResponseEntity<>(applicationErrors, HttpStatus.NOT_FOUND);
    }
}
