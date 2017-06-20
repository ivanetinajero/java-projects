// CS 106A: Programming Methodology, Spring 2017 
// http://stanford.edu/~stepp/cppdoc/
package net.itinajero;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import acm.graphics.GObject;
import java.awt.*;
import java.awt.event.MouseEvent;

public class RainMouse extends GraphicsProgram {
   
   private static final int RAINDROP_SIZE=20;
   private static final int RAINDROP_VELOCITY=4;

   public void run(){
      GOval firstDrop =  randomRaindrop();
      firstDrop.setFillColor(Color.RED);
      
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
           randomRaindrop();
         }
      }
   }
   
   public GOval randomRaindrop(){
      int x = RandomGenerator.getInstance().nextInt(0, getWidth()- RAINDROP_SIZE);
      return makeItRain(x, 0);
   }
 
   // make one raindrop at top by random X-location
   public GOval makeItRain(int x, int y){            
      GOval raindrop = new GOval(x, y, RAINDROP_SIZE,RAINDROP_SIZE);  
      raindrop.setFilled(true);
      raindrop.setFillColor(RandomGenerator.getInstance().nextColor());
      add(raindrop);
      return raindrop;
   }   
   
   @Override
   public void mouseClicked(MouseEvent event){
      makeItRain(event.getX(), event.getY());
   }
}
