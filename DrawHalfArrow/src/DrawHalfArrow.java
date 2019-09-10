//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Files: (a list of all source files used by that program)
// Course: (CS 200 ,Spring,2018)
//
// Author: (Shubham Mehta)
// Email: (smehta23@wisc.edu)
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

public class DrawHalfArrow {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int arrowBaseHeight = 0;
        int arrowBaseWidth = 0;
        int arrowHeadWidth = 0;

        int i = 0;
        int j = 0;

        System.out.print("Enter arrow base height: ");
        arrowBaseHeight = scnr.nextInt();

        System.out.print("Enter arrow base width: ");
        arrowBaseWidth = scnr.nextInt();

        System.out.print("Enter arrow head width: ");
        arrowHeadWidth = scnr.nextInt();

        for (; arrowHeadWidth <= arrowBaseWidth;) {
            System.out.print("Enter arrow head width: ");
            arrowHeadWidth = scnr.nextInt();
        }
        // make sure arrow head width is larger than base width
        // draw arrow base
        System.out.println("");
        for (i = 0; i < arrowBaseHeight; i++) {
            for (j = 0; j < arrowBaseWidth; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        // draw arrow head


        for (i = arrowHeadWidth; i > 0; i--) {
            for (j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
