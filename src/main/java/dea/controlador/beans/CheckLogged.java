/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;


import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.swing.JOptionPane;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 * @author Doppler
 */
@Component
@Scope("request")
public class CheckLogged implements Serializable{    
    public CheckLogged(){}
    

}
