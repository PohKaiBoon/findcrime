package com.crimewatch.incidentservice;

import com.crimewatch.incidentservice.entity.TextMessage;
import com.crimewatch.incidentservice.model.IncidentRequest;
import com.crimewatch.incidentservice.service.IncidentService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Component
@AllArgsConstructor
public class IncidentListener {

    public static final String INCIDENT_QUEUE = "incident_queue";
    @Autowired
    private IncidentService incidentService;

    private final WebClient.Builder webClientBuilder;


    @RabbitListener(queues = INCIDENT_QUEUE)
    public void listener(String incident) {
        Gson gson = new Gson();
        IncidentRequest incidentRequest = gson.fromJson(incident, IncidentRequest.class);

        System.out.println(incident);

        long incidentID= incidentService.addIncident(incidentRequest);
        String notification = "New incident ID: " + incidentID + " Detected!" ;

        val body = "{\"message\": \""+notification+"\"}";
        System.out.println(body);

        String response = webClientBuilder.build().post()
                .uri("http://notification-service/notification/send")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

}
