package site.ph0en1x.taskmanagementsystem.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.ph0en1x.taskmanagementsystem.model.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class) {

    }

}
