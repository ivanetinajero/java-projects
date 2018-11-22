package net.itinajero.model;

import java.sql.SQLException;
import java.util.List;

public class Principal {

   public static void main(String[] args) throws SQLException {
      DbConnection cn = new DbConnection();
      CategoriaService dao = new CategoriaService(cn);
      List<Category> list = dao.getAll();
      System.out.println("Todas: " + list);
      List<Category> resultado = dao.getChilds(new Category(7), list);
      System.out.println("Resultado: " + resultado);
      cn.desconectar();
   }
}
