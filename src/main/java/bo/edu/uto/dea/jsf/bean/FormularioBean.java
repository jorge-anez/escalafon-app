/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

//import antlr.CharBuffer;
import bo.edu.uto.dea.jhs.bo.IFormularioBO;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VIIII1
 */
public class FormularioBean implements Serializable {

    /**
     * @return the tipoContenedores
     */
    public String[] getTipoContenedores() {
        return tipoContenedores;
    }

    /**
     * @return the tipoDatos
     */
    public String[] getTipoDatos() {
        return tipoDatos;
    }

    /**
     * @return the categorias
     */
    public String[] getCategorias() {
        return categorias;
    }

    /**
     * @return the dirigidos
     */
    public String[] getDirigidos() {
        return dirigidos;
    }

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private long idFormulario;
    private String nombre;
    private String observaciones;
    private String categoria;
    private String dirigido;
    private boolean eliminado;
    private boolean aprobado;
    private String linkDisenio;
    private String linkVista;
    
    private List<EstructuraBean> estructuraBeans;
    private List<ContenidoBean> contenidoBeans;
    private ContenidoBean contenidoBeanAsignarCategoriaContenido;
    private long cantidad_estructuras;
    private long cantidad_contenidos;
    
    private List<FormularioBean> lista;
    private Map<String, Long> listaMap;
    
    private IFormularioBO implementFormularioBO;
    private FormularioBean formularioBeanSeleccionado;
    private List<FormularioBean> filtroFormularioBeans;
    
    private SelectItem[] estadosOptions;
    private SelectItem[] categoriasOptions;
    private SelectItem[] dirigidosOptions;
    
    private List<PairStringContenidoBean> pairStringContenidoBeans;
    private PairStringContenidoBean pairStringContenidoBeanSeleccionado;
    
    private String tabulador;
    private Integer creacion;
    
    private final static String[] estados;
    private final static String[] categorias;
    private final static String[] dirigidos;
    private final static String[] tipoDatos;
    private final static String[] tipoContenedores;

    static {
        estados = new String[3];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "ELIMINADO";
    }

    static {
        categorias = new String[2];
        categorias[0] = "FORMULARIO DE EVALUACION";
        categorias[1] = "FORMULARIO DE LLENADO";
    }

    static {
        dirigidos = new String[6];
        dirigidos[0] = "DOCENTE";
        dirigidos[1] = "DIRECTOR DE CARRERA";
        dirigidos[2] = "COMISION EVALUADORA";
        dirigidos[3] = "ESTUDIANTE";
        dirigidos[4] = "DIRECTOR DEA";
        dirigidos[5] = "COORDINADOR DEA";
    }

    static {
        tipoDatos = new String[7];
        tipoDatos[0] = "Texto_Simple";
        tipoDatos[1] = "Texto_Enriquecido";
        tipoDatos[2] = "Numero Entero";
        tipoDatos[3] = "Numero Real";
        tipoDatos[4] = "Fecha";
        tipoDatos[5] = "Hora";
        tipoDatos[6] = "Verdadero_Falso";
    }

    static {
        tipoContenedores = new String[7];
        tipoContenedores[0] = "Texto";
        tipoContenedores[1] = "Pregunta";
        tipoContenedores[2] = "Columna";
        tipoContenedores[3] = "Opciones";
        tipoContenedores[4] = "Dependencia";
        tipoContenedores[5] = "Grupos_Repetidos";
        tipoContenedores[6] = "Contenedor";
    }

    //tipos de dato
    public FormularioBean() {
        this.idFormulario = 0;
        this.nombre = "formulario";
        this.observaciones = "observaciones";
        this.categoria = "categoria";
        this.dirigido = "DOCENTE";
        this.eliminado = false;
        this.aprobado = false;
        //this.tabulador = "&nbsp;&nbsp;&nbsp;";
        this.tabulador = " . . .";

        estadosOptions = crearEstadosOptions(estados);
        categoriasOptions = crearEstadosOptions(categorias);
        dirigidosOptions = crearEstadosOptions(dirigidos);

        creacion = 0;
    }

