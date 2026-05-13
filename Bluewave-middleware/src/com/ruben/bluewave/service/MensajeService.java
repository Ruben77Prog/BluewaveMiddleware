package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.MensajeCriteria;
import com.ruben.bluewave.model.Mensaje;

public interface MensajeService {

	/**
	 * Busca un mensaje por su id.
	 * 
	 * @param id
	 * @return
	 */
	public Mensaje findById(Long id);

	/**
	 * Busqueda estructurada de mensajes.
	 * 
	 * @param criteria Encapsula los criterios de búsqueda.
	 * @return Mensajes encontrados.
	 */
	public List<Mensaje> findBy(MensajeCriteria criteria);

	/**
	 * Busca todos los mensajes.
	 * 
	 * @return
	 */
	public List<Mensaje> findAll();

	/**
	 * Crea un nuevo mensaje.
	 * 
	 * @param mensaje Datos del mensaje a insertar.
	 * @return Id del mensaje registrado, o null si falla.
	 */
	public Long create(Mensaje mensaje);

	/**
	 * Actualiza todos los datos de un mensaje.
	 * 
	 * @param mensaje Datos a actualizar.
	 * @return Si se ha podido actualizar.
	 */
	public boolean update(Mensaje mensaje);

	/**
	 * Elimina un mensaje.
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Long id);
}