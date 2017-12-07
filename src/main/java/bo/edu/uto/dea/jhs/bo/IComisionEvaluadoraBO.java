/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.ComisionEvaluadoraBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.MiembroComisionEvaluadoraBean;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public interface IComisionEvaluadoraBO {
    List<ComisionEvaluadoraBean> obtenerComisionEvaluadoras(CarreraBean obj);
    //DirectorCarreraBean obtenerDirectorCarreraByCi(DirectorCarreraBean obj);
    
    Long modificarComisionEvaluadora(ComisionEvaluadoraBean obj);
    Long crearComisionEvaluadora(ComisionEvaluadoraBean obj);
    
    List<MiembroComisionEvaluadoraBean> obtenerMiembroComisionEvaluadoras(ComisionEvaluadoraBean obj);
    Long modificarMiembroComisionEvaluadora(MiembroComisionEvaluadoraBean obj);
    Long crearMiembroComisionEvaluadora(MiembroComisionEvaluadoraBean obj);
    
    DocenteBean obtenerDocenteByCi(DocenteBean obj);
}
