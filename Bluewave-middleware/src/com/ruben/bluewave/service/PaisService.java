package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.Pais;

public interface PaisService {

	/**
	 * Busca un país por su ID.
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */

	Pais findById(Long id) throws Exception;

	/**
	 * Busca todos los países disponibles.
	 * 
	 * @return
	 */
	List<Pais> findAll() throws Exception;
}