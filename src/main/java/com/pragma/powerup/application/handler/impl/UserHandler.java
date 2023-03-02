package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.dto.response.UserSaveResponseDto;
import com.pragma.powerup.application.exception.PermissionDeniedException;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.rol.IRolDtoMapper;
import com.pragma.powerup.application.mapper.user.IUserRequestMapper;
import com.pragma.powerup.application.mapper.user.IUserResponseMapper;
import com.pragma.powerup.application.utils.IPasswordEncryption;
import com.pragma.powerup.application.utils.IRolUserSave;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {
    private final IRolUserSave rolUserSave;
    private final IPasswordEncryption passwordEncryption;
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    private final IRolServicePort rolServicePort;
    private final IRolDtoMapper rolDtoMapper;

    private final static Long ID_ROL_OWNER = 2L;
    @Override
    public UserSaveResponseDto saveUser(UserRequestDto userRequestDto, String rolLogin) {
        String passEncrip = passwordEncryption.passwordEncryption(userRequestDto.getPassword());
        userRequestDto.setPassword(passEncrip);
        userRequestDto.setIdRol(rolUserSave.rolUserSave(rolLogin));
        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        return userResponseMapper.toSaveResponse(userServicePort.saveUser(userModel));
    }

    @Override
    public UserResponseDto getUser(Long id) {
        UserModel userModel = userServicePort.getUser(id);
        RolModel rolModel = rolServicePort.getRol(userModel.getIdRol());
        return userResponseMapper.toResponse(userServicePort.getUser(id),rolDtoMapper.toDto(rolModel));
    }

    @Override
    public void validateRolUser(Long id) {
        UserModel userModel = userServicePort.getUser(id);
        RolModel rolModel = rolServicePort.getRol(userModel.getIdRol());
        if(rolModel.getId() != ID_ROL_OWNER){
            throw new PermissionDeniedException("Rol " + rolModel.getName());
        }
    }

    @Override
    public void validateOwner(Long id, String owner) {
        userServicePort.validateOwner(id, owner);
    }

    @Override
    public Long getIdUser(String userName) {
        return userServicePort.getIdUser(userName);
    }


}
