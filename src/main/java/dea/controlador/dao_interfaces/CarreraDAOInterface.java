/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_interfaces;

import dea.modelo.Carrera;
import java.util.List;

/**
 *
 * @author Doppler
 */
public interface CarreraDAOInterface extends GenericDAOInterface<Carrera, Long> {
    public List<Carrera> readCarrera(Long idFacultad);
}

