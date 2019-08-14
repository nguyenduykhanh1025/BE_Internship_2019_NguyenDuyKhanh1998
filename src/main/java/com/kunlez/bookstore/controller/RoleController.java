package com.kunlez.bookstore.controller;

import com.kunlez.bookstore.common.CommonMethot;
import com.kunlez.bookstore.entity.RoleEntity;
import com.kunlez.bookstore.entity.UserEntity;
import com.kunlez.bookstore.repository.UserRepository;
import com.kunlez.bookstore.services.RoleService;
import com.kunlez.bookstore.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public Set<String> get(){
        return roleService.get();
    }

    @GetMapping("/user")
    public String[] getRolesFollowNameUser(){
        return roleService.getRolesFollowNameUser();
    }

}
