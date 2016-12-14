package net.itinajero.application.gui.dto;

public class Producto {
   private int id;
   private String descripcion;
   private double precio;
   private String imagen;
   private Categoria categoria;

   public Producto(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public double getPrecio() {
      return precio;
   }

   public void setPrecio(double precio) {
      this.precio = precio;
   }

   public String getImagen() {
      return imagen;
   }

   public void setImagen(String imagen) {
      this.imagen = imagen;
   }

   public Categoria getCategoria() {
      return categoria;
   }

   public void setCategoria(Categoria categoria) {
      this.categoria = categoria;
   }

   public void copy(Producto copy){
      this.id = copy.getId();
      this.descripcion = copy.getDescripcion();
      this.precio = copy.getPrecio();
      this.imagen = copy.getImagen();
      this.categoria = copy.getCategoria();
   }
   
   @Override
   public String toString() {
      return "Producto{" + "id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + ", imagen=" + imagen + '}';
   }   
   
}
