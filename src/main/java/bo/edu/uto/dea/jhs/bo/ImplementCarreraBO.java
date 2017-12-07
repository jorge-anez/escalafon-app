/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.ICarreraDAO;
import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Facultad;
import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.FacultadBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementCarreraBO implements ICarreraBO,Serializable{
    private ICarreraDAO implementCarreraDAO;

    @Override
    public List<CarreraBean> obtenerCarreras(FacultadBean obj) {
        Facultad facultad = new Facultad();
        facultad.setIdFacultad(obj.getIdFacultad());

        List<CarreraBean> carreraBeans = new ArrayList<CarreraBean>();
        for (Carrera object : implementCarreraDAO.obtenerCarreras(facultad)) {
            CarreraBean carreraBean = new CarreraBean();
            carreraBean.setIdCarrera(object.getIdCarrera());
            carreraBean.setSiglaCarrera(object.getSiglaCarrera());
            carreraBean.setNombre(object.getNombre());
            carreraBean.setIdFacultad(object.getFacultad().getIdFacultad());
            carreraBean.setEstado(object.getEstado());
            carreraBeans.add(carreraBean);
        }
        return carreraBeans;
    }

    @Override
    public Long crearCarrera(CarreraBean obj) {
        Long retorno = new Long(0);
        Carrera carrera=new Carrera();
        carrera.setSiglaCarrera(obj.getSiglaCarrera());
        carrera.setNombre(obj.getNombre());
        Facultad facultad = new Facultad();
        facultad.setIdFacultad( obj.getIdFacultad() );
        carrera.setFacultad(facultad );
        carrera.setEstado("ACTIVO");
        
        retorno = implementCarreraDAO.crearCarrera(carrera);
        return retorno;
    }

    @Override
    public Long modificarCarrera(CarreraBean obj) {
        Long retorno=new Long(0);
        Carrera carrera=new Carrera();
        carrera.setIdCarrera(obj.getIdCarrera());
        carrera.setSiglaCarrera(obj.getSiglaCarrera());
        carrera.setNombre(obj.getNombre());
        Facultad facultad = new Facultad();
        facultad.setIdFacultad( obj.getIdFacultad() );
        carrera.setFacultad(facultad );
        carrera.setEstado("ACTIVO");
        
        retorno = implementCarreraDAO.modificarCarrera(carrera);
        return retorno;
    }

    /**
     * @return the implementCarreraDAO
     */
    public ICarreraDAO getImplementCarreraDAO() {
        return implementCarreraDAO;
    }

    /**
     * @param implementCarreraDAO the implementCarreraDAO to set
     */
    public void setImplementCarreraDAO(ICarreraDAO implementCarreraDAO) {
        this.implementCarreraDAO = implementCarreraDAO;
    }
    
    
    
}
