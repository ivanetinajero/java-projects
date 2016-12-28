// https://code.google.com/archive/p/java-simple-serial-connector/wikis/jSSC_examples.wiki

/*
  1. El puerto /dev/ttyUSB0 pertenece al grupo dialout.

  $ ls -l /dev/ttyUSB0 
  crw-rw---- 1 root dialout 188, 0 dic 24 18:03 /dev/ttyUSB0

  2. Hay que agregar el usuario actual a ese grupo. 
  $﻿sudo adduser <username> dialout

  3. Cerrar sesion y volver a entrar para aplicar los cambios.
*/  

package net.itinajero;

import java.io.UnsupportedEncodingException;
import jssc.SerialPort;
import jssc.SerialPortException;

public class Main {
   
   public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {          
      
      //SerialPort serialPort = new SerialPort("COM1"); // Windows
      SerialPort serialPort = new SerialPort("/dev/ttyUSB0"); // Linux
      try {
         serialPort.openPort(); // Open serial port.
         serialPort.setParams(9600, 8, 1, 0); // Set params.
         int i = 0;
         while (i<50){
            Thread.sleep(50);
            String text = "P"; // Text for writting.
            serialPort.writeBytes(text.getBytes()); // Write data to port.           
            serialPort.readHexString();
            byte[] buffer = serialPort.readBytes(8); // Read 8 bytes from serial port.          
            String result = new String(buffer);            
            System.out.println("Data: " + result.trim());
            i++;
         }                           
         serialPort.closePort(); // Close serial port.
      } catch (SerialPortException ex) {
         System.out.println("Error SerialPortException: " + ex.getMessage());
      }
   }

}
