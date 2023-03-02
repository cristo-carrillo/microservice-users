package com.pragma.powerup.factory;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;

public class FactoryUserDataTest {
    public static UserModel getUserModel(){
        UserModel userModel = new UserModel();
        userModel.setName("pepe");
        userModel.setLastName("pepe");
        userModel.setIdentityDocument(124545l);
        userModel.setPhone("3209290211");
        userModel.setEmail("cri@upo.com.co");
        userModel.setIdRol(2l);
        return userModel;
    }

    public static UserEntity getUserEntityNotId(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("pepe");
        userEntity.setLastName("pepe");
        userEntity.setIdentityDocument(124545l);
        userEntity.setPhone("3209290211");
        userEntity.setEmail("cri@upo.com.co");
        userEntity.setIdRol(2l);
        return userEntity;
    }

    public static UserEntity getUserEntity(){
        UserEntity userEntity = getUserEntityNotId();
        userEntity.setId(1L);
        return userEntity;
    }

    public static UserRequestDto getUserRequestDto(){
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("pepe");
        userRequestDto.setLastName("pepe");
        userRequestDto.setIdentityDocument(124545l);
        userRequestDto.setPhone("3209290211");
        userRequestDto.setEmail("cri@upo.com.co");
        userRequestDto.setPassword("agshgasha");
        userRequestDto.setIdRol(2L);
        return userRequestDto;
    }
}
