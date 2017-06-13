package net.itinajero;

import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import javax.swing.*;

public class ThreadsExample extends GraphicsProgram {

   /* private instance variables */
   private Thread[] threads;
   private RacingSquare[] racers;
   private boolean[] finished;

   /* constants */
   private static final int NUM_RACERS = 3;

   public void run() {
      racers = new RacingSquare[NUM_RACERS];
      finished = new boolean[NUM_RACERS];
      threads = new Thread[NUM_RACERS];

      // finish line
      add(new GLine(510, 0, 510, 400));

      add(new JButton("Start!"), SOUTH);
      addActionListeners();
   }

   public void actionPerformed(ActionEvent e) {
      String cmd = e.getActionCommand();
      if (cmd.equals("Start!")) {
         for (int i = 0; i < NUM_RACERS; i++) {
            // remove the racer, if it previously existed
            if (racers[i] != null) {
               remove(racers[i]);
            }

            // create new racer
            racers[i] = new RacingSquare(i, finished);
            add(racers[i], 10, 10 + (40 * i));

            // send the new racer along its way 
            // (on a separate thread)
            threads[i] = new Thread(racers[i]);
            threads[i].start();
         }
      }
   }

}
