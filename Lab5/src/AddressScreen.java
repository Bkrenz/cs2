/**
 * @author rdk5039 Robert Krency
 * email: rdk5039@rit.edu
 *
 */

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 * 
 *
 */
public class AddressScreen extends Application {

	/* Constants */
	
	private static final int DEFAULT_WINDOW_SIZE = 500;
	
	/* End Constants */
	

	/* Inherited Methods */
	
	@Override
	public void start(Stage p_Stage) throws Exception 
	{
		// Build the 5 Rows with a Label and a Text Box
		VBox l_VBox = new VBox();
		l_VBox.getChildren().add(createTextRow("Name"));
		l_VBox.getChildren().add(createTextRow("Address"));
		l_VBox.getChildren().add(createTextRow("City"));
		l_VBox.getChildren().add(createTextRow("State"));
		l_VBox.getChildren().add(createTextRow("Zip"));
		
		// Build the 3 Buttons at the Bottom		
		
		
		// Setup the Main Border Pane
		BorderPane l_BorderPane = new BorderPane();
		l_BorderPane.setTop(l_VBox);
		
		
		// Setup the Stage
		p_Stage.setTitle("Address Information");
		p_Stage.setScene( new Scene(l_BorderPane) );
		p_Stage.sizeToScene();
		p_Stage.show();
	}
	
	/* End Inherited Methods */
	
	
	/* Class Members */
	
	private HBox createTextRow(String p_Label)
	{
		// Setup the HBox
		HBox l_HBox = new HBox();
		
		// Build the Label in the HBox
		Label l_Label = new Label(p_Label);
		l_Label.setPrefWidth(DEFAULT_WINDOW_SIZE / 2);
		
		// Build the Text Field in the HBox
		TextField l_TextField = new TextField();
		l_TextField.setPrefWidth(DEFAULT_WINDOW_SIZE / 2);
		
		// Add components to the HBox
		l_HBox.getChildren().add(l_Label);
		l_HBox.getChildren().add(l_TextField);
		
		// Return the HBox
		return l_HBox;
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
