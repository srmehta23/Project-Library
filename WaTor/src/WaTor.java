
// file header comment
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: WaTor
// Files: a list of all source files used by that program
// Course: CS 200
//
// Author: Shubham mehta
// Email: smehta23@wisc.edu
// Lecturer's Name: Marc Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// class header comment

/**
 * 
 * This class is named WaTor and it contains different methods for the Wator Program.
 *
 */
/*
 * The class WaTor contains different methods for the program WaTor To work.
 */

public class WaTor {

    private static final Exception IOException = null;

    /**
     * This is the main method for WaTor simulation. Based on:
     * http://home.cc.gatech.edu/biocs1/uploads/2/wator_dewdney.pdf This method contains the main
     * simulation loop. In main the Scanner for System.in is allocated and used to interact with the
     * user.
     * 
     * @param args (unused)
     */
    // Main method is used to get all the methods to work along using loops and conditions to make
    // them work at the right time.
    public static void main(String[] args) {

        // scanner and random number generator for use throughout
        Scanner input = new Scanner(System.in);
        Random randGen = new Random();

        // values at the same index in these parallel arrays correspond to the
        // same creature

        // a value equal or greater than 0 at a location indicates a fish of
        // that age at that location.
        int[][] fish = null;

        // true at a location indicates that the fish moved during the current
        // chronon
        boolean[][] fishMoved = null;

        // a value equal or greater than 0 at a location indicates a shark of
        // that age at that location
        int[][] sharks = null;

        // true at a location indicates that the shark moved during the current
        // chronon
        boolean[][] sharksMoved = null;

        // a value equal or greater than 0 indicates the number of chronon
        // since the shark last ate.
        int[][] starve = null;

        // an array for simulation parameters
        // to be used when saving or loading parameters in Milestone 3
        int[] simulationParameters = null;

        // welcome message
        System.out.println("Welcome to Wa-Tor");
        char userAnswer;
        String fileName;
        // Ask user if they would like to load simulation parameters from a file.
        // If the user enters a y or Y as the only non-whitespace characters
        // then prompt for filename and call loadSimulationParameters
        System.out.print("Do you want to load simulation parameters from a file (y/n): ");
        userAnswer = input.nextLine().charAt(0);
        if (userAnswer == 'y' || userAnswer == 'Y') {
            System.out.print("Enter filename to load: ");
            fileName = input.nextLine();
            loadSimulationParameters(fileName);
        }

        // prompts the user to enter the simulation parameters
        if (simulationParameters == null) {
            simulationParameters = new int[Config.SIM_PARAMS.length];
            for (int i = 0; i < Config.SIM_PARAMS.length; i++) {
                System.out.print("Enter " + Config.SIM_PARAMS[i] + ": ");
                simulationParameters[i] = input.nextInt();
            }
            input.nextLine(); // read and ignore remaining newline
        }

        // if seed is > 0 then set the random number generator to seed
        if (simulationParameters[indexForParam("seed")] > 0) {
            randGen.setSeed(simulationParameters[indexForParam("seed")]);
        }

        // save simulation parameters in local variables to help make code
        // more readable.
        int oceanWidth = simulationParameters[indexForParam("ocean_width")];
        int oceanHeight = simulationParameters[indexForParam("ocean_height")];
        int startingFish = simulationParameters[indexForParam("starting_fish")];
        int startingSharks = simulationParameters[indexForParam("starting_sharks")];
        int fishBreed = simulationParameters[indexForParam("fish_breed")];
        int sharksBreed = simulationParameters[indexForParam("sharks_breed")];
        int sharksStarve = simulationParameters[indexForParam("sharks_starve")];

        // create parallel arrays to track fish and sharks
        fish = new int[oceanHeight][oceanWidth];
        fishMoved = new boolean[oceanHeight][oceanWidth];
        sharks = new int[oceanHeight][oceanWidth];
        sharksMoved = new boolean[oceanHeight][oceanWidth];
        starve = new int[oceanHeight][oceanWidth];

        // make sure fish, sharks and starve arrays are empty (call emptyArray)
        emptyArray(fish);
        emptyArray(sharks);
        emptyArray(starve);

        // place the initial fish and sharks and print out the number
        int numFish = placeFish(fish, startingFish, fishBreed, randGen);
        System.out.println("Placed " + numFish + " fish.");
        int numSharks = placeSharks(fish, sharks, startingSharks, sharksBreed, randGen);
        System.out.println("Placed " + numSharks + " sharks.");

        int currentChronon = 1;
        ArrayList<int[]> history = new ArrayList<int[]>(); // The ArrayList containing the number of
                                                           // fish and number of sharks at each
                                                           // chronon.

        // simulation ends when no more sharks or fish remain
        boolean simulationEnd = (numFish <= 0 || numSharks <= 0);


        while (!simulationEnd) {
            // print out the locations of the fish and sharks
            showFishAndSharks(currentChronon, fish, sharks);
            System.out.println("fish:" + numFish + " sharks:" + numSharks);

            // prompt user for Enter, # of chronon, or 'end'
            // Enter advances to next chronon, a number
            // entered means run that many chronon,
            // 'end' will end the simulation
            System.out.print("Press Enter, # of chronon, or 'end': ");
            String response = input.nextLine().trim();

            if (response.equalsIgnoreCase("end")) {
                break; // leave simulation loop
            }

            int numChronon = 1;
            try {
                numChronon = Integer.parseInt(response);
            } catch (Exception e) {
                numChronon = 1;
            }
            for (int counter = 0; counter < numChronon; counter++) {
                if (numSharks <= 0 || numFish <= 0) {
                    break;
                }
                // clear fishMoved and sharksMoved from previous chronon
                // TODO Milestone 1
                clearMoves(fishMoved);
                clearMoves(sharksMoved);

                // call fishSwimAndBreed
                int fsb = fishSwimAndBreed(fish, sharks, fishMoved, fishBreed, randGen);
                // TODO Milestone 2

                // call sharksHuntAndBreed
                int shb = sharksHuntAndBreed(fish, sharks, fishMoved, sharksMoved, sharksBreed,
                    starve, sharksStarve, randGen);

                int[] historyItems = new int[Config.NUM_HISTORY_ITEMS];
                historyItems[Config.HISTORY_CHRONON_INDEX] = currentChronon;
                historyItems[Config.HISTORY_NUM_FISH_INDEX] = numFish;
                historyItems[Config.HISTORY_NUM_SHARKS_INDEX] = numSharks;
                history.add(historyItems);
                // increment current chronon and count the current number of fish and sharks

                currentChronon++;
                numFish = countCreatures(fish);
                numSharks = countCreatures(sharks);
                // if all the fish or sharks are gone then end simulation
                simulationEnd = numFish <= 0 || numSharks <= 0;
            }
        } ;
        int[] historyItems = new int[Config.NUM_HISTORY_ITEMS];
        historyItems[Config.HISTORY_CHRONON_INDEX] = currentChronon;
        historyItems[Config.HISTORY_NUM_FISH_INDEX] = numFish;
        historyItems[Config.HISTORY_NUM_SHARKS_INDEX] = numSharks;
        history.add(historyItems);
        // print the final ocean contents
        showFishAndSharks(currentChronon, fish, sharks);
        System.out.println("fish:" + numFish + " sharks:" + numSharks);


        // Print out why the simulation ended.
        if (numSharks <= 0) {
            System.out.println("Wa-Tor simulation ended since no sharks remain.");
        } else if (numFish <= 0) {
            System.out.println("Wa-Tor simulation ended since no fish remain.");
        } else {
            System.out.println("Wa-Tor simulation ended at user request.");
        }

        // If the user was prompted to enter simulation parameters
        // then prompt the user to see if they would like to save them.
        // If the user enters a y or Y as the only non-whitespace characters
        // then prompt for filename and save, otherwise don't save parameters.
        // call saveSimulationParameters to actually save the parameters to the file.
        // If saveSimulationParameters throws an IOException then catch it and
        // repeat the code to prompt asking the user if they want to save
        // the simulation parameters.

        // Asking user to save the simulationParameters or not and also has try and catch block to
        // catch the errors thrown by saveSimulationParameters. If error is caught then it prompts
        // the user again to save the simulation parameters.
        boolean isThereAnError1 = false;
        do {
            System.out.print("Save simulation parameters (y/n): ");
            userAnswer = input.nextLine().charAt(0);
            if (userAnswer == 'y' || userAnswer == 'Y') {
                System.out.print("Enter filename: ");
                String filename = input.nextLine().trim();
                try {
                    saveSimulationParameters(simulationParameters, filename);
                    isThereAnError1 = false;
                } catch (Exception e) {
                    isThereAnError1 = true;
                    System.out.print("Unable to save to: " + filename);
                }
            } else {
                isThereAnError1 = false;
            }

        } while (isThereAnError1 == true);


        // Always prompt the user to see if they would like to save a
        // population chart of the simulation.
        // If the user enters a y or Y as the only non-whitespace characters
        // then prompt for filename and save, otherwise don't save parameters.
        // call savePopulationChart to save the parameters to the file.
        // If savePopulationChart throws an IOException then catch it and
        // repeat the code to prompt asking the user if they want to save
        // the population chart.

        // Asking user to save the population chart or not and also has try and catch block to
        // catch the errors thrown by savePopulationChart. If error is caught then it prompts
        // the user again to save the Population chart.
        boolean isThereAnError2 = false;
        do {
            System.out.print("Save population chart (y/n): ");
            userAnswer = input.nextLine().charAt(0);
            if (userAnswer == 'y' || userAnswer == 'Y') {
                System.out.print("Enter filename: ");
                String filename = input.nextLine();
                try {
                    savePopulationChart(simulationParameters, history, oceanWidth, oceanHeight,
                        filename);
                    isThereAnError2 = false;
                } catch (Exception e) {
                    isThereAnError2 = true;
                    System.out.print("Unable to save to: " + filename);
                }
            } else {
                isThereAnError2 = false;
            }
        } while (isThereAnError2 == true);
        input.close();
    }


