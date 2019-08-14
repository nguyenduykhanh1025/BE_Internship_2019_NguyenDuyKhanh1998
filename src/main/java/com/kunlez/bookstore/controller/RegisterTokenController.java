package com.kunlez.bookstore.controller;

import com.kunlez.bookstore.configurations.TokenProvider;
import com.kunlez.bookstore.entity.UserEntity;
import com.kunlez.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;

@Controller
@RequestMapping("/api/register")
public class RegisterTokenController {

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/{token}")
    public String getRegister(@PathVariable String token){

        String email = jwtTokenUtil.getUsernameFromToken(token);
        UserEntity userEntity = userRepository.findByEmail(email);
        userEntity.setEnable(true);
        userRepository.save(userEntity);

        return "toBeContinue";
    }
}
