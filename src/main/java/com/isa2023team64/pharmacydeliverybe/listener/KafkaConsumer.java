package com.isa2023team64.pharmacydeliverybe.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        try {
            System.out.println("Received Message in group foo: " + message);
    
            List<Coordinates> coordinatesList = parseMessage(message);
    
            if (coordinatesList != null && !coordinatesList.isEmpty()) {
                coordinatesService.sendCoordinatesWebSocket(coordinatesList);
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    @SuppressWarnings("unchecked")
	private List<Coordinates> parseMessage(String message) {
		ObjectMapper mapper = new ObjectMapper();
		List<Coordinates> retVal;

		try {
			retVal = mapper.readValue(message, List.class);
		} catch (IOException e) {
			retVal = null;
		}

		return retVal;
	}


}
