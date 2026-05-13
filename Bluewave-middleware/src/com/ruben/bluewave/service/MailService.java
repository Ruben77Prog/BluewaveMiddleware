package com.ruben.bluewave.service;

import java.util.List;

public interface MailService {

	/**
	 * Envia un email a un destinatario
	 * 
	 * @param para      Direccion del destinatario
	 * @param asunto    Asunto del email.
	 * @param contenido Contenido del email
	 */

	public void sendEmail(String para, String asunto, String contenido);

	/**
	 * Envia un email a multiples destinatarios
	 * 
	 * @param para      Direccion del destinatario
	 * @param asunto    Asunto del email.
	 * @param contenido Contenido del email
	 */

	public void sendEmail(List<String> destinatarios, String asunto, String contenido);
}
