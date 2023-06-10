package com.crimewatch.incidentservice.exceptions;

public class IncidentNotFoundException extends RuntimeException {
    private int errorCode;

    public IncidentNotFoundException(String message) {
        super(message);
        this.errorCode=errorCode;
    }
}
