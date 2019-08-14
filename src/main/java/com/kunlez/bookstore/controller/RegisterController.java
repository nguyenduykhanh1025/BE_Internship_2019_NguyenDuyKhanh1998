package com.kunlez.bookstore.controller;

import com.kunlez.bookstore.DTO.RegisterDTO;
import com.kunlez.bookstore.configurations.TokenProvider;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.RoleEntity;
import com.kunlez.bookstore.entity.UserEntity;
import com.kunlez.bookstore.exception.userException.UserIsExistException;
import com.kunlez.bookstore.repository.RoleRepository;
import com.kunlez.bookstore.repository.UserRepository;
import com.kunlez.bookstore.services.RegisterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    RegisterServices registerServices;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Validated RegisterDTO registerDTO, HttpServletRequest request){
        return registerServices.post(registerDTO, request);
    }
}
