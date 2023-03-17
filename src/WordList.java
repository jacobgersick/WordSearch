//Programmer: Jacob Gersick
//Date: 2/28/2023
//Assignment: Assignment 1 - Word Search
//Class: CIS145 w/Jeremiah Ramsey
//This class creates an ArrayList object
//it's probably overkill to have but
//I started with it so just kept it.

import java.util.*;

public class WordList {

   private ArrayList<String> list = new ArrayList<>();
   private int max = 0;

//adds words to list and updates max value
   public void add(String word) {
      this.list.add(word);
      if (word.length() > max) {
      max = word.length();
      }
      
   }//end add()

   public ArrayList<String> getList() {
      return list;
   }//end getList()

  //formats and prints list with numbers
   public void printList() {
   System.out.println("\nWords in your word list: ");
   int counter = 1;
      for (String word: list) {
         System.out.print(counter + ". ");
         System.out.println(word);
         counter++;
      }//end for
   }//end printList();
   
   public int getMax() {
   return max;
   }

}//end class