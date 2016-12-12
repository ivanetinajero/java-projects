package net.itinajero;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Principal {

   public static void main(String[] args) {
      JButton boton1 = new JButton("Boton1");
      JButton boton2 = new JButton("Boton2");
      JButton boton3 = new JButton("Boton3");
      JButton boton4 = new JButton("Boton4");
      JLabel etiqueta = new JLabel("Boton5");
      Font font = new Font("Courier New", 1, 40);
      etiqueta.setFont(font);
      JFrame frame = new JFrame("Sistema");
      frame.setLayout(new BorderLayout());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 400);

      JScrollPane scrollPane = new JScrollPane(new Panel());
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      JScrollBar bar = scrollPane.getVerticalScrollBar();
      bar.setPreferredSize(new Dimension(30, 0)); // ancho del scroll    
      frame.add(scrollPane, BorderLayout.CENTER);
      frame.add(boton1, BorderLayout.WEST);
      frame.add(boton2, BorderLayout.EAST);
      frame.add(boton3, BorderLayout.SOUTH);
      frame.add(etiqueta, BorderLayout.NORTH);
      frame.setVisible(true);
   }
}
