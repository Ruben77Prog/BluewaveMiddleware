package com.ruben.bluewave.service;

import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.service.impl.MailServiceApacheImpl;

public class MailServiceTest {

	private MailService mailService = null;

	public MailServiceTest() {
		mailService = new MailServiceApacheImpl();
	}

	public void testEnviarEmail() {
		mailService.sendEmail("bluewave.bluewave010@gmail.com", "Volamos", "La OO es alucinante");

	}

	public void testEnviarEmailMultiple() {
		List<String> destinatarios = new ArrayList<String>();
		destinatarios.add("rubenper77@gmail.com");
		destinatarios.add("bluewave.bluewave010@gmail.com");
		destinatarios.add("powerofneodent@gmail.com");

		mailService.sendEmail(destinatarios, "Volamos", "La OO es alucinante");
	}

	public static void main(String args[]) {
		MailServiceTest test = new MailServiceTest();
		test.testEnviarEmail();
		test.testEnviarEmailMultiple();
	}
}
