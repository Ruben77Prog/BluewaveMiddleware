package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.model.Results;

public interface ContratoService {

	/**
	 * Crea un nuevo contrato en el sistema.
	 * 
	 * @param contrato Datos del contrato a insertar.
	 * @return Id del contrato creado, también lo setea en el objeto, si falla
	 *         retorna null.
	 * @throws Exception 
	 */
	public Long create(ContratoDTO contrato) throws Exception;

	/**
	 * Busca un contrato por su id.
	 * 
	 * @param id
	 * @return Contrato encontrado o null si no existe.
	 * @throws Exception 
	 */
	public ContratoDTO findById(Long id) throws Exception;

	/**
	 * Búsqueda estructurada de contratos.
	 * 
	 * @param criteria Encapsula los criterios de búsqueda.
	 * @return Lista de contratos encontrados.
	 */
	public Results<ContratoDTO> findByCriteria(ContratoCriteria criteria, int from, int pageSize)throws Exception;

	/**
	 * Busca contratos por cliente.
	 * 
	 * @param clienteId
	 * @return Lista de contratos del cliente.
	 */
	public List<ContratoDTO> findByCliente(Long clienteId)throws Exception;

	/**
	 * Busca contratos por número de contrato.
	 * 
	 * @param numeroContrato
	 * @return Lista de contratos que coincidan.
	 */
	public List<ContratoDTO> findByNumeroContrato(String numeroContrato)throws Exception;

	/**
	 * Busca contratos por estado.
	 * 
	 * @param estadoContratoId
	 * @return Lista de contratos con ese estado.
	 */
	public List<ContratoDTO> findByEstado(Long estadoContratoId)throws Exception;

	/**
	 * Actualiza todos los datos de un contrato, en base a su id.
	 * 
	 * @param contrato Datos a actualizar.
	 * @return Si se ha podido actualizar.
	 */
	public boolean update(ContratoDTO contrato)throws Exception;

	/**
	 * Elimina un contrato.
	 * 
	 * @param id
	 * @throws Exception 
	 */
	public void delete(Long id) throws Exception;

}