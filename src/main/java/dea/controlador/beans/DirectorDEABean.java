/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.DirectorDeaDAO;
import dea.controlador.dao_classes.PersonaDAO;
import dea.modelo.AdministradorDirector;
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
public class DirectorDEABean implements Serializable{
    @Autowired
    private PersonaDAO personaDAO;
    @Autowired
    private DirectorDeaDAO directorDeaDAO;
    private Persona dir_dea;    
    private String ci;
    private Date fechaInicio,FechaFin;
   
 
    public void asignarDirDEA(){      
        if(dir_dea.getCi().compareTo("-1")!=0)
            personaDAO.disableDir(dir_dea.getCi());        
        AdministradorDirector  admin_dir=new AdministradorDirector();
        admin_dir.setCi(this.ci);
        Persona p=new Persona(this.ci);
        admin_dir.setPersona(p);
        admin_dir.setFechaInicio(this.fechaInicio);
        admin_dir.setFechaFin(this.FechaFin);
        admin_dir.setEstado("ACTIVO");        
       if(directorDeaDAO.read(ci)==null)
            directorDeaDAO.create(admin_dir);
       else
            directorDeaDAO.merge(admin_dir);
       this.ci="";
    }
    public void retirarCargo(String ci){
       
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

    
    public void nuevo(){
        
    }
    public Persona getDir_dea() {
        Object[] p=this.getPersonaDAO().findDirDEA();
        if(p==null) {
            dir_dea=new Persona("-1");
            dir_dea.setAdministradorDirector(new AdministradorDirector());            
        }
        else{
            dir_dea=(Persona)p[0];
            dir_dea.setAdministradorDirector((AdministradorDirector) p[1]);  
            dir_dea.setDocente((Docente) p[2]); 
        }
            
        return dir_dea;
    }

    /**
     * @param dir_dea the dir_dea to set
     */
    public void setDir_dea(Persona dir_dea) {
        this.dir_dea = dir_dea;
    }
    /**
     * @return the directorDeaDAO
     */
    public DirectorDeaDAO getDirectorDeaDAO() {
        return directorDeaDAO;
    }

    /**
     * @param directorDeaDAO the directorDeaDAO to set
     */
    public void setDirectorDeaDAO(DirectorDeaDAO directorDeaDAO) {
        this.directorDeaDAO = directorDeaDAO;
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
    
   
    
}
