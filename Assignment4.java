
import java.util.*;

/**
 *
 * @author David Olin
 *
 * This class calls a SpellChecker class named Assignment2Check. Assignment2
 * creates an Assignment2Check Object and uses the object to read in a
 * dictionary and compare it to the document oliver.txt. Any words in oliver.txt
 * not found in the dictionary are considered to be possibly incorrect.
 *
 * contains the main method
 */
public class Assignment4 {

    /**
     * Assignment4 : main
     *
     * @param args default arguments of the main class
     *
     * object: Assignment4Check spell
     *
     * creates the object spell to test read the dictionary and compare it to
     * oliver.txt
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the name of the File you'd like to check: ");
        String file = in.nextLine().trim();
        System.out.print("add a and i?\n to front: FY  end:LY :");
        String AI = in.nextLine();
        Assignment4Check spell = new Assignment4Check(file + ".txt", AI);

        spell.readDoc();

    }

}
