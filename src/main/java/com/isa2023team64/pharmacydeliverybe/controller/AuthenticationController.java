package com.isa2023team64.pharmacydeliverybe.controller;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa2023team64.pharmacydeliverybe.dto.JwtAuthenticationRequest;
import com.isa2023team64.pharmacydeliverybe.dto.JwtChangePasswordRequest;
import com.isa2023team64.pharmacydeliverybe.dto.UserTokenState;
import com.isa2023team64.pharmacydeliverybe.model.User;
import com.isa2023team64.pharmacydeliverybe.util.TokenUtils;



//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="http://localhost:4200")
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
		System.out.println("Hello, World!");

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user);
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	@PostMapping("/change-password")
	public ResponseEntity<String> changePassword(@RequestBody JwtChangePasswordRequest changePasswordRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				changePasswordRequest.getUsername(), changePasswordRequest.getPassword()));
	
		User user = (User) authentication.getPrincipal();
		user.setPassword(changePasswordRequest.getNewPassword());
	
		return ResponseEntity.ok("Success");
	}
	


}