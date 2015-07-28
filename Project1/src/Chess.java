/** 
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 *  
 */

import java.util.Observable;
import java.util.Observer;

import javafx.application.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;

/**
 * 
 * 
 */
public class Chess extends Application implements Observer{
	
	/* Singleton Handler */
	
	public static Chess ChessInstance;
	
	public static Chess getInstance()
	{
		synchronized(ChessInstance){
			if (ChessInstance == null)
				ChessInstance = new Chess();
			
			return ChessInstance;
		}
	}
	
	/* End Singleton Handler */
	
	
	
	/* Inherited Application Methods */
	
	public void init()
	{
		Chess.ChessInstance = this;
	}
	
	public void start(Stage p_Stage)
	{
		
	}
	
	public void stop()
	{
		
	}
	
	/* End Inherited Application Methods */
	
	
	/* Observer Interface Methods */
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/* End Observer Interface Methods */
	
	
	/* Main Method */
	
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.out.println("Usage: java Chess {fileName}");
			return;
		}
		
		Application.launch(args);
	}
	
	/* End Main Method */

}
