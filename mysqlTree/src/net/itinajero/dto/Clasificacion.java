package net.itinajero.dto;

public class Clasificacion {

   private int id;
   private int padre;
   private String nombre;

   public Clasificacion(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getPadre() {
      return padre;
   }

   public void setPadre(int padre) {
      this.padre = padre;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   @Override
   public String toString() {
      return "Clasificacion{" + "id=" + id + ", padre=" + padre + ", nombre=" + nombre + '}';
   }

}
