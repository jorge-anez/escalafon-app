/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;
import dea.controlador.HibernateUtil;
import dea.controlador.dao_interfaces.PersonaDAOInterface;
import dea.modelo.Persona;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Doppler
 */
//@Repository
//@Component
//@Service
@Repository
//@Transactional
//@Scope("session")
public final class PersonaDAO extends GenericDAO<Persona,String> implements PersonaDAOInterface,Serializable {
    //@Autowired
    
    public PersonaDAO(){
       super();
     //  super.getSessionFactory().openSession();
    }
    @Override
    //@Transactional
    public Persona findByUsername(String username) {
      Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
            Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta", username)).uniqueResult();           
        trans.commit();
        
       // Session session= super.getSessionFactory().openSession();
       // session.get(Persona.class, session)
       // Session session=HibernateUtil.getSessionFactory().openSession();
      //   Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta", username)).uniqueResult();
        //Persona p=null;
       return p;
        //return super.getSessionFactory() getSessionFactory().getCurrentSession().get(getType(), id);
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] findDirDEA(){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        Object[] result=(Object[])session.createQuery("from Persona p inner join p.administradorDirector as a left join p.docente as d").uniqueResult();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();        
        return result;
    }

    @Override
    public Object[] findCoorDEA() {
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        Object[] result=(Object[])session.createQuery("from Persona p inner join p.administradorCoordinador as a left join p.docente as d").uniqueResult();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();        
        return result;
    }
    public List<Object[]> getDocente(){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        List<Object[]> result=session.createQuery("from Persona p inner join p.docente as d").list();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();        
        return result;
    }
    public List<Persona> getUserNotDocente(){
         Session session=this.getSession();  
         Transaction trans=session.beginTransaction();        
            List<Persona> result=session.createQuery("select p from Persona p  left join p.docente as d where d.ci=null").list();           
        trans.commit();
        return result;
    }
}
