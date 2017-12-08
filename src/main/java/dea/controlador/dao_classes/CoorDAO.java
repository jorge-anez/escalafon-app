/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;


import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.CoorDAOAInerface;
import dea.modelo.AdministradorCoordinador;
import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 *
 * @author Doppler
 */
@Service
@Transactional
public class CoorDAO{
    @Autowired
    private SessionFactory sessionFactory;

    private GenericDAOImpl<AdministradorCoordinador,String> administradorCoordinadorDAO;
    @PostConstruct
    public void init() {
        administradorCoordinadorDAO = new GenericDAOImpl<AdministradorCoordinador,String>(sessionFactory, AdministradorCoordinador.class);
    }

    public List<AdministradorCoordinador> readCoor(String ci) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public AdministradorCoordinador read(String ci) {
        return administradorCoordinadorDAO.find(ci);
    }

    public void create(AdministradorCoordinador admin_coor) {
        administradorCoordinadorDAO.save(admin_coor);
    }

    public void merge(AdministradorCoordinador admin_coor) {
        administradorCoordinadorDAO.merge(admin_coor);
    }
}
