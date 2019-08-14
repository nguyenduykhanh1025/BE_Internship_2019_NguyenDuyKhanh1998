package com.kunlez.bookstore.services;

import com.kunlez.bookstore.DTO.AuthorDTO;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.AuthorEntity;
import com.kunlez.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServices {
    @Autowired
    private Converter<AuthorEntity, AuthorDTO> authorEntityToAuthorDTOConverter;

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> getAll(){
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        for(AuthorEntity authorEntity : authorRepository.findAll()){
            authorDTOList.add(authorEntityToAuthorDTOConverter.convert(authorEntity));
        }

        return authorDTOList;
    }
}
