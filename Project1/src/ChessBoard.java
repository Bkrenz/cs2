import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Scanner;

/**
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 */



/**
 * 
 *
 */
public class ChessBoard extends Observable {
	
	/* Class Members */
	
	private char[][] m_Board;
	private Integer[] m_Dimensions;
	private Integer[] m_CurrentPiece;
	private int m_MovesCounter;
	private int m_GameStatus;
	private Solver<ChessBoard> m_Solver;
	
	/* End Class Members */
	
	
	/* Constructors */
	
	public ChessBoard(String p_FileName) throws FileNotFoundException
	{
		this.m_Dimensions = new Integer[2];
		this.m_CurrentPiece = null;
		this.m_MovesCounter = 0;
		this.m_GameStatus = 0;
		this.m_Solver = new Solver<ChessBoard>();
		
		// Parse File
		Scanner l_FileReader = new Scanner(new File(p_FileName));
		
		// Get Dimensions
		char[] l_DimCharArray = l_FileReader.nextLine().toCharArray();
		this.m_Dimensions[0] = Integer.parseInt(l_DimCharArray[0] + "");
		this.m_Dimensions[1] = Integer.parseInt(l_DimCharArray[2] + "");
		
		// Setup Board
		this.m_Board = new char[m_Dimensions[0]][m_Dimensions[1]];
		for (int i=0 ; l_FileReader.hasNext(); i++)
		{
			this.m_Board[i] = l_FileReader.nextLine().replaceAll(" ", "").toCharArray();
		}
		
		l_FileReader.close();
		this.update();
	}
	
	public ChessBoard(char[][] p_Board, Integer[] p_Dim, Integer[] p_Piece)
	{
		this.m_Board = new char[p_Dim[0]][p_Dim[1]];
		for (int i = 0 ; i < p_Dim[0] ; i++)
			for (int j = 0; j < p_Dim[1] ; j++)
				this.m_Board[i][j] = p_Board[i][j];
				
		this.m_Dimensions = p_Dim;
		this.m_CurrentPiece = p_Piece;
		this.update();
	}
	
	/* End constructors */
	
	
	/* Class Methods */
	
	public ArrayList<Integer[]> getMoves(Integer[] p_Location)
	{
		ArrayList<Integer[]> l_MovesList = new ArrayList<>();
		
		ArrayList<Integer[]> l_Queue = new ArrayList<>();
		l_Queue.add(p_Location);
		ArrayList<Integer> l_Visited = new ArrayList<>();
		
		while (!l_Queue.isEmpty())
		{
			Integer[] current = l_Queue.get(0);
			l_Queue.remove(0);

			if (this.isValidMove(p_Location, current) && !this.isSpaceEmpty(current))
				l_MovesList.add(current);
			else
			{
				ArrayList<Integer[]> neighbors = this.getAdjacentLocations(current);
				
				for (Integer[] loc : neighbors)
				{
					String locString = loc[0] + "," + loc[1];
					if (!l_Visited.contains(locString.hashCode()) && this.isValidMove(p_Location, loc))
					{
						l_Visited.add(locString.hashCode());
						l_Queue.add(loc);
						
					}
				}
			}
		}
		return l_MovesList;
	}
	
	private ArrayList<Integer[]> getAdjacentLocations(Integer[] p_Location)
	{
		ArrayList<Integer[]> l_Locations = new ArrayList<>();
		
		for (int i = -1 ; i < 2; i++)
			for (int j = -1; j < 2; j++)
			{
				Integer[] loc = mkLoc(p_Location[0] + i, p_Location[1] + j);
				if (loc != null)
					l_Locations.add(loc);
			}
		
		return l_Locations;
	}
	
	private Integer[] mkLoc(int i1, int i2)
	{
		if ( i1 > this.m_Dimensions[0] -1 || i1 < 0 )
			return null;
		
		if ( i2 > this.m_Dimensions[1] -1 || i2 < 0 )
			return null;
		
		Integer[] loc = new Integer[2];
		loc[0] = i1;
		loc[1] = i2;
		return loc;
	}
	
	public boolean isValidMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		char l_Piece = this.m_Board[p_Location[0]][p_Location[1]];
		
		if (p_Location[0] == p_NewLocation[0] && p_Location[1] == p_NewLocation[1])
			return false;
		
		switch (l_Piece)
		{
		case 'B':
			return this.isValidBishopMove(p_Location, p_NewLocation);
		case 'K':
			return this.isValidKingMove(p_Location, p_NewLocation);
		case 'N':
			return this.isValidKnightMove(p_Location, p_NewLocation);
		case 'P':
			return this.isValidPawnMove(p_Location, p_NewLocation);
		case 'R':
			return this.isValidRookMove(p_Location, p_NewLocation);
		case 'Q':
			return this.isValidQueenMove(p_Location, p_NewLocation);
		default:
			break;
	
		}
		
