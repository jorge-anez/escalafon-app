/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.DocenteRegistroMateriaDAOInterface;
import dea.modelo.DocenteRegistroMateria;
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
public class DocenteRegistroMateriaDAO{
    @Autowired
    private SessionFactory sessionFactory;
    private GenericDAOImpl<DocenteRegistroMateria, Long> docenteRegistroMateriaDAO;

    @PostConstruct
    public void init() {
        docenteRegistroMateriaDAO = new GenericDAOImpl<DocenteRegistroMateria, Long>(sessionFactory, DocenteRegistroMateria.class);
    }

    public List<Object[]> getRegistro(Long idCarrera){
        Session session=this.sessionFactory.getCurrentSession();
        List<Object[]> result=session.createQuery("from DocenteRegistroMateria r left join r.materia  as m left join r.docente.persona where m.carrera.idCarrera=:id").setParameter("id",idCarrera).list();
        return result;
    }
    public List<Object[]> getRegistro(String g){
        Session session=this.sessionFactory.getCurrentSession();
        List<Object[]> result=session.createQuery("from DocenteRegistroMateria r left join r.materia  as m where r.gestion=:g").setParameter("g",g).list();
        return result;
    }

    public void delete(DocenteRegistroMateria registro) {
        docenteRegistroMateriaDAO.remove(registro);
    }

    public void create(DocenteRegistroMateria registro) {
        docenteRegistroMateriaDAO.save(registro);
    }
}
