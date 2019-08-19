package com.kunlez.bookstore.services;

import com.kunlez.bookstore.DTO.RegisterDTO;
import com.kunlez.bookstore.common.CommonMethot;
import com.kunlez.bookstore.configurations.TokenProvider;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.RoleEntity;
import com.kunlez.bookstore.entity.UserEntity;
import com.kunlez.bookstore.exception.userException.EmailException;
import com.kunlez.bookstore.exception.userException.UserIsExistException;
import com.kunlez.bookstore.repository.RoleRepository;
import com.kunlez.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class RegisterServices {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private Converter<RegisterDTO, UserEntity> registerDTOToUserEntityConverter;

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> post(@RequestBody RegisterDTO registerDTO, HttpServletRequest request){

        if(userRepository.findByEmail(registerDTO.getEmail())!=null){
            throw new UserIsExistException();
        }
        if(!CommonMethot.isFormatEmail(registerDTO.getEmail())){
            throw new EmailException();
        }

        UserEntity userEntity = registerDTOToUserEntityConverter.convert(registerDTO);
        userEntity.setPassword( new BCryptPasswordEncoder().encode(registerDTO.getPassword()));

        // default is member
        RoleEntity roleEntity =roleRepository.findByName("ROLE_MEMBER");

        userEntity.setRoles(new HashSet<>(Arrays.asList(roleEntity)));
        userRepository.save(userEntity);

        String token = jwtTokenUtil.generateToken(registerDTO);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(registerDTO.getEmail());

        msg.setSubject("click link to config: ");
        msg.setText("blablablablab");

        javaMailSender.send(msg);

        return ResponseEntity.ok(registerDTO);
    }
}
