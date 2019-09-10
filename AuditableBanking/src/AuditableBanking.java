
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Files: (a list of all source files used by that program)
// Course: CS 300
//
// Author: Shubham Mehta
// Email: smehta23@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: -
// Partner Email: -
// Partner Lecturer's Name: -
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;

public class AuditableBanking {
  public static void main(String[] args) {
    System.out.println("========== Welcome to the Auditable Banking App ==========");

    String command = "x";
    int currentBalance;
    int[][] allTransactions = new int[100][]; // The Oversize array containing all the transactions
    int transactionCount = 0;
    Scanner scnr = new Scanner(System.in);
    for (int i = 0; i < 1000; i++) {
      System.out.println("COMMAND MENU:");
      System.out.println("  Submit a Transaction (enter sequence of integers separated by spaces)");
      System.out.println("  Show Current [B]alance");
      System.out.println("  Show Number of [O]verdrafts");
      System.out.println("  [Q]uit Program");
      System.out.println("ENTER COMMAND: ");
      command = scnr.nextLine();
      command = command.toLowerCase();
      if (command.equals("b")) {
        System.out.println(
            "Current Balance: " + calculateCurrentBalance(allTransactions, transactionCount));
        System.out.println("");
      } else if (command.equals("o")) {
        System.out.println("Number of Overdrafts: "
            + calculateNumberOfOverdrafts(allTransactions, transactionCount));
        System.out.println("");
      } else if (command.equals("q")) {
        System.out.println("============ Thank you for using this App!!!! ============");
        break;
      } else {
        transactionCount = processCommand(command, allTransactions, transactionCount);
        System.out.println("");
      }

    }
  }

  /**
   * Adds a transaction group to an array of transaction groups. If the allTransactions array is
   * already full then this method will do nothing other than return allTransactionCount.
   * 
   * @param newTransactions is the new transaction group being added (perfect size).
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *        newTransactions is added.
   * @return the number of transaction groups within allTransactions after newTransactions is added.
   */
  public static int submitTransactions(int[] newTransactions, int[][] allTransactions,
      int allTransactionsCount) {
    if (allTransactions.length == allTransactionsCount) { // Checking if the allTransactions array
                                                          // is already full
      return allTransactionsCount;
    } else {
      allTransactions[allTransactionsCount] = newTransactions;
      allTransactionsCount++;
    }
    return allTransactionsCount;
  }

  /**
   * Adds a transaction group to an array of transaction groups. If the allTransactions array is
   * already full or if the first transaction in the string is anything else except 0, 1 or 2 then
   * this method will do nothing other than return allTransactionCount.
   * 
   * @param command is the string of transaction which is to be checked and to be added to the array
   *        allTransactions.
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *        newTransactions is added.
   * @return the number of transaction groups within allTransactions after newTransactions is added.
   */
  public static int processCommand(String command, int[][] allTransactions,
      int allTransactionsCount) {

    command = command.trim();
    String[] divided = command.split(" "); // @divided Dividing string from spaces and putting it in
                                           // a string array
    int firstInt = Integer.parseInt(divided[0]); // the integer at index 0 of string command

    if (allTransactions.length == allTransactionsCount) { // checking if array is full
      return allTransactionsCount;
    } else if (firstInt < 0 || firstInt > 2) { // checking if first integer of transaction is
                                               // anything else except
                                               // 0,1 or 2.
      return allTransactionsCount;
    } else {
      int[] newTransaction = new int[divided.length]; // an integer array of same length as command
                                                      // string or say the divided array , this int
                                                      // array contains all the transactions, which
                                                      // are
                                                      // to be added to the allTransactions array

      for (int i = 0; i < divided.length; i++) { // copying the string @command to integer array
                                                 // @newTransaction
        newTransaction[i] = Integer.parseInt(divided[i]);
      }
      allTransactions[allTransactionsCount] = newTransaction; // adding @newTransaction(transaction
                                                              // array)
                                                              // to @allTransactions
                                                              // array
      allTransactionsCount++;
    }
    String upperCase = command.toUpperCase();
    char firstLetter = upperCase.charAt(0);
    if (firstLetter == 'B') {
      System.out.println(calculateCurrentBalance(allTransactions, allTransactionsCount));
    } else if (firstLetter == 'O') {
      System.out.println(calculateNumberOfOverdrafts(allTransactions, allTransactionsCount));
    }
    return allTransactionsCount;
  }

