package net.itinajero;

public class Proceso extends Thread {
   private volatile boolean running = true; // thread 
   
   public Proceso(String name) {
      super(name);     
   }

   @Override
   public void run() { 
      while (running){
         try {
            System.out.println("Doing something " + this.getName());
            Thread.sleep(500);
         } catch (InterruptedException ex) {
            System.out.println("Error");
         }      
      }
      System.out.println("Thread stopped.");
   }
   
   public void stopProcess() {
      running = false;
   }
}
