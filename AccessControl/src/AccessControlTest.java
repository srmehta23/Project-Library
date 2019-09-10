//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: CS300 P3
// Files: n/a
// Course: CS300 FALL 2018
//
// Author: Shubham Mehta
// Email: smehta23@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Rachel hon
// Partner Email: khon@wisc.edu
// Partner Lecturer's Name: Gary Dahl
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
 * This class tests the methods from AccessControl class. It tests isValidLogin(), addUser(),
 * removeUser(), giveAdmin(), takeAdmin(), changePassword() and resetPassword().
 */
public class AccessControlTest {
  /*
   * Testing main. Runs each test and prints which (if any) failed.
   */
  public static void main(String[] args) {

    int fails = 0;
    if (!testLogin1()) {
      System.out.println("testLogin1 [bad username] failed");
      fails++;
    }
    if (!testLogin2()) {
      System.out.println("testLogin2 [good login] failed");
      fails++;
    }
    if (!testLogin3()) {
      System.out.println("testLogin1 [bad username with default password] failed");
      fails++;
    }
    if (!testAddUser1()) {
      System.out.println("testAddUser1 failed: [User is added even when there is no admin");
      fails++;
    }
    if (!testAddUser2()) {
      System.out.println("testAddUser2 failed: [User is not added when admin attempts to");
      fails++;
    }
    if (!testRemoveUser1()) {
      System.out.println("testRemoveUser1 failed: [User is not removed when admin attempts to");
      fails++;
    }
    if (!testRemoveUser2()) {
      System.out
          .println("testRemoveUser2 failed: [User is removed when the currentUser is not admin");
      fails++;
    }
    if (!testGiveAdmin()) {
      System.out.println("testGiveAdmin failed: [User did not become an Admin");
      fails++;
    }
    if (!testTakeAdmin()) {
      System.out.println("testTakeAdmin failed: [Admin did not become a user");
      fails++;
    }
    if (!testChangePassword()) {
      System.out.println("testChangePassword() failed: [System still recognize old password "
          + "or does not aceept new password]");
      fails++;
    }
    if (!testResetPassword()) {
      System.out
          .println("testResetPssword() failed: [Password does not reset to DEFAULT_PASSWORD]");
      fails++;
    }

    if (fails == 0) {
      System.out.println("All tests passed!");
    } else {
      System.out.print("Numbe of tests failed: " + fails + "!");
    }
  }

  /*
   * This test tries to log in a user that doesn't exist
   * 
   * @param user user that has not been saved
   * 
   * @param pw user password
   * 
   * @return boolean test passed
   */
  public static boolean testLogin1() {
    AccessControl ac = new AccessControl();
    String user = "probablyNotInTheSystem1234";
    String pw = "password";
    return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
  }

  /*
   * This test tries to log in as a default admin and its default password
   * 
   * @param user default admin
   * 
   * @param pw admin password
   * 
   * @return boolean test passed
   */
  public static boolean testLogin2() {
    AccessControl ac = new AccessControl();
    String user = "admin";
    String pw = "root";
    return AccessControl.isValidLogin(user, pw);
  }

  /*
   * This test tries to log in a user that doesn't exist with a DEFAULT PASSWORD
   * 
   * @param user bad username that has not been saved
   * 
   * @param DEFAULT_PASSWORD user password
   * 
   * @return boolean test passed
   */
  public static boolean testLogin3() {
    AccessControl ac = new AccessControl();
    String user = "probablyNotInTheSystem1234"; // bad username that is not saved
    String DEFAULT_PASSWORD = "1234000";
    return !AccessControl.isValidLogin(user, DEFAULT_PASSWORD); // isValidLogin should return false
  }

  /*
   * Create a new AccessControl and do not log in an admin. Verify that addUser(String username)
   * returns false and that the new user is not added.
   * 
   * @param "admin" default admin
   * 
   * @param user user that failed to be added later in this method
   * 
   * @param DEFAULT_PASSWORD user password
   * 
   * @return boolean test passed
   */
  public static boolean testAddUser1() {
    AccessControl ac = new AccessControl();
    String user = "alexi";
    String DEFAULT_PASSWORD = "1234000";
    boolean addUserReport = ac.addUser(user);
    if (addUserReport)
      return false; // addUserReport should be false
    // Make sure user wasn't added anyway
    return !AccessControl.isValidLogin(user, DEFAULT_PASSWORD);
  }

  /*
   * Create a new AccessControl that log in an admin. Verify that addUser(String username) returns
   * true properly and that the new user is added.
   * 
   * @param "admin" default admin
   * 
   * @param user user that will be added later through this method
   * 
   * @param DEFAULT_PASSWORD user password
   * 
   * @return boolean test passed
   */
  public static boolean testAddUser2() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");