    /**
     * This is called when a fish cannot move. This increments the fish's age and notes in the
     * fishMove array that it has been updated this chronon.
     * 
     * @param fish The array containing all the ages of all the fish.
     * @param fishMove The array containing the indicator of whether each fish moved this chronon.
     * @param row The row of the fish that is staying.
     * @param col The col of the fish that is staying.
     */
    public static void aFishStays(int[][] fish, boolean[][] fishMove, int row, int col) {
        if (Config.DEBUG) {
            System.out.printf("DEBUG fish %d,%d stays\n", row, col);
        }
        fish[row][col]++; // increment age of fish
        fishMove[row][col] = true;
    }

    /**
     * The fish moves from fromRow,fromCol to toRow,toCol. The age of the fish is incremented. The
     * fishMove array records that this fish has moved this chronon.
     * 
     * @param fish The array containing all the ages of all the fish.
     * @param fishMove The array containing the indicator of whether each fish moved this chronon.
     * @param fromRow The row the fish is moving from.
     * @param fromCol The column the fish is moving from.
     * @param toRow The row the fish is moving to.
     * @param toCol The column the fish is moving to.
     */
    public static void aFishMoves(int[][] fish, boolean[][] fishMove, int fromRow, int fromCol,
        int toRow, int toCol) {
        if (Config.DEBUG) {
            System.out.printf("DEBUG fish moved from %d,%d to %d,%d\n", fromRow, fromCol, toRow,
                toCol);
        }
        // just move fish
        fish[toRow][toCol] = fish[fromRow][fromCol] + 1; // increment age
        fishMove[toRow][toCol] = true;
        // clear previous location
        fish[fromRow][fromCol] = Config.EMPTY;
        fishMove[fromRow][fromCol] = false;
    }

    /**
     * The fish moves from fromRow,fromCol to toRow,toCol. This fish breeds so its age is reset to
     * 0. The new fish is put in the fromRow,fromCol with an age of 0. The fishMove array records
     * that both fish moved this chronon.
     * 
     * @param fish The array containing all the ages of all the fish.
     * @param fishMove The array containing the indicator of whether each fish moved this chronon.
     * @param fromRow The row the fish is moving from and where the new fish is located.
     * @param fromCol The column the fish is moving from and where the new fish is located.
     * @param toRow The row the fish is moving to.
     * @param toCol The column the fish is moving to.
     */
    public static void aFishMovesAndBreeds(int[][] fish, boolean[][] fishMove, int fromRow,
        int fromCol, int toRow, int toCol) {
        if (Config.DEBUG) {
            System.out.printf("DEBUG fish moved from %d,%d to %d,%d and breed\n", fromRow, fromCol,
                toRow, toCol);
        }
        // move fish, resetting age in new location
        fish[toRow][toCol] = 0;
        fishMove[toRow][toCol] = true;
        // breed
        fish[fromRow][fromCol] = 0; // new fish in previous location
        fishMove[fromRow][fromCol] = true;
    }

