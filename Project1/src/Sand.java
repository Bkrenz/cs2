/**
 * Created by Meghan Dwyer and Bob Krency on 7/12/2015.
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class used to solve the Sand puzzle
 */
public class Sand implements Puzzle<ArrayList<Bucket>> {
    /**
     * Goal of the sand puzzle
     */
    int goal;

    /**
     * ArrayList representing the Buckets used to solve the puzzle
     */
    ArrayList<Bucket> buckets;

    /**
     * the sand puzzle
     * @param arguments - the command line arguments used to initilize the
     *                  puzzle
     */
    public Sand(String[] arguments){
        buckets = new ArrayList<>();
        this.goal = Integer.parseInt(arguments[0]);
        for (int i = 1; i < arguments.length; i++){
            buckets.add(new Bucket(Integer.parseInt(arguments[i])));
        } // for
    } // constructor

    /**
     * the initial configuration of the puzzle
     * @return - ArrayList of buckets that is the first config
     */
    public ArrayList<Bucket> getStart(){
        return this.buckets;
    } // getStart

    /**
     * determines if the current config is the same as the parameter config
     * @param config - a configuration of buckets
     * @return - boolean, true if current config and parameter config are the
     * same
     */
    public boolean isGoal( ArrayList<Bucket> config){
        for (Bucket b: config){
            if (b.getFill() == this.goal){
                return true;
            } // if
        } // for
        return false;
    } // getGoal

    /**
     * Find the neighbors of the parameter config
     * @param config - the incoming config
     * @return - An ArrayList of configs that are neighbors to the parameter
     * config
     */
    public ArrayList<ArrayList<Bucket>> getNeighbors(ArrayList<Bucket> config){

        ArrayList<ArrayList<Bucket>> neighbors = new ArrayList<>();

        for (int i = 0; i < config.size(); i++){

            // Create a neighbor where the bucket at the index is emptied
            if (config.get(i).getFill() != 0) {
                ArrayList<Bucket> clone = cloneList(config);
                clone.get(i).emptyBucket();
                neighbors.add(clone);
            }

            // Create a neighbor where the bucket at the index is filled
            if (config.get(i).getFill() != config.get(i).getCapacity())
            {
                ArrayList<Bucket> clone = cloneList(config);
                clone.get(i).fillBucket();
                neighbors.add(clone);
            }

            // For every other bucket, fill that bucket using the bucket at the index
            ArrayList<Bucket> clone = cloneList(config);
            for (int j = 0; j< clone.size(); j++)
            {
                ArrayList<Bucket> newClone = cloneList(config);
                newClone.get(j).fillBucket(newClone.get(i));
                neighbors.add(newClone);
            }

        } // for - seprerates config

        return neighbors;
    } // getNeighbors

    /**
     * prints the solution to the puzzle
     * @param solution - the puzzle solution
     */
    public void printSolution(ArrayList<ArrayList<Bucket>> solution){

        if (!this.isGoal(solution.get(solution.size()-1)))
            System.out.println("No solution.");
        else {
            for (int i = 0; i < solution.size(); i++) {
                String s = "Step " + i + ":";
                for (Bucket b : solution.get(i)) {
                    s += " " + b.getFill();
                } // for
                System.out.println(s);
            }
        }

    } // printSoultion

    /**
     * makes a copy of the parameter buckets
     * @param buckets - a config
     * @return - copy of the parameter config
     */
    private ArrayList<Bucket> cloneList(ArrayList<Bucket> buckets) {
        ArrayList<Bucket> newBuckets = new ArrayList<>();

        for (Bucket b: buckets)
            newBuckets.add(b.clone());

        return newBuckets;
    }

    /**
     * hash function so that configs are appropriately viewed for memoization
     * @param config - a puzzle's config
     * @return - hash value of a config
     */
    public int hash(ArrayList<Bucket> config)
    {
        String configString= "";
        ArrayList<String> stringList = new ArrayList<>();

        for (Bucket b : config)
            stringList.add( b.getCapacity() + "," + b.getFill() + ";");

        Collections.sort(stringList);

        for (String s: stringList)
            configString += s;

        return configString.hashCode();
    }

    /**
     * main function to run the Sand puzzle
     * @param args - arguments used to generate the sand puzzle
     */
    public static void main(String[] args) {

        if (args.length < 2)
            System.out.println("Usage: java Sand amount bucket1 bucket2 ...");

        Sand beachParty = new Sand(args);
        Solver<ArrayList<Bucket>> solver = new Solver<>();
        ArrayList<ArrayList<Bucket>> solution = solver.solverBFS(beachParty);
        beachParty.printSolution(solution);

    }


} // Sand
