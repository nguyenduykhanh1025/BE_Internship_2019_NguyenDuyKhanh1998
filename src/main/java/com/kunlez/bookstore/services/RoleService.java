package com.kunlez.bookstore.services;

import com.kunlez.bookstore.common.CommonMethot;
import com.kunlez.bookstore.entity.RoleEntity;
import com.kunlez.bookstore.entity.UserEntity;
import com.kunlez.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {

    @Autowired
    UserRepository userRepository;

    public Set<String> get(){
        return CommonMethot.getAllRole();
    }


    public String[] getRolesFollowNameUser(){
        UserEntity userEntity = userRepository.findByEmail(CommonMethot.getUserName());

        String[] roles = new String[userEntity.getRoles().size()];

        int indexRole = 0;
        for(RoleEntity roleEntity : userEntity.getRoles()){
            roles[indexRole++] = roleEntity.getName();
        }
        return roles;
    }

}