    /**
     * This removes the shark from the sharks, sharksMove and starve arrays.
     * 
     * @param sharks The array containing all the ages of all the sharks.
     * @param sharksMove The array containing the indicator of whether each shark moved this
     *        chronon.
     * @param starve The array containing the time in chronon since the sharks last ate.
     * @param row The row the shark is in.
     * @param col The column the shark is in.
     */
    public static void sharkStarves(int[][] sharks, boolean[][] sharksMove, int[][] starve, int row,
        int col) {
        if (Config.DEBUG) {
            System.out.printf("DEBUG shark %d,%d starves\n", row, col);
        }
        sharks[row][col] = Config.EMPTY;
        starve[row][col] = Config.EMPTY;
        sharksMove[row][col] = false;
    }

    /**
     * This is called when a shark cannot move. This increments the shark's age and time since the
     * shark last ate and notes in the sharkMove array that it has been updated this chronon.
     * 
     * @param sharks The array containing all the ages of all the sharks.
     * @param sharksMove The array containing the indicator of whether each shark moved this
     *        chronon.
     * @param starve The array containing the time in chronon since the sharks last ate.
     * @param row The row the shark is in.
     * @param col The column the shark is in.
     */
    public static void sharkStays(int[][] sharks, boolean[][] sharksMove, int[][] starve, int row,
        int col) {
        if (Config.DEBUG) {
            System.out.printf("DEBUG shark %d,%d can't move\n", row, col);
        }
        sharks[row][col]++; // increment age of shark
        starve[row][col]++; // increment time since last ate
        sharksMove[row][col] = true;
    }

    /**
     * This moves a shark from fromRow,fromCol to toRow,toCol. This increments the age and time
     * since the shark last ate and notes that this shark has moved this chronon.
     * 
     * @param sharks The array containing all the ages of all the sharks.
     * @param sharksMove The array containing the indicator of whether each shark moved this
     *        chronon.
     * @param starve The array containing the time in chronon since the sharks last ate.
     * @param fromRow The row the shark is moving from.
     * @param fromCol The column the shark is moving from.
     * @param toRow The row the shark is moving to.
     * @param toCol The column the shark is moving to.
     */
    public static void sharkMoves(int[][] sharks, boolean[][] sharksMove, int[][] starve,
        int fromRow, int fromCol, int toRow, int toCol) {
        if (Config.DEBUG) {
            System.out.printf("DEBUG shark moved from %d,%d to %d,%d\n", fromRow, fromCol, toRow,
                toCol);
        }
        // just move shark
        sharks[toRow][toCol] = sharks[fromRow][fromCol] + 1; // move age
        sharksMove[toRow][toCol] = true;
        starve[toRow][toCol] = starve[fromRow][fromCol] + 1;

        sharks[fromRow][fromCol] = Config.EMPTY;
        sharksMove[fromRow][fromCol] = false;
        starve[fromRow][fromCol] = 0;
    }

    /**
     * The shark moves from fromRow,fromCol to toRow,toCol. This shark breeds so its age is reset to
     * 0 but its time since last ate is incremented. The new shark is put in the fromRow,fromCol
     * with an age of 0 and 0 time since last ate. The fishMove array records that both fish moved
     * this chronon.
     * 
     * @param sharks The array containing all the ages of all the sharks.
     * @param sharksMove The array containing the indicator of whether each shark moved this
     *        chronon.
     * @param starve The array containing the time in chronon since the sharks last ate.
     * @param fromRow The row the shark is moving from.
     * @param fromCol The column the shark is moving from.
     * @param toRow The row the shark is moving to.
     * @param toCol The column the shark is moving to.
     */
    public static void sharkMovesAndBreeds(int[][] sharks, boolean[][] sharksMove, int[][] starve,
        int fromRow, int fromCol, int toRow, int toCol) {

        if (Config.DEBUG) {
            System.out.printf("DEBUG shark moved from %d,%d to %d,%d and breeds\n", fromRow,
                fromCol, toRow, toCol);
        }
        sharks[toRow][toCol] = 0; // reset age in new location
        sharks[fromRow][fromCol] = 0; // new fish in previous location

        sharksMove[toRow][toCol] = true;
        sharksMove[fromRow][fromCol] = true;

        starve[toRow][toCol] = starve[fromRow][fromCol] + 1;
        starve[fromRow][fromCol] = 0;
    }

    /**
     * The shark in fromRow,fromCol moves to toRow,toCol and eats the fish. The sharks age is
     * incremented, time since it last ate and that this shark moved this chronon are noted. The
     * fish is now gone.
     * 
     * @param sharks The array containing all the ages of all the sharks.
     * @param sharksMove The array containing the indicator of whether each shark moved this
     *        chronon.
     * @param starve The array containing the time in chronon since the sharks last ate.
     * @param fish The array containing all the ages of all the fish.
     * @param fishMove The array containing the indicator of whether each fish moved this chronon.
     * @param fromRow The row the shark is moving from.
     * @param fromCol The column the shark is moving from.
     * @param toRow The row the shark is moving to.
     * @param toCol The column the shark is moving to.
     */
    public static void sharkEatsFish(int[][] sharks, boolean[][] sharksMove, int[][] starve,
        int[][] fish, boolean[][] fishMove, int fromRow, int fromCol, int toRow, int toCol) {
        if (Config.DEBUG) {
            System.out.printf("DEBUG shark moved from %d,%d and ate fish %d,%d\n", fromRow, fromCol,
                toRow, toCol);
        }
        // eat fish
        fish[toRow][toCol] = Config.EMPTY;
        fishMove[toRow][toCol] = false;

        // move shark
        sharks[toRow][toCol] = sharks[fromRow][fromCol] + 1; // move age
        sharksMove[toRow][toCol] = true;
        starve[toRow][toCol] = starve[fromRow][fromCol] = 0;

        // clear old location
        sharks[fromRow][fromCol] = Config.EMPTY;
        sharksMove[fromRow][fromCol] = true;
        starve[fromRow][fromCol] = 0;
    }

    /**
     * The shark in fromRow,fromCol moves to toRow,toCol and eats the fish. The fish is now gone.
     * This shark breeds so its age is reset to 0 and its time since last ate is incremented. The
     * new shark is put in the fromRow,fromCol with an age of 0 and 0 time since last ate. That
     * these sharks moved this chronon is noted.
     * 
     * @param sharks The array containing all the ages of all the sharks.
     * @param sharksMove The array containing the indicator of whether each shark moved this
     *        chronon.
     * @param starve The array containing the time in chronon since the sharks last ate.
     * @param fish The array containing all the ages of all the fish.
     * @param fishMove The array containing the indicator of whether each fish moved this chronon.
     * @param fromRow The row the shark is moving from.
     * @param fromCol The column the shark is moving from.
     * @param toRow The row the shark is moving to.
     * @param toCol The column the shark is moving to.
     */
    public static void sharkEatsFishAndBreeds(int[][] sharks, boolean[][] sharksMove,
        int[][] starve, int[][] fish, boolean[][] fishMove, int fromRow, int fromCol, int toRow,
        int toCol) {
        if (Config.DEBUG) {
            System.out.printf("DEBUG shark moved from %d,%d and ate fish %d,%d and breed\n",
                fromRow, fromCol, toRow, toCol);
        }
        // shark eats fish and may breed
        // eat fish
        fish[toRow][toCol] = Config.EMPTY;
        fishMove[toRow][toCol] = false;

        // move to new location
        sharks[toRow][toCol] = 0; // reset age in new location
        sharksMove[toRow][toCol] = true;
        starve[toRow][toCol] = 0;

        // breed
        sharks[fromRow][fromCol] = 0; // new shark in previous location
        sharksMove[fromRow][fromCol] = true;
        starve[fromRow][fromCol] = 0;
    }

