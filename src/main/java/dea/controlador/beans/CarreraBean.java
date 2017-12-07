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
import java.util.List;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Doppler
 */
@Component
@Scope("session")
public class CarreraBean implements Serializable{  
    
    private FacultadBean facultadBean;
    private List<Universidad> universidadList;
    private List<Facultad> facultadList; 
    private List<Carrera> carreraList;
    private String uSelected;
    private long fSelected;
    private Carrera cSelected;
    
    @Autowired 
    private UniversidadDAO universidadDAO;
    @Autowired     
    private CarreraDAO carreraDAO;
    @Autowired     
    private FacultadDAO facultdadDAO;
    
    public CarreraBean() {       
       cSelected=new Carrera();  
    } 
    public void removeItem(Carrera c){
      cSelected=new Carrera();
      cSelected.setIdCarrera(c.getIdCarrera());
      cSelected.setNombre(c.getNombre());
      cSelected.setSiglaCarrera(c.getSiglaCarrera());       
    }
    public void removeItem(){
       Facultad f=new Facultad(fSelected);
       cSelected.setFacultad(f); 
       this.getCarreraDAO().delete(cSelected);
       carreraList=(ArrayList<Carrera>) carreraDAO.readCarrera(fSelected);
    }
    public void updateItem(){
       Facultad f=new Facultad(fSelected);
       cSelected.setFacultad(f);
       this.getCarreraDAO().update(cSelected);     
    }
    public void updateItem(Carrera f){        
      cSelected=new Carrera();
      cSelected.setIdCarrera(f.getIdCarrera());
      cSelected.setSiglaCarrera(f.getSiglaCarrera());
      cSelected.setNombre(f.getNombre());    
    }
    public void viewItem(Carrera c){
      cSelected.setNombre(c.getNombre());
      cSelected.setSiglaCarrera(c.getSiglaCarrera());
    }    
    
    public void createItem(){       
       cSelected=new Carrera();      
    }
    public void createItem(boolean op){        
        Facultad f=new Facultad(fSelected);
        cSelected.setFacultad(f);
        carreraDAO.create(cSelected);       
    }

    public void changeUniversidad(AjaxBehaviorEvent vce){      
        this.uSelected= (String) ((UIOutput) vce.getSource()).getValue();        
        this.fSelected=0;
    }
    public void changeFacultad(AjaxBehaviorEvent vce){      
        fSelected= (Long) ((UIOutput) vce.getSource()).getValue();        
    }

    /**
     * @return the carreraList
     */
    public List<Carrera> getCarreraList() {        
        return carreraDAO.readCarrera(fSelected);
    }

    /**
     * @param carreraList the carreraList to set
     */
    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    /**
     * @return the facultadBean
     */
    public FacultadBean getFacultadBean() {
        return facultadBean;
    }

    /**
     * @param facultadBean the facultadBean to set
     */
    public void setFacultadBean(FacultadBean facultadBean) {
        this.facultadBean = facultadBean;
    }

    /**
     * @return the carreraDAO
     */
    public CarreraDAO getCarreraDAO() {
        return carreraDAO;
    }

    /**
     * @param carreraDAO the carreraDAO to set
     */
    public void setCarreraDAO(CarreraDAO carreraDAO) {
        this.carreraDAO = carreraDAO;
    }

    /**
     * @return the cSelected
     */
    public Carrera getcSelected() {
        return cSelected;
    }

    /**
     * @param cSelected the cSelected to set
     */
    public void setcSelected(Carrera cSelected) {
        this.cSelected = cSelected;
    }

    /**
     * @return the universidadList
     */
    public List<Universidad> getUniversidadList(){
        return universidadDAO.readAll();
    }

    /**
     * @param universidadList the universidadList to set
     */
    public void setUniversidadList(List<Universidad> universidadList) {
        this.universidadList = universidadList;
    }

    /**
     * @return the facultadList
     */
    public List<Facultad> getFacultadList() {
        return facultdadDAO.readFacultdad(uSelected);
    }

    /**
     * @param facultadList the facultadList to set
     */
    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
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
     * @return the fSelected
     */
    public long getfSelected() {
        return fSelected;
    }

    /**
     * @param fSelected the fSelected to set
     */
    public void setfSelected(long fSelected) {
        this.fSelected = fSelected;
    }

    /**
     * @return the uSelected
     */
    public String getuSelected() {
        return uSelected;
    }

    /**
     * @param uSelected the uSelected to set
     */
    public void setuSelected(String uSelected) {
        this.uSelected = uSelected;
    }

}

