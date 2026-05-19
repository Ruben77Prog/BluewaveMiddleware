package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.PlanCriteria;
import com.ruben.bluewave.model.PlanDTO;

public interface PlanService {

	PlanDTO findById(Long id) throws Exception;

	List<PlanDTO> findByCriteria(PlanCriteria criteria) throws Exception;

	List<PlanDTO> findAll() throws Exception;

	List<PlanDTO> findActivos() throws Exception;

	Long create(PlanDTO plan) throws Exception;

	boolean update(PlanDTO plan) throws Exception;

	boolean delete(Long id) throws Exception;
}