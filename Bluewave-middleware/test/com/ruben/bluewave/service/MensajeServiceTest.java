package com.ruben.bluewave.service;

import java.util.Date;
import java.util.List;

import com.ruben.bluewave.dao.criteria.MensajeCriteria;
import com.ruben.bluewave.model.Mensaje;
import com.ruben.bluewave.service.impl.MensajeServiceImpl;

public class MensajeServiceTest {

	private MensajeService service = null;

	public MensajeServiceTest() {
		service = new MensajeServiceImpl();
	}

	private Mensaje createTest() {
		Mensaje mensaje = new Mensaje();
		mensaje.setNumeroMensaje("MSG-001");
		mensaje.setAsunto("Soporte técnico");
		mensaje.setCuerpo("Requiero soporte para mi conexión");
		mensaje.setTipoMensajeId(1L);
		mensaje.setIncidenciaId(1L);
		mensaje.setTipoRemitente("cliente");
		mensaje.setClienteRemitenteId(1L);
		mensaje.setTipoDestinatario("empleado");
		mensaje.setEmpleadoDestinatarioId(1L);
		mensaje.setLeido(false);
		mensaje.setImportante(false);
		mensaje.setFechaEnvio(new Date());
		return mensaje;
	}

	public void testCreate() {
		Mensaje newMensaje = createTest();
		Long id = service.create(newMensaje);
		if (id != null) {
			System.out.println("Mensaje registrado con ID: " + id);
		} else {
			System.out.println("El registro del Mensaje falló.");
		}
	}

	public void testFindById() {
		Mensaje mensaje = service.findById(1L);
		if (mensaje != null) {
			System.out.println("Mensaje encontrado: " + mensaje.getAsunto());
		} else {
			System.out.println("Mensaje no encontrado");
		}
	}

	public void testFindAll() {
		List<Mensaje> resultados = service.findAll();
		if (resultados != null && !resultados.isEmpty()) {
			System.out.println("Mensajes encontrados: " + resultados.size());
			for (Mensaje msg : resultados) {
				System.out.println("  - " + msg.getAsunto());
			}
		} else {
			System.out.println("No se encontraron mensajes");
		}
	}

	public void testFindByCriteria() {
		MensajeCriteria criteria = new MensajeCriteria();
		criteria.setAsunto("Soporte");
		List<Mensaje> resultados = service.findBy(criteria);
		System.out.println("Resultados encontrados: " + (resultados != null ? resultados.size() : 0));
		if (resultados != null && !resultados.isEmpty()) {
			for (Mensaje msg : resultados) {
				System.out.println("Mensaje encontrado:");
				System.out.println("  ID: " + msg.getId());
				System.out.println("  Asunto: " + msg.getAsunto());
				System.out.println("  Cuerpo: " + msg.getCuerpo());
				System.out.println("  Leído: " + msg.getLeido());
				System.out.println();
			}
		}
	}

	public void testUpdate() {
		Mensaje mensaje = service.findById(1L);
		if (mensaje != null) {
			mensaje.setLeido(true);
			boolean resultado = service.update(mensaje);
			System.out.println("Actualización: " + (resultado ? "OK" : "Falló"));
		}
	}

	public void testDelete() {
		boolean resultado = service.delete(1L);
		System.out.println("Eliminación: " + (resultado ? "OK" : "Falló"));
	}

	public static void main(String[] args) {
		MensajeServiceTest test = new MensajeServiceTest();
		test.testCreate();
		// test.testFindById();
		// test.testFindAll();
		// test.testFindByCriteria();
		// test.testUpdate();
		// test.testDelete();
	}
}