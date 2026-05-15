package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class ContratoDAO {

	private static final String BASE_QUERY = "SELECT con.id, con.numero_contrato, con.fecha_inicio, con.fecha_fin, "
			+ "con.coste_instalacion, con.coste_mensual, " + "con.fecha_creacion, con.fecha_actualizacion, "
			+ "con.metodo_pago_id, mp.nombre as metodo_pago_nombre, "
			+ "con.cliente_id, c.nombre as cliente_nombre, c.apellido1 as cliente_apellido, "
			+ "con.plan_id, p.nombre as plan_nombre, " + "con.estado_contrato_id, ec.nombre as estado_contrato_nombre "
			+ "FROM contrato con " + "INNER JOIN metodo_pago mp ON con.metodo_pago_id = mp.id "
			+ "INNER JOIN cliente c ON con.cliente_id = c.id " + "INNER JOIN plan p ON con.plan_id = p.id "
			+ "INNER JOIN estado_contrato ec ON con.estado_contrato_id = ec.id";

	public ContratoDAO() {
	}

	public ContratoDTO findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = BASE_QUERY + " WHERE con.id = ?";
			ps = c.prepareStatement(sql);
			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				return loadNext(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return null;
	}

	public Results<ContratoDTO> findByCriteria(Connection c, ContratoCriteria criteria, int from, int pageSize) {

		Results<ContratoDTO> results = new Results<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> params = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " con.id = ? ", params, criteria.getId());
			SQLUtils.addClause(criteria.getNumeroContrato(), condiciones, " con.numero_contrato LIKE ? ", params,
					"%" + criteria.getNumeroContrato() + "%");
			SQLUtils.addClause(criteria.getClienteId(), condiciones, " con.cliente_id = ? ", params,
					criteria.getClienteId());
			SQLUtils.addClause(criteria.getPlanId(), condiciones, " con.plan_id = ? ", params, criteria.getPlanId());
			SQLUtils.addClause(criteria.getEstadoContratoId(), condiciones, " con.estado_contrato_id = ? ", params,
					criteria.getEstadoContratoId());
			SQLUtils.addClause(criteria.getMetodoPagoId(), condiciones, " con.metodo_pago_id = ? ", params,
					criteria.getMetodoPagoId());

			if (criteria.getFechaInicioDesde() != null) {
				SQLUtils.addClause(true, condiciones, " con.fecha_inicio >= ? ", params,
						new java.sql.Date(criteria.getFechaInicioDesde().getTime()));
			}

			if (criteria.getFechaInicioHasta() != null) {
				SQLUtils.addClause(true, condiciones, " con.fecha_inicio <= ? ", params,
						new java.sql.Date(criteria.getFechaInicioHasta().getTime()));
			}

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ").append(String.join(" AND ", condiciones));
			}

			sql.append(" ORDER BY ").append(criteria.getOrderBy());
			sql.append(Boolean.FALSE.equals(criteria.getAscDesc()) ? " DESC " : " ASC ");

			ps = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			for (Object p : params) {
				ps.setObject(i++, p);
			}

			rs = ps.executeQuery();

			List<ContratoDTO> resultados = new ArrayList<>();

			int filaInicial = Math.max(1, from);
			if (pageSize > 0 && rs.absolute(filaInicial)) {
				int count = 0;
				do {
					resultados.add(loadNext(rs));
					count++;
				} while (count < pageSize && rs.next());
			}
			int total = 0;

			if (rs.last()) {
				total = rs.getRow();
			}
			results.setPage(resultados);
			results.setTotal(total);

			return results;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		results.setPage(new ArrayList<>());
		results.setTotal(0);

		return results;
	}

	public ContratoDTO create(Connection c, ContratoDTO contrato) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = "INSERT INTO contrato (numero_contrato, fecha_inicio, fecha_fin, "
					+ "coste_instalacion, coste_mensual, metodo_pago_id, cliente_id, "
					+ "plan_id, estado_contrato_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, contrato.getNumeroContrato());
			ps.setDate(i++,
					contrato.getFechaInicio() != null ? new java.sql.Date(contrato.getFechaInicio().getTime()) : null);
			ps.setDate(i++,
					contrato.getFechaFin() != null ? new java.sql.Date(contrato.getFechaFin().getTime()) : null);
			ps.setDouble(i++, contrato.getCostoInstalacion());
			ps.setDouble(i++, contrato.getCostoMensual());
			ps.setLong(i++, contrato.getMetodoPagoId());
			ps.setLong(i++, contrato.getClienteId());
			ps.setLong(i++, contrato.getPlanId());
			ps.setLong(i++, contrato.getEstadoContratoId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				contrato.setId(rs.getLong(1));
			}

			return contrato;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return null;
	}

	public boolean update(Connection c, ContratoDTO contrato) {
		if (contrato == null || contrato.getId() == null)
			return false;

		PreparedStatement ps = null;

		try {

			String sql = "UPDATE contrato SET numero_contrato = ?, fecha_inicio = ?, fecha_fin = ?, "
					+ "coste_instalacion = ?, coste_mensual = ?, metodo_pago_id = ?, "
					+ "cliente_id = ?, plan_id = ?, estado_contrato_id = ?, "
					+ "fecha_actualizacion = CURRENT_TIMESTAMP " + "WHERE id = ?";

			ps = c.prepareStatement(sql);

			int i = 1;
			ps.setString(i++, contrato.getNumeroContrato());
			ps.setDate(i++,
					contrato.getFechaInicio() != null ? new java.sql.Date(contrato.getFechaInicio().getTime()) : null);
			ps.setDate(i++,
					contrato.getFechaFin() != null ? new java.sql.Date(contrato.getFechaFin().getTime()) : null);
			ps.setDouble(i++, contrato.getCostoInstalacion());
			ps.setDouble(i++, contrato.getCostoMensual());
			ps.setLong(i++, contrato.getMetodoPagoId());
			ps.setLong(i++, contrato.getClienteId());
			ps.setLong(i++, contrato.getPlanId());
			ps.setLong(i++, contrato.getEstadoContratoId());
			ps.setLong(i++, contrato.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}

		return false;
	}

	public boolean delete(Connection c, Long id) {
		if (id == null)
			return false;

		PreparedStatement ps = null;

		try {

			ps = c.prepareStatement("DELETE FROM contrato WHERE id = ?");
			ps.setLong(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}

		return false;
	}

	private ContratoDTO loadNext(ResultSet rs) {
		try {
			ContratoDTO contrato = new ContratoDTO();

			contrato.setId(rs.getLong("id"));
			contrato.setNumeroContrato(rs.getString("numero_contrato"));
			contrato.setFechaInicio(rs.getDate("fecha_inicio"));
			contrato.setFechaFin(rs.getDate("fecha_fin"));

			contrato.setCostoInstalacion(rs.getDouble("coste_instalacion"));
			contrato.setCostoMensual(rs.getDouble("coste_mensual"));

			contrato.setCreadoEn(rs.getTimestamp("fecha_creacion"));
			contrato.setActualizadoEn(rs.getTimestamp("fecha_actualizacion"));

			contrato.setMetodoPagoId(rs.getLong("metodo_pago_id"));
			contrato.setMetodoPagoNombre(rs.getString("metodo_pago_nombre"));

			contrato.setClienteId(rs.getLong("cliente_id"));
			contrato.setClienteNombre(rs.getString("cliente_nombre"));
			contrato.setClienteApellido(rs.getString("cliente_apellido"));

			contrato.setPlanId(rs.getLong("plan_id"));
			contrato.setPlanNombre(rs.getString("plan_nombre"));

			contrato.setEstadoContratoId(rs.getLong("estado_contrato_id"));
			contrato.setEstadoContratoNombre(rs.getString("estado_contrato_nombre"));

			return contrato;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}