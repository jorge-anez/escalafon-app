/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.controlador.dao_interfaces.EscalafonDAOInterface;
import dea.modelo.Escalafon;
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
public class EscalafonDAO extends GenericDAO<Escalafon,Long> implements EscalafonDAOInterface,Serializable {
    public List<Escalafon> getHistorial(String ci,String g){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        List<Escalafon> result=session.createQuery("from Escalafon e where e.docenteEscalafon.ci=:ci and e.gestion<=:g order by e.gestion DESC").setParameter("ci",ci).setParameter("g", g).list();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();   
        return result;
    }
    public Escalafon getEscalafon(String ci,String g){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        Escalafon result=(Escalafon)session.createQuery("from Escalafon e where e.docenteEscalafon.ci=:ci and e.gestion=:g").setParameter("ci",ci).setParameter("g", g).uniqueResult();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();   
        return result;
    }
    public String getResolucion(String ci,String g){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();  
        String cad=(String)session.createSQLQuery("SELECT resolucion FROM escalafon LEFT JOIN resolucion USING(tipo_res) WHERE ci=:ci AND gestion=:g").setParameter("ci",ci).setParameter("g", g).uniqueResult();
        //Escalafon result=(Escalafon)session.createQuery("from Escalafon e where e.docenteEscalafon.ci=:ci and e.gestion=:g").setParameter("ci",ci).setParameter("g", g).uniqueResult();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit(); 
        //System.out.print(cad);
        return cad;
    }
    
}
