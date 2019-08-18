package com.kunlez.bookstore.controller;

import com.kunlez.bookstore.common.CommonMethot;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id) {
        return "detail";
    }

    @Secured("ROLE_MEMBER")
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/author/{nametag}")
    public String author(){
        return "author";
    }

    @GetMapping("/poster/{nameposter}")
    public String poster(){
        return "poster";
    }
}
