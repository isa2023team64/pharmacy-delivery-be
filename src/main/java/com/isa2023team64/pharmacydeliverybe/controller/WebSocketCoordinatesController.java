package com.isa2023team64.pharmacydeliverybe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.socket.WebSocketMessage;

import java.util.List;
import java.net.URI;

import com.isa2023team64.pharmacydeliverybe.model.Coordinates;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class WebSocketCoordinatesController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    
    @MessageMapping("/delivery")
    public String handleDelivery(String message){

        System.out.println("RADI WEBSOCKETTTTTTTTTTTT");
        try{
            // String locationSimulatorUrl = "http://localhost:8001";

            // WebClient webClient = WebClient.create();

            // URI locationSimulatorURI = new URI(locationSimulatorUrl + "/delivery");

            // webClient.method(HttpMethod.POST).uri(locationSimulatorURI).bodyValue(coordinatesList).retrieve().toBodilessEntity().block();


            messagingTemplate.convertAndSend("/ws-publisher", "Delivery successful");

            return message;
        }
        catch (Exception e){
            e.printStackTrace();

            messagingTemplate.convertAndSend("/ws-publisher", "Error in delivery: " + e.getMessage());
            return("Error in delivery: " + e.getMessage());
        }
    }

    @MessageMapping("/info")
    public String handleInfo(){

        System.out.println("RADI INFOO WEBSOCKETTTTTTTTTTTT");
        try{
            // String locationSimulatorUrl = "http://localhost:8001";

            // WebClient webClient = WebClient.create();

            // URI locationSimulatorURI = new URI(locationSimulatorUrl + "/delivery");

            // webClient.method(HttpMethod.POST).uri(locationSimulatorURI).bodyValue(coordinatesList).retrieve().toBodilessEntity().block();


            messagingTemplate.convertAndSend("/ws-publisher", "Delivery Info successful");

            return "Info Hello";
        }
        catch (Exception e){
            e.printStackTrace();

            messagingTemplate.convertAndSend("/ws-publisher", "Error in delivery: " + e.getMessage());
            return("Error in delivery: " + e.getMessage());
        }
    }

    
}
