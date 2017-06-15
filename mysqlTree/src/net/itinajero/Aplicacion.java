package net.itinajero;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import net.itinajero.dao.ClasificacionDao;
import net.itinajero.dto.Clasificacion;
import saaf.DbConnection;

public class Aplicacion {

   private static List<Clasificacion> lista;   
   
   public static void main(String[] args) {
      DbConnection cn = new DbConnection();
      ClasificacionDao dao = new ClasificacionDao(cn);
      lista = dao.getChilds(6); // PORTABLE ELECTRONICS
      System.out.println("Result: " + lista);
      cn.desconectar();
   }
}
