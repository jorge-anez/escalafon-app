/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.ICoordinadorDeaBO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VIIII1
 */
public class CoordinadorDeaBean extends PersonaBean implements Serializable {

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private long idCoordinadorDea;
    private PersonaBean personaBean;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    
    private String nombreCompleto;
    
    private List<CoordinadorDeaBean> lista;
    private ICoordinadorDeaBO implementCoordinadorDeaBO;
    private CoordinadorDeaBean coordinadorDeaBeanSeleccionado;
    private List<CoordinadorDeaBean> filtroCoordinadorDeaBeans;
    
    private final static String[] estados;
    private SelectItem[] estadosOptions;

    static {
        estados = new String[4];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "FINALIZADO";
        estados[3] = "ELIMINADO";
    }

    public CoordinadorDeaBean() {
        this.idCoordinadorDea=0;
        this.fechaInicio = new Date();
        this.fechaFin = new Date();
        this.estado = "estado";
        this.setCi("ci");
        this.setEstado("estado");
        this.setNombre("nombre");
        this.setApp("apellido paterno");
        this.setApm("apellido materno");
        this.setCuenta("cuenta");
        this.setContrasenia("password");
        
        estadosOptions=crearEstadosOptions(estados);
    }

    /**
     * @return the personaBean
     */
    public PersonaBean getPersonaBean() {
        return personaBean;
    }

    /**
     * @param personaBean the personaBean to set
     */
    public void setPersonaBean(PersonaBean personaBean) {
        this.personaBean = personaBean;
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
     * @return the lista
     */
    public List<CoordinadorDeaBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<CoordinadorDeaBean> lista) {
        this.lista = lista;
    }

    /**
     * @return the implementCoordinadorDeaBO
     */
    public ICoordinadorDeaBO getImplementCoordinadorDeaBO() {
        return implementCoordinadorDeaBO;
    }

    /**
     * @param implementCoordinadorDeaBO the implementCoordinadorDeaBO to set
     */
    public void setImplementCoordinadorDeaBO(ICoordinadorDeaBO implementCoordinadorDeaBO) {
        this.implementCoordinadorDeaBO = implementCoordinadorDeaBO;
    }
    
    //@PostConstruct
    public void obtenerCoordinadorDeas() {
        setLista(getImplementCoordinadorDeaBO().obtenerCoordinadorDeas());
        //mapearLista();
    }
    
    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        CoordinadorDeaBean coordinadorDeaBean = new CoordinadorDeaBean();
        coordinadorDeaBean.setIdCoordinadorDea(((CoordinadorDeaBean) event.getObject()).getIdCoordinadorDea());
        coordinadorDeaBean.setCi(((CoordinadorDeaBean) event.getObject()).getCi());
        coordinadorDeaBean.setFechaInicio(((CoordinadorDeaBean) event.getObject()).getFechaInicio());
        coordinadorDeaBean.setFechaFin(((CoordinadorDeaBean) event.getObject()).getFechaFin());
        coordinadorDeaBean.setEstado(((CoordinadorDeaBean) event.getObject()).getEstado());
        
        respuesta = implementCoordinadorDeaBO.modificarCoordinadorDea(coordinadorDeaBean);
        obtenerCoordinadorDeas();
        System.out.println("1 " + getCi());
        System.out.println("2 " + getFechaFin());
        System.out.println("Resultado " + respuesta);
        
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Coordinador DEA fue Modificado", ((CoordinadorDeaBean) event.getObject()).getPersonaBean().getNombre());
        } else {
            msg = new FacesMessage("No se encontro Coordinador DEA", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        obtenerCoordinadorDeas();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String crearCoordinadorDea() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        respuesta = getImplementCoordinadorDeaBO().crearCoordinadorDea(this);
        obtenerCoordinadorDeas();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El coordinador con C.I:" + getCi() + "Ya existe");
        }
        else if( respuesta.longValue() == -1 ){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La cuenta " + getCuenta() + " No esta disponible, no se creo Coordinador Dea");
        }
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "El coordinador a sido creado");
            this.idCoordinadorDea=0;
            this.fechaInicio = new Date();
            this.fechaFin = new Date();
            this.estado = "estado";
            this.setCi("ci");
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
    
    public String obtenerCoordinadorDea() {
        FacesMessage msg = null;
        CoordinadorDeaBean coordinadorDeaBean = getImplementCoordinadorDeaBO().obtenerCoordinadorDeaByCi(this);

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");
        this.fechaInicio = coordinadorDeaBean.getFechaInicio();
        this.fechaFin = coordinadorDeaBean.getFechaFin();
        this.estado = coordinadorDeaBean.getEstado();

        this.setEstado(coordinadorDeaBean.getEstado());
        this.setNombre(coordinadorDeaBean.getNombre());
        this.setApp(coordinadorDeaBean.getApp());
        this.setApm(coordinadorDeaBean.getApm());
        this.setCuenta(coordinadorDeaBean.getCuenta());
        this.setContrasenia(coordinadorDeaBean.getContrasenia());

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
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
    
    private SelectItem[] crearEstadosOptions( String[] data ){
        SelectItem[] options=new SelectItem[data.length+1];
        options[0]=new SelectItem("", "Select");
        for( int i=0;i<data.length;i++ ){
            options[i+1]=new SelectItem(data[i],data[i]);
        }
        return options;
    }

    /**
     * @return the coordinadorDeaBeanSeleccionado
     */
    public CoordinadorDeaBean getCoordinadorDeaBeanSeleccionado() {
        return coordinadorDeaBeanSeleccionado;
    }

    /**
     * @param coordinadorDeaBeanSeleccionado the coordinadorDeaBeanSeleccionado to set
     */
    public void setCoordinadorDeaBeanSeleccionado(CoordinadorDeaBean coordinadorDeaBeanSeleccionado) {
        this.coordinadorDeaBeanSeleccionado = coordinadorDeaBeanSeleccionado;
    }

    /**
     * @return the filtroCoordinadorDeaBeans
     */
    public List<CoordinadorDeaBean> getFiltroCoordinadorDeaBeans() {
        return filtroCoordinadorDeaBeans;
    }

    /**
     * @param filtroCoordinadorDeaBeans the filtroCoordinadorDeaBeans to set
     */
    public void setFiltroCoordinadorDeaBeans(List<CoordinadorDeaBean> filtroCoordinadorDeaBeans) {
        this.filtroCoordinadorDeaBeans = filtroCoordinadorDeaBeans;
    }

    /**
     * @return the idCoordinadorDea
     */
    public long getIdCoordinadorDea() {
        return idCoordinadorDea;
    }

    /**
     * @param idCoordinadorDea the idCoordinadorDea to set
     */
    public void setIdCoordinadorDea(long idCoordinadorDea) {
        this.idCoordinadorDea = idCoordinadorDea;
    }
}
