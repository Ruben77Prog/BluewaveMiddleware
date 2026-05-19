package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.EstadoClienteCriteria;
import com.ruben.bluewave.model.EstadoCliente;
import com.ruben.bluewave.service.impl.EstadoClienteServiceImpl;

public class EstadoClienteServiceTest {

	private EstadoClienteService service = null;

	public EstadoClienteServiceTest() {
		service = new EstadoClienteServiceImpl();
	}

	private EstadoCliente createTestEstadoCliente() {
		EstadoCliente estado = new EstadoCliente();
		estado.setNombre("Estado test " + System.currentTimeMillis());
		estado.setDescripcion("Estado creado desde EstadoClienteServiceTest");
		return estado;
	}

	public void testFindById() {
		EstadoCliente estado = service.findById(1L);
		if (estado != null) {
			System.out.println("Estado cliente encontrado:");
			print(estado);
		} else {
			System.out.println("Estado cliente no encontrado");
		}
	}

	public void testFindAll() {
		try {
			List<EstadoCliente> resultados = service.findAll();
			System.out.println("Estados cliente encontrados: " + resultados.size());
			print(resultados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindBy() {
		try {
			EstadoClienteCriteria criteria = new EstadoClienteCriteria();
			criteria.setNombre("Activo");
			List<EstadoCliente> resultados = service.findBy(criteria);
			System.out.println("Resultados encontrados: " + resultados.size());
			print(resultados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCreate() {
		try {
			Long id = service.create(createTestEstadoCliente());
			if (id != null) {
				System.out.println("Estado cliente creado con ID: " + id);
			} else {
				System.out.println("No se pudo crear el estado cliente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testUpdate() {
		try {
			EstadoCliente estado = createTestEstadoCliente();
			Long id = service.create(estado);
			if (id == null) {
				System.out.println("No se pudo crear el estado cliente para actualizar");
				return;
			}

			estado.setId(id);
			estado.setDescripcion("Estado cliente actualizado desde test");
			boolean actualizado = service.update(estado);
			System.out.println("Actualizacion: " + (actualizado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDelete() {
		try {
			Long id = service.create(createTestEstadoCliente());
			if (id == null) {
				System.out.println("No se pudo crear el estado cliente para eliminar");
				return;
			}
			boolean eliminado = service.delete(id);
			System.out.println("Eliminacion: " + (eliminado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void print(List<EstadoCliente> estados) {
		if (estados == null || estados.isEmpty()) {
			System.out.println("No hay estados cliente para imprimir");
			return;
		}
		for (EstadoCliente estado : estados) {
			print(estado);
		}
	}

	private void print(EstadoCliente estado) {
		System.out.println("  ID: " + estado.getId());
		System.out.println("  Nombre: " + estado.getNombre());
		System.out.println("  Descripcion: " + estado.getDescripcion());
	}

	public static void main(String[] args) {
		EstadoClienteServiceTest test = new EstadoClienteServiceTest();
		test.testFindById();
		// test.testFindAll();
		// test.testFindBy();
		// test.testCreate();
		// test.testUpdate();
		// test.testDelete();
	}
}
