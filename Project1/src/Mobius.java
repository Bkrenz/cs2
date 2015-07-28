import java.util.ArrayList;

/**
 * Created by Meghan Dwyer on 6/27/2015.
 *
 * @author Meghan Dwyer mjd9008@rit.edu
 */

/**
 * Class representing a Mobius Puzzle
 */
public class Mobius implements Puzzle<Integer>{
    /**
     * Main function to solve a given Mobius puzzle
     *
     * **Constraints**
     * If args does not have exactly 3 arguments will print a usage message
     * then exit program
     * @param args - ArrayList of command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3){
            System.out.println("Usage: java Mobius range start goal");
            System.exit(0);
        } // if

        Solver puzzleSolver = new Solver();
        Puzzle mobiusPuzzle = new Mobius(args);
        ArrayList<Integer> solution = puzzleSolver.solverBFS(mobiusPuzzle);
        mobiusPuzzle.printSolution(solution);

    } // psvm

    /* Class Variables*/

    /*
     * Range associated with this Mobius puzzle
     */
    private int range;

    /*
     * Start associated with this Mobius puzzle
     */
    private int start;

    /*
     * Goal associated with this Mobius puzzle
     */
    private int goal;

    /* Class Constructor */

    /**
     * Constructor
     * @param args - command line arguments
     */
    public Mobius(String[] args){
        this.range = Integer.parseInt(args[0]);
        this.start = Integer.parseInt(args[1]);
        this.goal = Integer.parseInt(args[2]);
    } // constructor

    /* Class Methods */

    /**
     * Get the integer range associated with this Mobius object
     * @return - range
     */
    public int getRange(){
        return this.range;
    } // getRange

    /**
     * Get the integer starting config associated with this Mobius object
     * @return - start
     */
    public Integer getStart(){
        return this.start;

    } // getStart

    /**
     * Get the integer goal of the Mobius object
     * @return - goal
     */
    public boolean isGoal(Integer config){
        return config == this.goal;
    } // getGoal

    /**
     * Get the integer neighbors associated with parameter config
     * @param config - the incoming config
     * @return - ArrayList of the integer neighbors of the parameter config
     */
    public ArrayList<Integer> getNeighbors(Integer config){
        ArrayList<Integer> neighbors = new ArrayList<>();
        if (getRange() == 2){
            if (config == getRange()){
                neighbors.add(1);
                return neighbors;
            } // if getRange
            else{
                neighbors.add(2);
                return neighbors;
            } // else
        } // else if range 2
        else if (config == getRange()){
            neighbors.add(config-1);
            neighbors.add(1);
        } // else if config getRange
        else if (config == 1){
            neighbors.add(getRange());
            neighbors.add(config + 1);
        } //else if config 1
        else {
            neighbors.add(config - 1);
            neighbors.add(config + 1);
        } // else

        return neighbors;
    } // getNeighbors


    /**
     * print solution for Mobius
     * @param solution - the puzzle solution
     */
    public void printSolution(ArrayList <Integer> solution){

        if (!this.isGoal(solution.get(solution.size()-1)))
            System.out.println("No solution.");

        else
            for ( int i = 0 ; i < solution.size() ; i++ )
                System.out.println("Step " + i + ": " + solution.get(i));

    }

    /**
     * hash function for the mobius puzzle to be used for memoization
     * @param config - a puzzle's config
     * @return - hash value of a config
     */
    public int hash(Integer config)
    {
        return config;
    }

} // Mobius
