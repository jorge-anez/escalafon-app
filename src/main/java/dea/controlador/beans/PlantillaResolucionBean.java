/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import dea.controlador.dao_classes.EscalafonDAO;
import dea.controlador.jsf.PdfEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 * @author Doppler
 */
@Component
@Scope("session")
public class PlantillaResolucionBean implements Serializable{   
    @Autowired
    private EscalafonDAO escalafonDAO;    
    private String resolucion;
    private Map<String,Integer> resolucion_plantilla=new HashMap<String,Integer>(){{                                                                         
                                                                       // put("Seleccionar",0);
                                                                        put("Docente",1);
                                                                        put("Contrato",2);
                                                                        put("Ingreso",3);
                                                                        put("Emerito",4);
                                                                        put("Nota Autoridad Evaluacion",5);
                                                                        put("Nota Autoridad,Repetir",6);
                                                                        put("Nota FEDUTO Evaluacion",7);
                                                                        put("Nota FEDUTO, Repetir",8);
                                                                    }};                                
    private int resSelected;    
    
    public void changeResolucion(AjaxBehaviorEvent vce){        
        this.resSelected=(Integer)((UIOutput) vce.getSource()).getValue();
        this.resolucion=escalafonDAO.getPlantillarResolucion(this.resSelected);
        RequestContext.getCurrentInstance().reset(":form_plantilla");
    }
    public void save(){
     escalafonDAO.savePlantillaResolucion(getResSelected(), resolucion);
    }
    public void toPdf() throws MalformedURLException, DocumentException, IOException{
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext eContext=faces.getExternalContext();        
        HttpServletRequest request=(HttpServletRequest)eContext.getRequest();
        HttpServletResponse response = (HttpServletResponse)eContext.getResponse();
        URL url = new URL(request.getScheme(),request.getServerName(),request.getServerPort(), eContext.getRequestContextPath());
        
        Rectangle page=new Rectangle(612f,943.937f);        
        Document document = new Document(page,42.519685f, 42.519685f, 85.03937f, 56.69291f);
                PdfEvent event=new PdfEvent(new URL(url.toString()+"/resources/logo.png"));
		PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
                writer.setPageEvent(event);
		document.open();
                this.resolucion=escalafonDAO.getPlantillarResolucion(this.resSelected);
                this.resolucion=this.resolucion==null?"<h3 style='text-align:center;'>Sin Resolucion</h3>":this.resolucion;
                InputStream  in=new ByteArrayInputStream(this.resolucion.getBytes());                
                HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
                htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
                CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);                
                Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, new HtmlPipeline(htmlContext,
                                            new PdfWriterPipeline(document, writer)));
                XMLWorker worker = new XMLWorker(pipeline, true);
                XMLParser p = new XMLParser(worker);
                p.parse(in);        
	document.close();        
        response.setContentType("application/pdf");
        //response.setContentLength(data.length);
        response.setHeader("charset", "utf-8");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-disposition", "attachment; filename=Resolucion.pdf");        
        faces.responseComplete(); 
    }
    public List<String> getVariables(){        
        List<String> variables=new ArrayList<String>();
            variables.add("#{carrera}");
            variables.add("#{facultad}");
            variables.add("#{gestion_academica}");
            variables.add("#{nombre_docente}");
            variables.add("#{puntaje_categoria}");
            variables.add("#{puntaje_acumulado}");
            variables.add("#{categoria}");
        return variables;
    }
    public EscalafonDAO getEscalafonDAO() {
        return escalafonDAO;
    }
    /**
     * @param escalafonDAO the escalafonDAO to set
     */
    public void setEscalafonDAO(EscalafonDAO escalafonDAO) {
        this.escalafonDAO = escalafonDAO;
    }
    /**
     * @return the resolucion
     */
    public String getResolucion(){
        this.resolucion=escalafonDAO.getPlantillarResolucion(this.resSelected);
        
        return resolucion;
    }

    /**
     * @param resolucion the resolucion to set
     */
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    /**
     * @return the resSelected
     */
    public int getResSelected() {
        return resSelected;
    }
    /**
     * @param resSelected the resSelected to set
     */
    public void setResSelected(int resSelected) {
        this.resSelected = resSelected;
    }

    /**
     * @return the resolucion_plantilla
     */
    public Map<String,Integer> getResolucion_plantilla() {
        return resolucion_plantilla;
    }

    /**
     * @param resolucion_plantilla the resolucion_plantilla to set
     */
    public void setResolucion_plantilla(Map<String,Integer> resolucion_plantilla) {
        this.resolucion_plantilla = resolucion_plantilla;
    }
}