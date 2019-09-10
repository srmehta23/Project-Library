
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: BATTLESHIP
// Files: a list of all source files used by that program
// Course: 200,Spring, 2018
//
// Author: Shubham Mehta
// Email:  smehta23@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Eric kontny
// Partner Email: ekontny@wisc.edu
// Lecturer's Name: Marc Renault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// x Write-up states that pair programming is allowed for this assignment.
// x We have both read and understand the course Pair Programming Policy.
// x We have registered our team prior to the team registration deadline.
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
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////import
//////////////////// java.util.Scanner;
import java.util.Random;
import java.util.Scanner;

public class Battleship {

	/**
	 * This method converts a String representing a base (or radix) 26 number into a
	 * decimal (or base 10) number. The String representation of the base 26 number
	 * uses the letters of the Latin alphabet to represent the 26 digits. That is, A
	 * represents 0, B represents 1, C represents 2, ..., Y represents 24, and Z
	 * represents 25.
	 *
	 * A couple of examples: BAAA = 1 * 26^3 + 0 * 26^2 + 0 * 26^1 + 0 * 26^0 =
	 * 17576 ZERTY = 25 * 26^4 + 4 * 26^3 + 17 * 26^2 + 19 * 26^1 + 24 * 26^0 =
	 * 11506714
	 *
	 * For this method: - use Math.pow to calculate the powers of 26. - don't assume
	 * that the input is in any particular case; use toUpperCase(). - don't check
	 * that the input is only 'A' to 'Z'. - calculate the value of each digit
	 * relative to 'A'. - start from either the first or last character, and
	 * calculate the exponent based on the index of each character.
	 *
	 * @param coord
	 *            The coordinate value in base 26 as described above.
	 * @return The numeric representation of the coordinate.
	 */

