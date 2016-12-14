package net.itinajero.application.gui.util;

import java.util.Set;
import net.itinajero.application.gui.Process;

public class ThreadUtil {

   /**
    * Metodo para detener todos los hilos de tipo net.itinajero.application.gui.Process 
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
         if (tipoThread == Process.class) { // detener todos, sin importar el nombre
            // Los threads pueden ser de diferente tipo.             
            Process tmp = (Process) thread; // Hacemos un cast de tipo net.itinajero.Proceso
            System.out.println("Deteniendo theread: " + thread.getName());
            tmp.stopProcess(); // Los threads de tipo net.itinajero.Proceso tienen el metodo stopProcess
         }
      }
   }
   
   /**
    * Metodo que regresa el numero de Threads en ejecucion
    * @return 
    */
   public static int getRunningThreads() {
      int running=0;      
      Set<Thread> threadSet = Thread.getAllStackTraces().keySet();      
      // Convertimos el set a un arreglo de Threads
      Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
      for (Thread thread : threadArray) {        
         Class tipoThread = thread.getClass();         
         if (tipoThread == Process.class) running++;
      }
      return running;
   }
   
   /**
    * Metodo para detener un thread de tipo net.itinajero.application.gui.Process por nombre
    * @param threadName 
    */
   public static void stopThreadByName(String threadName) {     
      Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
      
      // Convertimos el set a un arreglo de Threads
      Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
      for (Thread thread : threadArray) {        
         Class tipoThread = thread.getClass();         
         // Si es un thread del tipo que andamos buscando
         if (tipoThread == Process.class && thread.getName().equals(threadName)) { // buscamos por nombre y lo detenemos                            
            Process tmp = (Process) thread; // Hacemos un cast de tipo net.itinajero.Proceso
            System.out.println("Deteniendo theread: " + thread.getName());
            tmp.stopProcess(); // Los threads de tipo net.itinajero.Proceso tienen el metodo stopProcess
         }
      }
   }

}
