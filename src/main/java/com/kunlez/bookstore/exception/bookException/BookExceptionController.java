package com.kunlez.bookstore.exception.bookException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionController {

    @ExceptionHandler(value = BookNotFound.class)
    public ResponseEntity<Object> exception(BookNotFound exception) {
        return new ResponseEntity<>("book not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BookNotOfUser.class)
    public ResponseEntity<Object> exception(BookNotOfUser exception) {
        return new ResponseEntity<>("book not of user", HttpStatus.BAD_GATEWAY);
    }
}
