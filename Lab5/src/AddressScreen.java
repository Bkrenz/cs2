/**
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 *
 */

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * 
 *
 */
public class AddressScreen extends Application {

	/* Constants */
	
	private static final int DEFAULT_WINDOW_WIDTH = 500;
	private static final int DEFAULT_WINDOW_HEIGHT = 300;
	
	/* End Constants */
	

	/* Inherited Methods */
	
	@Override
	public void start(Stage p_Stage) throws Exception 
	{
		// Build the 5 Labels
		VBox l_Labels = new VBox();
		String[] l_LabelStrings = { "Name" , "Address" , "City" , "State" , "Zip" };
		for (String s : l_LabelStrings)
		{
			Label newL = new Label(s);
			newL.setPrefHeight(DEFAULT_WINDOW_HEIGHT/12);
			l_Labels.getChildren().add( newL );
		}
		// Build the 5 Text Boxes
		VBox l_TextBoxes = new VBox();
		for (String s : l_LabelStrings)
		{
			TextField newTF = new TextField();
			newTF.setPrefHeight(DEFAULT_WINDOW_HEIGHT/12);
			l_TextBoxes.getChildren().add( newTF );
		}
		
		// Build the 3 Buttons at the Bottom		
		FlowPane l_Buttons = new FlowPane();
		l_Buttons.getChildren().add( new Button("Add") );
		l_Buttons.getChildren().add( new Button("Modify") );
		l_Buttons.getChildren().add( new Button("Delete") );
		l_Buttons.setAlignment(Pos.CENTER);
		
		// Setup the Main Border Pane
		BorderPane l_BorderPane = new BorderPane();
		l_BorderPane.setBottom(l_Buttons);
		l_BorderPane.setCenter(l_TextBoxes);
		l_BorderPane.setLeft(l_Labels);
		
		// Setup the Scene
		Scene l_Scene =  new Scene(l_BorderPane);
		
		// Setup scaling for the Grid Pane
		
		
		// Setup the Stage
		p_Stage.setTitle("Address Information");
		p_Stage.setScene(l_Scene);
		p_Stage.sizeToScene();
		p_Stage.show();
	}
	
	/* End Inherited Methods */
	
	
	/* Class Members */
	
	private void createTextRow(String p_Label, GridPane p_Grid, int row)
	{
		// Create the Label and add to the Grid
		p_Grid.add( new Label(p_Label) , 0, row);
		
		// Create the Text Field and add to the Grid
		p_Grid.add( new Label(p_Label) , 1, row );
	}
	
	
	/* End Class Methods */
	
	
	/* Main Method */
	
	/**
	 * Main entry point
	 * @param args command line argumnets
	 */
	public static void main(String[] args) {
		// Launch the application
		Application.launch(args);

	}
	
	/* Main Method */

}
