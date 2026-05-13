package com.ruben.bluewave.service.impl;

import java.util.List;
import com.ruben.bluewave.dao.GeneroDAO;
import com.ruben.bluewave.model.Genero;
import com.ruben.bluewave.service.GeneroService;

public class GeneroServiceImpl implements GeneroService {

    private GeneroDAO generoDAO;

    public GeneroServiceImpl() {
        this.generoDAO = new GeneroDAO();
    }

    @Override
    public List<Genero> findAll() {
        return generoDAO.findAll(); 
    }

    @Override
    public Genero findById(Long id) {
        return generoDAO.findById(id);
    }
}