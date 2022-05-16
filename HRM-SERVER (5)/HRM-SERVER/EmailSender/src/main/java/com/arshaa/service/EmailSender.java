package com.arshaa.service;

import java.util.Date;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailSender {
	@Autowired(required = true)
	private JavaMailSender mailSender;// public void sendEmail(int id,String userName,String email,String
										// password,String employeeId)
// {
//
//
//
// SimpleMailMessage msg = new SimpleMailMessage();
//
// msg.setTo(email);
// msg.setSubject("You are onboarded successfully to the SRI LAKSHMI HEAVEN'S PG");
// msg.setText("Hello "+userName+","+" Welcome to SRI LAKSHMI HEAVEN'S PG." +"\n"+"\n"+ "You are checked in to the PG successfully with the below details :"+"\n"+"\n"+
// "Employee Id: "+employeeId+"\n"+"\n");
//
// mailSender.send(msg);
//
//
//
//
// }

	public void postMail() {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("muralikrishna.miriyala@arshaa.com");
		mail.setSubject("This is the test email");
		mail.setText("This is the message from spring boot");
		mailSender.send(mail);
	}

	public void sendEmail(String Name, String userName, String email, String password, String employeeId) {
// TODO Auto-generated method stub
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Congratulations-Hr Approved Your Profile");
		msg.setText("Hello " + Name + "," + " Welcome to Arshaa Technologies." + "\n" + "\n"
				+ "These are your credentials  UserName: "+userName+" Password: "+password + "\n" + "\n" + "Employee Id: "
				+ employeeId + "\n" + "\n");
		mailSender.send(msg);
	}
}
