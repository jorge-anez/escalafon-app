/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IIdentificadorRegistroFormularioBO;
import bo.edu.uto.dea.jhs.persistence.Contenido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author VIIII1
 */
public class IdentificadorRegistroFormularioBean implements Serializable{

    /**
     * @return the tipoDatos
     */
    public String[] getTipoDatos() {
        return tipoDatos;
    }

    /**
     * @return the tipoContenedores
     */
    public String[] getTipoContenedores() {
        return tipoContenedores;
    }
    private long idIdentificadorRegistroFormulario;
    private DocumentoBean documentoBean;
    private IdentificadorRegistroMateriaBean identificadorRegistroMateriaBean;
    private PersonaBean personaBean;
    
    private String inicio;
    
    private IIdentificadorRegistroFormularioBO implementIdentificadorRegistroFormularioBO;
    
    private List<EstructuraBean> estructuraBeans;
    private List<ContenidoBean> contenidoBeans;
    
    private List<EstructuraRespuestaBean> estructuraRespuestaBeans;
    private List<ContenidoRespuestaBean> contenidoRespuestaBeans;
    
    private long size_estructuras;
    private long size_contenidos;
    private long size_estructura_Respuestas;
    private long size_contenido_Respuestas;
    
    private final static String[] tipoDatos;
    private final static String[] tipoContenedores;


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

    public IdentificadorRegistroFormularioBean() {
        this.idIdentificadorRegistroFormulario=0;
        this.inicio="";
    }
    
    
    
    /**
     * @return the idIdentificadorRegistroFormulario
     */
    public long getIdIdentificadorRegistroFormulario() {
        return idIdentificadorRegistroFormulario;
    }

    /**
     * @param idIdentificadorRegistroFormulario the idIdentificadorRegistroFormulario to set
     */
    public void setIdIdentificadorRegistroFormulario(long idIdentificadorRegistroFormulario) {
        this.idIdentificadorRegistroFormulario = idIdentificadorRegistroFormulario;
    }

    /**
     * @return the documentoBean
     */
    public DocumentoBean getDocumentoBean() {
        return documentoBean;
    }

    /**
     * @param documentoBean the documentoBean to set
     */
    public void setDocumentoBean(DocumentoBean documentoBean) {
        this.documentoBean = documentoBean;
    }

    /**
     * @return the identificadorRegistroMateriaBean
     */
    public IdentificadorRegistroMateriaBean getIdentificadorRegistroMateriaBean() {
        return identificadorRegistroMateriaBean;
    }

