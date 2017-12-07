/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.ImplementPersonaBO;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author VIIII1
 */
public class PersonaBean implements Serializable {

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private String ci;
    private String nombre;
    private String app;
    private String apm;
    private String cuenta;
    private String contrasenia;
    private String estado;
    private Boolean status;
    private String mensaje;
    private Integer tipoUsuario;
    private MenuModel menu;
    private String pagina;
    private ImplementPersonaBO implementPersonaBO;
    private List<PersonaBean> miLista;
    private PersonaBean personaBeanSeleccionado;
    private List<PersonaBean> filtroPersonaBeans;
    private List<PersonaBean> personaBeanSeleccionados;
    
    
    //DIRECTOR CARRERA
    private List<DocenteBean> listaDocentes;
    private Map<String, String> mapaListaDocente;
    //
    //
    //
    
    private final static String[] estados;
    private SelectItem[] estadosOptions;

    static {
        estados = new String[4];
        estados[0] = "ACTIVO";
        estados[1] = "SUSPENDIDO";
        estados[2] = "FINALIZADO";
        estados[3] = "ELIMINADO";
    }

    public PersonaBean() {
        cuenta = "";
        contrasenia = "";

        ci = "ci";
        nombre = "nombre";
        app = "apellido paterno";
        apm = "apellido materno";
        estado = "estado";
        mensaje="";

        estadosOptions = crearEstadosOptions(estados);

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
     * @return the app
     */
    public String getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * @return the apm
     */
    public String getApm() {
        return apm;
    }

    /**
     * @param apm the apm to set
     */
    public void setApm(String apm) {
        this.apm = apm;
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = Encriptar(contrasenia);
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

    public void verificarUsuario() {
        ///////////////////////
        
        /////////////////////

        FacesMessage msg = null;
        boolean loggedIn = false;
        String devolver = "";

        System.out.print("Cuenta : " + getCuenta());
        System.out.print("Contrasenia : " + getContrasenia());
        setTipoUsuario(getImplementPersonaBO().verificarUsuario(this));
        System.out.print(this.getMensaje());

        RequestContext context = RequestContext.getCurrentInstance();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        if (getTipoUsuario() > 1) {
            System.out.print("Nom : " + getCuenta());
            System.out.print("Contrasenia : " + getContrasenia());
            System.out.print("Cuenta : " + getCuenta());
            System.out.print("Contrasenia : " + getContrasenia());
            setPagina("/Wellcome.xhtml");

            Map<String, Object> sessionMap = externalContext.getSessionMap();
            //setListaUsuarios(new ArrayList<PersonaBean>());
            //sessionMap.put("ListaUsuario", getListaUsuarios() );
            sessionMap.put("usuarioLogeado", this);

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", getNombre() + " " + getApp() + " " + getApm() + " topo:" + getTipoUsuario());
            loggedIn = true;
            devolver = "Menu.dea";

            setMenu(new DefaultMenuModel());

            if (getTipoUsuario() >= 729) {
                setTipoUsuario(getTipoUsuario() - 729);

                DefaultSubMenu subMenuDirectorDEAPrincipal = new DefaultSubMenu("Director DEA");

                /////////////////////////////
                DefaultSubMenu subMenuDirectorDEA1 = new DefaultSubMenu("ADMINISTRAR");

                DefaultMenuItem itemUniversidadDirectorDea = new DefaultMenuItem("Universidad");
                //itemUniversidadDirectorDea.setUrl("/DEA/Director_Dea/UnidadAcademica/Universidad.dea");
                itemUniversidadDirectorDea.setIcon("ui-icon-home");
                itemUniversidadDirectorDea.setUpdate("mensajes body");
                itemUniversidadDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/UnidadAcademica/Universidad.xhtml')}");
                subMenuDirectorDEA1.addElement(itemUniversidadDirectorDea);

                DefaultMenuItem itemFacultadDirectorDea = new DefaultMenuItem("Facultad");
                //itemFacultadDirectorDea.setUrl("/DEA/Director_Dea/UnidadAcademica/Facultad.dea");
                itemFacultadDirectorDea.setIcon("ui-icon-home");
                itemFacultadDirectorDea.setUpdate("mensajes body");
                itemFacultadDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/UnidadAcademica/Facultad.xhtml')}");
                subMenuDirectorDEA1.addElement(itemFacultadDirectorDea);

                DefaultMenuItem itemCarreraDirectorDea = new DefaultMenuItem("Carrera");
                itemCarreraDirectorDea.setIcon("ui-icon-home");
                itemCarreraDirectorDea.setUpdate("mensajes body");
                itemCarreraDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/UnidadAcademica/Carrera.xhtml')}");
                subMenuDirectorDEA1.addElement(itemCarreraDirectorDea);

                DefaultMenuItem itemMateriaDirectorDea = new DefaultMenuItem("Materia");
                itemMateriaDirectorDea.setIcon("ui-icon-home");
                itemMateriaDirectorDea.setUpdate("mensajes body");
                itemMateriaDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/PlanCurricular/Materia.xhtml')}");
                subMenuDirectorDEA1.addElement(itemMateriaDirectorDea);

                subMenuDirectorDEAPrincipal.addElement(subMenuDirectorDEA1);

                DefaultSeparator separador1DirectorDea = new DefaultSeparator();
                subMenuDirectorDEAPrincipal.addElement(separador1DirectorDea);

                /////////////////////////////
                DefaultSubMenu subMenuDirectorDEA2 = new DefaultSubMenu("CREAR USUARIOS");

                DefaultMenuItem itemDirectorDeaDirectorDea = new DefaultMenuItem("Crear Director DEA");
                itemDirectorDeaDirectorDea.setIcon("ui-icon-home");
                itemDirectorDeaDirectorDea.setUpdate("mensajes body");
                itemDirectorDeaDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/Usuario/DirectorDea.xhtml')}");
                subMenuDirectorDEA2.addElement(itemDirectorDeaDirectorDea);

                DefaultMenuItem itemCoordinadorDeaDirectorDea = new DefaultMenuItem("Crear Coordinador DEA");
                itemCoordinadorDeaDirectorDea.setIcon("ui-icon-home");
                itemCoordinadorDeaDirectorDea.setUpdate("mensajes body");
                itemCoordinadorDeaDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/Usuario/CoordinadorDea.xhtml')}");
                subMenuDirectorDEA2.addElement(itemCoordinadorDeaDirectorDea);

                /*DefaultMenuItem itemComisionEvaluadoraDirectorDea = new DefaultMenuItem("Comision Evaluadora");
                itemComisionEvaluadoraDirectorDea.setIcon("ui-icon-home");
                itemComisionEvaluadoraDirectorDea.setUpdate("mensajes body");
                itemComisionEvaluadoraDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/Usuario/ComisionEvaluadora.xhtml')}");
                subMenuDirectorDEA2.addElement(itemComisionEvaluadoraDirectorDea);*/

                /*DefaultMenuItem itemDirectorCarreraDirectorDea = new DefaultMenuItem("Director Carrera");
                itemDirectorCarreraDirectorDea.setIcon("ui-icon-home");
                itemDirectorCarreraDirectorDea.setUpdate("mensajes body");
                itemDirectorCarreraDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/Usuario/DirectorCarrera.xhtml')}");
                subMenuDirectorDEA2.addElement(itemDirectorCarreraDirectorDea);*/

                DefaultMenuItem itemDocenteDirectorDea = new DefaultMenuItem("Crear Docente");
                itemDocenteDirectorDea.setIcon("ui-icon-home");
                itemDocenteDirectorDea.setUpdate("mensajes body");
                itemDocenteDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/Usuario/Docente.xhtml')}");
                subMenuDirectorDEA2.addElement(itemDocenteDirectorDea);

                /*DefaultMenuItem itemEstudianteDirectorDea = new DefaultMenuItem("Estudiante");
                itemEstudianteDirectorDea.setIcon("ui-icon-home");
                itemEstudianteDirectorDea.setUpdate("mensajes body");
                itemEstudianteDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/Usuario/Estudiante.xhtml')}");
                subMenuDirectorDEA2.addElement(itemEstudianteDirectorDea);*/

                DefaultMenuItem itemPersonaDirectorDea = new DefaultMenuItem("Persona");
                itemPersonaDirectorDea.setIcon("ui-icon-home");
                itemPersonaDirectorDea.setUpdate("mensajes body");
                itemPersonaDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/Usuario/Persona.xhtml')}");
                subMenuDirectorDEA2.addElement(itemPersonaDirectorDea);

                subMenuDirectorDEAPrincipal.addElement(subMenuDirectorDEA2);

                DefaultSeparator separador2DirectorDea = new DefaultSeparator();
                subMenuDirectorDEAPrincipal.addElement(separador2DirectorDea);

                /////////////////////////////
                DefaultSubMenu subMenuDirectorDEA3 = new DefaultSubMenu("ADMINISTRAR MATERIA");

                DefaultMenuItem itemRelacionDocenteMateriaDirectorDea = new DefaultMenuItem("Docente Materia");
                itemRelacionDocenteMateriaDirectorDea.setIcon("ui-icon-home");
                itemRelacionDocenteMateriaDirectorDea.setUpdate("mensajes body");
                itemRelacionDocenteMateriaDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/Materias/RelacionDocenteMateria.xhtml')}");
                subMenuDirectorDEA3.addElement(itemRelacionDocenteMateriaDirectorDea);

                subMenuDirectorDEAPrincipal.addElement(subMenuDirectorDEA3);
                
                DefaultSeparator separador2_5DirectorDea = new DefaultSeparator();
                subMenuDirectorDEAPrincipal.addElement(separador2_5DirectorDea);
                
                DefaultSubMenu subMenuDirectorDEA4 = new DefaultSubMenu("ADMINISTRAR ESCALAFON");
                
                DefaultMenuItem itemDocenteEscalafonDirectorDea = new DefaultMenuItem("Administrar Docente Escalafon");
                itemDocenteEscalafonDirectorDea.setIcon("ui-icon-home");
                itemDocenteEscalafonDirectorDea.setUpdate("mensajes body");
                itemDocenteEscalafonDirectorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Director_Dea/Usuario/Docente_Escalafon.xhtml')}");
                subMenuDirectorDEA4.addElement(itemDocenteEscalafonDirectorDea);

                subMenuDirectorDEAPrincipal.addElement(subMenuDirectorDEA4);

                DefaultSeparator separador3DirectorDea = new DefaultSeparator();
                subMenuDirectorDEAPrincipal.addElement(separador3DirectorDea);

                /////////////////////////////

                DefaultSeparator separador4DirectorDea = new DefaultSeparator();
                subMenuDirectorDEAPrincipal.addElement(separador4DirectorDea);

                getMenu().addElement(subMenuDirectorDEAPrincipal);
                getMenu().addElement(separador1DirectorDea);
                getMenu().addElement(separador1DirectorDea);

            }
            if (getTipoUsuario() >= 234) {
                setTipoUsuario(getTipoUsuario() - 243);

                DefaultSubMenu subMenuCoordinadorDEAPrincipal = new DefaultSubMenu("COORDINADOR DEA");

                /////////////////////////////
                DefaultSubMenu subMenuCoordinadorDEA1 = new DefaultSubMenu("ACADEMICO");

                DefaultMenuItem itemUniversidadCoordinadorDea = new DefaultMenuItem("Universidad");
                //itemUniversidadDirectorDea.setUrl("/DEA/Director_Dea/UnidadAcademica/Universidad.dea");
                itemUniversidadCoordinadorDea.setIcon("ui-icon-home");
                itemUniversidadCoordinadorDea.setUpdate("mensajes body");
                itemUniversidadCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/UnidadAcademica/Universidad.xhtml')}");
                subMenuCoordinadorDEA1.addElement(itemUniversidadCoordinadorDea);

                DefaultMenuItem itemFacultadCoordinadorDea = new DefaultMenuItem("Facultad");
                //itemFacultadDirectorDea.setUrl("/DEA/Director_Dea/UnidadAcademica/Facultad.dea");
                itemFacultadCoordinadorDea.setIcon("ui-icon-home");
                itemFacultadCoordinadorDea.setUpdate("mensajes body");
                itemFacultadCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/UnidadAcademica/Facultad.xhtml')}");
                subMenuCoordinadorDEA1.addElement(itemFacultadCoordinadorDea);

                DefaultMenuItem itemCarreraCoordinadorDea = new DefaultMenuItem("Carrera");
                itemCarreraCoordinadorDea.setIcon("ui-icon-home");
                itemCarreraCoordinadorDea.setUpdate("mensajes body");
                itemCarreraCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/UnidadAcademica/Carrera.xhtml')}");
                subMenuCoordinadorDEA1.addElement(itemCarreraCoordinadorDea);

                DefaultMenuItem itemMateriaCoordinadorDea = new DefaultMenuItem("Materia");
                itemMateriaCoordinadorDea.setIcon("ui-icon-home");
                itemMateriaCoordinadorDea.setUpdate("mensajes body");
                itemMateriaCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/PlanCurricular/Materia.xhtml')}");
                subMenuCoordinadorDEA1.addElement(itemMateriaCoordinadorDea);

                subMenuCoordinadorDEAPrincipal.addElement(subMenuCoordinadorDEA1);

                DefaultSeparator separador1CoordinadorDea = new DefaultSeparator();
                subMenuCoordinadorDEAPrincipal.addElement(separador1CoordinadorDea);

                /////////////////////////////
                DefaultSubMenu subMenuCoordinadorDEA2 = new DefaultSubMenu("USUARIOS");

                DefaultMenuItem itemDirectorDeaCoordinadorDea = new DefaultMenuItem("Director DEA");
                itemDirectorDeaCoordinadorDea.setIcon("ui-icon-home");
                itemDirectorDeaCoordinadorDea.setUpdate("mensajes body");
                itemDirectorDeaCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/Usuario/DirectorDea.xhtml')}");
                subMenuCoordinadorDEA2.addElement(itemDirectorDeaCoordinadorDea);

                DefaultMenuItem itemCoordinadorDeaCoordinadorDea = new DefaultMenuItem("Coordinador DEA");
                itemCoordinadorDeaCoordinadorDea.setIcon("ui-icon-home");
                itemCoordinadorDeaCoordinadorDea.setUpdate("mensajes body");
                itemCoordinadorDeaCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/Usuario/CoordinadorDea.xhtml')}");
                subMenuCoordinadorDEA2.addElement(itemCoordinadorDeaCoordinadorDea);

                /*DefaultMenuItem itemComisionEvaluadoraCoordinadorDea = new DefaultMenuItem("Comision Evaluadora");
                itemComisionEvaluadoraCoordinadorDea.setIcon("ui-icon-home");
                itemComisionEvaluadoraCoordinadorDea.setUpdate("mensajes body");
                itemComisionEvaluadoraCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/Usuario/ComisionEvaluadora.xhtml')}");
                subMenuCoordinadorDEA2.addElement(itemComisionEvaluadoraCoordinadorDea);*/

                /*DefaultMenuItem itemDirectorCarreraCoordinadorDea = new DefaultMenuItem("Director Carrera");
                itemDirectorCarreraCoordinadorDea.setIcon("ui-icon-home");
                itemDirectorCarreraCoordinadorDea.setUpdate("mensajes body");
                itemDirectorCarreraCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/Usuario/DirectorCarrera.xhtml')}");
                subMenuCoordinadorDEA2.addElement(itemDirectorCarreraCoordinadorDea);*/

                DefaultMenuItem itemDocenteCoordinadorDea = new DefaultMenuItem("Docente");
                itemDocenteCoordinadorDea.setIcon("ui-icon-home");
                itemDocenteCoordinadorDea.setUpdate("mensajes body");
                itemDocenteCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/Usuario/Docente.xhtml')}");
                subMenuCoordinadorDEA2.addElement(itemDocenteCoordinadorDea);

                /*DefaultMenuItem itemEstudianteCoordinadorDea = new DefaultMenuItem("Estudiante");
                itemEstudianteCoordinadorDea.setIcon("ui-icon-home");
                itemEstudianteCoordinadorDea.setUpdate("mensajes body");
                itemEstudianteCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/Usuario/Estudiante.xhtml')}");
                subMenuCoordinadorDEA2.addElement(itemEstudianteCoordinadorDea);*/

                DefaultMenuItem itemPersonaCoordinadorDea = new DefaultMenuItem("Persona");
                itemPersonaCoordinadorDea.setIcon("ui-icon-home");
                itemPersonaCoordinadorDea.setUpdate("mensajes body");
                itemPersonaCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/Usuario/Persona.xhtml')}");
                subMenuCoordinadorDEA2.addElement(itemPersonaCoordinadorDea);

                subMenuCoordinadorDEAPrincipal.addElement(subMenuCoordinadorDEA2);

                DefaultSeparator separador2CoordinadorDea = new DefaultSeparator();
                subMenuCoordinadorDEAPrincipal.addElement(separador2CoordinadorDea);

                /////////////////////////////
                DefaultSubMenu subMenuCoordinadorDEA3 = new DefaultSubMenu("MATERIA");

                DefaultMenuItem itemRelacionDocenteMateriaCoordinadorDea = new DefaultMenuItem("Docente Materia");
                itemRelacionDocenteMateriaCoordinadorDea.setIcon("ui-icon-home");
                itemRelacionDocenteMateriaCoordinadorDea.setUpdate("mensajes body");
                itemRelacionDocenteMateriaCoordinadorDea.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Coordinador_Dea/Materias/RelacionDocenteMateria.xhtml')}");
                subMenuCoordinadorDEA3.addElement(itemRelacionDocenteMateriaCoordinadorDea);

                subMenuCoordinadorDEAPrincipal.addElement(subMenuCoordinadorDEA3);

                DefaultSeparator separador3CoordinadorDea = new DefaultSeparator();
                subMenuCoordinadorDEAPrincipal.addElement(separador3CoordinadorDea);

                /////////////////////////////
                DefaultSeparator separador4CoordinadorDea = new DefaultSeparator();
                subMenuCoordinadorDEAPrincipal.addElement(separador4CoordinadorDea);

                getMenu().addElement(subMenuCoordinadorDEAPrincipal);

            }

            if (getTipoUsuario() >= 81) {//miembro com eva
                setTipoUsuario(getTipoUsuario() - 81);

            }
            if (getTipoUsuario() >= 27) {//dir car
                setTipoUsuario(getTipoUsuario() - 27);
                
                DefaultSubMenu subMenuEstudiantePrincipal = new DefaultSubMenu("Director de Carrera");

                /////////////////////////////
                DefaultSubMenu subMenuEstudiante1 = new DefaultSubMenu("Llenar");

                DefaultMenuItem item1Estudiante = new DefaultMenuItem("Cuestionarios");
                //itemUniversidadDirectorDea.setUrl("/DEA/Director_Dea/UnidadAcademica/Universidad.dea");
                item1Estudiante.setIcon("ui-icon-home");
                item1Estudiante.setUpdate("mensajes body");
                item1Estudiante.setCommand("#{usuarioLogeado.cambioPagina('/DEA/DirectorCarrera/ListaCuestionarios.xhtml')}");
                subMenuEstudiante1.addElement(item1Estudiante);

                subMenuEstudiantePrincipal.addElement(subMenuEstudiante1);

                getMenu().addElement(subMenuEstudiantePrincipal);
                
                ///////////////////////////////////
                
                
                
                /////////////
                

            }
            if (getTipoUsuario() >= 9) {//docente
                setTipoUsuario(getTipoUsuario() - 9);
                
                DefaultSubMenu subMenuEstudiantePrincipal = new DefaultSubMenu("Docente");

                /////////////////////////////
                DefaultSubMenu subMenuEstudiante1 = new DefaultSubMenu("Llenar");

                DefaultMenuItem item1Estudiante = new DefaultMenuItem("Cuestionarios");
                //itemUniversidadDirectorDea.setUrl("/DEA/Director_Dea/UnidadAcademica/Universidad.dea");
                item1Estudiante.setIcon("ui-icon-home");
                item1Estudiante.setUpdate("mensajes body");
                item1Estudiante.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Docente/ListaCuestionarios.xhtml')}");
                subMenuEstudiante1.addElement(item1Estudiante);

                subMenuEstudiantePrincipal.addElement(subMenuEstudiante1);

                getMenu().addElement(subMenuEstudiantePrincipal);

                ///////////////////////////////////

            }
            if (getTipoUsuario() >= 3) {//estud
                setTipoUsuario(getTipoUsuario() - 3);

                DefaultSubMenu subMenuEstudiantePrincipal = new DefaultSubMenu("ESTUDIANTE");

                /////////////////////////////
                DefaultSubMenu subMenuEstudiante1 = new DefaultSubMenu("Llenar");

                DefaultMenuItem item1Estudiante = new DefaultMenuItem("Cuestionarios");
                //itemUniversidadDirectorDea.setUrl("/DEA/Director_Dea/UnidadAcademica/Universidad.dea");
                item1Estudiante.setIcon("ui-icon-home");
                item1Estudiante.setUpdate("mensajes body");
                item1Estudiante.setCommand("#{usuarioLogeado.cambioPagina('/DEA/Estudiante/ListaCuestionarios.xhtml')}");
                subMenuEstudiante1.addElement(item1Estudiante);

                subMenuEstudiantePrincipal.addElement(subMenuEstudiante1);

                getMenu().addElement(subMenuEstudiantePrincipal);

                ///////////////////////////////////


            }

            //devolver= "dea/newjsf.xhtml" ;
        } else {
            //((HttpServletRequest)externalContext.getRequest()).setAttribute("errorAccesos", getMensaje());
            //return "login";
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario y/o Contraseña incorrecto(s)", "");
            loggedIn = false;
            devolver = "index.dea";
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);

        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("view", devolver);

        //return devolver;
    }

    private String Encriptar(String Contrasenia) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(Contrasenia.getBytes());
            int size = b.length;
            StringBuffer h = new StringBuffer(size);
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    h.append("0" + Integer.toHexString(u));
                } else {
                    h.append(Integer.toHexString(u));
                }
            }
            Contrasenia = h.toString();
        } catch (Exception e) {
        }
        return Contrasenia;
    }

    /**
     * @return the implementPersonaBO
     */
    public ImplementPersonaBO getImplementPersonaBO() {
        return implementPersonaBO;
    }

    /**
     * @param implementPersonaBO the implementPersonaBO to set
     */
    public void setImplementPersonaBO(ImplementPersonaBO implementPersonaBO) {
        this.implementPersonaBO = implementPersonaBO;
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the tipoUsuario
     */
    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the menu
     */
    public MenuModel getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    /**
     * @return the pagina
     */
    public String getPagina() {
        return pagina;
    }

    /**
     * @param pagina the pagina to set
     */
    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public void cambioPagina(String pagina) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargando", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.setPagina(pagina);
    }

    private SelectItem[] crearEstadosOptions(String[] data) {
        SelectItem[] options = new SelectItem[data.length + 1];
        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < data.length; i++) {
            options[i + 1] = new SelectItem(data[i], data[i]);
        }
        return options;
    }

    /**
     * @return the personaBeanSeleccionado
     */
    public PersonaBean getPersonaBeanSeleccionado() {
        return personaBeanSeleccionado;
    }

    /**
     * @param personaBeanSeleccionado the personaBeanSeleccionado to set
     */
    public void setPersonaBeanSeleccionado(PersonaBean personaBeanSeleccionado) {
        this.personaBeanSeleccionado = personaBeanSeleccionado;
    }

    /**
     * @return the filtroPersonaBeans
     */
    public List<PersonaBean> getFiltroPersonaBeans() {
        return filtroPersonaBeans;
    }

    /**
     * @param filtroPersonaBeans the filtroPersonaBeans to set
     */
    public void setFiltroPersonaBeans(List<PersonaBean> filtroPersonaBeans) {
        this.filtroPersonaBeans = filtroPersonaBeans;
    }

    /**
     * @return the personaBeanSeleccionados
     */
    public List<PersonaBean> getPersonaBeanSeleccionados() {
        return personaBeanSeleccionados;
    }

    /**
     * @param personaBeanSeleccionados the personaBeanSeleccionados to set
     */
    public void setPersonaBeanSeleccionados(List<PersonaBean> personaBeanSeleccionados) {
        this.personaBeanSeleccionados = personaBeanSeleccionados;
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
     * @return the miLista
     */
    public List<PersonaBean> getMiLista() {
        return miLista;
    }

    /**
     * @param miLista the miLista to set
     */
    public void setMiLista(List<PersonaBean> miLista) {
        this.miLista = miLista;
    }

    //@PostConstruct
    public void obtenerPersonas() {
        setMiLista(getImplementPersonaBO().obtenerPersonas());
        //mapearLista();
    }

    public void onEdit(RowEditEvent event) {
        FacesMessage msg = null;
        Long respuesta = new Long(0);
        PersonaBean personaBean = new PersonaBean();

        personaBean.setCi(((PersonaBean) event.getObject()).getCi());
        personaBean.setNombre(((PersonaBean) event.getObject()).getNombre());
        personaBean.setApp(((PersonaBean) event.getObject()).getApp());
        personaBean.setApm(((PersonaBean) event.getObject()).getApm());
        personaBean.setCuenta(((PersonaBean) event.getObject()).getCuenta());
        personaBean.setContrasenia(((PersonaBean) event.getObject()).getContrasenia());
        personaBean.setEstado(((PersonaBean) event.getObject()).getEstado());

        respuesta = implementPersonaBO.modificarPersona(personaBean);
        obtenerPersonas();
        System.out.println("1 " + getCi());
        System.out.println("2 " + getNombre());

        System.out.println("Resultado " + respuesta);
        if (respuesta.longValue() == 1) {
            msg = new FacesMessage("Persona fue Modificado", ((PersonaBean) event.getObject()).getNombre());
        } else {
            msg = new FacesMessage("No se encontro Persona", "");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        obtenerPersonas();
        FacesMessage msg = new FacesMessage("Modificacion cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String crearPersona() {
        Long respuesta = new Long(0);
        FacesMessage msg = null;
        respuesta = getImplementPersonaBO().crearPersona(this);
        obtenerPersonas();
        if (respuesta.longValue() == 0) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La persona con C.I:" + getCi() + " Ya existe");
        } else if (respuesta.longValue() == -1) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La cuenta " + getCuenta() + " No esta disponible, no se creo Director Dea");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "El Persona a sido creado");
            this.ci = "ci";
            this.nombre = "nombre";
            this.app = "apellido paterno";
            this.apm = "apellido materno";
            this.cuenta = "cuenta";
            this.contrasenia = "password";
            this.estado = "estado";
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }

    public String obtenerPersona() {
        FacesMessage msg = null;
        PersonaBean personaBean = getImplementPersonaBO().obtenerPersonaByCi(this);

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargado", "");

        this.setEstado(personaBean.getEstado());
        this.setNombre(personaBean.getNombre());
        this.setApp(personaBean.getApp());
        this.setApm(personaBean.getApm());
        this.setCuenta(personaBean.getCuenta());
        this.setContrasenia(personaBean.getContrasenia());

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    /**
     * @return the listaDocentes
     */
    public List<DocenteBean> getListaDocentes() {
        return listaDocentes;
    }

    /**
     * @param listaDocentes the listaDocentes to set
     */
    public void setListaDocentes(List<DocenteBean> listaDocentes) {
        this.listaDocentes = listaDocentes;
    }
    
    public void ObtenerDocenteByIdDirectorCarrera(){
        //-ObtenerDocenteByIdDirectorCarrera
        DirectorCarreraBean dcb=new DirectorCarreraBean();
        dcb.setCi( this.getCi() );
        setListaDocentes( implementPersonaBO.ObtenerDocenteByIdDirectorCarrera(dcb) );
        mapearListaDocentes();
    }
    public void mapearListaDocentes(){
        mapaListaDocente=(new LinkedHashMap<>());
        for( DocenteBean obj: getListaDocentes()){
            getMapaListaDocente().put(obj.getNombreCompleto(),obj.getCi() );
        }
    }

    /**
     * @return the mapaListaDocente
     */
    public Map<String, String> getMapaListaDocente() {
        return mapaListaDocente;
    }

    /**
     * @param mapaListaDocente the mapaListaDocente to set
     */
    public void setMapaListaDocente(Map<String, String> mapaListaDocente) {
        this.mapaListaDocente = mapaListaDocente;
    }
}