	/*
	 * @english[] Reference to the Array containing all the alphabet
	 * 
	 * @alphaVal[] designates values to alphabet
	 * 
	 * @coord is the string which the program converts into a number.
	 * 
	 * @count counts index of each alphabet in the string coord.
	 * 
	 * @sum sums up the value of our decimal number from radix
	 * 
	 * @ returnval Converts the alphabets to integers and then returns that integer.
	 * 
	 * @i and j count up for our loops
	 * 
	 */
	public static int coordAlphaToNum(String coord) {

		char english[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int alphaVal[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25 };
		int count = coord.length() - 1;
		double sum = 0;
		int returnVal;
		int i;
		int j;
		coord = coord.toUpperCase();
		coord = coord.trim();

		for (i = 0; i < coord.length(); i++) {
			for (j = 0; j < 26; j++) {

				if (english[j] == coord.charAt(i)) {
					sum += alphaVal[j] * Math.pow(26, count); // formula derived as per the calculations provided in the
																// method header.
					count--;
					break;
				}

			}
		}

		returnVal = (int) sum;

		return returnVal;
	}

	/**
	 * This method converts an int value into a base (or radix) 26 number, where the
	 * digits are represented by the 26 letters of the Latin alphabet. That is, A
	 * represents 0, B represents 1, C represents 2, ..., Y represents 24, and Z
	 * represents 25. A couple of examples: 17576 is BAAA, 11506714 is ZERTY.
	 *
	 * The algorithm to convert an int to a String representing these base 26
	 * numbers is as follows: - Initialize res to the input integer - The next digit
	 * is determined by calculating the remainder of res with respect to 26 -
	 * Convert this next digit to a letter based on 'A' - Set res to the integer
	 * division of res and 26 - Repeat until res is 0
	 *
	 * @param coord
	 *            The integer value to covert into an alpha coordinate.
	 * @return The alpha coordinate in base 26 as described above. If coord is
	 *         negative, an empty string is returned.
	 */

	/*
	 * @english[] Reference to the Array containing all the alphabet .
	 * 
	 * 
	 * @count keeps count of which index of the string we are on starting from right
	 * to left.
	 * 
	 * @temp stores the remainder of coord %26.
	 * 
	 * @result Takes a character from array english and keeps adding on the next
	 * char until we have the string
	 * 
	 * returns the aplhabet converted from the number.
	 */

	public static String coordNumToAlpha(int coord) {

		int temp = 0;

		String result = "";
		String english[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		if (coord > 0) {
			while (coord > 0) {
				temp = coord % 26;
				result = (english[temp] + result);
				coord = (int) (coord / 26);

				result = result.trim();

			}
		} else {
			return "A";
		}

		return result;
	}

	/**
	 * Prompts the user for an integer value, displaying the following: "Enter the
	 * valName (min to max): " Note: There should not be a new line terminating the
	 * prompt. valName should contain the contents of the String referenced by the
	 * parameter valName. min and max should be the values passed in the respective
	 * parameters.
	 *
	 * After prompting the user, the method will read an int from the console and
	 * consume an entire line of input. If the value read is between min and max
	 * (inclusive), that value is returned. Otherwise, "Invalid value." terminated
	 * by a new line is output and the user is prompted again.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param valName
	 *            The name of the value for which the user is prompted.
	 * @param min
	 *            The minimum acceptable int value (inclusive).
	 * @param min
	 *            The maximum acceptable int value (inclusive).
	 * @return Returns the value read from the user.
	 */

	/*
	 * @valName stores the name of what you are getting.
	 * 
	 * @min and @max stores the minimum and maximum value possible ,a user can
	 * enter.
	 */

	public static int promptInt(Scanner sc, String valName, int min, int max) {

		System.out.print("Enter the " + valName + " (" + min + " to " + max + "): ");
		int nameVal = sc.nextInt();
		while (nameVal < min || nameVal > max) { // The loop checks whether the given no. is between the minimum and
													// maximum number.
			System.out.println("Invalid value.");
			System.out.print("Enter the " + valName + " (" + min + " to " + max + "): ");
			nameVal = sc.nextInt();
		}

		return nameVal;
	}

	/**
	 * Prompts the user for an String value, displaying the following: "Enter the
	 * valName (min to max): " Note: There should not be a new line terminating the
	 * prompt. valName should contain the contents of the String referenced by the
	 * parameter valName. min and max should be the values passed in the respective
	 * parameters.
	 *
	 * After prompting the user, the method will read an entire line of input,
	 * trimming any trailing or leading whitespace. If the value read is
	 * (lexicographically ignoring case) between min and max (inclusive), that value
	 * is returned. Otherwise, "Invalid value." terminated by a new line is output
	 * and the user is prompted again.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param valName
	 *            The name of the value for which the user is prompted.
	 * @param min
	 *            The minimum acceptable String value (inclusive).
	 * @param min
	 *            The maximum acceptable String value (inclusive).
	 * @return Returns the value read from the user.
	 */

	/*
	 * @param valName stores the name of what you are prompting the user for.
	 * 
	 * @param min and @param max stores the minimum and maximum value possible, a
	 * user can enter. The loop checks whether the given no. is between the minimum
	 * and maximum number , lexicographically.
	 */

	public static String promptStr(Scanner sc, String valName, String min, String max) {

		System.out.print("Enter the " + valName + " (" + min + " to " + max + "): ");

		String nameVal = sc.next();
		nameVal = nameVal.trim();
		nameVal = nameVal.toUpperCase();
		while (nameVal.compareToIgnoreCase(min) < 0 || nameVal.compareToIgnoreCase(max) > 0) {
			System.out.println("Invalid value.");
			System.out.print("Enter the " + valName + " (" + min + " to " + max + "): ");
			nameVal = sc.next();
		}

		return nameVal;

	}

	/**
	 * Prompts the user for an char value. The prompt displayed is the contents of
	 * the String referenced by the prompt parameter. Note: There should not be a
	 * new line terminating the prompt.
	 *
	 * After prompting the user, the method will read an entire line of input and
	 * return the first non-whitespace character in lower case.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in
	 * @param prompt
	 *            The user prompt.
	 * @return Returns the first non-whitespace character (in lower case) read from
	 *         the user. If there are no non-whitespace characters read, the null
	 *         character is returned.
	 */

	/*
	 * This method prompts the user with a question, to enter a char for.
	 * 
	 * @param prompt , prompts the user with a question for which he has to enter a
	 * char.
	 * 
	 * @param returnVal returns the char entered by the user.
	 * 
	 */

	public static char promptChar(Scanner sc, String prompt) {

		char returnVal;// FIXME
		System.out.print(prompt); // Changed

		prompt = sc.next();
		prompt = prompt.trim();
		returnVal = prompt.charAt(0);
		returnVal = Character.toLowerCase(returnVal);
		return returnVal;
	}

	/**
	 * Initializes a game board so that all the entries are Config.WATER_CHAR.
	 *
	 * @param board
	 *            The game board to initialize.
	 */
	/*
	 * This method fills the whole board[2D array] with water character as elements
	 * of array.
	 */
	public static void initBoard(char board[][]) {

		int i;
		int j;
		for (i = 0; i < board.length; ++i) {
			for (j = 0; j < board[i].length; ++j) {
				board[i][j] = Config.WATER_CHAR;
			}
		}
	}

	/**
	 * Prints the game boards as viewed by the user. This method is used to print
	 * the game boards as the user is placing their ships and during the game play.
	 *
	 * Some notes on the display: - Each column printed will have a width of
	 * Config.MAX_COL_WIDTH. - Each row is followed by an empty line. - The values
	 * in the headers and cells are to be right justified.
	 *
	 * @param board
	 *            The board to print.
	 * @param caption
	 *            The board caption.
	 */

	/*
	 * @param caption The caption to be printed above the board.
	 * 
	 * @english[] contains alphabets, which we use to name the x axis.
	 * 
	 */
	public static void printBoard(char board[][], String caption) {
		String firstLine = "";
		String english[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };

		System.out.println(caption + ":");
		for (int i = 0; i < board[0].length; ++i) { // prints the first line of the board(x axis) as alphabets.
			if (i != board[0].length - 1) {
				firstLine = (firstLine + coordNumToAlpha(i) + "  ");
			} else {
				firstLine = (firstLine + coordNumToAlpha(i)); // So that a space is not printed after the last alphabet
																// on that row is printed.
			}
		}
		System.out.println("     " + firstLine);
		for (int i = 0; i < board.length; ++i) { // This loop prints out the whole board
			for (int j = 0; j < board[0].length; ++j) {

				if (board[0].length - 1 == 0) { // This counts the line we are on starting from 0 followed by a
												// WATER_CHAR.
					System.out.println("  " + i + "  " + board[i][j]);
					System.out.println();
				} else if (j == board[0].length - 1) { // if we are on the last column it will print WATER_CHAR with no
														// spaces ahead.
					System.out.println("  " + board[i][j]);
					System.out.println();
				}

				else if (j == 0) { // if the width of the board is 1, then this will just printout

					System.out.print("  " + i + "  " + board[i][j]);

				} else {
					System.out.print("  " + board[i][j]);
				}

			}
		}
	}

	/**
	 * Determines if a sequence of cells of length len in a game board is clear or
	 * not. This is used to determine if a ship will fit on a given game board. The
	 * x and y coordinates passed in as parameters represent the top-left cell of
	 * the ship when considering the grid.
	 * 
	 * @param board
	 *            The game board to search.
	 * @param xcoord
	 *            The x-coordinate of the top-left cell of the ship.
	 * @param ycoord
	 *            The y-coordinate of the top-left cell of the ship.
	 * @param len
	 *            The length of the ship.
	 * @param dir
	 *            true if the ship will be vertical, otherwise horizontal
	 * @return 1 if the cells to be occupied by the ship are all Config.WATER_CHAR,
	 *         -1 if the cells to be occupied are not Config.WATER_CHAR, and -2 if
	 *         the ship would go out-of-bounds of the board.
	 */
	/*
	 * This method checks whether the place at which you are placing the ship has
	 * all water chars and also, that if the ship is not going out of bounds.
	 */
	public static int checkWater(char board[][], int xcoord, int ycoord, int len, boolean dir) {
		if (dir) { // checking vertically
			if (board.length - ycoord >= len) {
				int i = ycoord;
				int w = ycoord;
				for (int j = 0; j < len; ++j) {
					if (board[i][xcoord] == Config.WATER_CHAR) {
						if (j == len - 1) {
							return 1;
						}
					} else { // if there is ship in the path where you are placing a new ship
						return -1;
					}
					++i;
				}
			} else { // if the ship gets out of bounds
				return -2;
			}
		} else if (!dir) { // Checking Horizontally
			if (board[board.length - 1].length - xcoord >= len) {
				int i = xcoord;
				int w = xcoord;
				for (int j = 0; j < len; ++j) {
					if (board[ycoord][i] == Config.WATER_CHAR) {
						if (j == len - 1) {
							return 1;
						}
					} else {
						return -1;
					}
					++i;
				}
			} else {
				return -2;
			}
		}

		return 0;
	}

	/**
	 * Checks the cells of the game board to determine if all the ships have been
	 * sunk.
	 *
	 * @param board
	 *            The game board to check.
	 * @return true if all the ships have been sunk, false otherwise.
	 */

	/*
	 * This method is to check whether all of your ships or the opponents ship have
	 * sunk or not.
	 * 
	 * @board the board you are passing, whether the opponents board or the user's
	 * board
	 */
	public static boolean checkLost(char board[][]) {

		int count = 0;
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[0].length; ++j) {

				switch (board[i][j]) { // this checks if at any place there is a ship with id (1 to 9)
				case '1':
					return false;

				case '2':
					return false;
				case '3':

					return false;
				case '4':

					return false;

				case '5':

					return false;
				case '6':

					return false;
				case '7':

					return false;
				case '8':

					return false;
				case '9':

					return false;

				default:
					break;
				}
			}
		}
		return true;
	}

