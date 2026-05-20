package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.List;

import com.ruben.bluewave.dao.EstadoContratoDAO;
import com.ruben.bluewave.model.EstadoContrato;
import com.ruben.bluewave.service.EstadoContratoService;
import com.ruben.bluewave.util.JDBCUtils;

public class EstadoContratoServiceImpl implements EstadoContratoService {

	private EstadoContratoDAO dao = new EstadoContratoDAO();

	@Override
	public List<EstadoContrato> findAll() throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			List<EstadoContrato> lista = dao.findAll(c);
			commit = true;
			return lista;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}
}