package com.kunlez.bookstore.controller;

import com.kunlez.bookstore.DTO.RegisterDTO;
import com.kunlez.bookstore.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<?> get(@RequestHeader("Authorization") String token){
        return userServices.get(token);
    }

    @GetMapping("/all")
    public List<RegisterDTO> getAllUser(){
        return userServices.getAllUser();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/disable/{idUser}")
    public ResponseEntity<?> putDisableForUser(@PathVariable int idUser){
        return userServices.putDisableForUser(idUser);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/enable/{idUser}")
    public ResponseEntity<?> putEnableForUser(@PathVariable int idUser){
        return userServices.putEnableForUser(idUser);
    }
}
