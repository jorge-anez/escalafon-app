/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.controlador.dao_interfaces.DocenteEscalafonDAOInterface;
import dea.modelo.DocenteEscalafon;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class DocenteEscalafonDAO extends GenericDAO<DocenteEscalafon,Long> implements DocenteEscalafonDAOInterface,Serializable{
    
    public List<Object[]> getRegistro(Long idCarrera){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        List<Object[]> result=session.createQuery("from DocenteEscalafon r left join r.docente.persona where r.carrera.idCarrera=:id").setParameter("id",idCarrera).list();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();   
        return result;
    }
}