package com.austinia.utils;

import com.austinia.domain.UserEo;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {
    // id == int, but no data
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserEo noSuchElement(Exception e) {
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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public UserEo httpRequestMethodNotSupported(Exception e) {
        UserEo userEo = new UserEo();
        userEo.setStateCode(405);
        userEo.setMessage(e.getLocalizedMessage());
        return userEo;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public UserEo httpMessageNotReadable(Exception e) {
        UserEo userEo = new UserEo();
        userEo.setStateCode(400);
        userEo.setMessage("Required request body is missing");
        return userEo;
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public UserEo entityExists(Exception e) {
        UserEo userEo = new UserEo();
        userEo.setStateCode(405);
        userEo.setMessage(e.getLocalizedMessage());
        return userEo;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public UserEo IllegalArgument(Exception e) {
        UserEo userEo = new UserEo();
        userEo.setStateCode(405);
        userEo.setMessage(e.getLocalizedMessage());
        return userEo;
    }

}
