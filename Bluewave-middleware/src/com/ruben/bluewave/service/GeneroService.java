package com.ruben.bluewave.service;

import java.util.List;
import com.ruben.bluewave.model.Genero;

public interface GeneroService {
    List<Genero> findAll();
    Genero findById(Long id);
}