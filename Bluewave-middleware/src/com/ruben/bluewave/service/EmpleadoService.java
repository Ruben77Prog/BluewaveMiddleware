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
	Long create(EmpleadoDTO empleado);

	/**
	 * Busca un empleado por su id.
	 *
	 * @param id identificador del empleado
	 * @return empleado encontrado o null
	 */
	EmpleadoDTO findById(Long id);

	/**
	 * Búsqueda paginada de empleados por criterios.
	 *
	 * @param criteria filtros de búsqueda
	 * @param from índice inicial (paginación)
	 * @param pageSize tamaño de página
	 * @return resultados paginados
	 */
	Results<EmpleadoDTO> findBy(EmpleadoCriteria criteria, int from, int pageSize);

	/**
	 * Búsqueda simple sin paginación.
	 *
	 * @param criteria filtros de búsqueda
	 * @return lista de empleados
	 */
	List<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria);

	/**
	 * Busca empleados por correo.
	 */
	List<EmpleadoDTO> findByCorreo(String correo);

	/**
	 * Busca empleados por rol.
	 */
	List<EmpleadoDTO> findByRol(Long rolId);

	/**
	 * Lista empleados activos.
	 */
	List<EmpleadoDTO> findActivos();

	/**
	 * Lista todos los empleados.
	 */
	List<EmpleadoDTO> findAll();

	/**
	 * Actualiza un empleado.
	 */
	boolean update(EmpleadoDTO empleado);

	/**
	 * Activa un empleado.
	 */
	boolean activar(Long empleadoId);

	/**
	 * Desactiva un empleado.
	 */
	boolean desactivar(Long empleadoId);

	/**
	 * Cambia el rol de un empleado.
	 */
	boolean cambiarRol(Long empleadoId, Long nuevoRolId);

	/**
	 * Login del empleado.
	 */
	EmpleadoDTO login(String correo, String contrasena);

	/**
	 * Elimina un empleado.
	 */
	boolean delete(Long id);
}