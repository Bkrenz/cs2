/** 
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 *  
 */

import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;

/**
 * 
 * 
 */
public class Chess extends Application implements Observer{
	
	/* Singleton Handler */
	
	private static Chess ChessInstance;
	
	public static Chess getInstance()
	{
		if (ChessInstance == null)
			ChessInstance = new Chess();
		
		return ChessInstance;
	}
	
	/* End Singleton Handler */
	
	
	/* Class Members */
	
	private ArrayList<Button> m_ButtonList;
	private ChessBoard m_Board;
	private Solver<ChessBoard> m_Solver;
	private Label m_StatusBar;
	
	/* End Class Members */
	
	
	/* Inherited Application Methods */
	
	public void init()
	{
		Chess.ChessInstance = this;
		this.m_ButtonList = new ArrayList<>();
		
		try {
			this.m_Board = new ChessBoard(this.getParameters().getRaw().get(0));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			this.stop();
			e.printStackTrace();
		}
		
		this.m_Board.addObserver(this);
		this.m_Solver = new Solver<ChessBoard>();
	}
	
	public void start(Stage p_Stage)
	{
		BorderPane l_BP = new BorderPane();
		
		l_BP.setTop(this.makeStatusBar());
		l_BP.setCenter(this.makeChessGrid());
		l_BP.setBottom(this.makeBottomButtons());
		
		Scene l_Scene = new Scene(l_BP);
		p_Stage.setScene(l_Scene);
		p_Stage.sizeToScene();
		p_Stage.setTitle("Solitaire Chess, rdk5039, Robert Krency");
		p_Stage.show();
		
		this.m_Board.update();
	}
	
	public void stop()
	{
		Platform.exit();
	}
	
	/* End Inherited Application Methods */
	
	
	/* Observer Interface Methods */
	
	@Override
	public void update(Observable arg0, Object arg1) {
		for (int i = 0; i < this.m_ButtonList.size(); i++ )
		{
			Integer[] loc = this.getLocFromIndex(i);
			char piece = this.m_Board.getPiece(loc);
			Button b = this.m_ButtonList.get(i);
			b.setText(piece+"");
			b.setStyle("-fx-base: white");
		}
		
		for (int loc : this.m_Board.getAvailableMoves())
			this.m_ButtonList.get(loc).setStyle("-fx-base: yellow");
		
		this.m_StatusBar.setText(this.m_Board.getStatus());
		
	}
	
	/* End Observer Interface Methods */
	
	
	/* GUI Builder Methods */
	
	public VBox makeStatusBar()
	{
		VBox l_VBox = new VBox();
		
		this.m_StatusBar = new Label(this.m_Board.getStatus());
		l_VBox.getChildren().add(this.m_StatusBar);
		l_VBox.getChildren().add(makeRules());
		
		return l_VBox;
	}
	
	public Label makeRules()
	{
		Label l_Rules = new Label();
		
		String text = "Rules:\n";
		text += "All pieces move according to traditional chess rules.\n";
		text += "You must take a piece with each move.\n";
		text += "The game is won when one piece remains";
		
		l_Rules.setText(text);
		
		return l_Rules;
	}
	
	public GridPane makeChessGrid()
	{
		GridPane l_GridPane = new GridPane();
		
		int size = this.m_Board.getDimensions()[0] * this.m_Board.getDimensions()[1];
		
		for (int i = 0; i < size; i++)
		{
			Integer[] l_Loc = this.getLocFromIndex(i);
			Button b = makeChessButton(this.m_Board.getPiece(l_Loc[0], l_Loc[1]), i);
			this.m_ButtonList.add(b);
			l_GridPane.add(b, l_Loc[1], l_Loc[0]);
		}
		
		return l_GridPane;
	}
	
	private Button makeChessButton(char p_Label, int p_Index)
	{
		Button l_Button = new Button(p_Label + "");
		
		l_Button.setPrefSize(75, 75);
		l_Button.setOnAction(new ChessButtonHandler(p_Index));
		
		return l_Button;
	}
	
	private class ChessButtonHandler implements EventHandler<ActionEvent>
	{
		private Integer[] m_Location;
		
		public ChessButtonHandler(int p_Loc)
		{
			this.m_Location = Chess.getInstance().getLocFromIndex(p_Loc);
			
		}

		@Override
		public void handle(ActionEvent arg0) {
			if (Chess.getInstance().m_Board.isPieceSelected())
			{
				Chess.getInstance().m_Board.movePiece(this.m_Location);
			}
			else
			{
				Chess.getInstance().m_Board.selectPiece(this.m_Location);
			}
			
		}
		
	}
	
	public HBox makeBottomButtons()
	{
		HBox l_HBox = new HBox();
		
		Button l_Exit = new Button("Exit");
		l_Exit.setOnAction(new ExitButtonHandler());
		
		Button l_Reset = new Button("Reset");
		l_Reset.setOnAction(new ResetButtonHandler());
		
		Button l_NextStep = new Button("Next Step");
		l_NextStep.setOnAction(new NextStepButtonHandler());
		
		l_HBox.getChildren().addAll(l_Exit, l_Reset, l_NextStep);
		
		return l_HBox;
	}
	
	private class NextStepButtonHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent arg0) {
			Chess.getInstance().nextStep();
		}
		
	}
	
	private class ResetButtonHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent arg0) {
			Chess.getInstance().reset();
		}
		
	}
	
	private class ExitButtonHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent arg0) {
			Chess.getInstance().stop();
		}
		
	}
	
	
	/* End GUI Builder Methods */
	
	
	/* Class Methods */
	
	public void reset()
	{
		try {
			this.m_Board.updateBoard(new ChessBoard(this.getParameters().getRaw().get(0)));
			this.m_Board.resetMoveCounter();
		} catch (FileNotFoundException e) {
			System.out.println("Hey, where'd the file go?!");
			e.printStackTrace();
		}
	}
	
	public void nextStep()
	{
		this.m_Board.makeNextMove();
	}
	
	public Integer[] getLocFromIndex(int p_Index)
	{
		Integer[] l_Loc = new Integer[2];
		l_Loc[0] = p_Index/this.m_Board.getDimensions()[1];
		l_Loc[1] = p_Index%this.m_Board.getDimensions()[1];
		return l_Loc;
	}
	
	/* End Class Methods */
	
	
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
