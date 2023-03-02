package com.pragma.powerup.application.utils.imp;


import com.pragma.powerup.application.utils.IRolUserSave;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;

public class RolUserSave implements IRolUserSave {

    private static final String nameRolAdmin = "ROLE_Administrador";
    private static final String nameRolOwner = "ROLE_Propietario";
    private static final String nameRolClient = "ROLE_Cliente";
    private static final Long idRolOwner = 2L;
    private static final Long idRolEmployee = 3L;
    private static final Long idRolClient = 4L;

    @Override
    public Long rolUserSave(String rolUser) {
        switch(rolUser){
            case nameRolAdmin:
                return idRolOwner;
            case nameRolOwner:
                return idRolEmployee;
            case nameRolClient:
                return idRolClient;
            default:
                throw new NoDataFoundException();
        }
    }
}
