/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.controlador.dao_interfaces.DirectorDeaDAOInterface;
import dea.modelo.AdministradorDirector;
import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Doppler
 */
@Repository
public class DirectorDeaDAO extends GenericDAO<AdministradorDirector,String> implements DirectorDeaDAOInterface,Serializable{
     public DirectorDeaDAO(){
       super();      
    }

    @Override
    public List<AdministradorDirector> readDirector(String ci) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
