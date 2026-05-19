package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.TipoIncidenciaCriteria;
import com.ruben.bluewave.model.TipoIncidencia;
import com.ruben.bluewave.service.impl.TipoIncidenciaServiceImpl;

public class TipoIncidenciaServiceTest {

	private TipoIncidenciaService service = null;

	public TipoIncidenciaServiceTest() {
		service = new TipoIncidenciaServiceImpl();
	}

	public void testFindAll() {
		try {
			List<TipoIncidencia> resultados = service.findAll();
			System.out.println("Tipos de incidencia encontrados: " + resultados.size());
			print(resultados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByCriteria() {
		try {
			TipoIncidenciaCriteria criteria = new TipoIncidenciaCriteria();
			criteria.setActivo(true);
			List<TipoIncidencia> resultados = service.findByCriteria(criteria);
			System.out.println("Tipos de incidencia activos encontrados: " + resultados.size());
			print(resultados);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void print(List<TipoIncidencia> tipos) {
		if (tipos == null || tipos.isEmpty()) {
			System.out.println("No hay tipos de incidencia para imprimir");
			return;
		}
		for (TipoIncidencia tipo : tipos) {
			System.out.println("  ID: " + tipo.getId());
			System.out.println("  Nombre: " + tipo.getNombre());
			System.out.println("  Prioridad: " + tipo.getPrioridadDefault());
			System.out.println("  Activo: " + tipo.getActivo());
		}
	}

	public static void main(String[] args) {
		TipoIncidenciaServiceTest test = new TipoIncidenciaServiceTest();
		test.testFindAll();
		// test.testFindByCriteria();
	}
}
