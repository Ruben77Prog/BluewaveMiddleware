package com.ruben.bluewave.service;

import java.util.List;
import com.ruben.bluewave.model.EstadoContrato;

public interface EstadoContratoService {
	List<EstadoContrato> findAll() throws Exception;
}