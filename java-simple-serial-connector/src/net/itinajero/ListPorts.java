// https://code.google.com/archive/p/java-simple-serial-connector/wikis/jSSC_examples.wiki
package net.itinajero;

import jssc.SerialPortList;

public class ListPorts {
    
   public static void main(String[] args) {
      String[] portNames = SerialPortList.getPortNames();
      for (String portName : portNames) {
         System.out.println(portName);
      }
   }
   
}
