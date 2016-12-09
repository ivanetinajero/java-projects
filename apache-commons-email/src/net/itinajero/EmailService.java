package net.itinajero;
/*
   https://commons.apache.org/proper/commons-email/download_email.cgi
   https://java.net/projects/javamail/pages/Home#Download_JavaMail_Release
*/

import java.util.Collection;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailService {

   // La direccion de correo de gmail que sera la usada para enviar el correo.
   static String GMAIL_USER = "some@gmail.com";
   // Esta es la contrasena de la cuenta de gmail que se usara para enviar el correo.
   static String GMAIL_PWD = "*******";
   static String SMTP_HOST_NAME = "smtp.gmail.com";
   static Integer SMTP_HOST_PORT = 587;

   /**
    * Metodo para enviar un correo electronico tipo HTML
    *
    * @param recipients
    * @param subject
    * @param text
    * @throws EmailException
    */
   public void send(Collection<String> recipients, String subject, String text) {

      try {
         HtmlEmail email = new HtmlEmail();
         email.setHostName(SMTP_HOST_NAME);
         email.setSmtpPort(SMTP_HOST_PORT);
         email.setSSLOnConnect(true);
         email.setAuthentication(GMAIL_USER, GMAIL_PWD);
         email.setSSLOnConnect(true);
         email.setFrom(GMAIL_USER);
         //email.addTo("ivanetinajero@gmail.com", "ivan"); // only to 1 user     
         email.addTo(recipients.toArray(new String[]{}));
         email.setSubject(subject);
         email.setHtmlMsg(text);      
         email.send();
      } catch (EmailException ex) {
         System.out.println("Error EmailService.send: " + ex.getMessage());
      }
         
   }

   /**
    * Metodo para enviar un correo electronico tipo HTML con un archivo adjunto
    * @param recipients
    * @param subject
    * @param text
    * @param filePath Ruta del archivo. Por ejemplo: /home/ivan/Documents/banorte.png
    * @throws EmailException 
    */
   public void sendAttachment(Collection<String> recipients, String subject, String text, String filePath) {
      try {
         // Create the attachment
         EmailAttachment attachment = new EmailAttachment();
         attachment.setPath(filePath);
         attachment.setDisposition(EmailAttachment.ATTACHMENT);
         
         // Create the email message
         HtmlEmail email = new HtmlEmail();
         email.setHostName(SMTP_HOST_NAME);
         email.setSmtpPort(SMTP_HOST_PORT);
         email.setSSLOnConnect(true);
         email.setAuthentication(GMAIL_USER, GMAIL_PWD);
         email.setSSLOnConnect(true);
         email.setFrom(GMAIL_USER);
         email.addTo(recipients.toArray(new String[]{}));
         email.setSubject(subject);
         email.setMsg(text);
         
         // add the attachment
         email.attach(attachment);
         
         // send the email
         email.send();
         
      } catch (EmailException ex) {
         System.out.println("Error EmailService.sendAttachment: " + ex.getMessage());
      }

   }

}
