import javafx.event.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 *
 */
public class ScramblePad extends Application{

	/* Constants */
	
	public static final int BTN_MIN_SIZE = 50;
	public static final double BTN_MAX_SIZE = Double.MAX_VALUE;
	
	/* End Constants */
	
	
	/* Class Members */
	
	private String m_InputCode;
	private String m_SecretCode;
	private Label m_LockedLabel;
	
	/* End Class Members */
	
	
	/* Inherited Methods */
	
	@Override
	public void start(Stage p_PrimaryStage) throws Exception {
		
		this.m_InputCode = "";
		this.m_SecretCode = this.getParameters().getRaw().get(0);
		
		// Setup the top label
		FlowPane l_LabelPane = new FlowPane();
		this.m_LockedLabel = new Label("Locked");
		l_LabelPane.getChildren().add(this.m_LockedLabel);
		l_LabelPane.setAlignment( Pos.CENTER );
		this.m_LockedLabel.setTextFill(Color.RED);
		
		// Setup the bottom buttons
		FlowPane l_Buttons = new FlowPane();
		l_Buttons.getChildren().add( makeButton("Start") );
		l_Buttons.getChildren().add( makeButton("Okay") );
		l_Buttons.setAlignment(Pos.CENTER);
		
		// Setup the number grid
		GridPane l_NumberGrid = new GridPane();
		for (int i = 0; i<9; i++)
			l_NumberGrid.add( makeButton(i+1+"") , i%3, i/3);
		l_NumberGrid.add( makeButton("") , 0 , 3 );
		l_NumberGrid.add( makeButton("0") , 1 , 3 );
		l_NumberGrid.add( makeButton("") , 2 , 3 );
		for (Node n : l_NumberGrid.getChildren())
			n.setStyle("-fx-base: #000000");
		
		for (int i = 0; i < 3 ; i++)
		{
			ColumnConstraints l_CC = new ColumnConstraints();
			l_CC.setHgrow(Priority.ALWAYS);
			l_NumberGrid.getColumnConstraints().add(l_CC);
		}
		
		for (int i = 0; i < 4 ; i++)
		{
			RowConstraints l_RC = new RowConstraints();
			l_RC.setVgrow(Priority.ALWAYS);
			l_NumberGrid.getRowConstraints().add(l_RC);
		}
		
		// Setup the Border Pane
		BorderPane l_BorderPane = new BorderPane();
		l_BorderPane.setTop(l_LabelPane);
		l_BorderPane.setBottom( l_Buttons );
		l_BorderPane.setCenter(l_NumberGrid);
		l_BorderPane.setBackground( new Background( new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));
		
		// Setup the Scene
		Scene l_Scene = new Scene(l_BorderPane);
		
		// Setup the Stage
		p_PrimaryStage.setScene(l_Scene);
		p_PrimaryStage.sizeToScene();
		p_PrimaryStage.show();
		
	}
	
	/* End Inherited Methods */
	
	
	/* Class Methods */
	
	private Button makeButton(String p_Label)
	{
		Button l_Button = new Button(p_Label);
		
		l_Button.setMinSize(BTN_MIN_SIZE, BTN_MIN_SIZE);
		l_Button.setMaxSize(BTN_MAX_SIZE, BTN_MAX_SIZE);
		
		l_Button.setOnAction( new ButtonHandler(p_Label, this) );
		
		return l_Button;
	}
	
	public void processInputs(String p_Input)
	{
		switch (p_Input)
		{
		case "Start" : 
			this.pressStart();
			break;
		case "Okay" :
			this.pressOkay();
			break;
		default :
			this.m_InputCode += p_Input;
		
		}
	}
	
	private void pressStart(){
		
		this.m_InputCode = "";
		this.m_LockedLabel.setText("Locked");
		this.m_LockedLabel.setTextFill(Color.RED);
		
	}
	
	private void pressOkay(){
		
		if (this.m_InputCode.equals(this.m_SecretCode))
		{
			this.m_LockedLabel.setText("Open");
			this.m_LockedLabel.setTextFill(Color.GREEN);
		}
		
		
	}
	
	/* End Class Methods */
	
	
	/* Main Method */
	
	/**
	 * Main Entry Point when class is called
	 * @param args - command line args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);

	}
	
	/* End Main Method */

}

class ButtonHandler implements EventHandler<ActionEvent> {
	
	private String m_Label;
	private ScramblePad m_Pad;
	
	public ButtonHandler(String p_Label, ScramblePad p_Pad)
	{
		this.m_Label = p_Label;
		this.m_Pad = p_Pad;
	}
	
	public void handle(ActionEvent p_Event)
	{
		this.m_Pad.processInputs(this.m_Label);
	}
	
}

