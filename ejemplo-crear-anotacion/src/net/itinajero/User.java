package net.itinajero;

public class User {
   
   private int id;
   @StringValid(minLength = 2, maxLength = 5)
   private String name;
   @StringValid(minLength = 5,maxLength = 10)
   private String password;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String toString() {
      return "User{" + "id=" + id + ", name=" + name + ", password=" + password + '}';
   }   
   
}
