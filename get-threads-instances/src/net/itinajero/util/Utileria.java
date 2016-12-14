package net.itinajero.util;

import java.util.Set;
import net.itinajero.Proceso;

public class Utileria {

   /**
    * Metodo para detener todos los hilos de tipo net.itinajero.Proceso 
    */
   public static void stopThreads() {
      // Creamos un set de Threads que todos los threads que estan vivos
      Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
      
      // Convertimos el set a un arreglo de Threads
      Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);

      for (Thread thread : threadArray) {
         System.out.println("Thread name: " + thread.getName());
         Class tipoThread = thread.getClass(); // Vemos de que tipo es cada thread vivo
         
         // Si es un thread del tipo que andamos buscando       
         if (tipoThread == Proceso.class) { // detener todos, sin importar el nombre
            // Los threads pueden ser de diferente tipo.             
            Proceso tmp = (Proceso) thread; // Hacemos un cast de tipo net.itinajero.Proceso
            System.out.println("Deteniendo theread: " + thread.getName());
            tmp.stopProcess(); // Los threads de tipo net.itinajero.Proceso tienen el metodo stopProcess
         }
      }
   }
   
   /**
    * Metodo para detener un thread de tipo net.itinajero.Proceso por nombre
    * @param threadName 
    */
   public static void stopThreadByName(String threadName) {
      // Creamos un set de Threads que todos los threads que estan vivos
      Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
      
      // Convertimos el set a un arreglo de Threads
      Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);

      for (Thread thread : threadArray) {
         System.out.println("Thread name: " + thread.getName());
         Class tipoThread = thread.getClass(); // Vemos de que tipo es cada thread vivo
         
         // Si es un thread del tipo que andamos buscando
         if (tipoThread == Proceso.class && thread.getName().equals(threadName)) { // buscamos por nombre y lo detenemos         
            // Los threads pueden ser de diferente tipo.             
            Proceso tmp = (Proceso) thread; // Hacemos un cast de tipo net.itinajero.Proceso
            System.out.println("Deteniendo theread: " + thread.getName());
            tmp.stopProcess(); // Los threads de tipo net.itinajero.Proceso tienen el metodo stopProcess
         }
      }
   }

}
