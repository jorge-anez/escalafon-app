/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IDocenteBO;
import java.io.Serializable;
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
public class DocenteBean extends PersonaBean implements Serializable{

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private String ci;
    private PersonaBean personaBean;
    private String gradoAcademico;
    private String gradoAcademicoAbreviatura;
    private String estado;
    
    private String nombreCompleto;
    
    private List<DocenteBean> mylista;
    private IDocenteBO implementDocenteBO;
    private DocenteBean docenteBeanSeleccionado;
    private List<DocenteBean> filtroDocenteBeans;
    
    private final static String[] estados;
    private SelectItem[] estadosOptions;

    static {
        estados = new String[4];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "FINALIZADO";
        estados[3] = "ELIMINADO";
    }

    public DocenteBean() {
        this.ci="ci";
        this.gradoAcademico = "grado academico";
        this.gradoAcademicoAbreviatura = "grado academico (Abr)";
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
     * @return the gradoAcademico
     */
    public String getGradoAcademico() {
        return gradoAcademico;
    }

    /**
     * @param gradoAcademico the gradoAcademico to set
     */
    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    /**
     * @return the gradoAcademicoAbreviatura
     */
    public String getGradoAcademicoAbreviatura() {
        return gradoAcademicoAbreviatura;
    }

    /**
     * @param gradoAcademicoAbreviatura the gradoAcademicoAbreviatura to set
     */
    public void setGradoAcademicoAbreviatura(String gradoAcademicoAbreviatura) {
        this.gradoAcademicoAbreviatura = gradoAcademicoAbreviatura;
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
    public List<DocenteBean> getMylista() {
        return mylista;
    }

    /**
     * @param mylista the mylista to set
     */
    public void setMylista(List<DocenteBean> mylista) {
        this.mylista = mylista;
    }

    /**
     * @return the docenteBeanSeleccionado
     */
    public DocenteBean getDocenteBeanSeleccionado() {
        return docenteBeanSeleccionado;
    }

    /**
     * @param docenteBeanSeleccionado the docenteBeanSeleccionado to set
     */
    public void setDocenteBeanSeleccionado(DocenteBean docenteBeanSeleccionado) {
        this.docenteBeanSeleccionado = docenteBeanSeleccionado;
    }

    /**
     * @return the filtroDocenteBeans
     */
    public List<DocenteBean> getFiltroDocenteBeans() {
        return filtroDocenteBeans;
    }

    /**
     * @param filtroDocenteBeans the filtroDocenteBeans to set
     */
    public void setFiltroDocenteBeans(List<DocenteBean> filtroDocenteBeans) {
        this.filtroDocenteBeans = filtroDocenteBeans;
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
     * @return the implementDocenteBO
     */
    public IDocenteBO getImplementDocenteBO() {
        return implementDocenteBO;
    }

    /**
     * @param implementDocenteBO the implementDocenteBO to set
     */
    public void setImplementDocenteBO(IDocenteBO implementDocenteBO) {
        this.implementDocenteBO = implementDocenteBO;
    }
    
    //@PostConstruct
    public void obtenerDocentes() {
        setMylista(getImplementDocenteBO().obtenerDocentes());
        //mapearLista();
    }
    
    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        DocenteBean docenteBean = new DocenteBean();
        docenteBean.setCi(((DocenteBean) event.getObject()).getCi());
        docenteBean.setGradoAcademico(((DocenteBean) event.getObject()).getGradoAcademico());
        docenteBean.setGradoAcademicoAbreviatura(((DocenteBean) event.getObject()).getGradoAcademicoAbreviatura());
        docenteBean.setEstado(((DocenteBean) event.getObject()).getEstado());
        
        respuesta = implementDocenteBO.modificarDocente(docenteBean);
        obtenerDocentes();
        System.out.println("1 " + getCi());
        System.out.println("2 " + getGradoAcademico());
        System.out.println("Resultado " + respuesta);
        
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Docente fue Modificado", ((DocenteBean) event.getObject()).getPersonaBean().getCi());
        } else {
            msg = new FacesMessage("No se encontro Docente", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCancel(RowEditEvent event) {
        obtenerDocentes();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String crearDocente() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        respuesta = getImplementDocenteBO().crearDocente(this);
        obtenerDocentes();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El docente con C.I:" + getCi() + " Ya existe");
        }
        else if( respuesta.longValue() == -1 ){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La cuenta " + getCuenta() + " No esta disponible, no se creo Docente");
        }
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "El docente a sido creado");
            this.ci="ci";
            this.gradoAcademico = "grado academico";
            this.gradoAcademicoAbreviatura = "grado academico (Abr)";
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
    
    public String obtenerDocente() {
        FacesMessage msg = null;
        DocenteBean docenteBean = getImplementDocenteBO().obtenerDocenteByCi(this);

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");
        this.gradoAcademico = docenteBean.getGradoAcademico();
        this.gradoAcademicoAbreviatura = docenteBean.getGradoAcademicoAbreviatura();
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
}
