/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IFacultadBO;
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
public class FacultadBean implements Serializable {

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private long idFacultad;
    private String siglaUniversidad;
    private String siglaFacultad;
    private String nombre;
    private String estado;
    private List<FacultadBean> lista;
    private Map<String, Long> listaMap;
    private IFacultadBO implementFacultadBO;
    private final static String[] estados;
    private Integer Creacion;

    static {
        estados = new String[3];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "ELIMINADO";
    }

    public FacultadBean() {
        this.idFacultad = 0;
        this.siglaFacultad = "sigla facultad";
        this.nombre = "nombre";
        this.siglaUniversidad = "sigla universidad";
        this.estado = "estado";
        this.Creacion = 0;
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
     * @return the siglaUniversidad
     */
    public String getSiglaUniversidad() {
        return siglaUniversidad;
    }

    /**
     * @param siglaUniversidad the siglaUniversidad to set
     */
    public void setSiglaUniversidad(String siglaUniversidad) {
        this.siglaUniversidad = siglaUniversidad;
    }

    /**
     * @return the siglaFacultad
     */
    public String getSiglaFacultad() {
        return siglaFacultad;
    }

    /**
     * @param siglaFacultad the siglaFacultad to set
     */
    public void setSiglaFacultad(String siglaFacultad) {
        this.siglaFacultad = siglaFacultad;
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
     * @return the lista
     */
    public List<FacultadBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<FacultadBean> lista) {
        this.lista = lista;
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
     * @return the implementFacultadBO
     */
    public IFacultadBO getImplementFacultadBO() {
        return implementFacultadBO;
    }

    /**
     * @param implementFacultadBO the implementFacultadBO to set
     */
    public void setImplementFacultadBO(IFacultadBO implementFacultadBO) {
        this.implementFacultadBO = implementFacultadBO;
    }

    @PostConstruct
    public void obtenerFacultads() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        UniversidadBean ub = (UniversidadBean) (sessionMap.get("universidadBean"));
        FacesMessage msg = null;

        String siglaUniversidad = "";
        System.out.println("yoummdfxsxss " + ub.getSiglaUniversidad() + " " + ub.getNombre());

        if (ub.getSiglaUniversidad().compareTo("-") != 0) {

            setSiglaUniversidad(ub.getSiglaUniversidad());

            UniversidadBean universidadBean = new UniversidadBean();
            universidadBean.setSiglaUniversidad(ub.getSiglaUniversidad());
            setLista(implementFacultadBO.obtenerFacultads(universidadBean));
            mapearLista();

            msg = new FacesMessage("Cargando Facultades", "");
        } else {
            msg = new FacesMessage("Seleccione una Universidad", "");
        }

        if (Creacion == 0) {
            Creacion = 1;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);

        setIdFacultad(((FacultadBean) event.getObject()).getIdFacultad());
        setSiglaFacultad(((FacultadBean) event.getObject()).getSiglaFacultad());
        setNombre(((FacultadBean) event.getObject()).getNombre());
        setSiglaUniversidad(((FacultadBean) event.getObject()).getSiglaUniversidad());
        setEstado(((FacultadBean) event.getObject()).getEstado());

        respuesta = implementFacultadBO.modificarFacultad(this);
        obtenerFacultads();

        System.out.println("1 " + getSiglaUniversidad());
        System.out.println("2 " + getNombre());

        System.out.println("Resultado " + respuesta);
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Facultad fue Modificado", ((FacultadBean) event.getObject()).getNombre());
        } else {
            msg = new FacesMessage("No se encontro Facultad", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        obtenerFacultads();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", ((FacultadBean) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String crearFacultad() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        UniversidadBean ub = (UniversidadBean) (sessionMap.get("universidadBean"));
        FacesMessage msg = null;

        Long respuesta = new Long(0);
        setSiglaUniversidad(ub.getSiglaUniversidad());

        respuesta = getImplementFacultadBO().crearFacultad(this);
        obtenerFacultads();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La facultad con sigla " + getSiglaFacultad() + " Ya existe");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "La facultad fue creada");
            setSiglaFacultad("sigla facultad");
            setNombre("nombre");
            setSiglaUniversidad("sigla universidad");
            setEstado("estado");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }

    public void mapearLista() {
        listaMap = new LinkedHashMap<>();
        for (FacultadBean obj : getLista()) {
            listaMap.put(obj.getNombre(), obj.getIdFacultad());
        }
    }
}
