/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_interfaces;

import dea.modelo.AdministradorCoordinador;
import java.util.List;

/**
 *
 * @author Doppler
 */
public interface CoorDAOAInerface extends GenericDAOInterface<AdministradorCoordinador, String> {
    public List<AdministradorCoordinador> readCoor(String ci);
}