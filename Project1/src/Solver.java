/**
 * Created by Meghan Dwyer and Bob Krency on 6/27/2015.
 *
 * @author Meghan Dwyer mjd9008@rit.edu
 * @author Bob Krency rdk5039@rit.edu
 *
 */


import java.util.ArrayList;
import java.util.HashSet;


/**
 * Takes an object as a Puzzle interface to solve the algorithm using a
 * Breadth First Search. Pseudo code for BFS provided in Project documentation,
 */
public class Solver<E>{

    /**
     * Default constructor
     */
    public Solver(){} // constructor

    /**
     * BFS search that utilizes memoization so larger problem sets can be
     * attempted
     *
     * @param obj - object that implements the Puzzle interface
     * @return - the BFS solution for obj
     */
    public ArrayList<E> solverBFS(Puzzle obj){

        ArrayList<ArrayList<E>> queue = new ArrayList<>();
        ArrayList<E> currentPath = new ArrayList<E>();
        currentPath.add((E) obj.getStart());
        queue.add(currentPath);
        HashSet<Integer> visited = new HashSet<>();

        boolean found = false;

        //while the queue is not empty and not found:
        while (!queue.isEmpty() && !found) {
            //dequeue the front element from the queue and set it to current
            currentPath = queue.get(0);
            queue.remove(0);
            //for each neighbor of the last element in current:
            ArrayList<E> neighbors = obj.getNeighbors(currentPath.get(currentPath.size() - 1));
            for (E neighbor : neighbors) {
                int hashCode = obj.hash(neighbor);
                if (!visited.contains(hashCode)) {
                    visited.add(hashCode);
                    //create an ArrayList<E> for the next config and make it a copy of current
                    ArrayList<E> clone = (ArrayList<E>) currentPath.clone();
                    //append the neighbor to the next config
                    clone.add(neighbor);
                    //if the next config is the goal:
                    if (obj.isGoal(neighbor)) {
                        //set current to the next config
                        currentPath = clone;
                        //set found to true
                        found = true;
                        //break out of the for loop
                        break;
                    }
                    //else:
                    //enqueue the next config
                    else {
                        queue.add(clone);
                    }
                }
            }
        }

        return currentPath;
    } // SolverBFS

} // Solver