    /**
     * @param identificadorRegistroMateriaBean the identificadorRegistroMateriaBean to set
     */
    public void setIdentificadorRegistroMateriaBean(IdentificadorRegistroMateriaBean identificadorRegistroMateriaBean) {
        this.identificadorRegistroMateriaBean = identificadorRegistroMateriaBean;
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
     * @return the implementIdentificadorRegistroFormularioBO
     */
    public IIdentificadorRegistroFormularioBO getImplementIdentificadorRegistroFormularioBO() {
        return implementIdentificadorRegistroFormularioBO;
    }

    /**
     * @param implementIdentificadorRegistroFormularioBO the implementIdentificadorRegistroFormularioBO to set
     */
    public void setImplementIdentificadorRegistroFormularioBO(IIdentificadorRegistroFormularioBO implementIdentificadorRegistroFormularioBO) {
        this.implementIdentificadorRegistroFormularioBO = implementIdentificadorRegistroFormularioBO;
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
     * @return the estructuraRespuestaBeans
     */
    public List<EstructuraRespuestaBean> getEstructuraRespuestaBeans() {
        return estructuraRespuestaBeans;
    }

    /**
     * @param estructuraRespuestaBeans the estructuraRespuestaBeans to set
     */
    public void setEstructuraRespuestaBeans(List<EstructuraRespuestaBean> estructuraRespuestaBeans) {
        this.estructuraRespuestaBeans = estructuraRespuestaBeans;
    }

    /**
     * @return the contenidoRespuestaBeans
     */
    public List<ContenidoRespuestaBean> getContenidoRespuestaBeans() {
        return contenidoRespuestaBeans;
    }

    /**
     * @param contenidoRespuestaBeans the contenidoRespuestaBeans to set
     */
    public void setContenidoRespuestaBeans(List<ContenidoRespuestaBean> contenidoRespuestaBeans) {
        this.contenidoRespuestaBeans = contenidoRespuestaBeans;
    }
    
    public void cargarIdentificadorRegistroFormularioBean(){
        setEstructuraRespuestaBeans( implementIdentificadorRegistroFormularioBO.obtenerEstructuraRespuestaIdIdentificadorRegistroFormulario(this) );
        setContenidoRespuestaBeans( implementIdentificadorRegistroFormularioBO.obtenerContenidoRespuestaIdIdentificadorRegistroFormulario(this) );
        
        setEstructuraBeans( implementIdentificadorRegistroFormularioBO.obtenerEstructuraIdIdentificadorRegistroFormulario(this) );
        setContenidoBeans( implementIdentificadorRegistroFormularioBO.obtenerContenidoIdIdentificadorRegistroFormulario(this) );
    }
    
    public List<String> Imprimir_Estructura_Respuesta_Recursivo(long Id_Estructura_Respuesta, String tab) {
        List<String> estructura_imprimir = new ArrayList<String>();
        List<String> estructura_imprimir2 = new ArrayList<String>();
        List<String> estructura_imprimir3 = new ArrayList<String>();
        Iterator<String> it;
        EstructuraRespuestaBean estructura_Respuesta = new EstructuraRespuestaBean();
        ContenidoRespuestaBean contenido_Respuesta = new ContenidoRespuestaBean();
        ContenidoRespuestaBean contenido_Respuesta2 = new ContenidoRespuestaBean();

        ContenidoBean contenido = new ContenidoBean(); //
        ContenidoBean contenido2 = new ContenidoBean(); //

        long Siguiente_Hermano = new Long(0);
        Integer i = 0;
        Integer j = 0;
        Integer k = 0;
        String str = "";
        for (i = 0; i < size_estructura_Respuestas; i++) {
            estructura_Respuesta = estructuraRespuestaBeans.get(i);
            if (estructura_Respuesta.getIdEstructuraRespuesta() == Id_Estructura_Respuesta) {
                break;
            }
        }
        estructura_imprimir.add("<div id=\"M-" + estructura_Respuesta.getIdEstructuraRespuesta() + "-" + estructura_Respuesta.getTipo() + "\" class=\"Marco_General\">");
        //estructura_imprimir.add("<div id=\"Contenedor\" class=\"Marco_Editor_Principal\">" + estructura_Respuesta.getId_Estructura_Respuesta());
        estructura_imprimir.add("<div id=\"Contenedor\" class=\"Marco_Editor_Principal\">");

        if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[0]) == 0) {
            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if (contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) {
                    //encontrar su contenido par
                    for (k = 0; k < size_contenidos; k++) {
                        contenido = contenidoBeans.get(k);
                        if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {
                            estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getIdContenidoRespuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            estructura_imprimir.add(tab + contenido.getPregunta());
                            //---
                            estructura_imprimir.add("</div>");
                            k = Integer.parseInt(size_contenidos+"") + 1;
                        }
                    }
                    break;
                }
            }
        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[1]) == 0) {
            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if (contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) {
                    for (k = 0; k < size_contenidos; k++) {
                        contenido = contenidoBeans.get(k);
                        if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                            estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getIdContenidoRespuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            estructura_imprimir.add(tab + contenido.getPregunta());

                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Texto_Simple_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 200px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add("<textarea id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" class=\"Texto_Enriquecido_insert\"  style=\"width: 200px; height: 40px;\" >" + contenido_Respuesta.getRespuesta() + "</textarea> <!--br /-->");
                                estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"button\" value=\"Guadar\" class=\"Texto_Enriquecido_insert_B\">");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Entero_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Real_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Fecha_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Hora_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                //preguntar si es true o no
                                if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                                    estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" checked value=\"\"> <!--br /-->");
                                } else {
                                    estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" value=\"\"> <!--br /-->");
                                }
                            }
                            //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");

                            estructura_imprimir.add("</div>");

                            k = Integer.parseInt(size_contenidos+"") + 1;
                        }
                    }
                    break;
                }
            }
        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[2]) == 0) {

            boolean existe_Siguiente = true;
            Integer Numero_Columnas = 0;
            Integer Contador = 0;
            List<String> tipos = new ArrayList<String>();
            //guardar los tipos de las columnas
            estructura_imprimir.add("<table border=\"1\" >");
            estructura_imprimir.add("<tr>");
            for (i = 0; i < size_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if ((contenido.getIdEstructura() == estructura_Respuesta.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add("<td>" + contenido.getPregunta() + "</td>");
                    tipos.add(new String(contenido.getTipoDato()));
                    Numero_Columnas++;

                    existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < size_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(size_contenidos+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            estructura_imprimir.add("<td>" + contenido.getPregunta() + "</td>");
                            tipos.add(new String(contenido.getTipoDato()));
                            Numero_Columnas++;
                        }
                    }
                    estructura_imprimir.add("<td>" + "Eliminar" + "</td>");
                    //estructura_imprimir.add("<td>" + "Guardar" + "</td>");
                    //Numero_Columnas++;

                    break;
                }
            }
            estructura_imprimir.add("</tr>");




            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if ((contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) && (contenido_Respuesta.getIdContenidoRespuesta() == contenido_Respuesta.getIdContenidoRespuestaPredecesor() )) {
                    estructura_imprimir.add("<tr>");
                    estructura_imprimir.add("<td>");
                    //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                    Contador = 0;
                    if (tipos.get(Contador).compareTo(tipoDatos[0]) == 0) {
                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Texto_Simple_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 200px; height: 20px;\" > <!--br /-->");
                    } else if (tipos.get(Contador).compareTo(tipoDatos[1]) == 0) {
                        estructura_imprimir.add("<textarea id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\"  class=\"Texto_Enriquecido_insert\" style=\"width: 200px; height: 40px;\" >" + contenido_Respuesta.getRespuesta() + "</textarea> <!--br /-->");
                        estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"button\" value=\"Guadar\" class=\"Texto_Enriquecido_insert_B\">");
                    } else if (tipos.get(Contador).compareTo(tipoDatos[2]) == 0) {
                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Entero_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (tipos.get(Contador).compareTo(tipoDatos[3]) == 0) {
                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Real_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (tipos.get(Contador).compareTo(tipoDatos[4]) == 0) {
                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Fecha_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (tipos.get(Contador).compareTo(tipoDatos[5]) == 0) {
                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Hora_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                    } else if (tipos.get(Contador).compareTo(tipoDatos[6]) == 0) {
                        if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                            estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" checked value=\"\"> <!--br /-->");
                        } else {
                            estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" value=\"\"> <!--br /-->");
                        }
                    }
                    estructura_imprimir.add("</td>");
                    Contador++;
                    if (Contador >= Numero_Columnas) {
                        Contador = 0;
                        estructura_imprimir.add("<td><input id=\"E-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"E-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"button\" value=\"eliminar\" class=\"eliminar\"></td> ");
                        estructura_imprimir.add("</tr></tr>");
                    }
                    //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> ");
                    //estructura_imprimir.add("</div>");

                    existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < size_contenido_Respuestas; j++) {
                            contenido_Respuesta2 = contenidoRespuestaBeans.get(j);
                            if ((contenido_Respuesta2.getIdContenidoRespuesta() != contenido_Respuesta2.getIdContenidoRespuestaPredecesor()) && (contenido_Respuesta2.getIdContenidoRespuestaPredecesor() == contenido_Respuesta.getIdContenidoRespuesta())) {
                                contenido_Respuesta = contenidoRespuestaBeans.get(j);
                                j = Integer.parseInt(size_contenido_Respuestas+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            estructura_imprimir.add("<td>");
                            if (tipos.get(Contador).compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Texto_Simple_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 200px; height: 20px;\" > <!--br /-->");
                            } else if (tipos.get(Contador).compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add("<textarea id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\"  class=\"Texto_Enriquecido_insert\" style=\"width: 200px; height: 40px;\" >" + contenido_Respuesta.getRespuesta() + "</textarea> <!--br /-->");
                                estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"button\" value=\"Guadar\" class=\"Texto_Enriquecido_insert_B\">");
                            } else if (tipos.get(Contador).compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Entero_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (tipos.get(Contador).compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Real_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (tipos.get(Contador).compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Fecha_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (tipos.get(Contador).compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Hora_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (tipos.get(Contador).compareTo(tipoDatos[6]) == 0) {
                                if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                                    estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" checked value=\"\"> <!--br /-->");
                                } else {
                                    estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" value=\"\"> <!--br /-->");
                                }
                            }
                            estructura_imprimir.add("</td>");
                            Contador++;
                            if (Contador >= Numero_Columnas) {
                                Contador = 0;
                                estructura_imprimir.add("<td><input id=\"E-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"E-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"button\" value=\"eliminar\" class=\"eliminar\"></td> ");
                                estructura_imprimir.add("</tr></tr>");
                            }
                            //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> ");
                            //estructura_imprimir.add("</div>");
                        }
                    }
                    estructura_imprimir.add("</tr>");

                    break;
                }
            }

            estructura_imprimir.add("</table>");
            estructura_imprimir.add("<input id=\"AE-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" name=\"AE-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" type=\"button\" value=\"Agregar Fila\" class=\"\"> <br />");


        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[3]) == 0) {

            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if ((contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) && (contenido_Respuesta.getIdContenidoRespuesta() == contenido_Respuesta.getIdContenidoRespuestaPredecesor())) {

                    for (k = 0; k < size_contenidos; k++) {
                        contenido = contenidoBeans.get(k);
                        if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                            //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            //verificar cual esta checasdo
                            estructura_imprimir.add(tab + contenido.getPregunta() + " <input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"radio\" name=\"opcion-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" class=\"Opcion_insert\" value=\"\" /> &nbsp;&nbsp;&nbsp;&nbsp;");
                            //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");
                            //estructura_imprimir.add("</div>");

                            k = Integer.parseInt(size_contenidos+"") + 1;
                        }
                    }



                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < size_contenido_Respuestas; j++) {
                            contenido_Respuesta2 = contenidoRespuestaBeans.get(j);
                            if ((contenido_Respuesta2.getIdContenidoRespuesta() != contenido_Respuesta2.getIdContenidoRespuestaPredecesor()) && (contenido_Respuesta2.getIdContenidoRespuestaPredecesor() == contenido_Respuesta.getIdContenidoRespuesta())) {
                                contenido_Respuesta = contenidoRespuestaBeans.get(j);
                                j = Integer.parseInt(size_contenido_Respuestas+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            for (k = 0; k < size_contenidos; k++) {
                                contenido = contenidoBeans.get(k);
                                if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                                    //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                                    //verificar cual esta checasdo
                                    estructura_imprimir.add(contenido.getPregunta() + " <input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"radio\" name=\"opcion-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" class=\"Opcion_insert\" value=\"\" /> &nbsp;&nbsp;&nbsp;&nbsp;");
                                    //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");
                                    //estructura_imprimir.add("</div>");

                                    k = Integer.parseInt(size_contenidos+"") + 1;
                                }
                            }

                        }
                    }
                    break;
                }


            }
        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[4]) == 0) {

            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if ((contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) && (contenido_Respuesta.getIdContenidoRespuesta() == contenido_Respuesta.getIdContenidoRespuestaPredecesor())) {
                    for (k = 0; k < size_contenidos; k++) {
                        contenido = contenidoBeans.get(k);
                        if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                            //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            estructura_imprimir.add(tab + contenido.getPregunta());

                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Texto_Simple_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 200px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add("<textarea id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\"  class=\"Texto_Enriquecido_insert\" style=\"width: 200px; height: 40px;\" >" + contenido_Respuesta.getRespuesta() + "</textarea> <!--br /-->");
                                estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"button\" value=\"Guadar\" class=\"Texto_Enriquecido_insert_B\">");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Entero_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Real_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Fecha_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Hora_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                                    estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" checked value=\"\"> <!--br /-->");
                                } else {
                                    estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" value=\"\"> <!--br /-->");
                                }
                            }
                            estructura_imprimir.add("&nbsp;&nbsp;&nbsp;&nbsp;");
                            //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");

                            //estructura_imprimir.add("</div>");

                            k = Integer.parseInt(size_contenidos+"") + 1;
                        }
                    }

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < size_contenido_Respuestas; j++) {
                            contenido_Respuesta2 = contenidoRespuestaBeans.get(j);
                            if ((contenido_Respuesta2.getIdContenidoRespuesta() != contenido_Respuesta2.getIdContenidoRespuestaPredecesor()) && (contenido_Respuesta2.getIdContenidoRespuestaPredecesor() == contenido_Respuesta.getIdContenidoRespuesta())) {
                                contenido_Respuesta = contenidoRespuestaBeans.get(j);
                                j = Integer.parseInt(size_contenido_Respuestas+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            //imprimir
                            for (k = 0; k < size_contenidos; k++) {
                                contenido = contenidoBeans.get(k);
                                if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                                    //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                                    estructura_imprimir.add(contenido.getPregunta());

                                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Texto_Simple_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 200px; height: 20px;\" > <!--br /-->");
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                        estructura_imprimir.add("<textarea id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\"  class=\"Texto_Enriquecido_insert\" style=\"width: 200px; height: 40px;\" >" + contenido_Respuesta.getRespuesta() + "</textarea> <!--br /-->");
                                        estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"B-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"button\" value=\"Guadar\" class=\"Texto_Enriquecido_insert_B\">");
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Entero_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Real_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Fecha_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Hora_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                        if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                                            estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" checked value=\"\"> <!--br /-->");
                                        } else {
                                            estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"checkbox\" class=\"Verdadero_Falso_insert\" value=\"\"> <!--br /-->");
                                        }
                                    }
                                    estructura_imprimir.add("&nbsp;&nbsp;&nbsp;&nbsp;");
                                    //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");

                                    //estructura_imprimir.add("</div>");

                                    k = Integer.parseInt(size_contenidos+"") + 1;
                                }
                            }

                        }
                    }
                    break;
                }
            }
        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[5]) == 0) {
            estructura_imprimir.add("<input id=\"EGP-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" name=\"EGP-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" type=\"button\" value=\"Eliminar Grupo\" class=\"\"> <br />");
            estructura_imprimir.add("<input id=\"AGP-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" name=\"AGP-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" type=\"button\" value=\"Agregar Grupo\" class=\"\"> <br />");
        }

