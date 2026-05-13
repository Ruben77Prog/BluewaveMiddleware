//package com.ruben.bluewave.service;
//
//import java.util.Date;
//import java.util.List;
//
//import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
//import com.ruben.bluewave.model.Incidencia;
//import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;
//
//public class IncidenciaServiceTest {
//
//	private IncidenciaService service = null;
//
//	public IncidenciaServiceTest() {
//		service = new IncidenciaServiceImpl();
//	}
//
//	private Incidencia createTest() {
//		Incidencia incidencia = new Incidencia();
//		incidencia.setNumeroIncidencia("INC-001");
//		incidencia.setTitulo("Problema de conexión");
//		incidencia.setDescripcion("El cliente reporta problemas de conexión");
//		incidencia.setFechaIncidencia(new Date());
//		incidencia.setHorasEstimadas(2.5);
//		incidencia.setTipoIncidenciaId(1L);
//		incidencia.setContratoId(1L);
//		incidencia.setEstadoIncidenciaId(1L);
//		
//		return incidencia;
//	}
//
//	public void testCreate() {
//		Incidencia newIncidencia = createTest();
//		Long id = service.create(newIncidencia);
//		if (id != null) {
//			System.out.println("Incidencia registrada con ID: " + id);
//		} else {
//			System.out.println("El registro de la Incidencia falló.");
//		}
//	}
//
//	public void testFindById() {
//		Incidencia incidencia = service.findById(1L);
//		if (incidencia != null) {
//			System.out.println("Incidencia encontrada: " + incidencia.getTitulo());
//		} else {
//			System.out.println("Incidencia no encontrada");
//		}
//	}
//
//	public void testFindAll() {
//		List<Incidencia> resultados = service.findAll();
//		if (resultados != null && !resultados.isEmpty()) {
//			System.out.println("Incidencias encontradas: " + resultados.size());
//			for (Incidencia inc : resultados) {
//				System.out.println("  - " + inc.getTitulo());
//			}
//		} else {
//			System.out.println("No se encontraron incidencias");
//		}
//	}
//
//	public void testFindByCriteria() {
//		IncidenciaCriteria criteria = new IncidenciaCriteria();
//		criteria.setTitulo("Problema");
//		List<Incidencia> resultados = service.findBy(criteria);
//		System.out.println("Resultados encontrados: " + (resultados != null ? resultados.size() : 0));
//		if (resultados != null && !resultados.isEmpty()) {
//			for (Incidencia inc : resultados) {
//				System.out.println("Incidencia encontrada:");
//				System.out.println("  ID: " + inc.getId());
//				System.out.println("  Título: " + inc.getTitulo());
//				System.out.println("  Descripción: " + inc.getDescripcion());
//				System.out.println();
//			}
//		}
//	}
//
//	public void testUpdate() {
//		Incidencia incidencia = service.findById(1L);
//		if (incidencia != null) {
//			incidencia.setTitulo("Problema de conexión - Actualizado");
//			boolean resultado = service.update(incidencia);
//			System.out.println("Actualización: " + (resultado ? "OK" : "Falló"));
//		}
//	}
//
//	public void testDelete() {
//		boolean resultado = service.delete(1L);
//		System.out.println("Eliminación: " + (resultado ? "OK" : "Falló"));
//	}
//
//	public static void main(String[] args) {
//		IncidenciaServiceTest test = new IncidenciaServiceTest();
//		test.testCreate();
//		// test.testFindById();
//		// test.testFindAll();
//		// test.testFindByCriteria();
//		// test.testUpdate();
//		// test.testDelete();
//	}
//}