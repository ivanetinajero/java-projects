package net.itinajero;

import java.util.LinkedList;
import java.util.List;

public class Test {
   public static void main(String[] args){
      EmailService service = new EmailService();
      List<String> recipients = new LinkedList();
      recipients.add("ivanetinajero@gmail.com");
      //recipients.add("ivan.tinajero@sazacatecas.gob.mx");
      //recipients.add("ivan.tinajero@cimat.mx"); 
      //service.send(recipients, "Prueba", "<h2>Este es el mensaje</h2>"); // normal email (no file)
      String archivo = "/home/ivan/Documents/banorte.png";
      service.sendAttachment(recipients, "Fotografia", "<h2>Esta es la foto</h2>",archivo); // email (with file)
   }
}