	/**
	 * Places a ship into a game board. The coordinate passed in the parameters
	 * xcoord and ycoord represent the top-left coordinate of the ship. The ship is
	 * represented on the game board by the Character representation of the ship id.
	 * (For this method, you can assume that the id parameter will only be values 1
	 * through 9.)
	 *
	 * @param board
	 *            The game board to search.
	 * @param xcoord
	 *            The x-coordinate of the top-left cell of the ship.
	 * @param ycoord
	 *            The y-coordinate of the top-left cell of the ship.
	 * @param len
	 *            The length of the ship.
	 * @param dir
	 *            true if the ship will be vertical, otherwise horizontal.
	 * @param id
	 *            The ship id, assumed to be 1 to 9.
	 * @return false if the ship goes out-of-bounds of the board, true otherwise.
	 */
	/*
	 * This method places ship on the specified coordinates and also according the
	 * mentioned length.
	 * 
	 * @dir decides if the ship would be vertical or horizontal.
	 */
	public static boolean placeShip(char board[][], int xcoord, int ycoord, int len, boolean dir, int id) {

		if (dir) { // placing ship vertically
			if (board.length - ycoord >= len) {
				int i = ycoord;
				for (int j = 0; j < len; ++j) {
					board[i][xcoord] = Integer.toString(id).charAt(0); // converting int id to a character and placing
																		// it out on board
					if (j == len - 1) {
						return true; // if placed return true
					}

					++i;
				}
			} else {
				return false;
			}
		} else if (!dir) {// placing ship Horizontally
			if (board[board.length - 1].length - xcoord >= len) {
				int i = xcoord;
				for (int j = 0; j < len; ++j) {

					board[ycoord][i] = Integer.toString(id).charAt(0); // converting int id to a characcter and placing
																		// it out on board
					if (j == len - 1) {
						return true; // if placed it returns true.
					}
					++i;
				}
			} else {
				return false;
			}

		}

		// FIXME
		return false;
	}

