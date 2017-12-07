/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;


import dea.controlador.dao_classes.UniversidadDAO;
import dea.modelo.Universidad;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Doppler
 */
@Component
@Scope("session")
public class UniversidadBean implements Serializable{
     private String siglaUniversidad;
     private String nombre;
     private Universidad uSelected;
     private boolean editVisible;
     private ArrayList<Universidad> universidadList;
     
    @Autowired     
    private UniversidadDAO universidadDAO;
    public UniversidadBean() {
       uSelected=new Universidad();
       
    }

    
 
    public void removeItem(Universidad u){
       // uSelected=new Universidad();
        uSelected.setNombre(u.getNombre());
        uSelected.setSiglaUniversidad(u.getSiglaUniversidad());
        //RequestContext.getCurrentInstance().execute("PF('_dlgEdit').show()");
       // this.getUniversidadDAO().delete(uSelected);
       // JOptionPane.showMessageDialog(null, "parece que funciona "+u.getNombre());
    }
    public void removeItem(){
        this.getUniversidadDAO().delete(uSelected);
    }
    public void updateItem(){
        this.getUniversidadDAO().update(uSelected);
       // JOptionPane.showMessageDialog(null, "parece que funciona ");
       //RequestContext.getCurrentInstance().
      
    }
    public void updateItem(Universidad u){
        uSelected=new Universidad();
        uSelected.setNombre(u.getNombre());
        uSelected.setSiglaUniversidad(u.getSiglaUniversidad());      
    }
    public void viewItem(Universidad u){
        uSelected.setNombre(u.getNombre());
        uSelected.setSiglaUniversidad(u.getSiglaUniversidad());
    }    
    public void createItem(){
        //uSelected=new Universidad();
        uSelected.setNombre("");
        uSelected.setSiglaUniversidad("");
    }
    public void createItem(boolean op){
        Universidad u=new Universidad(uSelected.getSiglaUniversidad(), uSelected.getNombre());
        this.universidadDAO.create(u);
        uSelected.setNombre("");
        uSelected.setSiglaUniversidad("");
    }
    
    /**
     * @return the siglaUniversidad
     */
    public String getSiglaUniversidad() {
        return siglaUniversidad;
    }

    /**
     * @param siglaUniversidad the siglaUniversidad to set
     */
    public void setSiglaUniversidad(String siglaUniversidad) {
        this.siglaUniversidad = siglaUniversidad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
   

    /**
     * @return the universidadDAO
     */
  

    /**
     * @return the universidadList
     */
    public ArrayList<Universidad> getUniversidadList(){
        
        //JOptionPane.showMessageDialog(null, "parece que funciona ");
       
       
       
        universidadList=(ArrayList<Universidad>) getUniversidadDAO().readAll();
       
       /* String cad="";
        for (Universidad e: universidad) {
           cad+=e.getNombre();
            
        }
        JOptionPane.showMessageDialog(null, cad);
        */
       
        return universidadList;
    }

    /**
     * @param universidadList the universidadList to set
     */
    public void setUniversidadList(ArrayList<Universidad> universidadList) {
        this.universidadList = universidadList;
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

    /**
     * @return the editVisible
     */
    public boolean isEditVisible() {
        return editVisible;
    }

    /**
     * @param editVisible the editVisible to set
     */
    public void setEditVisible(boolean editVisible) {
        this.editVisible = editVisible;
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
}
