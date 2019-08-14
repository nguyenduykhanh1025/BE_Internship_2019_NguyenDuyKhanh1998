package com.kunlez.bookstore.controller;

import com.kunlez.bookstore.DTO.AuthorDTO;
import com.kunlez.bookstore.services.AuthorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorServices authorServices;
    @GetMapping
    public List<AuthorDTO> getAll(){
        return authorServices.getAll();
    }
}
