package com.pet.controller_advice;

import com.pet.error.ErrorModel;
import com.pet.error.ResourceAlreadyExistsException;
import com.pet.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class HandlerErrors {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public String handleResourceNotFound(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseBody
    public String handleResourceExists(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorModel> handleMethodArg(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new ErrorModel(error.getField(), error.getDefaultMessage()))
                .toList();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorModel> handleConstraintViolation(ConstraintViolationException e) {
        return e.getConstraintViolations()
                .stream()
                .map(error -> new ErrorModel(error.getPropertyPath().toString(), error.getMessage()))
                .toList();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String emptyRequest(HttpMessageNotReadableException e) {
        return "body should not be null";
    }
}
