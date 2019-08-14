package com.kunlez.bookstore.services;

import com.kunlez.bookstore.DTO.CategoriesDTO;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.CategoriesEntity;
import com.kunlez.bookstore.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServices {
    @Autowired
    private Converter<CategoriesEntity, CategoriesDTO> categoriesEntityToCategoriesDTOConverter;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<CategoriesDTO> getAll(){

        List<CategoriesDTO> categoriesDTOS = new ArrayList<>();

        for(CategoriesEntity categoriesEntity : categoriesRepository.findAll()){
            categoriesDTOS.add(categoriesEntityToCategoriesDTOConverter.convert(categoriesEntity));
        }

        return categoriesDTOS;
    }
}
