package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.Ciudad;
import com.ruben.bluewave.service.impl.CiudadServiceImpl;

public class CiudadServiceTest {

	private CiudadService service = null;

	public CiudadServiceTest() {
		service = new CiudadServiceImpl();
	}

	public void testFindById() {
		try {
			Ciudad ciudad = service.findById(1L);
			if (ciudad != null) {
				System.out.println("Ciudad encontrada:");
				print(ciudad);
			} else {
				System.out.println("Ciudad no encontrada");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByProvinciaId() {
		try {
			Long provinciaId = 1L;
			List<Ciudad> ciudades = service.findByProvinciaId(provinciaId);
			System.out.println("Ciudades encontradas para provincia " + provinciaId + ": " + ciudades.size());
			print(ciudades);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByPaisId() {
		try {
			Long paisId = 1L;
			List<Ciudad> ciudades = service.findByPaisId(paisId);
			System.out.println("Ciudades encontradas para pais " + paisId + ": " + ciudades.size());
			print(ciudades);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void print(List<Ciudad> ciudades) {
		if (ciudades == null || ciudades.isEmpty()) {
			System.out.println("No hay ciudades para imprimir");
			return;
		}
		for (Ciudad ciudad : ciudades) {
			print(ciudad);
		}
	}

	private void print(Ciudad ciudad) {
		System.out.println("  ID: " + ciudad.getId());
		System.out.println("  Nombre: " + ciudad.getNombre());
		System.out.println("  Provincia ID: " + ciudad.getProvinciaId());
	}

	public static void main(String[] args) {
		CiudadServiceTest test = new CiudadServiceTest();
		test.testFindById();
		// test.testFindByProvinciaId();
		// test.testFindByPaisId();
	}
}
