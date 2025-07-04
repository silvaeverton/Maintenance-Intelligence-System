package com.example.maintenance_Intelligence_system.exceptions.handler;

import com.example.maintenance_Intelligence_system.exceptions.custom.NotFoundException;
import com.example.maintenance_Intelligence_system.exceptions.response.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Error> handlerNotFoundException(NotFoundException e) {
        Error error = Error.builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .build();

        return  new ResponseEntity<>(error, HttpStatusCode.valueOf(e.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Error> handlerExceptionGenerics(Exception e){
        Error error = Error.builder()
                .status(500)
                .message(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                .build();

        return new  ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Error> handlerNotValidException(MethodArgumentNotValidException e) {
        List<Error.Fields> fieldsList = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> {
            Error.Fields fields = Error.Fields.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            fieldsList.add(fields);
        });

        Error error = Error.builder()
                .status(e.getStatusCode().value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(error,HttpStatusCode.valueOf(e.getStatusCode().value()));
    }
}