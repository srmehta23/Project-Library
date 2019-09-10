
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
 * Account class can be used to create transactionGroups for each user and get the transactionCount
 * and amount, it can also be used to calclate the current balance of the account or the number of
 * overdrafts that have occured.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Account {

  private static final int MAX_GROUPS = 10000;
  private static int nextUniqueId = 1000;
  private String name;
  private final int UNIQUE_ID;
  private TransactionGroup[] transactionGroups;
  private int transactionGroupsCount;

  /*
   * This is the constructor method for Account class.
   * 
   * @param name is the name of the person whose account it is.
   * 
   * @param UNIQUE_ID is the Unique ID no. for each account or say person.
   * 
   * @param transactionGroups is the array containing the transaction type as well as the
   * transaction amounts.
   * 
   */
  public Account(String name) {

    this.name = name;
    this.UNIQUE_ID = Account.nextUniqueId;
    Account.nextUniqueId++;
    this.transactionGroups = new TransactionGroup[MAX_GROUPS];
    this.transactionGroupsCount = 0;
  }

  /*
   * This is another constructor method for Account class. it takes input from a file provided in
   * the parameter.
   * 
   * @param name is the name of the person whose account it is.
   * 
   * @param UNIQUE_ID is the Unique ID no. for each account or say person.
   * 
   * @param transactionGroups is the array containing the transaction type as well as the
   * transaction amounts.
   * 
   * 
   */
  public Account(File file) throws FileNotFoundException, Exception {

    Scanner in = new Scanner(file);
    this.name = in.nextLine();
    this.UNIQUE_ID = Integer.parseInt(in.nextLine());
    Account.nextUniqueId = this.UNIQUE_ID + 1;
    this.transactionGroups = new TransactionGroup[MAX_GROUPS];
    this.transactionGroupsCount = 0;
    String nextLine = "";
    while (in.hasNextLine()) {
      try {
        this.addTransactionGroup(in.nextLine()); // addTransactionGroup can throw a
                                                 // DataFormatException.
      } catch (DataFormatException e) {
        System.out.println(e.getMessage());
      }
        catch(Exception e) {
          throw e;
        }
    }
    in.close();
  }

  /*
   * This method is used to get the UNIQUE_ID each user.
   * 
   * @return UNIQUE_ID of this instance of Account class or say for the current user.
   */
  public int getId() {

    return this.UNIQUE_ID;
  }

  /*
   * This method is used to initialise a transaction group from a string and then add it to the
   * transactionGroups[] array.
   * 
   * @param command is the string which contains the transaction type and the transactions.
   * 
   * @param newTransactions is the integer representation of the string command in an array.
   * 
   * @param transactionCount is the counter for the number of transactions added.
   * 
   */
  public void addTransactionGroup(String command) throws DataFormatException, OutOfMemoryError {

    if (this.getTransactionCount() > MAX_GROUPS) { // if the transactionCount is greater than
                                                   // MAX_GROUPS size then an OutOfMemoryError is
                                                   // thrown.

      throw new OutOfMemoryError("The capacity of Accounts internal storage is: " + MAX_GROUPS);
    }

    try {
      String[] parts = command.split(" ");

      int[] newTransactions = new int[parts.length];

      for (int i = 0; i < parts.length; i++) { // This loop is used to convert the whole string
                                               // array to an integer array. If the string contain
                                               // anything except space seperated integers then it
                                               // throws an error.
        newTransactions[i] = Integer.parseInt(parts[i]);
      }

      TransactionGroup t = new TransactionGroup(newTransactions);
      this.transactionGroups[this.transactionGroupsCount] = t;
      this.transactionGroupsCount++;

    } catch (NumberFormatException e) {
      throw new DataFormatException(
          "addTransactionGroup requires string commands that contain only space separated "
              + "integer values");
    }
  }

  /*
   * This method is used to get the total number of transactions for the current instance of account
   * or say the current user.
   * @return transactionCount is the counter for the number of transactions.
   */
  public int getTransactionCount() {

    int transactionCount = 0;
    for (int i = 0; i < this.transactionGroupsCount; i++)
      transactionCount += this.transactionGroups[i].getTransactionCount(); // getTransactionCount
                                                                           // returns the number of
                                                                           // transactions present
                                                                           // in the current
                                                                           // transaction group,
                                                                           // such that it returns
                                                                           // number of transactions
                                                                           // for each
                                                                           // transactionGroup and
                                                                           // adds it all to
                                                                           // calculate the total
                                                                           // transactionCount.
    return transactionCount;
  }

  /*
   * This method returns the transaction amount at the specified index in values array.
   * 
   * @param lastAmount is the Last Amount which the loop was on.
   * 
   * @param amountCount keeps track of the consecutive same transactionAmount for binary amount
   * transactions, so that they can be multiplied with 1 or -1 to get the total amount.
   * 
   * @throws IndexOutOfBoundsException if the provided index doesnt fall within the range of valid
   * indexes for the array values.
   */
  public int getTransactionAmount(int index) throws IndexOutOfBoundsException {
    int transactionCount = 0;

    if (index > getTransactionCount()) {
      throw new IndexOutOfBoundsException(  "java.lang.ArrayIndexOutOfBoundsException: " + index
          + " ,total number of transaction indexes that can be accessed from the array are: "
          + this.transactionGroupsCount);
    }
    for (int i = 0; i < this.transactionGroupsCount; i++) { // this loop is used to get
                                                            // transactionAmount at each index of
                                                            // each transactionGroup.
      int prevTransactionCount = transactionCount;
      transactionCount += this.transactionGroups[i].getTransactionCount();
      if (transactionCount > index) { // index gets bigger as we go through the whole
                                      // transactionGroup at each index, therefore we subtract the
                                      // prevTransactionCount from the index to go to the next index
                                      // which refers to another transactionGroup.
        index -= prevTransactionCount;
        return this.transactionGroups[i].getTransactionAmount(index);
      }
    }
    return -1;
  }

  /*
   * This method calculates the current balance or say the total balance after taking into account
   * all the transactions.
   * 
   * @size is the total number of transactions.
   * 
   * @return balance the total balance after considering all the transactions.
   */
  public int getCurrentBalance() {
    int balance = 0;
    int size = this.getTransactionCount();
    for (int i = 0; i < size; i++) {
      try {
        balance += this.getTransactionAmount(i); // this returns the transactionAmount at each index
                                                 // and adds it to the balance in order to calculate
                                                 // the total balance.
      } catch (IndexOutOfBoundsException e) {
        System.out.println(e.getMessage());
      }
    }
    return balance;
  }

  /*
   * This method counts the number of overdrafts or say the number of times the account went
   * negative.
   * 
   * @param balance is the current balance.
   * 
   * @return overdraftCount is the counter for number of overdrafts.
   * 
   */
  public int getNumberOfOverdrafts() {
    int balance = 0;
    int overdraftCount = 0;
    int size = this.getTransactionCount();

    for (int i = 0; i < size; i++) { // to calculate current balance.
      int amount = this.getTransactionAmount(i);
      balance += amount;
      if (balance < 0 && amount < 0) // if current balance is less than 0, then the number of
                                     // overdrafts
                                     // increases by 1.
        overdraftCount++;
    }

    return overdraftCount;
  }

}
