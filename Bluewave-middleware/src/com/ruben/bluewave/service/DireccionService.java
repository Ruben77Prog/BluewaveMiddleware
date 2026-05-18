package com.ruben.bluewave.service;

import java.util.List;
import com.ruben.bluewave.dao.criteria.DireccionCriteria;
import com.ruben.bluewave.model.Direccion;

public interface DireccionService {

    Direccion findById(Long id) throws Exception;

    List<Direccion> findByCriteria(DireccionCriteria criteria) throws Exception;

    List<Direccion> findAll() throws Exception;

    Direccion create(Direccion direccion) throws Exception;

    boolean update(Direccion direccion) throws Exception;

    boolean delete(Long id) throws Exception;
}