package com.ruben.bluewave.service.impl;

import java.util.List;
import com.ruben.bluewave.dao.PaisDAO;
import com.ruben.bluewave.model.Pais;
import com.ruben.bluewave.service.PaisService;

public class PaisServiceImpl implements PaisService {

    private PaisDAO paisDAO;

    public PaisServiceImpl() {
        this.paisDAO = new PaisDAO();
    }

    @Override
    public List<Pais> findAll() {
        return paisDAO.findAll(); 
    }

    @Override
    public Pais findById(Long id) {
        return paisDAO.findById(id);
    }
}