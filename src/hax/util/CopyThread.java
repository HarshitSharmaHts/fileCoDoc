package hax.util;

import java.io.File;
import java.util.ArrayList;

import com.company.Style;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*!
 * this class is responsible for
 * - functioning of CopyPastePage
 * - to providing checkbox list of all files
 * - copying files from source directory to destination directory.
 */

public class CopyThread extends Thread
{
	private static Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
	private static double height= screenSize.getHeight();
	private static volatile int hasCopied = 0;
	private static Button copySelectedFilesButton = new Button("Copy");
	private static Button destinationDirectoryChooser = new Button("Destination");
	private static TextField destinationTextField = new TextField();
	private static ProgressBar progressBar = new ProgressBar();
	public static ArrayList<CheckBox> checkBoxes = new ArrayList<>();
	public static VBox checkBoxPane = new VBox(5);
	public static Label notificationLabel = new Label();


	/**
	 * run function of Thread class to generate thread
	 * for copying files so that application could not hang.
	 *
	 * @see Thread
	 */

	@Override
	public void run()
	{
		copySelectedFilesButton.setDisable(true);
		progressBar.setVisible(true);

		ArrayList<String> fileArrayList = new ArrayList<>();

		for(int i = 0; i < checkBoxes.size() ; i ++)
			if (checkBoxes.get(i).isSelected())
				fileArrayList.add(checkBoxes.get(i).getText());

		String destinationPath = destinationTextField.getText();
		destinationPath += File.separator;
		for(String fileName : fileArrayList)
		{
			hasCopied++;
			String destinationFileName = destinationPath + new File(fileName).getName();
			FileCopier.copier(fileName,destinationFileName);
			Platform.runLater(() ->
				progressBar.setProgress(hasCopied/fileArrayList.size())
			);
		}
		copySelectedFilesButton.setDisable(false);
		progressBar.setVisible(false);
	}


	/**
	 *
	 * static method returns a new Stage, this stage will have list of checkboxes,
	 * so that you can choose which file you want to copy or which you don't,
	 * and where you want to paste them.
	 *
	 * @param strList an ArrayList of file's name belonging
	 *                to a specific keyword or extension.
	 * @return Stage
	 * @see Stage
	 */
	public static Stage addStage(ArrayList<String> strList)
	{
		Stage stage = new Stage();

		BorderPane borderPane = new BorderPane();
		ScrollPane scrollPane = new ScrollPane();
		VBox contentPane = new VBox();
		GridPane copyPane= new GridPane();

		CheckBox selectAll = new CheckBox("Select All");
		for(String str : strList)
		{
			CheckBox dum = new CheckBox(str);
			checkBoxes.add(dum);
			Style.setCheckBoxCss(dum);
		}

		selectAll.setOnAction(event ->
		{
			if(selectAll.isSelected())
				for(int i = 0 ; i < checkBoxes.size() ; i ++)
					checkBoxes.get(i).setSelected(true);
		});


		progressBar.setVisible(false);
		progressBar.setPrefWidth(600);
		progressBar.setProgress(0.0);
		notificationLabel.setPadding(new Insets(5,5,5,10));


/*
 * destinationFieldPane Start
 * */

		HBox destinationFieldPane = new HBox();
		destinationTextField.setPrefWidth(300);
		destinationTextField.setEditable(false);
		destinationTextField.setPromptText("--select destination directory--");
		destinationDirectoryChooser.setPrefWidth(100);
		destinationDirectoryChooser.setPrefHeight(27);

		destinationDirectoryChooser.setOnAction(e ->
		{
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File selectedDirectory = directoryChooser.showDialog(stage);

			if(selectedDirectory == null)
			{
				destinationTextField.setText(null);
			}
			else
			{
				destinationTextField.setText(selectedDirectory.getAbsolutePath());
			}
		});
		destinationFieldPane.getChildren().addAll(destinationTextField,destinationDirectoryChooser);

/*-*/


		copySelectedFilesButton.setPrefWidth(400);

		copySelectedFilesButton.setOnAction(e ->
			new CopyThread().start()
		);

		Style.setTextFieldCss(destinationTextField);
		Style.setButtonCss(destinationDirectoryChooser);
		Style.setCheckBoxCss(selectAll);
		Style.setProgressBarCss(progressBar);
		Style.setLargeButtonCss(copySelectedFilesButton);

		notificationLabel.setTextFill(Color.WHITE);
		contentPane.getChildren().addAll(notificationLabel,scrollPane);
		contentPane.setStyle("-fx-background-color : #212121;");

		checkBoxPane.getChildren().add(selectAll);
		for(int i = 0 ; i < checkBoxes.size() ; i++)
			checkBoxPane.getChildren().add(checkBoxes.get(i));
		checkBoxPane.setPadding(new Insets(20,20,20,20));

		scrollPane.setVmax(440);
		scrollPane.setPrefHeight(height*0.40);
		scrollPane.setContent(checkBoxPane);

		copyPane.setAlignment(Pos.CENTER);
		copyPane.add(destinationFieldPane,0,0);
		copyPane.add(copySelectedFilesButton,0,1);
		copyPane.setStyle("-fx-background-color : #212121;");

		borderPane.setTop(contentPane);
		borderPane.setCenter(copyPane);
		borderPane.setBottom(progressBar);
		borderPane.setStyle("-fx-background-color : #212121;");

		Scene scene  = new Scene(borderPane,600,height * 0.60);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.setFullScreen(false);
		return stage;
	}
}
