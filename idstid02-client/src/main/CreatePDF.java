package main;
import java.io.File;

public class CreatePDF{
    public static void main(String arg[])throws Exception{
    	 File file = new File("C:/newfile.pdf");
         if (file.exists()) {
                 System.out.println("File already exists");
         } else {
                 file.createNewFile();
                 System.out.println("File is created");
         }
   }
}

