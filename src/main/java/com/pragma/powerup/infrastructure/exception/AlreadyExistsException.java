package com.pragma.powerup.infrastructure.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String msg){
        super(msg);
    }

}
