package com.lm99.sandwichservices.common;

import com.lm99.sandwichservices.role.RoleNameAlreadyInUseException;
import com.lm99.sandwichservices.role.RoleNotFoundException;
import com.lm99.sandwichservices.user.UserEmailAlreadyInUseException;
import com.lm99.sandwichservices.user.UserNotFoundException;
import com.lm99.sandwichservices.user.UserUpdateWithEmptyRoleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public record GlobalExceptionHandler() {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> handleRuntimeException(RuntimeException exception) {
        Error error = new Error(
                LocalDateTime.now(ZoneId.of("UTC")),
                HttpStatus.BAD_REQUEST.value(),
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Collections.emptyList()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErrorField> errorFields = exception.getBindingResult().getAllErrors().stream()
                .map(objectError -> new ErrorField(
                        ((FieldError) objectError).getField(),
                        objectError.getDefaultMessage()
                )).toList();
        Error error = new Error(
                LocalDateTime.now(ZoneId.of("UTC")),
                HttpStatus.BAD_REQUEST.value(),
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                errorFields
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Error> handleRoleNotFoundException(RoleNotFoundException exception) {
        Error error = new Error(
                LocalDateTime.now(ZoneId.of("UTC")),
                HttpStatus.NOT_FOUND.value(),
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Collections.emptyList()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNameAlreadyInUseException.class)
    public ResponseEntity<Error> handleRoleNameAlreadyInUseException(RoleNameAlreadyInUseException exception) {
        Error error = new Error(
                LocalDateTime.now(ZoneId.of("UTC")),
                HttpStatus.CONFLICT.value(),
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Collections.emptyList()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFoundException(UserNotFoundException exception) {
        Error error = new Error(
                LocalDateTime.now(ZoneId.of("UTC")),
                HttpStatus.NOT_FOUND.value(),
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Collections.emptyList()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserEmailAlreadyInUseException.class)
    public ResponseEntity<Error> handleUserEmailAlreadyInUseException(UserEmailAlreadyInUseException exception) {
        Error error = new Error(
                LocalDateTime.now(ZoneId.of("UTC")),
                HttpStatus.CONFLICT.value(),
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Collections.emptyList()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserUpdateWithEmptyRoleException.class)
    public ResponseEntity<Error> handleUserUpdateWithEmptyRoleException(UserUpdateWithEmptyRoleException exception) {
        Error error = new Error(
                LocalDateTime.now(ZoneId.of("UTC")),
                HttpStatus.BAD_REQUEST.value(),
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Collections.emptyList()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