		return false;
	}
	
	private boolean isValidBishopMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		int vertDiff = p_Location[0] - p_NewLocation[0];
		int horiDiff = p_Location[1] - p_NewLocation[1];
		
		if (Math.abs(horiDiff) == Math.abs(vertDiff))
			return true;
		
		return false;
	}
	
	private boolean isValidKingMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		int diff = p_Location[0] - p_NewLocation[0];
		if (Math.abs(diff) > 1)
			return false;
		
		diff = p_Location[1] - p_NewLocation[1];
		if (Math.abs(diff) > 1)
			return false;
		
		return true;
	}
	
	private boolean isValidKnightMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		int vertDiff = Math.abs(p_Location[0] - p_NewLocation[0]);
		int horiDiff = Math.abs(p_Location[1] - p_NewLocation[1]);
		
		if (vertDiff == 1 && horiDiff == 2)
			return true;
		
		if (vertDiff == 2 && horiDiff == 1)
			return true;
		
		return false;
	}
	
	private boolean isValidPawnMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		int vertDiff = p_Location[0] - p_NewLocation[0];
		int horiDiff = Math.abs(p_Location[1] - p_NewLocation[1]);
		
		if (vertDiff == 1 && horiDiff == 1)
			return true;
		
		return false;
	}
	
	private boolean isValidQueenMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		return this.isValidBishopMove(p_Location, p_NewLocation) 
				|| this.isValidRookMove(p_Location, p_NewLocation);
	}
	
	private boolean isValidRookMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		if ((p_Location[1] == p_NewLocation[1]) || (p_Location[0] == p_NewLocation[0]))
			return true;
		
		return false;
	}
	
	public boolean isSpaceEmpty(Integer[] p_Location)
	{
		return this.m_Board[p_Location[0]][p_Location[1]] == '.';
	}
	
	public void selectPiece(Integer[] p_Location)
	{
		if (this.m_GameStatus != 0)
			return;
		
		if (isSpaceEmpty(p_Location))
			this.m_CurrentPiece = null;
		else
			this.m_CurrentPiece = p_Location;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public boolean isPieceSelected()
	{
		return this.m_CurrentPiece != null;
	}
	
	public void movePiece(Integer[] p_Location)
	{
		if (this.m_GameStatus != 0)
			return;
		
		for (Integer[] loc : this.getMoves(this.m_CurrentPiece))
		{
			if (loc[0] == p_Location[0] && loc[1] == p_Location[1])
			{
				char l_Piece = this.m_Board[this.m_CurrentPiece[0]][this.m_CurrentPiece[1]];
				this.m_Board[this.m_CurrentPiece[0]][this.m_CurrentPiece[1]] = '.';
				this.m_Board[p_Location[0]][p_Location[1]] = l_Piece;
				this.m_MovesCounter++;
				break;
			}
		}
		
		this.m_CurrentPiece = null;
	
		this.setChanged();
		this.notifyObservers();
	}
	
	public Integer[] getDimensions()
	{
		return this.m_Dimensions;
	}
	
	public String toString()
	{
		String l_String = "";
		
		for (char[] chAr : this.m_Board)
		{
			for (char ch : chAr)
			{
				l_String += ch;
			}
			l_String += "\n";
		}
		
		return l_String;
	}
	
	public ChessBoard clone()
	{
		char[][] l_Clone = new char[this.m_Dimensions[0]][this.m_Dimensions[1]];
		
		for (int i = 0; i < this.m_Dimensions[0] ; i++)
			for (int j = 0 ; j < this.m_Dimensions[1] ; j++)
				l_Clone[i][j] = this.m_Board[i][j];
		
		return new ChessBoard(l_Clone, this.m_Dimensions, this.m_CurrentPiece);
	}
	
	public char getPiece(Integer[] loc)
	{
		return this.m_Board[loc[0]][loc[1]];
	}
	
	public char getPiece(int x, int y)
	{
		return this.getPiece(mkLoc(x,y));
	}
	
	public char[][] getBoard()
	{
		return this.m_Board;
	}
	
	public String getStatus()
	{
		int pieceCount = 0;
		
		for (int i = 0 ; i < this.getDimensions()[0] ; i++)
			for (int j = 0; j < this.getDimensions()[1] ; j++)
			{
				Integer[] loc = new Integer[2];
				loc[0] = i;
				loc[1] = j;
				if (!this.isSpaceEmpty(loc))
				{
					pieceCount++;
				}
			}
		
		if (pieceCount == 1)
			this.m_GameStatus = 1;

		Puzzle<ChessBoard> l_Puzzle = new ChessPuzzle(this);
		ArrayList<ChessBoard> l_Solution = (ArrayList<ChessBoard>) this.m_Solver.solverBFS(l_Puzzle);
		if (!l_Puzzle.isGoal(l_Solution.get(l_Solution.size()-1)))
			this.m_GameStatus = 2;
		
		if (this.m_GameStatus == 1)
			return "Moves: " + this.m_MovesCounter + "    YOU WIN!";
		else if (this.m_GameStatus == 2)
			return "Moves: " + this.m_MovesCounter + "    No moves left. YOU LOSE";

		System.out.println(this.toString());
		
		char l_Piece = ' ';
		if ( this.isPieceSelected() )
			l_Piece = this.getPiece(this.m_CurrentPiece);
		return "Moves: " + this.m_MovesCounter + "   Piece Selected: " + l_Piece;
	}
	
	public void updateBoard(ChessBoard p_NewBoard)
	{
		this.m_Board = p_NewBoard.clone().m_Board;
		this.m_MovesCounter += 1;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void resetMoveCounter()
	{
		this.m_MovesCounter = 0;
		this.m_GameStatus = 0;
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Integer> getAvailableMoves(){
		
		ArrayList<Integer> l_Moves = new ArrayList<>();
		
		if (this.m_CurrentPiece != null)
		{
			for (Integer[] loc : this.getMoves(this.m_CurrentPiece))
			{
				int newLoc = loc[0] * this.m_Dimensions[1] + loc[1];
				l_Moves.add(newLoc);
			}
		}
		
		return l_Moves;
	}
	
	public void makeNextMove()
	{
		Puzzle<ChessBoard> l_Puzzle = new ChessPuzzle(this);
		ArrayList<ChessBoard> l_Solution = this.m_Solver.solverBFS(l_Puzzle);
		if (l_Puzzle.isGoal(l_Solution.get(l_Solution.size()-1)))
			this.updateBoard(l_Solution.get(1));
	}
	
	public void update() 
	{
		this.setChanged();
		this.notifyObservers();
	}
	
	/* End Class Methods */
}
