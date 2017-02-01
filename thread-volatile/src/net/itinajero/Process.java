package net.itinajero;

public class Process extends Thread {

   private volatile boolean running = true; // Variable to stop the Thread
   private int counter = 0;

   public Process(String name){
      setName(name); // name of the thread...
   }
   
   @Override
   public void run() {
      System.out.println("Starting theread: " + getName());
      while (running) {
         counter++;
         System.out.println("Counter: " + counter);
         try {
            Thread.sleep(1000);
         } catch (InterruptedException ex) {
            System.out.println("Error: " + ex.getMessage());
         }
      }
   }

   public boolean isRunning() {
      return running;
   }

   public void stopProcess() {
      System.out.println("Stoping theread: " + getName());
      running = false;
   }
   
}
