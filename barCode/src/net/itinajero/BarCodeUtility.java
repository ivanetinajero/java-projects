// https://javaavabodhaka.wordpress.com/2014/10/03/barcode4j-hello-word-exampleusing-javabean-api/
package net.itinajero;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class BarCodeUtility {

   private String barCodePath = "/home/ivan/Downloads/";

   public boolean createBarCode39(String fileName) {

      try {
         Code39Bean bean39 = new Code39Bean();
         final int dpi = 160;

         //Configure the barcode generator
         bean39.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
         bean39.doQuietZone(false);

         //Open output file
         File outputFile = new File(barCodePath + fileName + ".png");
         FileOutputStream out = new FileOutputStream(outputFile);

         //Set up the canvas provider for monochrome PNG output
         BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                 out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

         //Generate the barcode
         bean39.generateBarcode(canvas, fileName);

         //Signal end of generation
         canvas.finish();
         System.out.println("Bar Code is generated successfully…");
         return true;         
      } catch (IOException ex) {
         System.out.println("Error: " + ex.getMessage());
         return false;
      }
   }
   
   public boolean createBarCode128(String fileName) {
    try {
      Code128Bean bean = new Code128Bean();
      final int dpi = 160;

      //Configure the barcode generator
      bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
      bean.doQuietZone(false);

      //Open output file
      File outputFile = new File(barCodePath + fileName + ".png");
      FileOutputStream out = new FileOutputStream(outputFile);    
      BitmapCanvasProvider canvas = new BitmapCanvasProvider(
          out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

      //Generate the barcode
      bean.generateBarcode(canvas, fileName);
   
      //Signal end of generation
      canvas.finish();    
      System.out.println("Bar Code is generated successfully…");
      return true;
    }
    catch (IOException ex) {
      System.out.println("Error: " + ex.getMessage());
      return false;
    }
  }
}
