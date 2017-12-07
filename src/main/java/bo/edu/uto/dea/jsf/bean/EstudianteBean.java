/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IDocenteBO;
import bo.edu.uto.dea.jhs.bo.IEstudianteBO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VIIII1
 */
public class EstudianteBean extends PersonaBean implements Serializable{

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    
    private String ci;
    private PersonaBean personaBean;
    private String estado;
    
    private String nombreCompleto;
    
    private List<EstudianteBean> mylista;
    private IEstudianteBO implementEstudianteBO;
    private EstudianteBean estudianteBeanSeleccionado;
    private List<EstudianteBean> filtroEstudianteBeans;
    
    private final static String[] estados;
    private SelectItem[] estadosOptions;

    static {
        estados = new String[4];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "FINALIZADO";
        estados[3] = "ELIMINADO";
    }

    public EstudianteBean() {
        this.ci="ci";
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
     * @return the ci
     */
    public String getCi() {
        return ci;
    }

    /**
     * @param ci the ci to set
     */
    public void setCi(String ci) {
        this.ci = ci;
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
     * @return the mylista
     */
    public List<EstudianteBean> getMylista() {
        return mylista;
    }

    /**
     * @param mylista the mylista to set
     */
    public void setMylista(List<EstudianteBean> mylista) {
        this.mylista = mylista;
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
    
    //@PostConstruct
    public void obtenerEstudiantes() {
        setMylista(getImplementEstudianteBO().obtenerEstudiantes());
        //mapearLista();
    }
    
    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        EstudianteBean estudianteBean = new EstudianteBean();
        estudianteBean.setCi(((EstudianteBean) event.getObject()).getCi());
        estudianteBean.setEstado(((EstudianteBean) event.getObject()).getEstado());
        
        respuesta = implementEstudianteBO.modificarEstudiante(estudianteBean);
        obtenerEstudiantes();
        System.out.println("1 " + getCi());
        System.out.println("2 " + getEstado());
        System.out.println("Resultado " + respuesta);
        
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Estudiante fue Modificado", ((EstudianteBean) event.getObject()).getCi());
        } else {
            msg = new FacesMessage("No se encontro Estudiante", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCancel(RowEditEvent event) {
        obtenerEstudiantes();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String crearEstudiante() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        respuesta = getImplementEstudianteBO().crearEstudiante(this);
        obtenerEstudiantes();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El estudiante con C.I:" + getCi() + " Ya existe");
        }
        else if( respuesta.longValue() == -1 ){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La cuenta " + getCuenta() + " No esta disponible, no se creo Estudiante");
        }
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "El estudiante a sido creado");
            this.ci="ci";
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
    
    public String obtenerEstudiante() {
        FacesMessage msg = null;
        EstudianteBean docenteBean = getImplementEstudianteBO().obtenerEstudianteByCi(this);

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");
        this.estado = docenteBean.getEstado();

        this.setEstado(docenteBean.getEstado());
        this.setNombre(docenteBean.getNombre());
        this.setApp(docenteBean.getApp());
        this.setApm(docenteBean.getApm());
        this.setCuenta(docenteBean.getCuenta());
        this.setContrasenia(docenteBean.getContrasenia());

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
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
     * @return the implementEstudianteBO
     */
    public IEstudianteBO getImplementEstudianteBO() {
        return implementEstudianteBO;
    }

    /**
     * @param implementEstudianteBO the implementEstudianteBO to set
     */
    public void setImplementEstudianteBO(IEstudianteBO implementEstudianteBO) {
        this.implementEstudianteBO = implementEstudianteBO;
    }

    /**
     * @return the estudianteBeanSeleccionado
     */
    public EstudianteBean getEstudianteBeanSeleccionado() {
        return estudianteBeanSeleccionado;
    }

    /**
     * @param estudianteBeanSeleccionado the estudianteBeanSeleccionado to set
     */
    public void setEstudianteBeanSeleccionado(EstudianteBean estudianteBeanSeleccionado) {
        this.estudianteBeanSeleccionado = estudianteBeanSeleccionado;
    }

    /**
     * @return the filtroEstudianteBeans
     */
    public List<EstudianteBean> getFiltroEstudianteBeans() {
        return filtroEstudianteBeans;
    }

    /**
     * @param filtroEstudianteBeans the filtroEstudianteBeans to set
     */
    public void setFiltroEstudianteBeans(List<EstudianteBean> filtroEstudianteBeans) {
        this.filtroEstudianteBeans = filtroEstudianteBeans;
    }
}
