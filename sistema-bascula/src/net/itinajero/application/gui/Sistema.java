package net.itinajero.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTextField;
import net.itinajero.application.gui.dto.Producto;
import net.itinajero.application.gui.util.ThreadUtil;

public class Sistema {

   private JFrame frame;   
   private JLabel productoDisplay; // Referencia al nombre del producto Seleccionado.
   private String nomProducto; // Referencia para tener el nombre del producto seleccionado
   private JScrollPane scroll;
   private JScrollBar bar;
   private JPanel panelProductos; // Panel para los botones del productos (CENTER)
   private PanelWest panelCategorias; // panel para los botones de las categorias (IZQ)      
   // Cada de texto para buscar
   private JTextField txtBuscar;
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
   
   /**
    * Metodo que construye el panel NORTH
    */
   private void initPanelNorth(){
      Font font = new Font("Courier New", 1, 35);
      // Configuracion etiqueta el producto (Peso)
      productoDisplay = new JLabel("Seleccione Categoría");
      productoDisplay.setHorizontalAlignment(0);      
      productoDisplay.setFont(font);
      
      // Panel para organizar los componentes de la parte NORTH
      JPanel panel = new JPanel(); 
      panel.setLayout(new GridLayout(0, 3));      
      // Creamos el boton de imprimir
      JButton imprimir = buttonPrint();            
      // Creamos el boton de Buscar
      JButton buscar = buttonSearch();
      
      // Agregamos en este orden los objetos al panel NORTH      
      panel.add(imprimir);
      panel.add(buscar); 
      txtBuscar = new JTextField();
      txtBuscar.setFont(font);
      panel.add(txtBuscar); 
      panel.add(productoDisplay); // 1.      
      frame.add(panel,BorderLayout.NORTH);          
      
   }
   
   /**
    * Metodo para crear el boton de imprimir
    * @return 
    */
   private JButton buttonPrint(){   
      JButton print;
      // Configuracion boton de imprimir
      print = new JButton("Imprimir");
      print.setPreferredSize(new Dimension(30,40));
      
      // Evento Click del boton Imprimir
      print.addActionListener(new ActionListener() {
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
      return print;
   }
   
   /**
    * Metodo para crear el boton de Buscar
    * @return 
    */
   private JButton buttonSearch(){     
      // Configuracion boton de buscar
      JButton search = new JButton("Buscar");
      search.setPreferredSize(new Dimension(30,40));
      
      // Evento Click del boton Buscar
      search.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // Arreglo con todos los componentes (JButton) del panel CENTER
            Component[] botones = panelProductos.getComponents();
            // Solo permitimos buscar si hay texto hay botones
            String busqueda = txtBuscar.getText().toLowerCase();
            if (busqueda.length()>0 && botones.length>0){                           
               for (Component comp : botones) {
                  JButton tmpbtn = (JButton) comp;
                  if (tmpbtn.getText().toLowerCase().contains(busqueda)){
                     System.out.println("Encontrado Huevo");
                     tmpbtn.setBackground(Color.lightGray);
                  } else{
                     //tmpbtn.setVisible(false);
                  }  
               }
            }
            else
              JOptionPane.showMessageDialog(null, "Búsqueda incorrecta."); 
         }
      });
      return search;
   }
   
   public static void main(String[] args) {
      Sistema sys = new Sistema();
   }
}
