package com.ruben.bluewave.service.impl;

import java.util.Date;
import java.util.List;

import com.ruben.bluewave.dao.ContratoDAO;
import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.service.ContratoService;
import com.ruben.bluewave.service.MailService;

public class ContratoServiceImpl implements ContratoService {

    private ContratoDAO contratoDAO = null;
    private MailService mailService = null;

    public ContratoServiceImpl() {
        contratoDAO = new ContratoDAO();
        mailService = new MailServiceApacheImpl();
    }

    @Override
    public Long create(ContratoDTO contrato) {
        try {
            if (contratoDAO == null) {
                System.err.println("Error: ContratoDAO no está inicializado");
                contratoDAO = new ContratoDAO();
            }


            contrato = contratoDAO.create(contrato);

            if (contrato != null && contrato.getId() != null) {
                return contrato.getId();
            }

            return null;

        } catch (Exception e) {
            System.err.println("Error ");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ContratoDTO findById(Long id) {
        try {
            if (contratoDAO == null) {
                System.err.println("Error:  ");
                contratoDAO = new ContratoDAO();
            }

            return contratoDAO.findById(id);

        } catch (Exception e) {
            System.err.println("Error ");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ContratoDTO> findBy(ContratoCriteria criteria,int from, int pageSize) {
        try {
            if (contratoDAO == null) {
                System.err.println("Error: ");
                contratoDAO = new ContratoDAO();
            }

            List<ContratoDTO> resultados = contratoDAO.findBy(criteria);
            return resultados;

        } catch (Exception e) {
            System.err.println("Error  ");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ContratoDTO> findByCliente(Long clienteId) {
        try {
            if (clienteId == null) {
                System.err.println("Error: clienteId es null");
                return null;
            }

            ContratoCriteria criteria = new ContratoCriteria();
            criteria.setClienteId(clienteId);
            return contratoDAO.findBy(criteria);

        } catch (Exception e) {
            System.err.println("Error ");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ContratoDTO> findByNumeroContrato(String numeroContrato) {
        try {
            if (numeroContrato == null || numeroContrato.trim().isEmpty()) {
                System.err.println("Error: numeroContrato es null");
                return null;
            }

            ContratoCriteria criteria = new ContratoCriteria();
            criteria.setNumeroContrato(numeroContrato);
            return contratoDAO.findBy(criteria);

        } catch (Exception e) {
            System.err.println("Error");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ContratoDTO> findByEstado(Long estadoContratoId) {
        try {
            if (estadoContratoId == null) {
                System.err.println("Error");
                return null;
            }

            ContratoCriteria criteria = new ContratoCriteria();
            criteria.setEstadoContratoId(estadoContratoId);
            return contratoDAO.findBy(criteria);

        } catch (Exception e) {
            System.err.println("Error ");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(ContratoDTO contrato) {
        try {
            if (contrato == null || contrato.getId() == null) {
                System.err.println("Error:");
                return false;
            }

            if (contratoDAO == null) {
                System.err.println("Error");
                contratoDAO = new ContratoDAO();
            }

          

            boolean actualizado = contratoDAO.update(contrato);

            if (actualizado) {
                System.out.println("Contrato actualizado: " + contrato.getNumeroContrato());
            }

            return actualizado;

        } catch (Exception e) {
            System.err.println("Erro ");
            e.printStackTrace();
            return false;
        }
    }

   
    

    

    
  


    @Override
    public void delete(Long id) {
        try {
            if (id == null) {
                System.err.println("Error: id es null");
                return;
            }

            if (contratoDAO == null) {
                System.err.println("Error:");
                contratoDAO = new ContratoDAO();
            }

      
            boolean eliminado = contratoDAO.delete(id);

            if (eliminado) {
                System.out.println("Contrato eliminado con id: " + id);
            } else {
                System.err.println("No se pudo eliminar el contrato con id: " + id);
            }

        } catch (Exception e) {
            System.err.println("Error en: ");
            e.printStackTrace();
        }
    }
}