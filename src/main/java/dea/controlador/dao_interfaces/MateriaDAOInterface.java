/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_interfaces;

import dea.modelo.Materia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Doppler
 */
public interface MateriaDAOInterface extends GenericDAOInterface<Materia, Long> {
    public List<Materia> readMateria(Long id);
} 
    
