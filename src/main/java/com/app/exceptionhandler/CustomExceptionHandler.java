package com.app.exceptionhandler;

import com.app.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler   {

    @ExceptionHandler({IllegalArgumentException.class,CustomException.class})
    public ApiResponse handleException(Exception e){
       return new ApiResponse(Boolean.FALSE,e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
