/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IComisionEvaluadoraBO;
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
public class ComisionEvaluadoraBean implements Serializable {

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private long idComisionEvaluadora;
    private ComisionEvaluadoraBean comisionEvaluadoraBean;
    
    private long idComisionEvaluadoraEvaluar;
    private String carreraComisionEvaluadoraEvaluar;
    private String facultadComisionEvaluadoraEvaluar;
    private String universidadComisionEvaluadoraEvaluar;
    private String nombreComisionEvaluadoraEvaluar;
    
    private CarreraBean carreraBean;
    private long idCarreraBean;
    private String nombre;
    
    private Integer gestion;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    
    private List<ComisionEvaluadoraBean> lista;
    private IComisionEvaluadoraBO implementComisionEvaluadoraBO;
    private ComisionEvaluadoraBean comisionEvaluadoraBeanSeleccionado;
    private List<ComisionEvaluadoraBean> filtroComisionEvaluadoraBeans;
    
    private List<MiembroComisionEvaluadoraBean> listaMiembroComisionEvaluadoraBeans;
    private MiembroComisionEvaluadoraBean miembroComisionEvaluadoraBean;
    
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

    public ComisionEvaluadoraBean() {
        this.idComisionEvaluadora = 0;
        this.idComisionEvaluadoraEvaluar = -1;
        this.nombre="nombre carrera";
        this.fechaInicio = new Date();
        this.fechaFin = new Date();
        this.gestion=fechaInicio.getYear();
        this.estado = "estado";
        this.carreraComisionEvaluadoraEvaluar="null";
        this.facultadComisionEvaluadoraEvaluar="null";
        this.universidadComisionEvaluadoraEvaluar="null";
        this.nombreComisionEvaluadoraEvaluar="null";

        this.idCarreraBean=-1;
    
        estadosOptions = crearEstadosOptions(estados);
        
        this.miembroComisionEvaluadoraBean=new MiembroComisionEvaluadoraBean();
        this.miembroComisionEvaluadoraBean.setIdComisionEvaluadoraBean(0);
        this.miembroComisionEvaluadoraBean.setCi("ci");
        this.miembroComisionEvaluadoraBean.setIdMiembroComisionEvaluadora(0);
        //ComisionEvaluadoraBean ceb=new ComisionEvaluadoraBean();
        //ceb.setIdComisionEvaluadora(0);
        //this.miembroComisionEvaluadoraBean.setComisionEvaluadoraBean( ceb );
        DocenteBean db=new DocenteBean();
        db.setCi("ci");
        db.setGradoAcademico("ci");
        db.setGradoAcademicoAbreviatura("ci");
        db.setEstado("ci");
        db.setNombre("ci");
        db.setApp("ci");
        db.setApm("ci");
        db.setNombreCompleto("ci");
        db.setCuenta("ci");
        db.setContrasenia("ci");
        this.miembroComisionEvaluadoraBean.setDocenteBean( db );
        this.miembroComisionEvaluadoraBean.setEstado("estado");
        this.miembroComisionEvaluadoraBean.setFechaFin(new Date());
        this.miembroComisionEvaluadoraBean.setFechaInicio(new Date());

    }

