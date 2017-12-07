/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.DirectorDeaDAO;
import dea.controlador.dao_classes.PersonaDAO;
import dea.modelo.AdministradorCoordinador;
import dea.modelo.AdministradorDirector;
import dea.modelo.Docente;
import dea.modelo.Persona;
import java.util.Date;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Doppler
 */
@Component
@Scope("session")
public class DirectorDEABean {
    @Autowired
    private PersonaDAO personaDAO;
    @Autowired
    private DirectorDeaDAO directorDeaDAO;
    private Persona dir_dea;
    //private AdministradorDirector admin_dir;
    //private Docente doc;
    private String ci;
    private Date fechaInicio,FechaFin;
   
    public void asignarDirDEA(String ci){
        //admin_dir=new AdministradorDirector();
       // admin_dir.setCi(p.getCi());
       
        this.ci=ci;
    }
    public void asignarDirDEA(){
      //  admin_dir.setFechaInicio(new Date(fechaInicio));
      //  admin_dir.setFechaFin(new Date(FechaFin));
      AdministradorDirector  admin_dir=new AdministradorDirector();
        admin_dir.setCi(this.ci);
        Persona p=new Persona(this.ci);
        admin_dir.setPersona(p);
        admin_dir.setFechaInicio(this.fechaInicio);
        admin_dir.setFechaFin(this.FechaFin);
        directorDeaDAO.create(admin_dir);
       // JOptionPane.showMessageDialog(null, "maldicion "+getFechaInicio()+ "  "+getFechaFin() ); 
    }
    public void retirarCargo(Persona a){
        AdministradorDirector y=new AdministradorDirector();
        y.setCi(a.getCi());
        y.setFechaInicio(a.getAdministradorDirector().getFechaInicio());
        y.setFechaFin(a.getAdministradorDirector().getFechaFin());
        getDirectorDeaDAO().delete(y);        
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
     * @return the dir_dea
     */
    public Persona getDir_dea() {
       
            //JOptionPane.showMessageDialog(null, "  "+dir_dea.getAdministradorDirector().getFechaInicio());
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
    
    public boolean  render(){
         Object[] p=this.getPersonaDAO().findDirDEA();
        if(p==null) return false;           
            dir_dea=(Persona)p[0];
            dir_dea.setAdministradorDirector((AdministradorDirector) p[1]);
            dir_dea.setDocente((Docente) p[2]);
        return true;
    }
   

    
}
