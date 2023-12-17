package com.isa2023team64.pharmacydeliverybe.controller;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.isa2023team64.pharmacydeliverybe.dto.UserChangedPassword;
import com.isa2023team64.pharmacydeliverybe.dto.UserTokenState;
import com.isa2023team64.pharmacydeliverybe.model.Role;
import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;
import com.isa2023team64.pharmacydeliverybe.model.User;
import com.isa2023team64.pharmacydeliverybe.model.CompanyAdministrator;
import com.isa2023team64.pharmacydeliverybe.service.CompanyAdministratorService;
import com.isa2023team64.pharmacydeliverybe.service.RoleService;
import com.isa2023team64.pharmacydeliverybe.service.SystemAdministratorService;
import com.isa2023team64.pharmacydeliverybe.service.UserService;
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

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SystemAdministratorService systemAdministratorService;
	
	@Autowired
	private CompanyAdministratorService companyAdministratorService;
	
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
	public ResponseEntity<UserChangedPassword> changePassword(@RequestBody JwtChangePasswordRequest changePasswordRequest) {
        System.out.println("Greetings World");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                changePasswordRequest.getUsername(), changePasswordRequest.getPassword()));

        User user = (User) authentication.getPrincipal();
        user.setPassword(changePasswordRequest.getNewPassword());

        user = userService.update(user);
		List<Role> roles = roleService.findByUserId(user.getId());

		boolean isSystemAdministrator = false;
		boolean isCompanyAdministrator = false;


    	System.out.println("USER:");		
    	System.out.println(user.getEmail());
		System.out.println(user.getId());

		if(roles.isEmpty()){
        	System.out.println("ROLES ARE EMPTY!");
		}

		for (Role role : roles) {
        	System.out.println("ROLES:");
        	System.out.println(role.getName());
			if ("ROLE_SYSTEM_ADMIN".equals(role.getName())) {
				isSystemAdministrator = true;
				break;
			}
			if ("ROLE_COMPANY_ADMIN".equals(role.getName())) {
				isCompanyAdministrator = true;
				break;
			}
		}
		
		if (isSystemAdministrator) {
			SystemAdministrator systemAdministrator = systemAdministratorService.findByEmail(changePasswordRequest.getUsername());
			systemAdministrator.setFirstLogged(false);
			systemAdministratorService.update(systemAdministrator);
		}
		else if (isCompanyAdministrator) {
			CompanyAdministrator companyAdministrator = companyAdministratorService.findByEmail(changePasswordRequest.getUsername());
			companyAdministrator.setFirstLogin(false);
			companyAdministratorService.update(companyAdministrator);
		}

		// CompanyAdministrator companyAdministrator = companyAdministratorService.findByUserId(user.getId());
		// if(companyAdministrator != null){
		// 	System.out.println("COMPANY ADMIN IS NOT NULL");
		// 	companyAdministrator.setFirstLogged(false);
		// 	companyAdministratorService.update(companyAdministrator);
		// }

        // Prepare the UserChangedPassword response
        UserChangedPassword response = new UserChangedPassword(true, "Password changed successfully");

        return ResponseEntity.ok(response);
    }
}
