<%@page import="com.sun.java.swing.plaf.motif.resources.motif"%>
<%@page import="dea.service.VerificarUsuarioService"%>
<%@page import="dea.model.Formulario"%>
<%@page import="dea.service.ListasEntidades"%>
<%@page import="dea.model.Universidad"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="javax.swing.JOptionPane" %>
<%  //VERIFICAR CUENTA, TIEMPO DE VIDA DE LA CONEXION
    Long TypeUsuarioApp = (Long) session.getAttribute("TypeUsuarioApp");
    dea.model.UsuarioModel usuarioModel = (dea.model.UsuarioModel) session.getAttribute("usuarioModel");
    dea.dao.UsuarioDaoImplement usuarioDaoImplement = (dea.dao.UsuarioDaoImplement) session.getAttribute("usuarioDaoImplement");

    dea.model.Persona persona = new dea.model.Persona();
    dea.model.Formulario formulario = new Formulario();
    dea.service.VerificarUsuarioService verificarUsuarioService = new VerificarUsuarioService();

    //Long id_formulario = Long.parseLong(verificarUsuarioService.desencriptar((String) session.getAttribute("id_formulario"), verificarUsuarioService.getClave()));
    Long id_formulario = Long.parseLong((String) request.getParameter("id_formulario"));
    formulario.setId_Formulario(id_formulario);
    List<dea.model.Estructura> estructuras;
    List<dea.model.Contenido> contenidos;

    Integer i = 0;
    Integer n = 0;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page, perfilll</title>
        <link rel="stylesheet" type="text/css" href="css/script_disenio_formulario.css"/>
        <script src="js/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
        <script src="js/script_disenio_formulario.js" type="text/javascript"></script>     

        <script src="js/nicEdit-latest.js" type="text/javascript"></script>
        <script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
    </head>
    <body>        
        <p><!--jsp:include page="CabeceraView.html" flush="true"/ --></p>
        <!-- INICIA PERFIL Y SUS DATOS PERSONALES --> 
        <%

            persona.Get_Persona_DB(usuarioModel, usuarioDaoImplement);
            out.print("<div id=\"Marco_Principal\">");
            out.print("<div id=\"Marco_P_Titulo\">");
            out.print("Datos Personales");
            out.print("</div>");
            out.print("<div id=\"Marco_P_Interior\" >");
            out.print("<div id=\"Marco_P_I_Detalle\">Cedula Identidad: " + persona.getCi() + "</div>");
            out.print("<div id=\"Marco_P_I_Detalle\">Nombre: " + persona.getNombre() + "</div>");
            out.print("<div id=\"Marco_P_I_Detalle\">Apellido: " + persona.getApellido() + "</div>");
            //
            //
            out.print("</div>");
            out.print("<div id=\"Marco_P_Titulo\">");
            out.print("<a href=\"modificardatos.htm\">Modificar Datos</a>");
            out.print("</div>");
            out.print("</div>");
        %>
        21232f297a57a5a743894a0e4a801fc3<br/>
        <!-- AUMENTAR LAS PROPIEDADES DE CADA ACTOR (GRADO ACADEMICO) -->
        <!-- FIN PERFIL Y SUS DATOS PERSONALES -->
        <div style="width: 500px;">-</div>
        <div><h1>VISTA DEL FORMULARIO</h1></div>
        <div id="Marco_Form_Principal">
            <%
                formulario = usuarioDaoImplement.Obtener_Formulario_Id_Formulario(formulario);
                out.print("<div id=\"Marco_Form_P_Titulo\">Nombre: " + formulario.getNombre() + "</div>");
                out.print("<div id=\"Marco_Form_P_Titulo\">Categoria: " + formulario.getCategoria() + "</div>");
                out.print("<div id=\"Marco_Form_P_Titulo\">Dirigido: " + formulario.getDirigido() + "</div>");
                out.print("<div id=\"Marco_Form_P_Titulo\">Observaciones: " + formulario.getObservaciones() + "</div>");
            %>
        </div>
        <!-- ---------------poner modelos-------------------- -->
        <!-- --------------------------------------------------------------------- -->
         <div class="Marco_General">
            <%
                //formulario  
                estructuras = usuarioDaoImplement.Obtener_Estructura_Id_Formulario(formulario);
                contenidos = usuarioDaoImplement.Obtener_Contenido_Id_Formulario(formulario);
                //
                List<String> estructura_imprimir = verificarUsuarioService.Imprimir_Estructura_Preview(estructuras, contenidos);
                Iterator<String> it = estructura_imprimir.iterator();
                String y = new String();
                while (it.hasNext()) {
                    y = it.next();
                    out.print(y);
                }

                ///////////////////////
%>
        </div>
    </body>
</html> 
