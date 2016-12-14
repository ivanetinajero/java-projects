package net.itinajero;

import net.itinajero.util.Utileria;

public class Principal {

   public static void main(String[] args) throws InterruptedException {
      // Iniciamos dos procesos
      String threadName="thread1";
      Proceso h1 = new Proceso(threadName);
      threadName="thread2";
      Proceso h2 = new Proceso(threadName);
      h1.start();
      h2.start();
      Thread.sleep(5000); // dejamos que trabajen un poco los threads 
      
      // Metodo para detener todos los threads
      Utileria.stopThreads();
      
      // Metodo para detener por nombre ()
      //Utileria.stopThreadByName("thread2"); // el thread1 deberia seguir trabajando
      
   }
  
}
