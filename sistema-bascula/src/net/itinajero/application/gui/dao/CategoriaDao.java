package net.itinajero.application.gui.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import net.itinajero.application.gui.dto.Categoria;

public class CategoriaDao {

   private DbConnection conn;

   public CategoriaDao(DbConnection conn) {
      this.conn = conn;
   }
  
   public Categoria getById(int idCategoria) throws SQLException {
      String sql = "select * from Categoria where id = ?";

      PreparedStatement pst = conn.getConnection().prepareStatement(sql);
      pst.setInt(1, idCategoria); 
      ResultSet rs = pst.executeQuery();
      Categoria categoria = new Categoria(0);      
      while (rs.next()) {
         categoria.setId(idCategoria);
         categoria.setNombre(rs.getString("nombre"));
         categoria.setDescripcion(rs.getString("descripcion"));
         categoria.setImagen(rs.getString("imagen"));         
      }

      rs.close();
      pst.close();
      return categoria;
   }
   
   public List<Categoria> getAll() throws SQLException {
      String sql = "select * from Categoria order by nombre";

      PreparedStatement pst = conn.getConnection().prepareStatement(sql);   
      ResultSet rs = pst.executeQuery();
      Categoria categoria;    
      List<Categoria> lista = new LinkedList<>();
      while (rs.next()) {
         categoria = new Categoria(rs.getInt("id"));
         categoria.setNombre(rs.getString("nombre"));
         categoria.setDescripcion(rs.getString("descripcion"));
         categoria.setImagen(rs.getString("imagen"));
         lista.add(categoria);
      }

      rs.close();
      pst.close();
      return lista;
   }
}
