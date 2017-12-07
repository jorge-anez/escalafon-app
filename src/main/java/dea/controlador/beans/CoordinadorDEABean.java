/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.CoorDAO;
import dea.controlador.dao_classes.PersonaDAO;
import dea.modelo.AdministradorCoordinador;
import dea.modelo.Docente;
import dea.modelo.Persona;
import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Doppler
 */
@Component
@Scope("session")
public class CoordinadorDEABean implements Serializable{
    @Autowired
    private PersonaDAO personaDAO;
    @Autowired
    private CoorDAO coorDAO;
    private Persona coor_dea;    
    private String ci;
    private Date fechaInicio,FechaFin;   
    
    public void asignarCoorDEA(){
        if(coor_dea.getCi().compareTo("-1")!=0)
            personaDAO.disableCoor(coor_dea.getCi());
        
        AdministradorCoordinador  admin_coor=new AdministradorCoordinador();
        //coor_dea.setCi(this.ci);
        Persona p=new Persona(this.ci);
        admin_coor.setCi(ci);
        admin_coor.setPersona(p);
        admin_coor.setFechaInicio(this.fechaInicio);
        admin_coor.setFechaFin(this.FechaFin);
        admin_coor.setEstado("ACTIVO");        
        
       if(coorDAO.read(this.ci)==null)
            coorDAO.create(admin_coor);
       else
            coorDAO.merge(admin_coor);
        this.ci="";
    }
    public void nuevo(){
        
    }
    /**
     * @return the personaDAO
     */
    public PersonaDAO getPersonaDAO() {
        return personaDAO;
    }

    /**
     * @param personaDAO the personaDAO to set
     */
    public void setPersonaDAO(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

       
    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        if(fechaInicio==null)
            return new Date();
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the FechaFin
     */
    public Date getFechaFin() {
        if(FechaFin==null)
            return new Date();
        return FechaFin;
    }

    /**
     * @param FechaFin the FechaFin to set
     */
    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }  

    /**
     * @return the coorDAO
     */
    public CoorDAO getCoorDAO() {
        return coorDAO;
    }

    /**
     * @param coorDAO the coorDAO to set
     */
    public void setCoorDAO(CoorDAO coorDAO) {
        this.coorDAO = coorDAO;
    }

    /**
     * @return the coor_dea
     */
    public Persona getCoor_dea() {
         Object[] p=this.personaDAO.findCoorDEA();
        if(p==null) {
            coor_dea=new Persona("-1");
            coor_dea.setAdministradorCoordinador(new AdministradorCoordinador());            
        }
        else{
            coor_dea=(Persona)p[0];
            coor_dea.setAdministradorCoordinador((AdministradorCoordinador) p[1]);  
            coor_dea.setDocente((Docente) p[2]); 
        }        
        return coor_dea;
    }

    /**
     * @return the ci
     */
    public String getCi() {
        return ci;
    }

    /**
     * @param ci the ci to set
     */
    public void setCi(String ci) {
        this.ci = ci;
    }

    /**
     * @return the render
     */
    
}
