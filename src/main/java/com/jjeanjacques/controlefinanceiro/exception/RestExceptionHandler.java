package com.jjeanjacques.controlefinanceiro.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {NoHandlerFoundException.class, NoSuchElementException.class,
            EmptyResultDataAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseException notFoundException(HttpServletRequest request, Exception exception) {
        return new ResponseException(request, "Not found", exception.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, InvalidParameterException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseException badRequestException(HttpServletRequest request,
                                                 MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        HashMap<String, String> detail = new HashMap<>();
        for (FieldError fieldError : errors) {
            detail.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseException(request, "Invalid Arguments", detail);
    }
}
