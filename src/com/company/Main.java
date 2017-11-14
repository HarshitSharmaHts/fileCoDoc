package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**!
 * This class is an Application class that will
 * provide a desktop Application with good gui.
 * */

public class Main extends Application
{

	/**
	 * @Driver function start this function is going
	 * to be invoked, when you run the application.
	 * */
	@Override
	public void start(Stage primaryStage)
	{
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();   /*!< A Rectangle2D instance visualBounds */

		FrontPage f = new FrontPage();                                      /*!< An object of FrontPage Class*/
		AssignmentPage a = new AssignmentPage(primaryStage);                /*!< An Object of AssignmentPage Class*/
		CopyPastePage c = new CopyPastePage(primaryStage);                  /*!< An Object of CopyPastePage Class*/

		double sceneWidth = visualBounds.getWidth();                        //!< A double sceneWidth to hold screen width value
		double sceneHeight = visualBounds.getHeight();                      //!< A double sceneHeight to hold screen height value

		/**
		 * Required Panes for Main Application, A GridPane gridPane, and a MenuBar menuBar
		 * will be added to a VBox upperPane A BorderPane borderPane will have upperPane at the Top.
		 */
		BorderPane borderPane = new BorderPane();
		VBox upperPane = new VBox();
		GridPane gridPane = new GridPane();

		MenuBar menuBar = new MenuBar();
		Menu menuHelp = new Menu("Help");
		menuBar.getMenus().addAll(menuHelp);

		MenuItem menuHelpItemContents = new MenuItem("Contents");
		MenuItem menuHelpItemAbout = new MenuItem("About");

		menuHelp.getItems().addAll(menuHelpItemContents,menuHelpItemAbout);

		menuHelpItemAbout.setOnAction(e ->
			Popups.display(primaryStage)
		);

		Button assignmentPageButton = new Button("Assignment");
		Button copyPastePageButton = new Button("Copy/Paste");
		Button frontPageButton = new Button("Home");

		assignmentPageButton.setPrefWidth(200);
		copyPastePageButton.setPrefWidth(200);
		frontPageButton.setPrefWidth(200);

		Style.setLargeButtonCss(assignmentPageButton);
		Style.setLargeButtonCss(copyPastePageButton);
		Style.setLargeButtonCss(frontPageButton);

		/**
		 * Action Events
		 */

		copyPastePageButton.setOnAction(e ->
		{
			primaryStage.setTitle("fileCoDoc ~ CopyPaste");
			gridPane.getChildren().clear();
			gridPane.add(frontPageButton,1,0);
			gridPane.add(assignmentPageButton,0,0);
			borderPane.setCenter(c.content);
		});


		frontPageButton.setOnAction(e ->
		{
			primaryStage.setTitle("fileCoDoc");
			gridPane.getChildren().clear();
			gridPane.add(assignmentPageButton,0,0);
			gridPane.add(copyPastePageButton,1,0);
			borderPane.setCenter(f.content);
		});


		assignmentPageButton.setOnAction(e ->
		{
            primaryStage.setTitle("fileCoDoc ~ Assignment");
			gridPane.getChildren().clear();
			gridPane.add(frontPageButton,0,0);
			gridPane.add(copyPastePageButton,1,0);
			borderPane.setCenter(a.content);
		});

		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setPadding(new Insets(20,25,10,25));
		gridPane.setStyle("-fx-background-color:#212121;");
		gridPane.add(assignmentPageButton,0,0);
		gridPane.add(copyPastePageButton,1,0);

		upperPane.getChildren().addAll(menuBar,gridPane);

		borderPane.setTop(upperPane);
		borderPane.setCenter(f.content);

		primaryStage.setTitle("fileCoDoc");
		Scene scene = new Scene(borderPane,sceneWidth * 0.45,sceneHeight * 0.45);
		scene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(false);
		primaryStage.show();
	}
}