	/**
	 * Randomly attempts to place a ship into a game board. The random process is as
	 * follows: 1 - Pick a random boolean, using rand. True represents vertical,
	 * false horizontal. 2 - Pick a random integer, using rand, for the x-coordinate
	 * of the top-left cell of the ship. The number of integers to choose from
	 * should be calculated based on the width of the board and length of the ship
	 * such that the placement of the ship won't be out-of-bounds. 3 - Pick a random
	 * integer, using rand, for the y-coordinate of the top-left cell of the ship.
	 * The number of integers to choose from should be calculated based on the
	 * height of the board and length of the ship such that the placement of the
	 * ship won't be out-of-bounds. 4 - Verify that this random location can fit the
	 * ship without intersecting another ship (checkWater method). If so, place the
	 * ship with the placeShip method.
	 *
	 * It is possible for the configuration of a board to be such that a ship of a
	 * given length may not fit. So, the random process will be attempted at most
	 * Config.RAND_SHIP_TRIES times.
	 * 
	 * @param board
	 *            The game board to search.
	 * @param len
	 *            The length of the ship.
	 * @param id
	 *            The ship id, assumed to be 1 to 9..
	 * @param rand
	 *            The Random object.
	 * @return true if the ship is placed successfully, false otherwise.
	 */

	// This method places ships randomly for the computer
	public static boolean placeRandomShip(char board[][], int len, int id, Random rand) {

		int x = 0;
		int xcoord = 0;
		int ycoord = 0;
		int a = 0;
		int i = 0;

		while ((x < Config.RAND_SHIP_TRIES)) {
			boolean dir = rand.nextBoolean(); // Decides a random direction for the ship to be placed
			if (dir) { // Vertical
				xcoord = rand.nextInt(board[0].length);
				ycoord = rand.nextInt(board.length - len + 1);
			} else { // Horizontal
				xcoord = rand.nextInt(board[0].length - len + 1);
				ycoord = rand.nextInt(board.length);
			}
			a = checkWater(board, xcoord, ycoord, len, dir); // Checks whether the ship can be placed or not.
			if (a == 1) {
				boolean ship = placeShip(board, xcoord, ycoord, len, dir, id); // Shhipping place using placeShip
				return ship;
			}

			++x;
		}
		return false;
	}

