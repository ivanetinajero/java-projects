package net.itinajero.model;
import java.sql.*;
/**
* Clase que permite conectar con la base de datos
*/
public class DbConnection 
{
   // Constantes globales de la clase
   /* Se declaran constantes, porque van a ser siempre los mismos valores
      cada vez que se vaya a crear un objeto de tipo DbConnection
   */
   static String bd = "test";
   static String login = "root";
   static String password = "admin";
   static String url = "jdbc:mysql://localhost/" + bd +"?noAccessToProcedureBodies=true";
   // Esta variable va a guardar la conexion
   Connection conn = null;
   /** Constructor de DbConnection 
    *  Se llama constructor porque tiene el mismo nombre que la clase y cuando se crea un nuevo objeto de esta clase
    *  es como se va a inicializar al crear un nuevo objeto de este tipo   
    * */
   public DbConnection() 
   {
      try
      {
         //obtenemos el driver para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexion
         conn = DriverManager.getConnection(url,login,password);
         // Si conn no es nulo, significa que si se pudo conectar
         if (conn!=null)
         {
            //System.out.println("Coneccion a base de datos " + bd + " OK");
         }
      }
         catch(SQLException e) // Excepcion ocurrida por la conexion 
         {
            System.out.println("Excepcion conexion.");
            System.out.println(e);
         }
         catch(ClassNotFoundException e) // Excepcion ocurrida por no encontrar el driver
         {
	         System.out.println("Excepcion driver.");
	         System.out.println(e);
         }
   }
   /**Permite retornar la conexion*/
   public Connection getConnection()
   {
      return conn;
   }
   // Quitamos de memoria la conexion
   public void desconectar()
   {
       if (conn != null) 
       {  
           try 
           {  
                //System.out.println("Desconectado de " + bd + " OK");
               conn.close();  
           } 
           catch (SQLException e) 
           {  
               System.out.println(e);  
           }  
       }  
   }
}
