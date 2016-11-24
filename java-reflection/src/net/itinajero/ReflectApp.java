package net.itinajero;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectApp {
   public static void main(String[] args) {
      try {
         Class cls = Usuario.class;
         
         // Constructor sin parametros
         Usuario user1 = (Usuario) cls.newInstance();
         System.out.println("User1 (constructor sin parametros): " + user1);
         
         // Llamar un metodo SET en tiempo de ejecucion
         Class[] paramString = new Class[1]; // Lo utilizamos para representar en un metodo dinamico que recibe un parametro de tipo String
         paramString[0] = String.class;         
         String set = "setName";
         
         // La clase Method representa un metodo dinamico del objeto
         Method method = cls.getDeclaredMethod(set, paramString);
         method.invoke(user1, "ivanetinajero");
         System.out.println("User1 despues setName: "+ user1);
         
         // Llamar un metodo GET en tiempo de ejecucion
         Class[] noParams= {}; // Lo utilizamos para representar un metodo dinamico sin parametros. Por ejemplo un getXYZ()
         paramString[0] = String.class;         
         String get = "getName";
         method = cls.getDeclaredMethod(get, noParams);
         // Equivatente a user1.getName()
         // method.invoke(user1, null) nos regresa un Object
         System.out.println("Getting name from user1: " + method.invoke(user1, null)); 
         
         // Vamos a comprobar de que tipo el Object del metodo dinamico
         // Deberia ser de String debido a la firma del metodo: <  public String getName() > 
         System.out.println("Tipo regresado: " +method.invoke(user1, null).getClass());
             
         // constructor 1 parametro
         Constructor constructor1Param =  cls.getConstructor(new Class[] { Integer.class});         
         Usuario user2 = (Usuario) constructor1Param.newInstance(new Object[] { 110 });
         System.out.println("User2 (constructor 1 parametro): " + user2);
         
         // constructor 3 parametros
         Constructor constructor3Param =  cls.getConstructor(new Class[] { Integer.class, String.class, String.class});         
         Usuario user3 = (Usuario) constructor3Param.newInstance(new Object[] { 102, "ivan", "ivanetinajero@gmail.com" });
         System.out.println("User3 (constructor 3 parametros): " + user3);
         

      } catch (InstantiationException ex) {
         System.out.println("Error: " + ex.getMessage());
      } catch (IllegalAccessException ex) {
         System.out.println("Error: " + ex.getMessage());
      } catch (SecurityException ex) {
         System.out.println("Error: " + ex.getMessage());
      } catch (NoSuchMethodException ex) {
         System.out.println("Error: " + ex.getMessage());
      } catch (IllegalArgumentException ex) {
         System.out.println("Error: " + ex.getMessage());
      } catch (InvocationTargetException ex) {
         System.out.println("Error: " + ex.getMessage());
      }
   }
}
