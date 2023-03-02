package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.infrastructure.exception.AlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static com.pragma.powerup.factory.FactoryRolDataTest.getRolEntity;
import static com.pragma.powerup.factory.FactoryRolDataTest.getRolModel;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
class RolJpaAdapterTest {
    @InjectMocks
    RolJpaAdapter rolJpaAdapter;
    @Mock
    IRolRepository rolRepository;
    @Mock
    IRolEntityMapper rolEntityMapper;
    @Test
    void mustSaveARol() {
        //Given
        //Yo como usuario quiero guardar un rol
        RolModel rolModel = getRolModel();
        RolEntity rolEntity = getRolEntity();

        //When
        //Le envio todos los datos correctamente
        when(rolRepository.findByName(rolModel.getName())).thenReturn(Optional.empty());
        when(rolEntityMapper.toEntity(rolModel)).thenReturn(rolEntity);
        when(rolRepository.save(rolEntity)).thenReturn(rolEntity);
        when(rolEntityMapper.toRolModel(rolEntity)).thenReturn(new RolModel());

        //Then
        //El sistema me guardar un nuevo rol a la base de datos
        assertNotNull(rolJpaAdapter.saveRol(rolModel));
    }
    @Test
    void throwAlreadyExistsExceptionWhenAttempSaveRol(){
        //Given
        //Yo como usuario quiero guardar un sol que ya existe
        RolModel rolModel = getRolModel();

        //When
        //Envio un rol que ya existe

        when(rolRepository.findByName(anyString())).thenReturn(Optional.of(getRolEntity()));

        //Then
        //El sistema retorna una exception del tipo AlreadyExistsException
        assertThrows(AlreadyExistsException.class, () -> rolJpaAdapter.saveRol(rolModel));


    }

    @Test
    void mustGetAllRol() {
        //Given
        //Yo como usuario quiero obtener todos los roles

        RolModel rolModel = getRolModel();
        RolEntity rolEntity = getRolEntity();

        //When
        //Me obtiene todos los roles
        when(rolRepository.findAll()).thenReturn(Arrays.asList(rolEntity));
        when(rolEntityMapper.toRolModelList(Arrays.asList(rolEntity))).thenReturn(Arrays.asList(rolModel));
        assertNotNull(rolJpaAdapter.getAllRol());
    }
    @Test
    void throwNoDataFoundExceptionWhenNotRolesExist() {
        //Given
        //Yo como usuario quiero consultar los roles con la tabla de roles vacia
        when(rolRepository.findAll()).thenReturn(Arrays.asList());

        //Then
        //El sistema retorna una exception del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> rolJpaAdapter.getAllRol());

    }

    @Test
    void mustGetRol() {
        //Given
        //Yo como usuario quiero obtener un rol por id

        RolModel rolModel = getRolModel();
        RolEntity rolEntity = getRolEntity();

        //When
        //Me obtiene un rol
        when(rolRepository.findById(anyLong())).thenReturn(Optional.of(rolEntity));
        when(rolEntityMapper.toRolModel(rolEntity)).thenReturn(rolModel);

        //Then
        assertNotNull(rolJpaAdapter.getRol(anyLong()));;
    }
    @Test
    void throwNoDataFoundExceptionWhenRolNotExists(){
        //Given
        //Yo como usuario quiero obtener un rol que no existe

        when(rolRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Then
        //El sistema retorna una exception del tipo NoDataFoundException
        assertThrows(
                NoDataFoundException.class, () -> rolJpaAdapter.getRol(anyLong())
        );

    }
}