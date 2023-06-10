package com.crimewatch.incidentservice.exceptions;

import com.crimewatch.incidentservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IncidentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIncidentServiceException(IncidentNotFoundException incidentNotFoundException) {
        return new ResponseEntity<>(new ErrorResponse().builder().errorMessage(incidentNotFoundException.getMessage()).errorCode("404").build(), HttpStatus.NOT_FOUND);
    }
}
