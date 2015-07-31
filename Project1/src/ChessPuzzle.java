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
public class ChessPuzzle implements Puzzle<ChessBoard>{

	/* Class Members */
	
	private ChessBoard m_Start;
	
	/* End Class Members */
	
	
	/* Constructors */
	
	public ChessPuzzle(String p_FileName) throws FileNotFoundException
	{
		this.m_Start = new ChessBoard(p_FileName);
	}
	
	public ChessPuzzle(ChessBoard p_Board)
	{
		this.m_Start = p_Board;
	}
	
	/* End Constructors */
	
	
	/* Puzzle Interface Methods */
	
	@Override
	public ChessBoard getStart() {
		// TODO Auto-generated method stub
		return this.m_Start;
	}

	@Override
	public boolean isGoal(ChessBoard config) {
		
		int pieceCount = 0;
		
		for (int i = 0 ; i < config.getDimensions()[0] ; i++)
			for (int j = 0; j < config.getDimensions()[1] ; j++)
			{
				Integer[] loc = new Integer[2];
				loc[0] = i;
				loc[1] = j;
				if (!config.isSpaceEmpty(loc))
				{
					pieceCount++;
				}
			}
		if (pieceCount == 1)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public ArrayList<ChessBoard> getNeighbors(ChessBoard config) {
		
		ArrayList<ChessBoard> boardList = new ArrayList<>();
		
		
		
		for (int i = 0 ; i < config.getDimensions()[0] ; i++)
			for (int j = 0; j < config.getDimensions()[1] ; j++)
			{
				
				Integer[] loc = new Integer[2];
				loc[0] = i;
				loc[1] = j;
				if (!config.isSpaceEmpty(loc))
				for (Integer[] move : config.getMoves(loc))
					{
						ChessBoard newBoard = config.clone();
						newBoard.selectPiece(loc);
						newBoard.movePiece(move);
						boardList.add(newBoard);
					}
			}
		
		
		return boardList;
	}

	@Override
	public void printSolution(ArrayList<ChessBoard> puzzle) {
		
		if (!this.isGoal(puzzle.get(puzzle.size()-1)))
		{
			System.out.println("There is no solution.");
			return;
		}
		
		for (int i = 0; i < puzzle.size(); i++)
		{
			System.out.println("Step " + i + ":");
			System.out.println(puzzle.get(i));
		}
	}

	@Override
	public int hash(ChessBoard config) {
		return config.toString().hashCode();
	}
	
	/* End Puzzle Interface Methods */
	
	
	/* Main Method */
	
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.out.println("Usage: java ChessPuzzle {file-name}");
			return;
		}
		
		try {
			ChessBoard board = new ChessBoard(args[0]);
			Puzzle<ChessBoard> puzzle = new ChessPuzzle(board);
			Solver<ChessBoard> solver = new Solver<ChessBoard>();
			ArrayList<ChessBoard> solution = solver.solverBFS(puzzle);
			puzzle.printSolution(solution);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* End Main Method */

}
