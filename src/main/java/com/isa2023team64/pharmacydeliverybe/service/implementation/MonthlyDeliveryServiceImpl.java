package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.Contract;
import com.isa2023team64.pharmacydeliverybe.model.Delivery;
import com.isa2023team64.pharmacydeliverybe.repository.ContractRepository;
import com.isa2023team64.pharmacydeliverybe.repository.DeliveryRepository;
import com.isa2023team64.pharmacydeliverybe.repository.EquipmentRepository;
import com.isa2023team64.pharmacydeliverybe.service.MonthlyDeliveryService;
import com.isa2023team64.pharmacydeliverybe.util.enums.DeliveryStatus;

import jakarta.transaction.Transactional;

@Service
@EnableScheduling
public class MonthlyDeliveryServiceImpl implements MonthlyDeliveryService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Map<Integer, Integer> monthDays = new HashMap<Integer, Integer>() {{
        put(1, 31);
        put(2, 28);
        put(3, 31);
        put(4, 30);
        put(5, 31);
        put(6, 30);
        put(7, 31);
        put(8, 31);
        put(9, 30);
        put(10, 31);
        put(11, 30);
        put(12, 31);
    }};

    @Override
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void checkDeliveries() {
        System.out.println("scheduled activities");

        int currentYear = LocalDate.now().getYear();
        monthDays.put(2, isLeapYear(currentYear) ? 29 : 28);

        // naci sve ugovore
        var contracts = contractRepository.findAll();

        // za svaki contract uraditi posao
        for (var contract : contracts) {
            processDelivery(contract);
        }
    }

    @Override
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void startDeliveries() {
        LocalDate today = LocalDate.now();
        Collection<Delivery> deliveries = deliveryRepository.findAll().stream().filter(d -> d.getDeliveryDate().equals(today) && d.getStatus().equals(DeliveryStatus.SCHEDULED)).toList();

        for (var delivery : deliveries) {
            delivery.setStatus(DeliveryStatus.STARTED);
            String message = "Hospital " + delivery.getHospital().getId() + " ("+ delivery.getHospital().getName() + "): delivery started. Sent: " + LocalDateTime.now().toString();
            kafkaTemplate.send("hospital", message);
            deliveryRepository.save(delivery);
            // TODO: poslati location simulatoru poruku da zapocne slanje lokacija
            
        }
    }

    @Transactional
    private void processDelivery(Contract contract) {
        // odrediti trenutni mesec
        int currentMonth = LocalDate.now().getMonthValue();

        // odrediti datum isporuke
        int deliveryDateDay = Math.min(contract.getDay(), monthDays.get(currentMonth));

        // proveriti da li ima opreme na stanju
        int difference = deliveryDateDay - 3;
        int checkDate = difference;
        if (difference < 1) {
            checkDate = monthDays.get(currentMonth) + difference;
        }
        int currentDay = LocalDate.now().getDayOfMonth();
        LocalDate deliveryDate = LocalDate.now().plusDays(3);
        if (checkDate == currentDay) {
            if (checkStockCount(contract)) {
                // zakazujemo isporuku
                Delivery delivery = new Delivery(contract.getHospital(), deliveryDate, DeliveryStatus.SCHEDULED, 19.80503885486221, 45.25119792136614);
                deliveryRepository.save(delivery);
                String message = "Hospital " + contract.getHospital().getId() + " ("+ contract.getHospital().getName() + "): scheduled monthly delivery for" + deliveryDate.toString() + ". Sent: " + LocalDateTime.now().toString();
			    kafkaTemplate.send("hospital", message);
            }
            else {
                // saljemo preko kafke da nije moglo jer nema na stanju
                String message = "Hospital " + contract.getHospital().getId() + " ("+ contract.getHospital().getName() + "): not enough equipment available for monthly delivery. Sent: " + LocalDateTime.now().toString();
			    kafkaTemplate.send("hospital", message);
            }
        }
    }

    private boolean checkStockCount(Contract contract) {
        for (var contractItem : contract.getOrders()) {
            if (contractItem.getEquipment().getStockCount() < contractItem.getQuantity()) {
                return false;
            }
            int newStockCount = contractItem.getEquipment().getStockCount() - contractItem.getQuantity();
            contractItem.getEquipment().setStockCount(newStockCount);
        }
        for (var contractItem: contract.getOrders()) {
            var equipment = contractItem.getEquipment();
            equipmentRepository.save(equipment);
        }
        return true;
    }

    private boolean isLeapYear(int year) {
        if (year % 4 != 0) {
          return false;
        } else if (year % 400 == 0) {
          return true;
        } else if (year % 100 == 0) {
          return false;
        } else {
          return true;
        }
    }
    
}