	/**
	 * This method interacts with the user to place a ship on the game board of the
	 * human player and the computer opponent. The process is as follows: 1 - Print
	 * the user primary board, using the printBoard. 2 - Using the promptChar
	 * method, prompt the user with "Vertical or horizontal? (v/h) ". A response of
	 * v is interpreted as vertical. Anything else is assumed to be horizontal. 3 -
	 * Using the promptInt method, prompt the user for an integer representing the
	 * "ship length", where the minimum ship length is Config.MIN_SHIP_LEN and the
	 * maximum ship length is width or height of the game board, depending on the
	 * input of the user from step 1. 4 - Using the promptStr method, prompt the
	 * user for the "x-coord". The maximum value should be calculated based on the
	 * width of the board and the length of the ship. You will need to use the
	 * coordAlphaToNum and coordNumToAlpha methods to covert between int and String
	 * values of coordinates. 5 - Using the promptInt method, prompt the user for
	 * the "y-coord". The maximum value should be calculated based on the width of
	 * the board and the length of the ship. 6 - Check if there is space on the
	 * board to place the ship. 6a - If so: - Place the ship on the board using
	 * placeShip. - Then, call placeRandomShip to place the opponents ships of the
	 * same length. - If placeRandomShip fails, print out the error message
	 * (terminated by a new line): "Unable to place opponent ship: id", where id is
	 * the ship id, and return false. 6b - If not: - Using promptChar, prompt the
	 * user with "No room for ship. Try again? (y/n): " - If the user enters a 'y',
	 * restart the process at Step 1. - Otherwise, return false.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param boardPrime
	 *            The human player board.
	 * @param boardOpp
	 *            The opponent board.
	 * @param id
	 *            The ship id, assumed to be 1 to 9.
	 * @param rand
	 *            The Random object.
	 * @param userAnswer
	 *            holds the user answer
	 * @param dir
	 *            stores the direction of the ship
	 * @param shiplength
	 *            stores the length of the ship
	 * @param placed
	 *            stores if the ship was place or not
	 * @param orientation
	 *            Asks the user which way they want to orient the ship.
	 * @param max
	 *            stores the max value possible for an input
	 * @param ycoord
	 *            Stores the Y coordinate from the user
	 * @param xcoord
	 *            stores the X coordinate from the user
	 * @return true if ship placed successfully by player and computer opponent,
	 *         false otherwise.
	 * 
	 */
	public static boolean addShip(Scanner sc, char boardPrime[][], char boardOpp[][], int id, Random rand) {
		char userAnswer = 'y';
		boolean dir = true;
		int shipLength;
		boolean placed = false;
		while (userAnswer == 'y') {
			printBoard(boardPrime, "My Ships");
			char orientation = promptChar(sc, "Vertical or horizontal? (v/h): ");
			// v means vertical and anything else means horizontal

			if (orientation == 'v') {
				dir = true;
				shipLength = promptInt(sc, "ship length", Config.MIN_SHIP_LEN, boardPrime.length);
				String zero = coordNumToAlpha(0);
				String xcoord = coordNumToAlpha((boardPrime[0].length - 1));
				String x = promptStr(sc, "x-coord", zero, xcoord);
				int max = (boardPrime.length - shipLength);

				int ycoord = promptInt(sc, "y-coord", 0, max);
				if (checkWater(boardPrime, coordAlphaToNum(x), ycoord, shipLength, dir) == 1) {
					placed = placeShip(boardPrime, coordAlphaToNum(x), ycoord, shipLength, dir, id);
					placeRandomShip(boardOpp, shipLength, id, rand);
				} else { // returns false if the ship wasnt placed
					return false;
				}
				// if orientation is not vertical, then make it horizontal
			} else {
				dir = false;
				shipLength = promptInt(sc, "ship length", Config.MIN_SHIP_LEN, boardPrime[0].length);
				String zero = coordNumToAlpha(0);
				String xcoord = coordNumToAlpha(((boardPrime[0].length) - shipLength));
				String x = promptStr(sc, "x-coord", zero, xcoord);

				int ycoord = promptInt(sc, "y-coord", 0, boardPrime.length - 1);

				if (checkWater(boardPrime, coordAlphaToNum(x), ycoord, shipLength, dir) == 1) {
					placed = placeShip(boardPrime, coordAlphaToNum(x), ycoord, shipLength, dir, id);
					placeRandomShip(boardOpp, shipLength, id, rand);
				} else {// returns false if the ship wasnt placed
					return false;
				}
			}

			if (placed) {
				return true;
			}

		}

		return false;
	}

