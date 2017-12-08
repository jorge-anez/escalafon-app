/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.UniversidadDAOInterface;
import dea.modelo.Universidad;
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
public class UniversidadDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private GenericDAOImpl<Universidad, String> universidadDAO;
    @PostConstruct
    public void init() {
        universidadDAO = new GenericDAOImpl<Universidad, String>(sessionFactory, Universidad.class);
    }

    @Transactional
    public void delete(Universidad u) {
        universidadDAO.remove(u);
    }

    @Transactional
    public void update(Universidad u) {
        universidadDAO.update(u);
    }

    @Transactional
    public void create(Universidad u) {
        universidadDAO.save(u);
    }

    @Transactional
    public List readAll() {
        return universidadDAO.findAll();
    }
}
