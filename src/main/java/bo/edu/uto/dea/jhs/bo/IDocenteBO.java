/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.DocenteBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IDocenteBO {
    List<DocenteBean> obtenerDocentes();
    DocenteBean obtenerDocenteByCi(DocenteBean obj);
    
    Long modificarDocente(DocenteBean obj);
    Long crearDocente(DocenteBean obj);
}