	/**
	 * Checks the state of a targeted cell on the game board. This method does not
	 * change the contents of the game board.
	 *
	 * @return 3 if the cell was previously targeted. 2 if the shot would be a miss.
	 *         1 if the shot would be a hit. -1 if the shot is out-of-bounds.
	 * 
	 * @param x
	 *            is the x coordinate
	 * @param y
	 *            is the y coordinate
	 * 
	 */
	public static int takeShot(char[][] board, int x, int y) {
		// Checks if the shot would be out of bounds
		if (y > (board.length - 1) || x > (board[0].length - 1)) {
			return -1;
		}
		// Checks if the shot was on a WATER_CHAR
		else if (board[y][x] == Config.WATER_CHAR) {
			return 2;
			// Checks if the shot was a HIT_CHAR
		} else if (board[y][x] == Config.HIT_CHAR || board[y][x] == Config.MISS_CHAR) {
			return 3;
			// Checks if the shot hit a ship
		} else {
			switch (board[y][x]) {
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				return 1;
			}
		}

		return 0;
	}

	/**
	 * Interacts with the user to take a shot. The procedure is as follows: 1 -
	 * Using the promptStr method, prompt the user for the "x-coord shot". The
	 * maximum value should be based on the width of the board. You will need to use
	 * the coordAlphaToNum and coordNumToAlpha methods to covert between int and
	 * String values of coordinates. 2 - Using the promptInt method, prompt the user
	 * for the "y-coord shot". The maximum value should be calculated based on the
	 * Height of the board. 3 - Check the shot, using the takeShot method. If it
	 * returns: -1: Print out an error message "Coordinates out-of-bounds!",
	 * terminated by a new line. 3: Print out an error message "Shot location
	 * previously targeted!", terminated by a new line. 1 or 2: Update the cells in
	 * board and boardTrack with Config.HIT_CHAR or Config.MISS_CHAR accordingly.
	 * This process should repeat until the takeShot method returns 1 or 2.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param board
	 *            The computer opponent board (containing the ship placements).
	 * @param boardTrack
	 *            The human player tracking board.
	 * @param ycoord
	 *            stores the ycoordinate that the user wants to shoot
	 * @param xcoord
	 *            store the xcoordinate that the user wants to shoot
	 * @param shot
	 *            Stores What happened when the shot was taken
	 * 
	 */

