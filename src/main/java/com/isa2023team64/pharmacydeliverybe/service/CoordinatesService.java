package com.isa2023team64.pharmacydeliverybe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Coordinates;
import com.isa2023team64.pharmacydeliverybe.repository.CoordinatesRepository;
import com.isa2023team64.pharmacydeliverybe.service.CoordinatesService;

@Service
public class CoordinatesService{
 
    @Autowired
    private CoordinatesRepository coordinatesRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private int counter = 0;

    private final SimpMessagingTemplate messagingTemplate;

    public CoordinatesService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public List<Coordinates> findAll() {
        return coordinatesRepository.findAll();
    }

    public Coordinates findById(Integer id) {
        return coordinatesRepository.findById(id).orElseThrow();
    }

    public Coordinates save(Coordinates coordinates) {
        return coordinatesRepository.save(coordinates);
    }

    public Coordinates update(Coordinates coordinates) {
        return coordinatesRepository.save(coordinates);
    }

    public void sendCoordinatesWebSocket(List<Coordinates> updatedCoordinates) {
        
        System.out.println("RADI WEBSOCKET SERVIS" + updatedCoordinates.size());

        messagingTemplate.convertAndSend("/ws-publisher", updatedCoordinates);
        counter++;

        if (counter == 3) {
            kafkaTemplate.send("hospital", "Delivery has finished successfully.");
        }
    }


}
