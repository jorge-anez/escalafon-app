/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.PersonaDAO;
import dea.modelo.Menu;
import dea.modelo.Perfil;
import dea.modelo.PerfilPersona;
import dea.modelo.Persona;
import dea.modelo.Submenu;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
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
    private Map<String,Integer> perfiles=new HashMap<String,Integer>(){{                                                                         
                                                                        put("Administrador",1);
                                                                        put("Director DEA",2);
                                                                        put("Coordinador DEA",3);
                                                                        put("Docente",4);       
                                                                    }};
    private Integer[] selectedPerfiles;
    @Autowired
    private PersonaDAO personaDAO;   
    private Persona user_session;
    /**
     * Creates a new instance of PersonaBean
     */   
    public void changeTheme(AjaxBehaviorEvent vce){
        String str= (String) ((UIOutput) vce.getSource()).getValue();
        FacesContext context= FacesContext.getCurrentInstance();
        Persona psession=(Persona)context.getExternalContext().getSessionMap().get("user");
        Persona p=new Persona();
        p.setCi(psession.getCi());
        p.setNombre(psession.getNombre());
        p.setApellido(psession.getApellido());
        p.setCuenta(psession.getCuenta());
        p.setEstado(psession.getEstado());
        p.setContrasenia(psession.getContrasenia());
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
           }else
               if(user_session.getEstado().compareTo("ACTIVO")==0){
                    this.theme=user_session.getTheme();               
                    context.getExternalContext().getSessionMap().put("user",user_session);            
                    context.getExternalContext().redirect("crud/welcome.xhtml");
               }else{
                    context.getExternalContext().getSessionMap().clear();
                    FacesMessage msg=new FacesMessage("Usuario Inactivo Consulte con el Admin del Sistema!!", "EROR MSG");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    context.getCurrentInstance().addMessage(null, msg);
               }
           
            } catch (IOException e) {
              }
    }
    public void restorePassword(Persona p){
        p.setContrasenia(encriptPassword(p.getCi()));
        personaDAO.merge(p);
    }
    public void activarDesactivar(Persona p){
        if(p.getEstado()==null)
            p.setEstado("ACTIVO");
        String estado=p.getEstado().compareTo("ACTIVO")==0?"INACTIVO":"ACTIVO";
        p.setEstado(estado);
        personaDAO.merge(p);
    }
    public List<Menu> getPersona_menu(){
        if(user_session==null) return null;
        Set<PerfilPersona> perfil=user_session.getPerfilPersonas();
               ArrayList<Integer> p=new ArrayList<Integer>();
               p.add(-1);
               for(PerfilPersona e:perfil)
                   p.add(e.getPerfil().getIdPerfil());
               
               String cad_perfiles=p.toString().replace('[','(').replace(']',')');
               List<Menu> m=personaDAO.getMenu(cad_perfiles);
        return  m;
    }
    public String perfil_usuario(){
        FacesContext context= FacesContext.getCurrentInstance();
        Persona p=(Persona)context.getExternalContext().getSessionMap().get("user");
        Set<Integer> h=personaDAO.getPerfiles(p.getCi());
        String perfil="";
        for(Map.Entry<String,Integer> e:perfiles.entrySet())
            if(h.contains(e.getValue()))
                perfil+=" "+e.getKey();
        return perfil;
    }
    public void cancel(){
        selectedPerfiles=null;
    }
    public List<Submenu> getSub_menu(int id_menu){         
               List<Submenu> m=personaDAO.getSubmenu(id_menu);            
        return  m;
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
       pSelected.setContrasenia(p.getContrasenia());
       pSelected.setEstado(p.getEstado());
       pSelected.setTheme(p.getTheme());       
       selectedPerfiles=new Integer[p.getPerfilPersonas().size()];
       int i=0;
       for(Object e:p.getPerfilPersonas())
            selectedPerfiles[i++]=((PerfilPersona)e).getPerfil().getIdPerfil();       
       if(p.getTheme()==null)
           pSelected.setTheme("cupertino");
   }
   public void updateItem(){
        Set p=pSelected.getPerfilPersonas();
        for(Integer e:selectedPerfiles){
              PerfilPersona pf=new PerfilPersona();
                      pf.setPerfil(new Perfil(e));     
                      pf.setPersona(pSelected);                     
              p.add(pf);
       }       
       this.personaDAO.merge(pSelected);       
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
        this.getPersonaDAO().delete(pSelected);
        users=(ArrayList<Persona>) this.personaDAO.readAll();
    }
    
  public void createItem(){
      pSelected=new Persona();      
  }
  public void createItem(boolean  sw){      
      Set p=pSelected.getPerfilPersonas();      
      for(Integer e:selectedPerfiles){
            PerfilPersona pf=new PerfilPersona();
                    pf.setPerfil(new Perfil(e));     
                    pf.setPersona(pSelected);
            p.add(pf);            
      }            
      this.pSelected.setContrasenia(encriptPassword(this.pSelected.getContrasenia()));
      this.pSelected.setTheme("cupertino");
      this.pSelected.setPerfilPersonas(p);
      this.pSelected.setEstado("ACTIVO");
      this.personaDAO.create(pSelected);
      selectedPerfiles=null;
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
     * @return the perfiles
     */
    public Map<String,Integer> getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(Map<String,Integer> perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * @return the selectedPerfiles
     */
    public Integer[] getSelectedPerfiles() {
        return selectedPerfiles;
    }

    /**
     * @param selectedPerfiles the selectedPerfiles to set
     */
    public void setSelectedPerfiles(Integer[] selectedPerfiles) {
        this.selectedPerfiles = selectedPerfiles;
    }
    
}