	public static void shootPlayer(Scanner sc, char[][] board, char[][] boardTrack) {

		while (1 > 0) { // This runs until it hits a break statement
			String xcoord = promptStr(sc, "x-coord shot", "A", coordNumToAlpha(board[0].length - 1));// Prompts the user
																										// for a
																										// xcoordinate
			int ycoord = promptInt(sc, "y-coord shot", 0, board.length - 1);
			int shot = takeShot(board, coordAlphaToNum(xcoord), ycoord);
			// Checks shot and returns what happened with it
			if (shot == (-1)) {
				System.out.println("Coordinates out-of-bounds!");
			} else if (shot == 3) {
				System.out.println("Shot location previously targeted!");
			} else if (shot == 1) {
				board[ycoord][coordAlphaToNum(xcoord)] = Config.HIT_CHAR;
				boardTrack[ycoord][coordAlphaToNum(xcoord)] = Config.HIT_CHAR;
				break;
			} else if (shot == 2) {
				board[ycoord][coordAlphaToNum(xcoord)] = Config.MISS_CHAR;
				boardTrack[ycoord][coordAlphaToNum(xcoord)] = Config.MISS_CHAR;
				break;
			}
		}
	}

	/**
	 * Takes a random shot on the game board. The random process works as follows: 1
	 * - Pick a random valid x-coordinate 2 - Pick a random valid y-coordinate 3 -
	 * Check the shot, using the takeShot method. This process should repeat until
	 * the takeShot method returns 1 or 2, then update the cells in board with
	 * Config.HIT_CHAR or Config.MISS_CHAR accordingly.
	 *
	 *
	 * @param rand
	 *            The Random object.
	 * @param board
	 *            The human player game board.
	 * @param xcoord
	 *            gets a random x coordinate
	 * @param ycoord
	 *            gets a random y coordinate
	 * @param shot
	 *            stores what happend when the shot was taken
	 */
	public static void shootComputer(Random rand, char[][] board) {
		while (1 > 0) {
			int xcoord = rand.nextInt(board[0].length);
			int ycoord = rand.nextInt(board.length);
			int shot = takeShot(board, xcoord, ycoord);

			if (shot == 1) { // If shot returned a 1 change to Hit character
				board[ycoord][xcoord] = Config.HIT_CHAR;

				break;
			} else if (shot == 2) { // If shot returned a 2 change to a miss character
				board[ycoord][xcoord] = Config.MISS_CHAR;

				break;
			}
		}
	}

	/**
	 * This is the main method for the Battleship game. It consists of the main game
	 * and play again loops with calls to the various supporting methods. When the
	 * program launches (prior to the play again loop), a message of "Welcome to
	 * Battleship!", terminated by a newline, is displayed. After the play again
	 * loop terminiates, a message of "Thanks for playing!", terminated by a
	 * newline, is displayed.
	 *
	 * The Scanner object to read from System.in and the Random object with a seed
	 * of Config.SEED will be created in the main method and used as arguments for
	 * the supporting methods as required.
	 *
	 * Also, the main method will require 3 game boards to track the play: - One for
	 * tracking the ship placement of the user and the shots of the computer, called
	 * the primary board with a caption of "My Ship". - One for displaying the shots
	 * (hits and misses) taken by the user, called the tracking board with a caption
	 * of "My Shots"; and one for tracking the ship placement of the computer and
	 * the shots of the user. - The last board is never displayed, but is the
	 * primary board for the computer and is used to determine when a hit or a miss
	 * occurs and when all the ships of the computer have been sunk. Notes: - The
	 * size of the game boards are determined by the user input. - The game boards
	 * are 2d arrays that are to be viewed as row-major order. This means that the
	 * first dimension represents the y-coordinate of the game board (the rows) and
	 * the second dimension represents the x-coordinate (the columns).
	 *
	 * @param args
	 *            Unused.
	 */

