/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IRelacionDocenteMateriaBO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class RelacionDocenteMateriaBean implements Serializable {

    /**
     * @return the tipoMaterias
     */
    public String[] getTipoMaterias() {
        return tipoMaterias;
    }

    /**
     * @return the items
     */
    public String[] getItems() {
        return items;
    }

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }

    /**
     * @return the tipoPeriodos
     */
    public String[] getTipoPeriodos() {
        return tipoPeriodos;
    }
    private long idRelacionDocenteMateria;
    private long idIdentificadorRegistroMateria;
    private MateriaBean materiaBean;
    private DocenteBean docenteBean;
    private EvaluacionBean evaluacionBean;
    private String paralelo;
    private String periodo;
    private String tipoPeriodo;
    private int gestion;
    private Date fechaInicio;
    private Date fechaFin;
    private int horas;
    private String item;
    private String tipoMateria;
    private String estado;
    
    private List<RelacionDocenteMateriaBean> lista;
    private List<MateriaBean> listaMaterias;
    private Map<String, Long> listaMateriasMap;
    private RelacionDocenteMateriaBean relacionDocenteMateriaBeanSeleccionado;
    private List<RelacionDocenteMateriaBean> filtroRelacionDocenteMateriaBeans;
    private IRelacionDocenteMateriaBO implementRelacionDocenteMateriaBO;
    
    private List<RelacionEstudianteMateriaBean> listaRelacionEstudianteMateriaBeans;
    private RelacionEstudianteMateriaBean relacionEstudianteMateriaBean;
    private EstudianteBean estudianteBean;
    

    private final static String[] estados;
    private final static String[] tipoPeriodos;
    private final static String[] items;
    private final static String[] tipoMaterias;
    private SelectItem[] estadosOptions;
    private SelectItem[] tipoMateriasOptions;
    private SelectItem[] tipoPeriodosOptions;
    private SelectItem[] itemsOptions;

    static {
        estados = new String[4];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "FINALIZADO";
        estados[3] = "ELIMINADO";
    }

    static {
        tipoPeriodos = new String[2];
        tipoPeriodos[0] = "SEMESTRE";
        tipoPeriodos[1] = "ANUAL";
    }

    static {
        items = new String[2];
        items[0] = "TITULAR";
        items[1] = "INTERINO";
    }

    static {
        tipoMaterias = new String[3];
        tipoMaterias[0] = "MATERIA";
        tipoMaterias[1] = "LABORATORIO";
        tipoMaterias[2] = "INVESTIGACION";
    }

    public RelacionDocenteMateriaBean() {
        estadosOptions = crearOptions(estados);
        tipoPeriodosOptions = crearOptions(tipoPeriodos);
        itemsOptions = crearOptions(items);
        tipoMateriasOptions = crearOptions(tipoMaterias);

        this.idRelacionDocenteMateria = 0;
        this.idIdentificadorRegistroMateria = 0;
        MateriaBean materiaBean = new MateriaBean();
        materiaBean.setIdMateria(0);
        this.materiaBean = materiaBean;
        DocenteBean docenteBean = new DocenteBean();
        docenteBean.setCi("ci");
        docenteBean.setNombreCompleto("nombre completo");
        this.docenteBean = docenteBean;
        EvaluacionBean evaluacionBean = new EvaluacionBean();
        evaluacionBean.setIdEvaluacion(0);
        evaluacionBean.setIdRelacionDocenteMateria(0);
        evaluacionBean.setNota(0.0);
        this.evaluacionBean = evaluacionBean;

        this.paralelo = "paralelo";
        this.periodo = "periodo";
        this.tipoPeriodo = tipoPeriodos[0];
        this.fechaInicio = new Date();
        this.fechaFin = new Date();

        this.gestion = 2014;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(df.parse(sdf.format(this.fechaInicio)));
            this.gestion = c.get(Calendar.YEAR);
        } catch (Exception e) {
            System.out.print("Exception " + e.toString());
        }

        this.horas = 1;
        this.item = items[0];
        this.tipoMateria = tipoMaterias[0];
        this.estado = "estado";


        
        this.estudianteBean=new EstudianteBean();
        this.estudianteBean.setCi("ci");
        this.estudianteBean.setEstado("estado");
        this.estudianteBean.setNombre("nombre");
        this.estudianteBean.setApp("app");
        this.estudianteBean.setApm("apm");
        this.estudianteBean.setCuenta("cuenta");
        this.estudianteBean.setContrasenia("password");
        this.estudianteBean.setNombreCompleto("nombre completo");
        
        this.relacionEstudianteMateriaBean=new RelacionEstudianteMateriaBean();
        this.relacionEstudianteMateriaBean.setEstudianteBean(this.estudianteBean);
        this.relacionEstudianteMateriaBean.setIdIdentificadorRegistroMateria(0);
        this.relacionEstudianteMateriaBean.setIdRelacionDocenteMateria(0);
        this.relacionEstudianteMateriaBean.setIdRelacionEstudianteMateria(0);
        
    }

    /**
     * @return the idRelacionDocenteMateria
     */
    public long getIdRelacionDocenteMateria() {
        return idRelacionDocenteMateria;
    }

    /**
     * @param idRelacionDocenteMateria the idRelacionDocenteMateria to set
     */
    public void setIdRelacionDocenteMateria(long idRelacionDocenteMateria) {
        this.idRelacionDocenteMateria = idRelacionDocenteMateria;
    }

    /**
     * @return the idIdentificadorRegistroMateria
     */
    public long getIdIdentificadorRegistroMateria() {
        return idIdentificadorRegistroMateria;
    }

    /**
     * @param idIdentificadorRegistroMateria the idIdentificadorRegistroMateria
     * to set
     */
    public void setIdIdentificadorRegistroMateria(long idIdentificadorRegistroMateria) {
        this.idIdentificadorRegistroMateria = idIdentificadorRegistroMateria;
    }

    /**
     * @return the materiaBean
     */
    public MateriaBean getMateriaBean() {
        return materiaBean;
    }

    /**
     * @param materiaBean the materiaBean to set
     */
    public void setMateriaBean(MateriaBean materiaBean) {
        this.materiaBean = materiaBean;
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
     * @return the paralelo
     */
    public String getParalelo() {
        return paralelo;
    }

    /**
     * @param paralelo the paralelo to set
     */
    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the tipoPeriodo
     */
    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    /**
     * @param tipoPeriodo the tipoPeriodo to set
     */
    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    /**
     * @return the gestion
     */
    public int getGestion() {
        return gestion;
    }

    /**
     * @param gestion the gestion to set
     */
    public void setGestion(int gestion) {
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
     * @return the horas
     */
    public int getHoras() {
        return horas;
    }

    /**
     * @param horas the horas to set
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the tipoMateria
     */
    public String getTipoMateria() {
        return tipoMateria;
    }

    /**
     * @param tipoMateria the tipoMateria to set
     */
    public void setTipoMateria(String tipoMateria) {
        this.tipoMateria = tipoMateria;
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
    public List<RelacionDocenteMateriaBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<RelacionDocenteMateriaBean> lista) {
        this.lista = lista;
    }

    /**
     * @return the implementRelacionDocenteMateriaBO
     */
    public IRelacionDocenteMateriaBO getImplementRelacionDocenteMateriaBO() {
        return implementRelacionDocenteMateriaBO;
    }

    /**
     * @param implementRelacionDocenteMateriaBO the
     * implementRelacionDocenteMateriaBO to set
     */
    public void setImplementRelacionDocenteMateriaBO(IRelacionDocenteMateriaBO implementRelacionDocenteMateriaBO) {
        this.implementRelacionDocenteMateriaBO = implementRelacionDocenteMateriaBO;
    }

    public void obtenerRelacionDocenteMaterias() {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, Object> sessionMap = externalContext.getSessionMap();
        CarreraBean cb = (CarreraBean) (sessionMap.get("carreraBean"));
        FacesMessage msg = null;

        System.out.println("yPLOPL " + cb.getIdCarrera() + " " + cb.getSiglaCarrera() + " " + cb.getNombre());

        if (cb.getIdCarrera() != 0) {

            CarreraBean cb1 = new CarreraBean();
            cb1.setIdCarrera(cb.getIdCarrera());
            //implementMateriaBO.obtenerMaterias(carreraBean);
            setLista(implementRelacionDocenteMateriaBO.obtenerRelacionDocenteMaterias(cb1));
            setListaMaterias(implementRelacionDocenteMateriaBO.obtenerMaterias(cb1));
            mapearListaMaterias();

            msg = new FacesMessage("Cargando Materias", "");
        } else {
            msg = new FacesMessage("Seleccione una Carrera", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);

        setIdRelacionDocenteMateria(((RelacionDocenteMateriaBean) event.getObject()).getIdRelacionDocenteMateria());
        setParalelo(((RelacionDocenteMateriaBean) event.getObject()).getParalelo());
        setPeriodo(((RelacionDocenteMateriaBean) event.getObject()).getPeriodo());
        setTipoPeriodo(((RelacionDocenteMateriaBean) event.getObject()).getTipoPeriodo());
        setGestion(((RelacionDocenteMateriaBean) event.getObject()).getGestion());
        setFechaInicio(((RelacionDocenteMateriaBean) event.getObject()).getFechaInicio());
        setFechaFin(((RelacionDocenteMateriaBean) event.getObject()).getFechaFin());
        setHoras(((RelacionDocenteMateriaBean) event.getObject()).getHoras());
        setItem(((RelacionDocenteMateriaBean) event.getObject()).getItem());
        setTipoMateria(((RelacionDocenteMateriaBean) event.getObject()).getTipoMateria());
        setEstado(((RelacionDocenteMateriaBean) event.getObject()).getEstado());
        this.docenteBean.setCi(((RelacionDocenteMateriaBean) event.getObject()).getDocenteBean().getCi());
        this.materiaBean.setIdMateria(((RelacionDocenteMateriaBean) event.getObject()).getMateriaBean().getIdMateria());

        respuesta = implementRelacionDocenteMateriaBO.modificarRelacionDocenteMateria(this);

        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Materia fue Modificado", "");
        } else {
            msg = new FacesMessage("No se encontro Materia", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Modificacion cancelada", ((RelacionDocenteMateriaBean) event.getObject()).getParalelo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the relacionDocenteMateriaBeanSeleccionado
     */
    public RelacionDocenteMateriaBean getRelacionDocenteMateriaBeanSeleccionado() {
        return relacionDocenteMateriaBeanSeleccionado;
    }

    /**
     * @param relacionDocenteMateriaBeanSeleccionado the
     * relacionDocenteMateriaBeanSeleccionado to set
     */
    public void setRelacionDocenteMateriaBeanSeleccionado(RelacionDocenteMateriaBean relacionDocenteMateriaBeanSeleccionado) {
        this.relacionDocenteMateriaBeanSeleccionado = relacionDocenteMateriaBeanSeleccionado;
    }

    /**
     * @return the filtroRelacionDocenteMateriaBeans
     */
    public List<RelacionDocenteMateriaBean> getFiltroRelacionDocenteMateriaBeans() {
        return filtroRelacionDocenteMateriaBeans;
    }

    /**
     * @param filtroRelacionDocenteMateriaBeans the
     * filtroRelacionDocenteMateriaBeans to set
     */
    public void setFiltroRelacionDocenteMateriaBeans(List<RelacionDocenteMateriaBean> filtroRelacionDocenteMateriaBeans) {
        this.filtroRelacionDocenteMateriaBeans = filtroRelacionDocenteMateriaBeans;
    }

    /**
     * @return the tipoPeriodosOptions
     */
    public SelectItem[] getTipoPeriodosOptions() {
        return tipoPeriodosOptions;
    }

    /**
     * @param tipoPeriodosOptions the tipoPeriodosOptions to set
     */
    public void setTipoPeriodosOptions(SelectItem[] tipoPeriodosOptions) {
        this.setTipoPeriodosOptions(tipoPeriodosOptions);
    }

    private SelectItem[] crearOptions(String[] data) {
        SelectItem[] options = new SelectItem[data.length + 1];
        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < data.length; i++) {
            options[i + 1] = new SelectItem(data[i], data[i]);
        }
        return options;
    }

    /**
     * @return the itemsOptions
     */
    public SelectItem[] getItemsOptions() {
        return itemsOptions;
    }

    /**
     * @param itemsOptions the itemsOptions to set
     */
    public void setItemsOptions(SelectItem[] itemsOptions) {
        this.setItemsOptions(itemsOptions);
    }

    /**
     * @return the tipoMateriasOptions
     */
    public SelectItem[] getTipoMateriasOptions() {
        return tipoMateriasOptions;
    }

    /**
     * @param tipoMateriasOptions the tipoMateriasOptions to set
     */
    public void setTipoMateriasOptions(SelectItem[] tipoMateriasOptions) {
        this.setTipoMateriasOptions(tipoMateriasOptions);
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
        this.setEstadosOptions(estadosOptions);
    }

    /**
     * @return the listaMaterias
     */
    public List<MateriaBean> getListaMaterias() {
        return listaMaterias;
    }

    /**
     * @param listaMaterias the listaMaterias to set
     */
    public void setListaMaterias(List<MateriaBean> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public void mapearListaMaterias() {
        this.listaMateriasMap = new LinkedHashMap<>();
        /*setListaMateriasMap( new LinkedHashMap<>() );*/
        for (MateriaBean obj : getListaMaterias()) {
            getListaMateriasMap().put(obj.getNombre(), obj.getIdMateria());
        }
    }

    public String obtenerDocente() {
        FacesMessage msg = null;
        DocenteBean docenteBean = getImplementRelacionDocenteMateriaBO().obtenerDocenteByCi(this.docenteBean);

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");
        this.docenteBean.setGradoAcademico(docenteBean.getGradoAcademico());
        this.docenteBean.setGradoAcademicoAbreviatura(docenteBean.getGradoAcademicoAbreviatura());

        this.docenteBean.setEstado(docenteBean.getEstado());
        this.docenteBean.setNombre(docenteBean.getNombre());
        this.docenteBean.setApp(docenteBean.getApp());
        this.docenteBean.setApm(docenteBean.getApm());
        this.docenteBean.setCuenta(docenteBean.getCuenta());
        this.docenteBean.setContrasenia(docenteBean.getContrasenia());
        this.docenteBean.setNombreCompleto(docenteBean.getNombreCompleto());

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public String obtenerEstudiante() {
        FacesMessage msg = null;
        EstudianteBean eb = getImplementRelacionDocenteMateriaBO().obtenerEstudianteByCi(this.estudianteBean);

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");

        this.estudianteBean.setEstado(eb.getEstado());
        this.estudianteBean.setNombre(eb.getNombre());
        this.estudianteBean.setApp(eb.getApp());
        this.estudianteBean.setApm(eb.getApm());
        this.estudianteBean.setCuenta(eb.getCuenta());
        this.estudianteBean.setContrasenia(eb.getContrasenia());
        this.estudianteBean.setNombreCompleto(eb.getNombreCompleto());
        
        this.relacionEstudianteMateriaBean.setEstudianteBean(this.estudianteBean);

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }

    /**
     * @return the listaMateriasMap
     */
    public Map<String, Long> getListaMateriasMap() {
        return listaMateriasMap;
    }

    /**
     * @param listaMateriasMap the listaMateriasMap to set
     */
    public void setListaMateriasMap(Map<String, Long> listaMateriasMap) {
        this.listaMateriasMap = listaMateriasMap;
    }

    public void crearRelacionDocenteMateria() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesMessage msg = null;

        Long respuesta = new Long(0);

        respuesta = getImplementRelacionDocenteMateriaBO().crearRelacionDocenteMateria(this);
        obtenerRelacionDocenteMaterias();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La relacion docente materia Ya existe, \nNo se ha Creado");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "La relacion docente materia fue creada --");
            this.docenteBean.setCi("ci");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void cargarRelacionEstudianteMateriaBeans() {
        //FacesMessage msg = new FacesMessage("Cargando Requisito Materia de ", " "+this.materiaSeleccionada.getIdMateria()+" "+this.materiaSeleccionada.getNombre() );
        setListaRelacionEstudianteMateriaBeans(implementRelacionDocenteMateriaBO.obtenerRelacionEstudianteMateriasByIdRelacionDocenteMateria(this.relacionDocenteMateriaBeanSeleccionado));
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    

    /**
     * @return the evaluacionBean
     */
    public EvaluacionBean getEvaluacionBean() {
        return evaluacionBean;
    }

    /**
     * @param evaluacionBean the evaluacionBean to set
     */
    public void setEvaluacionBean(EvaluacionBean evaluacionBean) {
        this.evaluacionBean = evaluacionBean;
    }

    /**
     * @return the listaRelacionEstudianteMateriaBeans
     */
    public List<RelacionEstudianteMateriaBean> getListaRelacionEstudianteMateriaBeans() {
        return listaRelacionEstudianteMateriaBeans;
    }

    /**
     * @param listaRelacionEstudianteMateriaBeans the
     * listaRelacionEstudianteMateriaBeans to set
     */
    public void setListaRelacionEstudianteMateriaBeans(List<RelacionEstudianteMateriaBean> listaRelacionEstudianteMateriaBeans) {
        this.listaRelacionEstudianteMateriaBeans = listaRelacionEstudianteMateriaBeans;
    }

    public void onCancelDocumento(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Modificacion cancelada",  ((DocumentoBean) event.getObject()).getIdDocumento() );
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the estudianteBean
     */
    public EstudianteBean getEstudianteBean() {
        return estudianteBean;
    }

    /**
     * @param estudianteBean the estudianteBean to set
     */
    public void setEstudianteBean(EstudianteBean estudianteBean) {
        this.estudianteBean = estudianteBean;
    }

    /**
     * @return the relacionEstudianteMateriaBean
     */
    public RelacionEstudianteMateriaBean getRelacionEstudianteMateriaBean() {
        return relacionEstudianteMateriaBean;
    }

    /**
     * @param relacionEstudianteMateriaBean the relacionEstudianteMateriaBean to set
     */
    public void setRelacionEstudianteMateriaBean(RelacionEstudianteMateriaBean relacionEstudianteMateriaBean) {
        this.relacionEstudianteMateriaBean = relacionEstudianteMateriaBean;
    }
    
    public void crearRelacionEstudianteMateria(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesMessage msg = null;

        Long respuesta = new Long(0);
        
        //System.out.println( "ttette whine up "+this.relacionEstudianteMateriaBean.getIdRelacionDocenteMateria() );
        //System.out.println( "qqttette whine up "+this.relacionEstudianteMateriaBean.getEstudianteBean().getCi() );
        
        respuesta = getImplementRelacionDocenteMateriaBO().crearRelacionEstudianteMateria(this.relacionEstudianteMateriaBean);
        //cargarEstudiantesDocumentos();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La relacion docente materia Ya existe, \nNo se ha Creado");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "La relacion docente materia fue creada --");
            this.docenteBean.setCi("ci");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void cargarEstudiantesDocumentos(){
    }
}
