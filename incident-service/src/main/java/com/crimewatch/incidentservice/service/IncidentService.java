package com.crimewatch.incidentservice.service;

import com.crimewatch.incidentservice.entity.Incident;
import com.crimewatch.incidentservice.model.IncidentRequest;
import com.crimewatch.incidentservice.model.IncidentResponse;

import java.util.List;

public interface IncidentService {
    long addIncident(IncidentRequest incidentRequest);

    IncidentResponse getIncidentByID(long incidentID);

    List<Incident> getAllIncidents();
}
