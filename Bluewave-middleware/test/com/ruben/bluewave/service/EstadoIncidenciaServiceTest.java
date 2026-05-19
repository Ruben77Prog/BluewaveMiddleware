package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.EstadoIncidencia;
import com.ruben.bluewave.service.impl.EstadoIncidenciaServiceImpl;

public class EstadoIncidenciaServiceTest {

	private EstadoIncidenciaService service = null;

	public EstadoIncidenciaServiceTest() {
		service = new EstadoIncidenciaServiceImpl();
	}

	public void testFindById() {
		EstadoIncidencia estado = service.findById(1L);
		if (estado != null) {
			System.out.println("Estado incidencia encontrado:");
			print(estado);
		} else {
			System.out.println("Estado incidencia no encontrado");
		}
	}

	public void testFindAll() {
		List<EstadoIncidencia> resultados = service.findAll();
		System.out.println("Estados incidencia encontrados: " + resultados.size());
		print(resultados);
	}

	private void print(List<EstadoIncidencia> estados) {
		if (estados == null || estados.isEmpty()) {
			System.out.println("No hay estados incidencia para imprimir");
			return;
		}
		for (EstadoIncidencia estado : estados) {
			print(estado);
		}
	}

	private void print(EstadoIncidencia estado) {
		System.out.println("  ID: " + estado.getId());
		System.out.println("  Nombre: " + estado.getNombre());
		System.out.println("  Descripcion: " + estado.getDescripcion());
		System.out.println("  Es final: " + estado.getEsFinal());
	}

	public static void main(String[] args) {
		EstadoIncidenciaServiceTest test = new EstadoIncidenciaServiceTest();
		test.testFindById();
		// test.testFindAll();
	}
}
