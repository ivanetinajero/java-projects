package net.itinajero.application.gui;

import javax.swing.JLabel;

public class Process extends Thread { 

   private volatile boolean running = true; // thread   
   private int counter = 0;
   private String peso="";
   private JLabel producto;

   @Override
   public void run() {
      while (running) {
         counter++;
         try {           
            peso = "Loop "+ counter + ": " + Utility.randomAlphaNumeric(4);            
            producto.setText(peso);
            System.out.println(peso);
            Thread.sleep(1000);
         } catch (InterruptedException ex) {
            System.out.println("Error Process.run: " + ex.getMessage());
         }
         if (!running) {
            System.out.println("Detected stop");
         }
      }
   }

   public boolean isRunning() {
      return running;
   }

   public void setRunning(boolean running) {
      this.running = running;
   }
   
   public void stopProcess() {
      running = false;
   }

   public String getPeso() {
      return peso;
   }

   public void setProducto(JLabel producto) {
      this.producto = producto;
   }
         
}
