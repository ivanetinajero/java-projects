package net.itinajero.application.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class PanelWest extends JPanel {
     
   private JFrame frame;
   private JPanel panel;
   private JLabel producto;
   
   public PanelWest(JFrame frame, JPanel panel, JLabel producto) {
      System.out.println("Constructor PanelWest");  
      this.frame = frame;
      this.panel = panel;
      this.producto = producto;
     
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
         System.out.println("Click " + b1.getName());  
         int idCategoria = Integer.parseInt(b1.getName().substring(11));
         panel.removeAll();
         updateCenter(idCategoria,panel);            
         frame.revalidate();
         frame.repaint();                        

         });
         add(button);
      }
   }
   
   
   public void updateCenter(int idCategoria,JPanel panel) {
            
      // DAO productos por idCategoria
      int cols=5;
      int rows = 0;
      if (idCategoria<=4){
         cols=1;         
      }   
      GridLayout layout = new GridLayout(rows, cols);
      layout.setHgap(3); layout.setVgap(3); // espaciando entre componentes    
      panel.setLayout(layout);
      System.out.println("Dibujando: " + idCategoria + " botones");
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
            System.out.println(b1.getName());
            producto.setText("Precio: " + b1.getName());
            producto.setName(b1.getName());
            String choice = e.getActionCommand();
            //JOptionPane.showMessageDialog(null, "Escuchando: Producto: " + choice);            
         });
         panel.add(button);
      }
   }
   

}
