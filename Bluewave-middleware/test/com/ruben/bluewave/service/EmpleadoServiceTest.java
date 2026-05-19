package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.EmpleadoCriteria;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;

public class EmpleadoServiceTest {

	private EmpleadoService service = null;

	public EmpleadoServiceTest() {
		service = new EmpleadoServiceImpl();
	}

	private EmpleadoDTO createTestEmpleado() {
		EmpleadoDTO empleado = new EmpleadoDTO();
		empleado.setNombre("Empleado test");
		empleado.setApellido1("Apellido1");
		empleado.setApellido2("Apellido2");
		empleado.setDni("99999999T");
		empleado.setTelefono("600000000");
		empleado.setEmail("empleado.test." + System.currentTimeMillis() + "@email.com");
		empleado.setPassword("abc123.");
		empleado.setActivo(true);
		empleado.setRolId(1L);
		empleado.setGeneroId(1L);
		empleado.setDireccionId(1L);
		return empleado;
	}

	public void testCreate() {
		try {
			Long id = service.create(createTestEmpleado());
			if (id != null) {
				System.out.println("Empleado creado con ID: " + id);
			} else {
				System.out.println("No se pudo crear el empleado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindById() {
		try {
			EmpleadoDTO empleado = service.findById(1L);
			if (empleado != null) {
				System.out.println("Empleado encontrado:");
				print(empleado);
			} else {
				System.out.println("Empleado no encontrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByCriteria() {
		try {
			EmpleadoCriteria criteria = new EmpleadoCriteria();
			criteria.setActivo(true);
			Results<EmpleadoDTO> resultados = service.findByCriteria(criteria, 0, 10);
			System.out.println("Total empleados: " + resultados.getTotal());
			print(resultados.getPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByEmail() {
		try {
			String email = "empleado@email.com";
			List<EmpleadoDTO> resultados = service.findByEmail(email);
			System.out.println("Empleados encontrados por email: " + resultados.size());
			print(resultados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByRol() {
		try {
			Long rolId = 1L;
			List<EmpleadoDTO> resultados = service.findByRol(rolId);
			System.out.println("Empleados encontrados por rol " + rolId + ": " + resultados.size());
			print(resultados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindActivos() {
		try {
			List<EmpleadoDTO> resultados = service.findActivos();
			System.out.println("Empleados activos encontrados: " + resultados.size());
			print(resultados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindAll() {
		try {
			List<EmpleadoDTO> resultados = service.findAll();
			System.out.println("Empleados encontrados: " + resultados.size());
			print(resultados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testUpdate() {
		try {
			EmpleadoDTO empleado = createTestEmpleado();
			Long id = service.create(empleado);
			if (id == null) {
				System.out.println("No se pudo crear el empleado para actualizar");
				return;
			}

			EmpleadoDTO creado = service.findById(id);
			creado.setTelefono("611111111");
			boolean actualizado = service.update(creado);
			System.out.println("Actualizacion: " + (actualizado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testActivarDesactivar() {
		try {
			EmpleadoDTO empleado = createTestEmpleado();
			Long id = service.create(empleado);
			if (id == null) {
				System.out.println("No se pudo crear el empleado para activar/desactivar");
				return;
			}

			boolean desactivado = service.desactivar(id);
			boolean activado = service.activar(id);
			System.out.println("Desactivar: " + (desactivado ? "OK" : "Fallo"));
			System.out.println("Activar: " + (activado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCambiarRol() {
		try {
			Long empleadoId = 1L;
			Long nuevoRolId = 1L;
			boolean actualizado = service.cambiarRol(empleadoId, nuevoRolId);
			System.out.println("Cambio de rol: " + (actualizado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testLogin() {
		try {
			EmpleadoDTO empleado = service.login("empleado@email.com", "abc123.");
			if (empleado != null) {
				System.out.println("Login empleado OK: " + empleado.getNombre());
			} else {
				System.out.println("Login empleado fallo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDelete() {
		try {
			EmpleadoDTO empleado = createTestEmpleado();
			Long id = service.create(empleado);
			if (id == null) {
				System.out.println("No se pudo crear el empleado para eliminar");
				return;
			}

			boolean eliminado = service.delete(id);
			System.out.println("Eliminacion: " + (eliminado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void print(List<EmpleadoDTO> empleados) {
		if (empleados == null || empleados.isEmpty()) {
			System.out.println("No hay empleados para imprimir");
			return;
		}
		for (EmpleadoDTO empleado : empleados) {
			print(empleado);
		}
	}

	private void print(EmpleadoDTO empleado) {
		System.out.println("  ID: " + empleado.getId());
		System.out.println("  Nombre: " + empleado.getNombre() + " " + empleado.getApellido1());
		System.out.println("  Email: " + empleado.getEmail());
		System.out.println("  Activo: " + empleado.getActivo());
		System.out.println("  Rol ID: " + empleado.getRolId());
	}

	public static void main(String[] args) {
		EmpleadoServiceTest test = new EmpleadoServiceTest();
		test.testFindById();
		// test.testFindByCriteria();
		// test.testFindByEmail();
		// test.testFindByRol();
		// test.testFindActivos();
		// test.testFindAll();
		// test.testCreate();
		// test.testUpdate();
		// test.testActivarDesactivar();
		// test.testCambiarRol();
		// test.testLogin();
		// test.testDelete();
	}
}
