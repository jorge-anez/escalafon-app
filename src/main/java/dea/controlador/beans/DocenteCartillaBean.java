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
import dea.controlador.dao_classes.DocenteEscalafonDAO;
import dea.controlador.dao_classes.EscalafonDAO;
import dea.controlador.dao_classes.EvaluacionDAO;
import dea.controlador.dao_classes.PersonaDAO;
import dea.controlador.jsf.PdfEvent;
import dea.modelo.Cartilla;
import dea.modelo.ContenidoCartilla;
import dea.modelo.DocenteEscalafon;
import dea.modelo.Escalafon;
import dea.modelo.Evaluacion;
import dea.modelo.Persona;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 * @author Doppler
 */
@Component
@Scope("session")
public class DocenteCartillaBean implements Serializable{
    @Autowired
    private DocenteEscalafonDAO docenteEscalafonDAO;
    @Autowired
    private PersonaDAO personaDAO;    
    @Autowired
    private EvaluacionDAO evaluacionDAO;    
    @Autowired
    private EscalafonDAO escalafonDAO;    
    /*
    private List<Evaluacion> materiaList;
    private List<Escalafon> escalafonList;
    private List<ContenidoCartilla> contenidoCartillaList;
    */
    private Persona dSelected;
    private String gSelected;    
    private Cartilla carSelected;
    private DocenteEscalafon docenteEscalafon;
    private Escalafon escalafon;
    public DocenteCartillaBean(){
        ExternalContext eContext=FacesContext.getCurrentInstance().getExternalContext();
        try{
            dSelected=(Persona)eContext.getSessionMap().get("user");
            if(dSelected==null){
                eContext.invalidateSession();
                eContext.redirect("../login.xhtml");
            }            
        }catch(IOException exp){}
    }
    public void onTabChange(TabChangeEvent event){
    
    }    
    public void toPdf() throws DocumentException, IOException{
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
                String resolucion=escalafonDAO.getResolucion(dSelected.getCi(), gSelected);
                       resolucion=resolucion==null?"<h3 style='text-align:center;'>Sin Resolucion</h3>":resolucion;
                InputStream  in=new ByteArrayInputStream(resolucion.getBytes());                
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
        //response.getOutputStream().write(data);
        faces.responseComplete();
    }
    public String[] getGestiones(){
        Object[] range=escalafonDAO.getRange();
        if(range[0] == null)
            return new String[0];
        final int from=(Integer)range[0];
        int to=(Integer)range[1];
        String[] r=new String[to-from];
        for(int i=0;i<r.length;i++)
            r[i]=String.valueOf(from+i);        
        return r;
    }
    public void changeGestion(AjaxBehaviorEvent vce){
        String str= (String) ((UIOutput) vce.getSource()).getValue();        
        //List<Object[]> result=getEvaluacionDAO().getMaterias(dSelected.getCi(),str);
        this.gSelected=str;
    }
    /**
     * @return the personaDAO
     */
    public PersonaDAO getPersonaDAO() {
        return personaDAO;
    }

    /**
     * @param personaDAO the personaDAO to set
     */
    public void setPersonaDAO(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }    

    /**
     * @return the docenteEscalafonDAO
     */
    public DocenteEscalafonDAO getDocenteEscalafonDAO() {
        return docenteEscalafonDAO;
    }

    /**
     * @param docenteEscalafonDAO the docenteEscalafonDAO to set
     */
    public void setDocenteEscalafonDAO(DocenteEscalafonDAO docenteEscalafonDAO) {
        this.docenteEscalafonDAO = docenteEscalafonDAO;
    } 

    /**
     * @return the evaluacionDAO
     */
    public EvaluacionDAO getEvaluacionDAO() {
        return evaluacionDAO;
    }

    /**
     * @param evaluacionDAO the evaluacionDAO to set
     */
    public void setEvaluacionDAO(EvaluacionDAO evaluacionDAO) {
        this.evaluacionDAO = evaluacionDAO;
    }

    /**
     * @return the escalafonDAO
     */
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
     * @return the escalafonList
     */
    public List<Escalafon> getEscalafonList() {        
        return escalafonDAO.getHistorial(dSelected.getCi(),gSelected);
    }

    /**
     * @return the contenidoCartillaList
     */
    public List<ContenidoCartilla> getContenidoCartillaList() {
        return evaluacionDAO.getMateriasCartilla(dSelected.getCi(), gSelected);
    }

    /**
     * @return the carSelected
     */
    public Cartilla getCarSelected() {        
        //if(carSelected==null) carSelected=new Cartilla();
        this.carSelected=evaluacionDAO.getPuntajeEvaluacion(dSelected.getCi(), gSelected);
        return carSelected;
    }

    /**
     * @param carSelected the carSelected to set
     */
    public void setCarSelected(Cartilla carSelected) {
        this.carSelected = carSelected;
    }

    /**
     * @return the docenteEscalafon
     */
    public DocenteEscalafon getDocenteEscalafon() {
        //if(docenteEscalafon==null) docenteEscalafon=new DocenteEscalafon();
        this.docenteEscalafon=evaluacionDAO.getUniversidad(dSelected.getCi());
        return docenteEscalafon;
    }

    /**
     * @param docenteEscalafon the docenteEscalafon to set
     */
    public void setDocenteEscalafon(DocenteEscalafon docenteEscalafon) {
        this.docenteEscalafon = docenteEscalafon;
    }

   

    /**
     * @return the gSelected
     */
    public String getgSelected(){
        return gSelected;
    }

    /**
     * @param gSelected the gSelected to set
     */
    public void setgSelected(String gSelected) {
        this.gSelected = gSelected;
    }

    /**
     * @return the escalafon
     */
    public Escalafon getEscalafon() {    
        escalafon=evaluacionDAO.getEscalafon(dSelected.getCi(), gSelected);
       if(escalafon==null) {
          escalafon=new Escalafon();
          escalafon.setPuntaje(0);
          escalafon.setObs("Pendiente");
       }
        return escalafon;
    }
    public boolean renderCartilla(){
        this.escalafon=evaluacionDAO.getEscalafon(dSelected.getCi(), gSelected);        
        if(escalafon==null){
          escalafon=new Escalafon();
          escalafon.setObs("Pendiente");
        }
        return true;
    }
    /**
     * @param escalafon the escalafon to set
     */
    public void setEscalafon(Escalafon escalafon) {
        this.escalafon = escalafon;
    }

    /**
     * @return the dSelected
     */
    public Persona getdSelected() {
        return dSelected;
    }

    /**
     * @param dSelected the dSelected to set
     */
    public void setdSelected(Persona dSelected) {
        this.dSelected = dSelected;
    }

    /**
     * @return the resolucion
     */
    public String getResolucion(){   
        String resolucion=escalafonDAO.getResolucion(dSelected.getCi(), gSelected);
        return resolucion==null?"":resolucion;        
    }
}