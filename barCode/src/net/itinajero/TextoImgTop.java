package net.itinajero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class TextoImgTop {

   private static BufferedImage ORIGINAL;
   private static int extraPixels=50;
   private static BufferedImage ALTERED;
   private static final GraphicsConfiguration config
           = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

   public static void main(String[] args) throws Exception {
      ORIGINAL = ImageIO.read(new File("/home/ivan/Downloads/test.png"));
      ALTERED = config.createCompatibleImage(
              ORIGINAL.getWidth(),
              ORIGINAL.getHeight() + extraPixels);
      Graphics2D g2 = ALTERED.createGraphics();
      g2.setColor(Color.WHITE);
      g2.fillRect(0, 0, ALTERED.getWidth(), extraPixels);
      g2.drawImage(ORIGINAL, 0, extraPixels, null);
      g2.setFont(new Font("Monospaced", Font.BOLD, 12));
      g2.setColor(Color.BLACK);
      g2.drawString("Producto: chuleta ahumada", 5, 15);
      g2.drawString("Peso: 15.52 kg", 5, 30);
      g2.drawString("Precio:60 , Total: $931.", 5, 45);
      g2.dispose();

      // Save image
      ImageIO.write(ALTERED, "png", new File("/home/ivan/Downloads/alteredImage.png"));

   }
}