    private SelectItem[] crearEstadosOptions(String[] data) {
        SelectItem[] options = new SelectItem[data.length + 1];
        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < data.length; i++) {
            options[i + 1] = new SelectItem(data[i], data[i]);
        }
        return options;
    }

    public void obtenerFormularios() {
        /*if (creacion == 0) {
         ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
         Map<String, Object> sessionMap = externalContext.getSessionMap();
         sessionMap.put("implementFormularioBO", this.ImplementFormularioBO);
         }*/

        setLista(getImplementFormularioBO().obtenerFormularios());
        mapearLista();
    }

    public String crearFormulario() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        respuesta = getImplementFormularioBO().crearFormulario(this);
        obtenerFormularios();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El formulario con nombre " + getNombre() + " Ya existe");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "El formulario fue creado");
            this.idFormulario = 0;
            this.nombre = "formulario";
            this.observaciones = "observaciones";
            this.categoria = "categoria";
            this.dirigido = "DOCENTE";
            this.eliminado = false;
            this.aprobado = false;
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        setIdFormulario(((FormularioBean) event.getObject()).getIdFormulario());
        setNombre(((FormularioBean) event.getObject()).getNombre());
        setObservaciones(((FormularioBean) event.getObject()).getObservaciones());
        setCategoria(((FormularioBean) event.getObject()).getCategoria());
        setDirigido(((FormularioBean) event.getObject()).getDirigido());
        setEliminado(((FormularioBean) event.getObject()).isEliminado());
        setAprobado(((FormularioBean) event.getObject()).isAprobado());

        respuesta = getImplementFormularioBO().modificarFormulario(this);
        obtenerFormularios();
        System.out.println("2 " + getNombre());
        System.out.println("Resultado " + respuesta);
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Formulario fue Modificado", ((FormularioBean) event.getObject()).getNombre());
        } else {
            msg = new FacesMessage("No se encontro Formulario", "");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        obtenerFormularios();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", ((FormularioBean) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void mapearLista() {
        this.listaMap = new LinkedHashMap<>();
        for (FormularioBean obj : getLista()) {
            getListaMap().put(obj.getNombre(), obj.getIdFormulario());
        }
    }

    /**
     * @return the idFormulario
     */
    public long getIdFormulario() {
        return idFormulario;
    }

    /**
     * @param idFormulario the idFormulario to set
     */
    public void setIdFormulario(long idFormulario) {
        this.idFormulario = idFormulario;
        this.linkDisenio = "DEA/Director_Dea/Formulario/DisenioFormulario.dea?id_formulario=" + idFormulario;
        this.linkVista = "DEA/Director_Dea/Formulario/VistaFormulario.dea?id_formulario=" + idFormulario;
        //private String linkVista;
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the dirigido
     */
    public String getDirigido() {
        return dirigido;
    }

    /**
     * @param dirigido the dirigido to set
     */
    public void setDirigido(String dirigido) {
        this.dirigido = dirigido;
    }

    /**
     * @return the eliminado
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * @return the aprobado
     */
    public boolean isAprobado() {
        return aprobado;
    }

    /**
     * @param aprobado the aprobado to set
     */
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    /**
     * @return the lista
     */
    public List<FormularioBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<FormularioBean> lista) {
        this.lista = lista;
    }

    /**
     * @return the formularioBeanSeleccionado
     */
    public FormularioBean getFormularioBeanSeleccionado() {
        return formularioBeanSeleccionado;
    }

    /**
     * @param formularioBeanSeleccionado the formularioBeanSeleccionado to set
     */
    public void setFormularioBeanSeleccionado(FormularioBean formularioBeanSeleccionado) {
        this.formularioBeanSeleccionado = formularioBeanSeleccionado;
    }

    /**
     * @return the filtroFormularioBeans
     */
    public List<FormularioBean> getFiltroFormularioBeans() {
        return filtroFormularioBeans;
    }

    /**
     * @param filtroFormularioBeans the filtroFormularioBeans to set
     */
    public void setFiltroFormularioBeans(List<FormularioBean> filtroFormularioBeans) {
        this.filtroFormularioBeans = filtroFormularioBeans;
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
     * @return the categoriasOptions
     */
    public SelectItem[] getCategoriasOptions() {
        return categoriasOptions;
    }

    /**
     * @param categoriasOptions the categoriasOptions to set
     */
    public void setCategoriasOptions(SelectItem[] categoriasOptions) {
        this.categoriasOptions = categoriasOptions;
    }

    /**
     * @return the dirigidosOptions
     */
    public SelectItem[] getDirigidosOptions() {
        return dirigidosOptions;
    }

    /**
     * @param dirigidosOptions the dirigidosOptions to set
     */
    public void setDirigidosOptions(SelectItem[] dirigidosOptions) {
        this.dirigidosOptions = dirigidosOptions;
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

    public void obtenerFormularioSeleccionado() {
        System.out.println("this " + this.getIdFormulario());
        if (this.getFormularioBeanSeleccionado() == null) {
            System.out.println("null");
        } else {
            System.out.println("no null");
        }
        System.out.println("form seleccionado " + this.getFormularioBeanSeleccionado().getIdFormulario());
        if (this.getImplementFormularioBO() == null) {
            System.out.println("BO null");
        } else {
            System.out.println("BO no null");
        }
        setEstructuraBeans(getImplementFormularioBO().obtenerEstructurasByIdFormulario(this.getFormularioBeanSeleccionado()));

        setContenidoBeans(getImplementFormularioBO().obtenerContenidosByIdFormulario(this.getFormularioBeanSeleccionado()));
        //preparar para despleagr en pantalla

        //this.pairStringContenidoBeans = new ArrayList<>();
        //Imprimir_Estructura_Recursivo(estructuraBean.getIdEstructura());
    }

    public String Panel_Formula(String panel_formula) {
        String formula = "";
        /* **************** FILA 1****************************************** */
        formula = formula + "<input id=\"sin(\" type=\"button\" value=\"sin\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"cos(\" type=\"button\" value=\"cos\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"tan(\" type=\"button\" value=\"tan\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" > ";

        formula = formula + "<input id=\"7\" type=\"button\" value=\"7\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"8\" type=\"button\" value=\"8\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"9\" type=\"button\" value=\"9\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"^\" type=\"button\" value=\"^\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" ><br />";
        /* ********************************************************** */

        /* **************** FILA 2****************************************** */
        formula = formula + "<input id=\"asin(\" type=\"button\" value=\"asin\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"acos(\" type=\"button\" value=\"acos\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"atan(\" type=\"button\" value=\"atan\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" > ";

        formula = formula + "<input id=\"4\" type=\"button\" value=\"4\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"5\" type=\"button\" value=\"5\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"6\" type=\"button\" value=\"6\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"/\" type=\"button\" value=\"/\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" ><br />";
        /* ********************************************************** */

        /* **************** FILA 3****************************************** */
        formula = formula + "<input id=\"sinh(\" type=\"button\" value=\"sinh\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"cosh(\" type=\"button\" value=\"cosh\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"tanh(\" type=\"button\" value=\"tanh\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" > ";

        formula = formula + "<input id=\"1\" type=\"button\" value=\"1\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"2\" type=\"button\" value=\"2\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"3\" type=\"button\" value=\"3\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"*\" type=\"button\" value=\"*\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" ><br />";
        /* ********************************************************** */

        /* **************** FILA 4****************************************** */
        formula = formula + "<input id=\"ln(\" type=\"button\" value=\"ln\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"log(\" type=\"button\" value=\"log\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"exp(\" type=\"button\" value=\"exp\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" > ";

        formula = formula + "<input id=\"0\" type=\"button\" value=\"0\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\".\" type=\"button\" value=\".\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"+\" type=\"button\" value=\"+\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"-\" type=\"button\" value=\"-\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" ><br />";
        /* ********************************************************** */

        /* **************** FILA 5****************************************** */
        formula = formula + "<input id=\">=\" type=\"button\" value=\">=\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"<=\" type=\"button\" value=\"<=\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"<>\" type=\"button\" value=\"<>\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" > ";

        formula = formula + "<input id=\"(\" type=\"button\" value=\"(\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\")\" type=\"button\" value=\")\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"\"\" type=\"button\" value=\"\"\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"'\" type=\"button\" value=\"'\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" ><br />";
        /* ********************************************************** */

        /* **************** FILA 6****************************************** */
        formula = formula + "<input id=\">\" type=\"button\" value=\">\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"<\" type=\"button\" value=\"<\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"=\" type=\"button\" value=\"=\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" > ";

        formula = formula + "<input id=\"&\" type=\"button\" value=\"&\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"|\" type=\"button\" value=\"|\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"!\" type=\"button\" value=\"!\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\":\" type=\"button\" value=\":\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" ><br />";
        /* ********************************************************** */

        /* **************** FILA 7****************************************** */
        formula = formula + "<input id=\"raiz(\" type=\"button\" value=\"raiz\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"\" type=\"button\" value=\"\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" >";
        formula = formula + "<input id=\"\" type=\"button\" value=\"\" class=\"" + panel_formula + "\" style=\"width: 40px; height: 20px;\" > ";

        formula = formula + "<input id=\"\" type=\"button\" value=\"\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"\" type=\"button\" value=\"\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"X\" type=\"button\" value=\"X\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        formula = formula + "<input id=\"C\" type=\"button\" value=\"C\" class=\"" + panel_formula + "\" style=\"width: 20px; height: 20px;\" >";
        /* ********************************************************** */
        return formula;
    }

    public List<String> Imprimir_Estructura() {
        if (this.estructuraBeans != null) {
            this.setCantidad_estructuras((long) new Long(estructuraBeans.size()));
        } else {
            this.setCantidad_estructuras((long) new Long(0));
        }
        if (contenidoBeans != null) {
            this.setCantidad_contenidos((long) new Long(contenidoBeans.size()));
        } else {
            this.setCantidad_contenidos((long) new Long(0));
        }
        EstructuraBean estructuraBean = new EstructuraBean();
        Integer j = 0;
        for (j = 0; j < cantidad_estructuras; j++) {
            estructuraBean = estructuraBeans.get(j);
            if ((estructuraBean.getIdEstructura() == estructuraBean.getIdEstructuraPredecesor()) && (estructuraBean.getIdEstructura() == estructuraBean.getIdEstructuraPadre())) {
                break;
            }
        }
        return Imprimir_Estructura_Recursivo(estructuraBean.getIdEstructura());
    }

    public List<String> Imprimir_Estructura_Recursivo(long Id_Estructura) {
        List<String> estructura_imprimir = new ArrayList<String>();
        List<String> estructura_imprimir2 = new ArrayList<String>();
        List<String> estructura_imprimir3 = new ArrayList<String>();
        Iterator<String> it;
        EstructuraBean estructura = new EstructuraBean();
        ContenidoBean contenido = new ContenidoBean();
        ContenidoBean contenido2 = new ContenidoBean();
        //Contenido contenido3 = new Contenido();
        long Siguiente_Hermano = new Long(0);
        Integer i = 0;
        Integer j = 0;
        String str = "";
        for (i = 0; i < cantidad_estructuras; i++) {
            estructura = estructuraBeans.get(i);
            if (estructura.getIdEstructura() == Id_Estructura) {
                break;
            }
        }
        estructura_imprimir.add("<div id=\"M-" + estructura.getIdEstructura() + "-" + estructura.getTipo() + "\" class=\"Marco_General\">");
        //estructura_imprimir.add("<div id=\"Contenedor\" class=\"Marco_Editor_Principal\">" + estructura.getIdEstructura());
        estructura_imprimir.add("<div id=\"Contenedor\" class=\"Marco_Editor_Principal\">" + estructura.getIdEstructura() + " " + estructura.getTipo());

        if (estructura.getTipo().compareTo(tipoContenedores[0]) == 0) {
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if (contenido.getIdEstructura() == estructura.getIdEstructura()) {
                    estructura_imprimir.add("<div id=\"C-" + contenido.getIdContenido() + "-" + estructura.getTipo() + "\" >");
                    // Decode UTF-8                    
                    Charset utf8charset = Charset.forName("UTF-8");
                    Charset iso88591charset = Charset.forName("ISO-8859-1");

                    ByteBuffer bb = ByteBuffer.wrap(contenido.getPregunta().getBytes());
                    CharBuffer data = utf8charset.decode(bb);

                    // Encode ISO-8559-1
                    ByteBuffer outputBuffer = iso88591charset.encode(data);
                    byte[] outputData = outputBuffer.array();

                    String datos = new String(outputData);

                    //estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + contenido.getPregunta() + "</textarea>");
                    estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + datos + "</textarea>");
                    //---
                    //estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + contenido.getPregunta() + "</textarea>");
                    estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
                    estructura_imprimir.add("</div>");
                    break;
                }
            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[1]) == 0) {
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if (contenido.getIdEstructura() == estructura.getIdEstructura()) {
                    estructura_imprimir.add("<div id=\"C-" + contenido.getIdContenido() + "-" + estructura.getTipo() + "\" >");
                    estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + contenido.getPregunta() + "</textarea>");
                    estructura_imprimir.add("Tipo Respuesta <select id=\"TD-" + contenido.getIdContenido() + "\" name=\"TD-" + contenido.getIdContenido() + "\">");
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[0] + "\" >" + tipoDatos[0] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[1] + "\" >" + tipoDatos[1] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[2] + "\" >" + tipoDatos[2] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[3] + "\" >" + tipoDatos[3] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[4] + "\" >" + tipoDatos[4] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[5] + "\" >" + tipoDatos[5] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[6] + "\" >" + tipoDatos[6] + "</option>");
                    }

                    estructura_imprimir.add("</select>&nbsp;&nbsp;&nbsp;&nbsp;<!--br /-->");
                    estructura_imprimir.add("Respuesta por Defecto <input id=\"VD-" + contenido.getIdContenido() + "\" name=\"VD-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getValorDefecto() + "\" style=\"width: 300px; height: 20px;\" > <br />");
                    estructura_imprimir.add("Nota por Defecto <input id=\"ND-" + contenido.getIdContenido() + "\" name=\"ND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaDefecto() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("Nota Maxima <input id=\"NM-" + contenido.getIdContenido() + "\" name=\"NM-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaMaxima() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <br />");

//                    estructura_imprimir.add("<div>");//--

                    estructura_imprimir.add("<div id=\"FORMULA_POND-" + contenido.getIdContenido() + "\" class=\"formula_ponderacion\" >");
                    estructura_imprimir.add("Formula Ponderacion <input id=\"POND-" + contenido.getIdContenido() + "\" name=\"POND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("<div id=\"" + contenido.getIdContenido() + "-formula_ponderacion\" style=\"display:none;\" >");
                    estructura_imprimir.add(Panel_Formula("panel_formula_ponderacion"));
                    estructura_imprimir.add("</div>");
                    estructura_imprimir.add("</div>");

                    estructura_imprimir.add("<div id=\"FORMULA_VR-" + contenido.getIdContenido() + "\" class=\"formula_verificacion_respuesta\" >");
                    estructura_imprimir.add("Formula Varificacion Respuesta <input id=\"FVR-" + contenido.getIdContenido() + "\" name=\"FVR-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getFormulaVerificacionRespuesta() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("<div id=\"" + contenido.getIdContenido() + "-formula_verificacion_respuesta\" style=\"display:none;\" >");
                    estructura_imprimir.add(Panel_Formula("panel_formula_verificacion_respuesta"));
                    estructura_imprimir.add("</div>");
                    estructura_imprimir.add("</div>");

                    estructura_imprimir.add("<a href=\"Asignar_Categoria.dea?id_contenido=" + contenido.getIdContenido() + "\" target=\"_blank\" >" + "Califica-" + contenido.getIdContenidoCalifica() + "</a>&nbsp;&nbsp;&nbsp;&nbsp; ");
                    estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");

                    estructura_imprimir.add("<div>");

                    estructura_imprimir.add("</div>");//est

                    estructura_imprimir.add("</div>");//est
                    break;
                }
            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[2]) == 0) {
            //JOptionPane.showMessageDialog(null, "columna");
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if ((contenido.getIdEstructura() == estructura.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add("<div id=\"C-" + contenido.getIdContenido() + "-" + estructura.getTipo() + "\" >");
                    estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + contenido.getPregunta() + "</textarea>");

                    estructura_imprimir.add("Tipo Respuesta <select id=\"TD-" + contenido.getIdContenido() + "\" name=\"TD-" + contenido.getIdContenido() + "\">");
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[0] + "\" >" + tipoDatos[0] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[1] + "\" >" + tipoDatos[1] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[2] + "\" >" + tipoDatos[2] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[3] + "\" >" + tipoDatos[3] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[4] + "\" >" + tipoDatos[4] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[5] + "\" >" + tipoDatos[5] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[6] + "\" >" + tipoDatos[6] + "</option>");
                    }

                    estructura_imprimir.add("</select>&nbsp;&nbsp;&nbsp;&nbsp;<!--br /-->");

                    estructura_imprimir.add("Respuesta por Defecto <input id=\"VD-" + contenido.getIdContenido() + "\" name=\"VD-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getValorDefecto() + "\" style=\"width: 300px; height: 20px;\" > <br />");
                    estructura_imprimir.add("Nota por Defecto <input id=\"ND-" + contenido.getIdContenido() + "\" name=\"ND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaDefecto() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("Nota Maxima <input id=\"NM-" + contenido.getIdContenido() + "\" name=\"NM-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaMaxima() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <br />");

                    estructura_imprimir.add("<div id=\"FORMULA_POND-" + contenido.getIdContenido() + "\" class=\"formula_ponderacion\" >");
                    estructura_imprimir.add("Formula Ponderacion <input id=\"POND-" + contenido.getIdContenido() + "\" name=\"POND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("<div id=\"" + contenido.getIdContenido() + "-formula_ponderacion\" style=\"display:none;\" >");
                    estructura_imprimir.add(Panel_Formula("panel_formula_ponderacion"));
                    estructura_imprimir.add("</div>");
                    estructura_imprimir.add("</div>");

                    estructura_imprimir.add("<div id=\"FORMULA_VR-" + contenido.getIdContenido() + "\" class=\"formula_verificacion_respuesta\" >");
                    estructura_imprimir.add("Formula Varificacion Respuesta <input id=\"FVR-" + contenido.getIdContenido() + "\" name=\"FVR-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getFormulaVerificacionRespuesta() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("<div id=\"" + contenido.getIdContenido() + "-formula_verificacion_respuesta\" style=\"display:none;\" >");
                    estructura_imprimir.add(Panel_Formula("panel_formula_verificacion_respuesta"));
                    estructura_imprimir.add("</div>");
                    estructura_imprimir.add("</div>");

                    estructura_imprimir.add("<a href=\"Asignar_Categoria.dea?id_contenido=" + contenido.getIdContenido() + "\" target=\"_blank\" >" + "Califica-" + contenido.getIdContenidoCalifica() + "</a>&nbsp;&nbsp;&nbsp;&nbsp; ");
                    estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
                    ///////////////////////////////////

                    //estructura_imprimir.add("<div>");
                    //estructura_imprimir.add("<div id=\"FORMULA_POND-" + contenido.getIdContenido() + "\" class=\"formula_ponderacion\" >Formula Ponderacion <input id=\"POND-" + contenido.getIdContenido() + "\" name=\"POND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    //estructura_imprimir.add("<div id=\"FORMULA_VR-" + contenido.getIdContenido() + "\" class=\"formula_verificacion_respuesta\" >Formula Varificacion Respuesta <input id=\"FVR-" + contenido.getIdContenido() + "\" name=\"FVR-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");

                    //estructura_imprimir.add("<a href=\"Asignar_Categoria.htm?id_contenido=" + contenido.getIdContenido() + "\">" + "Califica-" + contenido.getIdContenidoCalifica() + "</a>&nbsp;&nbsp;&nbsp;&nbsp; ");

                    //estructura_imprimir.add("</div>");

                    //estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >&nbsp;&nbsp;&nbsp;&nbsp; ");
                    estructura_imprimir.add("<input id=\"E-" + contenido.getIdContenido() + "\" name=\"E-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Eliminar\" class=\"eliminar_opcion\">&nbsp;&nbsp;&nbsp;&nbsp; ");

                    estructura_imprimir.add("</div>");

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < cantidad_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(cantidad_contenidos + "") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            estructura_imprimir.add("<div id=\"C-" + contenido.getIdContenido() + "-" + estructura.getTipo() + "\" >");

                            estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + contenido.getPregunta() + "</textarea>");

                            estructura_imprimir.add("Tipo Respuesta <select id=\"TD-" + contenido.getIdContenido() + "\" name=\"TD-" + contenido.getIdContenido() + "\">");
                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[0] + "\" >" + tipoDatos[0] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[1] + "\" >" + tipoDatos[1] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[2] + "\" >" + tipoDatos[2] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[3] + "\" >" + tipoDatos[3] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[4] + "\" >" + tipoDatos[4] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[5] + "\" >" + tipoDatos[5] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[6] + "\" >" + tipoDatos[6] + "</option>");
                            }

                            estructura_imprimir.add("</select>&nbsp;&nbsp;&nbsp;&nbsp;<!--br /-->");

                            estructura_imprimir.add("Respuesta por Defecto <input id=\"VD-" + contenido.getIdContenido() + "\" name=\"VD-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getValorDefecto() + "\" style=\"width: 300px; height: 20px;\" > <br />");
                            estructura_imprimir.add("Nota por Defecto <input id=\"ND-" + contenido.getIdContenido() + "\" name=\"ND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaDefecto() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                            estructura_imprimir.add("Nota Maxima <input id=\"NM-" + contenido.getIdContenido() + "\" name=\"NM-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaMaxima() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <br />");

                            estructura_imprimir.add("<div id=\"FORMULA_POND-" + contenido.getIdContenido() + "\" class=\"formula_ponderacion\" >");
                            estructura_imprimir.add("Formula Ponderacion <input id=\"POND-" + contenido.getIdContenido() + "\" name=\"POND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                            estructura_imprimir.add("<div id=\"" + contenido.getIdContenido() + "-formula_ponderacion\" style=\"display:none;\" >");
                            estructura_imprimir.add(Panel_Formula("panel_formula_ponderacion"));
                            estructura_imprimir.add("</div>");
                            estructura_imprimir.add("</div>");

                            estructura_imprimir.add("<div id=\"FORMULA_VR-" + contenido.getIdContenido() + "\" class=\"formula_verificacion_respuesta\" >");
                            estructura_imprimir.add("Formula Varificacion Respuesta <input id=\"FVR-" + contenido.getIdContenido() + "\" name=\"FVR-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getFormulaVerificacionRespuesta() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                            estructura_imprimir.add("<div id=\"" + contenido.getIdContenido() + "-formula_verificacion_respuesta\" style=\"display:none;\" >");
                            estructura_imprimir.add(Panel_Formula("panel_formula_verificacion_respuesta"));
                            estructura_imprimir.add("</div>");
                            estructura_imprimir.add("</div>");

                            estructura_imprimir.add("<a href=\"Asignar_Categoria.dea?id_contenido=" + contenido.getIdContenido() + "\" target=\"_blank\" >" + "Califica-" + contenido.getIdContenidoCalifica() + "</a>&nbsp;&nbsp;&nbsp;&nbsp; ");
                            estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
                            ///////////////////////////////////

                            //estructura_imprimir.add("<div>");
                            //estructura_imprimir.add("<div id=\"FORMULA_POND-" + contenido.getIdContenido() + "\" class=\"formula_ponderacion\" >Formula Ponderacion <input id=\"POND-" + contenido.getIdContenido() + "\" name=\"POND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                            //estructura_imprimir.add("<div id=\"FORMULA_VR-" + contenido.getIdContenido() + "\" class=\"formula_verificacion_respuesta\" >Formula Varificacion Respuesta <input id=\"FVR-" + contenido.getIdContenido() + "\" name=\"FVR-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");

                            //estructura_imprimir.add("<a href=\"Asignar_Categoria.htm?id_contenido=" + contenido.getIdContenido() + "\">" + "Califica-" + contenido.getIdContenidoCalifica() + "</a>&nbsp;&nbsp;&nbsp;&nbsp; ");

                            //estructura_imprimir.add("</div>");

                            //estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >&nbsp;&nbsp;&nbsp;&nbsp; ");
                            estructura_imprimir.add("<input id=\"E-" + contenido.getIdContenido() + "\" name=\"E-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Eliminar\" class=\"eliminar_opcion\">&nbsp;&nbsp;&nbsp;&nbsp; ");

                            estructura_imprimir.add("</div>");
                            //JOptionPane.showMessageDialog(null, "imprimir columna cual: " + contenido.getIdContenido());
                        }
                    }

                    break;
                }
                //JOptionPane.showMessageDialog(null, "col: "+contenido.getIdContenido());


            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[3]) == 0) {
            //JOptionPane.showMessageDialog(null, "opciones");
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if ((contenido.getIdEstructura() == estructura.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add("<div id=\"C-" + contenido.getIdContenido() + "-" + estructura.getTipo() + "\" >");

                    estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + contenido.getPregunta() + "</textarea>");

                    estructura_imprimir.add("Tipo Respuesta <select id=\"TD-" + contenido.getIdContenido() + "\" name=\"TD-" + contenido.getIdContenido() + "\">");
                    /*if (contenido.getTipoDato().compareTo("Texto_Simple") == 0) {
                     estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                     } else {
                     estructura_imprimir.add("<option value=\"" + "Texto_Simple" + "\" >" + "Texto_Simple" + "</option>");
                     }

                     if (contenido.getTipoDato().compareTo("Texto_Enriquecido") == 0) {
                     estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                     } else {
                     estructura_imprimir.add("<option value=\"" + "Texto_Enriquecido" + "\" >" + "Texto_Enriquecido" + "</option>");
                     }

                     if (contenido.getTipoDato().compareTo("Numero_Entero") == 0) {
                     estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                     } else {
                     estructura_imprimir.add("<option value=\"" + "Numero_Entero" + "\" >" + "Numero_Entero" + "</option>");
                     }

                     if (contenido.getTipoDato().compareTo("Numero_Real") == 0) {
                     estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                     } else {
                     estructura_imprimir.add("<option value=\"" + "Numero_Real" + "\" >" + "Numero_Real" + "</option>");
                     }

                     if (contenido.getTipoDato().compareTo("Fecha") == 0) {
                     estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                     } else {
                     estructura_imprimir.add("<option value=\"" + "Fecha" + "\" >" + "Fecha" + "</option>");
                     }

                     if (contenido.getTipoDato().compareTo("Hora") == 0) {
                     estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                     } else {
                     estructura_imprimir.add("<option value=\"" + "Hora" + "\" >" + "Hora" + "</option>");
                     }*/

                    if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[6] + "\" >" + tipoDatos[6] + "</option>");
                    }

                    estructura_imprimir.add("</select>&nbsp;&nbsp;&nbsp;&nbsp;<!--br /-->");

                    estructura_imprimir.add("Respuesta por Defecto T/F <input id=\"VD-" + contenido.getIdContenido() + "\" name=\"VD-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getValorDefecto() + "\" style=\"width: 300px; height: 20px;\" > <br />");
                    estructura_imprimir.add("Nota por Defecto <input id=\"ND-" + contenido.getIdContenido() + "\" name=\"ND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaDefecto() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("Nota Maxima <input id=\"NM-" + contenido.getIdContenido() + "\" name=\"NM-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaMaxima() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <br />");

                    estructura_imprimir.add("<div>");
                    estructura_imprimir.add("<div id=\"FORMULA_POND-" + contenido.getIdContenido() + "\" class=\"formula_ponderacion\" >Formula Ponderacion <input id=\"POND-" + contenido.getIdContenido() + "\" name=\"POND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("<div id=\"FORMULA_VR-" + contenido.getIdContenido() + "\" class=\"formula_verificacion_respuesta\" >Formula Varificacion Respuesta <input id=\"FVR-" + contenido.getIdContenido() + "\" name=\"FVR-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");

                    estructura_imprimir.add("<a href=\"Asignar_Categoria.dea?id_contenido=" + contenido.getIdContenido() + "\" target=\"_blank\" >" + "Califica-" + contenido.getIdContenidoCalifica() + "</a>&nbsp;&nbsp;&nbsp;&nbsp; ");

                    estructura_imprimir.add("</div>");

                    estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >&nbsp;&nbsp;&nbsp;&nbsp;");
                    estructura_imprimir.add("<input id=\"E-" + contenido.getIdContenido() + "\" name=\"E-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Eliminar\" class=\"eliminar_opcion\">&nbsp;&nbsp;&nbsp;&nbsp; ");
                    estructura_imprimir.add("</div>");

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < cantidad_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(cantidad_contenidos + "") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            estructura_imprimir.add("<div id=\"C-" + contenido.getIdContenido() + "-" + estructura.getTipo() + "\" >");
                            estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + contenido.getPregunta() + "</textarea>");

                            estructura_imprimir.add("Tipo Respuesta <select id=\"TD-" + contenido.getIdContenido() + "\" name=\"TD-" + contenido.getIdContenido() + "\">");
                            /*if (contenido.getTipoDato().compareTo("Texto_Simple") == 0) {
                             estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                             } else {
                             estructura_imprimir.add("<option value=\"" + "Texto_Simple" + "\" >" + "Texto_Simple" + "</option>");
                             }

                             if (contenido.getTipoDato().compareTo("Texto_Enriquecido") == 0) {
                             estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                             } else {
                             estructura_imprimir.add("<option value=\"" + "Texto_Enriquecido" + "\" >" + "Texto_Enriquecido" + "</option>");
                             }

                             if (contenido.getTipoDato().compareTo("Numero_Entero") == 0) {
                             estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                             } else {
                             estructura_imprimir.add("<option value=\"" + "Numero_Entero" + "\" >" + "Numero_Entero" + "</option>");
                             }

                             if (contenido.getTipoDato().compareTo("Numero_Real") == 0) {
                             estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                             } else {
                             estructura_imprimir.add("<option value=\"" + "Numero_Real" + "\" >" + "Numero_Real" + "</option>");
                             }

                             if (contenido.getTipoDato().compareTo("Fecha") == 0) {
                             estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                             } else {
                             estructura_imprimir.add("<option value=\"" + "Fecha" + "\" >" + "Fecha" + "</option>");
                             }

                             if (contenido.getTipoDato().compareTo("Hora") == 0) {
                             estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                             } else {
                             estructura_imprimir.add("<option value=\"" + "Hora" + "\" >" + "Hora" + "</option>");
                             }*/

                            if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[6] + "\" >" + tipoDatos[6] + "</option>");
                            }

                            estructura_imprimir.add("</select>&nbsp;&nbsp;&nbsp;&nbsp;<!--br /-->");

                            estructura_imprimir.add("Respuesta por Defecto T/F <input id=\"VD-" + contenido.getIdContenido() + "\" name=\"VD-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getValorDefecto() + "\" style=\"width: 300px; height: 20px;\" > <br />");
                            estructura_imprimir.add("Nota por Defecto <input id=\"ND-" + contenido.getIdContenido() + "\" name=\"ND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaDefecto() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                            estructura_imprimir.add("Nota Maxima <input id=\"NM-" + contenido.getIdContenido() + "\" name=\"NM-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaMaxima() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <br />");

                            estructura_imprimir.add("<div>");
                            estructura_imprimir.add("<div id=\"FORMULA_POND-" + contenido.getIdContenido() + "\" class=\"formula_ponderacion\" >Formula Ponderacion <input id=\"POND-" + contenido.getIdContenido() + "\" name=\"POND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                            estructura_imprimir.add("<div id=\"FORMULA_VR-" + contenido.getIdContenido() + "\" class=\"formula_verificacion_respuesta\" >Formula Varificacion Respuesta <input id=\"FVR-" + contenido.getIdContenido() + "\" name=\"FVR-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");

                            estructura_imprimir.add("<a href=\"Asignar_Categoria.dea?id_contenido=" + contenido.getIdContenido() + "\" target=\"_blank\" >" + "Califica-" + contenido.getIdContenidoCalifica() + "</a>&nbsp;&nbsp;&nbsp;&nbsp; ");
                            estructura_imprimir.add("</div>");

                            estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >&nbsp;&nbsp;&nbsp;&nbsp;");
                            estructura_imprimir.add("<input id=\"E-" + contenido.getIdContenido() + "\" name=\"E-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Eliminar\" class=\"eliminar_opcion\">&nbsp;&nbsp;&nbsp;&nbsp; ");
                            estructura_imprimir.add("</div>");
                            //JOptionPane.showMessageDialog(null, "imprimir opciones");
                        }
                    }
                    break;
                }


            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[4]) == 0) {
            //JOptionPane.showMessageDialog(null, "opciones");
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if ((contenido.getIdEstructura() == estructura.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add("<div id=\"C-" + contenido.getIdContenido() + "-" + estructura.getTipo() + "\" >");
                    estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + contenido.getPregunta() + "</textarea>");

                    estructura_imprimir.add("Tipo Respuesta <select id=\"TD-" + contenido.getIdContenido() + "\" name=\"TD-" + contenido.getIdContenido() + "\">");
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[0] + "\" >" + tipoDatos[0] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[1] + "\" >" + tipoDatos[1] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[2] + "\" >" + tipoDatos[2] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[3] + "\" >" + tipoDatos[3] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[4] + "\" >" + tipoDatos[4] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[5] + "\" >" + tipoDatos[5] + "</option>");
                    }

                    if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                    } else {
                        estructura_imprimir.add("<option value=\"" + tipoDatos[6] + "\" >" + tipoDatos[6] + "</option>");
                    }

                    estructura_imprimir.add("</select>&nbsp;&nbsp;&nbsp;&nbsp;<!--br /-->");

                    estructura_imprimir.add("Respuesta por Defecto <input id=\"VD-" + contenido.getIdContenido() + "\" name=\"VD-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getValorDefecto() + "\" style=\"width: 300px; height: 20px;\" > <br />");
                    estructura_imprimir.add("Nota por Defecto <input id=\"ND-" + contenido.getIdContenido() + "\" name=\"ND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaDefecto() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("Nota Maxima <input id=\"NM-" + contenido.getIdContenido() + "\" name=\"NM-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaMaxima() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <br />");

                    estructura_imprimir.add("<div>");
                    estructura_imprimir.add("<div id=\"FORMULA_POND-" + contenido.getIdContenido() + "\" class=\"formula_ponderacion\" >Formula Ponderacion <input id=\"POND-" + contenido.getIdContenido() + "\" name=\"POND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                    estructura_imprimir.add("<div id=\"FORMULA_VR-" + contenido.getIdContenido() + "\" class=\"formula_verificacion_respuesta\" >Formula Varificacion Respuesta <input id=\"FVR-" + contenido.getIdContenido() + "\" name=\"FVR-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");

                    estructura_imprimir.add("<a href=\"Asignar_Categoria.dea?id_contenido=" + contenido.getIdContenido() + "\" target=\"_blank\" >" + "Califica-" + contenido.getIdContenidoCalifica() + "</a>&nbsp;&nbsp;&nbsp;&nbsp; ");

                    estructura_imprimir.add("</div>");

                    estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >&nbsp;&nbsp;&nbsp;&nbsp;");
                    estructura_imprimir.add("<input id=\"E-" + contenido.getIdContenido() + "\" name=\"E-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Eliminar\" class=\"eliminar_opcion\">&nbsp;&nbsp;&nbsp;&nbsp; ");
                    estructura_imprimir.add("</div>");
                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < cantidad_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(cantidad_contenidos + "") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            //imprimir
                            estructura_imprimir.add("<div id=\"C-" + contenido.getIdContenido() + "-" + estructura.getTipo() + "\" >");
                            estructura_imprimir.add("<textarea id=\"P-" + contenido.getIdContenido() + "\" name=\"P-" + contenido.getIdContenido() + "\" style=\"width: 800px; height: 50px;\" >" + contenido.getPregunta() + "</textarea>");

                            estructura_imprimir.add("Tipo Respuesta <select id=\"TD-" + contenido.getIdContenido() + "\" name=\"TD-" + contenido.getIdContenido() + "\">");
                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[0] + "\" >" + tipoDatos[0] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[1] + "\" >" + tipoDatos[1] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[2] + "\" >" + tipoDatos[2] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[3] + "\" >" + tipoDatos[3] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[4] + "\" >" + tipoDatos[4] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[5] + "\" >" + tipoDatos[5] + "</option>");
                            }

                            if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                estructura_imprimir.add("<option value=\"" + contenido.getTipoDato() + "\" selected >" + contenido.getTipoDato() + "</option>");
                            } else {
                                estructura_imprimir.add("<option value=\"" + tipoDatos[6] + "\" >" + tipoDatos[6] + "</option>");
                            }

                            estructura_imprimir.add("</select>&nbsp;&nbsp;&nbsp;&nbsp;<!--br /-->");

                            estructura_imprimir.add("Respuesta por Defecto <input id=\"VD-" + contenido.getIdContenido() + "\" name=\"VD-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getValorDefecto() + "\" style=\"width: 300px; height: 20px;\" > <br />");
                            estructura_imprimir.add("Nota por Defecto <input id=\"ND-" + contenido.getIdContenido() + "\" name=\"ND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaDefecto() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                            estructura_imprimir.add("Nota Maxima <input id=\"NM-" + contenido.getIdContenido() + "\" name=\"NM-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getNotaMaxima() + "\" style=\"width: 50px; height: 20px;\" >&nbsp;&nbsp;&nbsp;&nbsp; <br />");

                            estructura_imprimir.add("<div>");
                            estructura_imprimir.add("<div id=\"FORMULA_POND-" + contenido.getIdContenido() + "\" class=\"formula_ponderacion\" >Formula Ponderacion <input id=\"POND-" + contenido.getIdContenido() + "\" name=\"POND-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");
                            estructura_imprimir.add("<div id=\"FORMULA_VR-" + contenido.getIdContenido() + "\" class=\"formula_verificacion_respuesta\" >Formula Varificacion Respuesta <input id=\"FVR-" + contenido.getIdContenido() + "\" name=\"FVR-" + contenido.getIdContenido() + "\" type=\"text\" value=\"" + contenido.getPonderacion() + "\" style=\"width: 50px; height: 20px;\" ></div>&nbsp;&nbsp;&nbsp;&nbsp; <!--br /-->");

                            estructura_imprimir.add("<a href=\"Asignar_Categoria.dea?id_contenido=" + contenido.getIdContenido() + "\" target=\"_blank\" >" + "Califica-" + contenido.getIdContenidoCalifica() + "</a>&nbsp;&nbsp;&nbsp;&nbsp; ");

                            estructura_imprimir.add("</div>");

                            estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" class=\"guardar\" alt=\"Guardar\" style=\"background-image:url('icons_Edition_Form/guardar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >&nbsp;&nbsp;&nbsp;&nbsp;");
                            estructura_imprimir.add("<input id=\"E-" + contenido.getIdContenido() + "\" name=\"E-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Eliminar\" class=\"eliminar_opcion\">&nbsp;&nbsp;&nbsp;&nbsp; ");
                            estructura_imprimir.add("</div>");
                            //JOptionPane.showMessageDialog(null, "imprimir dependencia: " + contenido.getIdContenido());
                        }
                    }
                    break;
                }


            }
        } //else if (estructura.getTipo().compareTo(tipoContenedores[5]) == 0) {

        //}

