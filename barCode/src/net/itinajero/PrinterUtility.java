// http://www.rodrigoasensio.com/2010/08/zpl-2-para-impresoras-zebra-en-java/
// http://www.kinkajou.it/2007/01/use-zebra-printer-in-java.html
// https://docs.oracle.com/javase/8/docs/technotes/guides/jps/spec/jpsOverview.fm4.html#a998638
package net.itinajero;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class PrinterUtility {
  
   /**
    * Este metodo busca una impresora instalada en el equipo por nombre
    *
    * @param printerName
    * @return
    */
   public PrintService getService(String printerName) {
      PrintService psZebra = null;
      // Buscar la impresora predeterminada
      // PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

      // Set the document type
      DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE; 
      //DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;
      
      // Build a set of attributes
      PrintRequestAttributeSet attr_set = new HashPrintRequestAttributeSet(); 
      
//      attr_set.add(OrientationRequested.LANDSCAPE); // orientation      
//      attr_set.add(MediaSizeName.ISO_A4); // A4 paper format 
//      attr_set.add(new PageRanges(3, 4));
//      attr_set.add(Sides.DUPLEX); // find printers that have double-sided printing capability
//      attr_set.add(new Copies(3)); // three copies of your document
//            
      // discover the printers that can print the document type according to the
      // instructions in the attribute set
      PrintService[] services = PrintServiceLookup.lookupPrintServices(null,attr_set);
      
      for (PrintService service : services) {
         //System.out.println("Impresora instalada: " + service.getName());
         if (service.getName().contains(printerName)) {
            psZebra = service;
            break; // Si la encontramos, salimos del for
         }
      }
      return psZebra;
   }

   
}
