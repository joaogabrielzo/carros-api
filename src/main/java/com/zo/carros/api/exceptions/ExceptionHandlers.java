package com.zo.carros.api.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
    EmptyResultDataAccessException.class
  )
  public ResponseEntity notFound(Exception ex) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(
    IllegalArgumentException.class
  )
  public ResponseEntity badRequest(Exception ex) {
    return ResponseEntity.badRequest().build();
  }
}
