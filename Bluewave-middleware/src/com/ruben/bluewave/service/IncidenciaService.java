package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;

public interface IncidenciaService {

	/**
	 * Busca una incidencia por su id.
	 * 
	 * @param id
	 * @return
	 */
	public IncidenciaDTO findById(Long id);

	/**
	 * Busqueda estructurada de incidencias.
	 * 
	 * @param criteria Encapsula los criterios de búsqueda.
	 * @return Incidencias encontradas.
	 */
	public Results<IncidenciaDTO> findByCriteria(IncidenciaCriteria criteria, int from, int pageSize);

	/**
	 * Busca todas las incidencias.
	 * 
	 * @return
	 */
	public List<IncidenciaDTO> findAll();

	/**
	 * Crea una nueva incidencia.
	 * 
	 * @param incidencia Datos de la incidencia a insertar.
	 * @return Id de la incidencia registrada, o null si falla.
	 */
	public Long create(Incidencia incidencia);

	/**
	 * Actualiza todos los datos de una incidencia.
	 * 
	 * @param incidencia Datos a actualizar.
	 * @return Si se ha podido actualizar.
	 */
	public boolean update(Incidencia incidencia);

	/**
	 * Elimina una incidencia.
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Long id);
}
