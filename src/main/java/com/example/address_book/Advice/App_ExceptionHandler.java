package com.example.address_book.Advice;

import com.example.address_book.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class App_ExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handle_exception(MethodArgumentNotValidException ex){
        HashMap<String,String> error_map = new HashMap<>() ;
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            error_map.put(error.getField(), error.getDefaultMessage()) ;
        });
        return error_map ;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> businessException(UserNotFoundException ex){
        HashMap<String,String> error_map = new HashMap<>() ;
        error_map.put("errorMessage", ex.getMessage()) ;
        return error_map ;
    }

 }
