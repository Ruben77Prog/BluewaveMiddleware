package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.ProvinciaCriteria;
import com.ruben.bluewave.model.Provincia;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class ProvinciaDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, pais_id FROM provincia";

	public ProvinciaDAO() {
	}

	public Provincia findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = JDBCUtils.getConnection();
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			Provincia provincia = null;
			if (rs.next()) {
				int i = 1;
				provincia = new Provincia();
				provincia.setId(rs.getLong(i++));
				provincia.setNombre(rs.getString(i++));
				provincia.setPaisId(rs.getLong(i++));
			}

			return provincia;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<Provincia> findByCriteria(Connection c, ProvinciaCriteria criteria) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = JDBCUtils.getConnection();
			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getPaisId(), condiciones, " pais_id = ? ", parametros, criteria.getPaisId());

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ");
				sql.append(String.join(" AND ", condiciones));
			}

			System.out.println("SQL = " + sql.toString());
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			for (Object param : parametros) {
				ps.setObject(i++, param);
			}

			rs = ps.executeQuery();

			List<Provincia> provincias = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				Provincia provincia = new Provincia();
				provincia.setId(rs.getLong(col++));
				provincia.setNombre(rs.getString(col++));
				provincia.setPaisId(rs.getLong(col++));
				provincias.add(provincia);
			}

			return provincias;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
	}

	
}