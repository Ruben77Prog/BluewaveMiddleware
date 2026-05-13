package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.DireccionCriteria;
import com.ruben.bluewave.model.Direccion;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class DireccionDAO {	

	private static final String BASE_QUERY = "SELECT id, calle, numero, piso, puerta, codigo_postal, ciudad_id FROM direccion";

	public DireccionDAO() {
	}

	public Direccion findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);
			rs = ps.executeQuery();

			Direccion direccion = null;
			if (rs.next()) {
				direccion = loadNext(rs);
			}
			return direccion;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<Direccion> findBy(Connection c, DireccionCriteria criteria) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getCalle(), condiciones, " calle LIKE ? ", parametros,
					"%" + criteria.getCalle() + "%");
			SQLUtils.addClause(criteria.getNumero(), condiciones, " numero LIKE ? ", parametros,
					"%" + criteria.getNumero() + "%");
			SQLUtils.addClause(criteria.getCodigoPostal(), condiciones, " codigo_postal LIKE ? ", parametros,
					"%" + criteria.getCodigoPostal() + "%");
			SQLUtils.addClause(criteria.getCiudadId(), condiciones, " ciudad_id = ? ", parametros,
					criteria.getCiudadId());

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
			List<Direccion> resultados = new ArrayList<>();

			while (rs.next()) {
				resultados.add(loadNext(rs));
			}
			return resultados;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
	}

	public Direccion create(Connection c, Direccion direccion) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO direccion (calle, numero, piso, puerta, codigo_postal, ciudad_id) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?)");

			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, direccion.getCalle());
			ps.setString(i++, direccion.getNumero());
			ps.setString(i++, direccion.getPiso());
			ps.setString(i++, direccion.getPuerta());
			ps.setString(i++, direccion.getCodigoPostal());
			ps.setLong(i++, direccion.getCiudadId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				direccion.setId(rs.getLong(1));
			}
			return direccion;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return null;
	}

	public boolean update(Connection c, Direccion direccion) {
		if (direccion == null || direccion.getId() == null) {
			return false;
		}

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE direccion SET ");
			sql.append("calle = ?, numero = ?, piso = ?, puerta = ?, codigo_postal = ?, ciudad_id = ? ");
			sql.append("WHERE id = ?");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, direccion.getCalle());
			ps.setString(i++, direccion.getNumero());
			ps.setString(i++, direccion.getPiso());
			ps.setString(i++, direccion.getPuerta());
			ps.setString(i++, direccion.getCodigoPostal());
			ps.setLong(i++, direccion.getCiudadId());
			ps.setLong(i++, direccion.getId());

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}
		return false;
	}

	public boolean delete(Connection c, Long id) {
		if (id == null) {
			return false;
		}

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM direccion WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}
		return false;
	}

	private Direccion loadNext(ResultSet rs) {
		try {
			Direccion direccion = new Direccion();

			direccion.setId(rs.getLong("id"));
			direccion.setCalle(rs.getString("calle"));
			direccion.setNumero(rs.getString("numero"));
			direccion.setPiso(rs.getString("piso"));
			direccion.setPuerta(rs.getString("puerta"));
			direccion.setCodigoPostal(rs.getString("codigo_postal"));
			direccion.setCiudadId(rs.getLong("ciudad_id"));

			return direccion;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}