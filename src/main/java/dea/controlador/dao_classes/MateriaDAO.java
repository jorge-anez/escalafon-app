/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.FacultadDAOInterface;
import dea.controlador.dao_interfaces.MateriaDAOInterface;
import dea.controlador.dao_interfaces.UniversidadDAOInterface;
import dea.modelo.Facultad;
import dea.modelo.Materia;
import dea.modelo.Universidad;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
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
public class MateriaDAO{
    @Autowired
    private SessionFactory sessionFactory;
    private GenericDAOImpl<Materia,Long> materiaDAO;

    @PostConstruct
    public void init() {
        materiaDAO = new GenericDAOImpl<Materia,Long>(sessionFactory, Materia.class);
    }

    public List<Materia> readMateria(Long id) {
        Session session=this.sessionFactory.getCurrentSession();
        List<Materia> p=session.createQuery("from Materia as m where m.carrera.idCarrera=:id").setParameter("id", id).list();
       return p;
    }

    public void delete(Materia m) {
        materiaDAO.remove(m);
    }

    public void update(Materia m) {
        materiaDAO.update(m);
    }

    public void create(Materia materia) {
        materiaDAO.save(materia);
    }
}
