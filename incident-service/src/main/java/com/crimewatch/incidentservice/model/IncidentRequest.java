package com.crimewatch.incidentservice.model;

import lombok.Data;

import java.sql.Time;

@Data
public class IncidentRequest {
    private String incidentType;
    private String incidentDateTime;
    private String incidentSnapShotURL;
    private int incidentStatus;
}