    /**
     * This sets all elements within the array to Config.EMPTY. This does not assume any array size
     * but uses the .length attribute of the array. If arr is null the method prints an error
     * message and returns.
     * 
     * @param arr The array that only has EMPTY elements when method has executed.
     */
    public static void emptyArray(int[][] arr) {
        if (arr == null) {
            System.out.println("emptyArray arr is null");
            return;
        }
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                arr[row][col] = Config.EMPTY;
            }
        }
    }

    /**
     * This sets all elements within the array to false, indicating not moved this chronon. This
     * does not assume any array size but uses the .length attribute of the array. If arr is null
     * the method prints a message and returns.
     * 
     * @param arr The array will have only false elements when method completes.
     */
    public static void clearMoves(boolean[][] arr) {
        if (arr == null) {
            System.out.println("clearMoves arr is null");
            return;
        }
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                arr[row][col] = false;
            }
        }
    }

    /**
     * Shows the locations of all the fish and sharks noting a fish with Config.FISH_MARK, a shark
     * with Config.SHARK_MARK and empty water with Config.WATER_MARK. At the top is a title
     * "Chronon: " with the current chronon and at the bottom is a count of the number of fish and
     * sharks. Example of a 3 row, 5 column ocean. Note every mark is also followed by a space.
     * Chronon: 1 O . . O . . . . . O fish:7 sharks:3
     * 
     * @param chronon The current chronon.
     * @param fish The array containing all the ages of all the fish.
     * @param sharks The array containing all the ages of all the sharks.
     */

    // this method checks whether there is a fish or a shark at every row and column in fish and
    // shark array respectively. if there is , then it prints out the respective creature, and if
    // there's nothing there then it prints out the WATER_Char
    public static void showFishAndSharks(int chronon, int[][] fish, int[][] sharks) {
        // TODO Milestone 1
        int i;
        int j;;
        System.out.println("Chronon: " + chronon);
        for (i = 0; i < fish.length; i++) {
            for (j = 0; j < fish[0].length; j++) {
                if (i != fish.length) {
                    if (fish[i][j] != Config.EMPTY) {
                        System.out.print((Config.FISH_MARK) + " ");
                    } else if (sharks[i][j] >= 0) {
                        System.out.print((Config.SHARK_MARK) + " ");
                    } else {
                        System.out.print((Config.WATER_MARK) + " ");
                    }
                } else {
                    if (fish[i][j] != Config.EMPTY) {
                        System.out.print((Config.FISH_MARK));
                    } else if (sharks[i][j] >= 0) {
                        System.out.print((Config.SHARK_MARK));
                    } else {
                        System.out.print((Config.WATER_MARK));
                    }

                }

            }
            System.out.println("");
        }

    }

    /**
     * This places up to startingFish fish in the fish array. This randomly chooses a location and
     * age for each fish. Algorithm: For each fish this tries to place reset the attempts to place
     * the particular fish to 0. Try to place a single fish up to Config.MAX_PLACE_ATTEMPTS times
     * Randomly choose a row, then column using randGen.nextInt( ) with the appropriate fish array
     * dimension as the parameter. Increment the number of attempts to place the fish. If the
     * location is empty in the fish array then place the fish in that location, randomly choosing
     * its age from 0 up to and including fishBreed. If the location is already occupied, generate
     * another location and try again. If a single fish is not placed after
     * Config.MAX_PLACE_ATTEMPTS times, then stop trying to place the rest of the fish. Return the
     * number of fish actually placed.
     * 
     * @param fish The array containing all the ages of all the fish.
     * @param startingFish The number of fish to attempt to place in the fish array.
     * @param fishBreed The age at which fish breed.
     * @param randGen The random number generator.
     * @return the number of fish actually placed.
     */

    // This method tries to randomly place fish in the fish array, it can place upto startingFish
    // and also the age of fish should be less than or equal to fishBreed. Also, the position of the
    // fish is randomly decided on the board.
    public static int placeFish(int[][] fish, int startingFish, int fishBreed, Random randGen) {
        int numFishPlaced = 0;
        int tries = 0;
        int i;
        int j;
        int k;
        int fishAge;
        // TODO Milestone 1
        for (k = 0; k < startingFish; k++) {

            while (tries < Config.MAX_PLACE_ATTEMPTS) {
                i = randGen.nextInt(fish.length); // ycoord
                j = randGen.nextInt(fish[0].length); // xcoord
                if (fish[i][j] == Config.EMPTY) {
                    fish[i][j] = randGen.nextInt(fishBreed + 1);
                    numFishPlaced++;
                    tries++;
                    if (tries == Config.MAX_PLACE_ATTEMPTS) {
                        return numFishPlaced;
                    }
                    break;
                } else {
                    tries++;
                    if (tries == Config.MAX_PLACE_ATTEMPTS) {
                        return numFishPlaced;
                    }

                }

            }
            tries = 0;
        }
        return numFishPlaced;
    }

    /**
     * This places up to startingSharks sharks in the sharks array. This randomly chooses a location
     * and age for each shark. Algorithm: For each shark this tries to place reset the attempts to
     * place the particular shark to 0. Try to place a single shark up to Config.MAX_PLACE_ATTEMPTS
     * times Randomly choose a row, then column using randGen.nextInt( ) with the appropriate shark
     * array dimension as the parameter. Increment the number of attempts to place the shark. If the
     * location is empty in both the fish array and sharks array then place the shark in that
     * location, randomly choosing its age from 0 up to and including sharkBreed. If the location is
     * already occupied, generate another location and try again. If a single shark is not placed
     * after Config.MAX_PLACE_ATTEMPTS times, then stop trying to place the rest of the sharks.
     * Return the number of sharks actually placed. *
     * 
     * @param fish The array containing all the ages of all the fish.
     * @param sharks The array containing all the ages of all the sharks.
     * @param startingSharks The number of sharks to attempt to place in the sharks array.
     * @param sharksBreed The age at which sharks breed.
     * @param randGen The random number generator.
     * @return the number of sharks actually placed.
     */
    // This method tries to randomly place sharks in the sharks array, it can place upto
    // startingSharks
    // and also the age of sharks should be less than or equal to sharksBreed. Also, the position of
    // the
    // sharks is randomly decided on the board, with the condition that there is no fish or shark
    // already present at that location.
    public static int placeSharks(int[][] fish, int[][] sharks, int startingSharks, int sharksBreed,
        Random randGen) {

        int numSharksPlaced = 0;
        int tries = 0;
        int i;
        int j;
        int k;
        // TODO Milestone 1
        for (k = 0; k < startingSharks; k++) {
            while (tries < Config.MAX_PLACE_ATTEMPTS) {

                i = randGen.nextInt(sharks.length); // ycoord
                j = randGen.nextInt(sharks[0].length); // xcoord
                if (sharks[i][j] == Config.EMPTY && fish[i][j] == Config.EMPTY) {
                    sharks[i][j] = randGen.nextInt(sharksBreed + 1);
                    numSharksPlaced++;
                    tries++;
                    if (tries == Config.MAX_PLACE_ATTEMPTS) {
                        return numSharksPlaced;
                    }
                    break;
                } else {
                    tries++;
                    if (tries == Config.MAX_PLACE_ATTEMPTS) {
                        return numSharksPlaced;
                    }
                }

            }
            tries = 0;
        }

        return numSharksPlaced;
    }

    /**
     * This counts the number of fish or the number of sharks depending on the array passed in.
     * 
     * @param fishOrSharks Either an array containing the ages of all the fish or an array
     *        containing the ages of all the sharks.
     * @return The number of fish or number of sharks, depending on the array passed in.
     */
    // This loops through the passed array and checks how many places are not empty.
    // if not empty then it adds 1, summing the no. of creatures in the whole array.
    public static int countCreatures(int[][] fishOrSharks) {
        int numCreatures = 0;
        // TODO Milestone 1
        for (int i = 0; i < fishOrSharks.length; i++) {
            for (int j = 0; j < fishOrSharks[0].length; j++) {
                if (fishOrSharks[i][j] != Config.EMPTY) {
                    numCreatures++;
                }
            }
        }
        return numCreatures;
    }

    /**
     * This returns a list of the coordinates (row,col) of positions around the row, col parameters
     * that do not contain a fish or shark. The positions that are considered are directly above,
     * below, left and right of row, col and IN THAT ORDER. Where 0,0 is the upper left corner when
     * fish and sharks arrays are printed out. Remember that creatures moving off one side of the
     * array appear on the opposite side. For example, those moving left off the array appear on the
     * right side and those moving down off the array appear at the top.
     * 
     * @param fish A non-Config.EMPTY value indicates the age of the fish occupying the location.
     * @param sharks A non-Config.EMPTY value indicates the age of the shark occupying the location.
     * @param row The row of a creature trying to move.
     * @param col The column of a creature trying to move.
     * @return An ArrayList containing 0 to 4, 2-element arrays with row,col coordinates of
     *         unoccupied locations. In each coordinate array the 0 index is the row, the 1 index is
     *         the column.
     */
    public static ArrayList<int[]> unoccupiedPositions(int[][] fish, int[][] sharks, int row,
        int col) {
        ArrayList<int[]> unoccupied = new ArrayList<>();
        // Up


        int newRow = row - 1;
        int newCol = col;
        // if the coordinate is at the top of board and you are going up
        if (newRow < 0) {
            newRow = fish.length - 1;
        }
        if (fish[newRow][newCol] == Config.EMPTY && sharks[newRow][newCol] == Config.EMPTY) {
            unoccupied.add(new int[] {newRow, newCol});
        }

        // Down

        newRow = row + 1;
        newCol = col;
        // if the coordinate is at the bottom of the board and you are going down
        if (newRow >= fish.length) {
            newRow = 0;
        }
        if (fish[newRow][newCol] == Config.EMPTY && sharks[newRow][newCol] == Config.EMPTY) {
            unoccupied.add(new int[] {newRow, newCol});
        }

        // left
        newRow = row;
        newCol = col - 1;
        // if the coordinate is at the leftmost end of the board and you are going left
        if (newCol < 0) {
            newCol = fish[row].length - 1;
        }
        if (fish[newRow][newCol] == Config.EMPTY && sharks[newRow][newCol] == Config.EMPTY) {
            unoccupied.add(new int[] {newRow, newCol});
        }

        // Right
        newRow = row;
        newCol = col + 1;
        // if you are at the rightmost side and going right
        if (newCol >= fish[row].length) {
            newCol = 0;
        }
        if (fish[newRow][newCol] == Config.EMPTY && sharks[newRow][newCol] == Config.EMPTY) {
            unoccupied.add(new int[] {newRow, newCol});
        }
        return unoccupied;
    }

    /**
     * This randomly selects, with the Random number generator passed as a parameter, one of
     * elements (array of int) in the neighbors list. If neighbors is 0 then null is returned. If
     * neighbors contains 1 then that element is returned. The randGen parameter is only used to
     * select 1 element from a neighbors list containing more than 1 element.
     * 
     * If neighbors or randGen is null then an error message is printed out and null is returned.
     * 
     * @param neighbors A list of potential neighbors to choose from.
     * @param randGen The random number generator used throughout the simulation.
     * @return A int[] containing the coordinates of a creatures move or null as specified above.
     */

    // this method selects a postion from 0 to 4 options in an array. if there is only one position
    // then it directly takes that.
    // if there are more than 1 position than it randomly selects a position.
    public static int[] chooseMove(ArrayList<int[]> neighbors, Random randGen) {
        // TODO Milestone 2
        int neighborElement;
        int[] a;
        if (neighbors == null || randGen == null) {
            System.err.println("Error in chooseMove");
            return null;
        } else if (neighbors.size() == 0) {
            return null;
        } else if (neighbors.size() == 1) {
            a = neighbors.get(0);
            return a;
        } else {
            neighborElement = randGen.nextInt(neighbors.size());
            a = neighbors.get(neighborElement);
            return a;
        }
    }

    /**
     * This attempts to move each fish each chronon.
     * 
     * This is a key method with a number of parameters. Check that the parameters are valid prior
     * to writing the code to move a fish. The parameters are checked in the order they appear in
     * the parameter list. If any of the array parameters are null or not at least 1 element in size
     * then a helpful error message is printed out and -1 is returned. An example message for an
     * invalid fish array is "fishSwimAndBreed Invalid fish array: Null or not at least 1 in each
     * dimension.". Testing will not check the content of the message but will check whether the
     * correct number is returned for the situation. Passing this test means we know fish[0] exists
     * and so won't cause a runtime error and also that fish[0].length is the width. For this
     * project it is safe to assume rectangular arrays (arrays where all the rows are the same
     * length). If fishBreed is less than zero a helpful error message is printed out and -2 is
     * returned. If randGen is null then a helpful error message is printed out and -3 is returned.
     * 
     *
     * Algorithm: for each fish that has not moved this chronon get the available unoccupied
     * positions for the fish to move (call unoccupiedPositions) choose a move from those positions
     * (call chooseMove) Based on the move chosen, either the fish stays (call aFishStays) fish
     * moves (call aFishMoves) or fish moves and breeds (call aFishMovesAndBreeds)
     * 
     * 
     * @param fish The array containing all the ages of all the fish.
     * @param sharks The array containing all the ages of all the sharks.
     * @param fishMove The array containing the indicator of whether each fish moved this chronon.
     * @param fishBreed The age in chronon that a fish must be to breed.
     * @param randGen The instance of the Random number generator.
     * @return -1, -2, -3 for invalid parameters as specified above. After attempting to move all
     *         fish 0 is returned indicating success.
     */
    public static int fishSwimAndBreed(int[][] fish, int[][] sharks, boolean[][] fishMove,
        int fishBreed, Random randGen) {
        // Checking fish array
        if (fish == null || fish.length < 1 || fish[0].length < 1) {
            System.err.println("fishSwimAndBreed Invalid fish array: Null or not at least 1 in \r\n"
                + "     * each dimension.");
            return -1;
        }

        // Checking shark for null or size < 1 array
        if (sharks == null || sharks.length < 1 || sharks[0].length < 1) {

            System.err
                .println("fishSwimAndBreed Invalid sharks array: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -1;
        }
        // Checking fishMove for null or size < 1 array
        if (fishMove == null || fishMove.length < 1 || fishMove[0].length < 1) {

            System.err
                .println("fishSwimAndBreed Invalid fishMove array: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -1;
        }
        if (fishBreed < 0) {
            System.err.println("fishSwimAndBreed Invalid fishBreed: Null or not at least 1 in \r\n"
                + "     * each dimension.");
            return -2;
        }
        if (randGen == null) {
            System.err.println("fishSwimAndBreed Invalid randGen: Null or not at least 1 in \r\n"
                + "     * each dimension.");
            return -3;
        }
        for (int i = 0; i < fish.length; i++) { // going through the whole fish array.
            for (int j = 0; j < fish[i].length; j++) {
                if (fish[i][j] != Config.EMPTY) { // if there is a fish
                    if (fishMove[i][j] == false) { // checking if the fish moved or not.
                        ArrayList<int[]> unoccupiedCoordinates =
                            unoccupiedPositions(fish, sharks, i, j); // to find the unoccupied
                                                                     // coordinates.
                        int[] selectedCoordinates = chooseMove(unoccupiedCoordinates, randGen); // using
                                                                                                // chooseMove
                                                                                                // to
                                                                                                // select
                                                                                                // a
                                                                                                // coordinate.
                        if (selectedCoordinates != null) { // if there is a space to move
                            if (fish[i][j] < fishBreed) {// checking if a fish can breed or not.
                                aFishMoves(fish, fishMove, i, j, selectedCoordinates[0],
                                    selectedCoordinates[1]);
                            } else {
                                aFishMovesAndBreeds(fish, fishMove, i, j, selectedCoordinates[0],
                                    selectedCoordinates[1]);
                            }
                        } else {// if there is no space to move.
                            aFishStays(fish, fishMove, i, j);
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     * This returns a list of the coordinates (row,col) of positions around the row, col parameters
     * that contain a fish. The positions that are considered are directly above, below, left and
     * right of row, col and IN THAT ORDER. Where 0,0 is the upper left corner when fish array is
     * printed out. Remember that sharks moving off one side of the array appear on the opposite
     * side. For example, those moving left off the array appear on the right side and those moving
     * down off the array appear at the top.
     * 
     * @param fish A non-Config.EMPTY value indicates the age of the fish occupying a location.
     * @param row The row of a hungry shark.
     * @param col The column of a hungry shark.
     * @return An ArrayList containing 0 to 4, 2-element arrays with row,col coordinates of fish
     *         locations. In each coordinate array the 0 index is the row, the 1 index is the
     *         column.
     */
    public static ArrayList<int[]> fishPositions(int[][] fish, int row, int col) {
        ArrayList<int[]> fishPositions = new ArrayList<>();

        // up-------------------------
        int newRow = row - 1;
        int newCol = col;
        // if the coordinate is at the top of board and you are going
        if (newRow < 0) {
            newRow = fish.length - 1;
        }
        if (fish[newRow][newCol] != Config.EMPTY) {
            fishPositions.add(new int[] {newRow, newCol});
        }

        // Down------------------
        newRow = row + 1;
        newCol = col;
        // if the coordinate is at the bottom of the board and you are going
        if (newRow >= fish.length) {
            newRow = 0;
        }
        if (fish[newRow][newCol] != Config.EMPTY) {
            fishPositions.add(new int[] {newRow, newCol});
        }

        // left------------------------
        newRow = row;
        newCol = col - 1;
        // if the coordinate is at the leftmost end of the board and you are going
        if (newCol < 0) {
            newCol = fish[row].length - 1;
        }
        if (fish[newRow][newCol] != Config.EMPTY) {
            fishPositions.add(new int[] {newRow, newCol});
        }

        // Right--------------------
        newRow = row;
        newCol = col + 1;
        // if you are at the rightmost side and going right--------
        if (newCol >= fish[row].length) {
            newCol = 0;
        }
        if (fish[newRow][newCol] != Config.EMPTY) {
            fishPositions.add(new int[] {newRow, newCol});
        }

        return fishPositions;
    }

    /**
     * This attempts to move each shark each chronon.
     *
     * This is a key method with a number of parameters. Check that the parameters are valid prior
     * to writing the code to move a shark. The parameters are checked in the order they appear in
     * the parameter list. If any of the array parameters are null or not at least 1 element in size
     * then a helpful error message is printed out and -1 is returned. An example message for an
     * invalid fish array is "sharksHuntAndBreed Invalid fish array: Null or not at least 1 in each
     * dimension.". Testing will not check the content of the message but will check whether the
     * correct number is returned for the situation. Passing this test means we know fish[0] exists
     * and so won't cause a runtime error and also that fish[0].length is the width. For this
     * project it is safe to assume rectangular arrays (arrays where all the rows are the same
     * length). If sharksBreed or sharksStarve are less than zero a helpful error message is printed
     * out and -2 is returned. If randGen is null then a helpful error message is printed out and -3
     * is returned.
     * 
     * Algorithm to move a shark: for each shark that has not moved this chronon check to see if the
     * shark has starved, if so call sharkStarves get the available positions of neighboring fish
     * (call fishPositions) if there are no neighboring fish to eat then determine available
     * positions (call unoccupiedPositions) choose a move (call chooseMove) and based on the move
     * chosen call sharkStays, sharkMoves or sharkMovesAndBreeds appropriately, using the sharkBreed
     * parameter to see if a shark breeds. else if there are neighboring fish then choose the move
     * (call chooseMove), eat the fish (call sharkEatsFish or sharkEatsFishAndBreeds) appropriately.
     * return 0, meaning success.
     * 
     * @param fish The array containing all the ages of all the fish.
     * @param sharks The array containing all the ages of all the sharks.
     * @param fishMove The array containing the indicator of whether each fish moved this chronon.
     * @param sharksMove The array containing the indicator of whether each shark moved this
     *        chronon.
     * @param sharksBreed The age the sharks must be in order to breed.
     * @param starve The array containing the time in chronon since the sharks last ate.
     * @param sharksStarve The time in chronon since the sharks last ate that results in them
     *        starving to death.
     * @param randGen The instance of the Random number generator.
     * @return -1, -2, -3 for invalid parameters as specified above. After attempting to move all
     *         sharks 0 is returned indicating success.
     */
    public static int sharksHuntAndBreed(int[][] fish, int[][] sharks, boolean[][] fishMove,
        boolean[][] sharksMove, int sharksBreed, int[][] starve, int sharksStarve, Random randGen) {
        if (fish == null || fish.length < 1 || fish[0].length < 1) {

            System.err
                .println("sharksHuntAndBreed Invalid fish array: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -1;
        }
        // Checking shark for null or size < 1 array
        if (sharks == null || sharks.length < 1 || sharks[0].length < 1) {

            System.err
                .println("sharksHuntAndBreed Invalid sharks array: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -1;

        }
        // Checking fishMove for null or size < 1 array
        if (fishMove == null || fishMove.length < 1 || fishMove[0].length < 1) {

            System.err
                .println("sharksHuntAndBreed Invalid fishMove array: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -1;
        }
        if (sharksMove == null || sharksMove.length < 1 || sharksMove[0].length < 1) {

            System.err.println(
                "sharksHuntAndBreed Invalid sharksMove array: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -1;
        }

        if (sharksBreed < 0) {
            System.err
                .println("sharksHuntAndBreed Invalid sharksBreed: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -2;
        }
        if (starve == null || starve.length < 1 || starve[0].length < 1) {

            System.err
                .println("sharksHuntAndBreed Invalid starve array: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -1;
        }

        if (sharksStarve < 0) {
            System.err
                .println("sharksHuntAndBreed Invalid sharksStarve: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -2;
        }

        if (randGen == null) {
            System.err
                .println("sharksHuntAndBreed Invalid fish array: Null or not at least 1 in \r\n"
                    + "     * each dimension.");
            return -3;
        }

        for (int i = 0; i < sharks.length; i++) { // Going through the whole shark array.
            for (int j = 0; j < sharks[0].length; j++) {
                if (sharks[i][j] != Config.EMPTY) {// checking for a shark
                    if (sharksMove[i][j] == false) {
                        if (starve[i][j] >= sharksStarve) {// if shark starves
                            sharkStarves(sharks, sharksMove, starve, i, j);
                        } else { // if it doesnt then execute eveything alse
                            ArrayList<int[]> fishCoords = fishPositions(fish, i, j);
                            if (fishCoords.size() == 0) {// if there are no fish around
                                ArrayList<int[]> unoccupiedCoordinates =
                                    unoccupiedPositions(fish, sharks, i, j);
                                int[] selectedCoordinates =
                                    chooseMove(unoccupiedCoordinates, randGen);
                                if (selectedCoordinates == null) { // checking if there is place for
                                                                   // shark to move
                                    sharkStays(sharks, sharksMove, starve, i, j);
                                } else {// if there is space.
                                    if (sharks[i][j] < sharksBreed) { // checking if sharks can
                                                                      // breed
                                        sharkMoves(sharks, sharksMove, starve, i, j,
                                            selectedCoordinates[0], selectedCoordinates[1]);
                                    } else {
                                        sharkMovesAndBreeds(sharks, sharksMove, starve, i, j,
                                            selectedCoordinates[0], selectedCoordinates[1]);
                                    }
                                }
                            } else { // if there are fish around
                                int[] selectedCoordinates = chooseMove(fishCoords, randGen);
                                if (sharks[i][j] < sharksBreed) { // checking if shark can breed.
                                    sharkEatsFish(sharks, sharksMove, starve, fish, fishMove, i, j,
                                        selectedCoordinates[0], selectedCoordinates[1]);
                                } else {
                                    sharkEatsFishAndBreeds(sharks, sharksMove, starve, fish,
                                        fishMove, i, j, selectedCoordinates[0],
                                        selectedCoordinates[1]);
                                }
                            }
                        }
                    }
                }
            }
        }

        // TODO Milestone 2
        return 0;
    }

    /**
     * This looks up the specified paramName in this Config.SIM_PARAMS array, ignoring case. If
     * found then the array index is returned.
     * 
     * @param paramName The parameter name to look for, ignoring case.
     * @return The index of the parameter name if found, otherwise returns -1.
     */
    public static int indexForParam(String paramName) {
        for (int i = 0; i < Config.SIM_PARAMS.length; i++) {
            if (paramName.equalsIgnoreCase(Config.SIM_PARAMS[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Writes the simulationParameters to the file named filename. The format of the file is the
     * name of the parameter and value on one line separated by =. The order of the lines does not
     * matter. Algorithm: Open the file named filename for writing. Any IOExceptions should be
     * handled with a throws clause and not a try-catch block. For each of the simulation parameters
     * whose names are found in Config.SIM_PARAMS Write out the name of the parameter, =, the
     * parameter value and then newline. Close the file.
     * 
     * Example contents of file: seed=233 ocean_width=20 ocean_height=10 starting_fish=100
     * starting_sharks=10 fish_breed=3 sharks_breed=10 sharks_starve=4
     * 
     * @param simulationParameters The values of the parameters to write out.
     * @param filename The name of the file to write the parameters to.
     */

    // This method creates a file named 'fileName' and assigns the parameter listed in
    // Config.SIM_PARAMS with the corresponding value in simulationParameters, and save them in the
    // file.
    public static void saveSimulationParameters(int[] simulationParameters, String filename)
        throws IOException {

        FileWriter writer = new FileWriter(new File(filename)); // creating a file named file name
        for (int i = 0; i < Config.SIM_PARAMS.length; i++) {
            writer.write(Config.SIM_PARAMS[i] + "=" + simulationParameters[i]); // assigns
                                                                                // parameters
                                                                                // mentioned in
                                                                                // Config.SIM_PARAMS
                                                                                // with the values
                                                                                // in
                                                                                // simulationParameters.
        }
        writer.close();
    }


    /**
     * This loads the simulation parameters from the file named filename. The names of the
     * parameters are in the Config.SIM_PARAMS array and the array returned from this method is a
     * parallel array containing the parameter values. The name corresponds to the value with the
     * same index. Algorithm: Try to open filename for reading. If the FileNotFoundException is
     * thrown print the message printing out the filename without < > and return null;
     * 
     * File not found: <filename>
     * 
     * Read lines from the file as long as each line contains "=". As soon as a line does not
     * contain "=" then stop reading from the file. The order of the lines in the file is not
     * significant. In a line the part before "=" is the name and the part after is the value. The
     * separate method you wrote in P7 is helpful here. Find the index of the name within
     * Config.SIM_PARAMS (call indexForParam). If the index is found then convert the value into an
     * int and store in the corresponding index in the array of int that will be returned from this
     * method. If the index is not found then print out the message followed by the entire line
     * without the < >.
     * 
     * Unrecognized: <line>
     * 
     * @param filename The name of the from which to read simulation parameters.
     * @return The array of parameters.
     */

    // This loads the simulation parameters from a file named 'filename'
    public static int[] loadSimulationParameters(String filename) {
        int[] params = null;
        params = new int[15];
        FileInputStream fileByteStream = null;
        Scanner reader = null;
        int i = 0;
        String name;
        try {
            fileByteStream = new FileInputStream(filename); // creating an input stream through the
                                                            // file named 'filename'
            reader = new Scanner(fileByteStream); // reader reads through the file
            reader.useDelimiter("="); // As we have to read only till the '=' , therefore taking '='
                                      // as the delimeter
            for (i = 0; i < Config.SIM_PARAMS.length; i++) {
                name = reader.nextLine();
                params[i] = indexForParam(name);
                reader.nextLine(); // this is used to get to the next line after one parameter.
            }
        } catch (FileNotFoundException e) { // if file throws an IOException when opening, then the
                                            // catch blocks catches that exception.
            System.out.println("File not found: " + filename);
            return null;
        }
        reader.close();
        return params;
    }

    /**
     * This writes the simulation parameters and the chart of the simulation to a file. If
     * simulationParameters is null or history is null then print an error message and leave the
     * method before any output. If filename cannot be written to then this method should throw an
     * IOException. *
     * 
     * Parameters are written first, 1 per line in the file. Use an = to separate the name from the
     * value. Then write a blank line and then the Population Chart. Example file contents are:
     * seed=111 ocean_width=5 ocean_height=2 starting_fish=6 starting_sharks=2 fish_breed=3
     * sharks_breed=3 sharks_starve=3
     * 
     * Population Chart Numbers of fish(.) and sharks(O) in units of 1. F 6,S 2 1)OO.... F 4,S 2
     * 2)OO.. F 2,S 4 3)..OO F 1,S 4 4).OOO F 0,S 4 5)OOOO
     * 
     * Looking at one line in detail F 6,S 2 1)OO.... ^^^^^^ 6 fish (the larger of sharks or fish is
     * in the background) ^^ 2 sharks ^^^^^ chronon 1 ^^^^ the number of sharks ^^^^ the number of
     * fish
     * 
     * The unit size is determined by dividing the maximum possible number of a creature (oceanWidth
     * * oceanHeight) by Config.POPULATION_CHART_WIDTH. Then iterate through the history printing
     * out the number of fish and sharks. PrintWriter has a printf method that is helpful for
     * formatting. printf("F%3d", 5) prints "F 5", a 5 right justified in a field of size 3.
     * 
     * @param simulationParameters The array of simulation parameter values.
     * @param history The ArrayList containing the number of fish and number of sharks at each
     *        chronon.
     * @param oceanWidth The width of the ocean.
     * @param oceanHeight The height of the ocean.
     * @param filename The name of the file to write the parameters and chart to.
     */


    public static void savePopulationChart(int[] simulationParameters, ArrayList<int[]> history,
        int oceanWidth, int oceanHeight, String filename) throws IOException {
        // TODO Milestone 3
        if (simulationParameters == null) {
            System.out.println("In savePopulationChart simulationParameters are null");
            return;
        }
        if (history == null) {
            System.out.println("In savePopulationChart history is null");
        }
        PrintWriter writer = new PrintWriter(new File(filename));
        for (int i = 0; i < simulationParameters.length; i++) {
            writer.write(Config.SIM_PARAMS[i] + "=" + simulationParameters[i] + "\n");
        }
        int unitSize = (oceanWidth * oceanHeight) / (Config.POPULATION_CHART_WIDTH);
        if (unitSize < 1) {
            unitSize = 1;
        }
        writer.println("\nPopulation Chart");
        writer.println("Numbers of fish(" + Config.FISH_MARK + ") and sharks(" + Config.SHARK_MARK
            + ") in units of " + unitSize + ".");
        for (int i = 0; i < history.size(); i++) {

            int[] historyArray = history.get(i);
            int chronon = historyArray[Config.HISTORY_CHRONON_INDEX];
            int numFish = historyArray[Config.HISTORY_NUM_FISH_INDEX];
            int numSharks = historyArray[Config.HISTORY_NUM_SHARKS_INDEX];
            writer.printf("F%3d,", numFish);
            writer.printf("S%3d", numSharks);
            writer.printf("%5d)", chronon);
            int unitsOfFishs = numFish / unitSize;
            if ((numFish % unitSize) > 0) {
                unitsOfFishs++;
            }
            int unitsOfSharks = numSharks / unitSize;
            if ((numSharks % unitSize) > 0) {
                unitsOfSharks++;
            }
            if ((numFish - numSharks) < 0) {
                for (int k = 0; k < unitsOfFishs; k++) {
                    writer.print(Config.FISH_MARK);
                }
                for (int j = unitsOfFishs; j < unitsOfSharks; j++) {
                    writer.print(Config.SHARK_MARK);
                }
                for (int m = unitsOfSharks; m < Config.POPULATION_CHART_WIDTH; m++) {
                    writer.print(" ");
                }
            } else {
                for (int b = 0; b < unitsOfSharks; b++) {
                    writer.print(Config.SHARK_MARK);
                }
                for (int c = unitsOfSharks; c < unitsOfFishs; c++) {
                    writer.print(Config.FISH_MARK);
                }
                for (int m = unitsOfFishs; m < Config.POPULATION_CHART_WIDTH; m++) {
                    writer.print(" ");
                }
            }
            writer.println();
        }
        writer.close();


    }
}
