/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IComisionEvaluadoraDAO;
import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.ComisionEvaluadora;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.Facultad;
import bo.edu.uto.dea.jhs.persistence.MiembroComisionEvaluadora;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.Universidad;
import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.ComisionEvaluadoraBean;
import bo.edu.uto.dea.jsf.bean.DirectorCarreraBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.MiembroComisionEvaluadoraBean;
import bo.edu.uto.dea.jsf.bean.PersonaBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementComisionEvaluadoraBO implements IComisionEvaluadoraBO, Serializable {

    private IComisionEvaluadoraDAO implementComisionEvaluadoraDAO;

    @Override
    public List<ComisionEvaluadoraBean> obtenerComisionEvaluadoras( CarreraBean obj ) {
        List<ComisionEvaluadoraBean> comisionEvaluadoraBeans= new ArrayList<ComisionEvaluadoraBean>();
        Carrera carrera=new Carrera();
        carrera.setIdCarrera( obj.getIdCarrera() );
        
        for (ComisionEvaluadoraBean object : getImplementComisionEvaluadoraDAO().obtenerComisionEvaluadoras(carrera) ) {
            ComisionEvaluadoraBean comisionEvaluadoraBean = new ComisionEvaluadoraBean();
            
            comisionEvaluadoraBean.setIdComisionEvaluadora(object.getIdComisionEvaluadora());
            comisionEvaluadoraBean.setIdComisionEvaluadoraEvaluar(object.getIdComisionEvaluadoraEvaluar());
            
            if( object.getIdComisionEvaluadoraEvaluar() !=-1 ){
                ComisionEvaluadoraBean comisionEvaluadoraBean1=new ComisionEvaluadoraBean();
                ComisionEvaluadora comisionEvaluadora=new ComisionEvaluadora();
                comisionEvaluadora.setIdComisionEvaluadora( object.getIdComisionEvaluadoraEvaluar() );
                comisionEvaluadoraBean1=implementComisionEvaluadoraDAO.obtenerComisionEvaluadoraByIdComisionEvaluadora(comisionEvaluadora);
                
                comisionEvaluadoraBean.setComisionEvaluadoraBean(comisionEvaluadoraBean1);
                        
                Carrera c=new Carrera();
                c.setIdCarrera( comisionEvaluadoraBean1.getIdCarreraBean() );
                c=implementComisionEvaluadoraDAO.obtenerCarreraByIdCarrera(c);
                if( c!=null ){
                    comisionEvaluadoraBean.setCarreraComisionEvaluadoraEvaluar(c.getNombre() );
                    Facultad f=new Facultad();
                    f.setIdFacultad( c.getFacultad().getIdFacultad() );
                    f=implementComisionEvaluadoraDAO.obtenerFacultadByIdFacultad(f);
                    if( f!=null ){
                        comisionEvaluadoraBean.setFacultadComisionEvaluadoraEvaluar(f.getNombre() );
                        Universidad u=new Universidad();
                        u.setSiglaUniversidad( f.getUniversidad().getSiglaUniversidad() );
                        u=implementComisionEvaluadoraDAO.obtenerUniversidadBySiglaUniversidad(u);
                        if( u!=null ){
                            comisionEvaluadoraBean.setUniversidadComisionEvaluadoraEvaluar(u.getNombre() );
                            comisionEvaluadoraBean.setNombreComisionEvaluadoraEvaluar(u.getNombre()+" "+f.getNombre()+" "+c.getNombre() );
                        }
                        else{
                            comisionEvaluadoraBean.setUniversidadComisionEvaluadoraEvaluar("null" );
                            comisionEvaluadoraBean.setNombreComisionEvaluadoraEvaluar("null"+" "+f.getNombre()+" "+c.getNombre() );
                        }
                    }
                    else{
                        comisionEvaluadoraBean.setFacultadComisionEvaluadoraEvaluar("null" );
                        comisionEvaluadoraBean.setUniversidadComisionEvaluadoraEvaluar("null" );
                        comisionEvaluadoraBean.setNombreComisionEvaluadoraEvaluar("null"+" "+"null"+" "+c.getNombre() );
                    }
                }
                else{
                    comisionEvaluadoraBean.setCarreraComisionEvaluadoraEvaluar("null" );
                    comisionEvaluadoraBean.setFacultadComisionEvaluadoraEvaluar("null" );
                    comisionEvaluadoraBean.setUniversidadComisionEvaluadoraEvaluar("null" );
                    comisionEvaluadoraBean.setNombreComisionEvaluadoraEvaluar("null"+" "+"null"+" "+"null" );
                }
                
            }
 
            comisionEvaluadoraBean.setIdCarreraBean(object.getIdCarreraBean());//--
            
            //comisionEvaluadoraBean.setn
            
            comisionEvaluadoraBean.setGestion(object.getGestion());
            comisionEvaluadoraBean.setFechaInicio(object.getFechaInicio());
            comisionEvaluadoraBean.setFechaFin(object.getFechaFin());
            comisionEvaluadoraBean.setEstado(object.getEstado());

            Carrera carrera1 = new Carrera();
            carrera1.setIdCarrera(object.getIdCarreraBean());
            carrera1 = getImplementComisionEvaluadoraDAO().obtenerCarreraByIdCarrera(carrera);
            if (carrera1 != null) {
                CarreraBean carreraBean = new CarreraBean();
                carreraBean.setIdCarrera(carrera1.getIdCarrera());
                carreraBean.setSiglaCarrera(carrera1.getSiglaCarrera());
                carreraBean.setNombre(carrera1.getNombre());
                carreraBean.setIdFacultad(carrera1.getFacultad().getIdFacultad());
                carreraBean.setEstado(carrera1.getEstado());

                comisionEvaluadoraBean.setIdCarreraBean(carrera1.getIdCarrera());//--
                comisionEvaluadoraBean.setNombre(carrera1.getNombre());//--

                comisionEvaluadoraBean.setCarreraBean(carreraBean);
            }
            else{
                comisionEvaluadoraBean.setIdCarreraBean(-1);//--
                comisionEvaluadoraBean.setNombre("null");//--
            }

            comisionEvaluadoraBeans.add(comisionEvaluadoraBean);
        }
        return comisionEvaluadoraBeans;
    }

    @Override
    public Long modificarComisionEvaluadora(ComisionEvaluadoraBean obj) {
        Long retorno = new Long(0);
        ComisionEvaluadora comisionEvaluadora = new ComisionEvaluadora();
        comisionEvaluadora.setIdComisionEvaluadora(obj.getIdComisionEvaluadora());

        ComisionEvaluadora comisionEvaluadora1 = new ComisionEvaluadora();
        comisionEvaluadora1.setIdComisionEvaluadora(obj.getIdComisionEvaluadoraEvaluar());
        comisionEvaluadora.setComisionEvaluadora(comisionEvaluadora1);
        
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(obj.getIdCarreraBean());
        comisionEvaluadora.setCarrera(carrera);

        comisionEvaluadora.setGestion(obj.getGestion());
        comisionEvaluadora.setFechaInicio(obj.getFechaInicio());
        comisionEvaluadora.setFechaFin(obj.getFechaFin());
        comisionEvaluadora.setEstado(obj.getEstado());


        retorno = implementComisionEvaluadoraDAO.modificarComisionEvaluadora(comisionEvaluadora);
        return retorno;
    }

    @Override
    public Long crearComisionEvaluadora(ComisionEvaluadoraBean obj) {
        Long retorno = new Long(0);
        ComisionEvaluadora comisionEvaluadora = new ComisionEvaluadora();

        ComisionEvaluadora comisionEvaluadora1 = new ComisionEvaluadora();
        comisionEvaluadora1.setIdComisionEvaluadora(obj.getIdComisionEvaluadoraEvaluar());
        comisionEvaluadora.setComisionEvaluadora(comisionEvaluadora1);
        
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(obj.getIdCarreraBean());
        comisionEvaluadora.setCarrera(carrera);

        comisionEvaluadora.setGestion(obj.getGestion());
        comisionEvaluadora.setFechaInicio(obj.getFechaInicio());
        comisionEvaluadora.setFechaFin(obj.getFechaFin());
        comisionEvaluadora.setEstado("ACTIVO");


        retorno = implementComisionEvaluadoraDAO.crearComisionEvaluadora(comisionEvaluadora);
        return retorno;
    }

    /*@Override
    public DirectorCarreraBean obtenerDirectorCarreraByCi(DirectorCarreraBean obj) {
        DirectorCarreraBean directorCarreraBean = new DirectorCarreraBean();
        directorCarreraBean.setCi(obj.getCi());
        directorCarreraBean.setFechaInicio(new Date());
        directorCarreraBean.setFechaFin(new Date());
        directorCarreraBean.setEstado("estado");

        directorCarreraBean.setGradoAcademico("grado academico");
        directorCarreraBean.setGradoAcademicoAbreviatura("grado academico (Abr)");

        directorCarreraBean.setNombre("nombre");
        directorCarreraBean.setApp("apellido paterno");
        directorCarreraBean.setApm("apellido materno");
        directorCarreraBean.setCuenta("cuenta");
        directorCarreraBean.setContrasenia("password");

        DirectorCarrera directorCarrera = new DirectorCarrera();
        Docente docente1 = new Docente();
        docente1.setCi(obj.getCi());
        directorCarrera.setDocente(docente1);
        directorCarrera = implementDirectorCarreraDAO.obtenerDirectorCarreraByCi(directorCarrera);

        if (directorCarrera != null) {
            directorCarreraBean.setFechaInicio(directorCarrera.getFechaInicio());
            directorCarreraBean.setFechaFin(directorCarrera.getFechaFin());
            directorCarreraBean.setEstado(directorCarrera.getEstado());

            Docente docente = new Docente();
            docente.setCi(obj.getCi());
            docente = implementDirectorCarreraDAO.obtenerDocenteByCi(docente);
            if (docente != null) {
                directorCarreraBean.setGradoAcademico(docente.getGradoAcademico());
                directorCarreraBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());

                Persona persona = new Persona();
                persona.setCi(obj.getCi());
                persona = implementDirectorCarreraDAO.obtenerPersonaByCi(persona);
                if (persona != null) {
                    directorCarreraBean.setNombre(persona.getNombre());
                    directorCarreraBean.setApp(persona.getApp());
                    directorCarreraBean.setApm(persona.getApm());
                    directorCarreraBean.setCuenta(persona.getCuenta());
                    directorCarreraBean.setContrasenia(persona.getContrasenia());
                }
            }
        }
        return directorCarreraBean;
    }*/

    /**
     * @return the implementComisionEvaluadoraDAO
     */
    public IComisionEvaluadoraDAO getImplementComisionEvaluadoraDAO() {
        return implementComisionEvaluadoraDAO;
    }

    /**
     * @param implementComisionEvaluadoraDAO the implementComisionEvaluadoraDAO to set
     */
    public void setImplementComisionEvaluadoraDAO(IComisionEvaluadoraDAO implementComisionEvaluadoraDAO) {
        this.implementComisionEvaluadoraDAO = implementComisionEvaluadoraDAO;
    }

    @Override
    public List<MiembroComisionEvaluadoraBean> obtenerMiembroComisionEvaluadoras(ComisionEvaluadoraBean obj) {
        ComisionEvaluadora comisionEvaluadora=new ComisionEvaluadora();
        comisionEvaluadora.setIdComisionEvaluadora(obj.getIdComisionEvaluadora());
        List<MiembroComisionEvaluadoraBean> miembroComisionEvaluadoraBeans=new ArrayList<MiembroComisionEvaluadoraBean>();
        for( MiembroComisionEvaluadora object : implementComisionEvaluadoraDAO.obtenerMiembroComisionEvaluadoras(comisionEvaluadora) ){
            MiembroComisionEvaluadoraBean miembroComisionEvaluadoraBean=new MiembroComisionEvaluadoraBean();
            miembroComisionEvaluadoraBean.setIdMiembroComisionEvaluadora( object.getIdMiembroComisionEvaluadora() );
            miembroComisionEvaluadoraBean.setCi(object.getCi() );
            //ComisionEvaluadoraBean ceb=new ComisionEvaluadoraBean();
            //ceb.setIdComisionEvaluadora( obj.getIdComisionEvaluadora() );
            miembroComisionEvaluadoraBean.setIdComisionEvaluadoraBean( obj.getIdComisionEvaluadora() );
            DocenteBean db=new DocenteBean();
            db.setCi( object.getCi() );
            db.setGradoAcademico("grado academico");
            db.setGradoAcademicoAbreviatura("grado academico (abr)");
            db.setEstado("estado");
            db.setNombre("nombre");
            db.setApp("apellido paterno");
            db.setApm("apellido materno");
            db.setNombreCompleto("nombre completo");
            db.setCuenta("cuenta");
            db.setContrasenia("contraseña");
            Docente d=new Docente();
            d.setCi( object.getCi() );
            d=implementComisionEvaluadoraDAO.obtenerDocenteByCi(d);
            if( d!=null ){
                db.setGradoAcademico( d.getGradoAcademico() );
                db.setGradoAcademicoAbreviatura(d.getGradoAcademicoAbreviatura());
                db.setEstado(d.getEstado() );
                Persona p=new Persona();
                p.setCi( object.getCi() );
                p=implementComisionEvaluadoraDAO.obtenerPersonaByCi(p);
                if( p!=null ){
                    db.setNombre( p.getNombre() );
                    db.setApp(p.getApp() );
                    db.setApm(p.getApm() );
                    db.setCuenta(p.getCuenta() );
                    db.setContrasenia(p.getContrasenia() );
                    db.setNombreCompleto(p.getNombre()+" "+p.getApp()+" "+p.getApm() );
                }
            }
            
            miembroComisionEvaluadoraBean.setDocenteBean( db );
            miembroComisionEvaluadoraBean.setEstado(object.getEstado() );
            miembroComisionEvaluadoraBean.setFechaFin(object.getFechaFin() );
            miembroComisionEvaluadoraBean.setFechaInicio(object.getFechaInicio() );            
            
            miembroComisionEvaluadoraBeans.add(miembroComisionEvaluadoraBean);
        }
        return miembroComisionEvaluadoraBeans;
    }

    @Override
    public Long modificarMiembroComisionEvaluadora(MiembroComisionEvaluadoraBean obj) {
        Long respuesta=new Long(0);
        MiembroComisionEvaluadora miembroComisionEvaluadora=new MiembroComisionEvaluadora();
        miembroComisionEvaluadora. setIdMiembroComisionEvaluadora( obj.getIdMiembroComisionEvaluadora() );
        miembroComisionEvaluadora. setCi(obj.getCi() );
        
        ComisionEvaluadora comisionEvaluadora=new ComisionEvaluadora();
        comisionEvaluadora.setIdComisionEvaluadora( obj.getIdComisionEvaluadoraBean() );
        miembroComisionEvaluadora. setComisionEvaluadora( comisionEvaluadora );
        
        Docente docente=new Docente();
        docente.setCi( obj.getCi() );
        miembroComisionEvaluadora. setDocente( docente );
        miembroComisionEvaluadora. setEstado(obj.getEstado() );
        miembroComisionEvaluadora. setFechaFin(obj.getFechaFin() );
        miembroComisionEvaluadora. setFechaInicio(obj.getFechaInicio() );
        respuesta=implementComisionEvaluadoraDAO.modificarMiembroComisionEvaluadora(miembroComisionEvaluadora);
        return respuesta;
    }
    
    @Override
    public DocenteBean obtenerDocenteByCi(DocenteBean obj) {
        DocenteBean docenteBean = new DocenteBean();
        docenteBean.setCi(obj.getCi());
        docenteBean.setGradoAcademico("grado academico");
        docenteBean.setGradoAcademicoAbreviatura("grado academico (Abr)");
        docenteBean.setEstado("estado");

        docenteBean.setNombre("nombre");
        docenteBean.setApp("apellido paterno");
        docenteBean.setApm("apellido materno");
        docenteBean.setCuenta("cuenta");
        docenteBean.setContrasenia("password");
        docenteBean.setNombreCompleto("nombre completo");

        Docente docente = new Docente();
        Persona persona1 = new Persona();
        persona1.setCi(obj.getCi());
        docente.setCi(obj.getCi());
        docente.setPersona(persona1);
        docente = implementComisionEvaluadoraDAO.obtenerDocenteByCi(docente);

        if (docente != null) {
            docenteBean.setGradoAcademico(docente.getGradoAcademico());
            docenteBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());
            docenteBean.setEstado(docente.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getCi());
            persona = implementComisionEvaluadoraDAO.obtenerPersonaByCi(persona);
            if (persona != null) {
                docenteBean.setNombre(persona.getNombre());
                docenteBean.setApp(persona.getApp());
                docenteBean.setApm(persona.getApm());
                docenteBean.setCuenta(persona.getCuenta());
                docenteBean.setContrasenia(persona.getContrasenia());
                docenteBean.setNombreCompleto(persona.getNombre()+" "+persona.getApp()+" "+persona.getApm());
            }
        }
        return docenteBean;
    }
    
    @Override
    public Long crearMiembroComisionEvaluadora(MiembroComisionEvaluadoraBean obj) {
        Long respuesta=new Long(0);
        MiembroComisionEvaluadora miembroComisionEvaluadora=new MiembroComisionEvaluadora();
        //miembroComisionEvaluadora. setIdMiembroComisionEvaluadora( obj.getIdMiembroComisionEvaluadora() );
        miembroComisionEvaluadora. setCi(obj.getCi() );
        
        ComisionEvaluadora comisionEvaluadora=new ComisionEvaluadora();
        comisionEvaluadora.setIdComisionEvaluadora( obj.getIdComisionEvaluadoraBean() );
        miembroComisionEvaluadora. setComisionEvaluadora( comisionEvaluadora );
        
        Docente docente=new Docente();
        docente.setCi( obj.getCi() );
        miembroComisionEvaluadora. setDocente( docente );
        
        miembroComisionEvaluadora. setEstado("ACTIVO" );
        miembroComisionEvaluadora. setFechaFin(obj.getFechaFin() );
        miembroComisionEvaluadora. setFechaInicio(obj.getFechaInicio() );
        respuesta=implementComisionEvaluadoraDAO.crearMiembroComisionEvaluadora(miembroComisionEvaluadora);
        return respuesta;
    }
}
