package net.itinajero;

public class Usuario {
   private Integer id;
   private String name;
   private String email;

   public Usuario() {
      System.out.println("Constructor sin parametros");
   }

   public Usuario(Integer id) {
      System.out.println("Constructor 1 parametro");
      this.id = id;
   }

   public Usuario(Integer id, String name, String email) {
      System.out.println("Constructor 3 parametros");
      this.id = id;
      this.name = name;
      this.email = email;
   }

   public int getId() {
      System.out.println("getId called Method");
      return id;
   }

   public void setId(Integer id) {
      System.out.println("setId called Method");
      this.id = id;
   }

   public String getName() {
      System.out.println("getName called Method");
      return name;
   }

   public void setName(String name) {
      System.out.println("setName called Method");
      this.name = name;
   }

   public String getEmail() {
      System.out.println("getEmail called Method");
      return email;
   }

   public void setEmail(String email) {
      System.out.println("setEmail called Method");
      this.email = email;
   }

   @Override
   public String toString() {
      return "Usuario{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
   }
           
}
