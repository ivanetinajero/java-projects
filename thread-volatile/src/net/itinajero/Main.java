package net.itinajero;

import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Process proceso = new Process("Proceso 1");
      proceso.start();
      Scanner scaner = new Scanner(System.in);
      scaner.nextLine();
      // proceso.stopProcess(); //  detenerlo
      // System.out.println("Hilos de tipo Process corriendo: " + ThreadUtil.getRunningThreads());
      // ThreadUtil.stopThreads(); // Detener todos los hilos de tipo Process
      // ThreadUtil.stopThreadByName("Proceso 1");// detener el hilo de tipo Process con este nombre
      Process tmpProcess = ThreadUtil.getThreadByName("Proceso "); // recuperar instancia de un hilo con este nombre
      tmpProcess.stopProcess();
   }
}
