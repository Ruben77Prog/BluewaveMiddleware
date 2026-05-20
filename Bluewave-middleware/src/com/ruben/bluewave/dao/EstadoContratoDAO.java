package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.model.EstadoContrato;
import com.ruben.bluewave.util.JDBCUtils;

public class EstadoContratoDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion, activo FROM estado_contrato";

	public List<EstadoContrato> findAll(Connection c) {
		List<EstadoContrato> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(BASE_QUERY + " ORDER BY id");
			rs = ps.executeQuery();
			while (rs.next()) {
				EstadoContrato ec = new EstadoContrato();
				ec.setId(rs.getLong("id"));
				ec.setNombre(rs.getString("nombre"));
				ec.setDescripcion(rs.getString("descripcion"));
				ec.setActivo(rs.getBoolean("activo"));
				lista.add(ec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return lista;
	}
}