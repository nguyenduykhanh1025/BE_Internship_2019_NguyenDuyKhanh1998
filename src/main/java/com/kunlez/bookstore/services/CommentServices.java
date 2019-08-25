package com.kunlez.bookstore.services;

import com.kunlez.bookstore.DTO.CommentDTO;
import com.kunlez.bookstore.common.CommonMethot;
import com.kunlez.bookstore.configurations.TokenProvider;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.CommentEntity;
import com.kunlez.bookstore.exception.bookException.BookNotFound;
import com.kunlez.bookstore.exception.commentException.CommentNotFound;
import com.kunlez.bookstore.exception.commentException.CommentNotOfUser;
import com.kunlez.bookstore.repository.BookRepository;
import com.kunlez.bookstore.repository.CommentRepository;
import com.kunlez.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.Date;

@Service
public class CommentServices {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private Converter<CommentDTO, CommentEntity> commentDTOToCommentEntityConverter;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Converter<CommentEntity, CommentDTO> commentEntityToCommentDTOConverter;


    @Autowired
    private TokenProvider jwtTokenUtil;

    public ResponseEntity<?> post(CommentDTO commentDTO,int id_book, String token) throws ParseException {

        if(!bookRepository.findById(id_book).isPresent()){
            throw new BookNotFound();
        }

        // server only message
        commentDTO.setUpdateAt(new Date());
        commentDTO.setCreateAt(new Date());
        CommentEntity commentEntity = commentDTOToCommentEntityConverter.convert(commentDTO);

        commentEntity.setUserEntity(userRepository.findByEmail(CommonMethot.getUserName(token, jwtTokenUtil)));
        commentEntity.setBookEntity(bookRepository.findById(id_book).get());

        commentRepository.save(commentEntity);

        CommentDTO commentDTO1 = commentEntityToCommentDTOConverter.convert(commentEntity);
        return ResponseEntity.ok(commentDTO1);
    }

    public ResponseEntity<?> editComment(int id, CommentDTO commentDTO, String token){

        if(!commentRepository.findById(id).isPresent()){
            throw new CommentNotFound();
        }
        CommentEntity commentEntity = commentRepository.findById(id).get();

        if(commentEntity.getUserEntity().getEmail().equals(CommonMethot.getUserName(token, jwtTokenUtil))){
            commentEntity.setUpdateAt(new Date());
            commentEntity.setMessage(commentDTO.getMessage());

            commentRepository.save(commentEntity);
            return ResponseEntity.ok(commentEntityToCommentDTOConverter.convert(commentEntity));
        }
        throw new CommentNotOfUser();
    }

    public ResponseEntity<?> deleteCommentFollowId(@PathVariable int id, String token){

        if(commentRepository.findById(id) == null){
            throw new CommentNotFound();
        }

        CommentEntity commentEntity = commentRepository.findById(id).get();

        if(commentEntity.getUserEntity().getEmail().equals(CommonMethot.getUserName(token, jwtTokenUtil))){
            commentRepository.delete(commentEntity);
            return ResponseEntity.ok(commentEntityToCommentDTOConverter.convert(commentEntity));
        }
        throw new CommentNotOfUser();
    }
}
