package com.ruben.bluewave.service.impl;

import java.util.List;

import com.ruben.bluewave.service.MailService;

public class MockMailServiceImpl implements MailService {

	public MockMailServiceImpl() {

	}

	@Override
	public void sendEmail(String para, String asunto, String contenido) {
		System.out.println("Enviando email:" + para + ": Asunto " + asunto);
		System.out.println(contenido);
		System.out.println("Enviado.");
	}

	@Override
	public void sendEmail(List<String> destinatarios, String asunto, String contenido) {
		for (String destinatario : destinatarios) {
			sendEmail(destinatarios, asunto, contenido);
		}
		System.out.println("Enviando a " + destinatarios.size() + "destinatarios");
	}

}
