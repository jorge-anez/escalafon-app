/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.jsf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Line;
import java.awt.Font;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JORGE-HP
 */
public class PdfEvent implements PdfPageEvent{
    protected Phrase header;
    protected Image image;
    public PdfEvent(URL urlImage){
        header = new Phrase("Universidad Técnica de Oruro",FontFactory.getFont(FontFactory.HELVETICA,10.0f));        
        try{
            //Image.getInstance(urlFile);
            //System.out.println("url es:"+urlImage.toString());
            this.image = Image.getInstance(urlImage);            
        }catch(Exception exp){}
    }
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
        PdfContentByte cb = writer.getDirectContent();        
        ColumnText.showTextAligned(cb,Element.ALIGN_LEFT, header,document.left()-10, document.top()+10, 0);
        
        cb.moveTo(document.left()-10, document.top()+8);
        cb.lineTo(document.right()+10, document.top()+8);
        //cb.moveTo(410, 525);
        //cb.lineTo(410, 0);
        cb.setLineWidth(1.5f);
        cb.stroke();
      
           image.scalePercent(25.0f);
           this.image.setAbsolutePosition(70,document.top()+20);           
           document.add(this.image); 
           
        } catch (DocumentException ex) {
            Logger.getLogger(PdfEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document dcmnt) {
       
    }

    @Override
    public void onStartPage(PdfWriter writer, Document dcmnt) {
       
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document dcmnt) {
        
    }

    @Override
    public void onParagraph(PdfWriter writer, Document dcmnt, float f) {
        
    }

    @Override
    public void onParagraphEnd(PdfWriter writer, Document dcmnt, float f) {
        
    }

    @Override
    public void onChapter(PdfWriter writer, Document dcmnt, float f, Paragraph prgrph) {
        
    }

    @Override
    public void onChapterEnd(PdfWriter writer, Document dcmnt, float f) {
        
    }

    @Override
    public void onSection(PdfWriter writer, Document dcmnt, float f, int i, Paragraph prgrph) {
        
    }

    @Override
    public void onSectionEnd(PdfWriter writer, Document dcmnt, float f) {
        
    }

    @Override
    public void onGenericTag(PdfWriter writer, Document dcmnt, Rectangle rctngl, String string) {
        
    }
}
