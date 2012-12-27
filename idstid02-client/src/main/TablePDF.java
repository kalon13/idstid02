package main;
import java.io.*;

import javax.swing.JTable;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class TablePDF {

     public static void main(String[] args){
        try {
        	JTable jtable = new JTable();
        	
            Document iText_Doc = new Document();
            Paragraph p = new Paragraph("paragraf", FontFactory.getFont(FontFactory.HELVETICA, 14));
            p.setAlignment(Element.ALIGN_LEFT);
                        
            PdfWriter.getInstance(iText_Doc, new FileOutputStream("CreateTableExample.pdf"));
            iText_Doc.open();
            iText_Doc.add(p);
            iText_Doc.add( Chunk.NEWLINE );
            PdfPTable table = new PdfPTable(3);    
            
            table.addCell("0,0 First Cell Data"); // Use AddCell to Add a string data in first Cell
            PdfPCell table_cell; // Create a PDFPCell Object
            table_cell=new PdfPCell(new Phrase("A Cell with Colspan of 2")); // Create a Phrase data for Colspan 2 First Row
            table_cell.setColspan(2); //Define the colspan for Cell object
            table.addCell(table_cell);//push the cell to the table
            
            /* Define a Cell Object with ROWSPAN of 2 and COLSPAN of 2 */
            table_cell=new PdfPCell(new Phrase("A Cell with COLSPAN of 2 and ROWSPAN of 2"));
            table_cell.setColspan(2); //Define the colspan for Cell object
           //table_cell.setRowspan(2); //Define the ROWSPAN for Cell object
            table.addCell(table_cell);//push the cell to the table
            
            /* Add data in left over cells*/
            table.addCell("2,3 Row 2 Column 3");
            table.addCell("3,3 Row 3 Column 3");
            
            iText_Doc.add(table);
            iText_Doc.close();
            
            File flPdf = new File("CreateTableExample.pdf");
            java.awt.Desktop.getDesktop().open(flPdf);
        }
        catch (Exception i)
        {
            System.out.println(i);
        }
    }
}
