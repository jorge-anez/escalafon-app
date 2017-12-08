/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.DocenteEscalafonDAOInterface;
import dea.modelo.DocenteEscalafon;
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

@Service
@Transactional
public class DocenteEscalafonDAO{
    @Autowired
    private SessionFactory sessionFactory;

    private GenericDAOImpl<DocenteEscalafon, String> docenteEscalafonDAO;
    @PostConstruct
    public void init() {
        docenteEscalafonDAO = new GenericDAOImpl<DocenteEscalafon, String>(sessionFactory, DocenteEscalafon.class);
    }

    public List<Object[]> getRegistro(Long idCarrera){
        Session session=this.sessionFactory.getCurrentSession();
        List<Object[]> result=session.createQuery("from DocenteEscalafon r left join r.docente.persona where r.carrera.idCarrera=:id").setParameter("id",idCarrera).list();        
        return result;
    }

    public List<Object[]> getDocenteList(){
        Session session=this.sessionFactory.getCurrentSession();
        List<Object[]> result=session.createQuery("from Persona p inner join p.docente as d where d.ci not in(select de.ci from DocenteEscalafon de)").list();
        return result;
    }

    public void delete(DocenteEscalafon d) {
        docenteEscalafonDAO.remove(d);
    }

    public void create(DocenteEscalafon registro) {
        docenteEscalafonDAO.save(registro);
    }

    public void update(DocenteEscalafon registro) {
        docenteEscalafonDAO.update(registro);
    }

    public List<DocenteEscalafon> readAll() {
        return docenteEscalafonDAO.findAll();
    }
}