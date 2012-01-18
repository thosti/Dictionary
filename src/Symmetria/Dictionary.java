/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Symmetria;

import java.util.List;
import java.util.ArrayList;
import java.lang.Long;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

/**
 *
 * @author Timo
 * ***************
 * master haaran editointeja
 * ja lisää editointeja
 * 
 */
public class Dictionary {

    private WordList wordList;

    /*
     * Constructor for Dictionary
     */
    private Dictionary() {
        // Initialize word list
        System.out.println("START: Reading word list");
        wordList = new WordList();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("sanat.txt"));
            String line = null;
            while ((line = in.readLine()) != null) {
                wordList.Add(line);
            }
            in.close();
        } catch (IOException ioe) {
            System.out.println("Error when reading word list");
            ioe.printStackTrace();
            System.exit(0);
        }
        System.out.println("Read total of " + wordList.Size() + " words from file");
        System.out.println("END: Reading word list");
        wordList.PrepareLists();
        System.out.println("Word list prepared for searches!");
        System.out.println("");
    }

    public List<String> find(String input) {
        // WRITE YOUR CODE HERE
        List<String> resultList;
        resultList = new ArrayList<String>();
        resultList = wordList.Search(input);
        return resultList;
    }

    /*
     * Simple helper method for printing list
     */
    public static void printList(List<String> list) {
        System.out.println("-----------------------------------------");
        if (list != null) {
            System.out.println("Found " + list.size() + " word(s):");
            for (String s : list) {
                System.out.println(s);
            }
        } else {
            System.out.println("Empty list");
        }
        System.out.println("-----------------------------------------");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide number serie as argument in following manner \"java Dictionary 74483374\"");
            System.exit(0);
        }

        Long start = System.currentTimeMillis();
        Dictionary dictionary = new Dictionary();

        printList(dictionary.find(args[0]));

        Long end = System.currentTimeMillis();
        System.out.println("Search took " + (end - start) + " ms");
    }
}