////////////////////
        if (estructura.getHijos().compareTo("") != 0) {
            StringTokenizer tokens = new StringTokenizer(estructura.getHijos(), "-");
            while (tokens.hasMoreTokens()) {
                str = tokens.nextToken();
                Long n = Long.valueOf(str);
                estructura_imprimir2 = Imprimir_Estructura_Recursivo(n);
                it = estructura_imprimir2.iterator();
                while (it.hasNext()) {
                    str = it.next();
                    estructura_imprimir.add(str);
                }
                estructura_imprimir2 = estructura_imprimir3;
                break;
            }
        }
        //

        estructura_imprimir.add("</div>");

        estructura_imprimir.add("<div id=\"M-" + estructura.getIdEstructura() + "-" + estructura.getTipo() + "-botones\" style=\"display:none;\" >");
        //JOptionPane.showMessageDialog(null, "aaa");
        if ((estructura.getTipo().compareTo(tipoContenedores[2]) == 0)) {
            estructura_imprimir.add("Insertar Nuevo (Adentro) <!--br /-->");
            estructura_imprimir.add("<input id=\"adicioncolumna_i\" name=\"adicioncolumna_i\" class=\"only\" type=\"button\" value=\"Agregar Columna\" >");
            estructura_imprimir.add("<!--br /--> ");

            estructura_imprimir.add("Agregar Nuevo (Abajo) <!--br /-->");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[6] + "_a\" name=\"" + tipoContenedores[6] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Agregar Contenedor\" style=\"background-image:url('icons_Edition_Form/contenedor.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[0] + "_a\" name=\"" + tipoContenedores[0] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Texto\"  style=\"background-image:url('icons_Edition_Form/texto.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[1] + "_a\" name=\"" + tipoContenedores[1] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Pregunta\"  style=\"background-image:url('icons_Edition_Form/pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[2] + "_a\" name=\"" + tipoContenedores[2] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Columna\"  style=\"background-image:url('icons_Edition_Form/columna.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[3] + "_a\" name=\"" + tipoContenedores[3] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Opciones\"  style=\"background-image:url('icons_Edition_Form/opciones.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[4] + "_a\" name=\"" + tipoContenedores[4] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Dependencia\"  style=\"background-image:url('icons_Edition_Form/dependencia.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[5] + "_a\" name=\"" + tipoContenedores[5] + "_a\" class=\"add_del_button\" type=\"button\" v=\"Grupo Preguntas Repetidos\"  style=\"background-image:url('icons_Edition_Form/grupo_pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"eliminar_a\" name=\"eliminar_a\" class=\"add_del_button\" type=\"button\" alt=\"Eliminar\"  style=\"background-image:url('icons_Edition_Form/eliminar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
        } else if ((estructura.getTipo().compareTo(tipoContenedores[3]) == 0)) {
            estructura_imprimir.add("Insertar Nuevo (Adentro) <!--br /-->");
            estructura_imprimir.add("<input id=\"adicionopcion_i\" name=\"adicionopcion_i\" class=\"only\" type=\"button\" value=\"Agregar Opcion\" >");
            estructura_imprimir.add("<!--br /--> ");

            estructura_imprimir.add("Agregar Nuevo (Abajo) <!--br /-->");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[6] + "_a\" name=\"" + tipoContenedores[6] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Agregar Contenedor\" style=\"background-image:url('icons_Edition_Form/contenedor.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[0] + "_a\" name=\"" + tipoContenedores[0] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Texto\"  style=\"background-image:url('icons_Edition_Form/texto.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[1] + "_a\" name=\"" + tipoContenedores[1] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Pregunta\"  style=\"background-image:url('icons_Edition_Form/pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[2] + "_a\" name=\"" + tipoContenedores[2] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Columna\"  style=\"background-image:url('icons_Edition_Form/columna.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[3] + "_a\" name=\"" + tipoContenedores[3] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Opciones\"  style=\"background-image:url('icons_Edition_Form/opciones.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[4] + "_a\" name=\"" + tipoContenedores[4] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Dependencia\"  style=\"background-image:url('icons_Edition_Form/dependencia.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[5] + "_a\" name=\"" + tipoContenedores[5] + "_a\" class=\"add_del_button\" type=\"button\" v=\"Grupo Preguntas Repetidos\"  style=\"background-image:url('icons_Edition_Form/grupo_pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"eliminar_a\" name=\"eliminar_a\" class=\"add_del_button\" type=\"button\" alt=\"Eliminar\"  style=\"background-image:url('icons_Edition_Form/eliminar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
        } else if ((estructura.getTipo().compareTo(tipoContenedores[4]) == 0)) {
            estructura_imprimir.add("Insertar Nuevo (Adentro) <!--br /-->");
            estructura_imprimir.add("<input id=\"adiciondependencia_i\" name=\"adiciondependencia_i\" class=\"only\" type=\"button\" value=\"Agregar Dependencia\" >");
            estructura_imprimir.add("<!--br /--> ");

            estructura_imprimir.add("Agregar Nuevo (Abajo) <!--br /-->");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[6] + "_a\" name=\"" + tipoContenedores[6] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Agregar Contenedor\" style=\"background-image:url('icons_Edition_Form/contenedor.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[0] + "_a\" name=\"" + tipoContenedores[0] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Texto\"  style=\"background-image:url('icons_Edition_Form/texto.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[1] + "_a\" name=\"" + tipoContenedores[1] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Pregunta\"  style=\"background-image:url('icons_Edition_Form/pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[2] + "_a\" name=\"" + tipoContenedores[2] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Columna\"  style=\"background-image:url('icons_Edition_Form/columna.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[3] + "_a\" name=\"" + tipoContenedores[3] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Opciones\"  style=\"background-image:url('icons_Edition_Form/opciones.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[4] + "_a\" name=\"" + tipoContenedores[4] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Dependencia\"  style=\"background-image:url('icons_Edition_Form/dependencia.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[5] + "_a\" name=\"" + tipoContenedores[5] + "_a\" class=\"add_del_button\" type=\"button\" v=\"Grupo Preguntas Repetidos\"  style=\"background-image:url('icons_Edition_Form/grupo_pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"eliminar_a\" name=\"eliminar_a\" class=\"add_del_button\" type=\"button\" alt=\"Eliminar\"  style=\"background-image:url('icons_Edition_Form/eliminar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
        } else if ((estructura.getTipo().compareTo(tipoContenedores[6]) == 0)
                || (estructura.getTipo().compareTo(tipoContenedores[0]) == 0)
                || (estructura.getTipo().compareTo(tipoContenedores[1]) == 0)
                || (estructura.getTipo().compareTo(tipoContenedores[5]) == 0)) {
            estructura_imprimir.add("Insertar Nuevo (Adentro) <!--br /-->");

            estructura_imprimir.add("<input id=\"" + tipoContenedores[6] + "_i\" name=\"" + tipoContenedores[6] + "_i\" class=\"add_del_button\" type=\"button\" alt=\"Agregar Contenedor\" style=\"background-image:url('icons_Edition_Form/contenedor.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[0] + "_i\" name=\"" + tipoContenedores[0] + "_i\" class=\"add_del_button\" type=\"button\" alt=\"Texto\"  style=\"background-image:url('icons_Edition_Form/texto.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[1] + "_i\" name=\"" + tipoContenedores[1] + "_i\" class=\"add_del_button\" type=\"button\" alt=\"Pregunta\"  style=\"background-image:url('icons_Edition_Form/pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[2] + "_i\" name=\"" + tipoContenedores[2] + "_i\" class=\"add_del_button\" type=\"button\" alt=\"Columna\"  style=\"background-image:url('icons_Edition_Form/columna.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[3] + "_i\" name=\"" + tipoContenedores[3] + "_i\" class=\"add_del_button\" type=\"button\" alt=\"Opciones\"  style=\"background-image:url('icons_Edition_Form/opciones.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[4] + "_i\" name=\"" + tipoContenedores[4] + "_i\" class=\"add_del_button\" type=\"button\" alt=\"Dependencia\"  style=\"background-image:url('icons_Edition_Form/dependencia.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[5] + "_i\" name=\"" + tipoContenedores[5] + "_i\" class=\"add_del_button\" type=\"button\" v=\"Grupo Preguntas Repetidos\"  style=\"background-image:url('icons_Edition_Form/grupo_pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"eliminar_i\" name=\"eliminar_i\" class=\"add_del_button\" type=\"button\" alt=\"Eliminar\"  style=\"background-image:url('icons_Edition_Form/eliminar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");

            estructura_imprimir.add("<!--br /--> ");
            estructura_imprimir.add("Agregar Nuevo (Abajo) <!--br /-->");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[6] + "_a\" name=\"" + tipoContenedores[6] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Agregar Contenedor\" style=\"background-image:url('icons_Edition_Form/contenedor.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[0] + "_a\" name=\"" + tipoContenedores[0] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Texto\"  style=\"background-image:url('icons_Edition_Form/texto.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[1] + "_a\" name=\"" + tipoContenedores[1] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Pregunta\"  style=\"background-image:url('icons_Edition_Form/pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[2] + "_a\" name=\"" + tipoContenedores[2] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Columna\"  style=\"background-image:url('icons_Edition_Form/columna.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[3] + "_a\" name=\"" + tipoContenedores[3] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Opciones\"  style=\"background-image:url('icons_Edition_Form/opciones.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[4] + "_a\" name=\"" + tipoContenedores[4] + "_a\" class=\"add_del_button\" type=\"button\" alt=\"Dependencia\"  style=\"background-image:url('icons_Edition_Form/dependencia.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"" + tipoContenedores[5] + "_a\" name=\"" + tipoContenedores[5] + "_a\" class=\"add_del_button\" type=\"button\" v=\"Grupo Preguntas Repetidos\"  style=\"background-image:url('icons_Edition_Form/grupo_pregunta.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
            estructura_imprimir.add("<input id=\"eliminar_a\" name=\"eliminar_a\" class=\"add_del_button\" type=\"button\" alt=\"Eliminar\"  style=\"background-image:url('icons_Edition_Form/eliminar.png'); background-repeat: no-repeat; height:20px; width:20px; background-position: center;\" >");
        }

        estructura_imprimir.add("</div>");

        estructura_imprimir.add("</div>");

        Siguiente_Hermano = estructura.getIdEstructura();
        for (i = 0; i < cantidad_estructuras; i++) {
            estructura = estructuraBeans.get(i);
            if (estructura.getIdEstructuraPredecesor() == Siguiente_Hermano && estructura.getIdEstructuraPredecesor() != estructura.getIdEstructura()) {
                break;
            }
        }
        if (i < cantidad_estructuras) {
            estructura_imprimir2 = Imprimir_Estructura_Recursivo(estructura.getIdEstructura());
            it = estructura_imprimir2.iterator();
            while (it.hasNext()) {
                str = it.next();
                estructura_imprimir.add(str);
            }
        }
        return estructura_imprimir;
    }

    /**
     * @return the estructuraBeans
     */
    public List<EstructuraBean> getEstructuraBeans() {
        return estructuraBeans;
    }

    /**
     * @param estructuraBeans the estructuraBeans to set
     */
    public void setEstructuraBeans(List<EstructuraBean> estructuraBeans) {
        this.estructuraBeans = estructuraBeans;
    }

    /**
     * @return the contenidoBeans
     */
    public List<ContenidoBean> getContenidoBeans() {
        return contenidoBeans;
    }

    /**
     * @param contenidoBeans the contenidoBeans to set
     */
    public void setContenidoBeans(List<ContenidoBean> contenidoBeans) {
        this.contenidoBeans = contenidoBeans;
    }

    /**
     * @return the pairStringContenidoBeans
     */
    public List<PairStringContenidoBean> getPairStringContenidoBeans() {
        return pairStringContenidoBeans;
    }

    /**
     * @param pairStringContenidoBeans the pairStringContenidoBeans to set
     */
    public void setPairStringContenidoBeans(List<PairStringContenidoBean> pairStringContenidoBeans) {
        this.pairStringContenidoBeans = pairStringContenidoBeans;
    }

    /**
     * @return the cantidad_estructuras
     */
    public long getCantidad_estructuras() {
        return cantidad_estructuras;
    }

    /**
     * @param cantidad_estructuras the cantidad_estructuras to set
     */
    public void setCantidad_estructuras(long cantidad_estructuras) {
        this.cantidad_estructuras = cantidad_estructuras;
    }

    /**
     * @return the cantidad_contenidos
     */
    public long getCantidad_contenidos() {
        return cantidad_contenidos;
    }

    /**
     * @param cantidad_contenidos the cantidad_contenidos to set
     */
    public void setCantidad_contenidos(long cantidad_contenidos) {
        this.cantidad_contenidos = cantidad_contenidos;
    }

    /**
     * @return the tabulador
     */
    public String getTabulador() {
        return tabulador;
    }

    /**
     * @param tabulador the tabulador to set
     */
    public void setTabulador(String tabulador) {
        this.tabulador = tabulador;
    }

    public void onEditContenido(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Msddsf cancelada", ((PairStringContenidoBean) event.getObject()).getPregunta());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancelContenido(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Mosssssn cancelada", ((PairStringContenidoBean) event.getObject()).getPregunta());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the pairStringContenidoBeanSeleccionado
     */
    public PairStringContenidoBean getPairStringContenidoBeanSeleccionado() {
        return pairStringContenidoBeanSeleccionado;
    }

    /**
     * @param pairStringContenidoBeanSeleccionado the
     * pairStringContenidoBeanSeleccionado to set
     */
    public void setPairStringContenidoBeanSeleccionado(PairStringContenidoBean pairStringContenidoBeanSeleccionado) {
        this.pairStringContenidoBeanSeleccionado = pairStringContenidoBeanSeleccionado;
    }

    /**
     * @return the linkDisenio
     */
    public String getLinkDisenio() {
        return linkDisenio;
    }

    /**
     * @param linkDisenio the linkDisenio to set
     */
    public void setLinkDisenio(String linkDisenio) {
        this.linkDisenio = linkDisenio;
    }

    /**
     * @return the linkVista
     */
    public String getLinkVista() {
        return linkVista;
    }

    /**
     * @param linkVista the linkVista to set
     */
    public void setLinkVista(String linkVista) {
        this.linkVista = linkVista;
    }

    public FormularioBean obtenerFormularioByIdFormulario() {
        return getImplementFormularioBO().obtenerFormularioByIdFormulario(this);
    }

    public Long agregarEstructura(EstructuraBean estructuraBeanRef, EstructuraBean estructuraBean, String tipoAdicion) {
        return getImplementFormularioBO().agregarEstructura(estructuraBeanRef, estructuraBean, tipoAdicion);
    }

    public Long eliminarEstructura(EstructuraBean estructuraBeanRef) {
        return getImplementFormularioBO().eliminarEstructura(estructuraBeanRef);
    }

    public Long agregarOpcion(ContenidoBean obj) {
        return getImplementFormularioBO().agregarOpcion(obj);
    }

    public Long eliminarOpcion(ContenidoBean obj) {
        return getImplementFormularioBO().eliminarOpcion(obj);
    }

    public Long actualizarContenido(ContenidoBean obj) {
        return getImplementFormularioBO().actualizarContenido(obj);
    }

    /**
     * @return the implementFormularioBO
     */
    public IFormularioBO getImplementFormularioBO() {
        return implementFormularioBO;
    }

    /**
     * @param implementFormularioBO the implementFormularioBO to set
     */
    public void setImplementFormularioBO(IFormularioBO implementFormularioBO) {
        this.implementFormularioBO = implementFormularioBO;
    }

    public List<String> Imprimir_Estructura_Preview() {
        if (this.estructuraBeans != null) {
            this.setCantidad_estructuras((long) new Long(estructuraBeans.size()));
        } else {
            this.setCantidad_estructuras((long) new Long(0));
        }
        if (contenidoBeans != null) {
            this.setCantidad_contenidos((long) new Long(contenidoBeans.size()));
        } else {
            this.setCantidad_contenidos((long) new Long(0));
        }
        EstructuraBean estructuraBean = new EstructuraBean();
        Integer j = 0;
        for (j = 0; j < cantidad_estructuras; j++) {
            estructuraBean = estructuraBeans.get(j);
            if ((estructuraBean.getIdEstructura() == estructuraBean.getIdEstructuraPredecesor()) && (estructuraBean.getIdEstructura() == estructuraBean.getIdEstructuraPadre())) {
                break;
            }
        }
        return Imprimir_Estructura_Preview_Recursivo(estructuraBean.getIdEstructura(), "");
    }

    public List<String> Imprimir_Estructura_Preview_Recursivo(long IdEstructura, String tab) {
        List<String> estructura_imprimir = new ArrayList<String>();
        List<String> estructura_imprimir2 = new ArrayList<String>();
        List<String> estructura_imprimir3 = new ArrayList<String>();
        Iterator<String> it;
        EstructuraBean estructura = new EstructuraBean();
        ContenidoBean contenido = new ContenidoBean();
        ContenidoBean contenido2 = new ContenidoBean();
        //Contenido contenido3 = new Contenido();
        long Siguiente_Hermano = new Long(0);
        Integer i = 0;
        Integer j = 0;
        String str = "";
        for (i = 0; i < cantidad_estructuras; i++) {
            estructura = estructuraBeans.get(i);
            if (estructura.getIdEstructura() == IdEstructura) {
                break;
            }
        }
        //estructura_imprimir.add("<div id=\"M-" + estructura.getId_Estructura() + "-" + estructura.getTipo() + "\" class=\"Marco_General\">");
        estructura_imprimir.add("<div id=\"M-" + estructura.getIdEstructura() + "-" + estructura.getTipo() + "\" >");
        //estructura_imprimir.add("<div id=\"Contenedor\" class=\"Marco_Editor_Principal\">" + estructura.getId_Estructura());
        estructura_imprimir.add("<div id=\"Contenedor\" >");

        if (estructura.getTipo().compareTo(tipoContenedores[0]) == 0) {
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if (contenido.getIdEstructura() == estructura.getIdEstructura()) {
                    estructura_imprimir.add(tab + contenido.getPregunta() + "<br />");
                    break;
                }
            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[1]) == 0) {
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if (contenido.getIdEstructura() == estructura.getIdEstructura()) {
                    estructura_imprimir.add(tab + contenido.getPregunta() + " <!--br /-->");
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 200px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        estructura_imprimir.add("<textarea style=\"width: 200px; height: 40px;\" ></textarea> <!--br /-->");
                        estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        estructura_imprimir.add("<input type=\"checkbox\" value=\"\"> <!--br /-->");
                    }
                    //estructura_imprimir.add("<input id=\"B-" + contenido.getId_Contenido() + "\" name=\"B-" + contenido.getId_Contenido() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");
                    estructura_imprimir.add("<br /><br />");

                    break;
                }
            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[2]) == 0) {
            //JOptionPane.showMessageDialog(null, "columna");
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                String Siguiente_Fila = "";

                if ((contenido.getIdEstructura() == estructura.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add(tab + "<table border=\"1\" >");
                    estructura_imprimir.add("<tr>");
                    estructura_imprimir.add("<td>" + contenido.getPregunta() + "</td>");
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<textarea style=\"width: 200px; height: 40px;\" ></textarea>" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"checkbox\" value=\"\" />" + "</td>";
                    }

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < cantidad_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(cantidad_contenidos + "") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            estructura_imprimir.add("<td>" + contenido.getPregunta() + "</td>");
                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<textarea style=\"width: 200px; height: 40px;\" ></textarea>" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"checkbox\" value=\"\" />" + "</td>";
                            }
                            //JOptionPane.showMessageDialog(null, "imprimir columna cual: " + contenido.getId_Contenido());
                        }
                    }
