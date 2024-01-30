package com.isa2023team64.pharmacydeliverybe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa2023team64.pharmacydeliverybe.model.Coordinates;
import com.isa2023team64.pharmacydeliverybe.service.DeliveryService;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class WebSocketCoordinatesController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private DeliveryService deliveryService;

    
    @MessageMapping("/delivery")
    //@PreAuthorize("hasRole('SYSTEMADMIN')")
    public List<Coordinates> handleDelivery(String message){

        System.out.println("RADI WEBSOCKET " + message);
        try{

            List<Coordinates> coordinates = parseMessage(message);

            int deliveryId = parseDeliveryId(message);

            deliveryService.finishDelivery(deliveryId);

            String locationSimulatorUrl = "http://localhost:8001";

            WebClient webClient = WebClient.create();

            URI locationSimulatorURI = new URI(locationSimulatorUrl + "/delivery");

            webClient.method(HttpMethod.POST).uri(locationSimulatorURI).bodyValue(coordinates).retrieve().toBodilessEntity().block();


            // messagingTemplate.convertAndSend("/ws-publisher", coordinates);

            return coordinates;
        }
        catch (Exception e){
            e.printStackTrace();

            messagingTemplate.convertAndSend("/ws-publisher", "Error in delivery: " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
	private List<Coordinates> parseMessage(String message) {
		ObjectMapper mapper = new ObjectMapper();
		List<Coordinates> retVal;

		try {
			retVal = mapper.readValue(message, List.class); // parsiranje JSON stringa
		} catch (IOException e) {
			retVal = null;
		}

		return retVal;
	}

    private int parseDeliveryId(String message) {
        String[] parts = message.split(":");
        String part = parts[3].split("\"")[1];
        return Integer.parseInt(part);
    }

    // @MessageMapping("/info")
    // public String handleInfo(){

    //     System.out.println("RADI INFOO WEBSOCKETTTTTTTTTTTT");
    //     try{
    //         // String locationSimulatorUrl = "http://localhost:8001";

    //         // WebClient webClient = WebClient.create();

    //         // URI locationSimulatorURI = new URI(locationSimulatorUrl + "/delivery");

    //         // webClient.method(HttpMethod.POST).uri(locationSimulatorURI).bodyValue(coordinatesList).retrieve().toBodilessEntity().block();


    //         messagingTemplate.convertAndSend("/ws-publisher", "Delivery Info successful");

    //         return "Info Hello";
    //     }
    //     catch (Exception e){
    //         e.printStackTrace();

    //         messagingTemplate.convertAndSend("/ws-publisher", "Error in delivery: " + e.getMessage());
    //         return("Error in delivery: " + e.getMessage());
    //     }
    // }

    
}
