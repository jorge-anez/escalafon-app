/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.PersonaDAO;
import dea.controlador.dao_classes.TestClass;
import dea.modelo.AdministradorCoordinador;
import dea.modelo.AdministradorDirector;
import dea.modelo.Docente;
import dea.modelo.Estudiante;
import dea.modelo.Persona;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.MenuModel;
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
public class PersonaBean implements Serializable{            
    private Persona pSelected;    
    private String theme="afternoon";
    private ArrayList<Persona> users;
    private List<String> perfiles;
    @Autowired
    private PersonaDAO personaDAO;   
    private Persona user_session;
    /**
     * Creates a new instance of PersonaBean
     */   
    public void changeTheme(AjaxBehaviorEvent vce){
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        //JOptionPane.showMessageDialog(null, ""+str);
        Persona p=new Persona();
        p.setCi(user_session.getCi());
        p.setNombre(user_session.getNombre());
        p.setApellido(user_session.getApellido());
        p.setCuenta(user_session.getCuenta());
        p.setContrasenia(user_session.getContrasenia());
        p.setTheme(str);
        personaDAO.update(p);
    }
    public void login(){    
        try {        
            FacesContext context= FacesContext.getCurrentInstance();
            String password=encriptPassword(user_session.getContrasenia());
            user_session=getPersonaDAO().findByUsername(user_session.getCuenta());
           if(user_session==null||user_session.getContrasenia().compareTo(password)!=0){
               context.getExternalContext().getSessionMap().clear();
               FacesMessage msg=new FacesMessage("Usuario o contraseña son incorrectos!!", "EROR MSG");
               msg.setSeverity(FacesMessage.SEVERITY_ERROR);
               context.getCurrentInstance().addMessage(null, msg);
           }else{         
               this.theme=user_session.getTheme();
               context.getExternalContext().getSessionMap().put("user",user_session);            
               context.getExternalContext().redirect("crud/universidad.xhtml");       
           }
            } catch (Exception e) {
                e.printStackTrace();
// System.out.println("Error.."+e.);
              }
    }
     public void logout(){       
         try {
             FacesContext context= FacesContext.getCurrentInstance();             
             context.getExternalContext().getSessionMap().clear();
             this.theme="afternoon";
             context.getExternalContext().redirect("../login.xhtml");             
         } catch (Exception e) {
         }         
    }
    public void isLogged(ComponentSystemEvent event){  
    try{
        FacesContext context= FacesContext.getCurrentInstance();    
        Map m=context.getExternalContext().getSessionMap();
        if(!m.containsKey("user")){        
            m.clear();
            context.getExternalContext().redirect("../login.xhtml"); 
        }
       }catch(IOException exp){}
    }
   public void updateItem(Persona p){
       pSelected=new Persona();
       pSelected.setCi(p.getCi());
       pSelected.setNombre(p.getNombre());
       pSelected.setApellido(p.getApellido());
       pSelected.setCuenta(p.getCuenta());
       pSelected.setTheme(p.getTheme());
       if(p.getTheme()==null)
           pSelected.setTheme("cupertino");
   }
   public void updateItem(){
       pSelected.setContrasenia(this.encriptPassword(pSelected.getContrasenia()));
       this.getPersonaDAO().update(pSelected);
   }
   public void viewItem(Persona p){
       pSelected=new Persona();
       pSelected.setCi(p.getCi());
       pSelected.setNombre(p.getNombre());
       pSelected.setApellido(p.getApellido());
       pSelected.setCuenta(p.getCuenta());       
   }
   public void removeItem(Persona p){
        pSelected=new Persona();
        pSelected.setCi(p.getCi());        
    }
    public void removeItem(){
        //JOptionPane.showMessageDialog(null, "se va aliminar "+pSelected.getCi());
        this.getPersonaDAO().delete(pSelected);
        users=(ArrayList<Persona>) this.personaDAO.readAll();
    }
  public void createItem(){
      pSelected=new Persona();
      perfiles=new ArrayList<String>();
  }
  public void createItem(boolean  sw){    
      this.pSelected.setContrasenia(encriptPassword(this.pSelected.getContrasenia()));
      this.pSelected.setTheme("cupertino");
      this.personaDAO.create(pSelected);
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
    /**
     * @return the tipoUsuario
     */
  /*
    public Integer getTipoUsuario() {
        return tipoUsuario;
    }
*/
    /**
     * @param tipoUsuario the tipoUsuario to set
     */
  /*
    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
  */
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
     * @return the users
     */
    public ArrayList<Persona> getUsers() {        
        users=(ArrayList<Persona>) this.personaDAO.readAll();
        
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(ArrayList<Persona> users) {
        this.users = users;
    }

    /**
     * @return the pSelected
     */
    public Persona getpSelected() {
        return pSelected;
    }

    /**
     * @param pSelected the pSelected to set
     */
    public void setpSelected(Persona pSelected) {
        
        this.pSelected = pSelected;
    }

    /**
     * @return the perfiles
     */
    public List<String> getPerfiles() {
        if(perfiles==null)
            perfiles=new ArrayList<String>(4);
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(List<String> perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        if(theme==null)
            theme="cupertino";
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * @return the user_session
     */
    public Persona getUser_session() {
        if(user_session==null)
            user_session=new Persona();        
        return user_session;
    }

    /**
     * @param user_session the user_session to set
     */
    public void setUser_session(Persona user_session) {
        this.user_session = user_session;
    }

    /**
     * @return the username
     */
    /*
    public String getUsername() {
        theme="afternoon";
        return username;
    }

    /**
     * @param username the username to set
     */
    /*
    public void setUsername(String username) {
        this.username = username;
    }  
    */

    /**
     * @return the password
     */
    /*
    public String getPassword() {
        return password;
    }
*/
    /**
     * @param password the password to set
     */
    /*
    public void setPassword(String password) {
        this.password = password;
    }
    */
}
