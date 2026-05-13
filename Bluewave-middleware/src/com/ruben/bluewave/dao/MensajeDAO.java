package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.MensajeCriteria;
import com.ruben.bluewave.model.Mensaje;
import com.ruben.bluewave.util.DAOUtils;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class MensajeDAO {

	private static final String BASE_QUERY = "SELECT m.id, m.numero_mensaje, m.asunto, m.cuerpo, "
			+ "m.tipo_mensaje_id, tm.nombre AS tipo_mensaje_nombre, "
			+ "m.incidencia_id, i.titulo AS incidencia_titulo, "
			+ "m.tipo_remitente, m.remitente_cliente_id, "
			+ "cr.nombre AS cliente_remitente_nombre, cr.apellido1 AS cliente_remitente_apellido, "
			+ "m.remitente_empleado_id, er.nombre AS empleado_remitente_nombre, er.apellido1 AS empleado_remitente_apellido, "
			+ "m.tipo_destinatario, m.destinatario_cliente_id, "
			+ "cd.nombre AS cliente_destinatario_nombre, cd.apellido1 AS cliente_destinatario_apellido, "
			+ "m.destinatario_empleado_id, ed.nombre AS empleado_destinatario_nombre, ed.apellido1 AS empleado_destinatario_apellido, "
			+ "m.mensaje_padre_id, m.leido, m.importante, m.fecha_envio, m.fecha_lectura "
			+ "FROM mensaje m "
			+ "INNER JOIN tipo_mensaje tm ON m.tipo_mensaje_id = tm.id "
			+ "INNER JOIN incidencia i ON m.incidencia_id = i.id "
			+ "LEFT JOIN cliente cr ON m.remitente_cliente_id = cr.id "
			+ "LEFT JOIN empleado er ON m.remitente_empleado_id = er.id "
			+ "LEFT JOIN cliente cd ON m.destinatario_cliente_id = cd.id "
			+ "LEFT JOIN empleado ed ON m.destinatario_empleado_id = ed.id";

	public MensajeDAO() {
	}

	public Mensaje findById(Connection c,Long id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE m.id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			Mensaje mensaje = null;
			if (rs.next()) {
				mensaje = loadNext(rs);
			}

			return mensaje;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<Mensaje> findBy(Connection c,MensajeCriteria criteria) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		
			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " m.id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNumeroMensaje(), condiciones, " m.numero_mensaje LIKE ? ", parametros,
					"%" + criteria.getNumeroMensaje() + "%");
			SQLUtils.addClause(criteria.getAsunto(), condiciones, " m.asunto LIKE ? ", parametros,
					"%" + criteria.getAsunto() + "%");

			SQLUtils.addClause(criteria.getIncidenciaId(), condiciones, " m.incidencia_id = ? ", parametros,
					criteria.getIncidenciaId());
			SQLUtils.addClause(criteria.getIncidenciaTitulo(), condiciones, " UPPER(i.titulo) LIKE UPPER(?) ", parametros,
					"%" + criteria.getIncidenciaTitulo() + "%");

			SQLUtils.addClause(criteria.getClienteRemitenteId(), condiciones, " m.remitente_cliente_id = ? ", parametros,
					criteria.getClienteRemitenteId());
			SQLUtils.addClause(criteria.getClienteRemitenteNombre(), condiciones, " UPPER(cr.nombre) LIKE UPPER(?) ",
					parametros, "%" + criteria.getClienteRemitenteNombre() + "%");
			SQLUtils.addClause(criteria.getEmpleadoRemitenteId(), condiciones, " m.remitente_empleado_id = ? ",
					parametros, criteria.getEmpleadoRemitenteId());
			SQLUtils.addClause(criteria.getEmpleadoRemitenteNombre(), condiciones, " UPPER(er.nombre) LIKE UPPER(?) ",
					parametros, "%" + criteria.getEmpleadoRemitenteNombre() + "%");

			SQLUtils.addClause(criteria.getClienteDestinatarioId(), condiciones, " m.destinatario_cliente_id = ? ",
					parametros, criteria.getClienteDestinatarioId());
			SQLUtils.addClause(criteria.getClienteDestinatarioNombre(), condiciones, " UPPER(cd.nombre) LIKE UPPER(?) ",
					parametros, "%" + criteria.getClienteDestinatarioNombre() + "%");
			SQLUtils.addClause(criteria.getEmpleadoDestinatarioId(), condiciones, " m.destinatario_empleado_id = ? ",
					parametros, criteria.getEmpleadoDestinatarioId());
			SQLUtils.addClause(criteria.getEmpleadoDestinatarioNombre(), condiciones, " UPPER(ed.nombre) LIKE UPPER(?) ",
					parametros, "%" + criteria.getEmpleadoDestinatarioNombre() + "%");

			SQLUtils.addClause(criteria.getLeido(), condiciones, " m.leido = ? ", parametros, criteria.getLeido());
			SQLUtils.addClause(criteria.getImportante(), condiciones, " m.importante = ? ", parametros,
					criteria.getImportante());

			if (criteria.getFechaEnvioDesde() != null) {
				SQLUtils.addClause(criteria.getFechaEnvioDesde(), condiciones, " m.fecha_envio >= ? ", parametros,
						new java.sql.Timestamp(criteria.getFechaEnvioDesde().getTime()));
			}
			if (criteria.getFechaEnvioHasta() != null) {
				SQLUtils.addClause(criteria.getFechaEnvioHasta(), condiciones, " m.fecha_envio <= ? ", parametros,
						new java.sql.Timestamp(criteria.getFechaEnvioHasta().getTime()));
			}

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ");
				sql.append(String.join(" AND ", condiciones));
			}

			sql.append(" ORDER BY ").append(criteria.getOrderBy());
			sql.append(Boolean.FALSE.equals(criteria.getAscDesc()) ? " DESC " : " ASC ");

			System.out.println("SQL = " + sql.toString());
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			for (Object param : parametros) {
				ps.setObject(i++, param);
			}

			rs = ps.executeQuery();

			List<Mensaje> mensajes = new ArrayList<>();
			while (rs.next()) {
				mensajes.add(loadNext(rs));
			}

			return mensajes;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
	}

	public Mensaje create(Connection c,Mensaje mensaje) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO mensaje (numero_mensaje, asunto, cuerpo, tipo_mensaje_id, ");
			sql.append("incidencia_id, tipo_remitente, remitente_cliente_id, remitente_empleado_id, ");
			sql.append("tipo_destinatario, destinatario_cliente_id, destinatario_empleado_id, ");
			sql.append("mensaje_padre_id, leido, importante) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, mensaje.getNumeroMensaje());
			ps.setString(i++, mensaje.getAsunto());
			ps.setString(i++, mensaje.getCuerpo());
			ps.setLong(i++, mensaje.getTipoMensajeId());
			ps.setLong(i++, mensaje.getIncidenciaId());
			ps.setString(i++, mensaje.getTipoRemitente());
			ps.setLong(i++, mensaje.getClienteRemitenteId());
			ps.setLong(i++, mensaje.getEmpleadoRemitenteId());
			ps.setString(i++, mensaje.getTipoDestinatario());
			ps.setLong(i++, mensaje.getClienteDestinatarioId());
			ps.setLong(i++, mensaje.getEmpleadoDestinatarioId());
			ps.setLong(i++, mensaje.getMensajePadreId());
			ps.setBoolean(i++, mensaje.getLeido());
			ps.setBoolean(i++, mensaje.getImportante());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				mensaje.setId(rs.getLong(1));
			}

			return mensaje;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public Mensaje update(Connection c,Mensaje mensaje) {
		if (mensaje == null || mensaje.getId() == null)
			return null;

		
		PreparedStatement ps = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE mensaje SET numero_mensaje = ?, asunto = ?, cuerpo = ?, ");
			sql.append("tipo_mensaje_id = ?, incidencia_id = ?, tipo_remitente = ?, ");
			sql.append("remitente_cliente_id = ?, remitente_empleado_id = ?, ");
			sql.append("tipo_destinatario = ?, destinatario_cliente_id = ?, ");
			sql.append("destinatario_empleado_id = ?, mensaje_padre_id = ?, ");
			sql.append("leido = ?, importante = ?, fecha_lectura = ? WHERE id = ?");
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, mensaje.getNumeroMensaje());
			ps.setString(i++, mensaje.getAsunto());
			ps.setString(i++, mensaje.getCuerpo());
			ps.setLong(i++, mensaje.getTipoMensajeId());
			ps.setLong(i++, mensaje.getIncidenciaId());
			ps.setString(i++, mensaje.getTipoRemitente());
			ps.setLong(i++, mensaje.getClienteRemitenteId());
			ps.setLong(i++, mensaje.getEmpleadoRemitenteId());
			ps.setString(i++, mensaje.getTipoDestinatario());
			ps.setLong(i++, mensaje.getClienteDestinatarioId());
			ps.setLong(i++, mensaje.getEmpleadoDestinatarioId());
			ps.setLong(i++, mensaje.getMensajePadreId());
			ps.setBoolean(i++, mensaje.getLeido());
			ps.setBoolean(i++, mensaje.getImportante());
			ps.setTimestamp(i++,
					mensaje.getFechaLectura() != null ? new java.sql.Timestamp(mensaje.getFechaLectura().getTime())
							: null);
			ps.setLong(i++, mensaje.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return mensaje;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}

		return null;
	}

	public boolean delete(Connection c,Long id) {
		if (id == null)
			return false;

		
		PreparedStatement ps = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM mensaje WHERE id = ?");
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

	public List<Mensaje> findAll(Connection c) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Mensaje> lista = new ArrayList<>();

		try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" ORDER BY m.fecha_envio DESC");

			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {

				lista.add(loadNext(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return lista;
	}

	private Mensaje loadNext(ResultSet rs) {
		try {
			Mensaje mensaje = new Mensaje();
			mensaje.setId(rs.getLong("id"));
			mensaje.setNumeroMensaje(rs.getString("numero_mensaje"));
			mensaje.setAsunto(rs.getString("asunto"));
			mensaje.setCuerpo(rs.getString("cuerpo"));
			mensaje.setTipoMensajeId(rs.getLong("tipo_mensaje_id"));
			mensaje.setIncidenciaId(rs.getLong("incidencia_id"));
			mensaje.setTipoRemitente(rs.getString("tipo_remitente"));
			mensaje.setClienteRemitenteId(rs.getLong("remitente_cliente_id"));
			mensaje.setEmpleadoRemitenteId(rs.getLong("remitente_empleado_id"));
			mensaje.setTipoDestinatario(rs.getString("tipo_destinatario"));
			mensaje.setClienteDestinatarioId(rs.getLong("destinatario_cliente_id"));
			mensaje.setEmpleadoDestinatarioId(rs.getLong("destinatario_empleado_id"));
			mensaje.setMensajePadreId(rs.getLong("mensaje_padre_id"));
			mensaje.setLeido(rs.getBoolean("leido"));
			mensaje.setImportante(rs.getBoolean("importante"));
			mensaje.setFechaEnvio(rs.getDate("fecha_envio"));
			mensaje.setFechaLectura(rs.getDate("fecha_lectura"));

			return mensaje;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
