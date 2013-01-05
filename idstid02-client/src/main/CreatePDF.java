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


public class CreatePDF{

  private static String FILE = "DDT.pdf";
  private static Font catFont = new Font(Font.TIMES_ROMAN, 18,
      Font.BOLD);
  private static Font redFont = new Font(Font.TIMES_ROMAN, 12,
      Font.NORMAL, Color.RED);
  private static Font subFont = new Font(Font.TIMES_ROMAN, 16,
      Font.BOLD);
  private static Font Norm = new Font(Font.TIMES_ROMAN, 10,
      Font.NORMAL);
  
  private Object[][] data;
  private Object[] tit;
  private String nwTitle = "DDT Numero: ";
  private String dtInv;
  private String mitt;
  
  public CreatePDF(Object[][] data, Object[] tit, String codDDT, String dtInv, String mitt) {
	  this.data = data;
	  this.tit = tit;
	  this.nwTitle += codDDT;
	  this.dtInv = dtInv;
	  this.mitt = mitt;

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
	    
	    title.add(new Paragraph(nwTitle, catFont));

	    document.add( Chunk.NEWLINE );
	    document.add( Chunk.NEWLINE );	
	    document.add( Chunk.NEWLINE );
	    document.add( Chunk.NEWLINE );
	    
	    Paragraph prefaceM = new Paragraph();
	    prefaceM.add(new Paragraph(Chunk.NEWLINE+"Mittente: "+ mitt,
	        Norm));

	    document.add( Chunk.NEWLINE );
	    document.add( Chunk.NEWLINE );
	    Paragraph preface = new Paragraph();
	    preface.add(new Paragraph(dtInv+Chunk.NEWLINE+Chunk.NEWLINE+"Destinatario: Azienda Casa Madre,"+Chunk.NEWLINE+" PIVA XXXXXXXXXX"+Chunk.NEWLINE+" Tel 0734902558  Fax 0734902625"+Chunk.NEWLINE+" CAP 63821 Via 20 Settembre n.23",
	    		Norm));
	    preface.setIndentationLeft(360);
	    document.add(title);
	    document.add(preface);
	    createTable(prefaceM);
	    document.add(prefaceM);
	    // Start a new page
	    //document.newPage();
	  }

  private void createTable(Paragraph subCatPart)
	      throws BadElementException {
	    PdfPTable table = new PdfPTable(tit.length);

	    PdfPCell c1;
	    for(Object title : tit){
		    c1 = new PdfPCell(new Phrase(title.toString()));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(c1);
	    }
	    table.setHeaderRows(1);
	    
	    for(int k =0; k<data.length; k++){
		    for(int i =0; i<tit.length; i++){
		    	String dat = data[k][i].toString();
		    	table.addCell(dat.toString());
		    }
	    }
	    
	    subCatPart.add(table);

	  }  
  }
  

 

