package net.itinajero.application.gui;

import javax.print.PrintException;
import net.itinajero.application.gui.dto.Producto;

public class Prueba {
   public static void main(String[] args) {
      try {
         Producto prod = new Producto(0);
         prod.setDescripcion("Carne");
         prod.setPrecio(80.5);
         Impresora printer = new Impresora(prod, "5KG","PDF");
         printer.printLabel();
      } catch (PrintException ex) {
         System.out.println("Error: " + ex.getMessage());
      }
   }
}
