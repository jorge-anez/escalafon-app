/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.GenericDAOImpl;
import dea.controlador.dao_interfaces.EscalafonDAOInterface;
import dea.modelo.Escalafon;
import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 *
 * @author Doppler
 */
@Service
@Transactional
public class EscalafonDAO{
    @Autowired
    private SessionFactory sessionFactory;

    private GenericDAOImpl<Escalafon,Long> escalafonDAO;
    @PostConstruct
    public void init() {
        escalafonDAO = new GenericDAOImpl<Escalafon,Long>(sessionFactory, Escalafon.class);
    }

    public List<Escalafon> getHistorial(String ci,String g){
        Session session=this.sessionFactory.getCurrentSession();
        List<Escalafon> result=session.createQuery("from Escalafon e where e.docenteEscalafon.ci=:ci and e.gestion<=:g order by e.gestion DESC").setParameter("ci",ci).setParameter("g", g).setMaxResults(5).list();
        return result;
    }

    public Escalafon getEscalafon(String ci,String g){
        Session session=this.sessionFactory.getCurrentSession();
        Escalafon result=(Escalafon)session.createQuery("from Escalafon e where e.docenteEscalafon.ci=:ci and e.gestion=:g").setParameter("ci",ci).setParameter("g", g).uniqueResult();
        return result;
    }

    public String getResolucion(String ci,String g){
        Session session=this.sessionFactory.getCurrentSession();
        String sql="SELECT resolucion_docente FROM escalafon WHERE ci=:ci AND gestion=:g";
        String result=(String)session.createSQLQuery(sql).setParameter("ci",ci).setParameter("g", g).uniqueResult();
        return result;
    }

    public String getResolucionPlantilla(String ci,String g){
        Session session=this.sessionFactory.getCurrentSession();
        String result=(String)session.createSQLQuery("SELECT resolucion FROM escalafon LEFT JOIN resolucion USING(tipo_res) WHERE ci=:ci AND gestion=:g").setParameter("ci",ci).setParameter("g", g).uniqueResult();
        return result;
    }

    public void saveResolucion(String ci,String g,String res){
        Session session=this.sessionFactory.getCurrentSession();
                session.createSQLQuery("UPDATE escalafon SET resolucion_docente=:res WHERE ci=:ci AND gestion=:g").setParameter("ci",ci).setParameter("g", g).setParameter("res", res).executeUpdate();
    }

