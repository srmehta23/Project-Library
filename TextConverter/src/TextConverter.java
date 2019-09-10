//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (descriptive title of the program making use of this file)
// Files:           (a list of all source files used by that program)
// Course:          (CS 200 ,Spring,2018)
//
// Author:          (Shubham Mehta)
// Email:           (smehta23@wisc.edu)
// Lecturer's Name: (Marc Renault)
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;
public class TextConverter {

    /**
     * 1337 - convert the string to leet-speak:
     *   Replace each L or l with a 1 (numeral one)
     *   Replace each E or e with a 3 (numeral three)
     *   Replace each T or t with a 7 (numeral seven)
     *   Replace each O or o with a 0 (numeral zero)
     *   Replace each S or s with a $ (dollar sign)
     *    
     * @param current Original string
     * @return string converted to leet-speak.
     */
    public static String action1337(String current) {
        current = current.replace('l','1');
        current = current.replace('e','3');
        current = current.replace('t','7');
        current = current.replace('o','0');
        current = current.replace('s','$');
        current = current.replace('L','1');
        current = current.replace('E','3');
        current = current.replace('T','7');
        current = current.replace('O','0');
        current = current.replace('S','$');
            return current;  //FIX ME
    }

    /**
     *  tests action1337 method with various cases to ensure it is working
     *  correctly.
     */
    public static void testAction1337() {
        boolean error = false;

        String input1 = "LEeTs";
        String expected1 = "1337$";
        String result1 = action1337( input1);
        if ( !result1.equals( expected1)) {
            error = true;
            System.out.println("1) testAction1337 with input " + input1 + ", expected: " + expected1 + " but result:" + result1);
        }

        //FIX ME
        //add at least 2 other test cases. What edge cases can you think of?
        String input3 = "EeLlTts";
        String expected3 = "331177$";
        
        String result3 = action1337( input3);
        if ( !result3.equals( expected3)) {
            error = true;
            System.out.println("1) testAction1337 with input " + input1 + ", expected: " + expected1 + " but result:" + result1);
        }
        String input2 = "LlshubhamTts";
        String expected2 = "11$hubham77$";
        
        String result2 = action1337( input1);
       if ( !result2.equals( expected2)) {
           error = true;
           System.out.println("1) testAction1337 with input " + input1 + ", expected: " + expected1 + " but result:" + result1);
       }
        
        if ( error) {
            System.out.println( "testAction1337 failed");
        } else {
            System.out.println("testAction1337 passed");
        }
        
       
    }



    /**
     * This reverses the order of characters in the current string. 
     * @param current  Original string to be reversed.
     * @return  The string in reversed order.
     */
    public static String actionReverse(String current) {
        int i ;
        String reverse = "";
        int stringLength = current.length();
            for (i = stringLength - 1 ; i >= 0 ; i--) {
               reverse = reverse + current.charAt(i) ;
            }
        return reverse;  //FIX ME
    }

    /**
     *  tests actionReverse method with various cases to ensure it is working
     *  correctly. 
     */
    public static void testActionReverse() {
        boolean error = false;

        String input1 = "Abc";
        String expected1 = "cbA";
        String result1 = actionReverse( input1);
        if ( !result1.equals( expected1)) {
            error = true;
            System.out.println("1) testActionReverse with input " + input1 + ", expected: " + expected1 + " but result:" + result1);
        }

        //FIX ME
        String input2 = "Shubham";
        String expected2 = "mahbuhS";
        String result2 = actionReverse( input2);
        
        if ( !result2.equals( expected2)) {
            error = true;
            System.out.println("1) testActionReverse with input " + input2 + ", expected: " + expected2 + " but result:" + result2);
        }
        
        String input3 = "aBc";
        String expected3 = "cBa";
        String result3 = actionReverse( input3);
        
        if ( !result3.equals( expected3)) {
            error = true;
            System.out.println("1) testActionReverse with input " + input3 + ", expected: " + expected3 + " but result:" + result3);
        }
        
      
        //add at least 2 other test cases. What edge cases can you think of?
        if ( error) {
            System.out.println( "testActionReverse failed");
        } else {
            System.out.println("testActionReverse passed");
        }
    }   

    /**
     * Provides the main menu for the text converter and calls methods based
     * on menu options chosen.
     * @param args
     */
    public static void main(String[] args) {
               System.out.println("Welcome to the Text Converter.");
               System.out.println("Available Actions:");
               System.out.println("  1337) convert to 1337-speak");
               System.out.println("  rev) reverse the string");
               System.out.println("  quit) exit the program");
               System.out.println("");
               System.out.print("Please enter a string: ");
               Scanner scnr = new Scanner(System.in);
               String current = scnr.nextLine();
               while(scnr.hasNextLine()) {
                   System.out.print("Action (1337, rev, quit): ");
                   String action = scnr.nextLine();
                   switch(action) {
                       case "1337":
                           current = action1337(current);
                           System.out.println(current);
                           
                           break;
                       case "rev":
                           current =actionReverse(current);
                          System.out.println(current);
                           break;
                       case "quit":
                           System.out.println("See you next time!");
                           break;
                       default:
                           System.out.println("Unrecognized action.");
                           System.out.println(current);
                   }
               }
            
               scnr.close();
               //FIX ME
    }

}
