// https://code.google.com/archive/p/java-simple-serial-connector/wikis/jSSC_examples.wiki
package net.itinajero;

import jssc.SerialPort;
import jssc.SerialPortException;

public class WriteToPort {

   public static void main(String[] args) {
      //SerialPort serialPort = new SerialPort("COM1"); // Windows
      SerialPort serialPort = new SerialPort("/dev/ttyUSB0"); // Linux
      try {
         serialPort.openPort(); // Open serial port
         serialPort.setParams(SerialPort.BAUDRATE_9600,
                 SerialPort.DATABITS_8,
                 SerialPort.STOPBITS_1,
                 SerialPort.PARITY_NONE); // Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
         String text = "P";
         serialPort.writeBytes(text.getBytes()); // Write data to port
         serialPort.closePort(); // Close serial port
      } catch (SerialPortException ex) {
         System.out.println("Error SerialPortException: " + ex.getMessage());
      }
   }
   
}
