package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.model.MetodoPago;
import com.ruben.bluewave.util.JDBCUtils;

public class MetodoPagoDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion, activo FROM metodo_pago";

	public List<MetodoPago> findAll(Connection c) {
		List<MetodoPago> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(BASE_QUERY + " ORDER BY nombre");
			rs = ps.executeQuery();
			while (rs.next()) {
				MetodoPago mp = new MetodoPago();
				mp.setId(rs.getLong("id"));
				mp.setNombre(rs.getString("nombre"));
				mp.setDescripcion(rs.getString("descripcion"));
				mp.setActivo(rs.getBoolean("activo"));
				lista.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return lista;
	}
}