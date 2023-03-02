package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.exception.PermissionDeniedException;
import com.pragma.powerup.application.mapper.rol.IRolDtoMapper;
import com.pragma.powerup.application.mapper.user.IUserRequestMapper;
import com.pragma.powerup.application.mapper.user.IUserResponseMapper;
import com.pragma.powerup.application.utils.IPasswordEncryption;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.pragma.powerup.factory.FactoryRolDataTest.getRolModel;
import static com.pragma.powerup.factory.FactoryUserDataTest.getUserModel;
import static com.pragma.powerup.factory.FactoryUserDataTest.getUserRequestDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserHandlerTest {
    @InjectMocks
    UserHandler userHandler;
    @Mock
    IPasswordEncryption passwordEncryption;
    @Mock
    IUserServicePort userServicePort;
    @Mock
    IUserRequestMapper userRequestMapper;
    @Mock
    IUserResponseMapper userResponseMapper;
    @Mock
    IRolServicePort rolServicePort;
    @Mock
    IRolDtoMapper rolDtoMapper;
    @Test
    void mustSaveAUser(){
        //Given
        //yo como usuario quiero guardar un usuario
        UserRequestDto userRequestDto = getUserRequestDto();
        UserModel userModel = getUserModel();
        userModel.setPassword("asdghsdah");

//        RolModel rolModel = new RolModel();
//        rolModel.setName("Propietario");
//        rolModel.setDescription("propio");
//        rolModel.setId(1l);
        //When
        // le envio todos los datos correctamente

        when(passwordEncryption.passwordEncryption(anyString())).thenReturn("asdghsdah");
//        when(passwordEncryption.passwordEncryption(anyString())).thenReturn(anyString());
//        when(rolServicePort.getRol(anyLong())).thenReturn(rolModel);
        when(userRequestMapper.toUser(userRequestDto)).thenReturn(userModel);
        userHandler.saveUser(userRequestDto, anyString());
        //Then
        //El sistema me guarda un nuevo usuario en la base de datos
        verify(userServicePort).saveUser(any(UserModel.class));
    }
    @Test
    void mustValidateRolToUser(){
        //Given
        //yo como usuario quiero validar que un usuario sea propietario
        UserModel userModel = getUserModel();
        RolModel rolModel = getRolModel();

        //When
        // Le envio un usuario que tenga el rol de administrador
        when(userServicePort.getUser(anyLong())).thenReturn(userModel);
        when(rolServicePort.getRol(anyLong())).thenReturn(rolModel);

        userHandler.validateRolUser(2L);
        //Then
        //El sistema me valida que el usuario sea propietario
        verify(rolServicePort).getRol(2L);
    }
    @Test
    void throwPermissionDeniedExceptionWhenUserIsNotOwner(){
        //Given
        //Yo como usuario quiero consultar un usuario que no sea propietario
        UserModel userModel = getUserModel();
        RolModel rolModel = getRolModel();
        rolModel.setId(1L);
        //When
        // Le envio un usuario que tenga el rol de administrador
        when(userServicePort.getUser(anyLong())).thenReturn(userModel);
        when(rolServicePort.getRol(anyLong())).thenReturn(rolModel);

        assertThrows(PermissionDeniedException.class, () -> userHandler.validateRolUser(2L));
    }

}