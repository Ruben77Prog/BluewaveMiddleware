package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.EmpleadoCriteria;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.Results;

public interface EmpleadoService {

	/**
	 * Crea un nuevo empleado en el sistema.
	 *
	 * @param empleado datos del empleado a registrar
	 * @return id del empleado creado o null si falla
	 */
	Long create(EmpleadoDTO empleado) throws Exception;

	/**
	 * Busca un empleado por su id.
	 *
	 * @param id identificador del empleado
	 * @return empleado encontrado o null
	 */
	EmpleadoDTO findById(Long id) throws Exception;

	/**
	 * Búsqueda paginada de empleados por criterios.
	 *
	 * @param criteria filtros de búsqueda
	 * @param from     índice inicial (paginación)
	 * @param pageSize tamaño de página
	 * @return resultados paginados
	 */
	Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria, int from, int pageSize) throws Exception;

	


	/**
	 * Busca empleados por correo.
	 */
	List<EmpleadoDTO> findByEmail(String email) throws Exception;

	/**
	 * Busca empleados por rol.
	 */
	List<EmpleadoDTO> findByRol(Long rolId) throws Exception;

	/**
	 * Lista empleados activos.
	 */
	List<EmpleadoDTO> findActivos() throws Exception;

	/**
	 * Lista todos los empleados.
	 */
	List<EmpleadoDTO> findAll() throws Exception;

	/**
	 * Actualiza un empleado.
	 */
	boolean update(EmpleadoDTO empleado) throws Exception;

	/**
	 * Activa un empleado.
	 */
	boolean activar(Long empleadoId) throws Exception;

	/**
	 * Desactiva un empleado.
	 */
	boolean desactivar(Long empleadoId) throws Exception;

	/**
	 * Cambia el rol de un empleado.
	 */
	boolean cambiarRol(Long empleadoId, Long nuevoRolId) throws Exception;

	/**
	 * Login del empleado.
	 * @throws Exception 
	 */
	EmpleadoDTO login(String correo, String contrasena) throws Exception;

	/**
	 * Elimina un empleado.
	 * @throws Exception 
	 */
	boolean delete(Long id) throws Exception;


}