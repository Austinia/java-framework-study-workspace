package com.austinia.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class UserAdvice {
    // id == int, but no data
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserEo noSuchEle(Exception e) {
        UserEo userEo = new UserEo();
        userEo.setStateCode(404);
        userEo.setMessage(e.getLocalizedMessage());
        return userEo;
    }

    // id != int
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserEo numberFormat(Exception e) {
        UserEo userEo = new UserEo();
        userEo.setStateCode(400);
        userEo.setMessage(e.getLocalizedMessage());
        return userEo;
    }

}
