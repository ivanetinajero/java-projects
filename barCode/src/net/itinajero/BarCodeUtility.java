// https://javaavabodhaka.wordpress.com/2014/10/03/barcode4j-hello-word-exampleusing-javabean-api/
package net.itinajero;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class BarCodeUtility {

   private static final String BARCODEPATH = "/home/ivan/Downloads/";   
   private static final int EXTRAPIXELS = 50;
   private static final GraphicsConfiguration CONFIG
           = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
   
   private BufferedImage ALTERED;
   private BarCodeData barCode;
   
   public BarCodeUtility(BarCodeData barCode) {
      this.barCode = barCode;
   }
   
   public boolean createBarCode128() {
      try {
         Code128Bean bean = new Code128Bean();
         final int dpi = 160;

         //Configure the barcode generator
         bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
         bean.doQuietZone(false);

         //Open output file
         File outputFile = new File(BARCODEPATH + barCode.getFileName() + ".png");
         FileOutputStream out = new FileOutputStream(outputFile);
         BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                 out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

         //Generate the barcode
         bean.generateBarcode(canvas, barCode.getNumber());

         // After the barcode was generated, add some additional text
         addTextBarCode(canvas.getBufferedImage());

         //Signal end of generation
         canvas.finish();
         return true;
      } catch (IOException ex) {
         System.out.println("Error: " + ex.getMessage());
         return false;
      }
   }

   public String addTextBarCode(BufferedImage imageBabcode) {

      try {
         ALTERED = CONFIG.createCompatibleImage(imageBabcode.getWidth(),
                 imageBabcode.getHeight() + EXTRAPIXELS);
         Graphics2D g2 = ALTERED.createGraphics();
         g2.setColor(Color.WHITE);
         g2.fillRect(0, 0, ALTERED.getWidth(), EXTRAPIXELS);
         g2.drawImage(imageBabcode, 0, EXTRAPIXELS, null);
         // Monospaced font - fixed-width (same amount of horizontal space)
         Font font = new Font("Monospaced", Font.BOLD, 14);
         g2.setFont(font);
         g2.setColor(Color.BLACK);
         
         int n = 15; // Pixel inicial de la primera linea de texto (posicion Y)                 
         for (String tmpText: barCode.getText()){
            g2.drawString(tmpText, 5, n);
            n+=15; // La siguiente linea se imprime 15 pixeles mas abajo.
         }           
         g2.dispose();
         String fileNamePNG = BARCODEPATH + barCode.getFileName() + "-final.png";
         // Save new barCode with Text
         ImageIO.write(ALTERED, "png", new File(fileNamePNG));
         return fileNamePNG;
      } catch (IOException ex) {
         System.out.println("Error: " + ex.getMessage());
         return null;
      }
   }

}
