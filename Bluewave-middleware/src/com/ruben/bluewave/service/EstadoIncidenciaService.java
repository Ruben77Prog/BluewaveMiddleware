package com.ruben.bluewave.service;

import java.util.List;
import com.ruben.bluewave.model.EstadoIncidencia;


public interface EstadoIncidenciaService {

	/**
	 * * Busca un estado de incidencia por su ID.
	 *
	 * @param id El ID del estado de incidencia a buscar.
	 * @return El estado de incidencia encontrado, o null si no se encuentra.
	 */
	EstadoIncidencia findById(Long id);

	/**
	 * Busca todos los estados de incidencia disponibles.
	 *
	 * @return Una lista de todos los estados de incidencia.
	 */

	List<EstadoIncidencia> findAll();

}
