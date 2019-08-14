package com.kunlez.bookstore.converters.bookConverter;

import com.kunlez.bookstore.DTO.AuthorDTO;
import com.kunlez.bookstore.DTO.BookDTO;
import com.kunlez.bookstore.DTO.CategoriesDTO;
import com.kunlez.bookstore.common.CommonMethot;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.AuthorEntity;
import com.kunlez.bookstore.entity.BookEntity;
import com.kunlez.bookstore.entity.CategoriesEntity;
import com.kunlez.bookstore.repository.AuthorRepository;
import com.kunlez.bookstore.repository.CategoriesRepository;
import com.kunlez.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookDTOToBokkEntityConverter extends Converter<BookDTO, BookEntity> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public BookEntity convert(BookDTO source) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(source.getTitle());
        bookEntity.setEnable(false);
        bookEntity.setUpdatedAt(source.getUpdateAt());
        bookEntity.setCreatedAt(source.getCreatedAt());
        bookEntity.setDescription(source.getDescription());
        bookEntity.setLinkImage(source.getLinkImage());

        bookEntity.setUserEntity(userRepository.findByEmail(CommonMethot.getUserName()));

        Set<CategoriesEntity> categoriesEntities =new HashSet<>();

        for(CategoriesDTO categoriesDTO : source.getCategoriesDTOS()){
            if(categoriesRepository.findByName(CommonMethot.getStringStandard(categoriesDTO.getName().toLowerCase()).replace(" ","_")) == null){
                CategoriesEntity categoriesEntity = new CategoriesEntity();
                categoriesEntity.setName(CommonMethot.getStringStandard(categoriesDTO.getName().toLowerCase()).replace(" ","_"));
                categoriesRepository.save(categoriesEntity);
            }
            categoriesEntities.add(categoriesRepository.findByName(CommonMethot.getStringStandard(categoriesDTO.getName().toLowerCase()).replace(" ","_")));
        }
        bookEntity.setCategoriesEntities(categoriesEntities);

        Set<AuthorEntity> authorEntities = new HashSet<>();
        for(AuthorDTO authorDTO : source.getAuthorDTOS()){
            if(authorRepository.findByName(CommonMethot.getStringStandard(authorDTO.getName().toLowerCase()).replace(" ","_")) == null){
                AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setName(CommonMethot.getStringStandard(authorDTO.getName().toLowerCase()).replace(" ","_"));
                authorRepository.save(authorEntity);
            }
            authorEntities.add(authorRepository.findByName(CommonMethot.getStringStandard(authorDTO.getName().toLowerCase()).replace(" ","_")));
        }
        bookEntity.setAuthorEntities(authorEntities);


        return bookEntity;
    }
}
