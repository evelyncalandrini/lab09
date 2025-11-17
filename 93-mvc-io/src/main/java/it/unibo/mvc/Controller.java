package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    /**
     * Returns the next string to be shown.
     * 
     * @param nextString the next string
     */
    void setNextString(String nextString);

    /**
     * Returns the next string to be shown.
     * 
     * @return  the next string
     */
    String getNextString();

    /**
     * Returns the history of all the strings shown so far.
     * 
     * @return  the history of all the strings shown so far
     */

    List<String> getHistory();

    /**
     * Prints the current string.
     * 
     * @throws void if no string has been set
     */
    void printCurrentString();

}
