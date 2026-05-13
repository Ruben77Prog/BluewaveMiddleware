package com.ruben.bluewave.service;

import java.util.Date;
import java.util.List;

import com.ruben.bluewave.dao.criteria.ClienteCriteria;
import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;

public class ClienteServiceTest {

	private static ClienteService clienteService = new ClienteServiceImpl();


	private static Cliente createTestCliente() {
		Cliente testCliente = new Cliente();
		testCliente.setDni("77757777Z");
		testCliente.setNombre("test77");
		testCliente.setApellido1("Pérez");
		testCliente.setApellido2("Gómez");
		testCliente.setFechaNacimiento(new Date());
		testCliente.setFechaAlta(new Date());
		testCliente.setGeneroId(1l);
		testCliente.setTelefono("600123456");
		testCliente.setEmail("test57@email.com");
		testCliente.setContrasena("abc123.");  
		testCliente.setEstadoClienteId(2L);
		testCliente.setDireccionId(1L);
		testCliente.setEmpleadoAsignadoId(2L);
		testCliente.setGeneroId(1L);

		return testCliente;
	}

	public static void testCreate() {
		Cliente cliente = createTestCliente();  

		Long clienteId = null;
		try {
			clienteId = clienteService.create(cliente);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		if (clienteId != null) {
			System.out.println("Cliente registrado con ID: " + clienteId);
		} else {
			System.out.println("El registro del Cliente falló.");
		}
	}

	public static void testFindByCriteria() {
		ClienteCriteria criteria = new ClienteCriteria();
		int pageSize = 10;
		List<ClienteDTO> resultsPage = null;
		int from = 1;
		do {
			Results<ClienteDTO> results = null;
			try {
				results = clienteService.findBy(criteria, from, pageSize);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			if (results != null) { 
				resultsPage = results.getPage();
				System.out.println("Total clientes: " + results.getTotal());
				print(resultsPage);
				from = from + pageSize;
			} else {
				System.out.println("No se encontraron resultados o ocurrió un error");
				break;
			}
		}
		while(resultsPage != null && resultsPage.size() == pageSize);
	}
	
	private static void print(List<ClienteDTO> resultsPage) {
		if (resultsPage == null || resultsPage.isEmpty()) {
			System.out.println("No hay clientes para imprimir");
			return;
		}
		System.out.println("Imprimiendo pagina (" + resultsPage.size() + " clientes)...");
		for(ClienteDTO cliente: resultsPage) {
			System.out.println(cliente);
		}
	}


	public static void testLoginOk() {
		Cliente loginCliente = new Cliente();
		loginCliente.setEmail("test77@email.com");
		loginCliente.setContrasena("abc123.");
		
		Cliente resultado = null;
		try {
			resultado = clienteService.login(loginCliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (resultado != null) {
			System.out.println("Login exitoso: " + resultado.getNombre());
		} else {
			System.out.println("Login falló");
		}
	}
	
	public static void testLoginEmailNoExiste() {
		Cliente loginCliente = new Cliente();
		loginCliente.setEmail("noexiste@email.com");
		loginCliente.setContrasena("cualquiercosa");
		
		Cliente resultado = null;
		try {
			resultado = clienteService.login(loginCliente);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (resultado == null) {
			System.out.println("Test correcto: No se encontró el email");
		} else {
			System.out.println("Error: Se encontró un email que no debería existir");
		}
	}

	public static void main(String[] args) {
		
		
		// testCreate();
		testFindByCriteria();
		// testLoginOk();
		// testLoginEmailNoExiste();
		
		
		// testFindByCriteria();
	}
}