    public void obtenerComisionEvaluadoras() { //-- select       
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        CarreraBean cb = (CarreraBean) (sessionMap.get("carreraBean"));
        FacesMessage msg = null;

        System.out.println("COMIIIIyPLOPL " + cb.getIdCarrera() + " " + cb.getSiglaCarrera() + " " + cb.getNombre());

        if (cb.getIdCarrera() != 0) {

            setIdCarreraBean(cb.getIdCarrera());

            CarreraBean cb1 = new CarreraBean();
            cb1.setIdCarrera(cb.getIdCarrera());
            //implementMateriaBO.obtenerMaterias(carreraBean);
            setLista(getImplementComisionEvaluadoraBO().obtenerComisionEvaluadoras(cb1));
            System.out.println("OKI " );
            //mapearLista();

            msg = new FacesMessage("Cargando Carreras", "");
        } else {
            msg = new FacesMessage("Seleccione una Facultad", "");
        }

            FacesContext.getCurrentInstance().addMessage(null, msg);
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
        System.out.println("entor " + getIdComisionEvaluadora());
        ComisionEvaluadoraBean comisionEvaluadoraBean = new ComisionEvaluadoraBean();

        comisionEvaluadoraBean.setIdComisionEvaluadora(((ComisionEvaluadoraBean) event.getObject()).getIdComisionEvaluadora());
        comisionEvaluadoraBean.setIdCarreraBean(((ComisionEvaluadoraBean) event.getObject()).getIdCarreraBean());
        comisionEvaluadoraBean.setIdComisionEvaluadoraEvaluar(((ComisionEvaluadoraBean) event.getObject()).getIdComisionEvaluadoraEvaluar());
        comisionEvaluadoraBean.setGestion(((ComisionEvaluadoraBean) event.getObject()).getGestion());
        comisionEvaluadoraBean.setFechaInicio(((ComisionEvaluadoraBean) event.getObject()).getFechaInicio());
        comisionEvaluadoraBean.setFechaFin(((ComisionEvaluadoraBean) event.getObject()).getFechaFin());
        comisionEvaluadoraBean.setEstado(((ComisionEvaluadoraBean) event.getObject()).getEstado());
        
        System.out.println("entor 2222" + getIdComisionEvaluadora());

        respuesta = implementComisionEvaluadoraBO.modificarComisionEvaluadora(comisionEvaluadoraBean);
        obtenerComisionEvaluadoras();
        System.out.println("1 " + getIdComisionEvaluadora());
        System.out.println("2 " + getFechaFin());

        System.out.println("Resultado " + respuesta);
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Comision Evaluadota fue Modificado", ""+((ComisionEvaluadoraBean) event.getObject()).getIdComisionEvaluadora());
        } else {
            msg = new FacesMessage("No se encontro Comision Evaluadora", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        obtenerComisionEvaluadoras();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String crearComisionEvaluadora() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        respuesta = getImplementComisionEvaluadoraBO().crearComisionEvaluadora(this);
        obtenerComisionEvaluadoras();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La comision evaluadora para el rango de fechas" + getFechaInicio()+" - " +getFechaFin()+ " Ya existe");
        }
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "La comision evaluadora a sido creado");
            this.idComisionEvaluadora = 0;
            this.idComisionEvaluadoraEvaluar = -1;
            this.nombre="nombre carrera";
            this.fechaInicio = new Date();
            this.fechaFin = new Date();
            this.gestion=fechaInicio.getYear();
            this.estado = "estado";
            this.carreraComisionEvaluadoraEvaluar="null";
            this.facultadComisionEvaluadoraEvaluar="null";
            this.universidadComisionEvaluadoraEvaluar="null";
            this.nombreComisionEvaluadoraEvaluar="null";

            this.idCarreraBean=-1;
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    /*public String obtenerDirectorCarrera() {
        FacesMessage msg = null;
        System.out.println("Aquiii1 ");
        ComisionEvaluadoraBean directorCarreraBean = getImplementDirectorCarreraBO().obtenerDirectorCarreraByCi(this);
        System.out.println("Aquiii222 "+directorCarreraBean.getNombre());

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");
        this.setFechaInicio(directorCarreraBean.getFechaInicio());
        this.setFechaFin(directorCarreraBean.getFechaFin());
        this.setEstado(directorCarreraBean.getEstado());

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
    }*/

    /**
     * @return the idComisionEvaluadora
     */
    public long getIdComisionEvaluadora() {
        return idComisionEvaluadora;
    }

    /**
     * @param idComisionEvaluadora the idComisionEvaluadora to set
     */
    public void setIdComisionEvaluadora(long idComisionEvaluadora) {
        this.idComisionEvaluadora = idComisionEvaluadora;
    }

    /**
     * @return the comisionEvaluadoraBean
     */
    public ComisionEvaluadoraBean getComisionEvaluadoraBean() {
        return comisionEvaluadoraBean;
    }

    /**
     * @param comisionEvaluadoraBean the comisionEvaluadoraBean to set
     */
    public void setComisionEvaluadoraBean(ComisionEvaluadoraBean comisionEvaluadoraBean) {
        this.comisionEvaluadoraBean = comisionEvaluadoraBean;
    }

    /**
     * @return the idComisionEvaluadoraEvaluar
     */
    public long getIdComisionEvaluadoraEvaluar() {
        return idComisionEvaluadoraEvaluar;
    }

    /**
     * @param idComisionEvaluadoraEvaluar the idComisionEvaluadoraEvaluar to set
     */
    public void setIdComisionEvaluadoraEvaluar(long idComisionEvaluadoraEvaluar) {
        this.idComisionEvaluadoraEvaluar = idComisionEvaluadoraEvaluar;
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
     * @return the lista
     */
    public List<ComisionEvaluadoraBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<ComisionEvaluadoraBean> lista) {
        this.lista = lista;
    }

    /**
     * @return the implementComisionEvaluadoraBO
     */
    public IComisionEvaluadoraBO getImplementComisionEvaluadoraBO() {
        return implementComisionEvaluadoraBO;
    }

    /**
     * @param implementComisionEvaluadoraBO the implementComisionEvaluadoraBO to set
     */
    public void setImplementComisionEvaluadoraBO(IComisionEvaluadoraBO implementComisionEvaluadoraBO) {
        this.implementComisionEvaluadoraBO = implementComisionEvaluadoraBO;
    }

