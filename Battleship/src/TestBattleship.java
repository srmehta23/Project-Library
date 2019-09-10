
/**
 * This file contains testing methods for the Battleship project. These methods are intended to
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Battleship methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Battleship project is
 * to write some tests and write header comments summarizing the tests that have been written.
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Random;
import java.util.Scanner;

/**
 * This class contains a few methods for testing methods in the Battleship class
 * as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Marc Renault
 * @author Shubham Mehta & Eric Kontny
 *
 */
public class TestBattleship {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests when
	 * you are ready for them to run.
	 * 
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {
		// Milestone 1
		testCoordAlphaToNum();
		testCoordNumToAlpha();
		// Milestone 2
		testCheckWater();
		testPlaceShip();
		// Milestone 3
		testTakeShot();
		testCheckLost();
	}

	/*
	 * This method checks whether the method(CoordAlphaToNum) can convert the given
	 * string to a number or not. If not, then it prints out ""FAILED:
	 * Battleship.coordAlphaToNum(@coord) != integer, but " + res:"
	 * 
	 * @param res is the integer output you get by using coordAlphaToNum.
	 */

	private static void testCoordAlphaToNum() {
		int numTests = 5;
		int passed = numTests;
		int res;
		if ((res = Battleship.coordAlphaToNum("BAAA")) != 17576) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"BAAA\") != 17576, but " + res);
			passed--;
		}
		if ((res = Battleship.coordAlphaToNum("ZERTY")) != 11506714) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"ZERTY\") != 11506714, but " + res);
			passed--;
		}
		if ((res = Battleship.coordAlphaToNum("zerty")) != 11506714) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"zerty\") != 11506714, but " + res);
			passed--;
		}
		if ((res = Battleship.coordAlphaToNum("eric")) != 82006) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"eric\") != 82006, but " + res);
			passed--;
		}
		if ((res = Battleship.coordAlphaToNum("mehta")) != 5559242) {
			System.out.println("FAILED: Battleship.coordAlphaToNum(\"mehta\") != 91260, but " + res);
			passed--;
		}
		System.out.println("testCoordAlphatoNum: Passed " + passed + " of " + numTests + " tests.");
	}

	/*
	 * This method checks whether the method(CoordNumToAlpha) can convert the given
	 * number to string or not. If not, then it prints out ""FAILED:
	 * Battleship.coordAlphaToNum(@coord) != string, but " + res:"
	 * 
	 * @param res is the String output you get by using coordNumToAlpha.
	 */

	private static void testCoordNumToAlpha() {
		int numTests = 2;
		int passed = numTests;
		String res;
		if (!(res = Battleship.coordNumToAlpha(82006)).equalsIgnoreCase("eric")) {
			System.out.println("FAILED: Battleship.coordNumToAlpha(82006) != eric, but " + res);
			passed--;
		}
		if (!(res = Battleship.coordNumToAlpha(5559242)).equalsIgnoreCase("mehta")) {
			System.out.println("FAILED: Battleship.coordNumToAlpha(5559242) != mehta, but " + res);
			passed--;
		}
		System.out.println("testCoordNumToAlpha: Passed " + passed + " of " + numTests + " tests.");

	}

	/*
	 * This method makes sure that the method(checkWater) returns 1,-1,-2
	 * respectively for all the required character being water char, there's a ship
	 * in betwween and if the ship goes out of bounds.
	 */
	private static void testCheckWater() {

		int numTests = 3;
		int passed = numTests;
		int res;
		int i = 5;
		int j = 4;
		char[][] board = new char[i][j];
		for (i = 0; i < board.length; ++i) { // This loop initialises a board full of water chars
			for (j = 0; j < board[i].length; ++j) {
				board[i][j] = Config.WATER_CHAR;
			}
		}
		// Placing ships on board
		board[1][0] = '1';
		board[1][2] = '1';
		board[1][1] = '1';

		if ((res = Battleship.checkWater(board, 0, 1, 3, true)) != -1) {
			System.out.println("FAILED: Battleship.checkWater(\"board , 0 , 1, 3, false\") != -1, but " + res);
			passed--;
		}
		if ((res = Battleship.checkWater(board, 1, 1, 10, false)) != -2) {
			System.out.println("FAILED: Battleship.checkWater(\"board , 1 , 1, 10, false\") != -2, but " + res);
			passed--;
		}
		if ((res = Battleship.checkWater(board, 1, 2, 2, true)) != 1) {
			System.out.println("FAILED: Battleship.checkWater(\"board , 1 ,2 , 2, true\") != 1, but " + res);
			passed--;
		}
		System.out.println("testCheckWater: Passed " + passed + " of " + numTests + " tests.");
	}

	/*
	 * Tests if the method(placeShip) , placed the ship on the board and returns int
	 * accordingly.
	 */
	private static void testPlaceShip() {

		int numTests = 2;
		int passed = numTests;
		boolean res;
		int i = 5;
		int j = 4;
		char[][] board = new char[i][j];
		for (i = 0; i < board.length; ++i) { // This loop initializes a board full of water chars
			for (j = 0; j < board[i].length; ++j) {
				board[i][j] = Config.WATER_CHAR;
			}
		}

		if ((res = Battleship.placeShip(board, 0, 0, 1, true, 1)) != true) {
			System.out.println("FAILED: Battleship.placeShip(board,0,0,1,true,1) != true, but " + res);
			passed--;
		}
		board[1][1] = '2';
		if ((res = Battleship.placeShip(board, 1, 1, 15, true, 1)) != false) {
			System.out.println("FAILED: Battleship.placeShip(board,1,1,1,true,1) != false, but " + res);
			passed--;
		}
		System.out.println("testPlaceShip: Passed " + passed + " of " + numTests + " tests.");
	}

	/*
	 * It makes sure that the method takeShot returns the correct value.
	 */
	private static void testTakeShot() {
		int numTests = 3;
		int passed = numTests;
		int res;
		//Creating a board
		char[][] board = new char[5][4];
		int i;
		int j;
		for (i = 0; i < board.length; ++i) {
			for (j = 0; j < board[i].length; ++j) {
				board[i][j] = Config.WATER_CHAR;
			}
		}

		if ((res = Battleship.takeShot(board, 1, 1)) != 1) {
			System.out.println("FAILED: Battleship.takeShot(\"board , 1 , 1\") != 1, but " + res);
			passed--;
		}
		if ((res = Battleship.takeShot(board, 6, 6)) != -1) {
			System.out.println("FAILED: Battleship.takeShot(\"board , 0 , 0\") != -1, but " + res);
			passed--;

		} 
		if ((res = Battleship.takeShot(board, 0, 0)) != 2) {
			System.out.println("FAILED: Battleship.takeShot(\"board , 0 , 0\") != -1, but " + res);
			passed--;

		}
		System.out.println("testTakeShot: Passed " + passed + " of " + numTests + " tests.");
	}

	/*
	 * checks if the method checkLost returns a correct boolean if all the ships
	 * have sunk.
	 */

	private static void testCheckLost() {
		int numTests = 2;
		int passed = numTests;
		boolean res;
		char[][] board = new char[5][4];
		int i;
		int j;
		for (i = 0; i < board.length; ++i) {
			for (j = 0; j < board[i].length; ++j) {
				board[i][j] = Config.WATER_CHAR;
			}
		}
		if ((res = Battleship.checkLost(board)) != true) {
			System.out.println("FAILED: Battleship.checkLost(board) != true, but " + res);
			passed--;
		}
		board[1][1] = '1'; //Placing a ship on the board
		if ((res = Battleship.checkLost(board)) != false) { //returns false because there is a ship on the board.
			System.out.println("FAILED: Battleship.checkLost(board) != false, but " + res);
			passed--;
		}
		System.out.println("testCheckLost: Passed " + passed + " of " + numTests + " tests.");
	}
}
