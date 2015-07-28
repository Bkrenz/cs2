import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	
	private char[][] p_Board;
	private Integer[] p_Dimensions;
	
	private Integer[] currentPiece;
	
	/* End Class Members */
	
	
	/* Constructors */
	
	public ChessBoard(String p_FileName) throws FileNotFoundException
	{
		this.p_Dimensions = new Integer[2];
		this.currentPiece = new Integer[2];
		
		// Parse File
		Scanner l_FileReader = new Scanner(new File(p_FileName));
		
		// Get Dimensions
		char[] l_DimCharArray = l_FileReader.nextLine().toCharArray();
		this.p_Dimensions[0] = Integer.parseInt(l_DimCharArray[0] + "");
		this.p_Dimensions[1] = Integer.parseInt(l_DimCharArray[2] + "");
		
		// Setup Board
		this.p_Board = new char[p_Dimensions[0]][p_Dimensions[1]];
		for (int i=0 ; l_FileReader.hasNext(); i++)
		{
			this.p_Board[i] = l_FileReader.nextLine().replaceAll(" ", "").toCharArray();
		}
		
		l_FileReader.close();
	}
	
	/* End constructors */
	
	
	/* Class Methods */
	
	public ArrayList<Integer[]> getMoves(Integer[] p_Location)
	{
		ArrayList<Integer[]> l_MovesList = new ArrayList<>();
		
		char l_Piece = this.p_Board[p_Location[0]][p_Location[1]];
		
		switch (l_Piece)
		{
		case 'B':
			
			break;
		case 'K':
				
			break;
		case 'N':
			
			break;
		case 'P':
			
			break;
		case 'R':
			
			break;
		case 'Q':
			
			break;
		default:
			break;
	
		}
		
		return l_MovesList;
	}
	
	private boolean isValidMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		
		char l_Piece = this.p_Board[p_Location[0]][p_Location[1]];
		
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
		
		
		return false;
	}
	
	private boolean isValidKingMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		
		
		return false;
	}
	
	private boolean isValidKnightMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		
		
		return false;
	}
	
	private boolean isValidPawnMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		
		
		return false;
	}
	
	private boolean isValidQueenMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		
		
		return false;
	}
	
	private boolean isValidRookMove(Integer[] p_Location, Integer[] p_NewLocation)
	{
		
		
		return false;
	}
	
	private boolean isSpaceEmpty(Integer[] p_Location)
	{
		return this.p_Board[p_Location[0]][p_Location[1]] == '.';
	}
	
	public void selectPiece(Integer[] p_Location)
	{
		if (isSpaceEmpty(p_Location))
			this.currentPiece = null;
		
		this.currentPiece = p_Location;
		this.notifyObservers();
	}
	
	public void movePiece(Integer[] p_Location)
	{
		if (this.currentPiece == null)
			return;
		
		if (!this.isValidMove(this.currentPiece, p_Location))
			return;
		
		char l_Piece = this.p_Board[this.currentPiece[0]][this.currentPiece[1]];
		this.p_Board[this.currentPiece[0]][this.currentPiece[1]] = '.';
		this.p_Board[p_Location[0]][p_Location[1]] = l_Piece;
		this.notifyObservers();
	}
	
	/* End Class Methods */
}
