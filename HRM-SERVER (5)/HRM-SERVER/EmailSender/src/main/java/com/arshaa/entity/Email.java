package com.arshaa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Email")

public class Email
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	
	private String guestId;
	
	private String emailId;
	
	
	 
    private String mailTo;
 
    private String mailCc;
 
    private String mailBcc;
 
    private String mailSubject;
 
    private String mailContent;
 
    private String contentType;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailCc() {
		return mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	public String getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Email(String emailId, String mailTo, String mailCc, String mailBcc, String mailSubject, String mailContent,
			String contentType) {
		super();
		this.emailId = emailId;
		this.mailTo = mailTo;
		this.mailCc = mailCc;
		this.mailBcc = mailBcc;
		this.mailSubject = mailSubject;
		this.mailContent = mailContent;
		this.contentType = contentType;
	}

	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Email [emailId=" + emailId + ", mailTo=" + mailTo + ", mailCc=" + mailCc + ", mailBcc=" + mailBcc
				+ ", mailSubject=" + mailSubject + ", mailContent=" + mailContent + ", contentType=" + contentType
				+ "]";
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
    
    
}
