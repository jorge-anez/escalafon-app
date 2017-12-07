/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.controlador.dao_interfaces.FacultadDAOInterface;
import dea.controlador.dao_interfaces.UniversidadDAOInterface;
import dea.modelo.Facultad;
import dea.modelo.Universidad;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Doppler
 */
@Repository
public class FacultadDAO extends GenericDAO<Facultad,Long> implements FacultadDAOInterface,Serializable{
     public FacultadDAO(){
       super();      
    }

    @Override
    public List<Facultad> readFacultdad(String sigla) {
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();   
        List<Facultad> p=session.createQuery("from Facultad as f where f.universidad.siglaUniversidad=:sigla").setParameter("sigla", sigla).list();
        //from Cat as cat where cat.mate.name like '%s%'
       // session.createCriteria(Facultad.class).
           // List<Facultad> p=(List<Facultad>)session.createCriteria(Facultad.class).add(Restrictions.eq("siglaUniversidad", sigla)).list();
        trans.commit();
        
                
       return p;
    }
}
