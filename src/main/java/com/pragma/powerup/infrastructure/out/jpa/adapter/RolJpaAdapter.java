package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.spi.IRolPersistencePort;
import com.pragma.powerup.infrastructure.exception.AlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolPersistencePort {

    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;


    @Override
    public RolModel saveRol(RolModel rolModel) {
        if(rolRepository.findByName(rolModel.getName()).isPresent()){
            throw new AlreadyExistsException("Rol " + rolModel.getName());
        }
        RolEntity rolEntity = rolRepository.save(rolEntityMapper.toEntity(rolModel));
        return rolEntityMapper.toRolModel(rolEntity);
    }

    @Override
    public List<RolModel> getAllRol() {
        List<RolEntity> entityList = rolRepository.findAll();
        if(entityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return rolEntityMapper.toRolModelList(entityList);
    }

    @Override
    public RolModel getRol(Long id) {
        Optional<RolEntity> rolEntity = rolRepository.findById(id);
        if(rolEntity.isEmpty()){
            throw new NoDataFoundException();
        }
        return rolEntityMapper.toRolModel(rolEntity.get());
    }
}
