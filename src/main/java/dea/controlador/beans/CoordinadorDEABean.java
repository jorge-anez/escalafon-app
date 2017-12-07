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
public class CoordinadorDEABean implements Serializable{
    @Autowired
    private PersonaDAO personaDAO;
    @Autowired
    private CoorDAO coorDAO;
    private Persona coor_dea,pSelected;
    //private AdministradorCoordinador admin_coor;
    //private Docente doc;
    private String ci;
    private Date fechaInicio,FechaFin;
    //private boolean render;
   
    public void asignarDirDEA(String ci){       
        this.ci=ci;
        //this.ci=p.getCi();
    }
    public void asignarDirDEA(){
      //  admin_coor.setFechaInicio(new Date(fechaInicio));
      //  admin_coor.setFechaFin(new Date(FechaFin));
      AdministradorCoordinador  y=new AdministradorCoordinador();
        y.setCi(this.ci);
        Persona p=new Persona(this.ci);
        y.setPersona(p);
        y.setFechaInicio(this.fechaInicio);
        y.setFechaFin(this.FechaFin);
        coorDAO.create(y);
       // JOptionPane.showMessageDialog(null, "maldicion "+getFechaInicio()+ "  "+getFechaFin() ); 
        
    }
    public void retirarCargo(Persona a){
        AdministradorCoordinador y=new AdministradorCoordinador();
        y.setCi(a.getCi());
        y.setFechaInicio(a.getAdministradorCoordinador().getFechaInicio());
        y.setFechaFin(a.getAdministradorCoordinador().getFechaFin());
        this.coorDAO.delete(y);
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
        return getCoor_dea();
    }

    /**
     * @param dir_dea the dir_dea to set
     */
    public void setCoor_dea(Persona coor_dea) {
        this.coor_dea = coor_dea;
    }

  
/*
    
    public Docente getDoc() {
        doc=this.getDir_dea().getDocente();
        return doc;
    }

    
    public void setDoc(Docente doc) {
        this.doc = doc;
    }
*/
   
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

   
    /*
    public AdministradorCoordinador getAdmin_coor() {
        return admin_coor;
    }

   
    public void setAdmin_coor(AdministradorCoordinador admin_coor) {
        this.admin_coor = admin_coor;
    }
*/
  

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
        return coor_dea;
    }

    /**
     * @return the render
     */
    public boolean render() {
        Object[] p=this.personaDAO.findCoorDEA();
        if(p==null) return false;
        //JOptionPane.showMessageDialog(null, ">"+p.length);                  
            setCoor_dea((Persona)p[0]);
            getCoor_dea().setAdministradorCoordinador((AdministradorCoordinador) p[1]);
            getCoor_dea().setDocente((Docente) p[2]);
            
       
        //JOptionPane.showMessageDialog(null, "joder");
        return true;
    }   
}
