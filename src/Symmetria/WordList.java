/*
 * Author Timo Hosti
 * 
 */
package Symmetria;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Class WordList contains two lists, one to contain the words and another to
 * contain the corresponding numbers. After the words have been read in from the
 * file both lists are prepared. The word list is sorted out and then the number
 * list is filled with corresponding numbers. After this the Search method can
 * be used to search for a set of numbers. If numbers in number list match for
 * what was searched the corresponding words are returned back to the calling
 * method.
 */
public class WordList {

    private List<String> wordList;
    private List<String> numberList;

    /*
     * Constructor for WordList
     */
    public WordList() {
        wordList = new ArrayList<String>();   // list for words
        numberList = new ArrayList<String>(); // another list for the corresponding numbers
    }

    // some wrapper methods
    public boolean Add(String str) {
        return wordList.add(str);
    }

    public int Size() {
        return wordList.size();
    }

    /*
     * Prepare the two lists. Word list is sorted and the number list is filled
     * with corresponding numbers.
     */
    public void PrepareLists() {
        // sort the words and empty number list
        Collections.sort(wordList);
        numberList.clear();
        // calculate corresponding numbers for the words and enter them to the number list
        int size = wordList.size();
        int count = 0;
        String str = "";
        while (count < size) {
            str = wordList.get(count);
            numberList.add(mapWordToNumbers(str));
            count++;
        }
    }

    /*
     * Search method goes through the entire number list. If searched numbers
     * are found, the corresponding word is added to the result list, that is
     * returned to the calling function.
     */
    public List<String> Search(String str) {
        List<String> resultList;
        resultList = new ArrayList<String>();
        int size = wordList.size();
        int count = 0;
        while (count < size) {
            if (Equals(numberList.get(count), str)) {
                resultList.add(wordList.get(count));
            }
            count++;
        }
        return resultList;
    }

    /*
     * Own string comparison method that takes also wildcards into account
     *
     */
    private boolean Equals(String str1, String str2) {

        // if the strings match no need to check further
        if (str1.equals(str2)) {
            return true;
        }

        // check that the lenghts of the strings match
        // if not, they cannot match
        if (str1.length() != str2.length()) {
            return false;
        }

        // now we still need to check the wildcards 1 and 0
        // compare strings char per char
        int length = str1.length();
        for (int i = 0; i < length; i++) {
            // check for wildcards
            if (str2.charAt(i) == '1' || str2.charAt(i) == '0') {
                continue;
            } // no wildcards, compare chars
            else {
                if (str2.charAt(i) != str1.charAt(i)) {
                    return false;
                } 
                // characters match, continue comparison
            }
        }
        // if we get here without returning false, strings do have wildcards 
        // and they match
        return true;
    }


    /*
     * Make the mapping from words to numbers
     */
    private String mapWordToNumbers(String str) {
        String strNumber = "";
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case 'a':
                case 'b':
                case 'c':
                case 'ä':
                    strNumber += "2";
                    break;
                case 'd':
                case 'e':
                case 'f':
                    strNumber += "3";
                    break;
                case 'g':
                case 'h':
                case 'i':
                    strNumber += "4";
                    break;
                case 'j':
                case 'k':
                case 'l':
                    strNumber += "5";
                    break;
                case 'm':
                case 'n':
                case 'o':
                case 'ö':
                case 'å':
                    strNumber += "6";
                    break;
                case 'p':
                case 'q':
                case 'r':
                case 's':
                    strNumber += "7";
                    break;
                case 't':
                case 'u':
                case 'v':
                    strNumber += "8";
                    break;
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    strNumber += "9";
                    break;
                default:
                    System.out.println("SKIPPED WORD \"" + str + "\" because only alphabets a-z and ä,ö,å are allowed in wordlist");
                    strNumber = "";
                    i = str.length() + 1; // terminate for loop
                    break;
            }
        }
        return strNumber;
    }
}
