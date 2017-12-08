/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.DocenteDAOInterface;
import dea.modelo.Docente;
import dea.modelo.Escalafon;
import dea.modelo.Persona;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
public class DocenteDAO{
    @Autowired
    private SessionFactory sessionFactory;
    private GenericDAOImpl<Docente,String> docenteDAO;

    @PostConstruct
    public void init() {
        docenteDAO = new GenericDAOImpl<Docente,String>(sessionFactory, Docente.class);
    }

   public List<Persona>  getUsers(){
        Session session=this.sessionFactory.getCurrentSession();
        List<Persona> result=session.createQuery("from Persona p where p.ci not in (select d.ci from Docente d)").list();
    return result;
   }

    public void create(Docente d) {
       docenteDAO.save(d);
    }

    public void delete(Docente x) {
       docenteDAO.remove(x);
    }

    public void update(Docente d) {
       docenteDAO.update(d);
    }
}
