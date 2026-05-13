package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;

public interface ContratoService {

	/**
	 * Crea un nuevo contrato en el sistema.
	 * 
	 * @param contrato Datos del contrato a insertar.
	 * @return Id del contrato creado, también lo setea en el objeto, si falla
	 *         retorna null.
	 */
	public Long create(ContratoDTO contrato);

	/**
	 * Busca un contrato por su id.
	 * 
	 * @param id
	 * @return Contrato encontrado o null si no existe.
	 */
	public ContratoDTO findById(Long id);

	/**
	 * Búsqueda estructurada de contratos.
	 * 
	 * @param criteria Encapsula los criterios de búsqueda.
	 * @return Lista de contratos encontrados.
	 */
	public List<ContratoDTO> findBy(ContratoCriteria criteria, int from, int pageSize);

	/**
	 * Busca contratos por cliente.
	 * 
	 * @param clienteId
	 * @return Lista de contratos del cliente.
	 */
	public List<ContratoDTO> findByCliente(Long clienteId);

	/**
	 * Busca contratos por número de contrato.
	 * 
	 * @param numeroContrato
	 * @return Lista de contratos que coincidan.
	 */
	public List<ContratoDTO> findByNumeroContrato(String numeroContrato);

	/**
	 * Busca contratos por estado.
	 * 
	 * @param estadoContratoId
	 * @return Lista de contratos con ese estado.
	 */
	public List<ContratoDTO> findByEstado(Long estadoContratoId);

	/**
	 * Actualiza todos los datos de un contrato, en base a su id.
	 * 
	 * @param contrato Datos a actualizar.
	 * @return Si se ha podido actualizar.
	 */
	public boolean update(ContratoDTO contrato);

	/**
	 * Elimina un contrato.
	 * 
	 * @param id
	 */
	public void delete(Long id);

}