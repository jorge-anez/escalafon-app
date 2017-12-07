/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IMateriaDAO;
import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Facultad;
import bo.edu.uto.dea.jhs.persistence.Materia;
import bo.edu.uto.dea.jhs.persistence.RequiereMateria;
import bo.edu.uto.dea.jhs.persistence.Universidad;
import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.MateriaBean;
import bo.edu.uto.dea.jsf.bean.RequiereMateriaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementMateriaBO implements IMateriaBO,Serializable{
    private IMateriaDAO implementMateriaDAO;

    /**
     * @return the implementMateriaDAO
     */
    public IMateriaDAO getImplementMateriaDAO() {
        return implementMateriaDAO;
    }

    /**
     * @param implementMateriaDAO the implementMateriaDAO to set
     */
    public void setImplementMateriaDAO(IMateriaDAO implementMateriaDAO) {
        this.implementMateriaDAO = implementMateriaDAO;
    }

    @Override
    public List<MateriaBean> obtenerMaterias(CarreraBean obj) {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(obj.getIdCarrera());

        List<MateriaBean> materiaBeans = new ArrayList<MateriaBean>();
        for (Materia object : implementMateriaDAO.obtenerMaterias(carrera) ) {
            MateriaBean materiaBean = new MateriaBean();
            materiaBean.setIdMateria(object.getIdMateria());
            materiaBean.setSiglaMateria(object.getSiglaMateria());
            materiaBean.setNombre(object.getNombre());
            materiaBean.setIdCarrera(object.getCarrera().getIdCarrera());
            materiaBean.setEstado(object.getEstado());
            materiaBeans.add(materiaBean);
        }
        return materiaBeans;
    }

    @Override
    public Long crearMateria(MateriaBean obj) {
        Long retorno = new Long(0);
        Materia materia=new Materia();
        materia.setSiglaMateria(obj.getSiglaMateria());
        materia.setNombre(obj.getNombre());
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(obj.getIdCarrera() );
        materia.setCarrera( carrera );
        materia.setEstado("ACTIVO");
        
        retorno = implementMateriaDAO.crearMateria(materia);
        return retorno;
    }

    @Override
    public Long modificarMateria(MateriaBean obj) {
        Long retorno=new Long(0);
        Materia materia=new Materia();
        materia.setIdMateria(obj.getIdMateria());
        materia.setSiglaMateria(obj.getSiglaMateria());
        materia.setNombre(obj.getNombre());
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(obj.getIdCarrera() );
        materia.setCarrera( carrera );
        materia.setEstado("ACTIVO");
        
        retorno = implementMateriaDAO.modificarMateria(materia);
        return retorno;
    }

    @Override
    public List<RequiereMateriaBean> obtenerRequiereMaterias(MateriaBean obj) {
        Materia materia = new Materia();
        materia.setIdMateria(obj.getIdMateria());

        List<RequiereMateriaBean> requiereMateriaBeans = new ArrayList<RequiereMateriaBean>();        
        for (RequiereMateria object : implementMateriaDAO.obtenerRequiereMaterias(materia) ) {
            RequiereMateriaBean requiereMateriaBean = new RequiereMateriaBean();
            requiereMateriaBean.setIdRequiereMateria( object.getIdRequiereMateria() );
            requiereMateriaBean.setIdMateriaByIdMateria( object.getMateriaByIdMateria().getIdMateria() );
            requiereMateriaBean.setIdMateriaByIdMateriaRequisito( object.getMateriaByIdMateriaRequisito().getIdMateria() );
            
            Materia m=new Materia();
            m.setIdMateria( object.getMateriaByIdMateriaRequisito().getIdMateria() );
            m=implementMateriaDAO.obtenerMateriaByIdMateria(m);
            if( m!=null ){
                requiereMateriaBean.setMateriaMateriaRequisito( m.getNombre() );
                Carrera c=new Carrera();
                c.setIdCarrera( m.getCarrera().getIdCarrera() );
                c=implementMateriaDAO.obtenerCarreraByIdCarrera(c);
                if( c!=null ){
                    requiereMateriaBean.setCarreraMateriaRequisito( c.getNombre() );
                    Facultad f=new Facultad();
                    f.setIdFacultad( c.getFacultad().getIdFacultad() );
                    f=implementMateriaDAO.obtenerFacultadByIdFacultad(f);
                    if( f!=null ){
                        requiereMateriaBean.setFacultadMateriaRequisito( f.getNombre() );
                        Universidad u=new Universidad();
                        u.setSiglaUniversidad( f.getUniversidad().getSiglaUniversidad() );
                        u=implementMateriaDAO.obtenerUniversidadBySiglaUniversidad(u);
                        if( u!=null ){
                            requiereMateriaBean.setUniversidadMateriaRequisito( u.getNombre() );
                        }
                        else{
                            requiereMateriaBean.setUniversidadMateriaRequisito( "null" );
                        }
                    }
                    else{
                        requiereMateriaBean.setFacultadMateriaRequisito( "null" );
                        requiereMateriaBean.setUniversidadMateriaRequisito( "null" );
                    }
                }
                else{
                    requiereMateriaBean.setCarreraMateriaRequisito( "null" );
                    requiereMateriaBean.setFacultadMateriaRequisito( "null" );
                    requiereMateriaBean.setUniversidadMateriaRequisito( "null" );
                }
            }
            else{
                requiereMateriaBean.setMateriaMateriaRequisito( "null" );
                requiereMateriaBean.setCarreraMateriaRequisito( "null" );
                requiereMateriaBean.setFacultadMateriaRequisito( "null" );
                requiereMateriaBean.setUniversidadMateriaRequisito( "null" );
            }
            
            requiereMateriaBeans.add(requiereMateriaBean);
        }
        return requiereMateriaBeans;
    }

    @Override
    public Long eliminarRequiereMateria(RequiereMateriaBean obj) {
        Long retorno=new Long(0);
        RequiereMateria requiereMateria=new RequiereMateria();
        requiereMateria.setIdRequiereMateria( obj.getIdRequiereMateria() );
        
        retorno = implementMateriaDAO.eliminarRequiereMateria(requiereMateria);
        return retorno;
    }

    @Override
    public Long crearRequiereMateria(RequiereMateriaBean obj) {
        Long retorno=new Long(0);
        RequiereMateria requiereMateria=new RequiereMateria();
        
        Materia materia=new Materia();
        materia.setIdMateria( obj.getIdMateriaByIdMateria() );
        requiereMateria.setMateriaByIdMateria(materia);
        
        Materia materiaRequisito=new Materia();
        materiaRequisito.setIdMateria( obj.getIdMateriaByIdMateriaRequisito() );
        requiereMateria.setMateriaByIdMateria(materiaRequisito);
        
        requiereMateria.setMateriaByIdMateria(materia);
        requiereMateria.setMateriaByIdMateriaRequisito(materiaRequisito);
        
        retorno = implementMateriaDAO.crearRequiereMateria(requiereMateria);
        return retorno;
    }
    
}
