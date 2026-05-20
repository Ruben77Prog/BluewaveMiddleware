package com.ruben.bluewave.service;

import java.util.List;
import com.ruben.bluewave.model.TipoPlan;

public interface TipoPlanService {
	List<TipoPlan> findAll() throws Exception;
}