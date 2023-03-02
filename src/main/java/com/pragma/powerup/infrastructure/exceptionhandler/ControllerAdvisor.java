package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.application.exception.PermissionDeniedException;
import com.pragma.powerup.domain.exception.EmailFormatException;
import com.pragma.powerup.domain.exception.EmptyValueException;
import com.pragma.powerup.domain.exception.LengthValueException;
import com.pragma.powerup.domain.exception.NumberPhoneFormatException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.AlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
            AlreadyExistsException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ALREADY_EXISTS.getMessage()
                 + " : " +ignoredNoDataFoundException.getMessage()));
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<Map<String, String>> handlePermissionDeniedException(
            PermissionDeniedException ignoredPermissionDeniedException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.IGNORED_PERMISSION_DENIED.getMessage()
                        + " : " +ignoredPermissionDeniedException.getMessage()));
    }

    @ExceptionHandler(EmailFormatException.class)
    public ResponseEntity<Map<String, String>> handleEmailFormatException(
            EmailFormatException ignoredEmailFormatException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMAIL_FORMAT.getMessage()
                        + " : " +ignoredEmailFormatException.getMessage()));
    }

    @ExceptionHandler(EmptyValueException.class)
    public ResponseEntity<Map<String, String>> handleEmptyValueException(
            EmptyValueException ignoredEmptyValueException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
               .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMPTY_VALUE.getMessage()
                        + " : " +ignoredEmptyValueException.getMessage()));
    }

    @ExceptionHandler(LengthValueException.class)
    public ResponseEntity<Map<String, String>> handleLengthValueException(
            LengthValueException ignoredLengthValueException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(Collections.singletonMap(MESSAGE, ExceptionResponse.LENGTH_VALUE.getMessage()
                        + " : " +ignoredLengthValueException.getMessage()));
    }

    @ExceptionHandler(NumberPhoneFormatException.class)
    public ResponseEntity<Map<String, String>> handleNumberFormatException(
            NumberPhoneFormatException ignoredNumberPhoneFormatException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NUMBER_PHONE_FORMAT.getMessage()
                        + " : " +ignoredNumberPhoneFormatException.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ignoredException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Collections.singletonMap(MESSAGE, ignoredException.getMessage()));
    }
}