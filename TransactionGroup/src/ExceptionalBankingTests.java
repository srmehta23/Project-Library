
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
/*
 * ExceptionnnalBankingTests is a test class for Account class and TransactionGroup class. It test's
 * these classes for various erroneous inputs and checks if they throw an error or not.
 * 
 * @param failed is the counter for the number of tests which failed.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class ExceptionalBankingTests {
  public static void main(String[] args) {

    int failed = 0;

    if (!testAccountBalance()) {
      System.out.println("Test failed for the testAccountBalance().");
      failed++;
    }
    if (!testOverdraftCount()) {
      System.out.println("Test failed for the testOverdraftCount().");
      failed++;
    }

    if (!testTransactionGroupEmpty()) {
      System.out.println("Test failed for testTransactionGroupEmpty()");
      failed++;
    }
    if (!testTransactionGroupInvalidEncoding()) {
      System.out.println("Test failed for testTransactionGroupInvalidEncoding()");
      failed++;
    }
    if (!testAccountAddNegativeQuickWithdraw()) {
      System.out.println("Test failed for testAccountAddNegativeQuickWithdraw()");
      failed++;
    }
    if (!testAccountBadTransactionGroup()) {
      System.out.println("Test failed for testAccountBadTransactionGroup()");
      failed++;
    }
    if (!testAccountIndexOutOfBounds()) {
      System.out.println("Test failed for testAccountIndexOutOfBounds()");
      failed++;
    }
    if (!testAccountMissingFile()) {
      System.out.println("Test failed for testAccountMissingFile()");
      failed++;
    }
    if (failed != 0) {
      System.out.println(failed + "/8 tests passed.");
    } else {
      System.out.println("All tests Passed");
    }
  }


  /*
   * This method is a test for method caclulateCurrentBalance of Account class. During this test we
   * add 3 transaction groups of 3 different encoding type and check whether the calculation of
   * currentBalance is accurate or not.
   * 
   * @param newAccount is the account of the new user.
   */
  public static boolean testAccountBalance() {
    Account newAccount = new Account("user");

    try {
      newAccount.addTransactionGroup("0 1 0 1 0 0 1 1 1 1"); // try-catch block is added because the
                                                             // method addTransactionGroup can throw
                                                             // a DataFormatException.
    } catch (DataFormatException e) {
      e.printStackTrace();
    }

    int currentBalance = newAccount.getCurrentBalance();
    if (currentBalance != (3)) {
      System.out.println("FAILURE TEST #1 @testAccountBalance");
      return false;
    }
    try {
      newAccount.addTransactionGroup("1 27 -30 50 100");
    } catch (DataFormatException e) {
      e.printStackTrace();
    }
    currentBalance = newAccount.getCurrentBalance();
    if (currentBalance != (150)) {

      System.out.println("FAILURE TEST #2 @testAccountBalance");
      return false;
    }
    try {
      newAccount.addTransactionGroup("2 2 1 1 0");
    } catch (DataFormatException e) {
      e.printStackTrace();
    }
    currentBalance = newAccount.getCurrentBalance();
    if (currentBalance != (-10)) {
      System.out.println("FAILURE TEST #3 @testAccountBalance");
      return false;
    }
    return true;
  }

  public static boolean testOverdraftCount() {
    Account newAccount = new Account("user");
    try {
      newAccount.addTransactionGroup("0 1 0 1 0 0 1 1 1 1");
    } catch (DataFormatException e) {
      e.printStackTrace();
    }

    int overdrafts = newAccount.getNumberOfOverdrafts();
    if (overdrafts != (1)) {
      System.out.println("FAILURE TEST #1 @testOverdraftCount");
      return false;
    }
    try {
      newAccount.addTransactionGroup("1 27 -31 50 100");
    } catch (DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    overdrafts = newAccount.getNumberOfOverdrafts();
    if (overdrafts != (2)) {

      System.out.println("FAILURE TEST #2 @testOverdraftCount");
      return false;
    }
    try {
      newAccount.addTransactionGroup("2 2 1 1 0");
    } catch (DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    overdrafts = newAccount.getNumberOfOverdrafts();
    if (overdrafts != (3)) {
      System.out.println("FAILURE TEST #3 @testOverdraftCount");
      return false;
    }
    return true;
  }

  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when it is passed an empty int[].
   * 
   * @param empty is a new Transaction group having a null array or say empty transaction Array.
   * @return true when test verifies correct functionality, and false otherwise.
   * 
   */
  public static boolean testTransactionGroupEmpty() {
    int[] a = null;
    try {
      TransactionGroup empty = new TransactionGroup(a);
    } catch (DataFormatException e) {
      e.printStackTrace();
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the TransactionGroup constructor throws an exception with an
   * appropriate message, when then first int in the provided array is not 0, 1, or 2.
   * 
   * @param invalidTransactions is the array which doesn't start with first int as 0,1 or 2.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testTransactionGroupInvalidEncoding() {

    int[] invalidTransactions = {3, 4, 5};
    try {
      TransactionGroup invalidGroup = new TransactionGroup(invalidTransactions); // A transaction
                                                                                 // group which
                                                                                 // starts with 3
                                                                                 // instead of 0,1
                                                                                 // or 2.
    } catch (DataFormatException e) {
      e.printStackTrace();
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a quick withdraw encoded group that contains negative
   * numbers of withdraws.
   * 
   * @invalidWithdraw is the account which has a quickWithdraw transaction group added with a
   *                  negative number int it, which is not allowed for quickWithdraw transactions.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountAddNegativeQuickWithdraw() {
    Account invalidWithdraw = new Account("user");

    try {
      invalidWithdraw.addTransactionGroup("2 -2 0 5 0"); // A transaction group having negative
                                                         // number in it.
    } catch (DataFormatException e) {
      e.printStackTrace();
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the Account.addTransactionGroup method throws an exception with an
   * appropriate message, when it is passed a string with space separated English words (non-int).
   * 
   * @param invalidString is the transaction group which has string characters in it.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountBadTransactionGroup() {
    Account invalidString = new Account("user");

    try {
      invalidString.addTransactionGroup("0 2 invalid 23 string j"); // a transaction group having
                                                                    // string characters in it.
    } catch (DataFormatException e) {
      e.printStackTrace();
      return true;
    }
    return false;
  }

  /**
   * This method checks whether the Account.getTransactionAmount method throws an exception with an
   * appropriate message, when it is passed an index that is out of bounds.
   * 
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountIndexOutOfBounds() {
    Account test = new Account("user");
    try {
      test.addTransactionGroup("1 2 3 4"); // Maximum index for this transaction group is 3.
      test.getTransactionAmount(4); // 4 is not a valid index.
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
      return true;
    } catch (OutOfMemoryError e) {
      e.printStackTrace();
    } catch (DataFormatException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * This method checks whether the Account constructor that takes a File parameter throws an
   * exception with an appropriate message, when it is passed a File object that does not correspond
   * to an actual file within the file system.
   * @param a is a non-existant file.
   * @return true when test verifies correct functionality, and false otherwise.
   */
  public static boolean testAccountMissingFile() {
    File a = new File("abcd"); // This file doesnt exist.
    try {
      Account test = new Account(a);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return true;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;
  }
}
