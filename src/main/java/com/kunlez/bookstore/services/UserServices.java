package com.kunlez.bookstore.services;

import com.kunlez.bookstore.DTO.RegisterDTO;
import com.kunlez.bookstore.common.CommonMethot;
import com.kunlez.bookstore.configurations.TokenProvider;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.UserEntity;
import com.kunlez.bookstore.exception.userException.UserNotfoundException;
import com.kunlez.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    private Converter<UserEntity, RegisterDTO> userEntityToRegisterDTOConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider jwtTokenUtil;

    public List<RegisterDTO> getAllUser(){
        List<RegisterDTO> registerDTOList = new ArrayList<>();

        for(UserEntity userEntity : userRepository.findAll()){
            registerDTOList.add(userEntityToRegisterDTOConverter.convert(userEntity));
        }
        return registerDTOList;
    }

    public ResponseEntity<?> putDisableForUser(int idUser){
        if(userRepository.findById(idUser) == null){
            throw new UserNotfoundException();
        }
        UserEntity userEntity = userRepository.findById(idUser).get();
        userEntity.setEnable(false);
        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    public ResponseEntity<?> putEnableForUser(int idUser){
        if(userRepository.findById(idUser) == null){
            throw new UserNotfoundException();
        }
        UserEntity userEntity = userRepository.findById(idUser).get();
        userEntity.setEnable(true);
        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    public ResponseEntity<?> get(String token){

        if(token.equals("") || token == null ){
            throw new UserNotfoundException();
        }
        String username = CommonMethot.getUserName(token, jwtTokenUtil);
        return ResponseEntity.ok(userEntityToRegisterDTOConverter.convert(userRepository.findByEmail(username)));
    }
}
