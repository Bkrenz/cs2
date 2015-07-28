/**
 * Created by Meghan Dwyer and Bob Krency on 7/12/2015.
 *
 * @author Meghan Dwyer mjd9008@rit.edu
 * @author Bob Krency rdk5039@rit.edu
 */

import java.util.ArrayList;

/**
 * An interface to a Puzzle. It contains the routines necessary for accessing
 * the start and goal configs, as well as generating new neighboring configs.
 */
public interface Puzzle<E> {

    /**
     * Get the starting config for this puzzle.
     * @return - the starting config
     */
    E getStart();

    /**
     * Returns true if the current config is the same as the parameter config
     * @return - boolean
     */
    boolean isGoal(E config);

    /**
     * For an incoming config, generate and return all direct neighbors to
     * this config.
     * @param config - the incoming config
     * @return - the collection of neighbor configs
     */
    ArrayList<E> getNeighbors(E config);

    /**
     * print the puzzle type
     * @param puzzle - the puzzle to be printed
     */
    void printSolution(ArrayList<E> puzzle);

    /**
     * hash function of for a particular type of config
     * @param config - a puzzle's config
     * @return - hash value of a particular type of config
     */
    int hash(E config);

} // Puzzle