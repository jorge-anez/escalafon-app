/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.controlador.dao_interfaces.DocenteDAOInterface;
import dea.modelo.Docente;
import dea.modelo.Escalafon;
import dea.modelo.Persona;
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
public class DocenteDAO extends GenericDAO<Docente,String> implements DocenteDAOInterface,Serializable{
   public List<Persona>  getUsers(){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        List<Persona> result=session.createQuery("from Persona p where p.ci not in (select d.ci from Docente d)").list();
        trans.commit();
    return result;
   }
}
