package com.eyecon.back.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eyecon.back.dto.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 401 에러를 반환합니다.
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorResponse handleUsernameNotFoundException(UsernameNotFoundException e) {
    	System.out.println("globalerror실행");
        return new ErrorResponse("USER_NOT_FOUND", "The user does not exist");
    }
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 401 에러를 반환합니다.
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorResponse handleBadCredentialsException(BadCredentialsException e) {
    	System.out.println("globalerror실행");
        return new ErrorResponse("USER_NOT_FOUND", "password error");
    }
    
    
    
}
