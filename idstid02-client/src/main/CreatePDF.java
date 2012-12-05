package main;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class CreatePDF{
  private JTable table;

  public CreatePDF(JTable table) {
	  this.table = table;
	  
  }
  public void print(String urlFile, String paragraf) {
    Document document = new Document(PageSize.A4);
    try {
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(urlFile));

      document.open();
      Paragraph p = new Paragraph(paragraf, FontFactory.getFont(FontFactory.HELVETICA, 14));
      p.setAlignment(Element.ALIGN_LEFT);
      document.add(p);
      
      PdfContentByte cb = writer.getDirectContent();

      cb.saveState();
      Graphics2D g2 = cb.createGraphicsShapes(800, 700);

      Shape oldClip = g2.getClip();
      g2.clipRect(10, 0, 500, 500);
      table.print(g2);
      g2.setClip(oldClip);

      g2.dispose();
      cb.restoreState();
      document.close();
      
      File flPdf = new File(urlFile);
      java.awt.Desktop.getDesktop().open(flPdf);
      } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    document.close();
  }
  
}
 

