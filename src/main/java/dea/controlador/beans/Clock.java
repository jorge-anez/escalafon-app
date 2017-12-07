/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import java.io.Serializable;
import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Doppler
 */
@Component
@Scope("request")
public class Clock implements Serializable {  
    private static final long serialVersionUID = -5427668306657486626L;      
    private final Date now = new Date();    
    public Date getNow() {  
        return now;  
    }  
  
}
