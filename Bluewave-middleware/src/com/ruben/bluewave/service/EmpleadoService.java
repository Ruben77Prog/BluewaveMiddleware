package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.EmpleadoCriteria;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.Results;

public interface EmpleadoService {

    Long create(EmpleadoDTO empleado) throws Exception;

    EmpleadoDTO findById(Long id) throws Exception;

    Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria, int from, int pageSize) throws Exception;

    List<EmpleadoDTO> findByEmail(String email) throws Exception;

    List<EmpleadoDTO> findByRol(Long rolId) throws Exception;

    List<EmpleadoDTO> findActivos() throws Exception;

    List<EmpleadoDTO> findAll() throws Exception;

    boolean update(EmpleadoDTO empleado) throws Exception;

    boolean activar(Long empleadoId) throws Exception;

    boolean desactivar(Long empleadoId) throws Exception;

    boolean cambiarRol(Long empleadoId, Long nuevoRolId) throws Exception;

    EmpleadoDTO login(String correo, String contrasena) throws Exception;

    boolean delete(Long id) throws Exception;
}