package net.itinajero;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BarCodeData {

   private int id;   
   private String number;
   private List<String> text;

   public BarCodeData(int id, String number) {
      this.id = id;
      this.number = number;
      text = new LinkedList<>();
   }

   // Nos aseguramos de que el nombre de la imagen, nunca se repita...
   public String getFileName() {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
      Date now = new Date();      
      return id+"-"+format.format(now);
   }

   public void addTextLine(String info){
      text.add(info);
   }

   public String getNumber() {
      return number;
   }   

   public List<String> getText() {
      return text;
   }
   
   @Override
   public String toString() {
      return "BarCodeData{" + "id=" + id + ", barcode=" + number + ", text=" + text + '}';
   }
      
}
