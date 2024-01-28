package com.isa2023team64.pharmacydeliverybe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.dto.CompanyInfoResponseDTO;
import com.isa2023team64.pharmacydeliverybe.dto.CompanyNoAdminDTO;

import com.isa2023team64.pharmacydeliverybe.dto.CompanySearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.dto.EquipmentSearchFilterDTO;
import com.isa2023team64.pharmacydeliverybe.model.Company;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.model.Equipment;
import com.isa2023team64.pharmacydeliverybe.model.ReservationItem;
import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;
import com.isa2023team64.pharmacydeliverybe.repository.CompanyRepository;
import com.isa2023team64.pharmacydeliverybe.repository.EquipmentRepository;
import com.isa2023team64.pharmacydeliverybe.repository.ReservationItemRepository;
import com.isa2023team64.pharmacydeliverybe.repository.SystemAdministratorRepository;
import com.isa2023team64.pharmacydeliverybe.util.PagedResult;
import com.isa2023team64.pharmacydeliverybe.util.converters.PagedResultConverter;

@Service
public class ReservationItemQRService {
    
    @Autowired
    private ReservationItemRepository reservationItemRepository;


    public ReservationItem findById(Integer id){
        return reservationItemRepository.findById(id).orElse(null);
    }

    public List<ReservationItem> findAll(){
        return reservationItemRepository.findAll();
    }

    public ReservationItem save(ReservationItem reservationItem) {
        return reservationItemRepository.save(reservationItem);
    }

    public List<ReservationItem> findByReservationId(Integer id){
        return reservationItemRepository.findByReservationId(id);
    }

    

}
