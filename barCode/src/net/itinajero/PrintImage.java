package net.itinajero;

import java.io.FileInputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

public class PrintImage {

   public static void main(String[] args) throws Exception{
      PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
      pras.add(new Copies(1));
      PrinterUtility pu = new PrinterUtility();
      PrintService ps = pu.getService("PDF");
      //PrintService ps = pu.getService("Brother-QL-700");
      System.out.println("Printing to " + ps);
      DocPrintJob job = ps.createPrintJob();
      FileInputStream fin = new FileInputStream("/home/ivan/Downloads/alteredImage.png");
      Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.GIF, null);
      job.print(doc, pras);
      fin.close();
   }
}
