/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jhs.bo;

import bo.edu.uto.dea.jdbc.dao.IDocenteEscalafonDAO;
import bo.edu.uto.dea.jhs.persistence.Carrera;
import bo.edu.uto.dea.jhs.persistence.Cartilla;
import bo.edu.uto.dea.jhs.persistence.Docente;
import bo.edu.uto.dea.jhs.persistence.DocenteEscalafon;
import bo.edu.uto.dea.jhs.persistence.Evaluacion;
import bo.edu.uto.dea.jhs.persistence.EvaluacionEscalafon;
import bo.edu.uto.dea.jhs.persistence.Materia;
import bo.edu.uto.dea.jhs.persistence.Persona;
import bo.edu.uto.dea.jhs.persistence.RelacionDocenteMateria;
import bo.edu.uto.dea.jhs.persistence.Resolucion;
import bo.edu.uto.dea.jsf.bean.CarreraBean;
import bo.edu.uto.dea.jsf.bean.CartillaBean;
import bo.edu.uto.dea.jsf.bean.ContenidoCartillaViewBean;
import bo.edu.uto.dea.jsf.bean.DocenteBean;
import bo.edu.uto.dea.jsf.bean.DocenteEscalafonBean;
import bo.edu.uto.dea.jsf.bean.EscalafonBean;
import bo.edu.uto.dea.jsf.bean.EvaluacionBean;
import bo.edu.uto.dea.jsf.bean.EvaluacionEscalafonBean;
import bo.edu.uto.dea.jsf.bean.FacultadBean;
import bo.edu.uto.dea.jsf.bean.MateriaBean;
import bo.edu.uto.dea.jsf.bean.PersonaBean;
import bo.edu.uto.dea.jsf.bean.RelacionDocenteMateriaBean;
import bo.edu.uto.dea.jsf.bean.ResolucionBean;
import bo.edu.uto.dea.jsf.bean.UniversidadBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIIII1
 */
public class ImplementDocenteEscalafonBO implements IDocenteEscalafonBO, Serializable {

    private IDocenteEscalafonDAO implementDocenteEscalafonDAO;