////////////////////
        if (estructura_Respuesta.getHijos().compareTo("") != 0) {
            StringTokenizer tokens = new StringTokenizer(estructura_Respuesta.getHijos(), "-");
            while (tokens.hasMoreTokens()) {
                str = tokens.nextToken();
                Long n = Long.valueOf(str);
                estructura_imprimir2 = Imprimir_Estructura_Respuesta_Recursivo(n, tab + " &nbsp;&nbsp;&nbsp;&nbsp;");
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
        /*
         estructura_imprimir.add("<div id=\"M-" + estructura_Respuesta.getId_Estructura_Respuesta() + "-" + estructura_Respuesta.getTipo() + "-botones\" >");
         //JOptionPane.showMessageDialog(null, "aaa");
         if ((estructura_Respuesta.getTipo().compareTo("columna") == 0)) {
            
         } else if ((estructura_Respuesta.getTipo().compareTo("opciones") == 0)) {
            
         } else if ((estructura_Respuesta.getTipo().compareTo("dependencia") == 0)) {
            
         } else if ((estructura_Respuesta.getTipo().compareTo("contenedor") == 0)
         || (estructura_Respuesta.getTipo().compareTo("texto") == 0)
         || (estructura_Respuesta.getTipo().compareTo("pregunta") == 0)
         || (estructura_Respuesta.getTipo().compareTo("grupopregunta") == 0)) {
            
         }

         estructura_imprimir.add("</div>");*/

        estructura_imprimir.add("</div>");

        Siguiente_Hermano = estructura_Respuesta.getIdEstructuraRespuesta();
        for (i = 0; i < size_estructura_Respuestas; i++) {
            estructura_Respuesta = estructuraRespuestaBeans.get(i);
            if (estructura_Respuesta.getIdEstructuraRespuestaByIdEstructuraRespuestaPredecesor() == Siguiente_Hermano && estructura_Respuesta.getIdEstructuraRespuestaByIdEstructuraRespuestaPredecesor() != estructura_Respuesta.getIdEstructuraRespuesta()) {
                break;
            }
        }
        if (i < size_estructura_Respuestas) {
            estructura_imprimir2 = Imprimir_Estructura_Respuesta_Recursivo(estructura_Respuesta.getIdEstructuraRespuesta(), tab);
            it = estructura_imprimir2.iterator();
            while (it.hasNext()) {
                str = it.next();
                estructura_imprimir.add(str);
            }
        }
        return estructura_imprimir;
    }

    public List<String> Imprimir_Estructura_Respuesta(){
            //List<EstructuraRespuestaBean> estructura_Respuestas, 
        //List<ContenidoRespuestaBean> contenido_Respuestas, 
        //List<Estructura> estructuras, 
        //List<Contenido> contenidos) {
        
        List<String> estructura_imprimir = new ArrayList<String>();

        //this.estructuraBeans = estructuraBeans;
        if (estructuraBeans != null) {
            this.size_estructuras = new Long(estructuraBeans.size());
        } else {
            this.size_estructuras = new Long(0);
        }
        //this.contenidoBeans = contenidoBeans;
        if (contenidoBeans != null) {
            this.size_contenidos = new Long(contenidoBeans.size());
        } else {
            this.size_contenidos = new Long(0);
        }
        /////////////////////////////////////////////////////////////
        //this.estructuraRespuestaBeans = estructuraRespuestaBeans;
        if (estructuraRespuestaBeans != null) {
            this.size_estructura_Respuestas = new Long(estructuraRespuestaBeans.size());
        } else {
            this.size_estructura_Respuestas = new Long(0);
        }
        //this.contenidoRespuestaBeans = contenidoRespuestaBeans;
        if (contenidoRespuestaBeans != null) {
            this.size_contenido_Respuestas = new Long(contenidoRespuestaBeans.size());
        } else {
            this.size_contenido_Respuestas = new Long(0);
        }
        EstructuraRespuestaBean estructura_Respuesta = new EstructuraRespuestaBean();
        Integer j = 0;
        for (j = 0; j < estructuraRespuestaBeans.size(); j++) {
            estructura_Respuesta = estructuraRespuestaBeans.get(j);
            if ((estructura_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuestaByIdEstructuraRespuestaPredecesor() ) && (estructura_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuestaByIdEstructuraRespuestaPadre() )) {
                break;
            }
        }
        estructura_imprimir = Imprimir_Estructura_Respuesta_Recursivo(estructura_Respuesta.getIdEstructuraRespuesta(), "");
        return estructura_imprimir;
    }

    /**
     * @return the inicio
     */
    public String getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(String inicio) {
        this.inicio = inicio;
    }
    
    public Long actualizarContenidoRespuestaLlenado( ContenidoRespuestaBean crb ){
        return implementIdentificadorRegistroFormularioBO.actualizarContenidoRespuestaLlenado(crb);
    }
    
    public List<String> Imprimir_Estructura_Respuesta_Recursivo_preview(long Id_Estructura_Respuesta, String tab) {
        List<String> estructura_imprimir = new ArrayList<String>();
        List<String> estructura_imprimir2 = new ArrayList<String>();
        List<String> estructura_imprimir3 = new ArrayList<String>();
        Iterator<String> it;
        EstructuraRespuestaBean estructura_Respuesta = new EstructuraRespuestaBean();
        ContenidoRespuestaBean contenido_Respuesta = new ContenidoRespuestaBean();
        ContenidoRespuestaBean contenido_Respuesta2 = new ContenidoRespuestaBean();

        ContenidoBean contenido = new ContenidoBean(); //
        ContenidoBean contenido2 = new ContenidoBean(); //

        long Siguiente_Hermano = new Long(0);
        Integer i = 0;
        Integer j = 0;
        Integer k = 0;
        String str = "";
        for (i = 0; i < size_estructura_Respuestas; i++) {
            estructura_Respuesta = estructuraRespuestaBeans.get(i);
            if (estructura_Respuesta.getIdEstructuraRespuesta() == Id_Estructura_Respuesta) {
                break;
            }
        }
        estructura_imprimir.add("<div id=\"M-" + estructura_Respuesta.getIdEstructuraRespuesta() + "-" + estructura_Respuesta.getTipo() + "\" class=\"Marco_General\">");
        //estructura_imprimir.add("<div id=\"Contenedor\" class=\"Marco_Editor_Principal\">" + estructura_Respuesta.getId_Estructura_Respuesta());
        estructura_imprimir.add("<div id=\"Contenedor\" class=\"Marco_Editor_Principal\">");

        if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[0]) == 0) {
            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if (contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) {
                    //encontrar su contenido par
                    for (k = 0; k < size_contenidos; k++) {
                        contenido = contenidoBeans.get(k);
                        if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {
                            estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getIdContenidoRespuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            estructura_imprimir.add(tab + contenido.getPregunta());
                            //---
                            estructura_imprimir.add("</div>");
                            k = Integer.parseInt(size_contenidos+"") + 1;
                        }
                    }
                    break;
                }
            }
        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[1]) == 0) {
            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if (contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) {
                    for (k = 0; k < size_contenidos; k++) {
                        contenido = contenidoBeans.get(k);
                        if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                            estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getIdContenidoRespuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            estructura_imprimir.add(tab + contenido.getPregunta() + " ");

                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add( contenido_Respuesta.getRespuesta() );
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                //preguntar si es true o no
                                if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                                    estructura_imprimir.add("Si");
                                } else {
                                    estructura_imprimir.add("No");
                                }
                            }
                            //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");

                            estructura_imprimir.add("</div>");

                            k = Integer.parseInt(size_contenidos+"") + 1;
                        }
                    }
                    break;
                }
            }
        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[2]) == 0) {

            boolean existe_Siguiente = true;
            Integer Numero_Columnas = 0;
            Integer Contador = 0;
            List<String> tipos = new ArrayList<String>();
            //guardar los tipos de las columnas
            estructura_imprimir.add("<table border=\"1\" >");
            estructura_imprimir.add("<tr>");
            for (i = 0; i < size_contenidos; i++) {
                contenido = contenidoBeans.get(i);
                if ((contenido.getIdEstructura() == estructura_Respuesta.getIdEstructura()) && (contenido.getIdContenido() == contenido.getIdContenidoPredecesor())) {
                    estructura_imprimir.add("<td>" + contenido.getPregunta() + "</td>");
                    tipos.add(new String(contenido.getTipoDato()));
                    Numero_Columnas++;

                    existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < size_contenidos; j++) {
                            contenido2 = contenidoBeans.get(j);
                            if ((contenido2.getIdContenido() != contenido2.getIdContenidoPredecesor()) && (contenido2.getIdContenidoPredecesor() == contenido.getIdContenido())) {
                                contenido = contenidoBeans.get(j);
                                j = Integer.parseInt(size_contenidos+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            estructura_imprimir.add("<td>" + contenido.getPregunta() + "</td>");
                            tipos.add(new String(contenido.getTipoDato()));
                            Numero_Columnas++;
                        }
                    }
                    estructura_imprimir.add("<td>" + "Eliminar" + "</td>");
                    //estructura_imprimir.add("<td>" + "Guardar" + "</td>");
                    //Numero_Columnas++;

                    break;
                }
            }
            estructura_imprimir.add("</tr>");




            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if ((contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) && (contenido_Respuesta.getIdContenidoRespuesta() == contenido_Respuesta.getIdContenidoRespuestaPredecesor())) {
                    estructura_imprimir.add("<tr>");
                    estructura_imprimir.add("<td>");
                    //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                    Contador = 0;
                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                        estructura_imprimir.add( contenido_Respuesta.getRespuesta() );
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                    } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                        //preguntar si es true o no
                        if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                            estructura_imprimir.add("Si");
                        } else {
                            estructura_imprimir.add("No");
                        }
                    }
                    estructura_imprimir.add("</td>");
                    Contador++;
                    if (Contador >= Numero_Columnas) {
                        Contador = 0;
                        estructura_imprimir.add("<td><input id=\"E-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"E-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"button\" value=\"eliminar\" class=\"eliminar\"></td> ");
                        estructura_imprimir.add("</tr></tr>");
                    }
                    //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> ");
                    //estructura_imprimir.add("</div>");

                    existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < size_contenido_Respuestas; j++) {
                            contenido_Respuesta2 = contenidoRespuestaBeans.get(j);
                            if ((contenido_Respuesta2.getIdContenidoRespuesta() != contenido_Respuesta2.getIdContenidoRespuestaPredecesor()) && (contenido_Respuesta2.getIdContenidoRespuestaPredecesor() == contenido_Respuesta.getIdContenidoRespuesta())) {
                                contenido_Respuesta = contenidoRespuestaBeans.get(j);
                                j = Integer.parseInt(size_contenido_Respuestas+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            estructura_imprimir.add("<td>");
                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add( contenido_Respuesta.getRespuesta() );
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                //preguntar si es true o no
                                if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                                    estructura_imprimir.add("Si");
                                } else {
                                    estructura_imprimir.add("No");
                                }
                            }
                            estructura_imprimir.add("</td>");
                            Contador++;
                            if (Contador >= Numero_Columnas) {
                                Contador = 0;
                                estructura_imprimir.add("<td><input id=\"E-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"E-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"button\" value=\"eliminar\" class=\"eliminar\"></td> ");
                                estructura_imprimir.add("</tr></tr>");
                            }
                            //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\"> ");
                            //estructura_imprimir.add("</div>");
                        }
                    }
                    estructura_imprimir.add("</tr>");

                    break;
                }
            }

            estructura_imprimir.add("</table>");
            estructura_imprimir.add("<input id=\"AE-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" name=\"AE-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" type=\"button\" value=\"Agregar Fila\" class=\"\"> <br />");


        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[3]) == 0) {

            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if ((contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) && (contenido_Respuesta.getIdContenidoRespuesta() == contenido_Respuesta.getIdContenidoRespuestaPredecesor())) {

                    for (k = 0; k < size_contenidos; k++) {
                        contenido = contenidoBeans.get(k);
                        if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                            //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            //verificar cual esta checasdo
                            estructura_imprimir.add(tab + contenido.getPregunta() + " <input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"radio\" name=\"opcion-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" class=\"Opcion_insert\" value=\"\" /> &nbsp;&nbsp;&nbsp;&nbsp;");
                            //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");
                            //estructura_imprimir.add("</div>");

                            k = Integer.parseInt(size_contenidos+"") + 1;
                        }
                    }



                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < size_contenido_Respuestas; j++) {
                            contenido_Respuesta2 = contenidoRespuestaBeans.get(j);
                            if ((contenido_Respuesta2.getIdContenidoRespuesta() != contenido_Respuesta2.getIdContenidoRespuestaPredecesor()) && (contenido_Respuesta2.getIdContenidoRespuestaPredecesor() == contenido_Respuesta.getIdContenidoRespuesta())) {
                                contenido_Respuesta = contenidoRespuestaBeans.get(j);
                                j = Integer.parseInt(size_contenido_Respuestas+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            for (k = 0; k < size_contenidos; k++) {
                                contenido = contenidoBeans.get(k);
                                if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                                    //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                                    //verificar cual esta checasdo
                                    estructura_imprimir.add(contenido.getPregunta() + " <input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"radio\" name=\"opcion-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" class=\"Opcion_insert\" value=\"\" /> &nbsp;&nbsp;&nbsp;&nbsp;");
                                    //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");
                                    //estructura_imprimir.add("</div>");

                                    k = Integer.parseInt(size_contenidos+"") + 1;
                                }
                            }

                        }
                    }
                    break;
                }


            }
        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[4]) == 0) {

            for (i = 0; i < size_contenido_Respuestas; i++) {
                contenido_Respuesta = contenidoRespuestaBeans.get(i);
                if ((contenido_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuesta()) && (contenido_Respuesta.getIdContenidoRespuesta() == contenido_Respuesta.getIdContenidoRespuestaPredecesor())) {
                    for (k = 0; k < size_contenidos; k++) {
                        contenido = contenidoBeans.get(k);
                        if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                            //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                            estructura_imprimir.add(tab + contenido.getPregunta());

                            if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                estructura_imprimir.add( contenido_Respuesta.getRespuesta() );
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                            } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                //preguntar si es true o no
                                if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                                    estructura_imprimir.add("Si");
                                } else {
                                    estructura_imprimir.add("No");
                                }
                            }
                            estructura_imprimir.add("&nbsp;&nbsp;&nbsp;&nbsp;");
                            //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");

                            //estructura_imprimir.add("</div>");

                            k = Integer.parseInt(size_contenidos+"") + 1;
                        }
                    }

                    boolean existe_Siguiente = true;
                    while (existe_Siguiente) {
                        existe_Siguiente = false;
                        for (j = 0; j < size_contenido_Respuestas; j++) {
                            contenido_Respuesta2 = contenidoRespuestaBeans.get(j);
                            if ((contenido_Respuesta2.getIdContenidoRespuesta() != contenido_Respuesta2.getIdContenidoRespuestaPredecesor()) && (contenido_Respuesta2.getIdContenidoRespuestaPredecesor() == contenido_Respuesta.getIdContenidoRespuesta())) {
                                contenido_Respuesta = contenidoRespuestaBeans.get(j);
                                j = Integer.parseInt(size_contenido_Respuestas+"") + 1;
                                existe_Siguiente = true;
                            }
                        }
                        if (existe_Siguiente) {
                            //imprimir
                            for (k = 0; k < size_contenidos; k++) {
                                contenido = contenidoBeans.get(k);
                                if (contenido_Respuesta.getIdContenido() == contenido.getIdContenido()) {

                                    //estructura_imprimir.add("<div id=\"C-" + contenido_Respuesta.getId_Contenido_Respuesta() + "-" + estructura_Respuesta.getTipo() + "\" >");
                                    estructura_imprimir.add(contenido.getPregunta());

                                    if (contenido.getTipoDato().compareTo(tipoDatos[0]) == 0) {
                                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[1]) == 0) {
                                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[2]) == 0) {
                                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[3]) == 0) {
                                        estructura_imprimir.add("<input id=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" name=\"R-" + contenido_Respuesta.getIdContenidoRespuesta() + "\" type=\"text\" class=\"Numero_Real_insert\" value=\"" + contenido_Respuesta.getRespuesta() + "\" style=\"width: 100px; height: 20px;\" > <!--br /-->");
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[4]) == 0) {
                                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[5]) == 0) {
                                        estructura_imprimir.add(contenido_Respuesta.getRespuesta());
                                    } else if (contenido.getTipoDato().compareTo(tipoDatos[6]) == 0) {
                                        //preguntar si es true o no
                                        if (contenido_Respuesta.getRespuesta().compareTo("V") == 0) {
                                            estructura_imprimir.add("Si");
                                        } else {
                                            estructura_imprimir.add("No");
                                        }
                                    }
                                    estructura_imprimir.add("&nbsp;&nbsp;&nbsp;&nbsp;");
                                    //estructura_imprimir.add("<input id=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" name=\"B-" + contenido_Respuesta.getId_Contenido_Respuesta() + "\" type=\"button\" value=\"Guadar\" class=\"guardar\">");

                                    //estructura_imprimir.add("</div>");

                                    k = Integer.parseInt(size_contenidos+"") + 1;
                                }
                            }

                        }
                    }
                    break;
                }
            }
        } else if (estructura_Respuesta.getTipo().compareTo(tipoContenedores[5]) == 0) {
            estructura_imprimir.add("<input id=\"EGP-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" name=\"EGP-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" type=\"button\" value=\"Eliminar Grupo\" class=\"\"> <br />");
            estructura_imprimir.add("<input id=\"AGP-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" name=\"AGP-" + estructura_Respuesta.getIdEstructuraRespuesta() + "\" type=\"button\" value=\"Agregar Grupo\" class=\"\"> <br />");
        }

