/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.CarreraDAO;
import dea.controlador.dao_classes.FacultadDAO;
import dea.controlador.dao_classes.UniversidadDAO;
import dea.modelo.Carrera;
import dea.modelo.Facultad;
import dea.modelo.Universidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Doppler
 */
@Component
@Scope("session")
public class FacultadBean implements Serializable{    
    private ArrayList<Universidad> universidadList;
    private ArrayList<Facultad> facultadList; 
    private Facultad fSelected;
    private Universidad uSelected;
    
    @Autowired     
    private UniversidadDAO universidadDAO;
    @Autowired     
    private FacultadDAO facultdadDAO;
    public FacultadBean() {
       fSelected=new Facultad();
       uSelected=new Universidad();
      // uSelected.setSiglaUniversidad("null");
       //facultadList=new ArrayList<Facultad>();
       //facultadList.clear();
    } 
    public void removeItem(Facultad f){
        fSelected=new Facultad();
        fSelected.setIdFacultad(f.getIdFacultad());
        
      //  uSelected.setNombre(u.getNombre());
      //  uSelected.setSiglaUniversidad(u.getSiglaUniversidad());
       
    }
    public void removeItem(){
        this.getFacultdadDAO().delete(fSelected);
        facultadList=(ArrayList<Facultad>) facultdadDAO.readFacultdad(uSelected.getSiglaUniversidad());
    }
    public void updateItem(){
        fSelected.setUniversidad(uSelected);
       this.getFacultdadDAO().update(fSelected);  
       facultadList=(ArrayList<Facultad>) facultdadDAO.readFacultdad(uSelected.getSiglaUniversidad());
    }
    public void updateItem(Facultad f){        
      fSelected=new Facultad();
      fSelected.setIdFacultad(f.getIdFacultad());
      fSelected.setNombre(f.getNombre());
      fSelected.setSiglaFacultad(f.getSiglaFacultad());  
      
      //JOptionPane.showMessageDialog(null, "joder "+f.getIdFacultad());
    }
    public void viewItem(Facultad f){
       fSelected.setNombre(f.getNombre());
       fSelected.setSiglaFacultad(f.getSiglaFacultad());
    }    
    public void createItem(){       
        fSelected=new Facultad();      
    }
    public void createItem(boolean op){       
        //Universidad p=universidadDAO.read(uSelected.getSiglaUniversidad());       
        //x.setFacultads(p.get);
        //fSelected.setIdFacultad(10L);
        //uSelected.getFacultads().add(fSelected);   
        //x.setNombre(uSelected.getNombre());
        //x.setSiglaUniversidad(uSelected.getSiglaUniversidad());
        //x.setFacultads(uSelected.getFacultads());
        //fSelected.setIdFacultad(10);
        //uSelected.getFacultads().add(fSelected);
        
        getfSelected().setUniversidad(uSelected);
        facultdadDAO.create(getfSelected());
        facultadList=(ArrayList<Facultad>) facultdadDAO.readFacultdad(uSelected.getSiglaUniversidad());
        //this.universidadList=(ArrayList<Universidad>) this.getUniversidadDAO().readAll();
        //universidadDAO.create(p);  
       // JOptionPane.showMessageDialog(null, "joder "+p.getFacultads().size());
    }
   
    /**
     * @return the universidadList
     */
    public void leerFacultad(String sigla){
        this.getFacultdadDAO().readFacultdad(sigla);
    }
    public void leerFacultad(ValueChangeEvent event){
        //JOptionPane.showMessageDialog(null, "joder "+event.getNewValue().toString());
        //this.getFacultdadDAO().readFacultdad(u);
    }
    
    public void handleChange(AjaxBehaviorEvent vce){  
        String sigla= (String) ((UIOutput) vce.getSource()).getValue();
        if (sigla.compareTo("null")==0) {
            facultadList.clear(); return;
        }        
        uSelected.setSiglaUniversidad(sigla);    
       // facultadList=(ArrayList<Facultad>) facultdadDAO.readFacultdad(uSelected.getSiglaUniversidad());
       //facultadList.clear();
       facultadList=(ArrayList<Facultad>) facultdadDAO.readFacultdad(uSelected.getSiglaUniversidad());       
      // FacesContext facesContext = FacesContext.getCurrentInstance(); 
      // CarreraBean b=(CarreraBean)facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "carreraBean");
      // b.getCarreraList().clear();
       //b.setCarreraList((ArrayList<Carrera>) b.getCarreraDAO().readCarrera(fSelected.getIdFacultad()));
        //b.getCarreraList().clear();
       //facultadList=(ArrayList<Facultad>) facultdadDAO.readFacultdad(uSelected.getSiglaUniversidad());
        
       //CarreraDAO c=b.getCarreraDAO();
       //             c.readCarrera(fSelected.getIdFacultad());
       // b.setCarreraList(b.getCarreraDAO.);
       //JOptionPane.showMessageDialog(null, "joder "+sigla);
        /*
        Iterator<Universidad> it=universidadList.iterator();
        while (it.hasNext()) {
            Universidad u=it.next();
            if(u.getSiglaUniversidad().compareTo(sigla)==0){
                uSelected=u;
                facultadList=new ArrayList(u.getFacultads());
                break;
            }
        }
        */
       //facultadList=(ArrayList<Facultad>) this.getFacultdadDAO().readFacultdad(sigla);
       // uSelected.setSiglaUniversidad(sigla);
        
       // uSelected=(Universidad) ((UIOutput) vce.getSource()).getValue();
       
    }

    public ArrayList<Universidad> getUniversidadList() {
       
        this.universidadList=(ArrayList<Universidad>) this.getUniversidadDAO().readAll();
       
        return universidadList;
    }
    

    /**
     * @param universidadList the universidadList to set
     */
    public void setUniversidadList(ArrayList<Universidad> universidadList) {
        this.universidadList = universidadList;
    }

    /**
     * @return the facultadList
     */
    public ArrayList<Facultad> getFacultadList() {
        //facultadList=(ArrayList<Facultad>) this.getFacultdadDAO().readAll();
        //facultadList=new ArrayList<Facultad>();
      //  if(uSelected.getSiglaUniversidad().compareTo("null")==0)
       //      facultadList.clear();
        return facultadList;
    }

    /**
     * @param facultadList the facultadList to set
     */
    public void setFacultadList(ArrayList<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    /**
     * @return the fSelected
     */
    public Facultad getfSelected() {
        return fSelected;
    }

    /**
     * @param fSelected the fSelected to set
     */
    public void setfSelected(Facultad fSelected) {
        this.fSelected = fSelected;
    }

    /**
     * @return the universidadDAO
     */
    public UniversidadDAO getUniversidadDAO() {
        
        return universidadDAO;
    }

    /**
     * @param universidadDAO the universidadDAO to set
     */
    public void setUniversidadDAO(UniversidadDAO universidadDAO) {
        this.universidadDAO = universidadDAO;
    }

    /**
     * @return the facultdadDAO
     */
    public FacultadDAO getFacultdadDAO() {
        return facultdadDAO;
    }

    /**
     * @param facultdadDAO the facultdadDAO to set
     */
    public void setFacultdadDAO(FacultadDAO facultdadDAO) {
        this.facultdadDAO = facultdadDAO;
    }

    /**
     * @return the uSelected
     */
    public Universidad getuSelected() {
        return uSelected;
    }

    /**
     * @param uSelected the uSelected to set
     */
    public void setuSelected(Universidad uSelected) {
        this.uSelected = uSelected;
    }
    
}
