/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import dea.controlador.dao_interfaces.DocenteDAOInterface;
import dea.modelo.Docente;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Doppler
 */
@Repository
public class DocenteDAO extends GenericDAO<Docente,String> implements DocenteDAOInterface,Serializable{
    
}
