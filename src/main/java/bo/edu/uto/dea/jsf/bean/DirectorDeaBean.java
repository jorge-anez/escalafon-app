/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IDirectorDeaBO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VIIII1
 */
public class DirectorDeaBean extends DocenteBean implements Serializable {

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private long idDirectorDea;
    private DocenteBean docenteBean;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    
    private String nombreCompleto;
    
    private List<DirectorDeaBean> lista;
    private IDirectorDeaBO implementDirectorDeaBO;
    
    private DirectorDeaBean directorDeaBeanSeleccionado;
    private List<DirectorDeaBean> filtroDirectorDeaBeans;
    private List<DirectorDeaBean> directorDeaBeanSeleccionados;
    
    private final static String[] estados;
    private SelectItem[] estadosOptions;

    static {
        estados = new String[4];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "FINALIZADO";
        estados[3] = "ELIMINADO";
    }

    public DirectorDeaBean() {
        this.idDirectorDea=0;
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
        
        estadosOptions=crearEstadosOptions(estados);
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

    //@PostConstruct
    public void obtenerDirectorDeas() {
        setLista(getImplementDirectorDeaBO().obtenerDirectorDeas());
        //mapearLista();
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
     * @return the lista
     */
    public List<DirectorDeaBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<DirectorDeaBean> lista) {
        this.lista = lista;
    }

    /**
     * @return the implementDirectorDeaBO
     */
    public IDirectorDeaBO getImplementDirectorDeaBO() {
        return implementDirectorDeaBO;
    }

    /**
     * @param implementDirectorDeaBO the implementDirectorDeaBO to set
     */
    public void setImplementDirectorDeaBO(IDirectorDeaBO implementDirectorDeaBO) {
        this.implementDirectorDeaBO = implementDirectorDeaBO;
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        DirectorDeaBean directorDeaBean = new DirectorDeaBean();
        
        directorDeaBean.setIdDirectorDea(((DirectorDeaBean) event.getObject()).getIdDirectorDea());
        directorDeaBean.setCi(((DirectorDeaBean) event.getObject()).getCi());
        directorDeaBean.setFechaInicio(((DirectorDeaBean) event.getObject()).getFechaInicio());
        directorDeaBean.setFechaFin(((DirectorDeaBean) event.getObject()).getFechaFin());
        directorDeaBean.setEstado(((DirectorDeaBean) event.getObject()).getEstado());

        respuesta = implementDirectorDeaBO.modificarDirectorDea(directorDeaBean);
        obtenerDirectorDeas();
        System.out.println("1 " + getCi());
        System.out.println("2 " + getFechaFin());

        System.out.println("Resultado " + respuesta);
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Director DEA fue Modificado", ((DirectorDeaBean) event.getObject()).getDocenteBean().getPersonaBean().getNombre());
        } else {
            msg = new FacesMessage("No se encontro Director DEA", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        obtenerDirectorDeas();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String crearDirectorDea() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        respuesta = getImplementDirectorDeaBO().crearDirectorDea(this);
        obtenerDirectorDeas();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El director con C.I:" + getCi() + "Ya existe");
        }
        else if( respuesta.longValue() == -1 ){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La cuenta " + getCuenta() + " No esta disponible, no se creo Director Dea");
        }
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "El director a sido creado");
            this.idDirectorDea=0;
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

    public String obtenerDirectorDea() {
        FacesMessage msg = null;
        DirectorDeaBean directorDeaBean = getImplementDirectorDeaBO().obtenerDirectorDeaByCi(this);

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");
        this.fechaInicio = directorDeaBean.getFechaInicio();
        this.fechaFin = directorDeaBean.getFechaFin();
        this.estado = directorDeaBean.getEstado();

        this.setGradoAcademico(directorDeaBean.getGradoAcademico());
        this.setGradoAcademicoAbreviatura(directorDeaBean.getGradoAcademicoAbreviatura());

        this.setEstado(directorDeaBean.getEstado());
        this.setNombre(directorDeaBean.getNombre());
        this.setApp(directorDeaBean.getApp());
        this.setApm(directorDeaBean.getApm());
        this.setCuenta(directorDeaBean.getCuenta());
        this.setContrasenia(directorDeaBean.getContrasenia());

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }

    /**
     * @return the directorDeaBeanSeleccionado
     */
    public DirectorDeaBean getDirectorDeaBeanSeleccionado() {
        return directorDeaBeanSeleccionado;
    }

    /**
     * @param directorDeaBeanSeleccionado the directorDeaBeanSeleccionado to set
     */
    public void setDirectorDeaBeanSeleccionado(DirectorDeaBean directorDeaBeanSeleccionado) {
        this.directorDeaBeanSeleccionado = directorDeaBeanSeleccionado;
    }

    /**
     * @return the filtroDirectorDeaBeans
     */
    public List<DirectorDeaBean> getFiltroDirectorDeaBeans() {
        return filtroDirectorDeaBeans;
    }

    /**
     * @param filtroDirectorDeaBeans the filtroDirectorDeaBeans to set
     */
    public void setFiltroDirectorDeaBeans(List<DirectorDeaBean> filtroDirectorDeaBeans) {
        this.filtroDirectorDeaBeans = filtroDirectorDeaBeans;
    }

    /**
     * @return the directorDeaBeanSeleccionados
     */
    public List<DirectorDeaBean> getDirectorDeaBeanSeleccionados() {
        return directorDeaBeanSeleccionados;
    }

    /**
     * @param directorDeaBeanSeleccionados the directorDeaBeanSeleccionados to set
     */
    public void setDirectorDeaBeanSeleccionados(List<DirectorDeaBean> directorDeaBeanSeleccionados) {
        this.directorDeaBeanSeleccionados = directorDeaBeanSeleccionados;
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
    
    private SelectItem[] crearEstadosOptions( String[] data ){
        SelectItem[] options=new SelectItem[data.length+1];
        options[0]=new SelectItem("", "Select");
        for( int i=0;i<data.length;i++ ){
            options[i+1]=new SelectItem(data[i],data[i]);
        }
        return options;
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
     * @return the idDirectorDea
     */
    public long getIdDirectorDea() {
        return idDirectorDea;
    }

    /**
     * @param idDirectorDea the idDirectorDea to set
     */
    public void setIdDirectorDea(long idDirectorDea) {
        this.idDirectorDea = idDirectorDea;
    }
}
