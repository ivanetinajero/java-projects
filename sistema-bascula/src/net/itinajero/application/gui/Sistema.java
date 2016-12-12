package net.itinajero.application.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Sistema {

   private JFrame frame;
   private Font font;
   private JLabel producto; // Referencia al producto Seleccionado.
   private JScrollPane scrollPane;
   private JScrollBar bar;
   private JPanel center;
   private PanelWest west;
   private JPanel botones;

   public Sistema() {
      botones = new JPanel();
      producto = new JLabel("Seleccione Categoría");
      producto.setHorizontalAlignment(0);
      font = new Font("Courier New", 1, 40);
      producto.setFont(font);
      frame = new JFrame("Sistema");
      frame.setLayout(new BorderLayout());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      
      center = new JPanel();
      scrollPane = new JScrollPane(center);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      bar = scrollPane.getVerticalScrollBar();
      bar.setPreferredSize(new Dimension(30, 0)); // ancho del scroll    
      frame.add(scrollPane, BorderLayout.CENTER);
      
      west = new PanelWest(frame, center, producto);
      scrollPane = new JScrollPane(west);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      bar = scrollPane.getVerticalScrollBar();
      bar.setPreferredSize(new Dimension(30, 0)); // ancho del scroll 

      // Cambiamos el scroll para la izquierda.
      scrollPane.remove(bar);
      JPanel tmp = new JPanel(new BorderLayout()); // un Panel para acomodar los botones de categorias
      tmp.add(scrollPane, BorderLayout.CENTER);
      tmp.add(bar, BorderLayout.WEST);

      frame.add(tmp, BorderLayout.WEST); // Agregamos el panel de las categorias a la ventana
      frame.add(producto, BorderLayout.NORTH);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      Sistema sys = new Sistema();
   }
}
