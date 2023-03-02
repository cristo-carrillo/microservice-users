package com.pragma.powerup.infrastructure.datarol;

public enum TypeRol {

    ADMINISTRATOR("ROLE_Administrador"),
    OWNER("ROLE_Propietario"),
    EMPLOYEE("ROLE_Empleado"),
    CUSTOMER("ROLE_Cliente");

    private final String nameRol;
    TypeRol(String nameRol){
        this.nameRol = nameRol;
    }

    public String getNameRol(){
        return nameRol;
    }
}
