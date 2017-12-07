/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_interfaces;

import java.io.Serializable;
import java.util.List;



public interface GenericDAOInterface <T, ID extends Serializable> {

    /** Persist the newInstance object into database
     * @param x
     * @return  */
    void create(T x);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     * @param id
     * @return 
     */
    T read(ID id);

    /** Save changes made to a persistent object.
     * @param x */
    void update(T x);

    /** Remove an object from persistent storage in the database
     * @param x */
    void delete(T x); 
    List<T> readAll();
} 	



