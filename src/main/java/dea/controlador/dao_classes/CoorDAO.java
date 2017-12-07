/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;


import dea.controlador.dao_interfaces.CoorDAOAInerface;
import dea.modelo.AdministradorCoordinador;
import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Doppler
 */
@Repository
public class CoorDAO extends GenericDAO<AdministradorCoordinador,String> implements CoorDAOAInerface,Serializable{
     public CoorDAO(){
       super();      
    }

    @Override
    public List<AdministradorCoordinador> readCoor(String ci) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