//                    estructura_imprimir.add("<td>" + "Eliminar" + "</td>");
//                    estructura_imprimir.add("<td>" + "Guardar" + "</td>");
//                    Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"button\" value=\"Eliminar\" />" + "</td>";
//                    Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"button\" value=\"Guardar\" />" + "</td>";

                    estructura_imprimir.add("</tr>");
                    estructura_imprimir.add("<tr>");
                    estructura_imprimir.add(Siguiente_Fila);
                    estructura_imprimir.add("</tr>");
                    estructura_imprimir.add("</table>");
                    estructura_imprimir.add("<input type=\"button\" value=\"Agregar Nueva Fila\" />");
                    break;
                }
                //JOptionPane.showMessageDialog(null, "col: "+contenido.getId_Contenido());
            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[3]) == 0) {
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if ((contenido.getIdEstructura() == estructura.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add(tab + contenido.getPregunta() + " <input type=\"radio\" name=\"opcion-" + estructura.getIdEstructura() + "\" value=\"\" /> &nbsp;&nbsp;&nbsp;&nbsp;");

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < cantidad_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(cantidad_contenidos + "") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            estructura_imprimir.add(contenido.getPregunta() + " <input type=\"radio\" name=\"opcion-" + estructura.getIdEstructura() + "\" value=\"\" /> &nbsp;&nbsp;&nbsp;&nbsp;");
                            //JOptionPane.showMessageDialog(null, "imprimir opciones");
                        }
                    }
                    break;
                }


            }

            estructura_imprimir.add("<br /><br />");

        } else if (estructura.getTipo().compareTo(tipoContenedores[4]) == 0) {
            //JOptionPane.showMessageDialog(null, "opciones");
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if ((contenido.getIdEstructura() == estructura.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add(tab + contenido.getPregunta() + " <!--br /-->");
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 150px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        estructura_imprimir.add("<textarea style=\"width: 150px; height: 40px;\" ></textarea>");
                        estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> &nbsp;&nbsp;&nbsp;&nbsp;");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        estructura_imprimir.add("<input type=\"checkbox\" value=\"\">");
                    }
                    //REBIZAR
                    //estructura_imprimir.add("<input id=\"B-" + contenido.getId_Contenido() + "\" name=\"B-" + contenido.getId_Contenido() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> &nbsp;&nbsp;&nbsp;&nbsp;");

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < cantidad_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(cantidad_contenidos + "") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            //imprimir
                            estructura_imprimir.add(contenido.getPregunta() + " <!--br /-->");
                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 150px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add("<textarea style=\"width: 150px; height: 40px;\" ></textarea>");
                                estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> &nbsp;&nbsp;&nbsp;&nbsp;");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                estructura_imprimir.add("<input type=\"checkbox\" value=\"\">");
                            }
                            //REBIZAR
                            //estructura_imprimir.add("<input id=\"B-" + contenido.getId_Contenido() + "\" name=\"B-" + contenido.getId_Contenido() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> &nbsp;&nbsp;&nbsp;&nbsp;");
                            //JOptionPane.showMessageDialog(null, "imprimir dependencia: " + contenido.getId_Contenido());
                        }
                    }
                    break;
                }
            }

            estructura_imprimir.add("<br /><br />");
        } else if (estructura.getTipo().compareTo(tipoContenedores[5]) == 0) {
            estructura_imprimir.add(tab + "<input type=\"button\" value=\"Agregar Nuevo Grupo\" /><br />");

        }

