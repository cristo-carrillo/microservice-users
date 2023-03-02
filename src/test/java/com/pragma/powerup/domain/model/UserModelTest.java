package com.pragma.powerup.domain.model;

import com.pragma.powerup.domain.exception.EmailFormatException;
import com.pragma.powerup.domain.exception.EmptyValueException;
import com.pragma.powerup.domain.exception.LengthValueException;
import com.pragma.powerup.domain.exception.NumberPhoneFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserModelTest {
    @Test
    void mustCreateUserObject(){
        //Given
        //Yo como usuario quiero crear una instacia de userModel
        UserModel userModel = new UserModel(
                2L,
                "name",
                "lastName",
                1215454L,
                "+573209290211",
                "cja@ufps.edu.co",
                "password",
                1L
        );
        //Then
        //Deberia crear un objeto User
        assertNotNull(userModel);
    }
    @Test
    void throwEmailFormatExceptionWhenCreateUserObject() {
        //Given
        //Yo como usuario quiero crear una instacia de userModel con un correo invalido
        assertThrows(
                EmailFormatException.class, () ->
                        new UserModel(
                                2L,
                                "name",
                                "lastName",
                                1215454L,
                                "3209290211",
                                "email",
                                "password",
                                1L
                        )
        );
    }

    @Test
    void throwEmptyValueExceptionWhenCreateUserObject() {
        //Given
        //Yo como usuario quiero crear una instacia de userModel con un campo vacio
        assertThrows(
                EmptyValueException.class, () ->
                        new UserModel(
                                2L,
                                "name",
                                "lastName",
                                1215454L,
                                "3209290211",
                                "cja@ufps.edu.co",
                                "",
                                1L
                        )
        );
    }

    @Test
    void throwNullPointerExceptionWhenCreateUserObject() {
        //Given
        //Yo como usuario quiero crear una instacia de userModel con un campo null
        assertThrows(
                NullPointerException.class, () ->
                        new UserModel(
                                2L,
                                "name",
                                "lastName",
                                1215454L,
                                "3209290211",
                                "cja@ufps.edu.co",
                                null,
                                1L
                        )
        );
    }

    @Test
    void throwLengthValueExceptionWhenCreateUserObject() {
        //Given
        //Yo como usuario quiero crear una instacia de userModel con un numero de telefono de longitud corta
        assertThrows(
                LengthValueException.class, () ->
                        new UserModel(
                                2L,
                                "name",
                                "lastName",
                                1215454L,
                                "32092902",
                                "cja@ufps.edu.co",
                                "password",
                                1L
                        )
        );
    }
    @Test
    void throwNumberPhoneFormatExceptionWhenCreateUserObject() {
        //Given
        //Yo como usuario quiero crear una instacia de userModel con un numero de telefono invalido
        assertThrows(
                NumberPhoneFormatException.class, () ->
                        new UserModel(
                                2L,
                                "name",
                                "lastName",
                                1215454L,
                                "+209290211",
                                "cja@ufps.edu.co",
                                "password",
                                1L
                        )
        );
    }
}