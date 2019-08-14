package com.kunlez.bookstore.converters.bookConverter;

import com.kunlez.bookstore.DTO.*;
import com.kunlez.bookstore.common.CommonMethot;
import com.kunlez.bookstore.configurations.TokenProvider;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.*;
import com.kunlez.bookstore.sortCollection.SortCommentFollowDateCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookEntityToBookDTOConverter extends Converter<BookEntity, BookDTO> {

    @Autowired
    private Converter<AuthorEntity, AuthorDTO> authorEntityToAuthorDTOConverter;

    @Autowired
    private Converter<UserEntity, RegisterDTO> userEntityToRegisterDTOConverter;

    @Autowired
    private TokenProvider jwtTokenUtil;


    @Override
    public BookDTO convert(BookEntity source) {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(source.getId());
        bookDTO.setTitle(source.getTitle());
        bookDTO.setDescription(source.getDescription());
        bookDTO.setLinkImage(source.getLinkImage());
        bookDTO.setCreatedAt(source.getCreatedAt());
        bookDTO.setUpdateAt(source.getUpdatedAt());
        bookDTO.setEnable(source.isEnable());

        AuthorDTO[] authorDTOS = new AuthorDTO[source.getAuthorEntities().size()];
        int indexAuthor = 0;
        for(AuthorEntity authorEntity : source.getAuthorEntities()){
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setName(authorEntity.getName());
            authorDTO.setId(authorEntity.getId());
            authorDTOS[indexAuthor++] = authorDTO;
        }
        bookDTO.setAuthorDTOS(authorDTOS);

        // categories
        CategoriesDTO[] categoriesDTOS = new CategoriesDTO[source.getCategoriesEntities().size()];
        int indexCategories = 0;
        for(CategoriesEntity categoriesEntity : source.getCategoriesEntities()){
            CategoriesDTO categoriesDTO = new CategoriesDTO();
            categoriesDTO.setName(categoriesEntity.getName());
            categoriesDTO.setId(categoriesEntity.getId());
            categoriesDTOS[indexCategories++] = categoriesDTO;
        }
        bookDTO.setCategoriesDTOS(categoriesDTOS);

        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(CommentEntity commentEntity : source.getCommentEntities()){

            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(commentEntity.getId());
            commentDTO.setMessage(commentEntity.getMessage());
            commentDTO.setCreateAt(commentEntity.getCreateAt());
            commentDTO.setUpdateAt(commentEntity.getUpdateAt());

            if(commentEntity.getUserEntity() != null){
                RegisterDTO registerDTO = userEntityToRegisterDTOConverter.convert(commentEntity.getUserEntity());
                commentDTO.setRegisterDTO(registerDTO);
            }
            commentDTOS.add(commentDTO);
        }
        Collections.sort(commentDTOS, new SortCommentFollowDateCreate());

        bookDTO.setCommentDTOList(commentDTOS);

        bookDTO.setNamePoster(source.getUserEntity().getEmail());
        return bookDTO;
    }
}
