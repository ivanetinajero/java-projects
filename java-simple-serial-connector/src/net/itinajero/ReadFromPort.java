// https://code.google.com/archive/p/java-simple-serial-connector/wikis/jSSC_examples.wiki
package net.itinajero;

import jssc.SerialPort;
import jssc.SerialPortException;

public class ReadFromPort {

   public static void main(String[] args) {
      
      //SerialPort serialPort = new SerialPort("COM9"); // Windows
      SerialPort serialPort = new SerialPort("/dev/ttyUSB0"); // Linux
      try {
         Thread.sleep(50);
         serialPort.openPort(); // Open serial port
         System.out.println("Opened Port: " + serialPort.isOpened());
         serialPort.setParams(9600, 8, 1, 0); // Set params. 
         String text = "P";
         serialPort.writeBytes(text.getBytes()); // Write data to port            
         serialPort.readHexString();
         byte[] buffer = serialPort.readBytes(10); // Read 10 bytes from serial port            
         String result = new String(buffer);            
         System.out.println("Data: " + result.trim());                           
         serialPort.closePort(); // Close serial port
         System.out.println("Opened Port: " + serialPort.isOpened());
      } catch (SerialPortException ex) {
         System.out.println("Error SerialPortException: " + ex.getMessage());
      } catch (InterruptedException ex) {
         System.out.println("Error InterruptedException: " + ex.getMessage());
      }
   }
}
