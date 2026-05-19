package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.Provincia;
import com.ruben.bluewave.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {

	private ProvinciaService service = null;

	public ProvinciaServiceTest() {
		service = new ProvinciaServiceImpl();
	}

	public void testFindById() {
		Provincia provincia = service.findById(1L);
		if (provincia != null) {
			System.out.println("Provincia encontrada:");
			print(provincia);
		} else {
			System.out.println("Provincia no encontrada");
		}
	}

	public void testFindAll() {
		List<Provincia> resultados = service.findAll();
		System.out.println("Provincias encontradas: " + resultados.size());
		print(resultados);
	}

	public void testFindByPais() {
		Long paisId = 1L;
		List<Provincia> resultados = service.findByPais(paisId);
		System.out.println("Provincias encontradas para pais " + paisId + ": " + resultados.size());
		print(resultados);
	}

	private void print(List<Provincia> provincias) {
		if (provincias == null || provincias.isEmpty()) {
			System.out.println("No hay provincias para imprimir");
			return;
		}
		for (Provincia provincia : provincias) {
			print(provincia);
		}
	}

	private void print(Provincia provincia) {
		System.out.println("  ID: " + provincia.getId());
		System.out.println("  Nombre: " + provincia.getNombre());
		System.out.println("  Pais ID: " + provincia.getPaisId());
	}

	public static void main(String[] args) {
		ProvinciaServiceTest test = new ProvinciaServiceTest();
		test.testFindById();
		// test.testFindAll();
		// test.testFindByPais();
	}
}
