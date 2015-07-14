import java.util.ArrayList;

/**
 * @version 
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 *
 */

public class Mobius implements Puzzle {
	
	/* PSVM */
	
	/**
	 * Entry point of the program
	 * @param args List of values for the Mobius Puzzle
	 */
	public static void main(String[] args)
	{
		if (args.length != 3)
		{
			System.out.println("Usage: java Mobius range start goal");
			return;
		}
		
		int l_Range = Integer.parseInt(args[0]);
		int l_Start = Integer.parseInt(args[1]);
		int l_Goal = Integer.parseInt(args[2]);
		
		Solver.Solve(new Mobius(l_Range, l_Start, l_Goal));
	}
	
	/* End PSVM */
	
	
	/* Class Members */
	
	private int m_Goal;
	private int m_Start;
	private int m_Range;
	
	/* End Class Members */
	
	
	/* Constructors */
	
	/**
	 * Create a Mobius Puzzle
	 * @param p_Range the range of the puzzle
	 * @param p_Start the starting point for the puzzle
	 * @param p_Goal the goal point for the puzzle
	 */
	public Mobius(int p_Range, int p_Start, int p_Goal)
	{
		this.m_Range = p_Range;
		this.m_Start = p_Start;
		this.m_Goal = p_Goal;
	}
	
	/* End Constructors */


	/* Puzzle Interface Methods */

	@Override
	public int getStart() {
		return this.m_Start;
	}


	@Override
	public int getGoal() {
		return this.m_Goal;
	}


	@Override
	public ArrayList<Integer> getNeighbors(int p_Config) {
		// Init local list
		ArrayList<Integer> l_Neighbors = new ArrayList<Integer>();
		
		// Get the Neighbors
		int num = p_Config-1;
		if (num < 1)
			num += this.m_Range;
		l_Neighbors.add(num);
		
		num += 2;
		if (num > this.m_Range)
			num -= this.m_Range;
		l_Neighbors.add(num);
		
		// return list
		return l_Neighbors;
	}
	
	/* End Puzzle Interface Methods */

	
}