////////////////////
        if (estructura.getHijos().compareTo("") != 0) {
            StringTokenizer tokens = new StringTokenizer(estructura.getHijos(), "-");
            while (tokens.hasMoreTokens()) {
                str = tokens.nextToken();
                Long n = Long.valueOf(str);
                estructura_imprimir2 = Imprimir_Estructura_Preview_Recursivo(n, tab + tabulador);
                it = estructura_imprimir2.iterator();
                while (it.hasNext()) {
                    str = it.next();
                    estructura_imprimir.add(str);
                }
                estructura_imprimir2 = estructura_imprimir3;
                break;
            }
        }
        //

        estructura_imprimir.add("</div>");



        estructura_imprimir.add("</div>");

        Siguiente_Hermano = estructura.getIdEstructura();
        for (i = 0; i < cantidad_estructuras; i++) {
            estructura = estructuraBeans.get(i);
            if (estructura.getIdEstructuraPredecesor() == Siguiente_Hermano && estructura.getIdEstructuraPredecesor() != estructura.getIdEstructura()) {
                break;
            }
        }
        if (i < cantidad_estructuras) {
            estructura_imprimir2 = Imprimir_Estructura_Preview_Recursivo(estructura.getIdEstructura(), tab);
            it = estructura_imprimir2.iterator();
            while (it.hasNext()) {
                str = it.next();
                estructura_imprimir.add(str);
            }
        }
        return estructura_imprimir;
    }
    
    public List<String> Imprimir_Estructura_Asignar_Categoria() {
        List<String> estructura_imprimir = new ArrayList<String>();
        if (this.estructuraBeans != null) {
            this.setCantidad_estructuras((long) new Long(estructuraBeans.size()));
        } else {
            this.setCantidad_estructuras((long) new Long(0));
        }

        if (contenidoBeans != null) {
            this.setCantidad_contenidos((long) new Long(contenidoBeans.size()));
        } else {
            this.setCantidad_contenidos((long) new Long(0));
        }

        EstructuraBean estructuraBean = new EstructuraBean();
        Integer j = 0;
        for (j = 0; j < cantidad_estructuras; j++) {
            estructuraBean = estructuraBeans.get(j);
            if ((estructuraBean.getIdEstructura() == estructuraBean.getIdEstructuraPredecesor()) && (estructuraBean.getIdEstructura() == estructuraBean.getIdEstructuraPadre())) {
                break;
            }
        }
        estructura_imprimir = Imprimir_Estructura_Asignar_Categoria_Recursivo(estructuraBean.getIdEstructura(), "");
        return estructura_imprimir;
    }
    
    public List<String> Imprimir_Estructura_Asignar_Categoria_Recursivo(long Id_Estructura, String tab) {
        List<String> estructura_imprimir = new ArrayList<String>();
        List<String> estructura_imprimir2 = new ArrayList<String>();
        List<String> estructura_imprimir3 = new ArrayList<String>();
        Iterator<String> it;
        EstructuraBean estructura = new EstructuraBean();
        ContenidoBean contenido = new ContenidoBean();
        ContenidoBean contenido2 = new ContenidoBean();
        //Contenido contenido3 = new Contenido();
        long Siguiente_Hermano = new Long(0);
        Integer i = 0;
        Integer j = 0;
        String str = "";
        for (i = 0; i < cantidad_estructuras; i++) {
            estructura = estructuraBeans.get(i);
            if (estructura.getIdEstructura() == Id_Estructura) {
                break;
            }
        }
        //estructura_imprimir.add("<div id=\"M-" + estructura.getId_Estructura() + "-" + estructura.getTipo() + "\" class=\"Marco_General\">");
        estructura_imprimir.add("<div id=\"M-" + estructura.getIdEstructura() + "-" + estructura.getTipo() + "\" >");
        //estructura_imprimir.add("<div id=\"Contenedor\" class=\"Marco_Editor_Principal\">" + estructura.getId_Estructura());
        estructura_imprimir.add("<div id=\"Contenedor\" >");

        if (estructura.getTipo().compareTo(tipoContenedores[0]) == 0) {
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if (contenido.getIdEstructura() == estructura.getIdEstructura()) {
                    estructura_imprimir.add(tab + contenido.getPregunta() + "<br />");
                    break;
                }
            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[1]) == 0) {
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if (contenido.getIdEstructura() == estructura.getIdEstructura()) {
                    estructura_imprimir.add(tab + "<input type=\"radio\" id=\"" + contenido.getIdContenido() + "\" name=\"categoria\" class=\"seleccion_categoria\" value=\"x" + contenido.getIdContenido() + "\" />" + contenido.getPregunta() + " <!--br /-->");
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 200px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        estructura_imprimir.add("<textarea style=\"width: 200px; height: 40px;\" ></textarea> <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        estructura_imprimir.add("<input type=\"checkbox\" value=\"\"> <!--br /-->");
                    }
                    estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");
                    estructura_imprimir.add("<br /><br />");

                    break;
                }
            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[2]) == 0) {
            //JOptionPane.showMessageDialog(null, "columna");
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                String Siguiente_Fila = "";

                if ((contenido.getIdEstructura() == estructura.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add(tab + "<table border=\"1\" >");
                    estructura_imprimir.add("<tr>");
                    estructura_imprimir.add("<td>" + contenido.getPregunta() + "</td>");
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<textarea style=\"width: 200px; height: 40px;\" ></textarea>" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"checkbox\" value=\"\" />" + "</td>";
                    }

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < cantidad_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(cantidad_contenidos+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            estructura_imprimir.add("<td>" + contenido.getPregunta() + "</td>");
                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<textarea style=\"width: 200px; height: 40px;\" ></textarea>" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"text\" value=\"\" />" + "</td>";
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"checkbox\" value=\"\" />" + "</td>";
                            }
                            //JOptionPane.showMessageDialog(null, "imprimir columna cual: " + contenido.getId_Contenido());
                        }
                    }
                    estructura_imprimir.add("<td>" + "Eliminar" + "</td>");
                    estructura_imprimir.add("<td>" + "Guardar" + "</td>");
                    Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"button\" value=\"Eliminar\" />" + "</td>";
                    Siguiente_Fila = Siguiente_Fila + "<td>" + "<input type=\"button\" value=\"Guardar\" />" + "</td>";

                    estructura_imprimir.add("</tr>");
                    estructura_imprimir.add("<tr>");
                    estructura_imprimir.add(Siguiente_Fila);
                    estructura_imprimir.add("</tr>");
                    estructura_imprimir.add("</table>");
                    estructura_imprimir.add("<input type=\"button\" value=\"Agregar Nueva Fila\" />");
                    break;
                }
                //JOptionPane.showMessageDialog(null, "col: "+contenido.getId_Contenido());
            }
        } else if (estructura.getTipo().compareTo(tipoContenedores[3]) == 0) {
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if ((contenido.getIdEstructura() == estructura.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add(tab + contenido.getPregunta() + " <input type=\"radio\" name=\"opcion-" + estructura.getIdEstructura() + "\" value=\"\" /> &nbsp;&nbsp;&nbsp;&nbsp;");

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < cantidad_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(cantidad_contenidos+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            estructura_imprimir.add(contenido.getPregunta() + " <input type=\"radio\" name=\"opcion-" + estructura.getIdEstructura() + "\" value=\"\" /> &nbsp;&nbsp;&nbsp;&nbsp;");
                            //JOptionPane.showMessageDialog(null, "imprimir opciones");
                        }
                    }
                    break;
                }


            }

            estructura_imprimir.add("<br /><br />");

        } else if (estructura.getTipo().compareTo(tipoContenedores[4]) == 0) {
            //JOptionPane.showMessageDialog(null, "opciones");
            for (i = 0; i < cantidad_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if ((contenido.getIdEstructura() == estructura.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add(tab + contenido.getPregunta() + " <!--br /-->");
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 150px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        estructura_imprimir.add("<textarea style=\"width: 150px; height: 40px;\" ></textarea>");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        estructura_imprimir.add("<input type=\"checkbox\" value=\"\">");
                    }
                    //REBIZAR
                    estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> &nbsp;&nbsp;&nbsp;&nbsp;");

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < cantidad_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(cantidad_contenidos+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            //imprimir
                            estructura_imprimir.add(contenido.getPregunta() + " <!--br /-->");
                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 150px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add("<textarea style=\"width: 150px; height: 40px;\" ></textarea>");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add("<input type=\"text\" value=\"\" style=\"width: 100px; height: 20px;\" >");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                estructura_imprimir.add("<input type=\"checkbox\" value=\"\">");
                            }
                            //REBIZAR
                            estructura_imprimir.add("<input id=\"B-" + contenido.getIdContenido() + "\" name=\"B-" + contenido.getIdContenido() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> &nbsp;&nbsp;&nbsp;&nbsp;");
                            //JOptionPane.showMessageDialog(null, "imprimir dependencia: " + contenido.getId_Contenido());
                        }
                    }
                    break;
                }
            }

            estructura_imprimir.add("<br /><br />");
        } else if (estructura.getTipo().compareTo(tipoContenedores[5]) == 0) {
            estructura_imprimir.add(tab + "<input type=\"button\" value=\"Agregar Nuevo Grupo\" /><br />");

        }

