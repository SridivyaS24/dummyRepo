package com.arshaa.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.arshaa.common.UserModel;
import com.arshaa.service.EmailSender;

@RestController
@RequestMapping("/mail")
public class EmailSenderController {

	@Autowired
	EmailSender emailSender;

	@PostMapping(value = "/sendmail")
	public void send(@RequestBody UserModel model) throws AddressException, MessagingException, IOException {
		emailSender.sendEmail(model.getName(), model.getUserName(), model.getEmail(), model.getPassword(),
				model.getEmployeeId());
		
	}

	@PostMapping(value = "/postmail")

	public String send() throws AddressException, MessagingException, IOException {
		emailSender.postMail();

		return "Email Sent Successfully";
	}

}