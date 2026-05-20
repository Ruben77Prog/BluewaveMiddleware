package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.List;

import com.ruben.bluewave.dao.TipoPlanDAO;
import com.ruben.bluewave.model.TipoPlan;
import com.ruben.bluewave.service.TipoPlanService;
import com.ruben.bluewave.util.JDBCUtils;

public class TipoPlanServiceImpl implements TipoPlanService {

    private TipoPlanDAO dao = new TipoPlanDAO();

    @Override
    public List<TipoPlan> findAll() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<TipoPlan> lista = dao.findAll(c);
            commit = true;
            return lista;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}