package com.crimewatch.incidentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentRequest {
    private String incidentType;
    private String incidentDateTime;
    private String incidentSnapShotURL;
    private int incidentStatus;
}
