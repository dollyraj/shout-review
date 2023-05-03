package com.practice.shoutreview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> resp=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String field=((FieldError)error).getField();
            String msg=error.getDefaultMessage();
            resp.put(field,msg);
        });

        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception){
        String message=exception.getMessage();
        ResourceNotFoundApiResponse apiResponse=new ResourceNotFoundApiResponse(message,false);

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
