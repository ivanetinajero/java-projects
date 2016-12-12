package net.itinajero;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JPanel {
   
   private JFrame window;

   public Panel() {
      System.out.println("Constructor Panel");      
      setLayout(new java.awt.GridLayout(0,5));      
      for (int i = 0; i < 128; ++i) {
         ImageIcon icon = new ImageIcon("/home/ivan/tmp-img/apple.png");
         String buttonTitle = "Title " + i;
         String buttonId = "id" + i;
         JButton button = new JButton(buttonTitle,icon); // text of the button   
         button.setHorizontalTextPosition(JButton.CENTER);
         button.setVerticalTextPosition(JButton.BOTTOM);         
         button.setName(buttonId); // Name of the button  
         button.setPreferredSize(new Dimension(150, 150));
         button.addActionListener((java.awt.event.ActionEvent e) -> {
            JButton b1 = (JButton) e.getSource();
            System.out.println(b1.getName());
            String choice = e.getActionCommand();
            System.out.println("Click: " + choice);
            
         });
         add(button);
      }        
   }
}
