
public class AuditableBankingTests {
  public static void main(String[] args) {
    testProcessCommand();
    testCalculateNumberOfOverdrafts();
    testCalculateCurrentBalance();
  }

  public static boolean testProcessCommand() {
    int[][] allTransactions = new int[5][5];
    allTransactions[0] = new int[] {1, 2, 3, 4, 5, 6};
    allTransactions[1] = new int[] {0, 1, 0, 0, 0, 1};

    String command = "0 1 0 1 1 1";
    int result = AuditableBanking.processCommand(command, allTransactions, 2);
    if (result != 3) {
      System.out.println("FALSE Test #1");
      return false;
    }

    command = "3 5 6 8 9";
    result = AuditableBanking.processCommand(command, allTransactions, 3);
    if (result != 3) {
      System.out.println("FALSE Test #2");
      return false;
    }

    command = "2 3 1 3 4 6 7 8 9 4 3 2 1";
    result = AuditableBanking.processCommand(command, allTransactions, 3);
    if (result != 4) {
      System.out.println("FALSE Test #3");
      return false;
    }

    command = " 1 4 3 2 4    ";
    result = AuditableBanking.processCommand(command, allTransactions, 4);
    if (result != 5) {
      System.out.println("FALSE Test #4");
      return false;
    }
    return true;

  }

  public static boolean testCalculateNumberOfOverdrafts() {
    boolean foundProblem = false;
    int[][] transactions = new int[][] {{1, 10, -20, +30, -20, -20}, // +2 overdrafts (ending
                                                                     // balance: -20)
        {0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // +2 overdrafts (ending balance: -15)
        {1, 115}, // +0 overdrafts (ending balance: +100)
        {2, 3, 1, 0, 1}, // +1 overdrafts (ending balance: -100)
    };
    // test with a single transaction of the Integer Amount encoding
    int transactionCount = 1;
    int overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
    if (overdrafts != 2) {
      System.out.println(
          "FAILURE: calculateNumberOfOverdrafts did not return 2 when transactionCount = 1, and transactions contained: ");
      foundProblem = true;
    } else {
      System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts!!!");
    }
    // test with four transactions: including one of each encoding
    transactionCount = 4;
    overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions, transactionCount);
    if (overdrafts != 4) {
      System.out.println(
          "FAILURE: calculateNumberOfOverdrafts did not return 5 when transactionCount = 4, and transactions contained: ");
      foundProblem = true;
      System.out.println(!foundProblem);
    } else {
      System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts!!!");
    }
    return !foundProblem;
  }

  public static boolean testCalculateCurrentBalance() {
    // TODO: Implement this method
    boolean foundProblem = false;
    int[][] transactions = new int[][] {{1, 10, -20, +30, -20, -20}, // +2 overdrafts (ending
                                                                     // balance: -20)
        {0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // +2 overdrafts (ending balance: -15)
        {1, 115}, // +0 overdrafts (ending balance: +100)
        {2, 3, 1, 0, 1}, // +1 overdrafts (ending balance: -100)
    };
    // test with a single transaction of the Integer Amount encoding
    int transactionCount = 1;
    int currentBalance = AuditableBanking.calculateCurrentBalance(transactions, transactionCount);
    if (currentBalance != -20) {
      System.out.print(currentBalance);
      System.out.println(
          "FAILURE: calculateCurrentBalance did not return -20 when transactionCount = 1, and transactions contained: ");
      foundProblem = true;
    } else {
      System.out.println("PASSED TEST 1/2 of TestcalculateCurrentBalance!!!");
    }
    // test with four transactions: including one of each encoding
    transactionCount = 4;
    currentBalance = AuditableBanking.calculateCurrentBalance(transactions, transactionCount);
    if (currentBalance != -100) {
      System.out.print(currentBalance);
      System.out.println(
          "FAILURE: calculateCurrentBalance did not return -100 when transactionCount = 4, and transactions contained: ");
      foundProblem = true;
      System.out.println(!foundProblem);
    } else {
      System.out.println("PASSED TESTS 2/2 of TestcalculateCurrentBalance!!!");
    }
    return !foundProblem;

  }
}
