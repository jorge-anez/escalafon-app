/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IDirectorCarreraBO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VIIII1
 */
public class DirectorCarreraBean extends DocenteBean implements Serializable {

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private long idDirectorCarrera;
    private DocenteBean docenteBean;
    private CarreraBean carreraBean;
    private long idCarrera;
    private Integer gestion;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private String nombreCompleto;
    private List<DirectorCarreraBean> lista;
    private IDirectorCarreraBO implementDirectorCarreraBO;
    private DirectorCarreraBean directorCarreraBeanSeleccionado;
    private List<DirectorCarreraBean> filtroDirectorCarreraBeans;
    private final static String[] estados;
    private SelectItem[] estadosOptions;
    private Integer Creacion;

    static {
        estados = new String[4];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "FINALIZADO";
        estados[3] = "ELIMINADO";
    }

    public DirectorCarreraBean() {
        this.idDirectorCarrera = 0;
        this.fechaInicio = new Date();
        this.fechaFin = new Date();
        this.estado = "estado";

        this.idCarrera = 0;

        this.setCi("ci");
        this.setGradoAcademico("grado academico");
        this.setGradoAcademicoAbreviatura("grado academico (Abr)");
        this.setEstado("estado");
        this.setNombre("nombre");
        this.setApp("apellido paterno");
        this.setApm("apellido materno");
        this.setCuenta("cuenta");
        this.setContrasenia("password");

        estadosOptions = crearEstadosOptions(estados);

        Creacion = 0;
    }

    /**
     * @return the idDirectorCarrera
     */
    public long getIdDirectorCarrera() {
        return idDirectorCarrera;
    }

    /**
     * @param idDirectorCarrera the idDirectorCarrera to set
     */
    public void setIdDirectorCarrera(long idDirectorCarrera) {
        this.idDirectorCarrera = idDirectorCarrera;
    }

    /**
     * @return the docenteBean
     */
    public DocenteBean getDocenteBean() {
        return docenteBean;
    }

    /**
     * @param docenteBean the docenteBean to set
     */
    public void setDocenteBean(DocenteBean docenteBean) {
        this.docenteBean = docenteBean;
    }

    /**
     * @return the carreraBean
     */
    public CarreraBean getCarreraBean() {
        return carreraBean;
    }

    /**
     * @param carreraBean the carreraBean to set
     */
    public void setCarreraBean(CarreraBean carreraBean) {
        this.carreraBean = carreraBean;
    }

    /**
     * @return the gestion
     */
    public Integer getGestion() {
        return gestion;
    }

