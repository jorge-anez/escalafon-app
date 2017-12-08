/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.FacultadDAOInterface;
import dea.controlador.dao_interfaces.UniversidadDAOInterface;
import dea.modelo.Facultad;
import dea.modelo.Universidad;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 *
 * @author Doppler
 */
@Repository
public class FacultadDAO{
    @Autowired
    private SessionFactory sessionFactory;

    private GenericDAOImpl<Facultad, Long> facultadDAO;
    @PostConstruct
    public void init() {
        facultadDAO = new GenericDAOImpl<Facultad, Long>(sessionFactory, Facultad.class);
    }

    @Transactional
    public List<Facultad> readFacultdad(String sigla) {
        Session session=this.sessionFactory.getCurrentSession();
        List<Facultad> p=session.createQuery("from Facultad as f where f.universidad.siglaUniversidad=:sigla").setParameter("sigla", sigla).list();
       return p;
    }

    @Transactional
    public void delete(Facultad f) {
        facultadDAO.remove(f);
    }

    @Transactional
    public void update(Facultad f) {
        facultadDAO.update(f);
    }

    @Transactional
    public void create(Facultad f) {
        facultadDAO.save(f);
    }

    @Transactional
    public Facultad read(long id) {
        return facultadDAO.find(id);
    }
}
