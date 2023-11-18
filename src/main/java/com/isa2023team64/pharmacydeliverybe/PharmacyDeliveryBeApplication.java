package com.isa2023team64.pharmacydeliverybe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PharmacyDeliveryBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyDeliveryBeApplication.class, args);
	}

}
