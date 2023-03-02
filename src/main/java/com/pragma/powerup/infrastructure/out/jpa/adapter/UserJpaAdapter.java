package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.AlreadyExistsException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public UserModel saveUser(UserModel userModel) {
        if (userRepository.findByEmail(userModel.getEmail()).isPresent()) {
            throw new AlreadyExistsException("User " + userModel.getEmail());
        }
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(userModel));
        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel getUser(Long id) {
        return userExist(id);
    }

    @Override
    public UserModel validateRolUser(Long id) {
        return userExist(id);
    }

    @Override
    public UserModel validateOwner(Long id, String owner) {
        Optional<UserEntity> userEntity = userRepository.findByIdAndEmail(id, owner);
        if (userEntity.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserModel(userEntity.get());
    }

    @Override
    public Long getIdUser(String userName) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(userName);
        if (userEntity.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntity.get().getId();
    }

    private UserModel userExist(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserModel(userEntity.get());
    }
}
