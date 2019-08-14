package com.kunlez.bookstore.converters.commentConverter;

import com.kunlez.bookstore.DTO.CommentDTO;
import com.kunlez.bookstore.DTO.RegisterDTO;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.CommentEntity;
import com.kunlez.bookstore.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityToCommentDTOConverter extends Converter<CommentEntity, CommentDTO> {
    @Autowired
    private Converter<UserEntity, RegisterDTO> userEntityToRegisterDTOConverter;
    @Override
    public CommentDTO convert(CommentEntity source) {

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(source.getId());
        commentDTO.setMessage(source.getMessage());
        commentDTO.setCreateAt(source.getCreateAt());
        commentDTO.setUpdateAt(source.getUpdateAt());
        commentDTO.setRegisterDTO(userEntityToRegisterDTOConverter.convert(source.getUserEntity()));
        return commentDTO;
    }
}
