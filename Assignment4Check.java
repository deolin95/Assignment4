
import java.io.*;
import java.util.*;

/**
 *
 * @author David Olin
 *
 * declares the private long variables correct, incorrect, tcomp, fcomp, and
 * comp to count found and not found words as well as calculate average searches
 * for each
 *
 * declares the private array dict to contain the dictionary
 *
 * declares the string variable file to hold the name of the document to be
 * parsed
 *
 * contains a constructor, an output method, a readDict method, a readDoc
 * method, and a BSearch(binary search) method
 *
 * This class is used by the Assignment4 class to create a spell checking object
 * to parse a selected document and compare its contents to a dictionary
 */
public class Assignment4Check {

    boolean fAI = false;
    boolean lAI = false;
    private long time = 0;
    private String file;
    private MyLinkedList[] dict = new MyLinkedList[26];
    private long correct;
    private long incorrect;
    private double tcomp;
    private double fcomp;

    /**
     * Assignment4Check : Constructor
     *
     * @param file the selected file passed by the class Assignment4
     * @requires file to be nonempty
     * @ensures a spell checking object is constructed, a passed file name is
     * properly assigned, and the readDict method is called
     *
     * This constructor constructs a spell checking object, assigns the selected
     * file name to a file variable, instantiates the dictionary linked lists,
     * determines if 'a' and 'i' should be added to the dictionary and where,
     * and automatically calls the readDict method
     */
    public Assignment4Check(String file, String AI) {
        this.file = file;
        for (int i = 0; i < 26; i++) {
            dict[i] = new MyLinkedList<String>();
        }
        if (AI.equals("FY")) {
            fAI = true;
        } else if (AI.equals("LY")) {
            lAI = true;
        }
        this.readDict();
    }

    /**
     * Assignment4Check : readDict
     *
     * @requires dictionary.txt to exist, be accessible, and be readable; the
     * linked list dict to be instantiated
     * @ensures the contents of dictionary.txt are read into the 26 dictionary
     * linked lists based on first character of each word, and that 'a' and 'i'
     * are added to appropriate positions This method reads the dictionary.txt
     * file and stores its contents in the 26 dict linked list associated with
     * its beginning character
     */
    public void readDict() {

        File webster = new File("dictionary.txt");
        int a = 0;
        try {
            Scanner peek = new Scanner(webster);
            while (peek.hasNext()) {
                String word = peek.nextLine();
                word = word.toLowerCase();

                dict[(int) word.charAt(0) - 97].addFirst(word);

                a++;
            }
            if (fAI) {
                dict[0].addFirst("a");
                dict[8].addFirst("i");
            } else if (lAI) {
                dict[0].addLast("a");
                dict[8].addLast("i");
            }
            
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Assignment4Check : readDoc
     *
     * @requires the file to exist, be accessible, and be readable
     * @ensures the document specified in the constructor is read, parsed,
     * compared to the dictionary linked list corresponding to the first
     * character of read word, correct and incorrect words are counted, average
     * number of comparisons for each counter is calculated, and counters and
     * total comparisons are sent to the output method
     *
     * This method uses a parser to read a selected document one word at a time,
     * compared to the dictionary linked list corresponding to the first
     * character of read word, and sends all counters and comparison totals to
     * the output method.
     */
    public void readDoc() {
        try {
            FileInputStream oliver = new FileInputStream(new File(file));
            char let;
            String npe = "";
            int n = 0;
            while ((n = oliver.read()) != -1) {

                let = (char) n;

                if (Character.isLetter(let)) {
                    npe += Character.toLowerCase(let);
                }
                if ((Character.isWhitespace(let) || let == '-' || let == ',' || let == '"' || let == '.' || let == ')' || let == '*') && !npe.isEmpty()) {

                    if (dict[(int) npe.charAt(0) - 97].contains(npe)) {
                        correct++;
                        tcomp += (double) dict[(int) npe.charAt(0) - 97].indexOf(npe) + 1;
                    } else {
                        incorrect++;
                        fcomp += (double) dict[(int) npe.charAt(0) - 97].size;
                    }

                    npe = "";
                }

            }

            oliver.close();
            output();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Assignment4Check : output
     *
     * @param word a word from the document that was not found in the dictionary
     * or a sentinel value to display the counts and averages
     *
     * @requres word to be nonempty
     * @ensures counts and average comparisons are displayed for words found and
     * not found
     *
     *
     *
     */
    public void output() {

        System.out.println("number words found = " + correct);
        System.out.println("average number of comparisons for every Correct word = " + tcomp / correct);
        System.out.println("number of words not found = " + incorrect);
        System.out.println("average number of comparisons on words not found = " + fcomp / incorrect);
    }

}
