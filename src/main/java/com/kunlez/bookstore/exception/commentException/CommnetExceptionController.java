package com.kunlez.bookstore.exception.commentException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommnetExceptionController  {

    @ExceptionHandler(value = CommentNotOfUser.class)
    public ResponseEntity<Object> exception(CommentNotOfUser exception) {
        return new ResponseEntity<>("comment not of user", HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(value = CommentNotFound.class)
    public ResponseEntity<Object> exception(CommentNotFound exception) {
        return new ResponseEntity<>("comment not found", HttpStatus.NOT_FOUND);
    }
}
