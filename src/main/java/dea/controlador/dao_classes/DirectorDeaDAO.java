/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.DirectorDeaDAOInterface;
import dea.modelo.AdministradorDirector;
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
public class DirectorDeaDAO{
    @Autowired
    private SessionFactory sessionFactory;

    private GenericDAOImpl<AdministradorDirector,String> administradorDirectorDAO;
    @PostConstruct
    public void init() {
        administradorDirectorDAO = new GenericDAOImpl<AdministradorDirector,String>(sessionFactory, AdministradorDirector.class);
    }

    public List<AdministradorDirector> readDirector(String ci) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AdministradorDirector read(String ci) {
        return administradorDirectorDAO.find(ci);
    }

    public void create(AdministradorDirector admin_dir) {
        administradorDirectorDAO.save(admin_dir);
    }

    public void merge(AdministradorDirector admin_dir) {
        administradorDirectorDAO.merge(admin_dir);
    }
}
