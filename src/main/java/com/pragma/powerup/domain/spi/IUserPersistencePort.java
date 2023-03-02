package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.UserModel;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel userModel);

    UserModel getUser(Long id);

    UserModel validateRolUser(Long id);

    UserModel validateOwner(Long id, String owner);

    Long getIdUser(String userName);
}