    /**
     * @return the comisionEvaluadoraBeanSeleccionado
     */
    public ComisionEvaluadoraBean getComisionEvaluadoraBeanSeleccionado() {
        return comisionEvaluadoraBeanSeleccionado;
    }

    /**
     * @param comisionEvaluadoraBeanSeleccionado the comisionEvaluadoraBeanSeleccionado to set
     */
    public void setComisionEvaluadoraBeanSeleccionado(ComisionEvaluadoraBean comisionEvaluadoraBeanSeleccionado) {
        this.comisionEvaluadoraBeanSeleccionado = comisionEvaluadoraBeanSeleccionado;
    }

    /**
     * @return the filtroComisionEvaluadoraBeans
     */
    public List<ComisionEvaluadoraBean> getFiltroComisionEvaluadoraBeans() {
        return filtroComisionEvaluadoraBeans;
    }

    /**
     * @param filtroComisionEvaluadoraBeans the filtroComisionEvaluadoraBeans to set
     */
    public void setFiltroComisionEvaluadoraBeans(List<ComisionEvaluadoraBean> filtroComisionEvaluadoraBeans) {
        this.filtroComisionEvaluadoraBeans = filtroComisionEvaluadoraBeans;
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

    /**
     * @return the idCarreraBean
     */
    public long getIdCarreraBean() {
        return idCarreraBean;
    }

    /**
     * @param idCarreraBean the idCarreraBean to set
     */
    public void setIdCarreraBean(long idCarreraBean) {
        this.idCarreraBean = idCarreraBean;
    }

    /**
     * @return the carreraComisionEvaluadoraEvaluar
     */
    public String getCarreraComisionEvaluadoraEvaluar() {
        return carreraComisionEvaluadoraEvaluar;
    }

    /**
     * @param carreraComisionEvaluadoraEvaluar the carreraComisionEvaluadoraEvaluar to set
     */
    public void setCarreraComisionEvaluadoraEvaluar(String carreraComisionEvaluadoraEvaluar) {
        this.carreraComisionEvaluadoraEvaluar = carreraComisionEvaluadoraEvaluar;
    }

    /**
     * @return the facultadComisionEvaluadoraEvaluar
     */
    public String getFacultadComisionEvaluadoraEvaluar() {
        return facultadComisionEvaluadoraEvaluar;
    }

    /**
     * @param facultadComisionEvaluadoraEvaluar the facultadComisionEvaluadoraEvaluar to set
     */
    public void setFacultadComisionEvaluadoraEvaluar(String facultadComisionEvaluadoraEvaluar) {
        this.facultadComisionEvaluadoraEvaluar = facultadComisionEvaluadoraEvaluar;
    }

    /**
     * @return the universidadComisionEvaluadoraEvaluar
     */
    public String getUniversidadComisionEvaluadoraEvaluar() {
        return universidadComisionEvaluadoraEvaluar;
    }

    /**
     * @param universidadComisionEvaluadoraEvaluar the universidadComisionEvaluadoraEvaluar to set
     */
    public void setUniversidadComisionEvaluadoraEvaluar(String universidadComisionEvaluadoraEvaluar) {
        this.universidadComisionEvaluadoraEvaluar = universidadComisionEvaluadoraEvaluar;
    }

    /**
     * @return the nombreComisionEvaluadoraEvaluar
     */
    public String getNombreComisionEvaluadoraEvaluar() {
        return nombreComisionEvaluadoraEvaluar;
    }

    /**
     * @param nombreComisionEvaluadoraEvaluar the nombreComisionEvaluadoraEvaluar to set
     */
    public void setNombreComisionEvaluadoraEvaluar(String nombreComisionEvaluadoraEvaluar) {
        this.nombreComisionEvaluadoraEvaluar = nombreComisionEvaluadoraEvaluar;
    }

    /**
     * @return the listaMiembroComisionEvaluadoraBeans
     */
    public List<MiembroComisionEvaluadoraBean> getListaMiembroComisionEvaluadoraBeans() {
        return listaMiembroComisionEvaluadoraBeans;
    }

    /**
     * @param listaMiembroComisionEvaluadoraBeans the listaMiembroComisionEvaluadoraBeans to set
     */
    public void setListaMiembroComisionEvaluadoraBeans(List<MiembroComisionEvaluadoraBean> listaMiembroComisionEvaluadoraBeans) {
        this.listaMiembroComisionEvaluadoraBeans = listaMiembroComisionEvaluadoraBeans;
    }

    /**
     * @return the miembroComisionEvaluadoraBean
     */
    public MiembroComisionEvaluadoraBean getMiembroComisionEvaluadoraBean() {
        return miembroComisionEvaluadoraBean;
    }

    /**
     * @param miembroComisionEvaluadoraBean the miembroComisionEvaluadoraBean to set
     */
    public void setMiembroComisionEvaluadoraBean(MiembroComisionEvaluadoraBean miembroComisionEvaluadoraBean) {
        this.miembroComisionEvaluadoraBean = miembroComisionEvaluadoraBean;
    }
    
    public void onEditMiembroComisionEvaluadoraBean(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        MiembroComisionEvaluadoraBean mceb=new MiembroComisionEvaluadoraBean();

        mceb.setIdMiembroComisionEvaluadora(((MiembroComisionEvaluadoraBean) event.getObject()).getIdMiembroComisionEvaluadora());
        mceb.setCi(((MiembroComisionEvaluadoraBean) event.getObject()).getCi());
        mceb.setIdComisionEvaluadoraBean(((MiembroComisionEvaluadoraBean) event.getObject()).getIdComisionEvaluadoraBean());
        DocenteBean db=new DirectorCarreraBean();
        db.setCi(((MiembroComisionEvaluadoraBean) event.getObject()).getDocenteBean().getCi() );
        db.setGradoAcademico(((MiembroComisionEvaluadoraBean) event.getObject()).getDocenteBean().getGradoAcademico() );
        db.setGradoAcademicoAbreviatura(((MiembroComisionEvaluadoraBean) event.getObject()).getDocenteBean().getGradoAcademicoAbreviatura() );
        db.setEstado(((MiembroComisionEvaluadoraBean) event.getObject()).getDocenteBean().getEstado() );
        db.setNombre(((MiembroComisionEvaluadoraBean) event.getObject()).getDocenteBean().getNombre() );
        db.setApp(((MiembroComisionEvaluadoraBean) event.getObject()).getDocenteBean().getApp() );
        db.setApm(((MiembroComisionEvaluadoraBean) event.getObject()).getDocenteBean().getApm() );
        db.setCuenta(((MiembroComisionEvaluadoraBean) event.getObject()).getDocenteBean().getCuenta() );
        db.setContrasenia(((MiembroComisionEvaluadoraBean) event.getObject()).getDocenteBean().getContrasenia() );
        mceb.setDocenteBean( db );
        mceb.setEstado(((MiembroComisionEvaluadoraBean) event.getObject()).getEstado());
        mceb.setFechaFin(((MiembroComisionEvaluadoraBean) event.getObject()).getFechaFin());
        mceb.setFechaInicio(((MiembroComisionEvaluadoraBean) event.getObject()).getFechaInicio());

        System.out.println("las alagruimas "+this.comisionEvaluadoraBeanSeleccionado.getIdComisionEvaluadora());
        respuesta = implementComisionEvaluadoraBO.modificarMiembroComisionEvaluadora(mceb);
        obtenerMiembroComisionEvaluadoraBeans();
        //System.out.println("1 " + getIdComisionEvaluadora());
        //System.out.println("2 " + getFechaFin());

        System.out.println("Resultado " + respuesta);
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Comision Evaluadota fue Modificado", ""+((MiembroComisionEvaluadoraBean) event.getObject()).getIdMiembroComisionEvaluadora());
        } else {
            msg = new FacesMessage("No se encontro Comision Evaluadora", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancelMiembroComisionEvaluadoraBean(RowEditEvent event) {
        obtenerComisionEvaluadoras();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void obtenerMiembroComisionEvaluadoraBeans(){
        System.out.println("podiendo "+this.comisionEvaluadoraBeanSeleccionado.getIdComisionEvaluadora());
        this.miembroComisionEvaluadoraBean.setIdComisionEvaluadoraBean( this.comisionEvaluadoraBeanSeleccionado.getIdComisionEvaluadora() );
        setListaMiembroComisionEvaluadoraBeans( implementComisionEvaluadoraBO.obtenerMiembroComisionEvaluadoras(this.comisionEvaluadoraBeanSeleccionado) );
    }
    
    public String obtenerDocente() {
        FacesMessage msg = null;
        DocenteBean docenteBean = new DocenteBean();
        docenteBean.setCi( this.miembroComisionEvaluadoraBean.getCi() );
        docenteBean=implementComisionEvaluadoraBO.obtenerDocenteByCi(docenteBean);

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");
        this.miembroComisionEvaluadoraBean.setDocenteBean(docenteBean);

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public void crearMiembroComisionEvaluadora(){
        Long respuesta=new Long(0);
        FacesMessage msg = null;
        //System.out.println("puesto "+this.miembroComisionEvaluadoraBean.getIdComisionEvaluadoraBean());
        respuesta=implementComisionEvaluadoraBO.crearMiembroComisionEvaluadora(this.miembroComisionEvaluadoraBean);

        if( respuesta.longValue()==1 ){
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creado Satisfactoriamente", "");
            obtenerMiembroComisionEvaluadoraBeans();
        }
        else{
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya existe un miembro de la comision evaluadora\npara el rango de fechas\nNo se cero nada", "");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}