package com.kunlez.bookstore.converters.userConverter;

import com.kunlez.bookstore.DTO.RegisterDTO;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class RegisterDTOToUserEntityConverter extends Converter<RegisterDTO, UserEntity> {

    @Override
    public UserEntity convert(RegisterDTO source) {
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(source.getEmail());
        userEntity.setFirstName(source.getFirstName());
        userEntity.setLastName(source.getLastName());
        userEntity.setPassword(source.getPassword());
        userEntity.setEnable(false);
        userEntity.setLinkAvatar(source.getLinkAvatar());
        return userEntity;
    }
}
