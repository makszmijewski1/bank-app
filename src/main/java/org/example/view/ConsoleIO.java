package org.example.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO {
   public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

   public static String readLine() throws IOException {
      return reader.readLine();
   }

   public static int readInt() throws IOException {
       return Integer.parseInt(reader.readLine());
   }

   public static double readDouble() throws IOException {
       return Double.parseDouble(reader.readLine());
   }

   public static Long readLong() throws IOException {
       return Long.parseLong(reader.readLine());
   }

   public static void print(String s) {
       System.out.print(s);
   }

    public static void println(String s) {
        System.out.println(s);
    }
}
