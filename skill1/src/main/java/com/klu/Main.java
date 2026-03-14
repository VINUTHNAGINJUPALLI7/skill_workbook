package com.klu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
         System.out.println("Hello from Skill 1: Git Version Control!");
         File file = new File("notes.txt");
         try {
             Scanner sc = new Scanner(file);
             while (sc.hasNextLine()) {
                 System.out.println(sc.nextLine());
             }
             sc.close();
         } catch (FileNotFoundException e) {
             System.out.println("notes.txt not found!");
         }
     }
}