    private String modelo(String t) {
        String m = "";
        String r = "";
        if (t.compareTo("CONTRATADO") == 0) {
            m = "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">RESOLUCION\n"
                    + "CONTRATADO</span></u></b><b><u><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></u></b></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align: center;\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">RESOLUCIÓN &nbsp;RECTORAL Nº. <span style=\"background-position: initial initial; background-repeat: initial initial;\">063/2013</span><o:p></o:p></span></u></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align: center;\"><span style=\"background-color: rgb(255, 255, 255);\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">A, 4 de febrero de 2013</span><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">CONSIDERANDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span style=\"background-color: rgb(255, 255, 255);\"><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\">Que, el Honorable Consejo Universitario, mediante resolución Nº 9/98\n"
                    + "(Art. 4to.) y 81/98 (Art. 3ro.), dispuso la aplicación del Reglamento de\n"
                    + "Evaluación Docente de la </span><span style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;\">Universidad\n"
                    + "Técnica de Oruro y en consecuencia la evaluación de los Docentes Contratados.<o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, en aplicación del\n"
                    + "Reglamento de Admisión Docente&nbsp; de&nbsp; la Universidad Técnica de Oruro,\n"
                    + "aprobado&nbsp; mediante resolución del\n"
                    + "Honorable Consejo Universitario Nº 50/98, el <span style=\"background-position: initial initial; background-repeat: initial initial;\">Dr. David Bustos Cisneros</span>,&nbsp; hasta la fecha venía desempeñándose como\n"
                    + "Docente Contratado en la asignatura: <span style=\"background-position: initial initial; background-repeat: initial initial;\">PSC 334 SALUD MENTAL I</span>, del Plan de Estudios vigente en la\n"
                    + "carrera de <span style=\"background-position: initial initial; background-repeat: initial initial;\">Enfermería</span>\n"
                    + "dependiente de la Facultad de <span style=\"background-position: initial initial; background-repeat: initial initial;\">Ciencias de la Salud</span>.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, la Facultad <span style=\"background-position: initial initial; background-repeat: initial initial;\">de Ciencias de la Salud</span>,\n"
                    + "en estricta aplicación del Reglamento de Evaluación Docente de la Universidad\n"
                    + "Técnica de Oruro, ha procedido a la evaluación docente, correspondiente a la\n"
                    + "gestión académica 2011.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, de acuerdo a&nbsp; informe de la Dirección de Evaluación y\n"
                    + "Acreditación, el <span style=\"background-position: initial initial; background-repeat: initial initial;\">Dr. David\n"
                    + "Bustos Cisneros</span>, ha obtenido una nota de evaluación docente de <span style=\"background-position: initial initial; background-repeat: initial initial;\">54.50</span> puntos en la\n"
                    + "asignatura: <span style=\"background-position: initial initial; background-repeat: initial initial;\">PSC 334 SALUD\n"
                    + "MENTAL I</span>.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><b><span style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Por\n"
                    + "tanto SE RESUELVE:<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><b><span style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">PRIMERO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Reconocer a favor del <b><span style=\"background-position: initial initial; background-repeat: initial initial;\">Dr. DAVID BUSTOS CISNEROS</span>, la categoría de Docente Titular, en\n"
                    + "la asignatura: <span style=\"background-position: initial initial; background-repeat: initial initial;\">PSC 334\n"
                    + "SALUD MENTAL I</span></b> del Plan de Estudios vigente en la carrera de <span style=\"background-position: initial initial; background-repeat: initial initial;\">Enfermería</span> de la Facultad\n"
                    + "<span style=\"background-position: initial initial; background-repeat: initial initial;\">de Ciencias de la Salud.</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">SEGUNDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Encomendar el cumplimiento de la presente determinación al\n"
                    + "Vicerrectorado, Dirección de Evaluación y Acreditación, Dirección Académica,\n"
                    + "Dirección Administrativa y Financiera, Recursos Humanos, División de Planillas\n"
                    + "y Decanato correspondiente.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Hágase conocer, cúmplase y archívese.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">Ing. RUBÉN\n"
                    + "MEDINACELI ORTÍZ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dr.&nbsp; RAÚL ARÁOZ VELASCO<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<b><span lang=\"ES-TRAD\" style=\"font-size: 11pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                    + "Rector de <st1:personname productid=\"la Universidad\" w:st=\"on\">la\n"
                    + " Universidad</st1:personname>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Secretario\n"
                    + "General de <st1:personname productid=\"la Universidad\" w:st=\"on\">la Universidad</st1:personname></span></b>";
        } else if (t.compareTo("INGRESO") == 0) {
            m = "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">RESOLUCION\n"
                    + "INGRESO</span></u></b><b><u><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></u></b></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align: center;\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">RESOLUCIÓN &nbsp;RECTORAL Nº. <span style=\"background-position: initial initial; background-repeat: initial initial;\">227/2012</span><o:p></o:p></span></u></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align: center;\"><span style=\"background-color: rgb(255, 255, 255);\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">A, 26 de abril de 2012</span><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">CONSIDERANDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, el Reglamento de Evaluación Docente&nbsp; expresa: “<i>Art.\n"
                    + "21 EVALUACIÓN DOCENTES&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                    + "CONTRATADOS,&nbsp; Si el resultado de\n"
                    + "la evaluación al cabo del año del contrato fuera favorable, el Docente\n"
                    + "Contratado pasará a la categoría de Docente”. <o:p></o:p></i></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Art. 20. PUNTOS DE CATEGORÍA PARA EL ESCALAFÓN. La suma del puntaje obtenido\n"
                    + "en cada aspecto,&nbsp; constituye la NOTA DE\n"
                    + "EVALUACIÓN y el puntaje de categoría a acumularse en el Escalafón docente, en\n"
                    + "adecuación al Reglamento General de la Docencia, se determinará en base a la\n"
                    + "siguiente tabla:<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align: center;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">NOTA DE EVALUACIÓN PUNTOS DE CATEGORÍA<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"margin-left: 106.35pt; text-indent: 35.45pt;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">		</span>0&nbsp; -&nbsp; 50&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>0<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"margin-left: 106.35pt; text-indent: 35.45pt;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\"><span class=\"Apple-tab-span\" style=\"white-space:pre\">		</span>51 -&nbsp;&nbsp; 70&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>30<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">			</span>71\n"
                    + "-&nbsp;&nbsp; 90 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>40&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span></p><p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">\n"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class=\"Apple-tab-span\" style=\"white-space:pre\">			</span>91\n"
                    + "- 100 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span>50<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, la carrera de <span style=\"background-position: initial initial; background-repeat: initial initial;\">Mecánica</span>\n"
                    + "dependiente de la Facultad <span style=\"background-position: initial initial; background-repeat: initial initial;\">Nacional\n"
                    + "de Ingeniería</span>, en estricta aplicación del Reglamento de Evaluación\n"
                    + "docente de la Universidad Técnica de Oruro, ha procedido a la evaluación\n"
                    + "docente correspondiente a la gestión académica <span style=\"background-position: initial initial; background-repeat: initial initial;\">2011</span>.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, en atención a los resultados de evaluación docente de la referida\n"
                    + "gestión y los respectivos informes, mediante Resolución Rectoral <span style=\"background-position: initial initial; background-repeat: initial initial;\">Nº&nbsp; 226/2012</span> de fecha <span style=\"background-position: initial initial; background-repeat: initial initial;\">26 de abril de 2012</span>, se\n"
                    + "ha reconocido a favor del <span style=\"background-position: initial initial; background-repeat: initial initial;\">Ing.\n"
                    + "Miguel Alejandro Ruiz Orellana</span>, la condición de Docente Titular en la\n"
                    + "asignatura <span style=\"background-position: initial initial; background-repeat: initial initial;\">LAB - 1140&nbsp; LABORATORIO DE SIMULACIÓN</span> del Plan de\n"
                    + "Estudios vigente en dicha unidad académica.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, en su calidad de Docente Titular en la asignatura <span style=\"background-position: initial initial; background-repeat: initial initial;\">LAB – 1140 LABORATORIO DE\n"
                    + "SIMULACIÓN</span> del Plan de Estudios de la carrera de <span style=\"background-position: initial initial; background-repeat: initial initial;\">Mecánica</span> dependiente de la Facultad <span style=\"background-position: initial initial; background-repeat: initial initial;\">Nacional de Ingeniería</span>,\n"
                    + "el <span style=\"background-position: initial initial; background-repeat: initial initial;\">Ing. Miguel\n"
                    + "Alejandro&nbsp; Ruiz Orellana</span>&nbsp; debe ingresar al Escalafón Docente de la\n"
                    + "Universidad Técnica de Oruro.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Por tanto SE RESUELVE:<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">PRIMERO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Asignar los siguientes puntajes al docente universitario<b> <span style=\"background-position: initial initial; background-repeat: initial initial;\">MIGUEL ALEJANDRO</span> <o:p></o:p></b></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span style=\"background-color: rgb(255, 255, 255);\"><b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\">Puntaje de Evaluación&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">100</span></span><span style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;\"><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span style=\"background-color: rgb(255, 255, 255);\"><b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\">Puntaje de Categoría&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">50</span><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Puntaje\n"
                    + "acumulado al <span style=\"background-position: initial initial; background-repeat: initial initial;\">2011</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">50</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Categoría\n"
                    + "en el Escalafón Docente&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">A</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">SEGUNDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Encomendar el cumplimiento de la presente determinación al\n"
                    + "Vicerrectorado, Dirección de Evaluación y Acreditación, Dirección Académica,\n"
                    + "Dirección Administrativa y Financiera, Recursos Humanos, División de Planillas\n"
                    + "y Decanato correspondiente.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Hágase conocer, cúmplase y archívese.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">Ing. RUBÉN\n"
                    + "MEDINACELI ORTÍZ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dr.&nbsp; RAÚL ARÁOZ VELASCO<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Rector de <st1:personname productid=\"la Universidad\" w:st=\"on\">la Universidad</st1:personname>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Secretario\n"
                    + "General de <st1:personname productid=\"la Universidad\" w:st=\"on\">la Universidad</st1:personname></span></b><b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-bidi-font-family:Arial;mso-ansi-language:ES-TRAD\"><o:p></o:p></span></b></p>";
        } else if (t.compareTo("GESTION") == 0) {
            m = "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">RESOLUCION\n"
                    + "GESTION</span></u></b><b><u><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></u></b></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">RESOLUCIÓN&nbsp;\n"
                    + "RECTORAL Nº<span style=\"background-position: initial initial; background-repeat: initial initial;\">.&nbsp; 581/2012</span> <o:p></o:p></span></u></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">A, 28 de noviembre de 2012</span><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">CONSIDERANDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que,\n"
                    + "según el informe elaborado por la Comisión Evaluadora de <span style=\"background-position: initial initial; background-repeat: initial initial;\">Contaduría Pública</span> dependiente\n"
                    + "de la Facultad <span style=\"background-position: initial initial; background-repeat: initial initial;\">de Ciencias\n"
                    + "Económicas, Financieras y Administrativas</span> y &nbsp;en aplicación del Reglamento de Evaluación\n"
                    + "Docente de <st1:personname productid=\"la Universidad T?cnica\" w:st=\"on\"><st1:personname productid=\"la Universidad\" w:st=\"on\">la Universidad</st1:personname> Técnica</st1:personname>\n"
                    + "de Oruro, ha procedido a la evaluación correspondiente a la gestión académica <span style=\"background-position: initial initial; background-repeat: initial initial;\">2009</span>.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que,\n"
                    + "la Comisión de Evaluación Docente estaba conformada por los docentes\n"
                    + "universitarios <span style=\"background-position: initial initial; background-repeat: initial initial;\">Oscar\n"
                    + "Panozo Torrico, Walker Paiva Quinteros, Félix Ampuero Escobar y Benigno\n"
                    + "Caballero Claure.</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, de acuerdo al Acta de Evaluación presentado por <st1:personname productid=\"la Comisi?n\" w:st=\"on\">la Comisión</st1:personname> de Evaluación,\n"
                    + "el docente <span style=\"background-position: initial initial; background-repeat: initial initial;\">Niver Montes\n"
                    + "Camacho</span> ha obtenido las ponderaciones siguientes:<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<div align=\"center\">\n"
                    + "\n"
                    + "<table class=\"MsoNormalTable\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;border:none;mso-border-alt:solid windowtext .5pt;\n"
                    + " mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt;mso-border-insideh:\n"
                    + " .5pt solid windowtext;mso-border-insidev:.5pt solid windowtext\">\n"
                    + " <tbody><tr style=\"mso-yfti-irow:0;mso-yfti-firstrow:yes;height:12.45pt\">\n"
                    + "  <td valign=\"top\" style=\"border:solid windowtext 1.0pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-size: 8pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">PONDERACIÓN<o:p></o:p></span></b></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border:solid windowtext 1.0pt;border-left:none;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-size: 8pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">A 10440 4D2<o:p></o:p></span></b></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border:solid windowtext 1.0pt;border-left:none;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-size: 8pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">A 10440 4V1<o:p></o:p></span></b></p>\n"
                    + "  </td>\n"
                    + " </tr>\n"
                    + " <tr style=\"mso-yfti-irow:1;height:12.45pt\">\n"
                    + "  <td valign=\"top\" style=\"border:solid windowtext 1.0pt;border-top:none;\n"
                    + "  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Docencia<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">58<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">58<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + " </tr>\n"
                    + " <tr style=\"mso-yfti-irow:2;height:12.45pt\">\n"
                    + "  <td valign=\"top\" style=\"border:solid windowtext 1.0pt;border-top:none;\n"
                    + "  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Investigación y/o extensión<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">6<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">6<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + " </tr>\n"
                    + " <tr style=\"mso-yfti-irow:3;height:12.45pt\">\n"
                    + "  <td valign=\"top\" style=\"border:solid windowtext 1.0pt;border-top:none;\n"
                    + "  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Prod. Científica e intelectual<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">30<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">30<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + " </tr>\n"
                    + " <tr style=\"mso-yfti-irow:4;height:12.45pt\">\n"
                    + "  <td valign=\"top\" style=\"border:solid windowtext 1.0pt;border-top:none;\n"
                    + "  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Superación y actualización Doc.<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">0<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">0<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + " </tr>\n"
                    + " <tr style=\"mso-yfti-irow:5;height:12.45pt\">\n"
                    + "  <td valign=\"top\" style=\"border:solid windowtext 1.0pt;border-top:none;\n"
                    + "  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Participación vida universitaria<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">1<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.45pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">1<o:p></o:p></span></p>\n"
                    + "  </td>\n"
                    + " </tr>\n"
                    + " <tr style=\"mso-yfti-irow:6;mso-yfti-lastrow:yes;height:12.0pt\">\n"
                    + "  <td valign=\"top\" style=\"border:solid windowtext 1.0pt;border-top:none;\n"
                    + "  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.0pt\">\n"
                    + "  <p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Nota de Evaluación (Máx.100)<o:p></o:p></span></b></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.0pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">95<o:p></o:p></span></b></p>\n"
                    + "  </td>\n"
                    + "  <td valign=\"top\" style=\"border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;\n"
                    + "  border-right:solid windowtext 1.0pt;mso-border-top-alt:solid windowtext .5pt;\n"
                    + "  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;\n"
                    + "  padding:0cm 5.4pt 0cm 5.4pt;height:12.0pt\">\n"
                    + "  <p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">95<o:p></o:p></span></b></p>\n"
                    + "  </td>\n"
                    + " </tr>\n"
                    + "</tbody></table>\n"
                    + "\n"
                    + "</div>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-size: 10pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, sumados los resultados parciales, se establece que el citado&nbsp; profesional alcanza una nota de <span style=\"background-position: initial initial; background-repeat: initial initial;\">95</span> puntos. <o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, según informe elaborado por la Dirección de Evaluación y\n"
                    + "Acreditación y en cumplimiento al Escalafón Docente.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">SE RESUELVE:<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">PRIMERO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Asignar los siguientes puntajes al docente universitario<b> <span style=\"background-position: initial initial; background-repeat: initial initial;\">NIVER MONTES CAMACHO</span><o:p></o:p></b></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span style=\"background-color: rgb(255, 255, 255);\"><b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\">Puntaje de Evaluación&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"background-position: initial initial; background-repeat: initial initial;\">95</span></span><span style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;\"><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span style=\"background-color: rgb(255, 255, 255);\"><b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\">Puntaje de Categoría&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"background-position: initial initial; background-repeat: initial initial;\">50</span><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Puntaje\n"
                    + "acumulado al <span style=\"background-position: initial initial; background-repeat: initial initial;\">2009</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"background-position: initial initial; background-repeat: initial initial;\">770</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Categoría\n"
                    + "en el Escalafón Docente&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style=\"background-position: initial initial; background-repeat: initial initial;\">C</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">SEGUNDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Encomendar el cumplimiento de la presente determinación al\n"
                    + "Vicerrectorado, Dirección de Evaluación y Acreditación, Dirección Académica, Dirección\n"
                    + "Administrativa y Financiera, Recursos Humanos, División de Planillas y Decanato\n"
                    + "correspondiente.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-size: 8pt; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Hágase conocer, cúmplase y archívese.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"margin-right: 2.55pt; text-align: justify;\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">Ing. RUBÉN MEDINACELI ORTÍZ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dr.&nbsp; RAÚL ARÁOZ VELASCO<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align: justify;\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp; Rector de la Universidad&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                    + "Secretario General de la Universidad</span></b><b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\"><o:p></o:p></span></b></p>";
        } else if (t.compareTo("EMERITO") == 0) {
            m = "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">RESOLUCION EMERITO</span></u></b><b><u><span lang=\"ES-TRAD\" style=\"font-family:\n"
                    + "&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:ES-TRAD\"><o:p></o:p></span></u></b></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">RESOLUCIÓN &nbsp;RECTORAL <span style=\"background-position: initial initial; background-repeat: initial initial;\">Nº. 036/2012</span><o:p></o:p></span></u></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">A, 14 de febrero de 2012</span><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">CONSIDERANDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que,\n"
                    + "el 3 de julio de 1984, el VI Congreso Nacional de Universidades, reunido en la\n"
                    + "ciudad de Tarija, aprobó el <b>Reglamento\n"
                    + "General de <st1:personname productid=\"la Docencia\" w:st=\"on\">la Docencia</st1:personname>,</b>\n"
                    + "cuyos artículos 30, 31, 32 y 33, se refieren al ESCALAFÓN DOCENTE.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que,\n"
                    + "el Honorable Consejo Universitario, mediante Resolución Nº.&nbsp; 59/93 de fecha 27 de agosto de <st1:metricconverter productid=\"1993, ha\" w:st=\"on\">1993, ha</st1:metricconverter> aprobado el\n"
                    + "Reglamento de Escalafón Docente de <st1:personname productid=\"la Universidad T?cnica\" w:st=\"on\"><st1:personname productid=\"la Universidad\" w:st=\"on\">la Universidad</st1:personname> Técnica</st1:personname>\n"
                    + "de Oruro.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que,\n"
                    + "el Art. 14 del Reglamento de Escalafón Docente de <st1:personname productid=\"la Universidad Técnica\" w:st=\"on\">la Universidad Técnica</st1:personname>\n"
                    + "de Oruro señala: “<i>El docente que acumule\n"
                    + "800 puntos, será declarado Docente Emérito mediante resolución rectoral y podrá\n"
                    + "continuar en la docencia hasta su jubilación o retiro voluntario”.<o:p></o:p></i></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><i><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></i></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que,\n"
                    + "de acuerdo a evaluación docente en la carrera <span style=\"background-position: initial initial; background-repeat: initial initial;\">de Administración de Empresas</span> dependiente de la Facultad\n"
                    + "<span style=\"background-position: initial initial; background-repeat: initial initial;\">de Ciencias Económicas,\n"
                    + "Financieras y Administrativas</span> durante la gestión <span style=\"background-position: initial initial; background-repeat: initial initial;\">2009</span>,&nbsp;\n"
                    + "la Dirección de Evaluación y Acreditación verifica que, al docente\n"
                    + "universitario <span style=\"background-position: initial initial; background-repeat: initial initial;\">René Toco\n"
                    + "Choque</span>&nbsp; se le asigna <span style=\"background-position: initial initial; background-repeat: initial initial;\">820</span> puntos en el\n"
                    + "Escalafón Docente.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Por tanto SE RESUELVE:<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">ARTÍCULO ÚNICO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 162.0pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Otorgar al distinguido docente universitario <b><span style=\"background-position: initial initial; background-repeat: initial initial;\">RENÉ TOCO CHOQUE</span></b>,&nbsp; la distinción de <b>DOCENTE EMÉRITO </b>de <st1:personname productid=\"la Universidad Técnica\" w:st=\"on\">la Universidad Técnica</st1:personname>\n"
                    + "de Oruro, en mérito a normas universitarias actualmente vigentes.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Hágase conocer, cúmplase y archívese.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">Ing. RUBÉN MEDINACELI ORTÍZ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                    + "Dr.&nbsp; RAÚL ARÁOZ VELASCO<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Rector de <st1:personname productid=\"la Universidad                                             Secretario\" w:st=\"on\">la Universidad&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Secretario</st1:personname>\n"
                    + "General de <st1:personname productid=\"la Universidad\" w:st=\"on\">la Universidad</st1:personname></span></b><b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\"><o:p></o:p></span></b></p>";
        } else if (t.compareTo("FEDUTO") == 0) {
            m = "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">RESOLUCION\n"
                    + "FEDUTO</span></u></b><b><u><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></u></b></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">RESOLUCIÓN &nbsp;RECTORAL <span style=\"background-position: initial initial; background-repeat: initial initial;\">Nº. 061/2013</span><o:p></o:p></span></u></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">A, 4 de febrero de 2013</span><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">CONSIDERANDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que,\n"
                    + "la Facultad Nacional de Ingeniería en aplicación del Reglamento de Evaluación\n"
                    + "Docente de <st1:personname productid=\"la Universidad T?cnica\" w:st=\"on\"><st1:personname productid=\"la Universidad\" w:st=\"on\">la Universidad</st1:personname> Técnica</st1:personname>\n"
                    + "de Oruro, ha procedido a la evaluación correspondiente a la gestión académica <span style=\"background-position: initial initial; background-repeat: initial initial;\">2011</span>.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-size: 8pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, de &nbsp;acuerdo a&nbsp; antecedentes se&nbsp; establece&nbsp;\n"
                    + "que el docente&nbsp; universitario <span style=\"background-position: initial initial; background-repeat: initial initial;\">Willie Richard Córdova Eguivar</span>\n"
                    + "en la gestión <span style=\"background-position: initial initial; background-repeat: initial initial;\">2011</span>\n"
                    + "se ha desempeñado como Presidente de la Federación de Docentes de la\n"
                    + "Universidad Técnica de Oruro.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-size: 8pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que\n"
                    + "el Art. 28, FEDUTO en su Capítulo III, CASOS ESPECIALES del Reglamento de\n"
                    + "Evaluación Docente de la Universidad Técnica de Oruro señala: ”<i>El Secretario Ejecutivo y Secretario General\n"
                    + "de la Federación&nbsp; de Docentes contarán\n"
                    + "con las siguientes opciones:<o:p></o:p></i></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt 36pt; text-align: justify; text-indent: -18pt;\"><!--[if !supportLists]--><span style=\"background-color: rgb(255, 255, 255);\"><i><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-fareast-font-family:&quot;Arial Narrow&quot;;mso-bidi-font-family:&quot;Arial Narrow&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">a)<span style=\"font-style: normal; font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></i><!--[endif]--><i><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">Repetir el puntaje obtenido en la última evaluación.<o:p></o:p></span></i></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt 36pt; text-align: justify; text-indent: -18pt;\"><!--[if !supportLists]--><span style=\"background-color: rgb(255, 255, 255);\"><i><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-fareast-font-family:&quot;Arial Narrow&quot;;mso-bidi-font-family:&quot;Arial Narrow&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">b)<span style=\"font-style: normal; font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></i><!--[endif]--><i><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">Ser sometidos a evaluación en su respectiva carrera.<o:p></o:p></span></i></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><i><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></i></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, el citado profesional ha optado por acogerse al inciso <span style=\"background-position: initial initial; background-repeat: initial initial;\">a)</span> del Artículo 26 Opciones.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><i><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></i></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Por tanto se RESUELVE:<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 162.0pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">PRIMERO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 162.0pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;Asignar los siguientes puntajes\n"
                    + "de evaluación al docente universitario <b><span style=\"background-position: initial initial; background-repeat: initial initial;\">WILLIE RICHARD CÓRDOVA</span> <span style=\"background-position: initial initial; background-repeat: initial initial;\">EGUIVAR</span></b><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 162.0pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Puntaje\n"
                    + "de Categoría&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">			</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">30</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 162.0pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Puntaje\n"
                    + "acumulado al <span style=\"background-position: initial initial; background-repeat: initial initial;\">2011</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">230</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 162.0pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Categoría\n"
                    + "en el Escalafón Docente&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                    + "<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">B</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 162.0pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 162.0pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">SEGUNDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Encomendar el cumplimiento de la presente determinación al\n"
                    + "Vicerrectorado, Dirección de Evaluación y Acreditación, Dirección Académica,\n"
                    + "Dirección Administrativa y Financiera, Recursos Humanos, División de Planillas\n"
                    + "y Decanato correspondiente.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Hágase conocer, cúmplase y archívese.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">Ing. RUBÉN\n"
                    + "MEDINACELI ORTÍZ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dr.&nbsp; RAÚL ARÁOZ VELASCO<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<b><span lang=\"ES-TRAD\" style=\"font-size: 11pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                    + "Rector de <st1:personname productid=\"la Universidad\" w:st=\"on\">la\n"
                    + " Universidad</st1:personname>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Secretario\n"
                    + "General de <st1:personname productid=\"la Universidad\" w:st=\"on\">la Universidad</st1:personname></span></b>";
        } else if (t.compareTo("AUTORIDAD") == 0) {
            m = "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><b><u><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">RESOLUCION\n"
                    + "AUTORIDADES</span></u></b><b><u><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></u></b></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center\"><span style=\"background-color: rgb(255, 255, 255);\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial;\">A, 28 de noviembre de 2012</span><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\"><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">CONSIDERANDO&nbsp; <o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que,\n"
                    + "la Facultad <span style=\"background-position: initial initial; background-repeat: initial initial;\">de Ciencias\n"
                    + "Económicas, Financieras y Administrativas</span> en aplicación del Reglamento\n"
                    + "de Evaluación Docente de <st1:personname productid=\"la Universidad T?cnica\" w:st=\"on\"><st1:personname productid=\"la Universidad\" w:st=\"on\">la Universidad</st1:personname>\n"
                    + " Técnica</st1:personname> de Oruro, ha procedido a la evaluación\n"
                    + "correspondiente a la gestión académica <span style=\"background-position: initial initial; background-repeat: initial initial;\">2009.</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, de &nbsp;acuerdo a&nbsp; antecedentes se&nbsp; establece&nbsp;\n"
                    + "que el docente&nbsp; universitario <span style=\"background-position: initial initial; background-repeat: initial initial;\">Miguel Herrera Leniz</span> se\n"
                    + "ha desempeñado como Director <span style=\"background-position: initial initial; background-repeat: initial initial;\">del\n"
                    + "Departamento de Ciencias Básicas</span>.&nbsp;\n"
                    + "<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, de conformidad al Capítulo II, Artículo 26 OPCIONES señala “<i>Las autoridades universitarias, con objeto\n"
                    + "de mejorar su puntaje, contarán con las siguientes opciones:<o:p></o:p></i></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><i><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></i></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt 36pt; text-indent: -18pt;\"><!--[if !supportLists]--><span style=\"background-color: rgb(255, 255, 255);\"><i><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-fareast-font-family:&quot;Arial Narrow&quot;;mso-bidi-font-family:&quot;Arial Narrow&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">a)<span style=\"font-style: normal; font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></i><!--[endif]--><i><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">Ser sometidos a evaluación, en su respectiva\n"
                    + "carrera.<o:p></o:p></span></i></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt 36pt; text-indent: -18pt;\"><!--[if !supportLists]--><span style=\"background-color: rgb(255, 255, 255);\"><i><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-fareast-font-family:&quot;Arial Narrow&quot;;mso-bidi-font-family:&quot;Arial Narrow&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">b)<span style=\"font-style: normal; font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></i><!--[endif]--><i><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">Repetir el puntaje obtenido en la última evaluación.<o:p></o:p></span></i></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><i><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></i></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Que, el citado profesional ha optado por acogerse al inciso <span style=\"background-position: initial initial; background-repeat: initial initial;\">b)</span> del Artículo 26 Opciones.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Por tanto, SE RESUELVE:<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">PRIMERO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Asignar los siguientes puntajes al docente universitario <b><span style=\"background-position: initial initial; background-repeat: initial initial;\">MIGUEL HERRERA LENIZ</span> <o:p></o:p></b></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span style=\"background-color: rgb(255, 255, 255);\"><b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;\n"
                    + "mso-ansi-language:ES-TRAD\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></b><span lang=\"ES-TRAD\" style=\"font-family:&quot;Arial Narrow&quot;,&quot;sans-serif&quot;;mso-ansi-language:\n"
                    + "ES-TRAD\">Puntaje última evaluación&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">30</span><o:p></o:p></span></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Puntaje\n"
                    + "acumulado al <span style=\"background-position: initial initial; background-repeat: initial initial;\">2009</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">740</span><o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Categoría\n"
                    + "en el Escalafón Docente&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-tab-span\" style=\"white-space:pre\">	</span><span style=\"background-position: initial initial; background-repeat: initial initial;\">C</span>&nbsp;&nbsp;&nbsp; <o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><b><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">SEGUNDO<o:p></o:p></span></b></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"text-align:justify;tab-stops:35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Encomendar el cumplimiento de la presente determinación al\n"
                    + "Vicerrectorado, Dirección de Evaluación y Acreditación, Dirección Académica,\n"
                    + "Dirección Administrativa y Financiera, Recursos Humanos, División de Planillas\n"
                    + "y Decanato correspondiente.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">Hágase conocer, cúmplase y archívese.<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-color: rgb(255, 255, 255);\">&nbsp;</span></p>\n"
                    + "\n"
                    + "<p class=\"MsoNormal\" style=\"margin-right:2.55pt;text-align:justify;tab-stops:\n"
                    + "35.4pt 70.8pt 106.2pt 141.6pt 177.0pt 212.4pt 355.25pt\"><span lang=\"ES-TRAD\" style=\"font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">Ing. RUBÉN MEDINACELI ORTÍZ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dr.&nbsp; RAÚL ARÁOZ VELASCO<o:p></o:p></span></p>\n"
                    + "\n"
                    + "<b><span lang=\"ES-TRAD\" style=\"font-size: 11pt; line-height: 115%; font-family: 'Arial Narrow', sans-serif; background-position: initial initial; background-repeat: initial initial; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Rector de la Universidad&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
                    + "Secretario General de la Universidad</span></b>";
        }
        r="";
        for( int i=0;i<m.length();i++ ){
            if( m.charAt(i)=='\'' ){
                r=r+"'";
            }
            r=r+m.charAt(i);
        }
        return r;
    }

    @Override
    public List<DocenteEscalafonBean> obtenerDocenteEscalafons(CarreraBean object) {
        Carrera cb = new Carrera();
        cb.setIdCarrera(object.getIdCarrera());
        List<DocenteEscalafonBean> docenteEscalafonBeans = new ArrayList<DocenteEscalafonBean>();
        for (DocenteEscalafon obj : getImplementDocenteEscalafonDAO().obtenerDocenteEscalafons(cb)) {
            DocenteEscalafonBean docenteEscalafonBean = new DocenteEscalafonBean();
            docenteEscalafonBean.setCi(obj.getCi());

            DocenteBean docenteBean = new DocenteBean();
            docenteBean.setCi(obj.getCi());
            docenteBean.setGradoAcademico("grado academico");
            docenteBean.setGradoAcademicoAbreviatura("grado academico abreviado");
            docenteBean.setEstado("estado");

            Docente docente = new Docente();
            docente.setCi(obj.getCi());
            Persona p = new Persona();
            p.setCi(obj.getCi());
            docente.setPersona(p);
            docente = implementDocenteEscalafonDAO.obtenerDocenteByCi(docente);
            if (docente != null) {
                docenteBean.setGradoAcademico(docente.getGradoAcademico());
                docenteBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());
                docenteBean.setEstado(docente.getEstado());

                Persona persona = new Persona();
                persona.setCi(obj.getCi());
                persona = getImplementDocenteEscalafonDAO().obtenerPersonaByCi(persona);

                if (persona != null) {
                    PersonaBean personaBean = new PersonaBean();
                    personaBean.setNombre(persona.getNombre());
                    personaBean.setApp(persona.getApp());
                    personaBean.setApm(persona.getApm());
                    personaBean.setCuenta(persona.getCuenta());
                    personaBean.setContrasenia(persona.getContrasenia());

                    docenteBean.setNombre(persona.getNombre());//--
                    docenteBean.setApp(persona.getApp());//--
                    docenteBean.setApm(persona.getApm());//--
                    docenteBean.setNombreCompleto(persona.getNombre() + " " + persona.getApp() + " " + persona.getApm());//--
                    docenteBean.setCuenta(persona.getCuenta());//--
                    docenteBean.setContrasenia(persona.getContrasenia());//--

                    docenteBean.setPersonaBean(personaBean);
                }
            }

            docenteEscalafonBean.setDocenteBean(docenteBean);

            CarreraBean carreraBean = new CarreraBean();
            carreraBean.setIdCarrera(obj.getCarrera().getIdCarrera());
            carreraBean.setIdFacultad(0);
            carreraBean.setSiglaCarrera("sigla carrera");
            carreraBean.setNombre("nombre carrera");
            carreraBean.setEstado("estado");

            Carrera c = new Carrera();
            c.setIdCarrera(obj.getCarrera().getIdCarrera());
            c = implementDocenteEscalafonDAO.obtenerCarreraByIdCarrera(c);

            if (c != null) {
                carreraBean.setIdFacultad(c.getFacultad().getIdFacultad());
                carreraBean.setSiglaCarrera(c.getSiglaCarrera());
                carreraBean.setNombre(c.getNombre());
                carreraBean.setEstado(c.getEstado());
            }

            docenteEscalafonBean.setCarreraBean(carreraBean);
            docenteEscalafonBean.setFechaNacimiento(obj.getFechaNacimiento());
            docenteEscalafonBean.setCorreoElectronico(obj.getCorreoElectronico());
            docenteEscalafonBean.setTipo(obj.getTipo());

            docenteEscalafonBeans.add(docenteEscalafonBean);
        }
        return docenteEscalafonBeans;
    }

    @Override
    public DocenteBean obtenerDocenteByCi(DocenteBean obj) {
        DocenteBean docenteBean = new DocenteBean();
        docenteBean.setCi(obj.getCi());
        docenteBean.setGradoAcademico("grado academico");
        docenteBean.setGradoAcademicoAbreviatura("grado academico (Abr)");
        docenteBean.setEstado("estado");

        docenteBean.setNombre("nombre");
        docenteBean.setApp("apellido paterno");
        docenteBean.setApm("apellido materno");
        docenteBean.setCuenta("cuenta");
        docenteBean.setContrasenia("password");
        docenteBean.setNombreCompleto("nombre completo");

        Docente docente = new Docente();
        Persona persona1 = new Persona();
        persona1.setCi(obj.getCi());
        docente.setCi(obj.getCi());
        docente.setPersona(persona1);
        docente = implementDocenteEscalafonDAO.obtenerDocenteByCi(docente);

        if (docente != null) {
            docenteBean.setGradoAcademico(docente.getGradoAcademico());
            docenteBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());
            docenteBean.setEstado(docente.getEstado());

            Persona persona = new Persona();
            persona.setCi(obj.getCi());
            persona = implementDocenteEscalafonDAO.obtenerPersonaByCi(persona);
            if (persona != null) {
                docenteBean.setNombre(persona.getNombre());
                docenteBean.setApp(persona.getApp());
                docenteBean.setApm(persona.getApm());
                docenteBean.setCuenta(persona.getCuenta());
                docenteBean.setContrasenia(persona.getContrasenia());
                docenteBean.setNombreCompleto(persona.getNombre() + " " + persona.getApp() + " " + persona.getApm());
            }
        }
        return docenteBean;
    }

    @Override
    public Long modificarDocente(DocenteBean obj) {
        Long retorno = new Long(0);
        Docente docente = new Docente();
        docente.setCi(obj.getCi());

        Persona persona = new Persona();
        persona.setCi(obj.getCi());

        docente.setPersona(persona);
        docente.setGradoAcademico(obj.getGradoAcademico());
        docente.setGradoAcademicoAbreviatura(obj.getGradoAcademicoAbreviatura());
        docente.setEstado(obj.getEstado());

        retorno = implementDocenteEscalafonDAO.modificarDocente(docente);
        return retorno;
    }

    @Override
    public Long crearDocente(DocenteBean obj) {
        Long retorno = new Long(0);
        Long respuesta = new Long(0);

        Persona persona = new Persona();
        persona.setCi(obj.getCi());
        persona.setNombre(obj.getNombre());
        persona.setApp(obj.getApp());
        persona.setApm(obj.getApm());
        persona.setCuenta(obj.getCuenta());
        persona.setContrasenia(obj.getContrasenia());
        persona.setEstado("ACTIVO");

        respuesta = implementDocenteEscalafonDAO.crearPersona(persona);

        if (respuesta.longValue() == -1) {
            retorno = new Long(-1);
        } else {
            Docente docente = new Docente();
            Persona persona1 = new Persona();
            persona1.setCi(obj.getCi());
            docente.setPersona(persona1);
            docente.setGradoAcademico(obj.getGradoAcademico());
            docente.setGradoAcademicoAbreviatura(obj.getGradoAcademicoAbreviatura());
            docente.setEstado("ACTIVO");

            retorno = implementDocenteEscalafonDAO.crearDocente(docente);
        }
        return retorno;
    }

    /**
     * @return the implementDocenteEscalafonDAO
     */
    public IDocenteEscalafonDAO getImplementDocenteEscalafonDAO() {
        return implementDocenteEscalafonDAO;
    }

    /**
     * @param implementDocenteEscalafonDAO the implementDocenteEscalafonDAO to
     * set
     */
    public void setImplementDocenteEscalafonDAO(IDocenteEscalafonDAO implementDocenteEscalafonDAO) {
        this.implementDocenteEscalafonDAO = implementDocenteEscalafonDAO;
    }

    @Override
    public Long modificarDocenteEscalafon(DocenteEscalafonBean obj) {
        Long respuesta = new Long(0);
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj.getCi());
        de.setFechaNacimiento(obj.getFechaNacimiento());
        de.setCorreoElectronico(obj.getCorreoElectronico());
        de.setTipo(obj.getTipo());
        Carrera c = new Carrera();
        c.setIdCarrera(obj.getCarreraBean().getIdCarrera());
        de.setCarrera(c);
        respuesta = implementDocenteEscalafonDAO.modificarDocenteEscalafon(de);
        return respuesta;
    }

    @Override
    public Long crearDocenteEscalafon(DocenteEscalafonBean obj) {
        Long respuesta = new Long(0);
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj.getCi());
        de.setFechaNacimiento(obj.getFechaNacimiento());
        de.setCorreoElectronico(obj.getCorreoElectronico());
        de.setTipo(obj.getTipo());
        Carrera c = new Carrera();
        c.setIdCarrera(obj.getIdCarrera());
        de.setCarrera(c);
        respuesta = implementDocenteEscalafonDAO.crearDocenteEscalafon(de);
        return respuesta;
    }

    @Override
    public List<RelacionDocenteMateriaBean> obtenerRelacionDocenteMateriaCiGestion(DocenteEscalafonBean obj, long gestion) {
        DocenteEscalafon docenteEscalafon = new DocenteEscalafon();
        docenteEscalafon.setCi(obj.getCi());

        List<RelacionDocenteMateriaBean> relacionDocenteMateriaBeans = new ArrayList<RelacionDocenteMateriaBean>();
        for (RelacionDocenteMateria object : implementDocenteEscalafonDAO.obtenerRelacionDocenteMateriaCiGestion(docenteEscalafon, gestion)) {
            RelacionDocenteMateriaBean relacionDocenteMateriaBean = new RelacionDocenteMateriaBean();
            relacionDocenteMateriaBean.setIdRelacionDocenteMateria(object.getIdRelacionDocenteMateria());
            relacionDocenteMateriaBean.setParalelo(object.getParalelo());
            relacionDocenteMateriaBean.setPeriodo(object.getPeriodo());
            relacionDocenteMateriaBean.setTipoPeriodo(object.getTipoPeriodo());
            relacionDocenteMateriaBean.setGestion(object.getGestion());
            relacionDocenteMateriaBean.setFechaInicio(object.getFechaInicio());
            relacionDocenteMateriaBean.setFechaFin(object.getFechaFin());
            relacionDocenteMateriaBean.setHoras(object.getHoras());
            relacionDocenteMateriaBean.setItem(object.getItem());
            relacionDocenteMateriaBean.setTipoMateria(object.getTipoMateria());
            relacionDocenteMateriaBean.setEstado(object.getEstado());

            //
            DocenteBean docenteBean = new DocenteBean();
            docenteBean.setCi(object.getDocente().getCi());
            docenteBean.setGradoAcademico("grado academico");
            docenteBean.setGradoAcademicoAbreviatura("grado academico (Abr)");
            docenteBean.setEstado("estado");

            docenteBean.setNombre("nombre");
            docenteBean.setApp("apellido paterno");
            docenteBean.setApm("apellido materno");
            docenteBean.setCuenta("cuenta");
            docenteBean.setContrasenia("password");
            docenteBean.setNombreCompleto("nombre completo");

            Docente docente = new Docente();
            Persona persona1 = new Persona();
            persona1.setCi(object.getDocente().getCi());
            docente.setCi(object.getDocente().getCi());
            docente.setPersona(persona1);
            docente = implementDocenteEscalafonDAO.obtenerDocenteByCi(docente);

            if (docente != null) {
                docenteBean.setGradoAcademico(docente.getGradoAcademico());
                docenteBean.setGradoAcademicoAbreviatura(docente.getGradoAcademicoAbreviatura());
                docenteBean.setEstado(docente.getEstado());

                Persona persona = new Persona();
                persona.setCi(object.getDocente().getCi());
                persona = implementDocenteEscalafonDAO.obtenerPersonaByCi(persona);
                if (persona != null) {
                    System.out.println("nooooo " + object.getDocente().getCi());
                    docenteBean.setNombre(persona.getNombre());
                    docenteBean.setApp(persona.getApp());
                    docenteBean.setApm(persona.getApm());
                    docenteBean.setCuenta(persona.getCuenta());
                    docenteBean.setContrasenia(persona.getContrasenia());
                    docenteBean.setNombreCompleto(persona.getNombre() + " " + persona.getApp() + " " + persona.getApm());
                }
            }
            //
            MateriaBean materiaBean = new MateriaBean();
            materiaBean.setEstado("estado");
            materiaBean.setSiglaMateria("sigla materia");
            materiaBean.setNombre("nombre");
            Materia materia = new Materia();
            materia.setIdMateria(object.getMateria().getIdMateria());
            materia = implementDocenteEscalafonDAO.obtenerMateriaByIdMateria(materia);
            if (materia != null) {
                materiaBean.setIdMateria(materia.getIdMateria());
                materiaBean.setSiglaMateria(materia.getSiglaMateria());
                materiaBean.setNombre(materia.getNombre());
                materiaBean.setEstado(materia.getEstado());
                materiaBean.setIdCarrera(materia.getCarrera().getIdCarrera());
            }
            //
            EvaluacionBean evaluacionBean = new EvaluacionBean();
            evaluacionBean.setIdEvaluacion(0);
            evaluacionBean.setIdRelacionDocenteMateria(object.getIdRelacionDocenteMateria());
            evaluacionBean.setNota(0.0);
            Evaluacion evaluacion = implementDocenteEscalafonDAO.obtenerEvaluacionByIdRelacionDocenteMateria(object);
            object.getIdRelacionDocenteMateria();
            if (evaluacion != null) {
                evaluacionBean.setIdEvaluacion(evaluacion.getIdEvaluacion());
                evaluacionBean.setIdRelacionDocenteMateria(evaluacion.getRelacionDocenteMateria().getIdRelacionDocenteMateria());
                evaluacionBean.setNota(evaluacion.getNota());
            }
            //

            relacionDocenteMateriaBean.setDocenteBean(docenteBean);
            relacionDocenteMateriaBean.setMateriaBean(materiaBean);
            relacionDocenteMateriaBean.setEvaluacionBean(evaluacionBean);

            relacionDocenteMateriaBeans.add(relacionDocenteMateriaBean);
        }
        return relacionDocenteMateriaBeans;
    }

    @Override
    public Long actualizarNotaEvaluacion(EvaluacionBean obj) {
        Long respuesta = new Long(0);
        Evaluacion e = new Evaluacion();
        e.setIdEvaluacion(obj.getIdEvaluacion());
        e.setNota(obj.getNota());
        respuesta = implementDocenteEscalafonDAO.actualizarNotaEvaluacion(e);
        return respuesta;
    }

    @Override
    public Long Actualizar_Cartilla_Ev_Docente(CartillaBean obj) {
        Long r = new Long(0);
        Long x = new Long(0);
        Cartilla c = new Cartilla();
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        c.setDocenteEscalafon(de);
        c.setGestion(obj.getGestion());
        r = implementDocenteEscalafonDAO.verificar_para_Docente(c);
        if (r.longValue() == 1) {
            r = implementDocenteEscalafonDAO.Actualizar_Cartilla_Ev_Docente(c);
            Resolucion rl = new Resolucion();
            Cartilla cc = new Cartilla();
            cc.setIdCartilla(r.longValue());
            rl.setCartilla(cc);
            rl.setContenido(modelo("GESTION"));
            x = implementDocenteEscalafonDAO.actualizar_contenido_Resolucion(rl);
        }
        return r;
    }

    @Override
    public Long Actualizar_Cartilla_Ev_Contratado(CartillaBean obj) {
        Long r = new Long(0);
        Long x = new Long(0);
        Cartilla c = new Cartilla();
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        c.setDocenteEscalafon(de);
        c.setGestion(obj.getGestion());
        r = implementDocenteEscalafonDAO.verificar_para_Contratado(c);
        if (r.longValue() == 1) {
            r = implementDocenteEscalafonDAO.Actualizar_Cartilla_Ev_Contratado(c);
            Resolucion rl = new Resolucion();
            Cartilla cc = new Cartilla();
            cc.setIdCartilla(r.longValue());
            rl.setCartilla(cc);
            rl.setContenido(modelo("CONTRATADO"));
            x = implementDocenteEscalafonDAO.actualizar_contenido_Resolucion(rl);
        }
        return r;
    }

    @Override
    public Long Actualizar_Cartilla_Ev_Ingreso(CartillaBean obj) {
        Long r = new Long(0);
        Long x = new Long(0);
        Cartilla c = new Cartilla();
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        c.setDocenteEscalafon(de);
        c.setGestion(obj.getGestion());
        r = implementDocenteEscalafonDAO.verificar_para_ingreso(c);
        if (r.longValue() == 1) {
            r = implementDocenteEscalafonDAO.Actualizar_Cartilla_Ev_Ingreso(c);
            Resolucion rl = new Resolucion();
            Cartilla cc = new Cartilla();
            cc.setIdCartilla(r.longValue());
            rl.setCartilla(cc);
            rl.setContenido(modelo("INGRESO"));
            x = implementDocenteEscalafonDAO.actualizar_contenido_Resolucion(rl);
        }
        return r;
    }

    @Override
    public Long Actualizar_Cartilla_Ev_Emerito(CartillaBean obj) {
        Long r = new Long(0);
        Long x = new Long(0);
        Cartilla c = new Cartilla();
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        c.setDocenteEscalafon(de);
        c.setGestion(obj.getGestion());
        r = implementDocenteEscalafonDAO.verificar_para_Docente(c);
        if (r.longValue() == 1) {
            r = implementDocenteEscalafonDAO.Actualizar_Cartilla_Ev_Emerito(c);
            Resolucion rl = new Resolucion();
            Cartilla cc = new Cartilla();
            cc.setIdCartilla(r.longValue());
            rl.setCartilla(cc);
            rl.setContenido(modelo("EMERITO"));
            x = implementDocenteEscalafonDAO.actualizar_contenido_Resolucion(rl);
        }
        return r;
    }

    @Override
    public Long Actualizar_Cartilla_Ev_Fed_Rep_Nota(CartillaBean obj) {
        Long r = new Long(0);
        Long x = new Long(0);
        Cartilla c = new Cartilla();
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        c.setDocenteEscalafon(de);
        c.setGestion(obj.getGestion());
        r = implementDocenteEscalafonDAO.verificar_para_Docente(c);
        if (r.longValue() == 1) {
            r = implementDocenteEscalafonDAO.Actualizar_Cartilla_Ev_Fed_Rep_Nota(c);
            Resolucion rl = new Resolucion();
            Cartilla cc = new Cartilla();
            cc.setIdCartilla(r.longValue());
            rl.setCartilla(cc);
            rl.setContenido(modelo("FEDUTO"));
            x = implementDocenteEscalafonDAO.actualizar_contenido_Resolucion(rl);
        }
        return r;
    }

    @Override
    public Long Actualizar_Cartilla_Ev_Aut_Rep_Nota(CartillaBean obj) {
        Long r = new Long(0);
        Long x = new Long(0);
        Cartilla c = new Cartilla();
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        c.setDocenteEscalafon(de);
        c.setGestion(obj.getGestion());
        r = implementDocenteEscalafonDAO.verificar_para_Docente(c);
        if (r.longValue() == 1) {
            r = implementDocenteEscalafonDAO.Actualizar_Cartilla_Ev_Aut_Rep_Nota(c);
            Resolucion rl = new Resolucion();
            Cartilla cc = new Cartilla();
            cc.setIdCartilla(r.longValue());
            rl.setCartilla(cc);
            rl.setContenido(modelo("AUTORIDAD"));
            x = implementDocenteEscalafonDAO.actualizar_contenido_Resolucion(rl);
        }
        return r;
    }

    @Override
    public Long Actualizar_Cartilla_Ev_Fed_Car_Nota(CartillaBean obj1, EvaluacionEscalafonBean obj2) {
        Long r = new Long(0);
        Long x = new Long(0);
        Cartilla c = new Cartilla();
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj1.getCiDocenteEscalafon());
        c.setDocenteEscalafon(de);
        c.setGestion(obj1.getGestion());

        EvaluacionEscalafon ee = new EvaluacionEscalafon();
        ee.setCargo(obj2.getCargo());
        ee.setLugar(obj2.getLugar());
        ee.setNota(obj2.getNota());

        r = implementDocenteEscalafonDAO.verificar_para_Docente(c);
        if (r.longValue() == 1) {
            r = implementDocenteEscalafonDAO.Actualizar_Cartilla_Ev_Fed_Car_Nota(c, ee);
            Resolucion rl = new Resolucion();
            Cartilla cc = new Cartilla();
            cc.setIdCartilla(r.longValue());
            rl.setCartilla(cc);
            rl.setContenido(modelo("FEDUTO"));
            x = implementDocenteEscalafonDAO.actualizar_contenido_Resolucion(rl);
        }
        return r;
    }

    @Override
    public Long Actualizar_Cartilla_Ev_Aut_Car_Nota(CartillaBean obj1, EvaluacionEscalafonBean obj2) {
        Long r = new Long(0);
        Long x = new Long(0);
        Cartilla c = new Cartilla();
        DocenteEscalafon de = new DocenteEscalafon();
        de.setCi(obj1.getCiDocenteEscalafon());
        c.setDocenteEscalafon(de);
        c.setGestion(obj1.getGestion());

        EvaluacionEscalafon ee = new EvaluacionEscalafon();
        ee.setCargo(obj2.getCargo());
        ee.setLugar(obj2.getLugar());
        ee.setNota(obj2.getNota());

        r = implementDocenteEscalafonDAO.verificar_para_Contratado(c);
        if (r.longValue() == 1) {
            r = implementDocenteEscalafonDAO.Actualizar_Cartilla_Ev_Aut_Car_Nota(c, ee);
            Resolucion rl = new Resolucion();
            Cartilla cc = new Cartilla();
            cc.setIdCartilla(r.longValue());
            rl.setCartilla(cc);
            rl.setContenido(modelo("AUTORIDAD"));
            x = implementDocenteEscalafonDAO.actualizar_contenido_Resolucion(rl);
        }
        return r;
    }

    @Override
    public ResolucionBean obtener_resolucion_by_ci_gestion(CartillaBean obj) {
        Resolucion resolucion=new Resolucion();
        ResolucionBean resolucionBean=new ResolucionBean();
        resolucionBean.setIdResolucion(0);
        resolucionBean.setCategoria("A");
        resolucionBean.setContenido("NO SE ENCONTRO NINGUNA RESOLUCION");
        resolucionBean.setGestion(obj.getGestion());
        resolucionBean.setIdCartilla(0);
        resolucionBean.setPuntajeAcumulado(0);
        resolucionBean.setPuntajeEscalafon(0);
        resolucionBean.setTipoResolucion("NINGUNO");
        
        Cartilla c=new Cartilla();
        
        DocenteEscalafon de=new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        c.setDocenteEscalafon(de);
        c.setGestion(obj.getGestion());
        resolucion=implementDocenteEscalafonDAO.obtener_resolucion_by_ci_gestion(c);
        if( resolucion!=null ){
            resolucionBean.setIdResolucion(resolucion.getIdResolucion());
            resolucionBean.setCategoria(resolucion.getCategoria());
            resolucionBean.setContenido(resolucion.getContenido());
            resolucionBean.setGestion(resolucion.getGestion());
            resolucionBean.setIdCartilla(resolucion.getCartilla().getIdCartilla());
            resolucionBean.setPuntajeAcumulado(resolucion.getPuntajeAcumulado());
            resolucionBean.setPuntajeEscalafon(resolucion.getPuntajeEscalafon());
            resolucionBean.setTipoResolucion(resolucion.getTipoResolucion());
        }
        return resolucionBean;
    }

    @Override
    public Long actualizar_contenido_Resolucion(ResolucionBean obj) {
        Long r=new Long(0);
        Resolucion resolucion=new Resolucion();
        Cartilla c=new Cartilla();
        c.setIdCartilla( obj.getIdCartilla() );
        resolucion.setCartilla(c);
        resolucion.setContenido(obj.getContenido());
        r=implementDocenteEscalafonDAO.actualizar_contenido_Resolucion(resolucion);
        return r;
    }

    @Override
    public CartillaBean obtener_cartilla_by_ci_gestion(CartillaBean obj) {
        Cartilla cartilla=new Cartilla();
        DocenteEscalafon de=new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        cartilla.setDocenteEscalafon(de);
        cartilla.setGestion(obj.getGestion());
        
        CartillaBean cartillaBean=new CartillaBean();
        cartillaBean.setIdCartilla(0);
        cartillaBean.setTotalHoras(0);
        cartillaBean.setTotalParcial(0);
        cartillaBean.setNotaPromedio(0);
        cartillaBean.setPuntajeEscalafon(0);
        cartillaBean.setGestion(obj.getGestion());
        cartillaBean.setCiDocenteEscalafon(obj.getCiDocenteEscalafon());
        
        cartilla=implementDocenteEscalafonDAO.obtener_cartilla_by_ci_gestion(cartilla);
        
        if( cartilla!=null ){
            cartillaBean.setIdCartilla(cartilla.getIdCartilla());
            cartillaBean.setTotalHoras(cartilla.getTotalHoras());
            cartillaBean.setTotalParcial(cartilla.getTotalParcial());
            cartillaBean.setNotaPromedio(cartilla.getNotaPromedio());
            cartillaBean.setPuntajeEscalafon(cartilla.getPuntajeEscalafon());
            cartillaBean.setGestion(cartilla.getGestion());
            cartillaBean.setCiDocenteEscalafon(cartilla.getDocenteEscalafon().getCi());
        }
        
        return cartillaBean;
    }

    @Override
    public List<ContenidoCartillaViewBean> Obtener_Contenido_Cartilla_Extendido1_Ci_Docente(CartillaBean obj) {
        
        List<ContenidoCartillaViewBean> contenidoCartillaViewBeans=new ArrayList<ContenidoCartillaViewBean>();
        Cartilla cartilla=new Cartilla();
        cartilla.setIdCartilla(obj.getIdCartilla());
        contenidoCartillaViewBeans=implementDocenteEscalafonDAO.Obtener_Contenido_Cartilla_Extendido1_Ci_Docente(cartilla);
        return contenidoCartillaViewBeans;
    }

    @Override
    public List<EscalafonBean> Obtener_Escalafon_by_Ci_gestion(CartillaBean obj) {
        List<EscalafonBean> escalafonBeans=new ArrayList<EscalafonBean>();
        Cartilla cartilla=new Cartilla();
        DocenteEscalafon de=new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        cartilla.setDocenteEscalafon(de);
        cartilla.setGestion(obj.getGestion());
        escalafonBeans=implementDocenteEscalafonDAO.Obtener_Escalafon_by_Ci_gestion(cartilla);
        return escalafonBeans;
    }

    @Override
    public EscalafonBean Obtener_Escalafon_by_id_cartilla(CartillaBean obj) {
        EscalafonBean escalafonBean=new EscalafonBean();
        EscalafonBean eb=new EscalafonBean();
        Cartilla cartilla=new Cartilla();
        cartilla.setIdCartilla(obj.getIdCartilla());
        eb.setIdEscalafon(0);
        eb.setPuntaje(0);
        eb.setPuntajeAcumulado(0);
        eb.setCategoria("");
        eb.setGestion("");
        eb.setIdCartilla(0);
        eb.setCiDocenteEscalafon("");
        
        escalafonBean=implementDocenteEscalafonDAO.Obtener_Escalafon_by_id_cartilla(cartilla);
        if(escalafonBean!=null){
            eb.setIdEscalafon(escalafonBean.getIdEscalafon());
            eb.setPuntaje(escalafonBean.getPuntaje());
            eb.setPuntajeAcumulado(escalafonBean.getPuntajeAcumulado());
            eb.setCategoria(escalafonBean.getCategoria());
            eb.setGestion(escalafonBean.getGestion());
            eb.setIdCartilla(escalafonBean.getIdCartilla());
            eb.setCiDocenteEscalafon(escalafonBean.getCiDocenteEscalafon());
        }
        return eb;
    }

    @Override
    public EvaluacionEscalafonBean Obtener_EvaluacionEscalafon_by_id_cartilla(CartillaBean obj) {
        EvaluacionEscalafonBean escalafonBean=new EvaluacionEscalafonBean();
        EvaluacionEscalafonBean eb=new EvaluacionEscalafonBean();
        Cartilla cartilla=new Cartilla();
        cartilla.setIdCartilla(obj.getIdCartilla());
        eb.setIdEvaluacionEscalafon(0);
        eb.setTipoEvaluacion("");
        eb.setCargo("");
        eb.setLugar("");
        eb.setNota(0);
        eb.setIdCartilla(0);
        
        escalafonBean=implementDocenteEscalafonDAO.Obtener_EvaluacionEscalafon_by_id_cartilla(cartilla);
        if(escalafonBean!=null){
            eb.setIdEvaluacionEscalafon(escalafonBean.getIdEvaluacionEscalafon());
            eb.setTipoEvaluacion(escalafonBean.getTipoEvaluacion());
            eb.setCargo(escalafonBean.getCargo());
            eb.setLugar(escalafonBean.getLugar());
            eb.setNota(escalafonBean.getNota());
            eb.setIdCartilla(escalafonBean.getIdCartilla());
        }
        return eb;
    }

    @Override
    public CarreraBean obtener_Carrera_by_ci(CartillaBean obj) {
        Cartilla cartilla=new Cartilla();
        DocenteEscalafon de=new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        cartilla.setDocenteEscalafon(de);
        return implementDocenteEscalafonDAO.obtener_Carrera_by_ci(cartilla);
    }

    @Override
    public FacultadBean obtener_Facultad_by_ci(CartillaBean obj) {
        Cartilla cartilla=new Cartilla();
        DocenteEscalafon de=new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        cartilla.setDocenteEscalafon(de);
        return implementDocenteEscalafonDAO.obtener_Facultad_by_ci(cartilla);
    }

    @Override
    public UniversidadBean obtener_Universidad_by_ci(CartillaBean obj) {
        Cartilla cartilla=new Cartilla();
        DocenteEscalafon de=new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        cartilla.setDocenteEscalafon(de);
        return implementDocenteEscalafonDAO.obtener_Universidad_by_ci(cartilla);
    }

    @Override
    public Long Actualizar_Cartilla_Refresh(CartillaBean obj) {
        Cartilla cartilla=new Cartilla();
        DocenteEscalafon de=new DocenteEscalafon();
        de.setCi(obj.getCiDocenteEscalafon());
        cartilla.setDocenteEscalafon(de);
        cartilla.setGestion(obj.getGestion());
        return implementDocenteEscalafonDAO.Actualizar_Cartilla_Refresh(cartilla);
    }
    
    
}
