package com.proyect.alkemy.controller;

import com.proyect.alkemy.exception.SpringException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SpringException.class})
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex , WebRequest request){
        String bodyOfResponse = "Spring Exception : "+ex.getMessage();
        return handleExceptionInternal(ex,bodyOfResponse,new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class,IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex,WebRequest request){
        String bodyOfResponse = "This should be application specific ";
        return handleExceptionInternal(ex,bodyOfResponse,new HttpHeaders(),HttpStatus.CONFLICT,request);
    }

}
