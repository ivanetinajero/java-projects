package net.itinajero;

public class Principal {
   
   static String barCodePath = "/home/ivan/Downloads/";

   public static void main(String[] args) throws Exception {
      
      // Los datos de la Etiqueta
      BarCodeData data = new BarCodeData(1, "0123456789123");
      data.addTextLine("Producto : XYZ");
      data.addTextLine("Precio: XYZ");
      data.addTextLine("Peso: XYZ");     
      
      BarCodeUtility barCodeUtil = new BarCodeUtility(data);
          
      // This will generate Bar-Code 128 format
      boolean status = barCodeUtil.createBarCode128();
      System.out.println("Etiqueta Generada: " +status);
           
   }
}
