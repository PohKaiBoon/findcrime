package com.crimewatch.incidentservice.controller;

import com.crimewatch.incidentservice.entity.Incident;
import com.crimewatch.incidentservice.model.IncidentRequest;
import com.crimewatch.incidentservice.model.IncidentResponse;
import com.crimewatch.incidentservice.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/incident")
@RestController
public class IncidentController {
    @Autowired
    private IncidentService incidentService;

    @GetMapping("/test")
    private String testMessage(){
        return "Can connect!";
    }
    @PostMapping
    public ResponseEntity<String> newIncident(IncidentRequest incidentRequest) {

        long incidentID = incidentService.addIncident(incidentRequest);
        return new ResponseEntity<>("Incident created, ID: "+incidentID, HttpStatus.CREATED);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Incident>> getAllIncident() {

        List<Incident> incidents = incidentService.getAllIncidents();
        return new ResponseEntity<>(incidents, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getIncidentByID(@PathVariable("id") long incidentID ){

        IncidentResponse incidentResponse= incidentService.getIncidentByID(incidentID);
        return new ResponseEntity<>(incidentResponse, HttpStatus.OK);
    }
}
