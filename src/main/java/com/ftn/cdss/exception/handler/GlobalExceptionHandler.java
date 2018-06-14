package com.ftn.cdss.exception.handler;

import com.ftn.cdss.controller.dto.ErrorDto;
import com.ftn.cdss.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity badRequestException(BadRequestException exception) {
//        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ForbiddenException.class)
//    public ResponseEntity forbiddenException(ForbiddenException exception) {
//        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.FORBIDDEN);
//    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(RuleWithSameNameExistsException.class)
//    public ResponseEntity sameRuleNameRequestException(RuleWithSameNameExistsException exception) {
//        return new ResponseEntity<>(new ErrorDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
//    }
}
