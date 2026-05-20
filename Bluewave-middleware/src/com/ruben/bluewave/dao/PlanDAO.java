package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.PlanCriteria;
import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class PlanDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion, precio, duracion_meses, descuento, fecha_creacion, activo, tipo_plan_id FROM plan";

	public PlanDAO() {
	}

	/**
	 * Busca un plan por su id.
	 * 
	 * @param id La id del plan a buscar.
	 * @return El plan encontrado, o null si no existe.
	 */
	public PlanDTO findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			PlanDTO plan = null;
			if (rs.next()) {
				plan = loadNext(rs);
			}

			return plan;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	/**
	 * Busca planes que cumplan con los criterios especificados.
	 * 
	 * @param criteria Los criterios de búsqueda.
	 * @return Una lista de planes que cumplen con los criterios.
	 */
	public Results<PlanDTO> findByCriteria(Connection c, PlanCriteria criteria, int from, int pageSize) {
		Results<PlanDTO> results = new Results<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psCount = null;
		ResultSet rsCount = null;

		try {
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			List<String> condiciones = new ArrayList<>();
			List<Object> params = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", params, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", params,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getDescripcion(), condiciones, " descripcion LIKE ? ", params,
					"%" + criteria.getDescripcion() + "%");
			SQLUtils.addClause(criteria.getPrecio(), condiciones, " precio = ? ", params, criteria.getPrecio());
			SQLUtils.addClause(criteria.getTipoPlanId(), condiciones, " tipo_plan_id = ? ", params,
					criteria.getTipoPlanId());
			SQLUtils.addClause(criteria.getActivo(), condiciones, " activo = ? ", params, criteria.getActivo());

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ").append(String.join(" AND ", condiciones));
			}

			sql.append(" ORDER BY nombre ASC");

		
			String sqlPaginado = sql.toString() + " LIMIT ? OFFSET ?";
			ps = c.prepareStatement(sqlPaginado);

			int idx = 1;
			for (Object p : params) {
				ps.setObject(idx++, p);
			}
			ps.setInt(idx++, pageSize);
			ps.setInt(idx++, from);

			rs = ps.executeQuery();
			List<PlanDTO> lista = new ArrayList<>();
			while (rs.next()) {
				lista.add(loadNext(rs));
			}
			results.setPage(lista);

			
			StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM plan ");
			if (!condiciones.isEmpty()) {
				countSql.append(" WHERE ").append(String.join(" AND ", condiciones));
			}
			psCount = c.prepareStatement(countSql.toString());
			idx = 1;
			for (Object p : params) {
				psCount.setObject(idx++, p);
			}
			rsCount = psCount.executeQuery();
			int total = 0;
			if (rsCount.next()) {
				total = rsCount.getInt(1);
			}
			results.setTotal(total);

			return results;

		} catch (Exception e) {
			e.printStackTrace();
			results.setPage(new ArrayList<>());
			results.setTotal(0);
			return results;
		} finally {
			JDBCUtils.close(rs, ps);
			JDBCUtils.close(rsCount, psCount);
		}
	}

	public PlanDTO create(Connection c, PlanDTO dto) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO plan (nombre, descripcion, precio, duracion_meses, descuento, fecha_creacion, activo, tipo_plan_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, dto.getNombre());
			ps.setString(i++, dto.getDescripcion());
			ps.setDouble(i++, dto.getPrecio() != null ? dto.getPrecio() : 0.0);
			ps.setInt(i++, dto.getDuracionMeses() != null ? dto.getDuracionMeses() : 0);
			ps.setDouble(i++, dto.getDescuento() != null ? dto.getDescuento() : 0.0);
			ps.setDate(i++, new java.sql.Date(System.currentTimeMillis()));
			ps.setBoolean(i++, dto.getActivo() != null ? dto.getActivo() : true);
			ps.setLong(i++, dto.getTipoPlanId() != null ? dto.getTipoPlanId() : 1L);

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				dto.setId(rs.getLong(1));
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtils.close(rs, ps);
		}
	}

	public PlanDTO update(Connection c, PlanDTO dto) {
		if (dto == null || dto.getId() == null)
			return null;
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE plan SET nombre = ?, descripcion = ?, precio = ?, duracion_meses = ?, descuento = ?, activo = ?, tipo_plan_id = ? WHERE id = ?";
			ps = c.prepareStatement(sql);

			int i = 1;
			ps.setString(i++, dto.getNombre());
			ps.setString(i++, dto.getDescripcion());
			ps.setDouble(i++, dto.getPrecio() != null ? dto.getPrecio() : 0.0);
			ps.setInt(i++, dto.getDuracionMeses() != null ? dto.getDuracionMeses() : 0);
			ps.setDouble(i++, dto.getDescuento() != null ? dto.getDescuento() : 0.0);
			ps.setBoolean(i++, dto.getActivo() != null ? dto.getActivo() : true);
			ps.setLong(i++, dto.getTipoPlanId() != null ? dto.getTipoPlanId() : 1L);
			ps.setLong(i++, dto.getId());

			int rows = ps.executeUpdate();
			return rows > 0 ? dto : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtils.close(null, ps);
		}
	}

	public boolean delete(Connection c, Long id) {
		if (id == null)
			return false;

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM plan WHERE id = ?");
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

	private PlanDTO loadNext(ResultSet rs) {
		try {
			PlanDTO plan = new PlanDTO();
			plan.setId(rs.getLong("id"));
			plan.setNombre(rs.getString("nombre"));
			plan.setDescripcion(rs.getString("descripcion"));
			plan.setPrecio(rs.getDouble("precio"));
			plan.setDuracionMeses(rs.getInt("duracion_meses"));
			plan.setDescuento(rs.getDouble("descuento"));
			plan.setFechaCreacion(rs.getDate("fecha_creacion"));
			plan.setActivo(rs.getBoolean("activo"));
			plan.setTipoPlanId(rs.getLong("tipo_plan_id"));
			return plan;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}