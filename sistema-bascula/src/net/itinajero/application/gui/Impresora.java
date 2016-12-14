package net.itinajero.application.gui;

import net.itinajero.application.gui.dto.Producto;

public class Impresora {
   private Producto producto;
   private String peso;

   public Impresora(Producto producto, String peso) {
      this.producto = producto;
      this.peso = peso;     
   }
      
   public boolean printLabel(){
      System.out.println("Printing " + peso + " of " + producto.getDescripcion());
      return true;
   }
}
