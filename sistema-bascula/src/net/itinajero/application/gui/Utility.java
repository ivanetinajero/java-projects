package net.itinajero.application.gui;

public class Utility {

   private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789._#@!¡=%&";

   /**
    * Metodo para generar una cadena de caracteres aleatorios con la longitud
    * pasada como parametro.
    *
    * @param count
    * @return
    */
   public static String randomAlphaNumeric(int count) {
      StringBuilder builder = new StringBuilder();

      while (count-- != 0) {
         int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
         builder.append(ALPHA_NUMERIC_STRING.charAt(character));
      }
      
      return builder.toString();
   }
}
