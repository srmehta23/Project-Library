
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////

//
// Title: AccessControl controls the mechanism of loggging in by different users and also gives them
// access to user related functions.

// Files: N/a
// Course: CS 300
//
// Author: Shubham Mehta
// Email: smehta23@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Rachel hon
// Partner Email: khon@wisc.edu
// Partner Lecturer's Name: Gary dahl
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
 * AccessControl class is used to control the access of an user. The user firstly logs in and then
 * he can perform certain commands pertaining to his admin status. It also checks that the correct
 * user has logged in and provides user with commands such as logout,adduser,change password, remove
 * user, rest password, give admin power and remove admin power. but the ability to use these
 * commands depends on the admin status of the current user.
 * 
 * @param users is the list containing all the valid users.
 * 
 * @param currentUser is the user currently logged into the system.
 * 
 * @param DEFAULT_PASSWORD is the default password given to new users or when we reset the password
 * of an user.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class AccessControl {
  private static ArrayList<User> users; // An ArrayList of valid users.
  private User currentUser; // Who is currently logged in, if anyone?
  private static final String DEFAULT_PASSWORD = "1234000"; // Default password given to
                                                            // new users or when we reset a user's
                                                            // password.

  /*
   * Main method launches an instance of AccessControl class.
   */
  public static void main(String[] args) {
    AccessControl ac = new AccessControl(); // If we have any persistent information to lead
                                            // this is where we load it.
    Scanner userIn = new Scanner(System.in);
    // create access control object

    ac.loginScreen(userIn);
  }

  /*
   * This is the constructor method for AccessControl Class. therefore, it initialises the required
   * fields when an object of AccessControl class is created.
   * 
   * @param currentUser is the user currently logged in.
   * 
   * @param users is the arraylist containing the name of all the valid users
   * 
   * @param admin is the default user whose isAdmin is true.
   */
  public AccessControl() {// A no-parameter constructor
    currentUser = null;

    users = new ArrayList<User>(); // An ArrayList of type users, which will constitute all the
                                   // newly added users.
    User admin = new User("admin", "root", true); // Creates a default admin user whose isAdmin is
                                                  // true and adds it to the list.
    users.add(admin);
  }

  /*
   * This method checks that whether the given username password pair matches with any of the
   * existing user in the list of users.
   * 
   * @param usernameInList this is the currentUsername the list is on , the loop tries to compare
   * currentUsername with the provided userName.
   * 
   * @return checkPassword Returns true if the given username and password matches with an existing
   * user.
   */
  public static boolean isValidLogin(String username, String password) {
    String usernameInList;

    boolean checkPassword = true;

    for (int i = 0; i < users.size(); i++) { // This loop goes through each username in the
                                             // Arraylist of users, and checks that if the username
                                             // provided to the method matches the name of any user
                                             // in the list.

      usernameInList = users.get(i).getUsername();

      if (username.equals(usernameInList)) { // This checks that whether the currentUsername in the
                                             // list is same as the provided usernname.

        checkPassword = users.get(i).isValidLogin(password); // calls the isValidLogin method of
                                                             // user class to check whether the
                                                             // password is correct or not.
        if (checkPassword == true) {

          System.out.println("This is a valid user.");
          return checkPassword; // This returns true if the password matches with an user's
                                // password.
        }
      } else if (i == users.size() - 1) {
        return !checkPassword;
      }
    }
    return checkPassword;
  }

  /*
   * This method is used by a currently logged in user to change his/her password
   * 
   * @param newPassword is the new password provided for the current user.
   */
  public void changePassword(String newPassword) { // Change the current user's password
    currentUser.setPassword(newPassword); // setPassword method
    System.out.println("Password changed ");

  }

  /*
   * This method is used by a currently logged in user to log out of the system. As the user logs
   * out the currentUser field becomes null.
   * 
   * @param currentUser is the user who is currently logged in.
   */
  public void logout() {// Log out the current user
    currentUser = null;
    System.out.println("Logged Out");
  }

  /*
   * This method is used to set the currently logged in user as the current user. This is a mutator
   * method used to write tests.
   * 
   * @param username is the username you want to set as the currentUser or say to set as the user
   * who is currently logged in.
   */
  public void setCurrentUser(String username) {
    for (int i = 0; i < users.size(); i++) {
      if (username.equals(users.get(i).getUsername())) { // If the given username matches any
                                                         // username in the list of users, then it
                                                         // is set to be the current user, the user
                                                         // who has logged in.
        currentUser = users.get(i);

        System.out.println("current user is :" + users.get(i).getUsername());
      }
    }
  }

  /*
   * This method Create a new user With the default password and isAdmin==false and adds it to the
   * list of users. This method will add a user only if the currentUser who is adding the user is a
   * admin and the name of newly added user doesnt match with an exisiting user.
   * 
   * @param currentUser is the user currently logged in.
   * 
   * @param newUser is the user being added to the list of users.
   * 
   * @return added returns true if the user is added successfully.
   */
  public boolean addUser(String username) {
    boolean added = true;
    if (currentUser == null) { // Checks if the currentUser is null.
      return !added;
    }
    if (currentUser.getIsAdmin() == true) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) { // This loop checks whether the provided
                                                           // username matches with any username
                                                           // already existing in the list
          return !added;
        }
      }
      User newUser = new User(username, DEFAULT_PASSWORD, false); // New user with isAdmin == false
                                                                  // and Default passwords.
      users.add(newUser);
      System.out.println("New user added: " + username);

      return added;
    } else {
      return !added;
    }
  }
  /*
   * This method Create a new user With the default password and the current user specifies their
   * admin status and adds it to the list of users. This method will add a user only if the
   * currentUser who is adding the user is a admin and the name of newly added user does not match
   * with an exisiting user in the list.
   * 
   * @param currentUser is the user currently logged in.
   * 
   * @param newUser is the user being added to the list of users.
   * 
   * @return added returns true if the user is added successfully.
   */

  public boolean addUser(String username, boolean isAdmin) { // Create a new user
                                                             // and specify their admin status
    boolean added = true;
    if (currentUser == null) { // Checks if the currentUser is null.
      return !added;
    }
    if (currentUser.getIsAdmin() == true) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) { // This loop checks whether the provided
                                                           // username matches with any username
                                                           // already existing in the list
          return !added;
        }
      }

      User newUser = new User(username, DEFAULT_PASSWORD, isAdmin); // New user is created with the
                                                                    // specified isAdmin status
                                                                    // and Default passwords.
      users.add(newUser);
      System.out.println("New user added: " + username);
      return added;
    } else {
      return !added;
    }
  }
  /*
   * This method is used to remove an user from the list of users. To remove an user the currentUser
   * must be an admin and the user to be removed should have a unique name.
   * 
   * @param username Name of the user who has to be removed.
   * 
   * @param currentUser is the user currently logged in.
   * 
   * @return removed returns true if the user is removed successfully.
   */

  public boolean removeUser(String username) { // Remove a user (names should be unique)
    boolean removed = true;
    if (currentUser == null) { // Checks if the currentUser is null.
      return !removed;
    }
    if (currentUser.getIsAdmin() == true) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) { // This loop checks whether the provided
                                                           // username matches with any username
                                                           // already existing in the list
          users.remove(i); // Removes the user for the corresponding index in the ArrayList whose
                           // name matches with the provided name.
          return removed;
        } else if (i == (users.size() - 1)) {// if the provided name matches with no one in the list
                                             // then false is returned.
          return !removed;
        }
      }
    }
    return false;
  }

  /*
   * This method is used to give the power of admin to an user. To give admin power to an user the
   * currentUser must be an admin.
   * 
   * @param username Name of the user who gets the admin power.
   * 
   * @param currentUser is the user currently logged in.
   * 
   * @return adminPower returns true if the user becomes admin successfully.
   */
  public boolean giveAdmin(String username) { // Give a user admin power
    boolean adminPower = true;
    if (currentUser == null) { // Checks if the currentUser is null.
      return !adminPower;
    }
    if (currentUser.getIsAdmin() == true) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {// This loop checks whether the provided
                                                          // username matches with any username
                                                          // already existing in the list
          users.get(i).setIsAdmin(true); // admin power is given to the user whose name in the list
                                         // matches with the provided name.

          return adminPower;
        } else if (i == users.size() - 1) {// if the provided name matches with no one in the list
                                           // then false is returned.
          return !adminPower;
        }
      }
    }
    return !adminPower;

  }

  /*
   * This method is used to take the power of admin from an user. To take admin power from an user
   * the currentUser must be an admin.
   * 
   * @param username Name of the user who gets the admin power.
   * 
   * @param currentUser is the user currently logged in.
   * 
   * @return adminPower returns true if the user becomes admin successfully.
   */
  public boolean takeAdmin(String username) { // Remove a user's admin power
    boolean adminPower = true;
    if (currentUser == null) { // Checks if the currentUser is null.
      return !adminPower;
    }
    if (currentUser.getIsAdmin() == true) {
      for (int i = 0; i < users.size(); i++) {// This loop checks whether the provided
                                              // username matches with any username
                                              // already existing in the list
        if (users.get(i).getUsername().equals(username)) {

          users.get(i).setIsAdmin(false); // admin power is taken away from the user whose name in
                                          // the list matches with the provided name.
          return adminPower;
        } else if (i == users.size() - 1) { // if the provided name matches with no one in the list
                                            // then false is returned.
          return (!adminPower);
        }
      }
    }
    return (!adminPower);
  }

  /*
   * This method is used to reset the password of an user. To reset the password of an user the
   * currentUser must be an admin.
   * 
   * @param username Name of the user whose password is changed to the default password.
   * 
   * @param currentUser is the user currently logged in.
   * 
   * @return arest returns true if the password resets successfully.
   */
  public boolean resetPassword(String username) { // Reset a user's password
    boolean reset = true;
    if (currentUser == null) { // Checks if the currentUser is null.
      return !reset;
    }
    if (currentUser.getIsAdmin() == true) {
      for (int i = 0; i < users.size(); i++) {// This loop checks whether the provided
                                              // username matches with any username
                                              // already existing in the list
        if (users.get(i).getUsername().equals(username)) {

          users.get(i).setPassword(DEFAULT_PASSWORD); // Resets the password of the user whose name
                                                      // in the list matches with the provided name
                                                      // to DEFAULT_PASSWORD
          return reset;
        } else if (i == users.size() - 1) {// if the provided name matches with no one in the list
                                           // then false is returned.
          return !reset;
        }
      }
    }
    return !reset;
  }

  /*
   * This method is the Main driver loop.It is the primary look of the programs output. It asks the
   * user for input and also checks that the input information is valid with the help of
   * isValidLogin(). If the login is valid then it calls the sessionScreen to get the commands from
   * the user.
   * 
   * @param username stores the username entered by the user.
   * 
   * @param password stores the password entered by the user.
   * 
   * @param userInputScanner is the scanner passed from main method so that it can be used by login
   * screen to recieve inputs.
   * 
   * @param validLogin stores the boolean value returned by the method isValidLogin().
   * 
   * If isValidLogin is true, then sessionScreen method is called, or else the message
   * "INVALID LOGIN" is printed out.
   */
  public void loginScreen(Scanner userInputScanner) {// Main driver loop.
                                                     // Prompt the user for login information
                                                     // calls the isValidLogin method
                                                     // If the login is valid, call the
                                                     // sessionScreen method

    while (true) { // infinite loop, so that the program asks for username and password as soon as
                   // the previous user logs out
      System.out.println("WELCOME to your AccessControl");
      System.out.println("Enter your Username and Password: ");
      String username = userInputScanner.next(); // takes the first word entered by the user as
                                                 // username

      String password = userInputScanner.next(); // takes the second word entered by the user as
                                                 // password.
      boolean validLogin = isValidLogin(username, password); // checks whether the username and
                                                             // password pair entered by the user
                                                             // matches with any of the pre
                                                             // exisiting user.
      if (validLogin == true) {
        sessionScreen(username, userInputScanner);
      } else {
        System.out.println("INVALID LOGIN");
      }
    }
  }

  /*
   * This method controls the working of the program. It firstly sets the currentUser and then
   * allows the currentUser to perform commands as per their admin status, if current user is an
   * admin then he can access the admin methods otherwise he can only work on changunng his own
   * password or logging out or another non-admin methods.
   * 
   * @param username it is the username of the user who has logged in. i.e currentUser
   * 
   * @param userInputScanner it is passed from the main method and is used to take commands from the
   * user, so that the sessionScreen method can work accordingly.
   * 
   * @command is the string containing the command given by the user and also the username on whome
   * the command has to work on.
   * 
   * @answer[] is the array containing the string command at different indexes divided by space.
   * 
   * @userAnswer is used to deal with the exception of having two methods with the same name. i.e it
   * is used just to differentiate between adduser[username] and adduser[username][true/false].
   */
  public void sessionScreen(String username, Scanner userInputScanner) { // Set the currentUser
                                                                         // Allows them to
                                                                         // changePassword or logout
                                                                         // If they are an admin,
                                                                         // gives access to admin
                                                                         // methods
    userInputScanner.nextLine();
    setCurrentUser(username);

    while (true && currentUser != null) { // this is an infinite loop and it only executes when
                                          // there is a current user.
      System.out.println("Hello user, Enter a command: ");
      System.out.println("To logout: Enter \"logout\" ");
      System.out.println("For new password: \"newpw [newpassword]\" ");
      System.out.println("To add user: adduser \"[username]\" ");
      System.out.println(
          "Add user and decide if admin or not : \" adduser [username] [true or false]\" ");
      System.out.println("To remove user: \"rmuser [username]\" ");
      System.out.println("To give the current user admin power: \"giveadmin [username]\" ");
      System.out.println("To take the power of admin from current user: \"rmadmin [username]\"");
      System.out.println("To reset Password: \"resetpw [username]\"");
      String command = userInputScanner.nextLine();
      command.trim();
      String[] answer = command.split(" "); // This accomodates the command given by user into an
                                            // array.
      String userAnswer;

      if (answer.length > 2) {
        userAnswer = "adduser2"; // Here it checks that if the length of array is greater than 2 ,
                                 // then it should be adduser2 i.e addUser[username][true/false].
      } else {
        userAnswer = answer[0];
      }
      switch (userAnswer) {
        case ("logout"):
          logout();
          return;
        case ("newpw"):
          if (answer.length > 1) { // Check if the length is greater than 1 so that we dont
                                   // experience indexOutOfBoundsException.
            currentUser.setPassword(answer[1]);
          }
          break;
        case ("adduser"):
          if (answer.length > 1) {
            addUser(answer[1]);
          }
          break;

        case ("adduser2"):
          if (answer.length > 1) {
            addUser(answer[1], Boolean.parseBoolean(answer[2]));
          }
          break;

        case ("rmuser"):
          if (answer.length > 1) {
            removeUser(answer[1]);
          }
          break;

        case ("giveadmin"):
          if (answer.length > 1) {
            giveAdmin(answer[1]);
          }
          break;

        case ("rmadmin"):
          if (answer.length > 1) {
            takeAdmin(answer[1]);
          }
          break;

        case ("resetpw"):
          if (answer.length > 1) {
            resetPassword(answer[1]);
          }
          break;

        default:
          if (answer.length > 1) { // If an invalid command is entered by the user then this is
                                   // printed out.
            System.out.println("INVALID CHOICE");
          }
          break;

      }
    }
  }
}


