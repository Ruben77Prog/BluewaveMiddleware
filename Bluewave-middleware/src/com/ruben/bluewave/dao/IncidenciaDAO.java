package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class IncidenciaDAO {

	private static final String BASE_QUERY = "SELECT i.id, i.numero_incidencia, i.titulo, i.descripcion, "
			+ "ti.nombre AS tipo_incidencia_nombre, "
			+ "i.contrato_id, con.numero_contrato AS contrato_numero, c.nombre AS cliente_nombre, "
			+ "i.estado_incidencia_id, ei.nombre AS estado_incidencia_nombre, "
			+ "i.creado_por_empleado_id, creador.nombre AS empleado_creador_nombre " + "FROM incidencia i "
			+ "INNER JOIN tipo_incidencia ti ON i.tipo_incidencia_id = ti.id "
			+ "INNER JOIN estado_incidencia ei ON i.estado_incidencia_id = ei.id "
			+ "INNER JOIN contrato con ON i.contrato_id = con.id " + "INNER JOIN cliente c ON con.cliente_id = c.id "
			+ "INNER JOIN empleado creador ON i.creado_por_empleado_id = creador.id";

	public IncidenciaDAO() {
	}

	public IncidenciaDTO findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE i.id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			IncidenciaDTO incidencia = null;
			if (rs.next()) {
				incidencia = loadNext(rs);
			}
			return incidencia;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<IncidenciaDTO> findByNumeroIncidencia(Connection c, String numeroIncidencia) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IncidenciaDTO> incidencias = new ArrayList<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE i.numero_incidencia = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setString(1, numeroIncidencia);

			rs = ps.executeQuery();

			while (rs.next()) {
				incidencias.add(loadNext(rs));
			}

			return incidencias;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return incidencias;
	}

	public Results<IncidenciaDTO> findByCriteria(Connection c, IncidenciaCriteria criteria, int from, int pageSize) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Results<IncidenciaDTO> results = new Results<>();

		try {

			StringBuilder sqlBuilder = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			// Lógica de filtrado
			SQLUtils.addClause(criteria.getId(), condiciones, " i.id = ? ", parametros, criteria.getId());

			if (criteria.getId() == null) {
				SQLUtils.addClause(criteria.getNumeroIncidencia(), condiciones, " i.numero_incidencia LIKE ? ",
						parametros, "%" + criteria.getNumeroIncidencia() + "%");
				SQLUtils.addClause(criteria.getTitulo(), condiciones, " UPPER(i.titulo) LIKE UPPER(?) ", parametros,
						"%" + criteria.getTitulo() + "%");
				SQLUtils.addClause(criteria.getTipoIncidenciaId(), condiciones, " i.tipo_incidencia_id = ? ",
						parametros, criteria.getTipoIncidenciaId());
				SQLUtils.addClause(criteria.getContratoId(), condiciones, " i.contrato_id = ? ", parametros,
						criteria.getContratoId());
				SQLUtils.addClause(criteria.getEstadoIncidenciaId(), condiciones, " i.estado_incidencia_id = ? ",
						parametros, criteria.getEstadoIncidenciaId());
			}

			if (!condiciones.isEmpty()) {
				sqlBuilder.append(" WHERE ");
				sqlBuilder.append(String.join(" AND ", condiciones));
			}

			
			sqlBuilder.append(" ORDER BY ").append(criteria.getOrderBy())
					.append(Boolean.FALSE.equals(criteria.getAscDesc()) ? " DESC " : " ASC ");

			
			ps = c.prepareStatement(sqlBuilder.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			for (Object param : parametros) {
				ps.setObject(i++, param);
			}

			rs = ps.executeQuery();
			List<IncidenciaDTO> resultsPage = new ArrayList<>();

			if (from >= 1) {
				int count = 0;
				if (rs.absolute(from)) {
					do {
						resultsPage.add(loadNext(rs));
						++count;
					} while (count < pageSize && rs.next());
				}
			}

			int totalResults = SQLUtils.getTotalRows(rs);

			results.setPage(resultsPage);
			results.setTotal(totalResults);

			return results;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return results;
	}

	public List<IncidenciaDTO> findAll(Connection c) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" ORDER BY i.fecha_incidencia DESC");

			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			List<IncidenciaDTO> resultados = new ArrayList<>();

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

	public Incidencia create(Connection c, Incidencia incidencia) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO incidencia (numero_incidencia, titulo, descripcion, ");
			sql.append("fecha_incidencia, fecha_resolucion, horas_estimadas, horas_reales, ");
			sql.append("coste_reparacion, tipo_incidencia_id, contrato_id, estado_incidencia_id, ");
			sql.append("empleado_asignado_id, creado_por_empleado_id, fecha_creacion) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, incidencia.getNumeroIncidencia());
			ps.setString(i++, incidencia.getTitulo());
			ps.setString(i++, incidencia.getDescripcion());
			ps.setTimestamp(i++,
					incidencia.getFechaIncidencia() != null
							? new java.sql.Timestamp(incidencia.getFechaIncidencia().getTime())
							: null);
			ps.setTimestamp(i++,
					incidencia.getFechaResolucion() != null
							? new java.sql.Timestamp(incidencia.getFechaResolucion().getTime())
							: null);
			ps.setDouble(i++, incidencia.getHorasEstimadas() != null ? incidencia.getHorasEstimadas() : 0);
			ps.setDouble(i++, incidencia.getHorasReales() != null ? incidencia.getHorasReales() : 0);
			ps.setDouble(i++, incidencia.getCosteReparacion() != null ? incidencia.getCosteReparacion() : 0);
			ps.setLong(i++, incidencia.getTipoIncidenciaId());
			ps.setLong(i++, incidencia.getContratoId());
			ps.setLong(i++, incidencia.getEstadoIncidenciaId());
			ps.setLong(i++, incidencia.getEmpleadoAsignadoId());
			ps.setLong(i++, incidencia.getCreadorEmpleadoId());
			ps.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				incidencia.setId(rs.getLong(1));
			}
			return incidencia;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public boolean update(Connection c, Incidencia incidencia) {
		if (incidencia == null || incidencia.getId() == null) {
			return false;
		}

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE incidencia SET ");
			sql.append("numero_incidencia = ?, titulo = ?, descripcion = ?, ");
			sql.append("fecha_incidencia = ?, fecha_resolucion = ?, horas_estimadas = ?, ");
			sql.append("horas_reales = ?, coste_reparacion = ?, tipo_incidencia_id = ?, ");
			sql.append(
					"contrato_id = ?, estado_incidencia_id = ?, empleado_asignado_id = ?, creado_por_empleado_id = ?, ");
			sql.append("fecha_actualizacion = ? WHERE id = ?");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, incidencia.getNumeroIncidencia());
			ps.setString(i++, incidencia.getTitulo());
			ps.setString(i++, incidencia.getDescripcion());
			ps.setTimestamp(i++,
					incidencia.getFechaIncidencia() != null
							? new java.sql.Timestamp(incidencia.getFechaIncidencia().getTime())
							: null);
			ps.setTimestamp(i++,
					incidencia.getFechaResolucion() != null
							? new java.sql.Timestamp(incidencia.getFechaResolucion().getTime())
							: null);
			ps.setDouble(i++, incidencia.getHorasEstimadas() != null ? incidencia.getHorasEstimadas() : 0);
			ps.setDouble(i++, incidencia.getHorasReales() != null ? incidencia.getHorasReales() : 0);
			ps.setDouble(i++, incidencia.getCosteReparacion() != null ? incidencia.getCosteReparacion() : 0);
			ps.setLong(i++, incidencia.getTipoIncidenciaId());
			ps.setLong(i++, incidencia.getContratoId());
			ps.setLong(i++, incidencia.getEstadoIncidenciaId());
			ps.setLong(i++, incidencia.getEmpleadoAsignadoId());
			ps.setLong(i++, incidencia.getCreadorEmpleadoId());
			ps.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			ps.setLong(i++, incidencia.getId());

			return ps.executeUpdate() > 0;

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
			sql.append("DELETE FROM incidencia WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}
		return false;
	}

	private IncidenciaDTO loadNext(ResultSet rs) {
		try {
			IncidenciaDTO incidencia = new IncidenciaDTO();

			incidencia.setId(rs.getLong("id"));
			incidencia.setNumeroIncidencia(rs.getString("numero_incidencia"));
			incidencia.setTitulo(rs.getString("titulo"));
			incidencia.setDescripcion(rs.getString("descripcion"));
			incidencia.setFechaIncidencia(rs.getTimestamp("fecha_incidencia"));
			incidencia.setFechaResolucion(rs.getTimestamp("fecha_resolucion"));
			incidencia.setHorasEstimadas(rs.getDouble("horas_estimadas"));
			incidencia.setHorasReales(rs.getDouble("horas_reales"));
			incidencia.setCosteReparacion(rs.getDouble("coste_reparacion"));

			incidencia.setTipoIncidenciaId(rs.getLong("tipo_incidencia_id"));
			incidencia.setContratoId(rs.getLong("contrato_id"));
			incidencia.setEstadoIncidenciaId(rs.getLong("estado_incidencia_id"));

			incidencia.setTipoIncidenciaNombre(rs.getString("tipo_incidencia_nombre"));
			incidencia.setContratoNumero(rs.getString("contrato_numero"));
			incidencia.setClienteNombre(rs.getString("cliente_nombre"));
			incidencia.setEstadoIncidenciaNombre(rs.getString("estado_incidencia_nombre"));
			incidencia.setEmpleadoAsignadoId(rs.getLong("empleado_asignado_id"));
			incidencia.setEmpleadoAsignadoNombre(rs.getString("empleado_asignado_nombre"));
			incidencia.setEmpleadoAsignadoApellido(rs.getString("empleado_asignado_apellido"));
			incidencia.setEmpleadoCreadorId(rs.getLong("creado_por_empleado_id"));
			incidencia.setEmpleadoCreadorNombre(rs.getString("empleado_creador_nombre"));
			incidencia.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
			incidencia.setFechaResolucion(rs.getTimestamp("fecha_actualizacion"));

			return incidencia;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
