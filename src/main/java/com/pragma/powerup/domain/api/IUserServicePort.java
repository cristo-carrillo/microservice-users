package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.UserModel;

public interface IUserServicePort {

    UserModel saveUser(UserModel userModel);
    UserModel getUser(Long id);
    void validateRolUser(Long id);
    UserModel validateOwner(Long id, String owner);

    Long getIdUser(String userName);

}