  /**
   * calculate and return the total account balance based on a collection of provided transaction
   * groups.
   * 
   * 
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *        newTransactions is added.
   * @return totalAccountBalance is the total balance after adding all the transactions till the
   *         allTransactionsCount ends.
   */
  public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {

    int totalAccountBalance = 0;
    for (int i = 0; i < allTransactionsCount; i++) {

      if (allTransactions[i][0] == 0) { // checks for Binary Amount Transactions
        for (int j = 1; j < allTransactions[i].length; j++) {
          if (allTransactions[i][j] == 0) { // Withdraws 1$ for 0
            totalAccountBalance = totalAccountBalance - 1;
          } else if (allTransactions[i][j] == 1) { // deposits 1$ for 1
            totalAccountBalance = totalAccountBalance + 1;
          }
        }
      } else if (allTransactions[i][0] == 1) { // Checks for Integer Amount Transactions
        for (int j = 1; j < allTransactions[i].length; j++) {

          totalAccountBalance = totalAccountBalance + allTransactions[i][j]; // If the transaction
                                                                             // amount is negative,
                                                                             // then it will be
                                                                             // subtracted from the
                                                                             // totalAccountBalance,
                                                                             // for positive values,
                                                                             // it will be added to
                                                                             // the balance.

        }
      } else if (allTransactions[i][0] == 2) { // Checks for Quick Withdraw Transactions
        for (int j = 1; j < allTransactions[i].length; j++) {
          if (j == 1) {
            totalAccountBalance = totalAccountBalance - allTransactions[i][1] * 20;
          } else if (j == 2) {
            totalAccountBalance = totalAccountBalance - allTransactions[i][2] * 40;
          } else if (j == 3) {
            totalAccountBalance = totalAccountBalance - allTransactions[i][3] * 80;
          } else if (j == 4) {
            totalAccountBalance = totalAccountBalance - allTransactions[i][4] * 100;
          }
        }
      }
    }
    return totalAccountBalance;
  }

  /**
   * calculate and return the total number of overdrafts based on the collection of provided
   * transaction groups.
   * 
   * 
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions (before
   *        newTransactions is added.
   * @return numberOfOverdrafts is the total number of times the account went negative till
   *         allTransactionsCount ends.
   */
  public static int calculateNumberOfOverdrafts(int[][] allTransactions, int allTransactionsCount) {
    int totalAccountBalance = 0;
    int numberOfOverdrafts = 0;

    for (int i = 0; i < allTransactionsCount; i++) {
      if (allTransactions[i][0] == 0) { // checks for Binary Amount Transactions
        for (int j = 1; j < allTransactions[i].length; j++) {
          if (allTransactions[i][j] == 0) { // Withdraws 1$ for 0
            totalAccountBalance = totalAccountBalance - 1;
            if (totalAccountBalance < 0) { // checks if the account has overdrafted, after every
                                           // transaction
              System.out.print(totalAccountBalance);
              numberOfOverdrafts++;
            }
          } else if (allTransactions[i][j] == 1) { // deposits 1$ for 1
            totalAccountBalance = totalAccountBalance + 1;
            if (totalAccountBalance < 0) {
              numberOfOverdrafts++;
            }
          }
        }
      } else if (allTransactions[i][0] == 1) { // Checks for Integer Amount Transactions
        for (int j = 1; j < allTransactions[i].length; j++) {

          totalAccountBalance = totalAccountBalance + allTransactions[i][j]; // If the transaction
                                                                             // amount is negative,
                                                                             // then it is
                                                                             // subtracted from the
                                                                             // totalBalance,
                                                                             // otherwise it is
                                                                             // added to the
                                                                             // balance.
          if (totalAccountBalance < 0) {
            numberOfOverdrafts++;
          }
        }
      } else if (allTransactions[i][0] == 2) { // Checks for Quick Withdraw Transactions
        for (int j = 1; j < allTransactions[i].length; j++) {
          if (j == 1) {
            totalAccountBalance = totalAccountBalance - allTransactions[i][1] * 20;
            if (totalAccountBalance < 0) {
              numberOfOverdrafts++;
            }
          } else if (j == 2) {
            totalAccountBalance = totalAccountBalance - allTransactions[i][2] * 40;
            if (totalAccountBalance < 0) {
              numberOfOverdrafts++;
            }
          } else if (j == 3) {
            totalAccountBalance = totalAccountBalance - allTransactions[i][3] * 80;
            if (totalAccountBalance < 0) {
              numberOfOverdrafts++;
            }
          } else if (j == 4) {
            totalAccountBalance = totalAccountBalance - allTransactions[i][4] * 100;
            if (totalAccountBalance < 0) {
              numberOfOverdrafts++;
            }
          }
        }

      }
    }
    return numberOfOverdrafts;
  }
}
