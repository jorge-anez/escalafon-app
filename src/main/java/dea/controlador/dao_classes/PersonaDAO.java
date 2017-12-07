/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;
import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.PersonaDAOInterface;
import dea.modelo.Menu;
import dea.modelo.Persona;
import dea.modelo.Submenu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
//@Repository
//@Component
//@Repository
//@Transactional
//@Scope("session")
@Service
public class PersonaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private GenericDAOImpl<Persona, String> personaDAO;
    @PostConstruct
    public void init() {
        personaDAO = new GenericDAOImpl<Persona, String>(sessionFactory, Persona.class);
    }


    @Transactional
    public Persona findByUsername(String username) {
      Session session=this.sessionFactory.getCurrentSession();
            Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta", username)).uniqueResult();
       return p;
    }

    /**
     *
     * @return
     */
    @Transactional
    public Object[] findDirDEA(){
        Session session=this.sessionFactory.getCurrentSession();
        Object[] result=(Object[])session.createQuery("from Persona p inner join p.administradorDirector as a left join p.docente as d where a.estado='ACTIVO'").uniqueResult();
        return result;
    }

    @Transactional
    public Object[] findCoorDEA() {
        Session session=this.sessionFactory.getCurrentSession();
        Object[] result=(Object[])session.createQuery("from Persona p inner join p.administradorCoordinador as a left join p.docente as d where a.estado='ACTIVO'").uniqueResult();
        return result;
    }

    @Transactional
    public Set<Integer> getPerfiles(String ci){
        Session session=this.sessionFactory.getCurrentSession();
        List<Integer> result=session.createSQLQuery("select id_perfil from perfil_persona where ci=:ci").setParameter("ci", ci).list();
        Set<Integer> r=new HashSet<Integer>();
        for(Integer e:result)
            r.add(e);
        return r;
    }

    @Transactional
    public List<Object[]> getDocente(){
        Session session=this.sessionFactory.getCurrentSession();
        List<Object[]> result=session.createQuery("from Persona p inner join p.docente as d").list();
        return result;
    }

    @Transactional
    public List<Persona> getUserNotDocente(){
        Session session=this.sessionFactory.getCurrentSession();
            List<Persona> result=session.createQuery("select p from Persona p  left join p.docente as d where d.ci=null").list();
        return result;
    }

    @Transactional
    public List<Menu> getMenu(String cad){
        Session session=this.sessionFactory.getCurrentSession();
         List<Menu> result=session.createQuery("select m from Menu m where m.perfil.idPerfil in "+cad+" order by m.idMenu").list();
         return result;
    }

    @Transactional
    public List<Submenu> getSubmenu(int id_menu){
        Session session=this.sessionFactory.getCurrentSession();
         List<Submenu> result=session.createQuery("select m from Submenu m where m.menu.idMenu="+id_menu+" order by m.idSubmenu").list();
         return result;
    }

    @Transactional
    public void disableDir(String ci){
        Session session=this.sessionFactory.getCurrentSession();
            session.createSQLQuery("UPDATE administrador_director SET estado='INACTIVO' WHERE ci=:ci").setParameter("ci", ci).executeUpdate();         
    }

    @Transactional
    public void disableCoor(String ci){
        Session session=this.sessionFactory.getCurrentSession();
            session.createSQLQuery("UPDATE administrador_coordinador SET estado='INACTIVO' WHERE ci=:ci").setParameter("ci", ci).executeUpdate();
    }

    @Transactional
    public void update(Persona p) {
        personaDAO.update(p);
    }

    @Transactional
    public void merge(Persona p) {
        personaDAO.merge(p);
    }

    @Transactional
    public void delete(Persona p) {
        personaDAO.remove(p);
    }

    @Transactional
    public List<Persona> readAll() {
        return personaDAO.findAll();
    }

    @Transactional
    public void create(Persona p) {
        personaDAO.save(p);
    }

    @Transactional
    public Persona read(String ci) {
        return personaDAO.find(ci);
    }
}
