package com.ruben.bluewave.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.impl.ContratoServiceImpl;

public class ContratoServiceTest {

	private ContratoService contratoService = null;

	public ContratoServiceTest() {
		contratoService = new ContratoServiceImpl();
	}

	private ContratoDTO createTestContrato() {
		ContratoDTO testContrato = new ContratoDTO();
		testContrato.setNumeroContrato("CNT-TEST-" + System.currentTimeMillis());
		testContrato.setFechaInicio(new Date());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		testContrato.setFechaFin(cal.getTime());

		testContrato.setCostoInstalacion(50.0);
		testContrato.setCostoMensual(39.99);
		testContrato.setMetodoPagoId(1L);
		testContrato.setClienteId(1L);
		testContrato.setPlanId(1L);
		testContrato.setEstadoContratoId(1L);
		return testContrato;
	}

	public void testCreate() {
		try {
			ContratoDTO newContrato = createTestContrato();
			Long contratoId = contratoService.create(newContrato);
			if (contratoId != null) {
				System.out.println("Contrato registrado con ID: " + contratoId);
			} else {
				System.out.println("El registro del Contrato fallo.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindById() {
		try {
			Long idBuscar = 1L;
			ContratoDTO contrato = contratoService.findById(idBuscar);
			if (contrato != null) {
				System.out.println("testFindById Ok");
				print(contrato);
			} else {
				System.out.println("No se encontro el contrato con ID " + idBuscar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByCliente() {
		try {
			Long clienteId = 1L;
			List<ContratoDTO> contratos = contratoService.findByCliente(clienteId);
			System.out.println("Contratos encontrados para cliente ID " + clienteId + ": " + contratos.size());
			print(contratos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByNumeroContrato() {
		try {
			String numeroContrato = "CNT";
			List<ContratoDTO> contratos = contratoService.findByNumeroContrato(numeroContrato);
			System.out.println("Resultados encontrados: " + contratos.size());
			print(contratos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByEstado() {
		try {
			Long estadoId = 1L;
			List<ContratoDTO> contratos = contratoService.findByEstado(estadoId);
			System.out.println("Contratos en estado " + estadoId + ": " + contratos.size());
			print(contratos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindByCriteria() {
		try {
			ContratoCriteria criteria = new ContratoCriteria();
			criteria.setClienteId(1L);
			criteria.setEstadoContratoId(1L);

			Results<ContratoDTO> resultados = contratoService.findByCriteria(criteria, 0, 10);
			System.out.println("Total encontrados: " + resultados.getTotal());
			print(resultados.getPage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testUpdate() {
		try {
			ContratoDTO contrato = createTestContrato();
			Long id = contratoService.create(contrato);
			if (id == null) {
				System.out.println("No se pudo crear el contrato para actualizar");
				return;
			}

			ContratoDTO creado = contratoService.findById(id);
			creado.setCostoMensual(49.99);
			boolean actualizado = contratoService.update(creado);
			System.out.println("Actualizacion: " + (actualizado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDelete() {
		try {
			ContratoDTO contrato = createTestContrato();
			Long id = contratoService.create(contrato);
			if (id == null) {
				System.out.println("No se pudo crear el contrato para eliminar");
				return;
			}

			contratoService.delete(id);
			ContratoDTO eliminado = contratoService.findById(id);
			System.out.println("Eliminacion: " + (eliminado == null ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void print(List<ContratoDTO> contratos) {
		if (contratos == null || contratos.isEmpty()) {
			System.out.println("No hay contratos para imprimir");
			return;
		}
		for (ContratoDTO contrato : contratos) {
			print(contrato);
		}
	}

	private void print(ContratoDTO contrato) {
		System.out.println("  ID: " + contrato.getId());
		System.out.println("  Numero: " + contrato.getNumeroContrato());
		System.out.println("  Cliente: " + contrato.getClienteNombre() + " " + contrato.getClienteApellido());
		System.out.println("  Plan: " + contrato.getPlanNombre());
		System.out.println("  Estado: " + contrato.getEstadoContratoNombre());
		System.out.println("  Costo mensual: " + contrato.getCostoMensual());
		System.out.println();
	}

	public static void main(String[] args) {
		ContratoServiceTest test = new ContratoServiceTest();
		test.testFindById();
		// test.testCreate();
		// test.testFindByCliente();
		// test.testFindByNumeroContrato();
		// test.testFindByEstado();
		// test.testFindByCriteria();
		// test.testUpdate();
		// test.testDelete();
	}
}
