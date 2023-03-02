package com.pragma.powerup.domain.exception;

public class EmptyValueException extends RuntimeException{

    public EmptyValueException(String msg){
        super(msg);
    }
}
