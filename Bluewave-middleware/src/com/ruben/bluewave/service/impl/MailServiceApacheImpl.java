package com.ruben.bluewave.service.impl;

import java.util.List;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import com.ruben.bluewave.service.MailService;

/**
 * Implementacion de MailService sobre la clase MailService
 */

public class MailServiceApacheImpl implements MailService{
	
	
	
	public MailServiceApacheImpl() {
		
	}
	
	
	

	public void sendEmail(String para, String asunto, String contenido) {
	
		try {

			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setStartTLSEnabled(true);
			email.setSSLOnConnect(false);
			email.setFrom("bluewave.bluewave010@gmail.com", "Bluewave");
			email.setAuthentication("bluewave.bluewave010@gmail.com", "bewl aysi cnpp rptd");
			email.setSSLOnConnect(true);
			email.setSubject(asunto);
			email.setMsg(contenido);
			email.addTo(para);
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// bewl aysi cnpp rptd
	
	public static void main (String[] args) {
		MailServiceApacheImpl ms = new MailServiceApacheImpl();
		ms.sendEmail("bluewave.bluewave010@gmail.com", "Bienvenido", "Bienvenido a Bluewave");
	}

	
		

	
	@Override
	public void sendEmail(List<String> destinatarios, String asunto, String contenido) {
		
		
	}

	
}
