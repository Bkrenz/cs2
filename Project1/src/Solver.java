import java.util.ArrayList;

/**
 * @author rdk5039 Robert Krency
 * @version
 * 
 * email: rdk5039@rit.edu
 *
 */


public class Solver {
	
	/* Static Methods */
	
	/**
	 * Naive puzzle solver utilizing a naive BFS 
	 * @param p_Puzzle Puzzle to be solved
	 */
	public static void Solve( Puzzle p_Puzzle )
	{	
		//create an empty queue as an ArrayList<ArrayList<Integer>>
		ArrayList<ArrayList<Integer>> l_Queue = new ArrayList<>();
		
		//create an ArrayList<Integer> of one element from the starting config and enqueue it
		ArrayList<Integer> l_Current = new ArrayList<>();
		l_Current.add(p_Puzzle.getStart());
		l_Queue.add(l_Current);
		
		//set found to whether the starting config is the goal config, or not
		boolean l_Found = false;
		if (p_Puzzle.getStart() == p_Puzzle.getGoal())
			l_Found = true;
		
		//while the queue is not empty and not found:
		while ( !l_Queue.isEmpty() && !l_Found)
		{
		    //dequeue the front element from the queue and set it to current
			l_Current = l_Queue.get(0);
			l_Queue.remove(0);
			
		    //for each neighbor of the last element in current:
			for (Integer neighbor : p_Puzzle.getNeighbors(((l_Current.get(l_Current.size() - 1)))))
			{
		        //create an ArrayList<Integer> for the next config and make it a copy of current
		        @SuppressWarnings("unchecked")
				ArrayList<Integer> newList = (ArrayList<Integer>) l_Current.clone();
				
				//append the neighbor to the next config
		        newList.add(neighbor);
		        
		        //if the next config is the goal:
		        if (newList.get(newList.size() - 1) == p_Puzzle.getGoal())
		        {
		            //set current to the next config
		        	l_Current = newList;
		        	
		            //set found to true
		        	l_Found = true;
		        	
		            //break out of the for loop
		        	break;
		        }
		        
		        //else:
		        else
		        {
		            //enqueue the next config
		        	l_Queue.add(newList);
		        }
			}
		}
			
		//if found:
		if (l_Found)
		    //current is the solution
			for (int i = 0; i < l_Current.size(); i++)
				System.out.println("Step " + i + ": " + l_Current.get(i));
		
		//else:
		else
		    //there is no solution
			System.out.println("There is no solution");

	}
	
	/* End Static Methods */

}
