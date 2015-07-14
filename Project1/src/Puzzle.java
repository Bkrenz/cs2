import java.util.ArrayList;

/**
 * 
 * @author rdk5039 Robert Krency
 * @version
 * 
 * email: rdk5039@rit.edu
 *
 */

public interface Puzzle 
{
	/**
	 * Get the starting config for this puzzle. 
	 * @return the starting config for this puzzle. 
	 */
	public int getStart();
	
	/**
	 * Get the goal config for this puzzle.
	 * @return the goal config
	 */
	public int getGoal();
	
	/**
	 * For an incoming config, generate and return all direct neighbors to this config. 
	 * @param config the incoming config 
	 * @return the collection of neighbor configs
	 */
	ArrayList<Integer> getNeighbors(int config);
	
	
	
}
