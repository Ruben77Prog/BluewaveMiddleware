package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.Genero;
import com.ruben.bluewave.service.impl.GeneroServiceImpl;

public class GeneroServiceTest {

	private GeneroService service = null;

	public GeneroServiceTest() {
		service = new GeneroServiceImpl();
	}

	public void testFindById() {
		Genero genero = service.findById(1L);
		if (genero != null) {
			System.out.println("Género encontrado: " + genero.getNombre());
			System.out.println("  ID: " + genero.getId());
			System.out.println("  Código: " + genero.getCodigo());
		} else {
			System.out.println("Género no encontrado");
		}
	}

	public void testFindAll() {
		List<Genero> resultados = service.findAll();
		if (resultados != null && !resultados.isEmpty()) {
			System.out.println("Géneros encontrados: " + resultados.size());
			for (Genero g : resultados) {
				System.out.println("  - ID: " + g.getId() + ", Nombre: " + g.getNombre() + ", Código: " + g.getCodigo());
			}
		} else {
			System.out.println("No se encontraron géneros");
		}
	}

	public static void main(String[] args) {
		GeneroServiceTest test = new GeneroServiceTest();
		test.testFindById();
		// test.testFindAll();
	}
}