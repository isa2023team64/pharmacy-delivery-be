package com.isa2023team64.pharmacydeliverybe.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.isa2023team64.pharmacydeliverybe.model.Coordinates;
import com.isa2023team64.pharmacydeliverybe.service.CoordinatesService;

/**
 * @author Febin Malik
 * @Mobile +91 9656668856
 * @Email codewithfebinmalik@gmail.com
 */

@Configuration
public class KafkaConsumer {

    @Autowired
    private CoordinatesService coordinatesService;


    @KafkaListener(topics = "Kafka-producer-topic", groupId = "Consumer-Group-1")
    public void listenGroupFoo(String message) {
        try{
            System.out.println("Received Message in group foo: " + message);
            
            String[] parts = message.split(",");
            double latitude = Double.parseDouble(parts[0].split(":")[1].trim());
            double longitude = Double.parseDouble(parts[1].split(":")[1].trim());

            Coordinates coordinates = new Coordinates(latitude, longitude);

            List<Coordinates> coordinatesList = new ArrayList<>();
            coordinatesList.add(coordinates);

            coordinatesService.sendCoordinatesWebSocket(coordinatesList);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
