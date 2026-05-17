package com.ruben.bluewave.service;

import java.util.List;
import com.ruben.bluewave.model.Genero;

public interface GeneroService {
	List<Genero> findAll() throws Exception;

	Genero findById(Long id) throws Exception;
}