/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.ICarreraBO;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VIIII1
 */
public class CarreraBean implements Serializable {

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private long idCarrera;
    private long idFacultad;
    private String siglaCarrera;
    private String nombre;
    private String estado;

    private ICarreraBO implementCarreraBO;
    private List<CarreraBean> lista;
    private Map<String, Long> listaMap;

    private Integer Creacion;

    private final static String[] estados;

    static {
        estados = new String[3];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "ELIMINADO";
    }

    public CarreraBean() {
        this.idCarrera = 0;
        this.siglaCarrera = "sigla Carrera";
        this.nombre = "nombre";
        this.idFacultad = 0;
        this.estado = "estado";
        this.Creacion = 0;
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

    /**
     * @return the idFacultad
     */
    public long getIdFacultad() {
        return idFacultad;
    }

    /**
     * @param idFacultad the idFacultad to set
     */
    public void setIdFacultad(long idFacultad) {
        this.idFacultad = idFacultad;
    }

    /**
     * @return the siglaCarrera
     */
    public String getSiglaCarrera() {
        return siglaCarrera;
    }

    /**
     * @param siglaCarrera the siglaCarrera to set
     */
    public void setSiglaCarrera(String siglaCarrera) {
        this.siglaCarrera = siglaCarrera;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the implementCarreraBO
     */
    public ICarreraBO getImplementCarreraBO() {
        return implementCarreraBO;
    }

    /**
     * @param implementCarreraBO the implementCarreraBO to set
     */
    public void setImplementCarreraBO(ICarreraBO implementCarreraBO) {
        this.implementCarreraBO = implementCarreraBO;
    }

    /**
     * @return the listaMap
     */
    public Map<String, Long> getListaMap() {
        return listaMap;
    }

    /**
     * @param listaMap the listaMap to set
     */
    public void setListaMap(Map<String, Long> listaMap) {
        this.listaMap = listaMap;
    }

    /**
     * @return the Creacion
     */
    public Integer getCreacion() {
        return Creacion;
    }

    /**
     * @param Creacion the Creacion to set
     */
    public void setCreacion(Integer Creacion) {
        this.Creacion = Creacion;
    }
    
    @PostConstruct
    public void obtenerCarreras() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        FacultadBean fb = (FacultadBean) (sessionMap.get("facultadBean"));
        FacesMessage msg = null;

        String siglaUniversidad = "";
        System.out.println("yossssss " +fb.getIdFacultad()+ " "+fb.getSiglaFacultad() + " " + fb.getNombre());

        if (fb.getIdFacultad() != 0) {

            setIdFacultad(fb.getIdFacultad());

            FacultadBean facultadBean = new FacultadBean();
            facultadBean.setIdFacultad(fb.getIdFacultad());
            setLista(implementCarreraBO.obtenerCarreras(facultadBean));
            mapearLista();

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

    /**
     * @return the lista
     */
    public List<CarreraBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<CarreraBean> lista) {
        this.lista = lista;
    }
    
    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);

        setIdCarrera(((CarreraBean) event.getObject()).getIdCarrera());
        setSiglaCarrera(((CarreraBean) event.getObject()).getSiglaCarrera());
        setNombre(((CarreraBean) event.getObject()).getNombre());
        setIdFacultad(((CarreraBean) event.getObject()).getIdFacultad());
        setEstado(((CarreraBean) event.getObject()).getEstado());      

        respuesta = implementCarreraBO.modificarCarrera(this);
        obtenerCarreras();

        System.out.println("1 " + getSiglaCarrera() );
        System.out.println("2 " + getNombre());

        System.out.println("Resultado " + respuesta);
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Carrera fue Modificado", ((CarreraBean) event.getObject()).getNombre());
        } else {
            msg = new FacesMessage("No se encontro Carrera", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCancel(RowEditEvent event) {
        obtenerCarreras();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", ((CarreraBean) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String crearCarrera() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        FacultadBean fb = (FacultadBean) (sessionMap.get("facultadBean"));
        FacesMessage msg = null;

        Long respuesta = new Long(0);
        setIdFacultad(fb.getIdFacultad());

        respuesta = getImplementCarreraBO().crearCarrera(this);
        obtenerCarreras();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La carrera con sigla " + getSiglaCarrera() + " Ya existe");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "La carrera fue creada");
            setSiglaCarrera("sigla carrera");
            setNombre("nombre");
            setEstado("estado");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public void mapearLista() {
        listaMap = new LinkedHashMap<>();
        for (CarreraBean obj : getLista()) {
            listaMap.put(obj.getNombre(), obj.getIdCarrera());
        }
    }
}
