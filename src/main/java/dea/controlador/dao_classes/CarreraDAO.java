/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.GenericDAOImpl;
import dea.modelo.Carrera;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 *
 * @author Doppler
 */
@Service
public class CarreraDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private GenericDAOImpl<Carrera, Long> carreraDAO;
    @PostConstruct
    public void init() {
        carreraDAO = new GenericDAOImpl<Carrera, Long>(sessionFactory, Carrera.class);
    }

    @Transactional
    public List<Carrera> readCarrera(Long idFacultad) {
        Session session=this.sessionFactory.getCurrentSession();
        List<Carrera> p=session.createQuery("from Carrera as c where c.facultad.idFacultad=:id").setParameter("id", idFacultad).list();
       return p;
    }

    @Transactional
    public void delete(Carrera c) {
        carreraDAO.remove(c);
    }

    @Transactional
    public void update(Carrera c) {
        carreraDAO.update(c);
    }

    @Transactional
    public void create(Carrera c) {
        carreraDAO.save(c);
    }

    @Transactional
    public Carrera read(long id) {
        return carreraDAO.find(id);
    }
}