    /**
     * @param gestion the gestion to set
     */
    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the lista
     */
    public List<DirectorCarreraBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<DirectorCarreraBean> lista) {
        this.lista = lista;
    }

    /**
     * @return the implementDirectorCarreraBO
     */
    public IDirectorCarreraBO getImplementDirectorCarreraBO() {
        return implementDirectorCarreraBO;
    }

    /**
     * @param implementDirectorCarreraBO the implementDirectorCarreraBO to set
     */
    public void setImplementDirectorCarreraBO(IDirectorCarreraBO implementDirectorCarreraBO) {
        this.implementDirectorCarreraBO = implementDirectorCarreraBO;
    }

    /**
     * @return the directorCarreraBeanSeleccionado
     */
    public DirectorCarreraBean getDirectorCarreraBeanSeleccionado() {
        return directorCarreraBeanSeleccionado;
    }

    /**
     * @param directorCarreraBeanSeleccionado the
     * directorCarreraBeanSeleccionado to set
     */
    public void setDirectorCarreraBeanSeleccionado(DirectorCarreraBean directorCarreraBeanSeleccionado) {
        this.directorCarreraBeanSeleccionado = directorCarreraBeanSeleccionado;
    }

    /**
     * @return the filtroDirectorCarreraBeans
     */
    public List<DirectorCarreraBean> getFiltroDirectorCarreraBeans() {
        return filtroDirectorCarreraBeans;
    }

    /**
     * @param filtroDirectorCarreraBeans the filtroDirectorCarreraBeans to set
     */
    public void setFiltroDirectorCarreraBeans(List<DirectorCarreraBean> filtroDirectorCarreraBeans) {
        this.filtroDirectorCarreraBeans = filtroDirectorCarreraBeans;
    }

    /**
     * @return the estadosOptions
     */
    public SelectItem[] getEstadosOptions() {
        return estadosOptions;
    }

    /**
     * @param estadosOptions the estadosOptions to set
     */
    public void setEstadosOptions(SelectItem[] estadosOptions) {
        this.estadosOptions = estadosOptions;
    }

    /**
     * @return the idCarrera
     */
    public long getIdCarrera() {
        return idCarrera;
    }

    /**
     * @param idCarrera the idCarrera to set
     */
    public void setIdCarrera(long idCarrera) {
        this.idCarrera = idCarrera;
    }

    public void obtenerDirectorCarreras() { //-- select       
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        CarreraBean cb = (CarreraBean) (sessionMap.get("carreraBean"));
        FacesMessage msg = null;

        System.out.println("yPLOPL " + cb.getIdCarrera() + " " + cb.getSiglaCarrera() + " " + cb.getNombre());

        if (cb.getIdCarrera() != 0) {

            setIdCarrera(cb.getIdCarrera());

            CarreraBean cb1 = new CarreraBean();
            cb1.setIdCarrera(cb.getIdCarrera());
            //implementMateriaBO.obtenerMaterias(carreraBean);
            setLista(getImplementDirectorCarreraBO().obtenerDirectorCarreras(cb1));
            //mapearLista();

            msg = new FacesMessage("Cargando Carreras", "");
        } else {
            msg = new FacesMessage("Seleccione una Facultad", "");
        }

        if (Creacion == 0) {
            Creacion = 1;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    private SelectItem[] crearEstadosOptions(String[] data) {
        SelectItem[] options = new SelectItem[data.length + 1];
        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < data.length; i++) {
            options[i + 1] = new SelectItem(data[i], data[i]);
        }
        return options;
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        DirectorCarreraBean directorCarreraBean = new DirectorCarreraBean();

        directorCarreraBean.setIdDirectorCarrera(((DirectorCarreraBean) event.getObject()).getIdDirectorCarrera());
        directorCarreraBean.setCi(((DirectorCarreraBean) event.getObject()).getCi());
        directorCarreraBean.setIdCarrera(((DirectorCarreraBean) event.getObject()).getIdCarrera());
        directorCarreraBean.setGestion(((DirectorCarreraBean) event.getObject()).getGestion());
        directorCarreraBean.setFechaInicio(((DirectorCarreraBean) event.getObject()).getFechaInicio());
        directorCarreraBean.setFechaFin(((DirectorCarreraBean) event.getObject()).getFechaFin());
        directorCarreraBean.setEstado(((DirectorCarreraBean) event.getObject()).getEstado());

        respuesta = implementDirectorCarreraBO.modificarDirectorCarrera(directorCarreraBean);
        obtenerDirectorCarreras();
        System.out.println("1 " + getCi());
        System.out.println("2 " + getFechaFin());

        System.out.println("Resultado " + respuesta);
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Director de Carrera fue Modificado", ((DirectorCarreraBean) event.getObject()).getDocenteBean().getPersonaBean().getNombre());
        } else {
            msg = new FacesMessage("No se encontro Director de Carrera", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        obtenerDirectorCarreras();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String crearDirectorCarrera() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        respuesta = getImplementDirectorCarreraBO().crearDirectorCarrera(this);
        obtenerDirectorCarreras();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El director de carrera con C.I:" + getCi() + " Ya existe");
        } else if (respuesta.longValue() == -1) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La cuenta " + getCuenta() + " No esta disponible, no se creo Director de Carrera");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "El director de carrera a sido creado");
            this.idDirectorCarrera = 0;
            this.fechaInicio = new Date();
            this.fechaFin = new Date();
            this.estado = "estado";

            this.setCi("ci");
            this.setGradoAcademico("grado academico");
            this.setGradoAcademicoAbreviatura("grado academico (Abr)");
            this.setEstado("estado");
            this.setNombre("nombre");
            this.setApp("apellido paterno");
            this.setApm("apellido materno");
            this.setCuenta("cuenta");
            this.setContrasenia("password");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public String obtenerDirectorCarrera() {
        FacesMessage msg = null;
        System.out.println("Aquiii1 ");
        DirectorCarreraBean directorCarreraBean = getImplementDirectorCarreraBO().obtenerDirectorCarreraByCi(this);
        System.out.println("Aquiii222 "+directorCarreraBean.getNombre());

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");
        this.fechaInicio = directorCarreraBean.getFechaInicio();
        this.fechaFin = directorCarreraBean.getFechaFin();
        this.estado = directorCarreraBean.getEstado();

        this.setGradoAcademico(directorCarreraBean.getGradoAcademico());
        this.setGradoAcademicoAbreviatura(directorCarreraBean.getGradoAcademicoAbreviatura());

        this.setEstado(directorCarreraBean.getEstado());
        this.setNombre(directorCarreraBean.getNombre());
        this.setApp(directorCarreraBean.getApp());
        this.setApm(directorCarreraBean.getApm());
        this.setCuenta(directorCarreraBean.getCuenta());
        this.setContrasenia(directorCarreraBean.getContrasenia());

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
}
