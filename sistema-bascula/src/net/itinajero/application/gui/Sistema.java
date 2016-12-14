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
import net.itinajero.application.gui.dto.Producto;
import net.itinajero.application.gui.util.ThreadUtil;

public class Sistema {

   private JFrame frame;
   private Font font;
   private JLabel productoDisplay; // Referencia al nombre del producto Seleccionado.
   private String nomProducto; // Referencia para tener el nombre del producto seleccionado
   private JScrollPane scroll;
   private JScrollBar bar;
   private JPanel panelProductos; // Panel para los botones del productos (CENTER)
   private PanelWest panelCategorias; // panel para los botones de las categorias (IZQ)   
   private JButton cmdPrint;
   // Este objeto de tipo Producto siempre tendra una referencia del producto seleccionado
   // con un boton y que, por lo tanto un Thread lo esta procesando.
   private Producto prodThread=new Producto(0);

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
      panelCategorias = new PanelWest(frame, panelProductos, productoDisplay, prodThread);
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
      productoDisplay = new JLabel("Seleccione Categoría");
      productoDisplay.setHorizontalAlignment(0);
      font = new Font("Courier New", 1, 40);
      productoDisplay.setFont(font);
      
      // Configuracion boton de imprimir
      cmdPrint = new JButton("Imprimir");
      cmdPrint.setPreferredSize(new Dimension(30,40));
      
      // Evento Click del boton Imprimir
      cmdPrint.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {             
            /* Cada que se presiona un boton de imprimir revisaremos si hay un thread en ejecucion. 
               Si existe uno, es señal que hay un thread trabajando mandando el peso.               
            */
            if (prodThread.getId()!=0){ 
               // al imprimir, primero detenemos el thread
               ThreadUtil.stopThreadByName(String.valueOf(prodThread.getId()));
               
               // Retenemos el ultimo display en pantalla
               String display = productoDisplay.getText(); 
               // retenemos el ultimo peso generado por la bascula
               String peso = productoDisplay.getToolTipText(); 
               productoDisplay.setText(display);
               Impresora printer = new Impresora(prodThread, peso); 
               printer.printLabel();
               JOptionPane.showMessageDialog(null, "Imprimiendo " + peso + " de " +prodThread.getDescripcion());
            }else{
               JOptionPane.showMessageDialog(null, "Seleccione un producto.");
            }   
         }
      });
      
      JPanel panel = new JPanel(); // Panel para organizar los componentes
      panel.setLayout(new GridLayout(1, 4));
      panel.add(productoDisplay);
      panel.add(cmdPrint);
      frame.add(panel,BorderLayout.NORTH);          
      
   }
   
   public static void main(String[] args) {
      Sistema sys = new Sistema();
   }
}
