import java.util.ArrayList;


/**
 * 
 */

/**
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 *
 */
public class WooliePuzzle {
	
	public WooliePuzzle(int p_Count)
	{
		Room l_Room = new Room(p_Count);
		
		ArrayList<Thread> l_ThreadList = new ArrayList<>();
		
		WoolieLeader l_Leader = new WoolieLeader(l_Room, p_Count-1, 0);
		
		for (int i = 1; i<p_Count; i++)
		{
			Thread l_Woolie = new Woolie(l_Room, i);
			l_ThreadList.add(l_Woolie);
		}
		
		l_ThreadList.add(l_Leader);
		for (Thread t : l_ThreadList)
		{
			t.start();
		}
		
		for (Thread t: l_ThreadList)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		double expectedValue = p_Count * (p_Count-1);
		
		for (int j = 1; j < p_Count ; j ++)
		{
			expectedValue += (double)p_Count / (double)(p_Count-j);
		}
		
		expectedValue = Math.ceil(expectedValue);
		
		while(true)
		{
			if (l_Room.getSolved())
			{
				System.out.println("Expected Value: " + expectedValue);
				System.out.println("Outcome: " + l_Room.getTimesVisited());
				break;
			}
		}
		
	}
	
	
	
	/* Main Method */
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Input Args Check
		if (args.length != 1)
		{
			System.out.println("Usage: java WooliePuzzle <number of woolies>");
			return;
		}
		
		// Setup Puzzle to Run
		WooliePuzzle l_Puzzle = new WooliePuzzle(Integer.parseInt(args[0]));

	}
	
	/* End Main Method */

}
