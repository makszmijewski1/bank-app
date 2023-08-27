package org.example.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class ConsoleIO {
   public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

   public static String readLine() {
       String result = null;
       try {
           result = reader.readLine();
       } catch (IOException e ) {
           print("IOException");
       }
       return result;
   }

   public static Integer readInt() {
       Integer result = null;
       try {
           result = Integer.parseInt(reader.readLine());
       } catch (IOException e) {
           print("IOException");
       } catch (NumberFormatException e) {
           println("Wrong input");
       }
       return result;
   }

   public static Double readDouble() {
       Double result = null;
       try {
          result = Double.parseDouble(reader.readLine());
       } catch (IOException e) {
           print("IOException");
       } catch (NumberFormatException e) {
           println("Wrong input");
       }
       return result;
   }

   public static Long readLong(){
       Long result = null;
       try {
           result = Long.parseLong(reader.readLine());
       } catch (IOException e) {
           println("IOException");
       } catch (NumberFormatException e) {
           println("Wrong input");
       }
       return  result;
   }

   public static void print(String s) {
       System.out.print(s);
   }

    public static void println(String s) {
        System.out.println(s);
    }
}
