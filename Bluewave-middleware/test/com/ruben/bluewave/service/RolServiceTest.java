package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.RolCriteria;
import com.ruben.bluewave.model.Rol;
import com.ruben.bluewave.service.impl.RolServiceImpl;

public class RolServiceTest {

	private RolService service = null;

	public RolServiceTest() {
		service = new RolServiceImpl();
	}

	private Rol createTestRol() {
		Rol rol = new Rol();
		rol.setNombre("Rol test " + System.currentTimeMillis());
		rol.setDescripcion("Rol creado desde RolServiceTest");
		rol.setActivo(true);
		return rol;
	}

	public void testCreate() {
		Rol rol = createTestRol();
		Long id = service.create(rol);
		if (id != null) {
			System.out.println("Rol creado con ID: " + id);
		} else {
			System.out.println("No se pudo crear el rol");
		}
	}

	public void testFindById() {
		Rol rol = service.findById(1L);
		if (rol != null) {
			System.out.println("Rol encontrado:");
			print(rol);
		} else {
			System.out.println("Rol no encontrado");
		}
	}

	public void testFindAll() {
		List<Rol> resultados = service.findAll();
		System.out.println("Roles encontrados: " + resultados.size());
		print(resultados);
	}

	public void testFindBy() {
		RolCriteria criteria = new RolCriteria();
		criteria.setActivo(true);
		List<Rol> resultados = service.findBy(criteria);
		System.out.println("Roles activos encontrados: " + resultados.size());
		print(resultados);
	}

	public void testUpdate() {
		Rol rol = createTestRol();
		Long id = service.create(rol);
		if (id == null) {
			System.out.println("No se pudo crear el rol para actualizar");
			return;
		}

		rol.setId(id);
		rol.setDescripcion("Rol actualizado desde test");
		boolean actualizado = service.update(rol);
		System.out.println("Actualizacion: " + (actualizado ? "OK" : "Fallo"));
	}

	public void testDelete() {
		Rol rol = createTestRol();
		Long id = service.create(rol);
		if (id == null) {
			System.out.println("No se pudo crear el rol para eliminar");
			return;
		}

		boolean eliminado = service.delete(id);
		System.out.println("Eliminacion: " + (eliminado ? "OK" : "Fallo"));
	}

	private void print(List<Rol> roles) {
		if (roles == null || roles.isEmpty()) {
			System.out.println("No hay roles para imprimir");
			return;
		}
		for (Rol rol : roles) {
			print(rol);
		}
	}

	private void print(Rol rol) {
		System.out.println("  ID: " + rol.getId());
		System.out.println("  Nombre: " + rol.getNombre());
		System.out.println("  Descripcion: " + rol.getDescripcion());
		System.out.println("  Activo: " + rol.getActivo());
	}

	public static void main(String[] args) {
		RolServiceTest test = new RolServiceTest();
		test.testFindById();
		// test.testFindAll();
		// test.testFindBy();
		// test.testCreate();
		// test.testUpdate();
		// test.testDelete();
	}
}
