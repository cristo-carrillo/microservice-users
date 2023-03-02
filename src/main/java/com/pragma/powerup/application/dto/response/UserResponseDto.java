package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.application.dto.RolDto;
import com.pragma.powerup.domain.model.RolModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long idDto;
    private String nameDto;
    private String lastName;

    private Long identityDocument;
    private String phone;
    private String email;
    private String password;
    private RolDto rolDto;
}
