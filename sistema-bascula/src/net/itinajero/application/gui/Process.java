package net.itinajero.application.gui;

import java.util.Random;
import javax.swing.JLabel;
import net.itinajero.application.gui.dto.Producto;

public class Process extends Thread {

   private volatile boolean running = true; // thread   
   private Producto seleccionado;
   private JLabel producto;

   public Process(Producto producto) {
      this.setName(String.valueOf(producto.getId()));
      this.seleccionado = producto;
   }

   @Override
   public void run() {
      // Solo para simular los pesos que nos da la bascula
      Random randomGenerator = new Random();
      System.out.println("Trabajando con producto: " + producto.getName());
      while (running) {
         
         try {
            
            int randomInt = randomGenerator.nextInt(100); // peso simulado

            producto.setText(seleccionado.getDescripcion() + " " + String.valueOf(randomInt)); // Actualizamos el display NombreProducto 4.5 KG            
            // en el toolTipText guardaremos lo que nos da la bascula. Ejemplo: 4.5 KG
            producto.setToolTipText(randomInt + " KG");
            
            if (producto.getName().equals("0")) {               
               System.out.println("Detected stop");
               running = false;
            }
            Thread.sleep(1000);
                        
         } catch (InterruptedException ex) {
            System.out.println("Error Process.run: " + ex.getMessage());
         }
      }
   }

   public boolean isRunning() {
      return running;
   }

   public void stopProcess() {
      running = false;
   }

   public void setProducto(JLabel producto) {
      this.producto = producto;
   }

}
