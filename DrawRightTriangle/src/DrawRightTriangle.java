
//
// Title: (descriptive title of the program making use of this file)
// Files: (a list of all source files used by that program)
// Course: (CS 203)
//
// Author: Shubham Mehta
// Email: (smehta23@wisc.edu )
// Lecturer's Name: (Marc Renault)
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;

public class DrawRightTriangle {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int i;
        int height;
        int j;
        System.out.print("Enter a character: ");
        Scanner scnr = new Scanner(System.in);
        char c = scnr.next().charAt(0);
        System.out.print("Enter triangle height (1-10): ");
        height = scnr.nextInt();
        for (; height < 0 || height > 10;) {
            System.out.println("Please enter height between 1 and 10.");
            height = scnr.nextInt();

        }
        System.out.println();
        for (i = 0; i < height; i++) {
            for (j = 0; j <= i; j++) {
                System.out.print(c + " ");
            }
            System.out.println("");
        }
        scnr.close();
    }

}
