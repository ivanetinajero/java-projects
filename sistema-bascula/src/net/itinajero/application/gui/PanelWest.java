package net.itinajero.application.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.itinajero.application.gui.dao.CategoriaDao;
import net.itinajero.application.gui.dao.DbConnection;
import net.itinajero.application.gui.dao.ProductoDao;
import net.itinajero.application.gui.dto.Categoria;
import net.itinajero.application.gui.dto.Producto;
import net.itinajero.application.gui.util.ThreadUtil;

public class PanelWest extends JPanel {

   private JFrame frame;
   private JPanel panelProductos;
   private JLabel productoDisplay;
   private Process proceso;
   private Producto prodThread;

   public PanelWest(JFrame frame, JPanel panelProductos, 
           JLabel productoDisplay,Producto prodThread) {   
      this.frame = frame;
      this.panelProductos = panelProductos;
      this.productoDisplay = productoDisplay;      
      this.prodThread=prodThread;
      
      updateWest(); // Metodo para dibujar los botones de las Categorias
   }

   /**
    * Metodo para dibujar los botones de las categorias
    */
   private void updateWest() {
      GridLayout layout = new GridLayout(0, 1);
      layout.setHgap(10);
      layout.setVgap(10); // Espaciado entre componentes
      setLayout(layout);
      //setPreferredSize(new Dimension(130, 130));

      // Vamos por las categorias a la BD
      DbConnection conn = new DbConnection();
      CategoriaDao daoCat = new CategoriaDao(conn);
      List<Categoria> listaCat = null;
      try {
         listaCat = daoCat.getAll();
      } catch (SQLException ex) {
         System.out.println("Error");
      } finally {
         conn.disconnect();
      }

      // Dibujamos un boton por cada categoria
      for (Categoria tmpcat : listaCat) {
         ImageIcon icon = new ImageIcon("/home/ivan/tmp-img/watermelon.png1");
         String buttonTitle = tmpcat.getNombre();        
         JButton button = new JButton(buttonTitle, icon); // text of the button
         button.setHorizontalTextPosition(JButton.CENTER);
         button.setVerticalTextPosition(JButton.BOTTOM);
         // button.setPreferredSize(new Dimension(90, 90));

         // Inicio Evento Click de cada boton de Categoria
         button.addActionListener((java.awt.event.ActionEvent e) -> {
            
            // Cada que se presiona un boton de Categoria revisaremos si hay un thread en ejecucion 
            // actualmente. Si existe uno, lo detenemos.
            if (prodThread.getId()!=0) 
               ThreadUtil.stopThreadByName(String.valueOf(prodThread.getId()));
            
            JButton b1 = (JButton) e.getSource(); // Referencia del boton Categoria seleccionado
            // Cada boton de Categoria enviara al metodo updateCenter su idCategoria 
            // para saber cuantos botones de productos dibujar la ser presionado
            int idCategoria = tmpcat.getId(); // idCategoria
            panelProductos.removeAll();
            updateCenter(idCategoria, panelProductos);
            // Despues de que se genero el panelProductos (CENTER) es necesario refrescar la ventana
            frame.revalidate();
            frame.repaint();
         });
         // Fin Evento Click de cada boton de Categoria

         add(button);
      }
   }

   /**
    * Metodo para dibujar los botones de productos para una determinada
    * categoria
    *
    * @param idCategoria
    * @param panelProductos
    */
   private void updateCenter(int idCategoria, JPanel panelProductos) {

      // Vamos por los productos de esta idCategoria      
      DbConnection conn = new DbConnection();
      ProductoDao daoProd = new ProductoDao(conn);
      List<Producto> listaProd = null;
      try {
         listaProd = daoProd.getByCategoria(idCategoria);
      } catch (SQLException ex) {
         System.out.println("Error");
      } finally {
         conn.disconnect();
      }

      int cols = 5;
      int rows = 0;
      // Si son pocos productos, los mostramos en una sola columna (hacia abajo)
      if (listaProd.size() <= 4) {
         cols = 1;
      }
      GridLayout layout = new GridLayout(rows, cols);
      layout.setHgap(10);
      layout.setVgap(10); // espaciando entre componentes    
      panelProductos.setLayout(layout);

      // Por cada producto, vamos a pintar un boton
      for (Producto tmpprod : listaProd) {
         ImageIcon icon = new ImageIcon("/home/ivan/tmp-img/apple.png1");
         String buttonTitle = tmpprod.getDescripcion();
         //String buttonId = "idProducto" + i;
         JButton button = new JButton(buttonTitle, icon); // text of the button   
         button.setHorizontalTextPosition(JButton.CENTER);
         button.setVerticalTextPosition(JButton.BOTTOM);
         button.setName(String.valueOf(tmpprod.getId())); // el nombre del boton es el idProducto  
         button.setPreferredSize(new Dimension(100, 100));

         // Inicio Evento Click de cada boton de Producto
         button.addActionListener((java.awt.event.ActionEvent e) -> {            
            int numThreads = ThreadUtil.getRunningThreads();
            System.out.println("Threads running: " + numThreads);
            JButton b1 = (JButton) e.getSource(); // Referencia del boton seleccionado            
            productoDisplay.setText(b1.getText());
            productoDisplay.setName(button.getName());
            if (prodThread.getId()==0) {                
               // Le pasamos al Thread la informacion del producto
               proceso = new Process(tmpprod);
               // Le mandamos al thread una referencia del JLabel del producto
               // para que siempre nos actualice el peso
               proceso.setProducto(productoDisplay);
               proceso.start(); // Iniciamos el thread
               // La variable prodThread es como una variable global la cual tiene Producto con 
               // el cual el thread esta trabajando actualmente.               
               prodThread.copy(tmpprod);               
            } else {
               
               // Detenemos el que esta en ejecucion.
               ThreadUtil.stopThreadByName(String.valueOf(prodThread.getId()));               
               
               // Volvemos a crear un nuevo Thread (nuevo producto seleccionado)
               proceso = new Process(tmpprod);
               proceso.setProducto(productoDisplay);
               proceso.start(); // Iniciamos el thread                 
               // La variable prodThread ahora tiene el Producto seleccionado 
               prodThread.copy(tmpprod);                
            }           
            
         });
         // Fin Evento Click de cada boton de Producto

         panelProductos.add(button);
      }
   }

}
