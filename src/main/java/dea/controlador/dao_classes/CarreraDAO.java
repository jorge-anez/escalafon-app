/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.controlador.dao_interfaces.CarreraDAOInterface;
import dea.modelo.Carrera;
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
public class CarreraDAO extends GenericDAO<Carrera,Long> implements CarreraDAOInterface,Serializable{
     public CarreraDAO(){
       super();
    }

    @Override
    public List<Carrera> readCarrera(Long idFacultad) {
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();   
        List<Carrera> p=session.createQuery("from Carrera as c where c.facultad.idFacultad=:id").setParameter("id", idFacultad).list();
        //from Cat as cat where cat.mate.name like '%s%'
       // session.createCriteria(Facultad.class).
           // List<Facultad> p=(List<Facultad>)session.createCriteria(Facultad.class).add(Restrictions.eq("siglaUniversidad", sigla)).list();
        trans.commit();                
       return p;
    }

   
}
