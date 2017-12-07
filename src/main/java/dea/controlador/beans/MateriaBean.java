/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.CarreraDAO;
import dea.controlador.dao_classes.FacultadDAO;
import dea.controlador.dao_classes.MateriaDAO;
import dea.controlador.dao_classes.UniversidadDAO;
import dea.modelo.Carrera;
import dea.modelo.Facultad;
import dea.modelo.Materia;
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
public class MateriaBean implements Serializable{  
    
   
    private List<Universidad> universidadList;
    private List<Facultad> facultadList; 
    private List<Carrera> carreraList;
    private List<Materia> materiaList;
    private Materia mSelected;
    private String uSelected;  
    private long fSelected,cSelected;
    
    @Autowired 
    private UniversidadDAO universidadDAO;
    @Autowired     
    private CarreraDAO carreraDAO;
    @Autowired     
    private FacultadDAO facultdadDAO;
    @Autowired     
    private MateriaDAO materiaDAO;
    
    
    public MateriaBean() {
      
    } 
    public void removeItem(Materia m){   
      mSelected=new Materia();
      mSelected.setIdMateria(m.getIdMateria());
      mSelected.setNombre(m.getNombre());
      mSelected.setSiglaMateria(m.getSiglaMateria());
    }
    public void removeItem(){
        Carrera c=new Carrera();
        c.setIdCarrera(this.cSelected);
        mSelected.setCarrera(c);
        this.getMateriaDAO().delete(mSelected);        
    }
    public void updateItem(){
        Carrera c=new Carrera();
        c.setIdCarrera(this.cSelected);
        mSelected.setCarrera(c);
        this.getMateriaDAO().update(mSelected);        
    }
    public void updateItem(Materia f){      
      mSelected=new Materia();
      mSelected.setIdMateria(f.getIdMateria());
      mSelected.setSiglaMateria(f.getSiglaMateria());
      mSelected.setNombre(f.getNombre());        
    }
    public void viewItem(Materia c){
      mSelected=new Materia();
      mSelected.setNombre(c.getNombre());
      mSelected.setSiglaMateria(c.getSiglaMateria());
    }    
    public void createItem(){       
        setmSelected(new Materia());
    }
    public void createItem(boolean op){      
        Carrera c=new Carrera();
        c.setIdCarrera(this.cSelected);
        mSelected.setCarrera(c);
        getMateriaDAO().create(this.getmSelected());       
    }    
    public void changeUniversidad(AjaxBehaviorEvent vce){      
        this.uSelected = (String) ((UIOutput) vce.getSource()).getValue();
        this.fSelected=this.cSelected=0;
    }
    public void changeFacultad(AjaxBehaviorEvent vce){      
        this.fSelected= (Long) ((UIOutput) vce.getSource()).getValue();
        this.cSelected=0;
    }
    public void changeCarrera(AjaxBehaviorEvent vce){      
        this.cSelected= (Long) ((UIOutput) vce.getSource()).getValue();        
    }
    /**
     * @return the materiaList
     */
    public List<Materia> getMateriaList(){        
        return  materiaDAO.readMateria(this.cSelected);
    }

    /**
     * @param materiaList the materiaList to set
     */
    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    /**
     * @return the mSelected
     */
    public Materia getmSelected() {
        return mSelected;
    }

    /**
     * @param mSelected the mSelected to set
     */
    public void setmSelected(Materia mSelected) {
        this.mSelected = mSelected;
    }

   

    /**
     * @return the materiaDAO
     */
    public MateriaDAO getMateriaDAO() {
        return materiaDAO;
    }

    /**
     * @param materiaDAO the materiaDAO to set
     */
    public void setMateriaDAO(MateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }

    /**
     * @return the universidadList
     */
    public List<Universidad> getUniversidadList() {        
        return universidadDAO.readAll();
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
     * @return the carreraList
     */
    public List<Carrera> getCarreraList(){
        return carreraDAO.readCarrera(this.fSelected);
    }

    /**
     * @param carreraList the carreraList to set
     */
    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
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
     * @return the cSelected
     */
    public long getcSelected() {
        return cSelected;
    }

    /**
     * @param cSelected the cSelected to set
     */
    public void setcSelected(long cSelected) {
        this.cSelected = cSelected;
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

