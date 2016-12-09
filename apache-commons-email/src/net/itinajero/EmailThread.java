package net.itinajero;

import java.util.Collection;

public class EmailThread extends Thread { 
 
   private Collection<String> recipients;
   private String subject; 
   private String text;
   private String fileName;

   public EmailThread(Collection<String> recipients, String subject, String text, String fileName) {
      this.recipients = recipients;
      this.subject = subject;
      this.text = text;
      this.fileName = fileName;
      setName("Gmail"); // nombre del hilo
   }
   
   @Override
   public void run() {
      System.out.println("Sending email... " +  getName() );
      EmailService service = new EmailService();
      if (fileName.equals(""))
         service.send(recipients,subject,text); // no tiene adjuntos         
      else
         service.sendAttachment(recipients,subject,text,fileName); // tiene archivo adjunto
      
      // Here we could put other tasks....  
      try {
         for(int i = 0; i < 5; i++) {
            System.out.println("Thread: " + getName() + ", " + i);
            // Let the thread sleep for a while.
            Thread.sleep(1000);
         }
      }catch (InterruptedException e) {
         System.out.println("Thread " +  getName() + " interrupted.");
      }
      System.out.println("The email was sent...");
   }     
}