////////////////////
        if (estructura_Respuesta.getHijos().compareTo("") != 0) {
            StringTokenizer tokens = new StringTokenizer(estructura_Respuesta.getHijos(), "-");
            while (tokens.hasMoreTokens()) {
                str = tokens.nextToken();
                Long n = Long.valueOf(str);
                estructura_imprimir2 = Imprimir_Estructura_Respuesta_Recursivo_preview(n, tab + " &nbsp;&nbsp;&nbsp;&nbsp;");
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

        //estructura_imprimir.add("<div id=\"M-" + estructura_Respuesta.getId_Estructura_Respuesta() + "-" + estructura_Respuesta.getTipo() + "-botones\" >");
        //JOptionPane.showMessageDialog(null, "aaa");
        //if ((estructura_Respuesta.getTipo().compareTo("columna") == 0)) {
        //   
        //} else if ((estructura_Respuesta.getTipo().compareTo("opciones") == 0)) {
        //   
        //} else if ((estructura_Respuesta.getTipo().compareTo("dependencia") == 0)) {
        //   
        //} else if ((estructura_Respuesta.getTipo().compareTo("contenedor") == 0)
        //|| (estructura_Respuesta.getTipo().compareTo("texto") == 0)
        //|| (estructura_Respuesta.getTipo().compareTo("pregunta") == 0)
        //|| (estructura_Respuesta.getTipo().compareTo("grupopregunta") == 0)) {
        //  
        //}

        //estructura_imprimir.add("</div>");

        estructura_imprimir.add("</div>");

        Siguiente_Hermano = estructura_Respuesta.getIdEstructuraRespuesta();
        for (i = 0; i < size_estructura_Respuestas; i++) {
            estructura_Respuesta = estructuraRespuestaBeans.get(i);
            if (estructura_Respuesta.getIdEstructuraRespuestaByIdEstructuraRespuestaPredecesor() == Siguiente_Hermano && estructura_Respuesta.getIdEstructuraRespuestaByIdEstructuraRespuestaPredecesor() != estructura_Respuesta.getIdEstructuraRespuesta()) {
                break;
            }
        }
        if (i < size_estructura_Respuestas) {
            estructura_imprimir2 = Imprimir_Estructura_Respuesta_Recursivo_preview(estructura_Respuesta.getIdEstructuraRespuesta(), tab);
            it = estructura_imprimir2.iterator();
            while (it.hasNext()) {
                str = it.next();
                estructura_imprimir.add(str);
            }
        }
        return estructura_imprimir;
    }

    public List<String> Imprimir_Estructura_Respuesta_preview() {
        List<String> estructura_imprimir = new ArrayList<String>();

        //this.estructuraBeans = estructuraBeans;
        if (estructuraBeans != null) {
            this.size_estructuras = new Long(estructuraBeans.size());
        } else {
            this.size_estructuras = new Long(0);
        }
        //this.contenidoBeans = contenidoBeans;
        if (contenidoBeans != null) {
            this.size_contenidos = new Long(contenidoBeans.size());
        } else {
            this.size_contenidos = new Long(0);
        }
        /////////////////////////////////////////////////////////////
        //this.estructuraRespuestaBeans = estructuraRespuestaBeans;
        if (estructuraRespuestaBeans != null) {
            this.size_estructura_Respuestas = new Long(estructuraRespuestaBeans.size());
        } else {
            this.size_estructura_Respuestas = new Long(0);
        }
        //this.contenidoRespuestaBeans = contenidoRespuestaBeans;
        if (contenidoRespuestaBeans != null) {
            this.size_contenido_Respuestas = new Long(contenidoRespuestaBeans.size());
        } else {
            this.size_contenido_Respuestas = new Long(0);
        }
        EstructuraRespuestaBean estructura_Respuesta = new EstructuraRespuestaBean();
        Integer j = 0;
        for (j = 0; j < estructuraRespuestaBeans.size(); j++) {
            estructura_Respuesta = estructuraRespuestaBeans.get(j);
            if ((estructura_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuestaByIdEstructuraRespuestaPredecesor() ) && (estructura_Respuesta.getIdEstructuraRespuesta() == estructura_Respuesta.getIdEstructuraRespuestaByIdEstructuraRespuestaPadre() )) {
                break;
            }
        }
        estructura_imprimir = Imprimir_Estructura_Respuesta_Recursivo_preview(estructura_Respuesta.getIdEstructuraRespuesta(), "");
        return estructura_imprimir;
    }
}
