package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.PlanCriteria;
import com.ruben.bluewave.model.Plan;
import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.util.DAOUtils;
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
	public List<PlanDTO> findByCriteria(Connection c, PlanCriteria criteria) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getDescripcion(), condiciones, " descripcion LIKE ? ", parametros,
					"%" + criteria.getDescripcion() + "%");
			SQLUtils.addClause(criteria.getPrecio(), condiciones, " precio = ? ", parametros, criteria.getPrecio());
			SQLUtils.addClause(criteria.getTipoPlanId(), condiciones, " tipo_plan_id = ? ", parametros,
					criteria.getTipoPlanId());
			SQLUtils.addClause(criteria.getActivo(), condiciones, " activo = ? ", parametros, criteria.getActivo());

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

			List<PlanDTO> planes = new ArrayList<>();
			while (rs.next()) {
				planes.add(loadNext(rs));
			}

			return planes;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
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
	    if (dto == null || dto.getId() == null) return null;
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