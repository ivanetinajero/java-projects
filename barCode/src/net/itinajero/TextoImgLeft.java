package net.itinajero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class TextoImgLeft {

   private static BufferedImage ORIGINAL;
   //private static int extraPixels=50;
   private static int extraPixels=150;
   private static BufferedImage ALTERED;
   private static final GraphicsConfiguration config
           = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

   public static void main(String[] args) throws Exception {
      ORIGINAL = ImageIO.read(new File("/home/ivan/Downloads/test.png"));
      ALTERED = config.createCompatibleImage(
              ORIGINAL.getWidth() + extraPixels,
              ORIGINAL.getHeight() );
      Graphics2D g2 = ALTERED.createGraphics();
      g2.setColor(Color.WHITE);
      g2.fillRect(0, 0, extraPixels,ALTERED.getHeight());      
      g2.drawImage(ORIGINAL, extraPixels, 0, null);
      g2.setFont(new Font("Monospaced", Font.BOLD, 14));
      g2.setColor(Color.BLACK);
      g2.drawString("Chuleta ahumada", 15, 15);
      g2.drawString("15.52 kg", 15, 30);
      g2.drawString("$931", 15, 45);
      g2.dispose();

      // Save image
      ImageIO.write(ALTERED, "png", new File("/home/ivan/Downloads/alteredImage.png"));

   }
}
