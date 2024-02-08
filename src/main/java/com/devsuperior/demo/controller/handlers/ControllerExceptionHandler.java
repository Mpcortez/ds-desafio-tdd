package com.devsuperior.demo.controller.handlers;

import com.devsuperior.demo.dto.error.CustomAdviceError;
import com.devsuperior.demo.exceptions.DatabaseException;
import com.devsuperior.demo.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomAdviceError> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        return responseEntityBuilder(getHttpStatusValue(HttpStatus.NOT_FOUND), e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomAdviceError> handleDatabaseException(DatabaseException e, HttpServletRequest request) {
        return responseEntityBuilder(getHttpStatusValue(HttpStatus.BAD_REQUEST), e.getMessage(), request.getRequestURI());
    }

    private Integer getHttpStatusValue(HttpStatus status) {
        return status.value();
    }

    private ResponseEntity<CustomAdviceError> responseEntityBuilder(Integer status, String error, String path) {
        var err = new CustomAdviceError(status, path, error);
        return ResponseEntity.status(status).body(err);
    }
}
