package net.itinajero;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class BarCodeXML {
    public static void main(String[] args) {
      try {
         DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
         //Configuration cfg = builder.buildFromFile(new File("/home/ivan/xmlBarCode/code39.xml"));
         Configuration cfg = builder.buildFromFile(new File("/home/ivan/xmlBarCode/code128.xml"));
         BarcodeGenerator gen = BarcodeUtil.getInstance().createBarcodeGenerator(cfg);   
         OutputStream out = new java.io.FileOutputStream(new File("/home/ivan/Downloads/output.png"));
         BitmapCanvasProvider provider = new BitmapCanvasProvider(
                 out, "image/x-png", 300, BufferedImage.TYPE_BYTE_GRAY, true, 0);
         
         gen.generateBarcode(provider, "0123456789");
         provider.finish();

      } catch (Exception ex) {
         System.out.println("Error: " + ex.getMessage());
      } 
   }
}
