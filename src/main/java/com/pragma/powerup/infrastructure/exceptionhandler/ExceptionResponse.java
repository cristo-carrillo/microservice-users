package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    ALREADY_EXISTS("Is already exists"),
    IGNORED_PERMISSION_DENIED("Permission denied"),
    EMAIL_FORMAT("The email format is invalid "),
    EMPTY_VALUE("The value is empty"),
    LENGTH_VALUE("The length value is invalid"),
    NUMBER_PHONE_FORMAT("The number format is invalid");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}