    String user = "alexi";
    String DEFAULT_PASSWORD = "1234000";

    boolean addUserReport = ac.addUser(user); // save user in ArrayList
    if (addUserReport) {
      return true;
    } else {
      return !AccessControl.isValidLogin(user, DEFAULT_PASSWORD);
    }
  }

  /*
   * Create a new AccessControl that log in an admin. Verify that removeUser(String username)
   * returns true properly and that the the user is removed and cannot login
   * 
   * @param "admin" default admin
   * 
   * @param user user that will be removed later through this method
   * 
   * @param DEFAULT_PASSWORD user password
   * 
   * @return boolean test passed
   */
  public static boolean testRemoveUser1() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");

    String user = "alexi";
    String DEFAULT_PASSWORD = "1234000";
    ac.addUser(user); // saved a user in the ArrayList to remove it later

    ac.removeUser(user);
    if (!AccessControl.isValidLogin(user, DEFAULT_PASSWORD)) {// isValidLogin should be false
      // Make sure user is removed (can no longer login)
      return true;
    } else {
      return false;
    }
  }

  /*
   * Create a new AccessControl and do not log in an admin. Verify that removeUser(String username)
   * returns false and that the user is not removed and reutrn false
   * 
   * @param "admin" default admin
   * 
   * @param user user that fail to attempt using admin power
   * 
   * @return boolean test passed
   */
  public static boolean testRemoveUser2() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");
    String user = "alexi";
    ac.addUser(user);

    ac.setCurrentUser("alexi"); // set non-admin user as currentTser
    if (!ac.removeUser("admin")) { // removeUser should be false
      // Make sure user is not removed when an non-Admin excute the code
      return true;
    } else {
      return false;
    }
  }

  /*
   * Create a new AccessControl and log in an admin. Verify that giveAdmin(alexi) returns true by
   * testing whether the new-Admin has a power of removing user and that the user should be removed
   * and reutrn true
   * 
   * @param "admin" default admin
   * 
   * @param user user to be changed to admin later through this method
   * 
   * @return boolean test passed
   */
  public static boolean testGiveAdmin() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");

    String user = "alexi";
    ac.addUser(user);

    ac.giveAdmin(user); // delegate admin power to ordinary user

    if (ac.removeUser("admin")) {
      return true;
    } else {
      return false;
    }
  }

  /*
   * Create a new AccessControl and do not log in an admin. Verify that takeAdmin(alexi) returns
   * true by testing whether the new-user has a power of removing user and that the user should not
   * be removed and reutrn false
   * 
   * @param "admin" default admin
   * 
   * @return boolean test passed
   */
  public static boolean testTakeAdmin() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");
    ac.takeAdmin("admin"); // change admin to non-admin user

    if (!ac.removeUser("admin")) {// removeUser should be false
      // Make sure user is not removed when an non-Admin excute the code
      return true;
    } else {
      return false;
    }
  }

  /*
   * Create a new AccessControl and log in an admin. Verify that ChangePassword(newPass) returns
   * true and that the the new password should be recognized and the old password should not be
   * accepted
   * 
   * @param "admin" default admin
   * 
   * @param oldPass admin's password before changing
   * 
   * @param newPass password after changing
   * 
   * @return boolean test passed
   */
  public static boolean testChangePassword() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");
    String oldPass = "root";
    String newPass = "michael1234";

    ac.changePassword(newPass);
    if (AccessControl.isValidLogin("admin", newPass) == true && // new password should be recognized
        AccessControl.isValidLogin("admin", oldPass) == false) { // old password should not be
                                                                 // accepted
      return true;
    } else {
      return false;
    }

  }

  /*
   * Create a new AccessControl and log in an admin. Verify that resetPassword(admin) returns true
   * and that the original password should not be accepted and the DEFAULT_PASSWORD should be
   * recognized
   * 
   * @param "admin" default admin
   * 
   * @param orginalPass admin's password before reset
   * 
   * @param DEFAULT_PASSWORD password after reset
   * 
   * @return boolean test passed
   */
  public static boolean testResetPassword() {
    AccessControl ac = new AccessControl();
    ac.setCurrentUser("admin");

    String originalPass = "root";
    String DEFAULT_PASSWORD = "1234000";

    ac.resetPassword("admin");

    if (AccessControl.isValidLogin("admin", originalPass) == false && // original password should
                                                                      // not be accepted
        AccessControl.isValidLogin("admin", DEFAULT_PASSWORD)) { // DEFAULT_PASSWORD should be
                                                                 // recognized
      return true;
    }
    return false;

  }

}
