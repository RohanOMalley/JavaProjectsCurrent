/*
* AUTHOR: Rohan OMalley
* 
* FILE: WordSearch.java
* 
* ASSIGNMENT: Programming Assignment 1 - WordSearch
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program reads in 2 text files. A dictionary of words
* and a Wordsearch grid. Then the program goes through each line in
* the grid takes in 4 forms of each line. Left to Right, Right to Left,
* Top to Bottom and Bottom to top. Then program search through each line
* and prints out words that are in the dictionary file that are greater
* than or equal to 3 letters in length.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class WordSearch {
    /*
     * This is how you declare constants in Java. You can now type
     * 'MIN_WORD_LENGTH' anywhere you want to refer to it.
     */
    private static final int MIN_WORD_LENGTH = 3;

    public static void main(String[] args) throws FileNotFoundException {
        // Remember, to access command-line arguments, you use args[0],
        // args[1],...
        // See CommandLine.java in the Class Examples github for guidance.
        String[][] grid_lines = makingGrid(new File(args[1]));
        Set<String> word_set = wordSetMaker(new File(args[0]));

        List<String> lr_rows = leftRightRows(grid_lines);
        List<String> tb_rows = topBottomRows(grid_lines);

        for (String cur_row : tb_rows) {
            lr_rows.add(cur_row);
        }

        wordChecker(lr_rows, word_set);


    }

    /*
     * Method makes the 2-D array of the grid file by reading
     * the grid file and formatting the file correctly taking
     * the size of the grid and then making an 2-D array according
     * to the size.
     *
     * @param grid_file, is the file for the grid from command line
     * 
     * @return grid_lines, a 2-D array with each element being a row
     * and inside that element each letter is an
     * element from each row.
     */
    private static String[][] makingGrid(File grid_file)
            throws FileNotFoundException {

        Scanner grid = new Scanner(grid_file);

        int[] grid_size = new int[2];

        grid_size[0] = (Integer.valueOf(grid.nextLine()));
        grid_size[1] = (Integer.valueOf(grid.nextLine()));

        String[][] grid_lines = new String[grid_size[0]][grid_size[1]];
        int i = 0;

        while (grid.hasNextLine()) {

            String[] cur_line = grid.nextLine().split(" ");
            int j = 0;

            for (String cur_letter : cur_line) {

                grid_lines[i][j] = cur_letter;
                j++;
            }
            i++;
        }
        return grid_lines;

    }

    /*
     * Method takes in the dictionary file and loops through to find
     * each word larger than 3 letters then converts it to lowercase.
     * Then word gets added to a set of words.
     *
     * @param dic_file, the dicitonary file from the command line containing
     * all the words to check in the grid
     * 
     * @return word_set, a set of all the words in the dictionary file that
     * are larger than 3 letters and also converts all
     * words to lowercase
     */
    private static Set<String> wordSetMaker(File dic_file)
            throws FileNotFoundException {

        Scanner diction_file = new Scanner(dic_file);

        Set<String> word_set = new HashSet<String>();

        while (diction_file.hasNextLine()) {
            String lower_word = diction_file.nextLine().toLowerCase();
            if (lower_word.length() >= MIN_WORD_LENGTH) {
                word_set.add(lower_word);
            }
        }
        return word_set;
    }

    /*
     * Method takes in the 2-D grid array and then builds strings
     * for each line then adds them to the normal Left to Right rows
     * list. Then it reverses the string and puts that string in
     * another list of reversed lines. Then all of the reversed lines
     * are added to the Left to Right rows list. Then the Left to Right
     * list is returned.
     *
     * @param grid_lines, a 2-D array of each element is a row and each row
     * element has an array of each letter on that row
     * 
     * @return l_r_rows, a List containing Strings for the regular Left to
     * Right rows and the reversed Right to Left rows
     */
    private static List<String> leftRightRows(String[][] grid_lines) {
        List<String> l_r_rows = new ArrayList<String>();
        List<String> l_r_rev_rows = new ArrayList<String>();

        for (String[] cur_row : grid_lines) {

            String new_line = "";

            for (String cur_let : cur_row) {
                new_line += cur_let;
            }
            StringBuffer old_lr_line = new StringBuffer(new_line);
            String rev_line = old_lr_line.reverse().toString();
            l_r_rows.add(new_line);
            l_r_rev_rows.add(rev_line);
        }

        // loop to add reversed left to right rows to
        // the l_r rows list

        for (String the_row : l_r_rev_rows) {

            l_r_rows.add(the_row);
        }
        return l_r_rows;
    }

    /*
     * Method takes in the 2-D grid array and then builds strings
     * for each column in the grid then adds them to the normal
     * Top to Bottom rows list. Then it reverses the string and puts the
     * reversed
     * string in another list of reversed lines. Then all of the reversed lines
     * are added to the Top to Bottom rows list. Then the Top to Bottom
     * list is returned.
     *
     * @param grid_lines, a 2-D array of each element is a row and each row
     * element has an array of each letter on that row
     * 
     * @return t_b_rows, a List of the Top to Bottom rows and the reversed
     * Bottom to Top rows.
     */
    private static List<String> topBottomRows(String[][] grid_lines) {
        // loop to make the vertical rows

        List<String> t_b_rows = new ArrayList<String>();
        List<String> t_b_rev_rows = new ArrayList<String>();

        int n = 0;
        int row_length = grid_lines[0].length;

        while (n < row_length) {

            String cur_col = "";
            int row_num = 0;

            while (row_num < grid_lines.length) {
                String letter_to_add = grid_lines[row_num][n];
                cur_col += letter_to_add;
                row_num++;
            }
            n++;

            StringBuffer old_vert_line = new StringBuffer(cur_col);
            String rev_vert_line = old_vert_line.reverse().toString();

            t_b_rev_rows.add(rev_vert_line);
            t_b_rows.add(cur_col);
        }

        // loop to add the reversed vertical rows to
        // main row list

        for (String vert_row : t_b_rev_rows) {
            t_b_rows.add(vert_row);
        }
        return t_b_rows;
    }

    /*
     * Method takes in a List of all the different types
     * of rows combined and searches each line to find words
     * in the word_set and when a word is found the word
     * gets printed out in lowercase
     *
     * @param rows, a List of every type of row: L to R, R to L,
     * T to B, and B to T
     * 
     * @param word_set, a Set containing every word from the
     * dictionary file passed in
     * 
     * @return None
     */
    private static void wordChecker(List<String> rows, Set<String> word_set) {
        // loop to check all words and all lines
        int r = 0;

        while (r < rows.size()) {

            int start = 0;
            String line = rows.get(r);
            int finish = MIN_WORD_LENGTH;

            while (start < line.length() - 2) {

                int line_pointer = finish;

                while (line_pointer < line.length() + 1) {

                    String pos_word = line.substring(start, line_pointer);
                    if (word_set.contains(pos_word.toLowerCase())) {
                        System.out.println(pos_word.toLowerCase());
                    }
                    line_pointer++;
                }
                start++;
                finish++;
            }
            r++;

        }
    }
}
