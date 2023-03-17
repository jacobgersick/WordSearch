//Programmer: Jacob Gersick
//Date: 2/28/2023
//Assignment: Assignment 1 - Word Search
//Class: CIS145 w/Jeremiah Ramsey
//This class creates the WordSearch puzzle
//and has methods for printing the puzzle and solution

import java.util.*;
public class WordSearch {

   private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   private int size;
   private char[][] grid;
   private ArrayList<String> words;
   private char[][] solved;
   Random random = new Random();
       
//constructs the char array using the largest word as base size +2 
   public WordSearch(WordList list) {
      this.size = list.getMax() +2;
      grid = new char[size][size];
      solved = new char[size][size];
      this.words = list.getList();
      //fills array with default chars
      initialize();
      //fills array with words from list
      generate();
   }//end constructor
   
//fills the char array with spaces, 'solved' array with dots.
   private void initialize() {
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            grid[i][j] = ' ';
            solved[i][j] = '.';
         }
      } 
   }//end initialize()
    
//places words if possible and prints any failed placements    
   public void generate() {
   
      for (String word : words) {
         boolean placed = false;
         int tryCount = 0;
         
         while (!placed && tryCount < 100) {
         
  //generates random x/y coordinates on the grid
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            
  //returns a number for direction between -1, 0 (bad option), or 1
            int dirX = random.nextInt(3) - 1;
            int dirY = random.nextInt(3) - 1; 
            
  //skips the case of no directional values at all
            if (dirX == 0 && dirY == 0) {
               continue;
            }
 //tries to place the word and moves to the next if so
            if (placeWord(word,x,y,dirX,dirY)) {
               placed = true;
               tryCount = 0;
            }
            tryCount++;   
         } // end while loop
         if (!placed) {
            System.out.println("Failed to place: " + word);
         }
      } // end for loop
      
      fillRest();
   }//end generate()
   
   
 //adds random characters in place of the default characters in grid 
   public void fillRest(){
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            if (grid[i][j] == ' ') {
            
               grid[i][j] = alphabet.charAt(random.nextInt(26));
            }
         }
      }
   }//end fillRest()


//tries to place the word somewhere using coordinate and direction parameters
   public boolean placeWord(String word, int x, int y, int dirX, int dirY) {  
   //checks if words overrun boundaries of the grid on x plane
      if (x + (word.length() * dirX) < 0 || x + (word.length()) * dirX >= size) {
         return false;
      }
      //checks if words overrun boundaries on the y plane
      if (y + (word.length() *dirY) < 0 || y + (word.length()) * dirY >= size) {
         return false;
      }
    
  //checks if grid space is empty or matches the needed character
 //exits for another try if not  
      for (int i = 0; i < word.length(); i++) {
         char c = word.charAt(i);
         int px = x + (i * dirX);
         int py = y + (i * dirY);
         if (grid[px][py] != ' ' && grid[px][py] != c) {
            return false;
         }
      }
 //now that check is complete, update grid to have word in it
      for (int i = 0; i < word.length(); i++) {
         char c = word.charAt(i);
         int px = x + (i * dirX);
         int py = y + (i * dirY);
         grid[px][py] = c;
         solved[px][py] = c;
      }
 //tells generate() to move to next word
      return true;
   }


 //prints the 'solved' version of the grid          
   public void printSolved() {
      for (int i = 0; i < solved.length; i++) {
         for (int j = 0; j < solved.length; j++) {
            System.out.print(solved[i][j] + " ");
         }
         System.out.println();
      }
   }//end printSolved()

   //prints everything in the grid and word list
   public void printMe() {
   System.out.println();
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid.length; j++) {
            System.out.print(grid[i][j] + " ");
         }
         System.out.println("");
      }//end for
      int num = 0;
      System.out.println("\nTry to find:");
      for (String word : words) {
      System.out.print(word + "\t");
      num++;
      if (num % 3 == 0) {
      System.out.println();
      }
      }
      System.out.println();
   }//end printme()

}// end class
