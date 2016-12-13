package net.itinajero.application.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelWest extends JPanel {
     
   private JFrame frame;
   private JPanel panelProductos;
   private JLabel producto;
   private Process proceso;
   
   public PanelWest(JFrame frame, JPanel panelProductos, JLabel producto,Process proceso) {
      System.out.println("Constructor PanelWest");  
      this.frame = frame;
      this.panelProductos = panelProductos;
      this.producto = producto;
      this.proceso=proceso;
      GridLayout layout = new GridLayout(0, 1);
      layout.setHgap(3); layout.setVgap(3); // Espaciado entre componentes     
      setLayout(layout);
      //setPreferredSize(new Dimension(130, 130));
      for (int i = 0; i < 100; ++i) {
         ImageIcon icon = new ImageIcon("/home/ivan/tmp-img/watermelon.png");
         String buttonTitle = "Title " + i;
         String buttonId = "idCategoria" + i;
         JButton button = new JButton(buttonTitle,icon); // text of the button   
         button.setHorizontalTextPosition(JButton.CENTER);
         button.setVerticalTextPosition(JButton.BOTTOM);
         button.setName(buttonId); // Name of the button  
         // button.setPreferredSize(new Dimension(90, 90));
         button.addActionListener((java.awt.event.ActionEvent e) -> {
         JButton b1 = (JButton) e.getSource(); // Referencia del boton seleccionado         
         int idCategoria = Integer.parseInt(b1.getName().substring(11));
         panelProductos.removeAll();
         updateCenter(idCategoria,panelProductos);            
         frame.revalidate();
         frame.repaint();                        

         });
         add(button);
      }
   }
   
   
   public void updateCenter(int idCategoria,JPanel panelProductos) {
            
      // DAO productos por idCategoria
      int cols=5;
      int rows = 0;
      if (idCategoria<=4){
         cols=1;         
      }   
      GridLayout layout = new GridLayout(rows, cols);
      layout.setHgap(3); layout.setVgap(3); // espaciando entre componentes    
      panelProductos.setLayout(layout);
      
      for (int i = 0; i < idCategoria; ++i) {
         ImageIcon icon = new ImageIcon("/home/ivan/tmp-img/apple.png");
         String buttonTitle = "Title " + i;
         String buttonId = "idProducto" + i;
         JButton button = new JButton(buttonTitle, icon); // text of the button   
         button.setHorizontalTextPosition(JButton.CENTER);
         button.setVerticalTextPosition(JButton.BOTTOM);
         button.setName(buttonId); // Name of the button  
         button.setPreferredSize(new Dimension(100, 100));
         button.addActionListener((java.awt.event.ActionEvent e) -> {
            JButton b1 = (JButton) e.getSource(); // Referencia del boton seleccionado            
            producto.setText("Precio: " + b1.getName());
            producto.setName(b1.getName());
            String choice = e.getActionCommand();
            if (proceso==null){
               System.out.println("Proceso no creado");
               proceso = new Process();
               // Le mandamos al thread una referencia del JLabel del producto
               // para que siempre nos actualice el peso
               proceso.setProducto(producto);
               proceso.start(); // Iniciamos el thread
            }else{
               if (proceso.isRunning()){
                  /* Detenemos este thread. Matar proceso, debido a que termina
                     el metodo run.
                  */
                  proceso.stopProcess();
                  // Volvemos a crear un nuevo Thread (nuevo producto seleccionado)
                  proceso = new Process();
                  proceso.setProducto(producto);
                  proceso.start(); // Iniciamos el thread
               }   
            }
            
            System.out.println("Estatus Proceso running: " +  proceso.isRunning());
            
            //JOptionPane.showMessageDialog(null, "Escuchando: Producto: " + choice);            
         });
         panelProductos.add(button);
      }
   }
   

}
