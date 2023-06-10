package com.crimewatch.incidentservice.service;

import com.crimewatch.incidentservice.entity.Incident;
import com.crimewatch.incidentservice.exceptions.IncidentNotFoundException;
import com.crimewatch.incidentservice.model.IncidentRequest;
import com.crimewatch.incidentservice.model.IncidentResponse;
import com.crimewatch.incidentservice.repository.IncidentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Override
    public long addIncident(IncidentRequest incidentRequest) {
        log.info("Adding Incident");
        Incident newIncident = Incident.builder().incidentType(incidentRequest.getIncidentType()).incidentDateTime(incidentRequest.getIncidentDateTime()).incidentSnapShotURL(incidentRequest.getIncidentSnapShotURL()).incidentStatus(incidentRequest.getIncidentStatus()).build();

        incidentRepository.save(newIncident);
        log.info("Incident Created");
        return newIncident.getIncidentID();
    }

    @Override
    public IncidentResponse getIncidentByID(long incidentID) {
        log.info("Finding " + incidentID);
        Incident incident = incidentRepository.findById(incidentID).orElseThrow(() -> new IncidentNotFoundException("Incident ID: " + incidentID + " not found"));
        IncidentResponse incidentResponse = new IncidentResponse();
        copyProperties(incident, incidentResponse);

        return incidentResponse;
    }

    @Override
    public List<Incident> getAllIncidents() {
        log.info("Finding all incidents");
        List<Incident> incidents = new ArrayList<>();
        incidents= incidentRepository.findAll();
        return  incidents;
    }
}
