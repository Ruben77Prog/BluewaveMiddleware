package com.ruben.bluewave.service;


import java.util.List;

import com.ruben.bluewave.dao.criteria.RolCriteria;
import com.ruben.bluewave.model.Rol;

public interface RolService {

	/**
	 * Busca un rol por su id.
	 * 
	 * @param id
	 * @return
	 */
	public Rol findById(Long id);

	/**
	 * Busqueda estructurada de roles.
	 * 
	 * @param criteria Encapsula los criterios de búsqueda.
	 * @return Roles encontrados.
	 */
	public List<Rol> findBy(RolCriteria criteria);

	/**
	 * Busca todos los roles.
	 * 
	 * @return
	 */
	public List<Rol> findAll();

	/**
	 * Crea un nuevo rol.
	 * 
	 * @param rol Datos del rol a insertar.
	 * @return Id del rol registrado, o null si falla.
	 */
	public Long create(Rol rol);

	/**
	 * Actualiza todos los datos de un rol.
	 * 
	 * @param rol Datos a actualizar.
	 * @return Si se ha podido actualizar.
	 */
	public boolean update(Rol rol);

	/**
	 * Elimina un rol.
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Long id);
}