package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.RolModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String name;
    private String lastName;

    private Long identityDocument;
    private String phone;
    private String email;
    private String password;
    private Long idRol;


}
