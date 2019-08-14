package com.kunlez.bookstore.controller;

import com.kunlez.bookstore.DTO.CategoriesDTO;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.CategoriesEntity;
import com.kunlez.bookstore.repository.CategoriesRepository;
import com.kunlez.bookstore.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category_books")
public class CategoryController {

    @Autowired
    CategoryServices categoryServices;

    @GetMapping
    public List<CategoriesDTO> getAll(){
        return categoryServices.getAll();
    }

}
