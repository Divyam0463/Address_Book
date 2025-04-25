package com.example.address_book.Advice;

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
    @ExceptionHandler
    public Map<String,String> handle_exception(MethodArgumentNotValidException ex){
        HashMap<String,String> error_map = new HashMap<>() ;
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            error_map.put(error.getField(), error.getDefaultMessage()) ;
        });
        return error_map ;
    }
}
