package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.List;

import com.ruben.bluewave.dao.GeneroDAO;
import com.ruben.bluewave.model.Genero;
import com.ruben.bluewave.service.GeneroService;
import com.ruben.bluewave.util.JDBCUtils;

public class GeneroServiceImpl implements GeneroService {

	private GeneroDAO generoDAO;

	public GeneroServiceImpl() {
		this.generoDAO = new GeneroDAO();
	}

	@Override
	public List<Genero> findAll() throws Exception {
		Connection c = null;
		try {
			c = JDBCUtils.getConnection();
			return generoDAO.findAll(c);
		} finally {
			JDBCUtils.close(c, true);
		}
	}

	@Override
	public Genero findById(Long id) {
		Connection c = null;
		try {
			c = JDBCUtils.getConnection();
			return generoDAO.findById(c, id);
		} finally {
			JDBCUtils.close(c, true);
		}
	}
}