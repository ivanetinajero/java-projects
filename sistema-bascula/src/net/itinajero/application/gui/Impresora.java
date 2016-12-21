// http://www.rodrigoasensio.com/2010/08/zpl-2-para-impresoras-zebra-en-java/
// http://www.kinkajou.it/2007/01/use-zebra-printer-in-java.html
package net.itinajero.application.gui;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import net.itinajero.application.gui.dto.Producto;

public class Impresora {

   private Producto producto;
   private String peso;
   private String printerName;

   public Impresora(Producto producto, String peso, String printerName) {
      this.producto = producto;
      this.peso = peso;
      this.printerName = printerName;
   }

   public boolean printLabel() throws PrintException {
      // Prepare string (ZPL Command) to send to the printer
      String zplCommand = "^XA\n"
              + "^FO10,0^ARN,11,7^FD SOME TEXT ^FS\n"
              + "^FO300,0^ARN,11,7^FD SOME VALUE ^FS\n"
              + "^FO10,35^ARN,11,7^FD SOME TEXT ^FS\n"
              + "^FO300,35^ARN,11,7^FD SOME VALUE ^FS\n"
              + "^FO10,70^ARN,11,7^FD SOME CODE ^FS\n"
              + "^FO10,115^ARN,11,7^BCN,60,Y,Y,N^FD 23749237439827 ^FS\n"
              + "^XZ";

      // Vamos a buscar la impresora por nombre
      PrintService psZebra = getService(printerName);
      if (psZebra != null) { // Si encontramos la impresora
         
         // convertimos el comando a bytes  
         byte[] cmd = zplCommand.getBytes();
         DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
         Doc doc = new SimpleDoc(cmd, flavor, null);

         // creamos el printjob  
         DocPrintJob job = psZebra.createPrintJob();

         // imprimimos  
         job.print(doc, null);

         System.out.println("Printing label: " + peso + " of " + producto.getDescripcion());
         return true;
      }
      System.out.println("No fue posible imprimir");
      return false;
   }

   /**
    * Este metodo busca una impresora instalada en el equipo por nombre
    *
    * @param printerName
    * @return
    */
   private PrintService getService(String printerName) {
      PrintService psZebra = null;
      // Buscar la impresora predeterminada
      // PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

      // Buscar todas las impresoras instaladas en el equipo
      PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
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
