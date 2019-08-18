package com.kunlez.bookstore.exception.userException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = UserNotfoundException.class)
    public ResponseEntity<Object> exception(UserNotfoundException exception) {
        return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserIsExistException.class)
    public ResponseEntity<Object> exception(UserIsExistException exception) {
        return new ResponseEntity<>("user is exist", HttpStatus.FOUND);
    }

    @ExceptionHandler(value = UserIsNotEnable.class)
    public ResponseEntity<Object> exception(UserIsNotEnable exception) {
        return new ResponseEntity<>("user is not enable", HttpStatus.LOCKED);
    }

    @ExceptionHandler(value = EmailException.class)
    public ResponseEntity<Object> exception(EmailException exception) {
        return new ResponseEntity<>("email not format", HttpStatus.NOT_EXTENDED);
    }
}
