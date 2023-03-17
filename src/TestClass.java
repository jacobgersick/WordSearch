//Programmer: Jacob Gersick
//Date: 2/28/2023
//Assignment: Assignment 1 - Word Search
//Class: CIS145 w/Jeremiah Ramsey
//this is the main program for the Word Search puzzle.

import java.util.*;

public class TestClass {

   private static WordSearch game;

   //runs the general program
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      String reply;
      System.out.println("Hello, welcome to word search generator 5000.");
      
      do {
         menu();
         reply = input.nextLine();
         switch(reply) {
         
            case "g":
               WordList list = new WordList();
               makeList(list, input);
               game = new WordSearch(list);
               break;
         
            case "p":
               try {
                  game.printMe();
               } catch (Exception e) {
                  System.out.println("\nInvalid Entry, try again.\n");
               }//end try/catch
               break;
         
            case "s":
               try {
                  game.printSolved();
               } catch (Exception e) {
                  System.out.println("\nInvalid Entry, try again.\n");
               }//end try/catch
               break;
           
            case "q":
               System.out.println("Exiting. Goodbye.");
               break;
                    
            default:
               System.out.println("Not a valid choice, try again\n");
               break;
         }//end switch
      }//end do
      while (!reply.equalsIgnoreCase("q"));
   }// end main
 
   //prompts user for input to fill WordList object    
   public static void makeList(WordList list,Scanner input) {
      String reply;
      System.out.println("Enter words to add to the list: ");
      do { 
         reply = input.nextLine();
         reply = reply.replaceAll("\\s","");
         reply = reply.toUpperCase();
         if (reply.equalsIgnoreCase("end")) {
            System.out.println("\nWord Search created!\n");
            break;
         } else {
            list.add(reply);
            list.printList();
            System.out.print("Type next word or 'end':");
         }//end if/else
      }//end do/while 
      while (!reply.equalsIgnoreCase("end"));
   }// end makeList() 
    
   //generic menu option screen
   public static void menu() {
      System.out.println("Please select from the menu below: \n");
      System.out.println("(g)enerate a new word search.");
      System.out.println("(p)rint out the current word search.");
      System.out.println("(s)how the solution to current word search.");
      System.out.println("(q)uit the program.");
    
   } //end menu()
    
} // end class Main