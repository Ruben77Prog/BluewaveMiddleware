package com.ruben.bluewave.service;

import java.util.List;
import com.ruben.bluewave.model.MetodoPago;

public interface MetodoPagoService {
	List<MetodoPago> findAll() throws Exception;
}