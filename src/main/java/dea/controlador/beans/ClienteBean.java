/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.PersonaDAO;
import dea.modelo.Persona;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Doppler
 */
//@ManagedBean
//@SessionScoped
@Component
@Scope("session")
public class ClienteBean implements Serializable{            
    private Persona pSelected;    
    @Autowired
    private PersonaDAO personaDAO;
    private String password_old,password_new;
  public void updateUser(){
      FacesContext context=FacesContext.getCurrentInstance();
      Persona p=personaDAO.read(pSelected.getCi());     
      if(encriptPassword(this.password_old).compareTo(p.getContrasenia())==0){
            pSelected.setContrasenia(encriptPassword(this.password_new));
            personaDAO.merge(pSelected);
            /*
            FacesMessage msg=new FacesMessage("Se ha Actualizado!!", "EROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            context.getCurrentInstance().addMessage(null, msg);
            */
            this.password_new=this.password_old="";       
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Datos Actualizados."));
      }
      else{
          /*
          FacesMessage msg=new FacesMessage("Ocurrio un Problema!!", "EROR MSG");
               msg.setSeverity(FacesMessage.SEVERITY_WARN);
               context.getCurrentInstance().addMessage(null, msg);
          */
            this.password_new=this.password_old="";       
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "No se ha podido actualizar"));
          
      }         
       
  }
  private String encriptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(password.getBytes());
            int size = b.length;
            StringBuffer h = new StringBuffer(size);
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    h.append("0" + Integer.toHexString(u));
                } else {
                    h.append(Integer.toHexString(u));
                }
            }
            password = h.toString();
        } catch (Exception e) {
        }
        return password;
    }
   
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
     * @return the pSelected
     */
    public Persona getpSelected() {
            FacesContext context= FacesContext.getCurrentInstance();    
            Map m=context.getExternalContext().getSessionMap();
            pSelected=(Persona)m.get("user");         
        return pSelected==null?new Persona():pSelected;
    }

    /**
     * @param pSelected the pSelected to set
     */
    public void setpSelected(Persona pSelected) {
        
        this.pSelected = pSelected;
    }    

  
    /**
     * @return the password_new
     */
    public String getPassword_new() {
        return password_new;
    }

    /**
     * @param password_new the password_new to set
     */
    public void setPassword_new(String password_new) {
        this.password_new = password_new;
    }

    /**
     * @return the password_old
     */
    public String getPassword_old() {
        return password_old;
    }

    /**
     * @param password_old the password_old to set
     */
    public void setPassword_old(String password_old) {
        this.password_old = password_old;
    }
}
