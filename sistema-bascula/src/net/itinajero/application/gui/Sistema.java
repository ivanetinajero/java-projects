package net.itinajero.application.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Sistema {

   private JFrame frame;
   private Font font;
   private JLabel producto; // Referencia al nombre del producto Seleccionado.
   private String nomProducto; // Referencia para tener el nombre del producto seleccionado
   private JScrollPane scroll;
   private JScrollBar bar;
   private JPanel panelProductos; // Panel para los botones del productos (CENTER)
   private PanelWest panelCategorias; // panel para los botones de las categorias (IZQ)
   private Process proceso;
   private JButton cmdPrint;

   public Sistema() {
      
      frame = new JFrame("Sistema");
      // Distribucion de los componentes (NORTH, SOUTH, EAST, WEST, and CENTER)
      frame.setLayout(new BorderLayout());      
      
      initPanelNorth(); // Construimos el panel de detalles del Producto
      
      /* Inicio Panel Productos */
      panelProductos = new JPanel();
      scroll = new JScrollPane(panelProductos);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      bar = scroll.getVerticalScrollBar();
      bar.setPreferredSize(new Dimension(30, 0)); // ancho del scroll    
      frame.add(scroll, BorderLayout.CENTER);
      /* Fin Panel Productos */
      
      /* Inicio Panel Categorias */
      panelCategorias = new PanelWest(frame, panelProductos, producto, proceso);
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
      /* Fin Panel Categorias */
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      frame.setVisible(true);
   }

   void initPanelNorth(){
      // Configuracion etiqueta el producto (Peso)
      producto = new JLabel("Seleccione Categoría");
      producto.setHorizontalAlignment(0);
      font = new Font("Courier New", 1, 40);
      producto.setFont(font);
      
      // Configuracion boton de imprimir
      cmdPrint = new JButton("Imprimir");
      cmdPrint.setPreferredSize(new Dimension(30,40));
      
      cmdPrint.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {            
            String display = producto.getText(); // Retenemos el ultimo display en pantalla
            String peso = producto.getToolTipText(); // retenemos el ultimo peso generado por la bascula
            int idProducto = Integer.parseInt(producto.getName()); // retenemos el idProducto
            producto.setName("0"); // Esta es la señal para detener el proceso            
            System.out.println("idProducto: " + idProducto);
            System.out.println("Peso Bascula: " + peso);
            System.out.println("Display: " + display);
            producto.setText(display);
            //JOptionPane.showMessageDialog(null, "Imprimiendo: Producto: ");
         }
      });
      
      JPanel panel = new JPanel(); // Panel para organizar los componentes
      panel.setLayout(new GridLayout(1, 4));
      panel.add(producto);
      panel.add(cmdPrint);
      frame.add(panel,BorderLayout.NORTH);          
      
   }
   
   public static void main(String[] args) {
      Sistema sys = new Sistema();
   }
}
