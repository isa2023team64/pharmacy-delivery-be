package com.isa2023team64.pharmacydeliverybe.listener;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa2023team64.pharmacydeliverybe.dto.ContractRequestDTO;
import com.isa2023team64.pharmacydeliverybe.model.Contract;
import com.isa2023team64.pharmacydeliverybe.service.ContractService;

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
    
    @Autowired
    private ContractService contractService;

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



    @KafkaListener(topics = "contract", groupId = "Consumer-Group-1")
    public void listenForContracts(String message, Acknowledgment acknowledgment) {
        acknowledgment.acknowledge();
        System.out.println("Received contract: " + message);
        ContractRequestDTO contract = deserialize(message);
        Contract savedContract = contractService.save(contract);
        System.out.println(savedContract.getHospital().getName());
    }

    private ContractRequestDTO deserialize(String message) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ContractRequestDTO contract = objectMapper.readValue(message, ContractRequestDTO.class);
            return contract;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Contract not valid.");
            return null;
        }
    }
}
