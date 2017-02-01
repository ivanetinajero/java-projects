package net.itinajero;

public class Proceso {

   private int balance = 0;

   public static void main(String[] args) {
      Proceso p = new Proceso();
      p.tarea1();

   }

   public void tarea1() {

      Thread t1 = new Thread(new Runnable() {
         @Override
         public void run() {
            System.out.println("Thread1 started");   
            for (int i = 0; i < 100000; i++) {               
               add();
            }
            System.out.println("Thread1 finished"); 
         }
      });

      Thread t2 = new Thread(new Runnable() {
         @Override
         public void run() {
            System.out.println("Thread2 started"); 
            for (int i = 0; i < 100000; i++) {               
               substract();
            }
            System.out.println("Thread2 finished");
         }
      });

      t1.start();
      t2.start();
      
      try {
         t1.join();
         t2.join();
      } catch (InterruptedException ex) {
         System.out.println("Error: " + ex.getMessage());
      }
            
      System.out.println("Balance: " + balance);
   }

   public synchronized void add() {
      balance++;
   }

   public synchronized void substract() {
      balance--;
   }

}
