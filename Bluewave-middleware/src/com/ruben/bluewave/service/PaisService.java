package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.Pais;

public interface PaisService {
	
	/**
	 * Busca un país por su ID.
	 * @param id
	 * @return
	 */

    Pais findById(Long id);
	/**
	 * Busca todos los países disponibles.
	 * @return
	 */
    List<Pais> findAll();
}