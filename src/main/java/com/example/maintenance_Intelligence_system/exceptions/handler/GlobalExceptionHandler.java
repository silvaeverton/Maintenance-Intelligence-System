package com.example.maintenance_Intelligence_system.exceptions.handler;

import com.example.maintenance_Intelligence_system.exceptions.custom.BadRequestException;
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
        e.printStackTrace();

        Error error = Error.builder()
                .status(500)
                .message(e.getMessage())
                .build();

        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
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

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<Error> handlerBadRequestException( BadRequestException e) {

        Error error = Error.builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .build();

        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}