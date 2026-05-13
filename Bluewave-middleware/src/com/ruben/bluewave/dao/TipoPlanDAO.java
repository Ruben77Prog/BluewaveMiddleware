package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.TipoPlanCriteria;
import com.ruben.bluewave.model.TipoPlan;
import com.ruben.bluewave.util.SQLUtils;

public class TipoPlanDAO {

	public TipoPlanDAO() {
	}

	public TipoPlan findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT id, nombre, descripcion, velocidad_id, subida_id, limite_datos_id "
					+ "FROM tipo_plan WHERE id = ?";

			ps = c.prepareStatement(sql);
			ps.setLong(1, id);

			rs = ps.executeQuery();

			TipoPlan tipoPlan = null;
			if (rs.next()) {
				int i = 1;
				tipoPlan = new TipoPlan();
				tipoPlan.setId(rs.getLong(i++));
				tipoPlan.setNombre(rs.getString(i++));
				tipoPlan.setDescripcion(rs.getString(i++));
				tipoPlan.setVelocidadId(rs.getLong(i++));
				tipoPlan.setSubidaId(rs.getLong(i++));
				tipoPlan.setLimiteDatosId(rs.getLong(i++));
			}

			return tipoPlan;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public List<TipoPlan> findBy(Connection c, TipoPlanCriteria criteria) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT id, nombre, descripcion, velocidad_id, subida_id, limite_datos_id "
					+ "FROM tipo_plan ";

			List<String> condiciones = new ArrayList<String>();
			List<Object> parameterValues = new ArrayList<Object>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parameterValues, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parameterValues,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getDescripcion(), condiciones, " descripcion LIKE ? ", parameterValues,
					"%" + criteria.getDescripcion() + "%");
			SQLUtils.addClause(criteria.getVelocidadId(), condiciones, " velocidad_id = ? ", parameterValues,
					criteria.getVelocidadId());
			SQLUtils.addClause(criteria.getSubidaId(), condiciones, " subida_id = ? ", parameterValues,
					criteria.getSubidaId());
			SQLUtils.addClause(criteria.getLimiteDatosId(), condiciones, " limite_datos_id = ? ", parameterValues,
					criteria.getLimiteDatosId());

			if (condiciones.size() > 0) {
				sql += " WHERE " + condiciones.get(0);
				for (int i = 1; i < condiciones.size(); i++) {
					sql += " AND " + condiciones.get(i);
				}
			}

			System.out.println("SQL: " + sql);
			ps = c.prepareStatement(sql);

			int i = 1;
			for (Object parameterValue : parameterValues) {
				ps.setObject(i++, parameterValue);
			}

			rs = ps.executeQuery();

			List<TipoPlan> tiposPlan = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				TipoPlan tipo = new TipoPlan();
				tipo.setId(rs.getLong(col++));
				tipo.setNombre(rs.getString(col++));
				tipo.setDescripcion(rs.getString(col++));
				tipo.setVelocidadId(rs.getLong(col++));
				tipo.setSubidaId(rs.getLong(col++));
				tipo.setLimiteDatosId(rs.getLong(col++));
				tiposPlan.add(tipo);
			}

			return tiposPlan;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public TipoPlan create(Connection c, TipoPlan tipoPlan) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = "INSERT INTO tipo_plan (nombre, descripcion, velocidad_id, subida_id, limite_datos_id) "
					+ "VALUES (?, ?, ?, ?, ?)";
			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, tipoPlan.getNombre());
			ps.setString(i++, tipoPlan.getDescripcion());
			ps.setLong(i++, tipoPlan.getVelocidadId());
			ps.setLong(i++, tipoPlan.getSubidaId());
			ps.setLong(i++, tipoPlan.getLimiteDatosId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				tipoPlan.setId(rs.getLong(1));
			}

			return tipoPlan;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public TipoPlan update(Connection c, TipoPlan tipoPlan) {
		if (tipoPlan == null || tipoPlan.getId() == null)
			return null;

		PreparedStatement ps = null;

		try {

			String sql = "UPDATE tipo_plan SET nombre = ?, descripcion = ?, velocidad_id = ?, "
					+ "subida_id = ?, limite_datos_id = ? WHERE id = ?";
			ps = c.prepareStatement(sql);

			int i = 1;
			ps.setString(i++, tipoPlan.getNombre());
			ps.setString(i++, tipoPlan.getDescripcion());
			ps.setLong(i++, tipoPlan.getVelocidadId());
			ps.setLong(i++, tipoPlan.getSubidaId());
			ps.setLong(i++, tipoPlan.getLimiteDatosId());
			ps.setLong(i++, tipoPlan.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return tipoPlan;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}

	public boolean delete(Connection c, Long id) {
		if (id == null)
			return false;

		PreparedStatement ps = null;

		try {

			String sql = "DELETE FROM tipo_plan WHERE id = ?";
			ps = c.prepareStatement(sql);
			ps.setLong(1, id);

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return false;
	}

}