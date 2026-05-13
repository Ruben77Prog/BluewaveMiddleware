package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.ClienteCriteria;
import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.Results;

public interface ClienteService {

	/**
	 * Registra un cliente dentro de la aplicacion.
	 *
	 * @param cliente datos del cliente a insertar
	 * @return id del cliente registrado o null si falla
	 */
	public Long create(Cliente cliente) throws Exception;

	/**
	 * Logea un cliente dentro de la aplicacion.
	 *
	 * @param cliente datos con email y contrasena del cliente
	 * @return Cliente logeado o null si no se ha podido logear
	 */
	public Cliente login(Cliente cliente) throws Exception;

	/**
	 * Busca clientes por email.
	 *
	 * @param email
	 * @return clientes encontrados
	 */
	public List<Cliente> findByEmail(String email) throws Exception;

	/**
	 * Busca un cliente por su id.
	 *
	 * @param id
	 * @return cliente encontrado o null
	 */
	public ClienteDTO findById(Long id) throws Exception;

	/**
	 * Modifica los datos de un cliente.
	 *
	 * @param cliente
	 * @return true si se ha modificado el cliente
	 */
	public boolean update(Cliente cliente) throws Exception;

	/**
	 * Actualiza la contrasena de un cliente.
	 *
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return true si se ha modificado la contrasena
	 */
	public boolean updatePassword(Long id, String oldPassword, String newPassword) throws Exception;

	/**
	 * Elimina un cliente de la aplicacion.
	 *
	 * @param cliente
	 * @return true si se ha eliminado el cliente
	 */
	public boolean delete(Cliente cliente) throws Exception;

	/**
	 * Busca clientes segun los criterios indicados.
	 *
	 * @param criteria
	 * @param from
	 * @param pageSize
	 * @return clientes encontrados
	 */
	public Results<ClienteDTO> findBy(ClienteCriteria criteria, int from, int pageSize) throws Exception;

	/**
	 * Busca clientes segun los criterios indicados sin paginacion explicita.
	 *
	 * @param criteria
	 * @return clientes encontrados
	 */
	public List<ClienteDTO> findByCriteria(ClienteCriteria criteria) throws Exception;
}