package net.itinajero;

import java.util.LinkedList;
import java.util.List;

public class TestThread {
   public static void main(String[] args){
      List<String> recipients = new LinkedList();
      recipients.add("ivanetinajero@gmail.com");
      //recipients.add("ivan.tinajero@sazacatecas.gob.mx");
      //recipients.add("ivan.tinajero@cimat.mx"); 
      //EmailThread email1 = new EmailThread(recipients,"Alerta","<h1><b>Texto HTML</b></h1>","");
      //email1.start();
           
      String archivo = "/home/ivan/Documents/permisoEconomico.xlsx";
      EmailThread email2 = new EmailThread(recipients,"Hoja Excel","<h1><b>Texto HTML con Archivo</b></h1>",archivo);
      email2.start();
      
   }
}
