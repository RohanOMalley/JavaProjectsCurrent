import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
* AUTHOR: Rohan OMalley
* 
* FILE: PA12Main.java
* 
* ASSIGNMENT: Programming Assignment 4 - Anagrams
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program reads in a dictionary file
* with lots of word on it. Then takes in word from the
* user and finds all of the anagrams of that word in
* the dictionary passed in. Then pairs of combonations
* of anagrams are recursively found. 
*/
public class PA12Main {
    public static void main(String[] args)
            throws FileNotFoundException {
        /*
         * String filename, String word, String num
         * Method calls formats the args passed in
         * then finds all anagrams then finds them
         * recursively
         * 
         * @param String[] args
         * file, word, num to find
         */
        File dictFile = new File(args[0]);
        String mainWord = args[1];
        int numToFind = Integer.parseInt(args[2]);
        limitNum = numToFind;
        LetterInventory main = new LetterInventory(mainWord);

        ArrayList<String> dictWords = dictReader(dictFile);
        ArrayList<String> choices = choices(dictWords, main);

        if (numToFind == 0) {
            limitNum = -1;
            mainPrint(mainWord, choices, main);
        }
        else {
            mainPrint(mainWord, choices, main);
            printer();
        }

    }

    public static void mainPrint(String phrase, ArrayList<String> choices,
            LetterInventory main) {
        /**
         * Method prints out the main statement that tells all
         * the anagrams and what combos were found
         * 
         * @param phrase
         *            - String that is the main word
         *            from the command line arguments
         * 
         * @param choices
         *            - List of all the possible
         *            choices for anagrams from the main word
         * 
         * @param main
         *            - LetterInventory object with
         *            the count for the main word
         */
        System.out.println("Phrase to scramble: " + phrase);
        System.out.println();
        System.out.println("All words found in " + phrase + ":");
        System.out.print(choices);
        System.out.println();
        System.out.println();
        System.out.println("Anagrams for " + phrase + ":");
        ArrayList<String> output = new ArrayList<String>();
        anagramHelper(main, choices, output);
    }

    public static ArrayList<String> dictReader(File dict)
            throws FileNotFoundException {
        /**
         * Method takes in a file and reads each line
         * into an ArrayList and returns the List
         * 
         * @param File
         *            dict, the filename of the dict
         *            to read into a list
         * 
         * @return dictWords, all the words from the
         *         dictionary file
         */
        Scanner fileReader = new Scanner(dict);
        ArrayList<String> dictWords = new ArrayList<String>();
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine();
            dictWords.add(line);
        }
        fileReader.close();
        return dictWords;
    }


    public static ArrayList<String> choices(ArrayList<String> dict,
            LetterInventory main) {
        /**
         * Method finds all choices for anagrams
         * from the main word passed in
         * 
         * @param ArrayList<String>
         *            dict, all the words
         *            from the dictionary file
         * 
         * @param LetterInventory
         *            main, the word passed in
         *            from the command line
         * 
         * @return ArrayList<String> retval, list of all the
         *         possible anagrams found in the dictionary
         */
        ArrayList<String> retval = new ArrayList<String>();
        
        for (String word : dict) {
            if (main.contains(word)) {
                retval.add(word);
            }
        }
        return retval;
         
    }

    public static void printer() {
        /**
         * Method finds what outputs from
         * the recursive function are below
         * or equal to the limit of words in each
         * anagram. Then it is printed out if output
         * matches length requirement.
         */
        ArrayList<ArrayList<String>> hey = outputs;
        for (ArrayList<String> anagrams : outputs) {
            if (anagrams.size() <= limitNum) {
                System.out.println(anagrams);
            }
        }
    }

    // outputs holds all the anagram combonations from
    // the recursive call
    public static ArrayList<ArrayList<String>> outputs = new ArrayList<ArrayList<String>>();
    // limitNum is max length of an output passed in from command
    // line arguments
    public static int limitNum;

    public static void
            anagramHelper(LetterInventory lettersToUse,
                    ArrayList<String> choices, ArrayList<String> output) {
        /*
         * Method recursively goes through the choices
         * and finds the anagrams
         * 
         * @param LetterInventory lettersToUse - holds
         * the inventory for the main word from the command
         * line arguments
         * 
         * @param ArrayList<String> choices - List of all the
         * possible anagrams from the main word
         * 
         * @param ArrayList<String> output - holds the current output
         * for the specific combo of anagrams
         */
        if (lettersToUse.isEmpty() && limitNum != -1) {
            ArrayList<String> newO = new ArrayList<String>(output);
            outputs.add(newO);
        }
        if (lettersToUse.isEmpty() && limitNum == -1) {
            System.out.println(output);
        }
        else {
            for (String curChoice : choices) {
                if (lettersToUse.contains(curChoice)) {
                    // Choose
                    lettersToUse.subtract(curChoice);
                    output.add(curChoice);
                    // Explore
                    anagramHelper(lettersToUse, choices, output);
                    // Unchoose
                    lettersToUse.add(curChoice);
                    output.remove(curChoice);

                }

            }
        }
    }
}
