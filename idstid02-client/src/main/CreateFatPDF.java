package main;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class CreateFatPDF{

  private static String FILE = "Fatt.pdf";
  private static Font catFont = new Font(Font.TIMES_ROMAN, 18,
      Font.BOLD);
  private static Font redFont = new Font(Font.TIMES_ROMAN, 12,
      Font.NORMAL, Color.RED);
  private static Font subFont = new Font(Font.TIMES_ROMAN, 16,
      Font.BOLD);
  private static Font Norm = new Font(Font.TIMES_ROMAN, 10,
      Font.NORMAL);
  
  private Object[][] dataLav;
  private Object[] titLav;
  private Object[][] dataExt;
  private Object[] titExt;
  private String fat = "Fattura Numero: ";
  private String dtInv;
  private String codDDT;
  private String mitt;
  private String imp = "Importo TOTALE: ";
  
  public CreateFatPDF(String codDDT, String dtInv, String mitt, String imp) {
	  this.fat += codDDT;
	  this.codDDT = codDDT;
	  this.dtInv = dtInv;
	  this.mitt = mitt;
	  this.imp += imp;	  
  }
  
  public void wrtPDF()
  {
	  
	try {
	      Document document = new Document();
	      FILE = codDDT + FILE;
	      PdfWriter.getInstance(document, new FileOutputStream(FILE));
	      document.open();
	      addDDTPage(document);
	      document.close();
	      File flPdf = new File(FILE);
	        java.awt.Desktop.getDesktop().open(flPdf);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	
  }
  private void addDDTPage(Document document)
	      throws DocumentException {
	    Paragraph title = new Paragraph();
	    document.add( Chunk.NEWLINE );
	    document.add( Chunk.NEWLINE );
	    
	    title.add(new Paragraph(mitt, catFont));

	    document.add( Chunk.NEWLINE );
	    document.add( Chunk.NEWLINE );	
	    document.add( Chunk.NEWLINE );
	    document.add( Chunk.NEWLINE );
	    
	    Paragraph prefaceM = new Paragraph();
	    prefaceM.add(new Paragraph(Chunk.NEWLINE+fat+"  "+dtInv+Chunk.NEWLINE,
	        Norm));

	    document.add( Chunk.NEWLINE );
	    document.add( Chunk.NEWLINE );
	    Paragraph preface = new Paragraph();
	    preface.add(new Paragraph(Chunk.NEWLINE+"Destinatario: Azienda Casa Madre,"+Chunk.NEWLINE+"63821 Via 20 Settembre n.23"+Chunk.NEWLINE+" PIVA XXXXXXXXXX"+Chunk.NEWLINE+" Tel 0734902558  Fax 0734902625",
	    		Norm));
	    preface.setIndentationLeft(360);
	    document.add(title);
	    document.add(preface);
	    createTable(prefaceM, dataLav, titLav);
	    document.add( Chunk.NEWLINE );
	    document.add(prefaceM);
	    document.add( Chunk.NEWLINE );
	    document.add( Chunk.NEWLINE );
	    Paragraph ext = new Paragraph();
	    ext.add(new Paragraph("Extraconsumo:"+Chunk.NEWLINE,
	    		Norm));
	    createTable(ext, dataExt, titExt);
	    //System.out.println(dataExt[0][0]);
	    document.add(ext);
	    
	    document.add( Chunk.NEWLINE );
	    Paragraph importo = new Paragraph();
	    importo.add(new Paragraph(imp, catFont));
	    document.add(importo);
	    // Start a new page
	    //document.newPage();
	  }

  private void createTable(Paragraph subCatPart, Object[][] dt, Object[] titl)
	      throws BadElementException {
	    PdfPTable table = new PdfPTable(titl.length);

	    PdfPCell c1;
	    for(Object title : titl){
		    c1 = new PdfPCell(new Phrase(title.toString()));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);
	    }
	    table.setHeaderRows(1);
	    
	    for(int k =0; k<dt.length; k++){
		    for(int i =0; i<titl.length; i++){
		    	String dat = dt[k][i].toString();
		    	table.addCell(dat.toString());
		    }
	    }
	    
	    subCatPart.add(table);

	  }

public Object[][] getDataExt() {
	return dataExt;
}
public void setDataExt(Object[][] dataExt) {
	this.dataExt = dataExt;
}
public Object[] getTitExt() {
	return titExt;
}
public void setTitExt(Object[] titExt) {
	this.titExt = titExt;
}
public Object[][] getDataLav() {
	return dataLav;
}
public void setDataLav(Object[][] dataLav) {
	this.dataLav = dataLav;
}
public Object[] getTitLav() {
	return titLav;
}
public void setTitLav(Object[] titLav) {
	this.titLav = titLav;
}  
  }
  

 

