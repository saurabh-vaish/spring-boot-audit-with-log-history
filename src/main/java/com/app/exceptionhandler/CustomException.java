package com.app.exceptionhandler;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@NoArgsConstructor
public class CustomException extends RuntimeException{

    public CustomException(String s){
        super(s);
    }
}
