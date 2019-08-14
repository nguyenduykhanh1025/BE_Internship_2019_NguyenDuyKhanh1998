package com.kunlez.bookstore.converters.authorConverter;

import com.kunlez.bookstore.DTO.AuthorDTO;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorEntityToAuthorDTOConverter extends Converter<AuthorEntity, AuthorDTO> {
    @Override
    public AuthorDTO convert(AuthorEntity source) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(source.getId());
        authorDTO.setName(source.getName());
        return authorDTO;
    }
}
