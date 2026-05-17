package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.EstadoIncidenciaDAO;
import com.ruben.bluewave.model.EstadoIncidencia;
import com.ruben.bluewave.service.EstadoIncidenciaService;
import com.ruben.bluewave.util.JDBCUtils;

public class EstadoIncidenciaServiceImpl implements EstadoIncidenciaService {

    private EstadoIncidenciaDAO dao;

    public EstadoIncidenciaServiceImpl() {
        dao = new EstadoIncidenciaDAO();
    }

    @Override
    public EstadoIncidencia findById(Long id) {
        if (id == null) return null;
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            EstadoIncidencia estado = dao.findById(c, id);
            commit = true;
            return estado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<EstadoIncidencia> findAll() {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<EstadoIncidencia> lista = dao.findAll(c);
            commit = true;
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}