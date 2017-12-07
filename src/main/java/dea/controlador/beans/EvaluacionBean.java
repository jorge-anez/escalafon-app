/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import dea.controlador.dao_classes.DocenteRegistroMateriaDAO;
import dea.controlador.dao_classes.EvaluacionDAO;
import dea.modelo.DocenteRegistroMateria;
import dea.modelo.Evaluacion;
import dea.modelo.Materia;
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
public class EvaluacionBean implements Serializable{
    @Autowired
    private EvaluacionDAO evaluacionDAO;
    @Autowired
    private DocenteRegistroMateriaDAO docenteRegistroMateriaDAO;
    private List<Evaluacion> evaluacionList;
    private List<DocenteRegistroMateria> registroList;
    private String gestion,nota;
    
    public void evaluar(DocenteRegistroMateria r){
        //JOptionPane.showMessageDialog(null, "maldicion"+e.getGestion());
        Evaluacion eval=new Evaluacion();
        eval.setDocenteRegistroMateria(r);
        eval.setNota(Double.parseDouble(r.getGestion()));
        evaluacionDAO.create(eval);
        
        List<Object[]> result=evaluacionDAO.getRegistro(this.gestion);
        if(evaluacionList==null)
            evaluacionList=new ArrayList<Evaluacion>();
        else evaluacionList.clear();
        for (Object[] e : result) {
            
            eval=(Evaluacion)e[0];         
            r=(DocenteRegistroMateria)e[1];
            Materia m=(Materia) e[2];
            r.setMateria(m);
            eval.setDocenteRegistroMateria(r);            
            evaluacionList.add(eval);
        }
    }
    public void prepareRegistro(){
        
    }
    public void changeGestion(AjaxBehaviorEvent vce){      
        String str= (String) ((UIOutput) vce.getSource()).getValue();
        List<Object[]> result=evaluacionDAO.getRegistro(str);
        if(evaluacionList==null)
            evaluacionList=new ArrayList<Evaluacion>();
        else evaluacionList.clear();
        for (Object[] e : result) {
            
            Evaluacion eval=(Evaluacion)e[0];         
            DocenteRegistroMateria r=(DocenteRegistroMateria)e[1];
            Materia m=(Materia) e[2];
            r.setMateria(m);
            eval.setDocenteRegistroMateria(r);            
            evaluacionList.add(eval);
        } 
        
        result= docenteRegistroMateriaDAO.getRegistro(str);
        if(registroList==null)
            registroList=new ArrayList<DocenteRegistroMateria>();
        else registroList.clear();
        for (Object[] e : result) {
            DocenteRegistroMateria r=(DocenteRegistroMateria)e[0];
            r.setGestion("");
            Materia m=(Materia) e[1];
            r.setMateria(m);           
            registroList.add(r);
        }
        this.setGestion(str);
        
    }
    /**
     * @return the evaluacionDAO
     */
    public EvaluacionDAO getEvaluacionDAO() {
        return evaluacionDAO;
    }

    /**
     * @param evaluacionDAO the evaluacionDAO to set
     */
    public void setEvaluacionDAO(EvaluacionDAO evaluacionDAO) {
        this.evaluacionDAO = evaluacionDAO;
    }

    /**
     * @return the evaluacionList
     */
    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    /**
     * @param evaluacionList the evaluacionList to set
     */
    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    /**
     * @return the registroList
     */
    public List<DocenteRegistroMateria> getRegistroList() {
        return registroList;
    }

    /**
     * @param registroList the registroList to set
     */
    public void setRegistroList(List<DocenteRegistroMateria> registroList) {
        this.registroList = registroList;
    }

    /**
     * @return the docenteRegistroMateriaDAO
     */
    public DocenteRegistroMateriaDAO getDocenteRegistroMateriaDAO() {
        return docenteRegistroMateriaDAO;
    }

    /**
     * @param docenteRegistroMateriaDAO the docenteRegistroMateriaDAO to set
     */
    public void setDocenteRegistroMateriaDAO(DocenteRegistroMateriaDAO docenteRegistroMateriaDAO) {
        this.docenteRegistroMateriaDAO = docenteRegistroMateriaDAO;
    }

    /**
     * @return the gestion
     */
    public String getGestion() {
        return gestion;
    }

    /**
     * @param gestion the gestion to set
     */
    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    /**
     * @return the nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

   
}
