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
		long suffix = System.currentTimeMillis() % 100000;

		Cliente testCliente = new Cliente();
		testCliente.setDni("77" + suffix + "Z");
		testCliente.setNombre("test" + suffix);
		testCliente.setApellido1("Perez");
		testCliente.setApellido2("Gomez");
		testCliente.setFechaNacimiento(new Date());
		testCliente.setFechaAlta(new Date());
		testCliente.setTelefono("600123456");
		testCliente.setEmail("test" + suffix + "@email.com");
		testCliente.setContrasena("abc123.");
		testCliente.setEstadoClienteId(2L);
		testCliente.setDireccionId(1L);
		testCliente.setEmpleadoAsignadoId(2L);
		testCliente.setGeneroId(1L);
		return testCliente;
	}

	public static void testCreate() {
		try {
			Long clienteId = clienteService.create(createTestCliente());
			if (clienteId != null) {
				System.out.println("Cliente registrado con ID: " + clienteId);
			} else {
				System.out.println("El registro del Cliente fallo.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindById() {
		try {
			Long clienteId = clienteService.create(createTestCliente());
			if (clienteId == null) {
				System.out.println("No se pudo crear el cliente para buscar por ID");
				return;
			}

			ClienteDTO cliente = clienteService.findById(clienteId);
			if (cliente != null) {
				System.out.println("Cliente encontrado por ID:");
				System.out.println(cliente);
			} else {
				System.out.println("No se encontro el cliente con ID " + clienteId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindByEmail() {
		Cliente cliente = createTestCliente();
		try {
			Long clienteId = clienteService.create(cliente);
			if (clienteId == null) {
				System.out.println("No se pudo crear el cliente para buscar por email");
				return;
			}

			List<Cliente> resultados = clienteService.findByEmail(cliente.getEmail());
			System.out.println("Clientes encontrados por email: " + resultados.size());
			for (Cliente c : resultados) {
				System.out.println("  - ID: " + c.getId() + ", Nombre: " + c.getNombre() + ", Email: " + c.getEmail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void testFindAll() {
	    try {
	        List<ClienteDTO> clientes = clienteService.findAll();
	        if (clientes != null && !clientes.isEmpty()) {
	            System.out.println("Total clientes encontrados: " + clientes.size());
	            int limite = Math.min(10, clientes.size());
	            System.out.println("Mostrando los primeros " + limite + " clientes:");
	            for (int i = 0; i < limite; i++) {
	                ClienteDTO c = clientes.get(i);
	                System.out.println("  - ID: " + c.getId() + ", Nombre: " + c.getNombre() + " " + c.getApellido1() + ", Email: " + c.getEmail());
	            }
	            if (clientes.size() > limite) {
	                System.out.println("  ... y " + (clientes.size() - limite) + " más");
	            }
	        } else {
	            System.out.println("No se encontraron clientes");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static void testFindByCriteria() {
		ClienteCriteria criteria = new ClienteCriteria();
		int pageSize = 10;
		List<ClienteDTO> resultsPage = null;
		int from = 1;
		do {
			try {
				Results<ClienteDTO> results = clienteService.findByCriteria(criteria, from, pageSize);
				if (results != null) {
					resultsPage = results.getPage();
					System.out.println("Total clientes: " + results.getTotal());
					print(resultsPage);
					from = from + pageSize;
				} else {
					System.out.println("No se encontraron resultados o ocurrio un error");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		} while (resultsPage != null && resultsPage.size() == pageSize);
	}

	public static void testLoginOk() {
		Cliente loginCliente = new Cliente();
		loginCliente.setEmail("test77@email.com");
		loginCliente.setContrasena("abc123.");

		try {
			Cliente resultado = clienteService.login(loginCliente);
			if (resultado != null) {
				System.out.println("Login exitoso: " + resultado.getNombre());
			} else {
				System.out.println("Login fallo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testLoginEmailNoExiste() {
		Cliente loginCliente = new Cliente();
		loginCliente.setEmail("noexiste@email.com");
		loginCliente.setContrasena("cualquiercosa");

		try {
			Cliente resultado = clienteService.login(loginCliente);
			if (resultado == null) {
				System.out.println("Test correcto: No se encontro el email");
			} else {
				System.out.println("Error: Se encontro un email que no deberia existir");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {
		try {
			Cliente cliente = createTestCliente();
			Long clienteId = clienteService.create(cliente);
			if (clienteId == null) {
				System.out.println("No se pudo crear el cliente para actualizar");
				return;
			}

			cliente.setId(clienteId);
			cliente.setTelefono("611111111");
			boolean actualizado = clienteService.update(cliente);
			System.out.println("Actualizacion: " + (actualizado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testUpdatePassword() {
		try {
			Cliente cliente = createTestCliente();
			Long clienteId = clienteService.create(cliente);
			if (clienteId == null) {
				System.out.println("No se pudo crear el cliente para cambiar contrasena");
				return;
			}

			boolean actualizado = clienteService.updatePassword(clienteId, "abc123.", "abc1234.");
			System.out.println("Cambio de contrasena: " + (actualizado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {
		try {
			Cliente cliente = createTestCliente();
			Long clienteId = clienteService.create(cliente);
			if (clienteId == null) {
				System.out.println("No se pudo crear el cliente para eliminar");
				return;
			}

			cliente.setId(clienteId);
			boolean eliminado = clienteService.delete(cliente);
			System.out.println("Eliminacion: " + (eliminado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void print(List<ClienteDTO> resultsPage) {
		if (resultsPage == null || resultsPage.isEmpty()) {
			System.out.println("No hay clientes para imprimir");
			return;
		}
		System.out.println("Imprimiendo pagina (" + resultsPage.size() + " clientes)...");
		for (ClienteDTO cliente : resultsPage) {
			System.out.println(cliente);
		}
	}

	public static void main(String[] args) {
		testCreate();
		testFindByCriteria();
		testFindById();
		testFindByEmail();
		testLoginOk();
		testLoginEmailNoExiste();
		testUpdate();
		testUpdatePassword();
		testDelete();
//		testFindAll();
	}
}
