package com.pragma.powerup.factory;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;

public class FactoryRolDataTest {

    public static RolModel getRolModel(){
        RolModel rolModel = new RolModel();
        rolModel.setName("Propietario");
        rolModel.setDescription("propio");
        rolModel.setId(2l);
        return rolModel;
    }

    public static RolEntity getRolEntity(){
        RolEntity rolEntity = new RolEntity();
        rolEntity.setName("Propietario");
        rolEntity.setDescription("propio");
        rolEntity.setId(2l);
        return rolEntity;
    }
}
