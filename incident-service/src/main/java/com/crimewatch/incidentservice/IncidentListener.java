package com.crimewatch.incidentservice;

import com.crimewatch.incidentservice.controller.IncidentController;
import com.crimewatch.incidentservice.model.IncidentRequest;
import com.crimewatch.incidentservice.service.IncidentService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class IncidentListener {

    public static final String INCIDENT_QUEUE = "incident_queue";
    @Autowired
    private IncidentService incidentService;

    @RabbitListener(queues = INCIDENT_QUEUE)
    public void listener(String incident) {
        Gson gson = new Gson();
        IncidentRequest incidentRequest = gson.fromJson(incident, IncidentRequest.class);
        incidentService.addIncident(incidentRequest);
    }

}
