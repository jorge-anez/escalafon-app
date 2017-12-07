/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_interfaces;

import dea.modelo.Facultad;
import dea.modelo.Universidad;
import java.util.List;

/**
 *
 * @author Doppler
 */
public interface FacultadDAOInterface extends GenericDAOInterface<Facultad, Long> {
    public List<Facultad> readFacultdad(String sigla);
}

