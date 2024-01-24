package com.isa2023team64.pharmacydeliverybe.service.implementation;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.dto.ContractRequestDTO;
import com.isa2023team64.pharmacydeliverybe.model.Contract;
import com.isa2023team64.pharmacydeliverybe.model.ContractItem;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.model.Hospital;
import com.isa2023team64.pharmacydeliverybe.repository.ContractRepository;
import com.isa2023team64.pharmacydeliverybe.repository.EquipmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.HospitalRepository;
import com.isa2023team64.pharmacydeliverybe.service.ContractService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContractServiceImplementation implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    @Transactional
    public Contract save(ContractRequestDTO contractRequestDTO) {

        Contract contract = new Contract();

        // try {
            Hospital hospital = hospitalRepository.findById(contractRequestDTO.getHospitalId()).orElseThrow();


            Collection<ContractItem> allContractItems = new ArrayList<>();
            for (var order : contractRequestDTO.getOrders()) {
                Equipment equipment = equipmentRepository.findById(order.getEquipmentId()).orElseThrow();
                int quantity = order.getQuantity();

                ContractItem contractItem = new ContractItem(contract, equipment, quantity);

                allContractItems.add(contractItem);
            }

            contract.setDay(contractRequestDTO.getDay());
            contract.setOrders(allContractItems);

            deleteByHospital(hospital);
            
            contract.setHospital(hospital);

            contractRepository.save(contract);

            return contract;
        // } catch (Exception e) {
        //     System.out.println("Requested items don't exist.");
        //     return null;
        // }
    }

    @Transactional
    private void deleteByHospital(Hospital hospital) {
        var contracts = contractRepository.findAll();

        for (var contract : contracts) {
            if (contract.getHospital().equals(hospital)) {
                contractRepository.delete(contract);
                break;
            }
        }
    }
}
