package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Coordinates;
import com.isa2023team64.pharmacydeliverybe.service.CoordinatesService;

@Service
public class CoordinatesService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private int counter = 0;

    private final SimpMessagingTemplate messagingTemplate;

    public CoordinatesService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendCoordinatesWebSocket(List<Coordinates> updatedCoordinates) {


        
        System.out.println("RADI WEBSOCKET SERVIS " + updatedCoordinates.size());
        System.out.println("COUNTER" + counter);
        

        messagingTemplate.convertAndSend("/ws-publisher", updatedCoordinates);
        counter++;

        if (counter == 3) {
            System.out.println("SALJE BOLNICI APLIKACIJI");
        
            kafkaTemplate.send("hospital", "Delivery has finished successfully.");
        }
    }

}
