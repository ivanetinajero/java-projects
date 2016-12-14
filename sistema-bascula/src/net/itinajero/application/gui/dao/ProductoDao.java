package net.itinajero.application.gui.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import net.itinajero.application.gui.dto.Categoria;
import net.itinajero.application.gui.dto.Producto;

public class ProductoDao {
    private DbConnection conn;

   public ProductoDao(DbConnection conn) {
      this.conn = conn;
   }
  
   public Producto getById(int idProducto) throws SQLException {
      String sql = "select * from Producto where id = ?";

      PreparedStatement pst = conn.getConnection().prepareStatement(sql);
      pst.setInt(1, idProducto); 
      ResultSet rs = pst.executeQuery();
      Producto producto = new Producto(0);
      Categoria categoria;
      CategoriaDao daoCat = new CategoriaDao(conn);
      while (rs.next()) {
         producto.setId(idProducto);         
         producto.setDescripcion(rs.getString("descripcion"));
         producto.setPrecio(rs.getDouble("precio"));
         producto.setImagen(rs.getString("imagen")); 
         // Inyectamos la Categoria
         categoria = daoCat.getById(rs.getInt("idCategoria"));
         producto.setCategoria(categoria);
      }
      daoCat = null;
      rs.close();
      pst.close();
      return producto;
   }
   
   public List<Producto> getByCategoria(int idCategoria) throws SQLException {
      String sql = "select * from Producto where idCategoria = ? order by descripcion";

      PreparedStatement pst = conn.getConnection().prepareStatement(sql);
      pst.setInt(1, idCategoria); 
      ResultSet rs = pst.executeQuery();
      Producto producto = new Producto(0);
      Categoria categoria;
      List<Producto> lista = new LinkedList<>();
      CategoriaDao daoCat = new CategoriaDao(conn);
      while (rs.next()) {
         producto = new Producto(rs.getInt("id"));         
         producto.setDescripcion(rs.getString("descripcion"));
         producto.setPrecio(rs.getDouble("precio"));
         producto.setImagen(rs.getString("imagen")); 
         // Inyectamos la Categoria
         categoria = daoCat.getById(rs.getInt("idCategoria"));
         producto.setCategoria(categoria);
         lista.add(producto);
      }
      daoCat = null;
      rs.close();
      pst.close();
      return lista;
   }
}
