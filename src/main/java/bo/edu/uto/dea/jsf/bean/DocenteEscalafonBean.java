/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IDocenteEscalafonBO;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
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
public class DocenteEscalafonBean implements Serializable{

    /**
     * @return the tipos
     */
    public static String[] getTipos() {
        return tipos;
    }

    private String ci;
    private DocenteBean docenteBean;
    private CarreraBean carreraBean;
    private Date fechaNacimiento;
    private String correoElectronico;
    private String tipo;
    
    private String nombre;
    private long idCarrera;
    private long gestion;
    private Map<Long, Long> listaGestion;
    
    private String selectTipoEv;
    private boolean selectTipoEvEdit;
           
    private List<DocenteEscalafonBean> mylista;
    private IDocenteEscalafonBO implementDocenteEscalafonBO;
    private DocenteEscalafonBean docenteEscalafonBeanSeleccionado;
    private List<DocenteEscalafonBean> filtroDocenteEscalafonBeans;
    
    private final static String[] tipos;
    private SelectItem[] tiposOptions;
    
    private List<RelacionDocenteMateriaBean> listRelacionDocenteMateriaBeans;
    
    private CartillaBean cartillaBeanActualizar;
    private EvaluacionEscalafonBean evaluacionEscalafonBeanActualizar;
    private EvaluacionEscalafonBean evaluacionEscalafonBeanActualizar2;
    
    private ResolucionBean resolucionBeanObtenido;
    private CartillaBean cartillaBeanObtenido;
    private List<ContenidoCartillaViewBean> contenidoCartillaBeansObtenido;
    private List<EscalafonBean> escalafonBeansObtenido;
    private EscalafonBean escalafonBeanObtenido;
    private EvaluacionEscalafonBean evaluacionEscalafonBeanObtenido;
    
    private CarreraBean cbObtenido;
    private FacultadBean fbObtenido;
    private UniversidadBean ubObtenido;
    

    public String getSelectTipoEv() {
        return selectTipoEv;
    }

    public void setSelectTipoEv(String selectTipoEv) {
        this.selectTipoEv = selectTipoEv;
    }

    public boolean isSelectTipoEvEdit() {
        return selectTipoEvEdit;
    }

    public void setSelectTipoEvEdit(boolean selectTipoEvEdit) {
        this.selectTipoEvEdit = selectTipoEvEdit;
    }
    
    public EvaluacionEscalafonBean getEvaluacionEscalafonBeanActualizar2() {
        return evaluacionEscalafonBeanActualizar2;
    }

    public void setEvaluacionEscalafonBeanActualizar2(EvaluacionEscalafonBean evaluacionEscalafonBeanActualizar2) {
        this.evaluacionEscalafonBeanActualizar2 = evaluacionEscalafonBeanActualizar2;
    }
    
    public CartillaBean getCartillaBeanActualizar() {
        return cartillaBeanActualizar;
    }

    public void setCartillaBeanActualizar(CartillaBean cartillaBeanActualizar) {
        this.cartillaBeanActualizar = cartillaBeanActualizar;
    }

    public EvaluacionEscalafonBean getEvaluacionEscalafonBeanActualizar() {
        return evaluacionEscalafonBeanActualizar;
    }

    public void setEvaluacionEscalafonBeanActualizar(EvaluacionEscalafonBean evaluacionEscalafonBeanActualizar) {
        this.evaluacionEscalafonBeanActualizar = evaluacionEscalafonBeanActualizar;
    }
    
    
    private String test;

    static {
        tipos = new String[2];
        tipos[0] = "DOCENTE";
        tipos[1] = "INVESTIGADOR";
    }

