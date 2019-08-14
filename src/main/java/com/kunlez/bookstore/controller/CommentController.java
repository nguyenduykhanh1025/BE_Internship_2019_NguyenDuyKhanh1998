package com.kunlez.bookstore.controller;

import com.kunlez.bookstore.DTO.CommentDTO;
import com.kunlez.bookstore.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentServices commentServices;

    @Secured("ROLE_MEMBER")
    @PostMapping("/{id_book}")
    public CommentDTO post(@RequestBody @Validated CommentDTO commentDTO, @PathVariable int id_book, @RequestHeader("Authorization") String token) throws ParseException {
        return commentServices.post(commentDTO, id_book, token);
    }

    @Secured("ROLE_MEMBER")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id, @RequestHeader("Authorization") String token){
       return commentServices.deleteCommentFollowId(id, token);
    }

    @Secured("ROLE_MEMBER")
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody @Validated CommentDTO commentDTO, @RequestHeader("Authorization") String token){
        return  commentServices.editComment(id, commentDTO, token);
    }
}
