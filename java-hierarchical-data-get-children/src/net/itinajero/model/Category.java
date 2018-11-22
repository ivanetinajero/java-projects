package net.itinajero.model;

public class Category {

   private int id;
   private String name;
   private int parentId;

   public Category(int id) {
      this.id = id;
   }

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

   public int getParentId() {
      return parentId;
   }

   public void setParentId(int parentId) {
      this.parentId = parentId;
   }

   @Override
   public String toString() {
      return "Category{" + "id=" + id + ", name=" + name + ", parentId=" + parentId + '}';
   }

}
