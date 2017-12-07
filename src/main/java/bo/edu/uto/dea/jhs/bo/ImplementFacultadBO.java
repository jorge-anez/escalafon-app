/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IFacultadDAO;
import bo.edu.uto.dea.jhs.persistence.Facultad;
import bo.edu.uto.dea.jhs.persistence.Universidad;
import bo.edu.uto.dea.jsf.bean.FacultadBean;
import bo.edu.uto.dea.jsf.bean.UniversidadBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementFacultadBO implements IFacultadBO ,Serializable{

    private IFacultadDAO implementFacultadDAO;

    /**
     * @return the implementFacultadDAO
     */
    public IFacultadDAO getImplementFacultadDAO() {
        return implementFacultadDAO;
    }

    /**
     * @param implementFacultadDAO the implementFacultadDAO to set
     */
    public void setImplementFacultadDAO(IFacultadDAO implementFacultadDAO) {
        this.implementFacultadDAO = implementFacultadDAO;
    }

    @Override
    public List<FacultadBean> obtenerFacultads(UniversidadBean obj) {
        Universidad universidad = new Universidad();
        universidad.setSiglaUniversidad(obj.getSiglaUniversidad());

        List<FacultadBean> facultadBeans = new ArrayList<FacultadBean>();
        for (Facultad object : implementFacultadDAO.obtenerFacultads(universidad)) {
            FacultadBean facultadBean = new FacultadBean();
            facultadBean.setIdFacultad(object.getIdFacultad());
            facultadBean.setSiglaFacultad(object.getSiglaFacultad());
            facultadBean.setNombre(object.getNombre());
            facultadBean.setSiglaUniversidad(object.getUniversidad().getSiglaUniversidad());
            facultadBean.setEstado(object.getEstado());
            facultadBeans.add(facultadBean);
        }
        return facultadBeans;
    }

    @Override
    public Long crearFacultad(FacultadBean obj) {
        Long retorno = new Long(0);
        Facultad facultad=new Facultad();
        facultad.setSiglaFacultad(obj.getSiglaFacultad());
        facultad.setNombre(obj.getNombre());
        Universidad universidad = new Universidad();
        universidad.setSiglaUniversidad(obj.getSiglaUniversidad());
        facultad.setUniversidad( universidad );
        facultad.setEstado("ACTIVO");
        
        retorno = implementFacultadDAO.crearFacultad(facultad);
        return retorno;
    }

    @Override
    public Long modificarFacultad(FacultadBean obj) {
        Long retorno=new Long(0);
        Facultad facultad=new Facultad();
        facultad.setIdFacultad(obj.getIdFacultad());
        facultad.setSiglaFacultad(obj.getSiglaFacultad());
        facultad.setNombre(obj.getNombre());
        Universidad universidad=new Universidad();
        universidad.setSiglaUniversidad(obj.getSiglaUniversidad());
        facultad.setUniversidad(universidad);
        facultad.setEstado(obj.getEstado());
        
        retorno = implementFacultadDAO.modificarFacultad(facultad);
        return retorno;
    }
}
