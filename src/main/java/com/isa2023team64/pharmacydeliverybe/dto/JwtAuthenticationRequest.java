package com.isa2023team64.pharmacydeliverybe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationRequest {
	
    private String username;
    private String password;
    
}
