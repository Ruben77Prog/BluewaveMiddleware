package com.ruben.bluewave.service;

import java.util.Date;
import java.util.List;

import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;

public class IncidenciaServiceTest {

	private IncidenciaService incidenciaService;

	public IncidenciaServiceTest() {
		incidenciaService = new IncidenciaServiceImpl();
	}

	private Incidencia createTestIncidencia() {
		Incidencia inc = new Incidencia();
		inc.setNumeroIncidencia("TEST-" + System.currentTimeMillis());
		inc.setTitulo("Incidencia de prueba");
		inc.setDescripcion("Descripcion automatica");
		inc.setFechaIncidencia(new Date());
		inc.setHorasEstimadas(1.5);
		inc.setTipoIncidenciaId(1L);
		inc.setContratoId(1L);
		inc.setEstadoIncidenciaId(1L);
		inc.setCreadorEmpleadoId(1L);
		return inc;
	}

	public void testCreate() {
		System.out.println();
		System.out.println("=== CREAR INCIDENCIA ===");
		try {
			Long id = incidenciaService.create(createTestIncidencia());
			if (id != null) {
				System.out.println("Incidencia creada con ID: " + id);
			} else {
				System.out.println("Error: no se pudo crear");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindById() {
		System.out.println();
		System.out.println("=== BUSCAR INCIDENCIA POR ID ===");
		try {
			Long id = 1L;
			IncidenciaDTO inc = incidenciaService.findById(id);
			if (inc != null) {
				print(inc);
			} else {
				System.out.println("No existe incidencia con ID " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindAll() {
		System.out.println();
		System.out.println("=== LISTAR INCIDENCIAS ===");
		try {
			List<IncidenciaDTO> incidencias = incidenciaService.findAll();
			System.out.println("Incidencias encontradas: " + incidencias.size());
			print(incidencias);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByCriteria() {
		System.out.println();
		System.out.println("=== BUSCAR INCIDENCIAS POR CRITERIO ===");
		try {
			IncidenciaCriteria criteria = new IncidenciaCriteria();
			criteria.setEstadoIncidenciaId(1L);
			Results<IncidenciaDTO> resultados = incidenciaService.findByCriteria(criteria, 0, 10);
			System.out.println("Total incidencias: " + resultados.getTotal());
			print(resultados.getPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testUpdate() {
		System.out.println();
		System.out.println("=== ACTUALIZAR INCIDENCIA ===");
		try {
			Incidencia incidencia = createTestIncidencia();
			Long id = incidenciaService.create(incidencia);
			if (id == null) {
				System.out.println("No se pudo crear la incidencia para actualizar");
				return;
			}

			incidencia.setId(id);
			incidencia.setTitulo("Incidencia de prueba modificada");
			boolean actualizada = incidenciaService.update(incidencia);
			System.out.println("Actualizacion: " + (actualizada ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDelete() {
		System.out.println();
		System.out.println("=== ELIMINAR INCIDENCIA ===");
		try {
			Long id = incidenciaService.create(createTestIncidencia());
			if (id == null) {
				System.out.println("No se pudo crear la incidencia para eliminar");
				return;
			}

			boolean eliminada = incidenciaService.delete(id);
			System.out.println("Eliminacion: " + (eliminada ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void print(List<IncidenciaDTO> incidencias) {
		if (incidencias == null || incidencias.isEmpty()) {
			System.out.println("No hay incidencias para imprimir");
			return;
		}
		for (IncidenciaDTO inc : incidencias) {
			print(inc);
		}
	}

	private void print(IncidenciaDTO inc) {
		System.out.println("  ID: " + inc.getId());
		System.out.println("  Numero: " + inc.getNumeroIncidencia());
		System.out.println("  Titulo: " + inc.getTitulo());
		System.out.println("  Estado: " + inc.getEstadoIncidenciaNombre());
		System.out.println("  Contrato: " + inc.getContratoNumero());
		System.out.println("  Cliente: " + inc.getClienteNombre());
	}

	public static void main(String[] args) {
		IncidenciaServiceTest test = new IncidenciaServiceTest();
		test.testFindById();
		// test.testCreate();
		// test.testFindAll();
		// test.testFindByCriteria();
		// test.testUpdate();
		// test.testDelete();
	}
}
