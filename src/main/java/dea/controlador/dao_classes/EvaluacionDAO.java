/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.controlador.dao_interfaces.EvaluacionDAOInterface;
import dea.modelo.Carrera;
import dea.modelo.Cartilla;
import dea.modelo.ContenidoCartilla;
import dea.modelo.DocenteEscalafon;
import dea.modelo.DocenteRegistroMateria;
import dea.modelo.Escalafon;
import dea.modelo.Evaluacion;
import dea.modelo.Facultad;
import dea.modelo.Materia;
import dea.modelo.Universidad;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Doppler
 */
@Repository
public class EvaluacionDAO extends GenericDAO<Evaluacion,Long> implements EvaluacionDAOInterface,Serializable{
    
    public List<Object[]> getRegistro(String g){
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        List<Object[]> result=session.createQuery("from Evaluacion e inner join e.docenteRegistroMateria r left join r.materia where r.gestion=:g").setParameter("g",g).list();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();   
        return result;
    }
    public List<Object[]> getMaterias(String ci,String g){
    Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        List<Object[]> result=session.createQuery("from Evaluacion e inner join e.docenteRegistroMateria r left join r.materia where r.docente.ci=:ci and r.gestion=:g").setParameter("g",g).setParameter("ci", ci).list();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult();           
        trans.commit();   
        return result;
    }
    public List<ContenidoCartilla> getMateriasCartilla(String ci,String g){
    Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        List<Object[]> result=session.createQuery("from ContenidoCartilla c inner join c.evaluacion e left join e.docenteRegistroMateria r left join r.materia where r.docente.ci=:ci and r.gestion=:g").setParameter("g",g).setParameter("ci", ci).list();        
        trans.commit(); 
        List<ContenidoCartilla> r=new ArrayList<ContenidoCartilla>();
        for (Object[] e : result) {
            ContenidoCartilla c=(ContenidoCartilla) e[0];
            Evaluacion eval=(Evaluacion) e[1];
            DocenteRegistroMateria reg=(DocenteRegistroMateria) e[2];
            Materia m=(Materia) e[3];
                    reg.setMateria(m);
                    eval.setDocenteRegistroMateria(reg);
                    c.setEvaluacion(eval);
                    r.add(c);
        }
        return r;
    }
    public Cartilla getPuntajeEvaluacion(String ci,String g){
    Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        Cartilla r=(Cartilla) session.createQuery("from Cartilla c where c.docenteEscalafon.ci=:ci and c.gestion=:g ").setParameter("g",g).setParameter("ci", ci).uniqueResult();
        trans.commit();        
        return r;
    }
    public DocenteEscalafon getUniversidad(String ci){
       Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        Object[] result=(Object[]) session.createQuery("from  DocenteEscalafon de inner join de.carrera c  inner join c.facultad f inner join f.universidad where de.ci=:ci").setParameter("ci", ci).uniqueResult();        
//Persona p=(Persona) session.createCriteria(Persona.class).add(Restrictions.eq("cuenta",)).uniqueResult(); 
        trans.commit();   
        Universidad u=(Universidad) result[3];
        Facultad    f=(Facultad) result[2];
        Carrera  c=(Carrera) result[1];
        DocenteEscalafon de=(DocenteEscalafon) result[0];
        f.setUniversidad(u);
        c.setFacultad(f);
        de.setCarrera(c);
            
        return de;
    }
    public Escalafon getEscalafon(String ci,String g){
    Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        Escalafon r=(Escalafon) session.createQuery("from Escalafon e where e.cartilla.docenteEscalafon.ci=:ci and e.gestion=:g ").setParameter("g",g).setParameter("ci", ci).uniqueResult();
        trans.commit();       
        return r;
    }
    public Escalafon getEscalafonAnt(String ci,String g){
      int x=Integer.parseInt(g)-1;
      Session session=this.getSession();       
        Transaction trans=session.beginTransaction();        
        Escalafon r=(Escalafon) session.createQuery("from Escalafon e where e.cartilla.docenteEscalafon.ci=:ci and e.gestion=:g ").setParameter("g",String.valueOf(x)).setParameter("ci", ci).uniqueResult();
        trans.commit();
        return r;
    }
    public void updateEscalafon(Escalafon esc){        
        Session session=this.getSession();       
        Transaction trans=session.beginTransaction(); 
        String sql=String.format("update escalafon set puntaje="+esc.getPuntaje()+", puntaje_acumulado="+esc.getPuntajeAcumulado()+",observaciones='%s',tipo_res=%d where id_escalafon=%d",esc.getObs(),esc.getTipoRes(),esc.getIdEscalafon());               
        session.createSQLQuery(sql).executeUpdate();
        trans.commit();         
    }
}