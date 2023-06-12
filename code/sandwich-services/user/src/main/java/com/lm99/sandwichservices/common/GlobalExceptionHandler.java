package com.lm99.sandwichservices.common;

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
        return ResponseEntity.badRequest().body(error);
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
        return ResponseEntity.badRequest().body(error);
    }

    // TODO: Specify all the other needed exceptions

}
