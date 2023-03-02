package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.exception.AlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.pragma.powerup.factory.FactoryUserDataTest.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserJpaAdapterTest {
    @InjectMocks
    UserJpaAdapter userJpaAdapter;
    @Mock
    IUserRepository userRepository;
    @Mock
    IUserEntityMapper userEntityMapper;

    @Test
    void mustSaveAUser() {
        //Given
        //yo como usuario quiero guardar un usuario
        UserModel userModel = getUserModel();
        UserEntity userEntity = getUserEntityNotId();
        UserEntity userEntity2 = getUserEntity();

        //When
        // le envio todos los datos correctamente

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userEntityMapper.toEntity(any(UserModel.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity2);

        when(userEntityMapper.toUserModel(userEntity2)).thenReturn(new UserModel());

        //then
        //El sistema me guarda un nuevo usuario en la base de datos
        assertNotNull(userJpaAdapter.saveUser(userModel));
    }

    @Test
    void throwAlreadyExistsExceptionWhenAttemptSaveUser() {
        //Given
        //Yo como usuario quiero guardar un usuario que ya existe
        UserModel userModel = getUserModel();

        //When
        //Envio un usuario que ya existe
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(getUserEntity()));

        //Then
        //El sistema retorna una exception del tipo AlreadyExistsException
        assertThrows(
                AlreadyExistsException.class, () -> userJpaAdapter.saveUser(userModel)
        );
    }

    @Test
    void mustGetUser() {
        //Given
        //Yo como usuario quiero consultar un usuario que ya existe
        UserEntity userEntity = getUserEntity();
        UserModel userModel = getUserModel();

        //When
        //Le envio un id de un usuario que ya exista

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toUserModel(userEntity)).thenReturn(userModel);

        //Then
        //El sistema me retorna un usuario
        assertNotNull(userJpaAdapter.getUser(1L));

    }

    @Test
    void mustValidateRolUser() {
        //Given
        //Yo como usuario quiero consultar un usuario que ya existe y tenga de rol propietario
        UserEntity userEntity = getUserEntity();
        UserModel userModel = getUserModel();

        //When
        //Le envio un id de un usuario que ya exista con el rol propiertario

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toUserModel(userEntity)).thenReturn(userModel);

        //Then
        //El sistema me retorna un usuario
        assertNotNull(userJpaAdapter.validateRolUser(1L));
    }

    @Test
    void throwNoDataFoundExceptionWhenUserNotExists(){
        //Given
        //Yo como usuario quiero consultar un usuario que no existe
        Long userId = 1L;

        //When
        //Le envio un id de un usuario que no existe

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Then
        //El sistema retorna una exception del tipo NoDataFoundException
        assertThrows(
                NoDataFoundException.class, () -> userJpaAdapter.getUser(userId)
        );
    }

    @Test
    void mustGetIsUserByUserName(){
        //Given
        //Yo como usuario quiero obatner el id del usuario a traves del email
        String userName = "cri@upo.com.co";
        UserEntity userEntity = getUserEntity();

        //When
        //Le envio un username valido y que exista en la base de datos
        when(userRepository.findByEmail(userName)).thenReturn(Optional.of(userEntity));

        //Then
        //El sistema me retorna el id del usuario
        assertThat(userEntity.getId()).isEqualTo(userJpaAdapter.getIdUser(userName));

    }
    @Test
    void throwNoDataFoundExceptionWhenUserNotExitsByUserName(){
        //Given
        //Yo como usuario quiero obatner el id del usuario a traves del email
        String userName = "noExiste@upo.com.co";

        //When
        //Le envio un username valido y que no existe en la base de datos
        when(userRepository.findByEmail(userName)).thenReturn(Optional.empty());

        //Then
        //El sistema retorna una exception del tipo NoDataFoundException
        assertThrows(
                NoDataFoundException.class, () -> userJpaAdapter.getIdUser(userName)
        );
    }



}