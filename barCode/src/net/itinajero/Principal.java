package net.itinajero;

public class Principal {
   
   static String barCodePath = "/home/ivan/Downloads/";

   public static void main(String[] args) throws Exception {
      BarCodeUtility barCodeUtil = new BarCodeUtility();
    
      // This will generate Bar-Code 3 of 9 format
      barCodeUtil.createBarCode39("naeemgik - 12345");
    
      // This will generate Bar-Code 128 format
      barCodeUtil.createBarCode128("0123456789");
   }
}
