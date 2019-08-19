package com.kunlez.bookstore.services;

import com.kunlez.bookstore.DTO.RegisterDTO;
import com.kunlez.bookstore.common.CommonMethot;
import com.kunlez.bookstore.configurations.TokenProvider;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.RoleEntity;
import com.kunlez.bookstore.entity.UserEntity;
import com.kunlez.bookstore.exception.userException.UserNotfoundException;
import com.kunlez.bookstore.repository.RoleRepository;
import com.kunlez.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServices {
    @Autowired
    private Converter<UserEntity, RegisterDTO> userEntityToRegisterDTOConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private RoleRepository roleRepository;

    public List<RegisterDTO> getAllUser(){
        List<RegisterDTO> registerDTOList = new ArrayList<>();

        for(UserEntity userEntity : userRepository.findAll()){
            if(!userEntity.getEmail().equals("admin@gmail.com")){
                registerDTOList.add(userEntityToRegisterDTOConverter.convert(userEntity));
            }
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

    public ResponseEntity<?> putEnableForAdmin(int idUser){
        if(userRepository.findById(idUser) == null){
            throw new UserNotfoundException();
        }
        UserEntity userEntity = userRepository.findById(idUser).get();

        RoleEntity roleEntity = roleRepository.findByName("ROLE_ADMIN");

        Set<RoleEntity> roleEntitySet = userEntity.getRoles();
        roleEntitySet.add(roleEntity);
        userEntity.setRoles(roleEntitySet);

        userRepository.save(userEntity);
        return ResponseEntity.ok(idUser);
    }

    public ResponseEntity<?> putDisableForAdmin(int idUser){
        if(userRepository.findById(idUser) == null){
            throw new UserNotfoundException();
        }
        UserEntity userEntity = userRepository.findById(idUser).get();

        RoleEntity roleEntity = roleRepository.findByName("ROLE_ADMIN");

        Set<RoleEntity> roleEntitySet = userEntity.getRoles();
        roleEntitySet.remove(roleEntity);
        userEntity.setRoles(roleEntitySet);

        userRepository.save(userEntity);
        return ResponseEntity.ok(idUser);
    }

    public ResponseEntity<?> get(String token){

        if(token.equals("") || token == null ){
            throw new UserNotfoundException();
        }
        String username = CommonMethot.getUserName(token, jwtTokenUtil);
        return ResponseEntity.ok(userEntityToRegisterDTOConverter.convert(userRepository.findByEmail(username)));
    }

}