////////////////////
        if (estructura.getHijos().compareTo("") != 0) {
            StringTokenizer tokens = new StringTokenizer(estructura.getHijos(), "-");
            while (tokens.hasMoreTokens()) {
                str = tokens.nextToken();
                Long n = Long.valueOf(str);
                estructura_imprimir2 = Imprimir_Estructura_Asignar_Categoria_Recursivo(n, tab + tabulador);
                it = estructura_imprimir2.iterator();
                while (it.hasNext()) {
                    str = it.next();
                    estructura_imprimir.add(str);
                }
                estructura_imprimir2 = estructura_imprimir3;
                break;
            }
        }
        //

        estructura_imprimir.add("</div>");



        estructura_imprimir.add("</div>");

        Siguiente_Hermano = estructura.getIdEstructura();
        for (i = 0; i < cantidad_estructuras; i++) {
            estructura = estructuraBeans.get(i);
            if (estructura.getIdEstructuraPredecesor() == Siguiente_Hermano && estructura.getIdEstructuraPredecesor() != estructura.getIdEstructura()) {
                break;
            }
        }
        if (i < cantidad_estructuras) {
            estructura_imprimir2 = Imprimir_Estructura_Asignar_Categoria_Recursivo(estructura.getIdEstructura(), tab);
            it = estructura_imprimir2.iterator();
            while (it.hasNext()) {
                str = it.next();
                estructura_imprimir.add(str);
            }
        }
        return estructura_imprimir;
    }

    /**
     * @return the contenidoBeanAsignarCategoriaContenido
     */
    public ContenidoBean getContenidoBeanAsignarCategoriaContenido() {
        return contenidoBeanAsignarCategoriaContenido;
    }

    /**
     * @param contenidoBeanAsignarCategoriaContenido the contenidoBeanAsignarCategoriaContenido to set
     */
    public void setContenidoBeanAsignarCategoriaContenido(ContenidoBean contenidoBeanAsignarCategoriaContenido) {
        this.contenidoBeanAsignarCategoriaContenido = contenidoBeanAsignarCategoriaContenido;
    }
    
    public Long asignarCategoriaContenido(){
        return implementFormularioBO.asignarCategoriaContenido( this.contenidoBeanAsignarCategoriaContenido );
    }
}