    public void savePlantillaResolucion(int id,String r){
        Session session=this.sessionFactory.getCurrentSession();
                session.createSQLQuery("UPDATE resolucion SET resolucion=:r WHERE tipo_res=:id").setParameter("id",id).setParameter("r", r).executeUpdate();
    }
    public String getPlantillarResolucion(int id){
        Session session=this.sessionFactory.getCurrentSession();
        String result=(String)session.createSQLQuery("SELECT resolucion FROM resolucion WHERE tipo_res=:id").setParameter("id",id).uniqueResult();
        return result;
    }
    public List<Object[]> getReporte(){
        Session session=this.sessionFactory.getCurrentSession();
            String sql=
                    "SELECT d.docente,d.facultad,d.carrera,es.escalafon FROM(  "
                    +"          SELECT"
                    +"           p.ci"
                    +"          ,p.nombre||' '||p.apellido as docente"
                    +"          ,string_agg(CASE WHEN f.nombre ISNULL THEN ' ' ELSE f.nombre END,',') as facultad"
                    +"          ,string_agg(CASE WHEN c.nombre ISNULL THEN ' ' ELSE c.nombre END,',') as carrera"
                    +"      FROM persona p"
                    +"      LEFT JOIN docente_escalafon de USING(ci)"
                    +"      LEFT JOIN carrera c USING(id_carrera)"
                    +"      LEFT JOIN facultad f USING(id_facultad)"
                    +"      GROUP BY p.ci"
                    +"    ) d "
                    +"    INNER JOIN("
                    +"        SELECT e.ci"
                    +"                ,string_agg(e.gestion||':'||e.puntaje||':'||e.puntaje_acumulado||':'||e.categoria,',') as escalafon"
                    +"        FROM"
                    +"            escalafon e"
                    +"        GROUP BY e.ci"
                    +"    ) es USING(ci)";
            List<Object[]> result=session.createSQLQuery(sql).list();            

        Object[] range=this.getRange();
        int from=(Integer)range[0];
        int to=(Integer)range[1];
        int i=0;        
        TreeMap<Integer,String> cat=new TreeMap<Integer, String>();
        for (int j=0;j<result.size();j++){            
            Object[] newRow=new Object[7+2*(to-from)];            
            newRow[0]=++i;
            newRow[1]=result.get(j)[0];
            newRow[2]=result.get(j)[1];
            newRow[3]=result.get(j)[2];
                String[] tokens=((String)result.get(j)[3]).split(",");                
                for(String w:tokens){                    
                    String[] v=w.split(":");
                    int year=Integer.parseInt((String)v[0]);
                    newRow[4+2*(year-from)]     =v[1];
                    newRow[4+2*(year-from)+1]   =v[2];                    
                    cat.put(year, v[3]);
                }
                newRow[4+2*(to-from)+2]=cat.lastEntry().getValue();
                result.set(j, newRow);                
                cat.clear();
        }      
        return result;
    }
    public List<Object[]> getReporteRagne(int from, int to){
        Session session=this.sessionFactory.getCurrentSession();
            String sql=
                    "SELECT d.docente,d.facultad,d.carrera,es.escalafon FROM(  "
                    +"          SELECT"
                    +"           p.ci"
                    +"          ,p.nombre||' '||p.apellido as docente"
                    +"          ,string_agg(CASE WHEN f.nombre ISNULL THEN ' ' ELSE f.nombre END,',') as facultad"
                    +"          ,string_agg(CASE WHEN c.nombre ISNULL THEN ' ' ELSE c.nombre END,',') as carrera"
                    +"      FROM persona p"
                    +"      LEFT JOIN docente_escalafon de USING(ci)"
                    +"      LEFT JOIN carrera c USING(id_carrera)"
                    +"      LEFT JOIN facultad f USING(id_facultad)"
                    +"      GROUP BY p.ci"
                    +"    ) d "
                    +"    INNER JOIN("
                    +"        SELECT e.ci"
                    +"                ,string_agg(e.gestion||':'||e.puntaje||':'||e.puntaje_acumulado||':'||e.categoria,',') as escalafon"
                    +"        FROM"
                    +"            escalafon e"
                    +"        WHERE cast(e.gestion as integer)>=:__from and cast(e.gestion as integer)<=:__to"
                    +"        GROUP BY e.ci"
                    +"    ) es USING(ci)";
            List<Object[]> result=session.createSQLQuery(sql).setParameter("__from", from).setParameter("__to", to).list();
        Object[] range=this.getRange();
        int i=0;        
        TreeMap<Integer,String> cat=new TreeMap<Integer,String>();
        for (int j=0;j<result.size();j++){            
            Object[] newRow=new Object[7+2*(to-from)];            
            newRow[0]=++i;
            newRow[1]=result.get(j)[0];
            newRow[2]=result.get(j)[1];
            newRow[3]=result.get(j)[2];
                String[] tokens=((String)result.get(j)[3]).split(",");                
                for(String w:tokens){                    
                    String[] v=w.split(":");
                    int year=Integer.parseInt((String)v[0]);
                    newRow[4+2*(year-from)]     =v[1];
                    newRow[4+2*(year-from)+1]   =v[2];                    
                    cat.put(year, v[3]);
                }
                newRow[4+2*(to-from)+2]=cat.lastEntry().getValue();
                result.set(j, newRow);                
                cat.clear();
        }
        return result;
    }
    public Object[] getRange(){
        Session session=this.sessionFactory.getCurrentSession();
           //String sql="select min(cast(gestion as integer)),max(cast(gestion as integer)) from docente_registro_materia";
        String sql="select min(gestion),max(gestion) from docente_registro_materia";
            Object[] range=(Object[])session.createSQLQuery(sql).uniqueResult();
        return range;
    }

    public void update(Escalafon escalafon) {
        escalafonDAO.update(escalafon);
    }

    public void create(Escalafon escalafon) {
        escalafonDAO.save(escalafon);
    }
}
