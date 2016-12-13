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
   private JScrollPane scroll;
   private JScrollBar bar;
   private JPanel panelProductos; 
   private PanelWest panelCategorias;
   private Process proceso;

   public Sistema() {
      producto = new JLabel("Seleccione Categoría");
      producto.setHorizontalAlignment(0);
      font = new Font("Courier New", 1, 40);
      producto.setFont(font);
      frame = new JFrame("Sistema");
      frame.setLayout(new BorderLayout());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      
      panelProductos = new JPanel();
      scroll = new JScrollPane(panelProductos);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      bar = scroll.getVerticalScrollBar();
      bar.setPreferredSize(new Dimension(30, 0)); // ancho del scroll    
      frame.add(scroll, BorderLayout.CENTER);
      
      panelCategorias = new PanelWest(frame, panelProductos, producto,proceso);
      scroll = new JScrollPane(panelCategorias);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      bar = scroll.getVerticalScrollBar();
      bar.setPreferredSize(new Dimension(30, 0)); // ancho del scroll 

      // Cambiamos el scroll para la izquierda.
      scroll.remove(bar);
      JPanel tmp = new JPanel(new BorderLayout()); // un Panel para acomodar los botones de categorias
      tmp.add(scroll, BorderLayout.CENTER);
      tmp.add(bar, BorderLayout.WEST);

      frame.add(tmp, BorderLayout.WEST); // Agregamos el panel de las categorias a la ventana
      frame.add(producto, BorderLayout.NORTH);
      frame.setVisible(true);
            
   }

   public static void main(String[] args) {
      Sistema sys = new Sistema();
   }
}
