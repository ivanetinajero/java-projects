//http://mikehillyer.com/articles/managing-hierarchical-data-in-mysql/
package net.itinajero.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoriaService {

   private final DbConnection conn;

   public CategoriaService(DbConnection conn) {
      this.conn = conn;
   }

   public List<Category> getAll() {
      try {
         String sql = "select id, name, parent_id from category";
         PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
         ResultSet rs = preparedStatement.executeQuery();
         List<Category> allCat = new LinkedList<>();
         while (rs.next()) {
            Category c = new Category(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setParentId(rs.getInt("parent_id"));
            allCat.add(c);
         }
         return allCat;
      } catch (SQLException ex) {
         System.out.println("Error: " + ex.getMessage());
         return null;
      }
   }

   /**
    * 
    * @param categorySearch
    * @param categories
    * @return 
    */
   public List<Category> getChilds(Category categorySearch, List<Category> categories) {
      try {
         List<Category> children = new LinkedList<>();
         // Metodo que agrega a la lista todos los hijos recursivamente
         findChilds(categorySearch.getId(), categories, children);
         // Al final de los hijos encontrados recursivamente, tambien agregamos el nodo buscado.
         children.add(categorySearch);
         return children;
      } catch (Exception ex) {
         System.out.println("Error: " + ex.getMessage());
         return null;
      }
   }

  /**
   * Metodo que busca todos los nodos hijos recursivamente
   * @param parent
   * @param categories
   * @param children 
   */
   private void findChilds(int parent, List<Category> categories, List<Category> children) {
      // Recorremos todo la lista completa
      for (Category tmpCat : categories) {
         // Si el nodo actual, tiene como padre el que viene como parametro (parent).
         // Significa que es hijo. Lo metemos a la lista
         if (tmpCat.getParentId() == parent) {
            children.add(tmpCat);
            int tmpKey = tmpCat.getId();
            // Recursivamente, ahora buscamos los hijos de este que fue hijo...
            findChilds(tmpKey, categories, children);
         }
      }
   }
}
