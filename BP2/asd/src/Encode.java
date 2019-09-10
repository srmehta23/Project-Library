import java.util.Scanner;

/**
 * Created for CS 200, Command-Line Arguments Exercise
 *  
 * @author Jim Williams
 */
public class Encode {
    
    /**
     * Converts each word in str to Proper Case.
     * @param str The string to convert.
     * @return The proper case of the string.
     */
    private static String properCase(String str) {
        StringBuffer translated = new StringBuffer();
        
        String[] words = str.trim().toLowerCase().split("\\s+");
        
        for (int i = 0; i < words.length; i++) {
            if ( words[i].length() >= 1) {
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
            }
            translated.append(words[i]);
            translated.append(" ");
        }

        return translated.toString().trim();
    }
    
    /** 
     * Reverses the string provided as an argument.
     * @param str  The string to reverse.
     * @return  The string with the characters in reverse order.
     */
    public static String reverse(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
    
    /**
     * A program to translate input per the specified command-line arguments.
     * @param args As specified in the usage message.
     */
    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("Usage: java Translate [-u | -p] [-r] (-i | sentence)");
            System.out.println("  Options may be in any order, sentence last.");
            System.out.println("  Any non-option argument is beginning of sentence.");
            System.out.println("  -u means upper case");
            System.out.println("  -p means proper case");
            System.out.println("     if both -u and -p then only do first");
            System.out.println("  -r means reverse");
            System.out.println("  -i means interactive mode ('quit' to end)"); 
            System.out.println("  sentence means the words to translate (ignored if interactive mode)");
            System.exit(1);
        }
        boolean interactive = false;
        boolean reverse = false;
        boolean properCase = false;
        boolean upperCase = false;
        String sentence = "";

        // write loop to process command line arguments.
        //TODO
        
        //translate according to command-line options set
        if (interactive) {
            Scanner input = new Scanner(System.in);
            boolean quit = false;
            while ( !quit && input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (line.equalsIgnoreCase("quit")) {
                    quit = true;
                } else {
                    if (upperCase)
                        line = line.toUpperCase();
                    if (properCase)
                        line = properCase(line);
                    if (reverse)
                        line = reverse(line);
                    System.out.println(line);
                }
            }
            input.close();
        } else {
            if (upperCase)
                sentence = sentence.toUpperCase();
            if (properCase)
                sentence = properCase(sentence);
            if (reverse)
                sentence = reverse(sentence);
            System.out.println(sentence);
        }
    }
}
