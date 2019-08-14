package com.kunlez.bookstore.converters.commentConverter;

import com.kunlez.bookstore.DTO.CommentDTO;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentDTOToCommentEntityConverter extends Converter<CommentDTO, CommentEntity> {
    @Override
    public CommentEntity convert(CommentDTO source) {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setMessage(source.getMessage());
        commentEntity.setCreateAt(source.getCreateAt());
        commentEntity.setUpdateAt(source.getUpdateAt());

        return commentEntity;
    }
}
