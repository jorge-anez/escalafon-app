/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_interfaces;
import dea.controlador.dao_interfaces.GenericDAOInterface;
import dea.modelo.Persona;
import java.io.Serializable;
/**
 *
 * @author Doppler
 */
public interface PersonaDAOInterface extends GenericDAOInterface<Persona, String>{
    public Persona findByUsername(String username);
    public Object[] findDirDEA();
    public Object[] findCoorDEA();
}
