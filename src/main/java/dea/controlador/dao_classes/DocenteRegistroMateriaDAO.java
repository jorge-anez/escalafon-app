/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.controlador.dao_interfaces.DocenteRegistroMateriaDAOInterface;
import dea.modelo.DocenteRegistroMateria;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Doppler
 */
@Repository
public class DocenteRegistroMateriaDAO extends GenericDAO<DocenteRegistroMateria,Long> implements DocenteRegistroMateriaDAOInterface,Serializable{
    
    public List<Object[]> getRegistro(Long idCarrera){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        List<Object[]> result=session.createQuery("from DocenteRegistroMateria r left join r.materia  as m left join r.docente.persona where m.carrera.idCarrera=:id").setParameter("id",idCarrera).list();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();   
        return result;
    }
    public List<Object[]> getRegistro(String g){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        List<Object[]> result=session.createQuery("from DocenteRegistroMateria r left join r.materia  as m where r.gestion=:g").setParameter("g",g).list();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();   
        return result;
    }
}
