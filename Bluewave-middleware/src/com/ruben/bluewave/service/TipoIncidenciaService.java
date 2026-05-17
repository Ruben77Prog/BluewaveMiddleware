package com.ruben.bluewave.service;

import java.util.List;
import com.ruben.bluewave.dao.criteria.TipoIncidenciaCriteria;
import com.ruben.bluewave.model.TipoIncidencia;

public interface TipoIncidenciaService {

    /**
     * Busca tipos de incidencia según criterios.
     */
    List<TipoIncidencia> findByCriteria(TipoIncidenciaCriteria criteria) throws Exception;

    /**
     * Obtiene todos los tipos de incidencia.
     */
    List<TipoIncidencia> findAll() throws Exception;
}