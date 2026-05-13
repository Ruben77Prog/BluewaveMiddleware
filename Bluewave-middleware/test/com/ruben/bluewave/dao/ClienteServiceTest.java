//package com.ruben.bluewave.dao;
//
//import java.util.Date;
//
//import com.ruben.bluewave.model.ClienteDTO;
//import com.ruben.bluewave.service.ClienteService;
//import com.ruben.bluewave.service.impl.ClienteServiceImpl;
//
//
//public class ClienteServiceTest {
//	
//	private ClienteService clienteService = null;
//
//	public ClienteServiceTest() {
//		clienteService = new ClienteServiceImpl();
//	}
//
//	private ClienteDTO createTestCliente() {
//		
//        // Crear un usuario de prueba 
//        ClienteDTO testCliente = new ClienteDTO();
//        testCliente.setDni("11111111H");
//        testCliente.setNombre("Juan");
//        testCliente.setApellido1("Pérez");
//        testCliente.setApellido2("Gómez");
//        testCliente.setFechaNacimiento(new Date());
//        testCliente.setGeneroNombre("M");
//        testCliente.setTelefono("600123456");
//        testCliente.setEmail("alvaro.arimore@gmail.com");
//        testCliente.setContrasena("abc123.");        
//        testCliente.setCiudadId(1L);     
//        testCliente.setEstadoClienteId(2l);
//        testCliente.setDireccionId(1l);
//        testCliente.setEmpleadoAsignadoId(2l);
//        testCliente.setGeneroId(1l);
//        
//        
//        return testCliente;
//    }
//	
//	public void testCreate() {
//		// Crear un usuario de prueba
//		ClienteDTO newCliente = createTestCliente();
//		
//		Long clienteId = clienteService.create(newCliente);
//		
//		if (clienteId != null) {
//			System.out.println("Cliente registrado con ID: " + clienteId);
//		} else {
//			System.out.println("El registro del Cliente falló."); 
//		}
//	}
//
//	public void testLogin() {
//        /*System.out.println("TEST: Login de usuario");
//        createTestUser();
//        
//        // Primero registrar el usuario
//        Long userId = userService.register(testUser);
//        if (userId == null) {
//            System.out.println("Usuario ya registrado, continuando con login...");
//        }
//        // Intentar login con las credenciales correctas
//     */   
//	}
//	
//	public static void main(String[] args) {
//		ClienteServiceTest test = new ClienteServiceTest();
//		test.testCreate();
//		//test.testLogin();
//
//	}
//
//}
//
//