    public DocenteEscalafonBean() {
        this.ci="ci";
        this.fechaNacimiento=new Date();
        this.correoElectronico="email";
        this.tipo="tipo";

        this.nombre="";
        this.idCarrera=0;
        Calendar cal= Calendar.getInstance();
        this.gestion= cal.get(Calendar.YEAR) ;
        
        crearMapaListaGestion();
        
        tiposOptions=crearTiposOptions(tipos);
        
        this.cartillaBeanActualizar=new CartillaBean();
        this.evaluacionEscalafonBeanActualizar=new EvaluacionEscalafonBean();
        this.evaluacionEscalafonBeanActualizar2=new EvaluacionEscalafonBean();
        
        this.resolucionBeanObtenido=new ResolucionBean();
        
        this.selectTipoEv="";
        this.selectTipoEvEdit=false;
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
     * @return the mylista
     */
    public List<DocenteEscalafonBean> getMylista() {
        return mylista;
    }

    /**
     * @param mylista the mylista to set
     */
    public void setMylista(List<DocenteEscalafonBean> mylista) {
        this.mylista = mylista;
    }
    
    public void obtenerDocenteEscalafons() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        CarreraBean cb = (CarreraBean) (sessionMap.get("carreraBean"));
        FacesMessage msg = null;

        if (cb.getIdCarrera() != 0) {

            CarreraBean cb1 = new CarreraBean();
            cb1.setIdCarrera(cb.getIdCarrera());
            
            this.idCarrera=cb.getIdCarrera();
            
            setMylista(implementDocenteEscalafonBO.obtenerDocenteEscalafons(cb1) );

            msg = new FacesMessage("Cargando Docentes del Escalafon", "");
        } else {
            msg = new FacesMessage("Seleccione una Carrera", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        DocenteEscalafonBean docenteBean = new DocenteEscalafonBean();
        docenteBean.setCi(((DocenteEscalafonBean) event.getObject()).getCi());
        docenteBean.setFechaNacimiento(((DocenteEscalafonBean) event.getObject()).getFechaNacimiento());
        docenteBean.setCorreoElectronico(((DocenteEscalafonBean) event.getObject()).getCorreoElectronico());
        docenteBean.setTipo(((DocenteEscalafonBean) event.getObject()).getTipo());
        docenteBean.setCarreraBean(((DocenteEscalafonBean) event.getObject()).getCarreraBean());
        
        respuesta = implementDocenteEscalafonBO.modificarDocenteEscalafon(docenteBean);
        
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Docente Escalafon fue Modificado", "");
        } else {
            msg = new FacesMessage("No se encontro Docente", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String crearDocenteEscalafon() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        respuesta = getImplementDocenteEscalafonBO().crearDocenteEscalafon(this);
        obtenerDocenteEscalafons();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El docente con C.I:" + getCi() + " Ya existe");
        }
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "El docente a sido creado");
            this.ci="ci";
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public String obtenerDocente() {
        FacesMessage msg = null;
        DocenteBean db=new DocenteBean();
        db.setCi(this.ci);
        DocenteBean docenteBean = getImplementDocenteEscalafonBO().obtenerDocenteByCi(db);
        
        this.nombre=docenteBean.getNombreCompleto();

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    private SelectItem[] crearTiposOptions( String[] data ){
        SelectItem[] options=new SelectItem[data.length+1];
        options[0]=new SelectItem("", "Select");
        for( int i=0;i<data.length;i++ ){
            options[i+1]=new SelectItem(data[i],data[i]);
        }
        return options;
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
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the implementDocenteEscalafonBO
     */
    public IDocenteEscalafonBO getImplementDocenteEscalafonBO() {
        return implementDocenteEscalafonBO;
    }

    /**
     * @param implementDocenteEscalafonBO the implementDocenteEscalafonBO to set
     */
    public void setImplementDocenteEscalafonBO(IDocenteEscalafonBO implementDocenteEscalafonBO) {
        this.implementDocenteEscalafonBO = implementDocenteEscalafonBO;
    }

    /**
     * @return the docenteEscalafonBeanSeleccionado
     */
    public DocenteEscalafonBean getDocenteEscalafonBeanSeleccionado() {
        return docenteEscalafonBeanSeleccionado;
    }

    /**
     * @param docenteEscalafonBeanSeleccionado the docenteEscalafonBeanSeleccionado to set
     */
    public void setDocenteEscalafonBeanSeleccionado(DocenteEscalafonBean docenteEscalafonBeanSeleccionado) {
        this.docenteEscalafonBeanSeleccionado = docenteEscalafonBeanSeleccionado;
    }

    /**
     * @return the filtroDocenteEscalafonBeans
     */
    public List<DocenteEscalafonBean> getFiltroDocenteEscalafonBeans() {
        return filtroDocenteEscalafonBeans;
    }

    /**
     * @param filtroDocenteEscalafonBeans the filtroDocenteEscalafonBeans to set
     */
    public void setFiltroDocenteEscalafonBeans(List<DocenteEscalafonBean> filtroDocenteEscalafonBeans) {
        this.filtroDocenteEscalafonBeans = filtroDocenteEscalafonBeans;
    }

    /**
     * @return the tiposOptions
     */
    public SelectItem[] getTiposOptions() {
        return tiposOptions;
    }

    /**
     * @param tiposOptions the tiposOptions to set
     */
    public void setTiposOptions(SelectItem[] tiposOptions) {
        this.tiposOptions = tiposOptions;
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
     * @return the gestion
     */
    public long getGestion() {
        return gestion;
    }

    /**
     * @param gestion the gestion to set
     */
    public void setGestion(long gestion) {
        this.gestion = gestion;
    }

    /**
     * @return the listaGestion
     */
    public Map<Long, Long> getListaGestion() {
        return listaGestion;
    }

    /**
     * @param listaGestion the listaGestion to set
     */
    public void setListaGestion(Map<Long, Long> listaGestion) {
        this.listaGestion = listaGestion;
    }
    
    public void crearMapaListaGestion() {
        listaGestion=new LinkedHashMap<>();
        for(long i = gestion-20;i<=(gestion+20);i++ ){
            listaGestion.put(i, i);
        }
    }
    
    public void cargarMaterias(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        
        CartillaBean cartillaBean=new CartillaBean();
        cartillaBean.setCiDocenteEscalafon( this.docenteEscalafonBeanSeleccionado.getCi() );
        cartillaBean.setGestion(this.gestion+"" );
        this.cartillaBeanActualizar=cartillaBean;
        
        EvaluacionEscalafonBean eeb=new EvaluacionEscalafonBean();
        eeb.setCargo("");
        eeb.setLugar("");
        eeb.setNota(0);
        this.evaluacionEscalafonBeanActualizar=eeb;
        EvaluacionEscalafonBean eeb1=new EvaluacionEscalafonBean();
        eeb1.setCargo("");
        eeb1.setLugar("");
        eeb1.setNota(0);
        this.evaluacionEscalafonBeanActualizar2=eeb1;

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        CarreraBean cb = (CarreraBean) (sessionMap.get("carreraBean"));
        FacesMessage msg = new FacesMessage("Mensaje", "");
        
        System.out.println("KIIIII "+ this.docenteEscalafonBeanSeleccionado.getCi() );

        listRelacionDocenteMateriaBeans=implementDocenteEscalafonBO.obtenerRelacionDocenteMateriaCiGestion(this.docenteEscalafonBeanSeleccionado, this.gestion);
        
        this.resolucionBeanObtenido=implementDocenteEscalafonBO.obtener_resolucion_by_ci_gestion(this.cartillaBeanActualizar);
        
        this.cartillaBeanObtenido=implementDocenteEscalafonBO.obtener_cartilla_by_ci_gestion(this.cartillaBeanActualizar);
        
        this.contenidoCartillaBeansObtenido=implementDocenteEscalafonBO.Obtener_Contenido_Cartilla_Extendido1_Ci_Docente(this.cartillaBeanObtenido);
        
        this.escalafonBeansObtenido=implementDocenteEscalafonBO.Obtener_Escalafon_by_Ci_gestion(this.cartillaBeanActualizar);
        
        this.escalafonBeanObtenido=implementDocenteEscalafonBO.Obtener_Escalafon_by_id_cartilla(this.cartillaBeanObtenido);
        
        this.evaluacionEscalafonBeanObtenido= implementDocenteEscalafonBO.Obtener_EvaluacionEscalafon_by_id_cartilla(this.cartillaBeanObtenido);
        
        this.cbObtenido=implementDocenteEscalafonBO.obtener_Carrera_by_ci(this.cartillaBeanObtenido);
        this.fbObtenido=implementDocenteEscalafonBO.obtener_Facultad_by_ci(this.cartillaBeanObtenido);
        this.ubObtenido=implementDocenteEscalafonBO.obtener_Universidad_by_ci(this.cartillaBeanObtenido);

        //cartilla
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the listRelacionDocenteMateriaBeans
     */
    public List<RelacionDocenteMateriaBean> getListRelacionDocenteMateriaBeans() {
        return listRelacionDocenteMateriaBeans;
    }

    /**
     * @param listRelacionDocenteMateriaBeans the listRelacionDocenteMateriaBeans to set
     */
    public void setListRelacionDocenteMateriaBeans(List<RelacionDocenteMateriaBean> listRelacionDocenteMateriaBeans) {
        this.listRelacionDocenteMateriaBeans = listRelacionDocenteMateriaBeans;
    }
    
    public void onEditNota(RowEditEvent event) {
        /*FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        EvaluacionBean eb=new EvaluacionBean();
        eb.setIdEvaluacion(((RelacionDocenteMateriaBean) event.getObject()).getEvaluacionBean().getIdEvaluacion());
        eb.setNota(((RelacionDocenteMateriaBean) event.getObject()).getEvaluacionBean().getNota());
        
        
        respuesta = implementDocenteEscalafonBO.actualizarNotaEvaluacion(eb);
        
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Nota Actualizada", "");
        } else {
            msg = new FacesMessage("No se encontro evaluacion", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCancelNota(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the test
     */
    public String getTest() {
        return test;
    }

    /**
     * @param test the test to set
     */
    public void setTest(String test) {
        this.test = test;
    }
    
    public String Actualizar_Cartilla_Ev_Docente(  ){
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        
        respuesta = implementDocenteEscalafonBO.Actualizar_Cartilla_Ev_Docente(this.cartillaBeanActualizar);
        if (respuesta.longValue() == 1){
            msg = new FacesMessage("Actualizado", "");
        }
        else if (respuesta.longValue() == 0) {
            msg = new FacesMessage("Docente no esta en el escalafon", "");
        } 
        else if (respuesta.longValue() == -1){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o ya esta como Docente Titular", "");
        }
        else if (respuesta.longValue() == -2){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o esta como en fase de Ingreso", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    public String Actualizar_Cartilla_Ev_Contratado( ){
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        
        respuesta = implementDocenteEscalafonBO.Actualizar_Cartilla_Ev_Contratado(this.cartillaBeanActualizar);
        if (respuesta.longValue() == 1){
            msg = new FacesMessage("Actualizado", "");
        }
        else if (respuesta.longValue() == 0) {
            msg = new FacesMessage("Docente no esta en el escalafon", "");
        } 
        else if (respuesta.longValue() == -1){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o ya esta como Docente Titular", "");
        }
        else if (respuesta.longValue() == -2){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o esta como en fase de Ingreso", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    public String Actualizar_Cartilla_Ev_Ingreso( ){
        FacesMessage msg = new FacesMessage("Mensaje", "");;
        Long respuesta = new Long(0);
        
        respuesta = implementDocenteEscalafonBO.Actualizar_Cartilla_Ev_Ingreso(this.cartillaBeanActualizar);
        if (respuesta.longValue() == 1){
            msg = new FacesMessage("Actualizado", "");
        }
        else if (respuesta.longValue() == 0) {
            msg = new FacesMessage("Docente no esta en el escalafon", "");
        } 
        else if (respuesta.longValue() == -1){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o ya esta como Docente Titular", "");
        }
        else if (respuesta.longValue() == -2){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o esta como en fase de Ingreso", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    public String Actualizar_Cartilla_Ev_Emerito( ){
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        
        respuesta = implementDocenteEscalafonBO.Actualizar_Cartilla_Ev_Emerito(this.cartillaBeanActualizar);
        if (respuesta.longValue() == 1){
            msg = new FacesMessage("Actualizado", "");
        }
        else if (respuesta.longValue() == 0) {
            msg = new FacesMessage("Docente no esta en el escalafon", "");
        } 
        else if (respuesta.longValue() == -1){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o ya esta como Docente Titular", "");
        }
        else if (respuesta.longValue() == -2){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o esta como en fase de Ingreso", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    public String Actualizar_Cartilla_Ev_Fed_Rep_Nota(){
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        
        respuesta = implementDocenteEscalafonBO.Actualizar_Cartilla_Ev_Fed_Rep_Nota(this.cartillaBeanActualizar);
        if (respuesta.longValue() == 1){
            msg = new FacesMessage("Actualizado", "");
        }
        else if (respuesta.longValue() == 0) {
            msg = new FacesMessage("Docente no esta en el escalafon", "");
        } 
        else if (respuesta.longValue() == -1){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o ya esta como Docente Titular", "");
        }
        else if (respuesta.longValue() == -2){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o esta como en fase de Ingreso", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    public String Actualizar_Cartilla_Ev_Aut_Rep_Nota(){
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        
        respuesta = implementDocenteEscalafonBO.Actualizar_Cartilla_Ev_Aut_Rep_Nota(this.cartillaBeanActualizar);
        if (respuesta.longValue() == 1){
            msg = new FacesMessage("Actualizado", "");
        }
        else if (respuesta.longValue() == 0) {
            msg = new FacesMessage("Docente no esta en el escalafon", "");
        } 
        else if (respuesta.longValue() == -1){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o ya esta como Docente Titular", "");
        }
        else if (respuesta.longValue() == -2){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o esta como en fase de Ingreso", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }

    public String Actualizar_Cartilla_Ev_Fed_Car_Nota(){
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        
        respuesta = implementDocenteEscalafonBO.Actualizar_Cartilla_Ev_Fed_Car_Nota(this.cartillaBeanActualizar,this.evaluacionEscalafonBeanActualizar);
        if (respuesta.longValue() == 1){
            msg = new FacesMessage("Actualizado", "");
        }
        else if (respuesta.longValue() == 0) {
            msg = new FacesMessage("Docente no esta en el escalafon", "");
        } 
        else if (respuesta.longValue() == -1){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o ya esta como Docente Titular", "");
        }
        else if (respuesta.longValue() == -2){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o esta como en fase de Ingreso", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    public String Actualizar_Cartilla_Ev_Aut_Car_Nota( ){
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        
        respuesta = implementDocenteEscalafonBO.Actualizar_Cartilla_Ev_Aut_Car_Nota(this.cartillaBeanActualizar,this.evaluacionEscalafonBeanActualizar2);
        if (respuesta.longValue() == 1){
            msg = new FacesMessage("Actualizado", "");
        }
        else if (respuesta.longValue() == 0) {
            msg = new FacesMessage("Docente no esta en el escalafon", "");
        } 
        else if (respuesta.longValue() == -1){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o ya esta como Docente Titular", "");
        }
        else if (respuesta.longValue() == -2){
            msg = new FacesMessage("Es posible que Docente este en Escalafon o esta como en fase de Ingreso", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    public void evaluar( ){
        FacesMessage msg = new FacesMessage("EVA", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("xxx");
        if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Docente")==0){
            System.out.println("yyy");
            Actualizar_Cartilla_Ev_Docente();
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Contratado")==0){
            Actualizar_Cartilla_Ev_Contratado();
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Ingreso")==0){
            Actualizar_Cartilla_Ev_Ingreso();
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Emerito")==0){
            Actualizar_Cartilla_Ev_Emerito();
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Fed_Rep_Nota")==0){
            Actualizar_Cartilla_Ev_Fed_Rep_Nota();
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Aut_Rep_Nota")==0){
            Actualizar_Cartilla_Ev_Aut_Rep_Nota();
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Fed_Car_Nota")==0){
            Actualizar_Cartilla_Ev_Fed_Car_Nota();
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Aut_Car_Nota")==0){
            Actualizar_Cartilla_Ev_Aut_Car_Nota();
        }
        
        
    }
    
    public String cargarEE(){
        if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Docente")==0){
            selectTipoEvEdit=false;
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Contratado")==0){
            selectTipoEvEdit=false;
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Ingreso")==0){
            selectTipoEvEdit=false;
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Emerito")==0){
            selectTipoEvEdit=false;
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Fed_Rep_Nota")==0){
            selectTipoEvEdit=false;
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Aut_Rep_Nota")==0){
            selectTipoEvEdit=false;
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Fed_Car_Nota")==0){
            selectTipoEvEdit=true;
        }
        else if (selectTipoEv.compareTo("Actualizar_Cartilla_Ev_Aut_Car_Nota")==0){
            selectTipoEvEdit=true;
        }
        return "";
    }

    /**
     * @return the resolucionBeanObtenido
     */
    public ResolucionBean getResolucionBeanObtenido() {
        return resolucionBeanObtenido;
    }

    /**
     * @param resolucionBeanObtenido the resolucionBeanObtenido to set
     */
    public void setResolucionBeanObtenido(ResolucionBean resolucionBeanObtenido) {
        this.resolucionBeanObtenido = resolucionBeanObtenido;
    }
    
    public void actualizar_contenido_Resolucion() {
        FacesMessage msg = null;
        Long respuesta = new Long(0);      
        String m=this.resolucionBeanObtenido.getContenido();
        String r="";
        for( int i=0;i<m.length();i++ ){
            if( m.charAt(i)=='\'' ){
                r=r+"'";
            }
            r=r+m.charAt(i);
        }
        this.resolucionBeanObtenido.setContenido(r);
        
        respuesta = implementDocenteEscalafonBO.actualizar_contenido_Resolucion(this.resolucionBeanObtenido);
        
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Resolucion Actualizada", "");
        } else {
            msg = new FacesMessage("No se encontro resolucion", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the cartillaBeanObtenido
     */
    public CartillaBean getCartillaBeanObtenido() {
        return cartillaBeanObtenido;
    }

    /**
     * @param cartillaBeanObtenido the cartillaBeanObtenido to set
     */
    public void setCartillaBeanObtenido(CartillaBean cartillaBeanObtenido) {
        this.cartillaBeanObtenido = cartillaBeanObtenido;
    }

    /**
     * @return the contenidoCartillaBeansObtenido
     */
    public List<ContenidoCartillaViewBean> getContenidoCartillaBeansObtenido() {
        return contenidoCartillaBeansObtenido;
    }

    /**
     * @param contenidoCartillaBeansObtenido the contenidoCartillaBeansObtenido to set
     */
    public void setContenidoCartillaBeansObtenido(List<ContenidoCartillaViewBean> contenidoCartillaBeansObtenido) {
        this.contenidoCartillaBeansObtenido = contenidoCartillaBeansObtenido;
    }

    /**
     * @return the escalafonBeansObtenido
     */
    public List<EscalafonBean> getEscalafonBeansObtenido() {
        return escalafonBeansObtenido;
    }

    /**
     * @param escalafonBeansObtenido the escalafonBeansObtenido to set
     */
    public void setEscalafonBeansObtenido(List<EscalafonBean> escalafonBeansObtenido) {
        this.escalafonBeansObtenido = escalafonBeansObtenido;
    }

    /**
     * @return the escalafonBeanObtenido
     */
    public EscalafonBean getEscalafonBeanObtenido() {
        return escalafonBeanObtenido;
    }

    /**
     * @param escalafonBeanObtenido the escalafonBeanObtenido to set
     */
    public void setEscalafonBeanObtenido(EscalafonBean escalafonBeanObtenido) {
        this.escalafonBeanObtenido = escalafonBeanObtenido;
    }

    /**
     * @return the evaluacionEscalafonBeanObtenido
     */
    public EvaluacionEscalafonBean getEvaluacionEscalafonBeanObtenido() {
        return evaluacionEscalafonBeanObtenido;
    }

    /**
     * @param evaluacionEscalafonBeanObtenido the evaluacionEscalafonBeanObtenido to set
     */
    public void setEvaluacionEscalafonBeanObtenido(EvaluacionEscalafonBean evaluacionEscalafonBeanObtenido) {
        this.evaluacionEscalafonBeanObtenido = evaluacionEscalafonBeanObtenido;
    }

    /**
     * @return the cbObtenido
     */
    public CarreraBean getCbObtenido() {
        return cbObtenido;
    }

    /**
     * @param cbObtenido the cbObtenido to set
     */
    public void setCbObtenido(CarreraBean cbObtenido) {
        this.cbObtenido = cbObtenido;
    }

    /**
     * @return the fbObtenido
     */
    public FacultadBean getFbObtenido() {
        return fbObtenido;
    }

    /**
     * @param fbObtenido the fbObtenido to set
     */
    public void setFbObtenido(FacultadBean fbObtenido) {
        this.fbObtenido = fbObtenido;
    }

    /**
     * @return the ubObtenido
     */
    public UniversidadBean getUbObtenido() {
        return ubObtenido;
    }

    /**
     * @param ubObtenido the ubObtenido to set
     */
    public void setUbObtenido(UniversidadBean ubObtenido) {
        this.ubObtenido = ubObtenido;
    }
    
    public void Actualizar_Cartilla_Refresh(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        
        CartillaBean cartillaBean=new CartillaBean();
        cartillaBean.setCiDocenteEscalafon( this.docenteEscalafonBeanSeleccionado.getCi() );
        cartillaBean.setGestion(this.gestion+"" );
        this.cartillaBeanActualizar=cartillaBean;

        implementDocenteEscalafonBO.Actualizar_Cartilla_Refresh(this.cartillaBeanActualizar);
        
    }
}