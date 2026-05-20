package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.model.TipoPlan;
import com.ruben.bluewave.util.JDBCUtils;

public class TipoPlanDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion, velocidad_mbps FROM tipo_plan";

	public List<TipoPlan> findAll(Connection c) {
		List<TipoPlan> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(BASE_QUERY + " ORDER BY nombre");
			rs = ps.executeQuery();
			while (rs.next()) {
				TipoPlan tp = new TipoPlan();
				tp.setId(rs.getLong("id"));
				tp.setNombre(rs.getString("nombre"));
				tp.setDescripcion(rs.getString("descripcion"));
				tp.setVelocidadMbps(rs.getInt("velocidad_mbps"));
				lista.add(tp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return lista;
	}
}