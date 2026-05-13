package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.EstadoClienteCriteria;
import com.ruben.bluewave.model.EstadoCliente;

public interface EstadoClienteService {

	public EstadoCliente findById(Long id);

	public List<EstadoCliente> findBy(EstadoClienteCriteria criteria) throws Exception;

	public List<EstadoCliente> findAll() throws Exception;

	public Long create(EstadoCliente estadoCliente) throws Exception;

	public boolean update(EstadoCliente estadoCliente) throws Exception;

	public boolean delete(Long id) throws Exception;
}