	/*
	 * @answer checks whether the user wants to continue or not.
	 * 
	 * @boardHeight calls the method promptInt and get's a number between minimum
	 * and maximum.
	 * 
	 * @boardwidth calls the method promptInt and get's a number between minimum and
	 * maximum.
	 * 
	 * @ the loop checks if the user wants to keep playing or not with the help
	 * of @answer.
	 * 
	 * @userAnswer tracks if if the user wants to continue after an invaild ship
	 * placement.
	 * 
	 * @failed tracks if a ship failed to place.
	 * 
	 * @numberOfShips tracks how many ships the user wants to place
	 */

	public static void main(String[] args) {

		Random rand = new Random();
		int id = 1;
		char boardPrime[][];
		char boardOpp[][];
		char boardComp[][];
		int boardHeight = 0;
		int boardWidth = 0;
		int numberOfShips;
		int i;
		boolean failed;
		char userAnswer = 'h';
		char answer = 'y';
		rand.setSeed(Config.SEED);
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Battleship!");

		while (answer == 'y') {
			boolean endGame = false;
			boardHeight = promptInt(sc, "board height", Config.MIN_HEIGHT, Config.MAX_HEIGHT);
			boardWidth = promptInt(sc, "board width", Config.MIN_WIDTH, Config.MAX_WIDTH);
			System.out.println("");
			boardPrime = new char[boardHeight][boardWidth]; // CREATE THE PLAYERS BOARD
			boardOpp = new char[boardHeight][boardWidth]; // CREATE THE OPPONENT BOARD
			boardComp = new char[boardHeight][boardWidth]; // CREATE A RENDERING BOARD
			initBoard(boardPrime);
			initBoard(boardOpp);
			initBoard(boardComp);
			numberOfShips = promptInt(sc, "number of ships", Config.MIN_SHIPS, Config.MAX_SHIPS); // ASKS PLAYER FOR
																									// NUMBER OF SHIPS
																									// WANTED
			for (i = 1; i <= numberOfShips; ++i) { // LOOPS UNTIL ALL SHIPS ARE PLACE ON THE BOARD

				failed = addShip(sc, boardPrime, boardOpp, i, rand);
				if (!failed) {
					// IF A SHIP FAILS TO PLACE, ASK IF THE USER WANTS TO RESTART
					userAnswer = promptChar(sc, "Error adding ships. Restart game? (y/n): ");
					break;

				}
			}

			if (userAnswer == 'n') {
				System.out.println("Thanks for playing!");
				break;
			} else if (userAnswer == 'y') {
				continue;
			}
			while (!endGame) { // CONTINUES THE GAME UNTIL ALL OF ONE PLAYERS SHIPS HAVE BEEN SUNK

				printBoard(boardPrime, "My Ships");
				printBoard(boardComp, "My Shots");
				shootPlayer(sc, boardOpp, boardComp);

				endGame = checkLost(boardOpp);
				if (endGame) {
					System.out.println("Congratulations, you sunk all the computer's ships!");
					printBoard(boardPrime, "My Ships");
					printBoard(boardComp, "My Shots");
					break;
				}
				shootComputer(rand, boardPrime);
				endGame = checkLost(boardPrime);
				if (endGame) {
					System.out.println("Oh no! The computer sunk all your ships!");
					printBoard(boardPrime, "My Ships");
					printBoard(boardComp, "My Shots");
					break;
				}

			}

			answer = promptChar(sc, "Would you like to play again? (y/n): "); // ASK IF THE USER WANTS TO PLAY AGAIN
			if (answer != 'y') {
				System.out.println("Thanks for playing!");
			}
		}

		sc.close();

	}
}
