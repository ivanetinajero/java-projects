// CS 106A: Programming Methodology, Spring 2017 
// http://stanford.edu/~stepp/cppdoc/
package net.itinajero;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import acm.graphics.GObject;
import java.awt.*;

public class Rain extends GraphicsProgram {
   
   private static final int RAINDROP_SIZE=10;
   private static final int RAINDROP_VELOCITY=4;

   public void run(){
      makeItRain();
      
     // make it fall down (FOREVER)
      int elapsedTime = 0;
      while (true) {  
  
         // update raindrop (drop by 2)         
         for (GObject obj : this){
           obj.move(0, RAINDROP_VELOCITY);
         }                  
   
         // wait
         pause(50);
         elapsedTime += 50;
         if (elapsedTime % 500 == 0){
            // add a new raindrop
            makeItRain();
         }
      }
   }
 
   // make one raindrop at top by random X-location
   public void makeItRain(){      
      int x = RandomGenerator.getInstance().nextInt(0, getWidth()- RAINDROP_SIZE);
      GOval raindrop = new GOval(x, 0, RAINDROP_SIZE,RAINDROP_SIZE);  
      raindrop.setFilled(true);
      raindrop.setFillColor(Color.CYAN);
      add(raindrop);            
   }   
}
