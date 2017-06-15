package net.itinajero.dao;

import java.sql.*;
import java.util.*;
import saaf.DbConnection;
import net.itinajero.dto.Clasificacion;

public class ClasificacionDao {

   private DbConnection conn;
   private Map<Integer, Clasificacion> map;
   private List<Clasificacion> lista;

   public ClasificacionDao(DbConnection conn) {
      this.conn = conn;
   }

   public List<Clasificacion> getChilds(int category_id) {

      try {
         String sql = "select CATEGORY_ID,PARENT,NAME from category";
         
         PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
         ResultSet rs = preparedStatement.executeQuery();
         map = new HashMap<>();
         while (rs.next()) {
            Clasificacion c = new Clasificacion(rs.getInt("CATEGORY_ID"));
            c.setPadre(rs.getInt("PARENT"));
            c.setNombre(rs.getString("NAME"));
            map.put(c.getId(), c);
         }
         lista = new LinkedList<>();
         
         // Agregamos a la lista el Padre seleccionado
         lista.add(map.get(category_id));
         
         // Metodo que agrega a la lista todos los hijos recursivamente
         findChilds(category_id);          
         return lista;
         
      } catch (SQLException ex) {
         System.out.println("Error: " + ex.getMessage());
         return null;
      }
   }
    
   /**
    * Metodo que busca todos los nodos hijos recursivamente
    * @param parent 
    */
   public void findChilds(int parent) { 
      // Recorremos todo el Mapa
      for (Integer key : map.keySet()) {
         // Si el nodo actual, tiene como padre el que viene como parametro (PARENT).
         // Significa que es hijo. Lo metemos a la lista
         if (map.get(key).getPadre() == parent) { 
            lista.add(map.get(key));                    
            //System.out.println("Child: " + map.get(key));
            int tmpKey = key;            
            // Recursivamente, ahora buscamos los hijos de este que fue hijo...
            findChilds(tmpKey);
         }
      }
   }
}
