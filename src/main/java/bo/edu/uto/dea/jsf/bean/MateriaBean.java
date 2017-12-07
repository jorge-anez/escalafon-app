/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IMateriaBO;
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
public class MateriaBean implements Serializable {

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private long idMateria;
    private long idCarrera;
    private String siglaMateria;
    private String nombre;
    private String estado;
    
    private IMateriaBO implementMateriaBO;
    private List<MateriaBean> lista;
    private Map<String, Long> listaMap;
    
    private MateriaBean materiaSeleccionada;
    
    private List<RequiereMateriaBean> requiereMaterias;
    private RequiereMateriaBean requiereMateriaSeleccionada;
    
    private RequiereMateriaBean requiereMateriaBean;

    private Integer Creacion;

    private final static String[] estados;

    static {
        estados = new String[3];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "ELIMINADO";
    }

    public MateriaBean() {
        this.idMateria = 0;
        this.siglaMateria = "sigla materia";
        this.nombre = "nombre";
        this.idCarrera = 0;
        this.estado = "estado";
        this.Creacion = 0;
    }
    
    /**
     * @return the idMateria
     */
    public long getIdMateria() {
        return idMateria;
    }

    /**
     * @param idMateria the idMateria to set
     */
    public void setIdMateria(long idMateria) {
        this.idMateria = idMateria;
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
     * @return the siglaMateria
     */
    public String getSiglaMateria() {
        return siglaMateria;
    }

    /**
     * @param siglaMateria the siglaMateria to set
     */
    public void setSiglaMateria(String siglaMateria) {
        this.siglaMateria = siglaMateria;
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
     * @return the implementMateriaBO
     */
    public IMateriaBO getImplementMateriaBO() {
        return implementMateriaBO;
    }

    /**
     * @param implementMateriaBO the implementMateriaBO to set
     */
    public void setImplementMateriaBO(IMateriaBO implementMateriaBO) {
        this.implementMateriaBO = implementMateriaBO;
    }

    /**
     * @return the lista
     */
    public List<MateriaBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<MateriaBean> lista) {
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
    public void obtenerMaterias() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        CarreraBean cb = (CarreraBean) (sessionMap.get("carreraBean"));
        FacesMessage msg = null;

        System.out.println("yPLOPL " + cb.getIdCarrera() +" "+ cb.getSiglaCarrera() + " " + cb.getNombre());

        if (cb.getIdCarrera() != 0) {

            setIdCarrera(cb.getIdCarrera());

            CarreraBean cb1 = new CarreraBean();
            cb1.setIdCarrera(cb.getIdCarrera());
            //implementMateriaBO.obtenerMaterias(carreraBean);
            setLista( implementMateriaBO.obtenerMaterias(cb1) );
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
    
    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);

        setIdMateria(((MateriaBean) event.getObject()).getIdMateria());
        setSiglaMateria(((MateriaBean) event.getObject()).getSiglaMateria());
        setNombre(((MateriaBean) event.getObject()).getNombre());
        setIdCarrera(((MateriaBean) event.getObject()).getIdCarrera());
        setEstado(((MateriaBean) event.getObject()).getEstado());
        
        respuesta = implementMateriaBO.modificarMateria(this);
        obtenerMaterias();

        System.out.println("1 " + getSiglaMateria() );
        System.out.println("2 " + getNombre());

        System.out.println("Resultado " + respuesta);
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Materia fue Modificado", ((MateriaBean) event.getObject()).getNombre());
        } else {
            msg = new FacesMessage("No se encontro Materia", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Modificacion cancelada", ((MateriaBean) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String crearMateria() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        CarreraBean cb = (CarreraBean) (sessionMap.get("carreraBean"));
        
        FacesMessage msg = null;
        //msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "LidDDDD " + cb.getIdCarrera()+" as "+getIdCarrera());

        Long respuesta = new Long(0);
        //setIdCarrera(cb.getIdCarrera());

        respuesta = getImplementMateriaBO().crearMateria(this);
        obtenerMaterias();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La materia con sigla " + getSiglaMateria() + " Ya existe");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "La materia fue creada --"+cb.getIdCarrera());
            setSiglaMateria("sigla materia");
            setNombre("nombre");
            setEstado("estado");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public void mapearLista() {
        listaMap = new LinkedHashMap<>();
        for (MateriaBean obj : getLista()) {
            listaMap.put(obj.getNombre(), obj.getIdMateria());
        }
    }

    /**
     * @return the materiaSeleccionada
     */
    public MateriaBean getMateriaSeleccionada() {
        return materiaSeleccionada;
    }

    /**
     * @param materiaSeleccionada the materiaSeleccionada to set
     */
    public void setMateriaSeleccionada(MateriaBean materiaSeleccionada) {
        this.materiaSeleccionada = materiaSeleccionada;
    }

    /**
     * @return the requiereMaterias
     */
    public List<RequiereMateriaBean> getRequiereMaterias() {
        return requiereMaterias;
    }

    /**
     * @param requiereMaterias the requiereMaterias to set
     */
    public void setRequiereMaterias(List<RequiereMateriaBean> requiereMaterias) {
        this.requiereMaterias = requiereMaterias;
    }
    
    public void cargarRequiereMaterias() {
        FacesMessage msg = new FacesMessage("Cargando Requisito Materia de ", " "+this.materiaSeleccionada.getIdMateria()+" "+this.materiaSeleccionada.getNombre() );
        setRequiereMaterias( implementMateriaBO.obtenerRequiereMaterias(this.materiaSeleccionada) );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the requiereMateriaSeleccionada
     */
    public RequiereMateriaBean getRequiereMateriaSeleccionada() {
        return requiereMateriaSeleccionada;
    }

    /**
     * @param requiereMateriaSeleccionada the requiereMateriaSeleccionada to set
     */
    public void setRequiereMateriaSeleccionada(RequiereMateriaBean requiereMateriaSeleccionada) {
        this.requiereMateriaSeleccionada = requiereMateriaSeleccionada;
    }
    
    public void eliminarRequiereMateria() {
        FacesMessage msg=null;
        Long respuesta = new Long(0);
        respuesta=implementMateriaBO.eliminarRequiereMateria(this.requiereMateriaSeleccionada);
        cargarRequiereMaterias();
        if( respuesta.longValue()==1 ){
            msg = new FacesMessage("Materia Requisito fue eliminado ", " " );
        }
        else{
            msg = new FacesMessage("No se encontro Requisito Materia ", " " );
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the requiereMateriaBean
     */
    public RequiereMateriaBean getRequiereMateriaBean() {
        return requiereMateriaBean;
    }

    /**
     * @param requiereMateriaBean the requiereMateriaBean to set
     */
    public void setRequiereMateriaBean(RequiereMateriaBean requiereMateriaBean) {
        this.requiereMateriaBean = requiereMateriaBean;
    }
    
    public String crearRequisitoMateria() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        RequiereMateriaBean requiereMateriaBean=new RequiereMateriaBean();
        requiereMateriaBean.setIdMateriaByIdMateria( this.materiaSeleccionada.getIdMateria() );
        requiereMateriaBean.setIdMateriaByIdMateriaRequisito( getIdMateria() );
        
        respuesta=implementMateriaBO.crearRequiereMateria(requiereMateriaBean);
        cargarRequiereMaterias();
        if( respuesta.longValue()==1 ){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Requisito Materia se a creado", " " );
        }
        else{
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La Materia Requisito ya esta asginada a la materia", " " );
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
}
