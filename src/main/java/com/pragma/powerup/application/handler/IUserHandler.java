package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.dto.response.UserSaveResponseDto;

public interface IUserHandler {

    UserSaveResponseDto saveUser(UserRequestDto userRequestDto, String rolLogin);

    UserResponseDto getUser(Long id);

    void validateRolUser(Long id);

    void validateOwner(Long id, String owner);

    Long getIdUser(String userName);
}
