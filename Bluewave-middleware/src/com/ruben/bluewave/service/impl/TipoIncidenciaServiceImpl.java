package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.TipoIncidenciaDAO;
import com.ruben.bluewave.dao.criteria.TipoIncidenciaCriteria;
import com.ruben.bluewave.model.TipoIncidencia;
import com.ruben.bluewave.service.TipoIncidenciaService;
import com.ruben.bluewave.util.JDBCUtils;

public class TipoIncidenciaServiceImpl implements TipoIncidenciaService {

    private TipoIncidenciaDAO dao;

    public TipoIncidenciaServiceImpl() {
        dao = new TipoIncidenciaDAO();
    }

    @Override
    public List<TipoIncidencia> findByCriteria(TipoIncidenciaCriteria criteria) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<TipoIncidencia> list = dao.findBy(c, criteria);
            commit = true;
            return list;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<TipoIncidencia> findAll() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<TipoIncidencia> list = dao.findAll(c);
            commit = true;
            return list;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}