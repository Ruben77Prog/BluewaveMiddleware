package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.Ciudad;

/**
 * Servicio para la gestión y consulta de ciudades.
 */
public interface CiudadService {

	/**
	 * Obtiene el listado de ciudades pertenecientes a una provincia específica.
	 * 
	 * @param provinciaId Identificador de la provincia.
	 * @return Lista de objetos Ciudad.
	 */
	List<Ciudad> findByProvinciaId(Long provinciaId) throws Exception;

	List<Ciudad> findByPaisId(Long paisId) throws Exception;

	Ciudad findById(Long id) throws Exception;
}