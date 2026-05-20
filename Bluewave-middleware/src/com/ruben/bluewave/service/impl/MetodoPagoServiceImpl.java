package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.List;

import com.ruben.bluewave.dao.MetodoPagoDAO;
import com.ruben.bluewave.model.MetodoPago;
import com.ruben.bluewave.service.MetodoPagoService;
import com.ruben.bluewave.util.JDBCUtils;

public class MetodoPagoServiceImpl implements MetodoPagoService {

	private MetodoPagoDAO dao = new MetodoPagoDAO();

	@Override
	public List<MetodoPago> findAll() throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			List<MetodoPago> lista = dao.findAll(c);
			commit = true;
			return lista;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}
}