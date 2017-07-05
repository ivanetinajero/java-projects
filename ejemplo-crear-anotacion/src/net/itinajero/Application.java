package net.itinajero;

import java.lang.reflect.Field;

public class Application {

   public static void main(String[] args) throws Exception {
      // Creamos nuestro objecto y lo seteamos
      User user = new User();
      user.setId(1);
      user.setName("Gustavo");
      user.setPassword("pass");

      // recorremos todos los atributos de la clase
      for (Field field : user.getClass().getDeclaredFields()) {

         if (!field.isAnnotationPresent(StringValid.class)) {
            continue; // no tiene anotacion
         }

         // tomamos la anotacion de atributo
         StringValid stringValidAnnotation = field.getAnnotation(StringValid.class);

         // hago accesible el atributo privado
         field.setAccessible(true);

         // obtengo el valor del atributo
         String valueString = (String) field.get(user);

         System.out.print("Nombre del atributo: " + field.getName());
         System.out.print(" - contiene: " + valueString);
         System.out.print(" - minimo permitido: " + stringValidAnnotation.minLength());
         System.out.print(" - maximo permitido: " + stringValidAnnotation.maxLength());

         System.out.print(" - length: " + valueString.length());

         // valido comparando el lenght del valor seteado con el lenght permitido 
         if (valueString.length() < stringValidAnnotation.minLength()) {
            System.out.print(" - ES MENOR QUE EL MINIMO PERMITIDO " + stringValidAnnotation.minLength());
         }

         if (valueString.length() > stringValidAnnotation.maxLength()) {
            System.out.print(" - ES MAYOR QUE EL MAXIMO PERMITIDO " + stringValidAnnotation.maxLength());
         }

         System.out.println("");
         System.out.println("----------");

      }

   }
}
