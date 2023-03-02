package com.pragma.powerup.application.exception;

public class PermissionDeniedException extends RuntimeException{
    public PermissionDeniedException(String msg) {
        super(msg);
    }
}
