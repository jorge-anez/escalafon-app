/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.beans;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import dea.controlador.dao_classes.CarreraDAO;
import dea.controlador.dao_classes.DocenteEscalafonDAO;
import dea.controlador.dao_classes.EscalafonDAO;
import dea.controlador.dao_classes.EvaluacionDAO;
import dea.controlador.dao_classes.FacultadDAO;
import dea.controlador.dao_classes.PersonaDAO;
import dea.controlador.dao_classes.UniversidadDAO;
import dea.controlador.jsf.PdfEvent;
import dea.modelo.Carrera;
import dea.modelo.Cartilla;
import dea.modelo.ContenidoCartilla;
import dea.modelo.Docente;
import dea.modelo.DocenteEscalafon;
import dea.modelo.DocenteRegistroMateria;
import dea.modelo.Escalafon;
import dea.modelo.Evaluacion;
import dea.modelo.Facultad;
import dea.modelo.Materia;
import dea.modelo.Persona;
import dea.modelo.Universidad;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.RowEditEvent;
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
public class DocenteEscalafonBean implements Serializable{
    @Autowired
    private DocenteEscalafonDAO docenteEscalafonDAO;
    @Autowired
    private PersonaDAO personaDAO;   
    @Autowired
    private UniversidadDAO universidadDAO;
    @Autowired
    private FacultadDAO facultadDAO;
    @Autowired
    private CarreraDAO carreraDAO;
    @Autowired
    private EvaluacionDAO evaluacionDAO;    
    @Autowired
    private EscalafonDAO escalafonDAO; 
    private DocenteEscalafon registro;
    private String uSelected;
    private long cSelected,fSelected;
    private String gSelected;
    private Persona dSelected;    
    private int tipoRes;
    private Cartilla carSelected;
    private DocenteEscalafon docenteEscalafon;
    private Escalafon escalafon;
    private String resolucion,obs;
    private int nota,from,to;
    private List<Evaluacion> materiaList;
    public void reset(){
    }
    public void changeUniversidad(AjaxBehaviorEvent vce){      
        uSelected= (String) ((UIOutput) vce.getSource()).getValue();
        fSelected=cSelected=0;
    }
    public void changeFacultad(AjaxBehaviorEvent vce){      
        this.fSelected = (Long) ((UIOutput) vce.getSource()).getValue();
        cSelected=0;
    }
    public void changeCarrera(AjaxBehaviorEvent vce){      
       cSelected = (Long) ((UIOutput) vce.getSource()).getValue();        
    }
    public void changeGestion(AjaxBehaviorEvent vce){
        this.gSelected= (String) ((UIOutput) vce.getSource()).getValue();
        
        List<Object[]> result=getEvaluacionDAO().getMaterias(dSelected.getCi(),gSelected);        
        //this.resolucion=escalafonDAO.getResolucion(dSelected.getCi(), gSelected);        
        materiaList=new ArrayList();
        for (Object[] e : result) {            
            Evaluacion eval=(Evaluacion)e[0];         
            DocenteRegistroMateria r=(DocenteRegistroMateria)e[1];
            Materia m=(Materia) e[2];
            r.setMateria(m);
            eval.setDocenteRegistroMateria(r);            
            materiaList.add(eval);
        }
    }
    public void changeException(AjaxBehaviorEvent vce){        
        String str= (String) ((UIOutput) vce.getSource()).getValue();       
        this.tipoRes=Integer.parseInt(str);   
        if(tipoRes==0) return;        
        if(this.tipoRes==6||this.tipoRes==8){            
            Escalafon ant=evaluacionDAO.getEscalafonAnt(dSelected.getCi(), gSelected);
            nota=(int) ant.getPuntaje(); 
        }else{
            this.carSelected=evaluacionDAO.getPuntajeEvaluacion(dSelected.getCi(), gSelected);
            nota=(int) this.carSelected.getPuntajeEscalafon();
        }        
    }
    public void changeFrom(AjaxBehaviorEvent vce){        
        from= (Integer)((UIOutput) vce.getSource()).getValue();
    }
    public void changeTo(AjaxBehaviorEvent vce){        
        to= (Integer)((UIOutput) vce.getSource()).getValue();
    }
    public void onTabChange(TabChangeEvent event){
        Tab tap=event.getTab();    
        if(tap.getId().compareTo("tabResolucion")==0){
            resolucion=escalafonDAO.getResolucion(dSelected.getCi(), gSelected);
        }
    }
    public void loadResolucionPlantilla(){
        this.resolucion=escalafonDAO.getResolucionPlantilla(dSelected.getCi(), gSelected);        
        escalafon=evaluacionDAO.getEscalafon(dSelected.getCi(), gSelected);
        if(escalafon!=null){
            DateFormat df=DateFormat.getDateInstance(DateFormat.LONG,new Locale("es","BOL"));        
            resolucion=resolucion.replace("#{date}",df.format(new Date()));
            resolucion=resolucion.replace("#{carrera}",carreraDAO.read(cSelected).getNombre());
            resolucion=resolucion.replace("#{facultad}",facultadDAO.read(fSelected).getNombre());
            resolucion=resolucion.replace("#{gestion_academica}",gSelected);
            resolucion=resolucion.replace("#{nombre_docente}",dSelected.getDocente().getGradoAcademico()+" "+ dSelected.getNombre()+" "+dSelected.getApellido());
            resolucion=resolucion.replace("#{puntaje}",""+escalafon.getPuntaje());
            resolucion=resolucion.replace("#{puntaje_acumulado}",""+escalafon.getPuntajeAcumulado());
            resolucion=resolucion.replace("#{categoria}",""+escalafon.getCategoria());
        }
    }
    public void saveResolucion(){
        escalafonDAO.saveResolucion(dSelected.getCi(), gSelected,this.resolucion);        
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
                this.resolucion=escalafonDAO.getResolucion(dSelected.getCi(), gSelected);
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
    public void pdfRender() throws DocumentException, IOException, CssResolverException{
        
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext eContext=faces.getExternalContext();
        HttpServletRequest request=(HttpServletRequest)eContext.getRequest();
        HttpServletResponse response = (HttpServletResponse)eContext.getResponse();
        
        URL url = new URL(request.getScheme(),request.getServerName(),request.getServerPort(), eContext.getRequestContextPath());
        //943.93700787401574803149606299213
        //Rectangle r=PageSize.LETTER;
        Rectangle page=new Rectangle(943.937f, 612f);
        
        //1.5    2.5
        //85.039370078740157480314960629921   56.692913385826771653543307086614
        Document document = new Document(page,42.519685f, 42.519685f, 85.03937f, 56.69291f);
                PdfEvent event=new PdfEvent(new URL(url.toString()+"/resources/logo.png"));
		PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
                writer.setPageEvent(event);
		document.open();
                
                
		// convert the HTML with the built-in convenience method
                //document.add(new Paragraph("Hello World"));
                //document.add(new Paragraph("Hello World"));
                
                
                URLConnection con = new URL(url.toString()+"/faces/crud/reporte_pdf.xhtml").openConnection();
                InputStream in = con.getInputStream();
                
                //System.out.println(u.toString());
                //String encoding = con.getContentEncoding();                
                //BufferedReader buff=new BufferedReader(new InputStreamReader(in));                
                //System.out.println(buff.readLine());
                //System.out.println(reconstructedURL.toString());                
                //InputStream input = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/crud/reporte.html");
		//XMLWorkerHelper.getInstance().parseXHtml(writer, document, input);
                
                
                
                
                
                HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
                htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
                CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
                InputStream cssInput = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdfStyle.css");
                CssFile cssfiletest = XMLWorkerHelper.getCSS(cssInput);
                //cssResolver.addCss("table{border:1px;}", true);
                cssResolver.addCss(cssfiletest);
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
        response.setHeader("Content-disposition", "attachment; filename=review.pdf");
        //response.getOutputStream().write(data);
        faces.responseComplete();       
    }
    
    public String[] getGestiones(){
        Object[] range=escalafonDAO.getRange();
        int from=(Integer)range[0];
        int to=(Integer)range[1];
        String[] r=new String[1+to-from];
        for(int i=0;i<r.length;i++)
            r[i]=String.valueOf(from+i);        
        return r;
    }
    public List<Column> getHeader(){
        if(from==0||to==0){
            Object[] range=escalafonDAO.getRange();
            from=(Integer)range[0];
            to=(Integer)range[1];
        }
        int from,to;
        if(this.to<this.from){
            from=this.to;
            to=this.from;
        }else{
            from=this.from;
            to=this.to;
        }
            
        List<Column> list=new ArrayList<Column>();
        list.add(new Column(0,"#",2,false));
        list.add(new Column(1,"DOCENTE",18,true));
        list.add(new Column(2,"FACULTAD",14,true));
        list.add(new Column(3,"CARRERA",14,true));
        int j=4;
        float interval=Math.abs(to-from)==0?1:Math.abs(to-from);
        float w=52/(2.0f*interval+1.0f);
        for(int i=from;i<=to;i++){                
                list.add(new Column(j,"EV/"+String.valueOf(i).substring(2),w,false)); j++;
                list.add(new Column(j,"P/"+String.valueOf(i).substring(2),w,false)); j++;
        }
        list.add(new Column(j,"CAT.",w,false)); j++;
        return list;
    }
    public List<Object[]> getReporteData(){
        //return escalafonDAO.getReporte();        
        return (to<from)?escalafonDAO.getReporteRagne(to,from):escalafonDAO.getReporteRagne(from,to);
    }
    public void reporteDataToPDF() throws MalformedURLException, DocumentException, IOException{
        List<Object[]> result=(to<from)?escalafonDAO.getReporteRagne(to,from):escalafonDAO.getReporteRagne(from,to);
        List<Column> cols=this.getHeader();
        Rectangle page=new Rectangle(943.937f, 612f);        
        
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext eContext=faces.getExternalContext();
        HttpServletRequest request=(HttpServletRequest)eContext.getRequest();
        HttpServletResponse response = (HttpServletResponse)eContext.getResponse();        
        URL url = new URL(request.getScheme(),request.getServerName(),request.getServerPort(), eContext.getRequestContextPath());
        Document document = new Document(page,42.519685f, 42.519685f, 85.03937f, 56.69291f);
                PdfEvent event=new PdfEvent(new URL(url.toString()+"/resources/logo.png"));
		PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
                writer.setPageEvent(event);
        document.open();
                
            Paragraph pa;
            PdfPCell cell;
            pa=new Paragraph("DIRECCION DE EVALUACION Y ACREDITACION",FontFactory.getFont(FontFactory.HELVETICA, 13));  pa.setAlignment(Element.ALIGN_CENTER);
            document.add(pa);        
            pa=new Paragraph("Escalafon Docente",FontFactory.getFont(FontFactory.HELVETICA, 12));  pa.setAlignment(Element.ALIGN_CENTER);
            document.add(pa);
            document.add(new Paragraph(" "));
            PdfPTable table=new PdfPTable(cols.size());     
                    table.setHorizontalAlignment(Element.ALIGN_LEFT);                
                    table.setWidthPercentage(100f);
            float widths[]=new float[cols.size()];            
            
            int j=0;
            for(Column e:cols){
                cell=new PdfPCell(new Phrase(e.getHeader(),FontFactory.getFont(FontFactory.HELVETICA,9.0f)));
                cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                widths[j++]=e.getWidth()/100;
            }
            //System.out.println(Arrays.toString(widths));
                    table.setWidths(widths);
            for(Object r[]:result){
                for(Column e:cols){
                    String str=r[e.getIndex()]==null?" ":r[e.getIndex()].toString();
                    cell=new PdfPCell(new Phrase(str,FontFactory.getFont(FontFactory.HELVETICA,8.0f)));
                    table.addCell(cell);
                }
            }
            document.add(table);
        document.close();        
        response.setContentType("application/pdf");
        //response.setContentLength(data.length);
        response.setHeader("charset", "utf-8");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-disposition", "attachment; filename=escalafon.pdf");
        //response.getOutputStream().write(data);
        faces.responseComplete();
    }
    public void updateEscalafon(){
        if(tipoRes==0) return;
        this.escalafon=evaluacionDAO.getEscalafon(dSelected.getCi(), gSelected);        
        if(this.escalafon!=null){            
            this.escalafon.setTipoRes(this.tipoRes);
            this.escalafon.setPuntaje(this.nota);
            this.escalafon.setObs(this.obs);
            escalafonDAO.update(escalafon);
            return;
        }
        this.escalafon=new Escalafon();
        this.escalafon.setTipoRes(this.tipoRes);
        this.escalafon.setPuntaje(this.nota);
        this.escalafon.setObs(this.obs);
        this.escalafon.setGestion(carSelected.getGestion());
        this.escalafon.setCartilla(carSelected);
        this.escalafon.setDocenteEscalafon(carSelected.getDocenteEscalafon());        
        escalafonDAO.create(escalafon);          
    }
    public void retirarRegistro(DocenteEscalafon r){
        this.registro=new DocenteEscalafon();
        this.registro.setCi(r.getCi());
        this.registro.setDocente(r.getDocente());        
        this.registro.setCarrera(r.getCarrera());       
    }
    public void retirarRegistro(){
        this.docenteEscalafonDAO.delete(this.registro);       
    }
    public void prepareRegistro(){
        registro=new DocenteEscalafon();
        registro.setDocente(new Docente());
        registro.setFechaNacimiento(new Date());        
        Carrera c=new Carrera();
                c.setIdCarrera(cSelected);
        registro.setCarrera(c);        
    }
    public void guardarRegistro(){
        registro.setTipo("NUEVO");        
        docenteEscalafonDAO.create(registro);        
    }    
    public void update(DocenteEscalafon e){
            registro=new DocenteEscalafon();
            registro.setCarrera(e.getCarrera());
            registro.setDocente(e.getDocente());
            registro.setCi(e.getCi());
            registro.setCorreoElectronico(e.getCorreoElectronico());
            registro.setFechaNacimiento(e.getFechaNacimiento());
            registro.setTipo(e.getTipo());
    }
    public void update(){
        docenteEscalafonDAO.update(registro);        
    }
    public void evaluarDocente(String ci) throws IOException{
        //this.setdSelected(ci);
        FacesContext context= FacesContext.getCurrentInstance();
        dSelected=personaDAO.read(ci);
        this.getMateriaList().clear();
        
        Persona p=(Persona)context.getExternalContext().getSessionMap().get("user");
        //Set<Integer> h=new HashSet<>();
        
        Set<Integer> h=personaDAO.getPerfiles(p.getCi());        
        
        if(h.contains(2)||h.contains(1))
            context.getExternalContext().redirect("cartilla.xhtml");
        else
            context.getExternalContext().redirect("cartilla_read_only.xhtml");
        //RequestContext.getCurrentInstance().reset(":table_years");
    }
    
    public void onEdit(RowEditEvent event) {
        Evaluacion eval=(Evaluacion) event.getObject();       
        Evaluacion e=new Evaluacion();
        e.setIdEvaluacion(eval.getIdEvaluacion());
        e.setNota(eval.getNota());
        e.setDocenteRegistroMateria(eval.getDocenteRegistroMateria());
        evaluacionDAO.update(e);
    }
    public void cartilla_pdf_todos() throws MalformedURLException, DocumentException, IOException{
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext eContext=faces.getExternalContext();        
        HttpServletRequest request=(HttpServletRequest)eContext.getRequest();
        HttpServletResponse response = (HttpServletResponse)eContext.getResponse();
        URL url = new URL(request.getScheme(),request.getServerName(),request.getServerPort(), eContext.getRequestContextPath());
        //Rectangle page=new Rectangle(612f,943.937f);     
        Rectangle page=new Rectangle(792.0f,612.0f);
        Document document = new Document(page,42.519685f, 42.519685f, 85.03937f, 56.69291f);
                PdfEvent event=new PdfEvent(new URL(url.toString()+"/resources/logo.png"));
		PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
                writer.setPageEvent(event);
	document.open();
        List<DocenteEscalafon> list=docenteEscalafonDAO.readAll();        
            for(DocenteEscalafon e:list){
                Persona pS=personaDAO.read(e.getCi());
                if(this.build_table(document,writer,pS, gSelected))
                    document.newPage();
            }
        document.close();        
        response.setContentType("application/pdf");        
        response.setHeader("charset", "utf-8");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-disposition", "attachment; filename=Cartilla.pdf");        
        faces.responseComplete();
    }
    public void cartilla_docente_pdf() throws MalformedURLException, DocumentException, IOException{
        FacesContext faces = FacesContext.getCurrentInstance();
        ExternalContext eContext=faces.getExternalContext();        
        HttpServletRequest request=(HttpServletRequest)eContext.getRequest();
        HttpServletResponse response = (HttpServletResponse)eContext.getResponse();
        URL url = new URL(request.getScheme(),request.getServerName(),request.getServerPort(), eContext.getRequestContextPath());
        Rectangle page=new Rectangle(792.0f,612.0f);        
        
        Document document = new Document(page,42.519685f, 42.519685f, 85.03937f, 56.69291f);
                PdfEvent event=new PdfEvent(new URL(url.toString()+"/resources/logo.png"));
		PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
                writer.setPageEvent(event);
	document.open();                
            this.build_table(document,writer, dSelected, gSelected);
        document.close();        
        response.setContentType("application/pdf");        
        response.setHeader("charset", "utf-8");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-disposition", "attachment; filename=Cartilla.pdf");        
        faces.responseComplete();        
    }
    public boolean build_table(Document document,PdfWriter writer,Persona pS,String g) throws DocumentException{
        String ci=pS.getCi();
        DocenteEscalafon e=this.evaluacionDAO.getUniversidad(ci);
        List<ContenidoCartilla> cartilla=this.evaluacionDAO.getMateriasCartilla(ci, g);
        List<Escalafon> escalafon_list=this.escalafonDAO.getHistorial(ci,g);
        Cartilla car=this.evaluacionDAO.getPuntajeEvaluacion(ci, g);
        Escalafon es;
        es=this.evaluacionDAO.getEscalafon(ci, g);
        if(es==null) {
          es=new Escalafon();
          es.setPuntaje(0);
          es.setCategoria("");
          es.setObs("Pendiente");
        }
        if(e==null||cartilla==null||escalafon_list==null||car==null) return false;
        Paragraph pa;
        PdfPCell cell;
        pa=new Paragraph("CARTILLA DOCENTE",FontFactory.getFont(FontFactory.HELVETICA, 14));  pa.setAlignment(Element.ALIGN_CENTER);
        document.add(pa);        
        document.add(new Paragraph(" "));
        
        PdfPTable table=new PdfPTable(new float[]{2,5,1.5f,1.5f});     
                table.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                table.setWidthPercentage(70f);                
                table.addCell("Facultad:");
                table.addCell(e.getCarrera().getFacultad().getNombre());
                table.addCell("Gestion:");
                table.addCell(g);
                table.addCell("Carrera:");
                table.addCell(e.getCarrera().getNombre());
                table.addCell("Categoria:");
                table.addCell(es.getCategoria());
                table.addCell("Docente:");
                table.addCell(pS.getNombre()+" "+pS.getApellido());
                table.addCell("");
                table.addCell("");
        document.add(table);        

        PdfPTable tableCartilla=new PdfPTable(new float[]{13,47,10,10,10,10});
            tableCartilla.setTotalWidth(530);
//tableCartilla.setWidthPercentage(70f);      

            cell=new PdfPCell(new Phrase("Sigla",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase("Materia",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase("Paralelo",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase("Carga Horaria",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase("Puntaje Evalucion.",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase("Parcial",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCartilla.addCell(cell);            
        
        for(ContenidoCartilla r:cartilla){
            tableCartilla.addCell(new Phrase(r.getEvaluacion().getDocenteRegistroMateria().getMateria().getSiglaMateria(),FontFactory.getFont(Font.SANS_SERIF, 11)));
            tableCartilla.addCell(new Phrase(r.getEvaluacion().getDocenteRegistroMateria().getMateria().getNombre(),FontFactory.getFont(Font.SANS_SERIF, 11)));
            
            cell=new PdfPCell(new Phrase(r.getEvaluacion().getDocenteRegistroMateria().getParalelo(),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCartilla.addCell(cell);            
            
            cell=new PdfPCell(new Phrase(String.format("%d", r.getEvaluacion().getDocenteRegistroMateria().getHoras()),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);

            cell=new PdfPCell(new Phrase(String.format("%.2f", r.getPuntajeEvaluacion()),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase(String.format("%.2f", r.getPuntajeParcial()),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
        }
        
            
            cell=new PdfPCell(new Phrase("Resumen",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setColspan(6); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase("T. Horas",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setColspan(3); cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase(String.format("%d", (int)car.getTotalHoras()),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase("T. Parcial",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase(String.format("%.2f", car.getTotalParcial()),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
            
                    
            cell=new PdfPCell(new Phrase("Promedio",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setColspan(5); cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase(String.format("%.2f", car.getNotaPromedio()),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
        

            
            cell=new PdfPCell(new Phrase("Puntaje Escalafon",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setColspan(5); cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
            
            cell=new PdfPCell(new Phrase(String.format("%d", (int)es.getPuntaje()),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);                     
            
            cell=new PdfPCell(new Phrase("Observación: "+es.getObs(),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setColspan(6); cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCartilla.addCell(cell);
        
        PdfPTable tableHistorial=new PdfPTable(3);
            //tableHistorial.setWidthPercentage(30f);
            tableHistorial.setTotalWidth(150);
            tableHistorial.setHorizontalAlignment(Element.ALIGN_CENTER);            
        
            cell=new PdfPCell(new Phrase("Ges.",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
            tableHistorial.addCell(cell);
            
            cell=new PdfPCell(new Phrase("P. Eval.",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
            tableHistorial.addCell(cell);
            
            cell=new PdfPCell(new Phrase("P. Acum.",FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setGrayFill(0.7f); cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
            tableHistorial.addCell(cell);
            
        for(Escalafon r:escalafon_list){            
            cell=new PdfPCell(new Phrase(r.getGestion(),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableHistorial.addCell(cell);            
            
            cell=new PdfPCell(new Phrase(String.format("%d", (int)r.getPuntaje()),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableHistorial.addCell(cell);
            
            cell=new PdfPCell(new Phrase(String.format("%d", (int)r.getPuntajeAcumulado()),FontFactory.getFont(Font.SANS_SERIF, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableHistorial.addCell(cell);            
        }        
        
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Phrase("EVALUACIÓN DOCENTE"),250f,PageSize.LETTER.getWidth()*0.65f, 0);        
        tableCartilla.writeSelectedRows(0, -1,42.519685f, PageSize.LETTER.getWidth() * 0.63f,writer.getDirectContent());        
        
        ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_LEFT, new Phrase("HISTORIAL"),PageSize.LETTER.getHeight()-150,PageSize.LETTER.getWidth()*0.65f, 0);        
        tableHistorial.writeSelectedRows(0, -1,PageSize.LETTER.getHeight()-150-42.519685f, PageSize.LETTER.getWidth() * 0.63f,writer.getDirectContent());

        return true;
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
     * @return the universidadDAO
     */
    public UniversidadDAO getUniversidadDAO() {
        return universidadDAO;
    }

    /**
     * @param universidadDAO the universidadDAO to set
     */
    public void setUniversidadDAO(UniversidadDAO universidadDAO) {
        this.universidadDAO = universidadDAO;
    }

    /**
     * @return the facultadDAO
     */
    public FacultadDAO getFacultadDAO() {
        return facultadDAO;
    }

    /**
     * @param facultadDAO the facultadDAO to set
     */
    public void setFacultadDAO(FacultadDAO facultadDAO) {
        this.facultadDAO = facultadDAO;
    }

    /**
     * @return the carreraDAO
     */
    public CarreraDAO getCarreraDAO() {
        return carreraDAO;
    }

    /**
     * @param carreraDAO the carreraDAO to set
     */
    public void setCarreraDAO(CarreraDAO carreraDAO) {
        this.carreraDAO = carreraDAO;
    }

    /**
     * @return the universidadList
     */
    public List<Universidad> getUniversidadList() {
        return universidadDAO.readAll();
    }

   
    /**
     * @return the facultadList
     */
    public List<Facultad> getFacultadList() {        
        return facultadDAO.readFacultdad(this.uSelected);
    }

    

    /**
     * @return the carreraList
     */
    public List<Carrera> getCarreraList() {        
        return carreraDAO.readCarrera(fSelected);
    }
    
    /**
     * @return the docenteList
     */
    public List<Persona> getDocenteList(){
        List<Persona> docenteList=new ArrayList<Persona>();
        List<Object[]> result=docenteEscalafonDAO.getDocenteList();
        for (Object[] e : result) {
            Persona p=(Persona) e[0];
            Docente d=(Docente) e[1];
            p.setDocente(d);
            docenteList.add(p);
        }        
        return docenteList;
    }
    /**
     * @return the registroList
     */
    public List<DocenteEscalafon> getRegistroList() {
      List<Object[]> result=docenteEscalafonDAO.getRegistro(cSelected);
      List<DocenteEscalafon> registroList=new ArrayList<DocenteEscalafon>();
        for (Object[] e : result) {
            DocenteEscalafon r=(DocenteEscalafon) e[0];            
            Persona p=(Persona)e[1];
            Docente d=new Docente();
                    d.setCi(p.getCi());
                    d.setPersona(p);
            r.setDocente(d);           
            registroList.add(r);
        }        
        return registroList;
    }
   
    /**
     * @return the registro
     */
    public DocenteEscalafon getRegistro() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(DocenteEscalafon registro) {
        this.registro = registro;
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
     * @return the cSelected
     */
    public long getcSelected() {
        return cSelected;
    }

    /**
     * @param cSelected the cSelected to set
     */
    public void setcSelected(long cSelected) {
        this.cSelected = cSelected;
    }

    /**
     * @return the materiaList
     */
    public List<Evaluacion> getMateriaList(){        
        if(materiaList==null)
            materiaList=new ArrayList<Evaluacion>();
        return materiaList;
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
     * @return the tipoRes
     */
    public int getTipoRes() {
        return tipoRes;
    }

    /**
     * @param tipoRes the tipoRes to set
     */
    public void setTipoRes(int tipoRes) {
        this.tipoRes = tipoRes;
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
        return resolucion==null?"":resolucion;        
    }

    /**
     * @param resolucion the resolucion to set
     */
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    /**
     * @return the fSelected
     */
    public long getfSelected() {
        return fSelected;
    }

    /**
     * @param fSelected the fSelected to set
     */
    public void setfSelected(long fSelected) {
        this.fSelected = fSelected;
    }

    /**
     * @return the nota
     */
    public int getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(int nota) {
        this.nota = nota;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @return the uSelected
     */
    public String getuSelected() {
        return uSelected;
    }

    /**
     * @param uSelected the uSelected to set
     */
    public void setuSelected(String uSelected) {
        this.uSelected = uSelected;
    }

    /**
     * @return the from
     */
    public int getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(int from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public int getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(int to) {
        this.to = to;
    }
    
    static public class Column implements Serializable{
        private int index;
        private String header;
        private boolean filterable;
        float width;
        public Column(int i,String s,float width,boolean sw){
            this.index=i;
            this.header=s;
            this.filterable=sw;
            this.width=width;
        }
        public float getWidth(){
            return this.width;
        }
        /**
         * @return the index
         */
        public int getIndex() {
            return index;
        }

        /**
         * @param index the index to set
         */
        public void setIndex(int index) {
            this.index = index;
        }

        /**
         * @return the header
         */
        public String getHeader() {
            return header;
        }

        /**
         * @param header the header to set
         */
        public void setHeader(String header) {
            this.header = header;
        }

        /**
         * @return the filterable
         */
        public boolean isFilterable() {
            return filterable;
        }

        /**
         * @param filterable the filterable to set
         */
        public void setFilterable(boolean filterable) {
            this.filterable = filterable;
        }
    }
}

