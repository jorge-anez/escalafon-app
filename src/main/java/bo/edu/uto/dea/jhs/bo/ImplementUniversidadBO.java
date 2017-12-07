/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IUniversidadDAO;
import bo.edu.uto.dea.jhs.persistence.Universidad;
import bo.edu.uto.dea.jsf.bean.UniversidadBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementUniversidadBO implements IUniversidadBO,Serializable{
    private IUniversidadDAO implementUniversidadDAO;
    
    @Override
    public List<UniversidadBean> obtenerUniversidads() {
        List<UniversidadBean> universidadBeans=new ArrayList<UniversidadBean>();
        for( Universidad obj: implementUniversidadDAO.obtenerUniversidads() ){
            UniversidadBean universidadBean=new UniversidadBean();
            universidadBean.setSiglaUniversidad( obj.getSiglaUniversidad() );
            universidadBean.setNombre(obj.getNombre());
            universidadBean.setEstado(obj.getEstado());
            universidadBeans.add(universidadBean);
        }
        return universidadBeans;
    }

    /**
     * @return the implementUniversidadDAO
     */
    public IUniversidadDAO getImplementUniversidadDAO() {
        return implementUniversidadDAO;
    }

    /**
     * @param implementUniversidadDAO the implementUniversidadDAO to set
     */
    public void setImplementUniversidadDAO(IUniversidadDAO implementUniversidadDAO) {
        this.implementUniversidadDAO = implementUniversidadDAO;
    }

    @Override
    public Long crearUniversidad(UniversidadBean obj) {
        Long retorno=new Long(0);
        Universidad universidad=new Universidad();
        universidad.setSiglaUniversidad(obj.getSiglaUniversidad());
        universidad.setNombre(obj.getNombre());
        universidad.setEstado("ACTIVO");
        retorno = implementUniversidadDAO.crearUniversidad(universidad);
        return retorno;
    }

    @Override
    public Long modificarUniversidad(UniversidadBean obj) {
        Long retorno=new Long(0);
        Universidad universidad=new Universidad();
        universidad.setSiglaUniversidad(obj.getSiglaUniversidad());
        universidad.setNombre(obj.getNombre());
        universidad.setEstado(obj.getEstado());
        retorno = implementUniversidadDAO.modificarUniversidad(universidad);
        return retorno;
    }
    
}
