/**
 * 
 */


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.application.*;

/**
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 *
 */
public class HeistGUI extends Application implements Observer{
	
	/* Public Members */
	
	public static HeistModel model;
	
	/* End Public Members */
	

	/* Class Members */
	
	private ArrayList<Button> m_ButtonList;
	private Image m_ThiefImage;
	private Image m_JewelsImage;
	private HeistModel m_Model;
	private Timer m_Timer;
	private Label m_MovesLabel;
	
	/* End Class Members */
	
	
	/* Constructors */
	
	public HeistGUI()
	{
		
	}
	
	/* End Constructors */
	
	
	/* Class Methods */
	
	private GridPane makeCenterGrid()
	{
		GridPane l_GPane = new GridPane();
		
		for (int i = 0; i < this.m_Model.getDim(); i++)
		{
			for (int j = 0; j < this.m_Model.getDim(); j++)
				l_GPane.add(makeButton(i*this.m_Model.getDim() + j), j, i);
		}
		
		
		return l_GPane;
	}
	
	private BorderPane makeBottom()
	{
		BorderPane l_BPane = new BorderPane();
		HBox l_HBox = new HBox();
		
		Label l_EnterExit = new Label("Enter/Exit");
		l_EnterExit.setStyle("-fx-base: #ffffff, -fx-font-size: 20");
		
		Button l_EMP = new Button("EMP");
		l_EMP.setPrefSize(90,30);
		l_EMP.setOnAction(new EMPButtonHandler(this.m_Model));
		
		Button l_Reset = new Button("Reset");
		l_Reset.setPrefSize(90,30);
		l_Reset.setOnAction(new ResetButtonHandler(this.m_Model));
		
		l_HBox.getChildren().add(l_EMP);
		l_HBox.getChildren().add(l_Reset);
		
		l_BPane.setLeft(l_EnterExit);
		l_BPane.setRight(l_HBox);
		
		return l_BPane;
	}
	
	
	private Button makeButton(int p_ButtonNum)
	{
		Button l_Button = new Button();
		
		l_Button.setPrefSize(150, 150);
		l_Button.setOnAction(new GridButtonHandler(p_ButtonNum, this.m_Model));
		this.m_ButtonList.add(l_Button);
		
		return l_Button;
	}
	
	class GridButtonHandler<ActionHandler> implements EventHandler{
		
		private int m_ButtonNum;
		private HeistModel m_Model;
		
		public GridButtonHandler(int p_Num, HeistModel p_Model)
		{
			this.m_ButtonNum = p_Num;
			this.m_Model = p_Model;
		}
		
		@Override
		public void handle(Event p_Event)
		{
			this.m_Model.selectCell(this.m_ButtonNum);
		}
		
	}
	
	class EMPButtonHandler<ActionHandler> implements EventHandler{
		
		private HeistModel m_Model;
		
		public EMPButtonHandler(HeistModel p_Model)
		{
			this.m_Model = p_Model;
		}
		
		@Override
		public void handle(Event p_Event)
		{
			if (!this.m_Model.getEMPUsed() && this.m_Model.getGameStatus() == 1)
				this.m_Model.disableAlarm();
		}
		
	}
	
	class ResetButtonHandler<ActionHandler> implements EventHandler{
		
		private HeistModel m_Model;
		
		public ResetButtonHandler(HeistModel p_Model)
		{
			this.m_Model = p_Model;
		}
		
		@Override
		public void handle(Event p_Event)
		{
			this.m_Model.reset();
		}
		
	}
	
	
	/* End Class Methods */
	
	
	/* Inherited Application Methods */
	
	public void init()
	{
		try {
			this.m_Model = new HeistModel(this.getParameters().getRaw().get(0));
			this.m_Model.addObserver(this);
			HeistGUI.model = this.m_Model;
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
		
		this.m_ButtonList = new ArrayList<Button>();
		this.m_JewelsImage = new Image(getClass().getResourceAsStream("Jewels.jpg"));
		this.m_ThiefImage = new Image(getClass().getResourceAsStream("Thief.jpg"));
		
	}
	
	@Override
	public void start(Stage p_Stage) throws Exception {
		
		BorderPane l_BPane = new BorderPane();
		
		this.m_MovesLabel = new Label("Moves: " + this.m_Model.getMoveCount());
		l_BPane.setTop(this.m_MovesLabel);
		l_BPane.setCenter(this.makeCenterGrid());
		l_BPane.setBottom(this.makeBottom());
		
		
		
		// Setup and Display Stage
		p_Stage.setScene(new Scene(l_BPane));
		p_Stage.sizeToScene();
		p_Stage.show();
		
		
		//Run the Timer
		this.m_Timer = new Timer();
		this.m_Timer.scheduleAtFixedRate(new TimerTask()
        {
        // to stop this thread, do a timer.cancel() in stop()
            @Override
            public void run()
            {
                Platform.runLater(new Runnable()
                {
                    public void run()
                    {
                        HeistGUI.model.updateAlarmPattern() ;
                    }
                }) ;
            }
        }, 0, this.m_Model.getRefreshRate() ) ;
	}
	
	public void stop()
	{
		this.m_Timer.cancel();
	}
	
	/* End Inherited Application Methods */
	
	
	/* Observer Interface Methods */
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		// Reset all the tiles and color according to alarm status
		List<Boolean> l_AlarmStatus = this.m_Model.getAlarms();
		for (int i=0 ; i < this.m_ButtonList.size(); i++)
		{
			Button b = this.m_ButtonList.get(i);
			if (l_AlarmStatus.get(i))
				b.setStyle("-fx-base: #000000");
			else
				b.setStyle("-fx-base: #ffffff");
			b.setGraphic(null);
		}
		
		// Assign images for Thief, Jewels
		if (this.m_Model.getAreJewelsStolen())
			this.m_ThiefImage = new Image(getClass().getResourceAsStream("Escape.jpg"));
		else
			this.m_ButtonList.get(this.m_Model.getJewelsLocation()).setGraphic(new ImageView(this.m_JewelsImage));
		this.m_ButtonList.get(this.m_Model.getThiefLocation()).setGraphic(new ImageView(this.m_ThiefImage));
		
		// Update Move Label
		String l_LabelText = "Moves: " + this.m_Model.getMoveCount();
		switch (this.m_Model.getGameStatus())
		{
		case 0 : l_LabelText += " GAME OVER - ALARM TRIGGERED"; break;
		case 2 : l_LabelText += " GAME OVER - YOU WIN"; break;
		}
		this.m_MovesLabel.setText(l_LabelText);
		
		
		
	}
	
	/* End Observer Interface Methods */
	
	
	/* Main Method */
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
	    if(args.length != 1) { 
	        String us = "Usage: java Heist <config-file>";
	        System.out.println(us);
	        return;
	    }
	    	
	    Application.launch(args) ;
	}
	
	/* End Main Method */

}
