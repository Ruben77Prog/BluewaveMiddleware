package com.ruben.bluewave.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.ProvinciaDAO;
import com.ruben.bluewave.dao.criteria.ProvinciaCriteria;
import com.ruben.bluewave.model.Provincia;
import com.ruben.bluewave.service.ProvinciaService;

public class ProvinciaServiceImpl implements ProvinciaService {

    private ProvinciaDAO provinciaDAO;

    public ProvinciaServiceImpl() {
        this.provinciaDAO = new ProvinciaDAO();
    }

    @Override
    public Provincia findById(Long id) {
        return provinciaDAO.findById(id);
    }

    @Override
    public List<Provincia> findAll() {
       
        ProvinciaCriteria criteria = new ProvinciaCriteria();
        return provinciaDAO.findBy(criteria);
    }

    @Override
    public List<Provincia> findByPais(Long paisId) {
        if (paisId == null) {
            return new ArrayList<>();
        }
        ProvinciaCriteria criteria = new ProvinciaCriteria();
        criteria.setPaisId(paisId);
        return provinciaDAO.findBy(criteria);
    }
}