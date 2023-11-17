package com.isa2023team64.pharmacydeliverybe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.isa2023team64.pharmacydeliverybe.model.RegisteredUser;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	/*
	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
	 */
	@Autowired
	private Environment env;

	/*
	 * Anotacija za oznacavanje asinhronog zadatka
	 * Vise informacija na: https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
	 */
	@Async
	public void sendNotificaitionAsync(RegisteredUser user) throws MailException, InterruptedException {
		System.out.println("Async metoda se izvrsava u drugom Threadu u odnosu na prihvaceni zahtev. Thread id: " + Thread.currentThread().getId());
		//Simulacija duze aktivnosti da bi se uocila razlika
		Thread.sleep(10000);
		System.out.println("Slanje emaila...");

		String activationLink = "http://localhost:4200/registrationComplete/"+user.getId();

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Welcome to MEDSPRESS!");
		mail.setText("Hello " + user.getFirstName() + ",\n\nWelcome to Medspress. We are pleased that you have decided to become part of our team! To activate your account, click on the following link: " + activationLink);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}

}
