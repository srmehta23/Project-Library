
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
 * This class is used to create TransactionGroups and get their count and amount of the transaction
 * at each index.
 */

import java.util.zip.DataFormatException;

public class TransactionGroup {
  private enum EncodingType {
    BINARY_AMOUNT, INTEGER_AMOUNT, QUICK_WITHDRAW
  };

  private EncodingType type;
  private int[] values;

  /*
   * Constructor method for the TransactionGroup class
   * 
   * @param groupEncoding is the provided array containg the type of transaction at index 0 and the
   * transaction amounts in the rest of the array.
   * 
   * @param type represents the type of transaction from [BINARY-AMOUNT, INTEGER_AMOUNT AND
   * [QUICK_WITHDRAW].
   * 
   * @param values is an array containing the transaction amount for each transaction.
   */
  public TransactionGroup(int[] groupEncoding) throws DataFormatException {

    if (groupEncoding == null) { // Checks if the provided array is null

      throw new DataFormatException("transaction group encoding cannot be null or empty");
    }
    if (groupEncoding[0] < 0 || groupEncoding[0] > 2) { // checks if the first index of provided
                                                        // array is anything else then 0,1 or 2.
      throw new DataFormatException(
          "the first element within a transaction group must be 0, 1, or 2");
    }

    this.type = EncodingType.values()[groupEncoding[0]]; // As the first index is not empty and it
                                                         // is 0,1 or 2. We check that it refers to
                                                         // which transaction type.

    if (this.type.name().equals("BINARY_AMOUNT")) { // Checks if it's a binary amount transaction.
      for (int i = 1; i < groupEncoding.length; i++) {
        if (groupEncoding[i] != 0 && groupEncoding[i] != 1) { // checks if the contents of the
                                                              // array are anything else except 0
                                                              // and 1.
          // are anything else except 1 or 0.
          throw new DataFormatException(
              "binary amount transaction groups may only contain 0s " + "and 1s");
        }
      }
    }
    if (this.type.name().equals("INTEGER_AMOUNT")) {
      for (int i = 1; i < groupEncoding.length; i++) {
        if (groupEncoding[i] == 0) { // Checks if there is a zero in INTEGER_AMOUNT transaction.
          throw new DataFormatException("integer amount transaction groups may not contain 0s");
        }
      }
    }
    if (this.type.name().equals("QUICK_WITHDRAW")) {
      if (groupEncoding.length != 5) { // checks the size of a quick withdraw transaction group
                                       // which must be 5.
        throw new DataFormatException("quick withdraw transaction groups must contain 5 elements");
      }
      for (int i = 1; i < groupEncoding.length; i++) {
        if (groupEncoding[i] < 0) { // checks if there's a negative number in the transaction group
                                    // of a quickWithdraw transaction.
          throw new DataFormatException(
              "quick withdraw transaction groups may not contain negative numbers");
        }
      }
    }

    this.values = new int[groupEncoding.length - 1];
    for (int i = 0; i < values.length; i++) { // if there are no exceptions then the transaction
                                              // amounts are stored into the integer type array
                                              // "values".
      this.values[i] = groupEncoding[i + 1];
    }
  }

  /*
   * This method is used to maintain the transaction count or say the number of transactions.
   * 
   * @transactionCount is the counter for the number of transactions.
   * 
   * @lastAmount denotes the end of transactions for a BINARY_AMOUNT array.
   * 
   */
  public int getTransactionCount() {
    int transactionCount = 0;
    switch (this.type) {
      case BINARY_AMOUNT:
        int lastAmount = -1;
        for (int i = 0; i < this.values.length; i++) {
          if (this.values[i] != lastAmount) { // this if condition is to check the number of
                                              // repeated transactions, because all the
                                              // consecutively repeated transactions are counted as
                                              // 1 for BINARY_AMOUNT transactions.
            transactionCount++;
            lastAmount = this.values[i];
          }
        }
        break;
      case INTEGER_AMOUNT:
        transactionCount = values.length; // The no. of transactions is same as the length of values
                                          // array which contains the transaction amount.
        break;
      case QUICK_WITHDRAW:
        for (int i = 0; i < this.values.length; i++)
          transactionCount += this.values[i]; // The number stored at each index is the no. of
                                              // withdraws of a certain amount, therefore we add all
                                              // numbers at each index to find the transactionCount.
    }
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
   * 
   */

  public int getTransactionAmount(int transactionIndex) throws IndexOutOfBoundsException {

    if (transactionIndex > getTransactionCount()) { // checks if the provided transactionIndex
                                                    // exceeds
                                                    // the limit of array.
      throw new IndexOutOfBoundsException(
          "java.lang.ArrayIndexOutOfBoundsException: " + transactionIndex
              + " ,total number of transaction indexes that can be accessed from the array are: "
              + getTransactionCount());
    }

    int transactionCount = 0;

    switch (this.type) {
      case BINARY_AMOUNT:
        int lastAmount = -1;
        int amountCount = 0;
        for (int i = 0; i <= this.values.length; i++) {
          if (i == this.values.length || this.values[i] != lastAmount) { // Short-circuit

            if (transactionCount - 1 == transactionIndex) {
              if (lastAmount == 0) { // if the last amount was zero then we withdraw 1$.
                return -1 * amountCount; // as the same consecutive transactions are considered as
                                         // one, the amountCount is multiplied to get the
                                         // transactionAmount.
              } else // if the last amount was one then we deposit 1$.
                return +1 * amountCount;
            }
            transactionCount++;
            lastAmount = this.values[i];
            amountCount = 1;
          } else
            amountCount++; // adds the amount count if there are more than one consecutively same
                           // transactions i.e consecutive 1's or 0's.
          lastAmount = this.values[i];
        }
        break;

      case INTEGER_AMOUNT:
        return this.values[transactionIndex];

      case QUICK_WITHDRAW:
        final int[] QW_AMOUNTS = new int[] {-20, -40, -80, -100};
        for (int i = 0; i < this.values.length; i++)
          for (int j = 0; j < this.values[i]; j++) // this loops according to the number mentioned
                                                   // in the transactionsGroup. Therefore for 3 it
                                                   // will withdraw thrice.
            if (transactionCount == transactionIndex)
              return QW_AMOUNTS[i];
            else
              transactionCount++;
    }
    return -1;
  }
}
