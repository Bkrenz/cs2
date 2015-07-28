/**
 * 
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 *
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

/**
 * 
 */
public class ChessModel implements Puzzle<ChessBoard>{

	/**/
	
	private ChessBoard m_Start;
	
	/**/
	
	
	/* Constructors */
	
	public ChessModel(String p_FileName) throws FileNotFoundException
	{
		this.m_Start = new ChessBoard(p_FileName);
	}
	
	public ChessModel(ChessBoard p_Board)
	{
		this.m_Start = p_Board;
	}
	
	/* End Constructors */
	
	
	/* Puzzle Interface Methods */
	
	@Override
	public ChessBoard getStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGoal(ChessBoard config) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ChessBoard> getNeighbors(ChessBoard config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printSolution(ArrayList<ChessBoard> puzzle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hash(ChessBoard config) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/* End Puzzle Interface Methods */
	
	
	/* Main Method */
	
	public static void main(String[] args)
	{
		try {
			ChessBoard board = new ChessBoard(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* End Main Method */

}
