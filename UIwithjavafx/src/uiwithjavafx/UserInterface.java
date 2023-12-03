package uiwithjavafx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterface extends Application {
	private MenuItem menuItem1;
	private MenuItem menuItem2;
	private MenuItem menuItem3;
	private MenuItem menuItem4;
	private MenuButton menuButton;
	private Label textLabel;
	private Boolean haveClickedMenuItem3 = false;
	
	// constants
	private static final String BACKGROUND_COLOR_MENU = "#5AC3B0";
	private static final String BACKGROUND_COLOR_TEXTAREA= "red";
	
	// helper method to write content to a file
	private void writeContentToFile() throws IOException {
		//create the file path for log.txt to Desktop
		String fileSeparator = System.getProperty("file.separator");
		String filePath = fileSeparator + "Users" +
				fileSeparator + "phuth" + fileSeparator + "OneDrive" + fileSeparator +
				"Desktop" + fileSeparator + "log.txt";
		
		// create a new file
		File file = new File(filePath);
		file.createNewFile();
		// writing vehicles' information to the file
		PrintWriter outFS = new PrintWriter(filePath);
		outFS.println(textLabel.getText());
		outFS.close();
	}
	
	@Override
	public void start(Stage primaryStage) {
		Scene scene = null;
		// containers to be used
		VBox mainComponent = null;
		HBox menuPane = null;
		HBox textPane = null;
		// paddings for containers/elements
		Insets paddingMainComp = null;
		Insets paddingMenuPane = null;
		Insets paddingTextLabel = null;
		
		// menu drop down section
		menuItem1 = new MenuItem("Option 1");
		menuItem2 = new MenuItem("Option 2");
		menuItem3 = new MenuItem("Option 3");
		menuItem4 = new MenuItem("Option 4");
		menuButton = new MenuButton("Options", null, menuItem1, menuItem2, menuItem3, menuItem4);
		// creating a container for the menu
		menuPane = new HBox(menuButton);
		// styling the menu
		menuPane.setAlignment(Pos.CENTER);
		paddingMenuPane = new Insets(10, 0, 10, 0);
		menuPane.setPadding(paddingMenuPane);
		menuPane.setStyle("-fx-border-color: white; -fx-border-width: 0 0 1 0; -fx-background-color: " + BACKGROUND_COLOR_MENU + ";");
		
		// text area section
		textLabel = new Label("Welcome!");
		textLabel.setMinWidth(300);
		textLabel.setAlignment(Pos.CENTER);
		// padding for the textArea 
		paddingTextLabel = new Insets(50, 50, 50, 50);
		textLabel.setPadding(paddingTextLabel);
		//styling for text
		textLabel.setStyle("-fx-text-fill: white;");
		// creating a container for the text label
		textPane = new HBox(textLabel);
		// styling for container
		textPane.setStyle("-fx-background-color: " + BACKGROUND_COLOR_TEXTAREA);
		textPane.setAlignment(Pos.CENTER);
		
		

		// handling events
		menuItem1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
				
				textLabel.setText(timeStamp);
			}
		});
		menuItem2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					writeContentToFile();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		menuItem3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// get a random number from 64 to 191
				int min = 64;
				int max = 191;
				int range = max - min + 1;
				int randomNum = (int)(Math.random() * range) + min;
				
				// check to see if menuItem3 is clicked for the first time, if it is change option color
				if (!haveClickedMenuItem3) {
					haveClickedMenuItem3 = true;
					menuButton.setStyle("-fx-background-color: rgb( 255, " + randomNum + ",0);");			
				}
				
				textLabel.setStyle("-fx-background-color: rgb( 255, " + randomNum + ",0);");
			}
		});
		menuItem4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
		
		// main container
		mainComponent = new VBox(menuPane, textPane);
		// padding main container
		paddingMainComp = new Insets(10, 10, 10, 10);
		mainComponent.setPadding(paddingMainComp);
		
		scene = new Scene(mainComponent);
		primaryStage.setTitle("Select an Option");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
