package com.company;

import hax.util.FileFinder;
import hax.util.PDF;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/*!
 * AssignmentPage to provide a  canvas to user, so that
 * user can compile all .c/.java/.cpp files in a PDF file.
 *
 *@see AssignmentPage
 */

public class AssignmentPage
{
	GridPane content;
	AssignmentPage(Stage primaryStage)
	{
		content = new GridPane();
		content.setAlignment(Pos.TOP_CENTER);
		content.setPadding(new Insets(50,25,10,25));
		content.setVgap(10);



		HBox toggleButtonsPane = new HBox();
		HBox sourceFieldPane = new HBox();

		ToggleGroup toggleGroup = new ToggleGroup();

		ToggleButton toggleButton1 = new ToggleButton("JAVA");
		ToggleButton toggleButton2 = new ToggleButton("C");
		ToggleButton toggleButton3 = new ToggleButton("C++");

		toggleButton1.setPrefWidth(400/3);
		toggleButton2.setPrefWidth(400/3);
		toggleButton3.setPrefWidth(400/3);

		toggleButton1.setToggleGroup(toggleGroup);
		toggleButton2.setToggleGroup(toggleGroup);
		toggleButton3.setToggleGroup(toggleGroup);


		TextField sourceTextField = new TextField();
		Button sourceDirectoryChooser = new Button("source");
		sourceDirectoryChooser.setPrefHeight(27);
		sourceTextField.setPrefWidth(300);
		sourceTextField.setEditable(false);
		sourceTextField.setPromptText("--select source directory--");

		sourceDirectoryChooser.setPrefWidth(100);
		sourceDirectoryChooser.setOnAction(e ->
		{
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File selectedDirectory = directoryChooser.showDialog(primaryStage);

			if(selectedDirectory == null){
			}
			else
			{
				sourceTextField.setText(selectedDirectory.getAbsolutePath());
			}
		});

		Label notificationLabel = new Label();



		Button createAssignmentButton = new Button("Export PDF");

		createAssignmentButton.setPrefWidth(400);

		createAssignmentButton.setOnAction(e ->
		{
			String extension = getSelectedToggleButtonText(toggleGroup);
			switch(extension)
			{
				case "NULL" :
					notificationLabel.setText("please select any language..");
					return;
				case "JAVA" :
					extension = "*.java";
					break;
				case "C" :
					extension = "*.c";
					break;
				case "C++" :
					extension = "*.cpp";
					break;
			}

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
			fileChooser.getExtensionFilters().add(extFilter);
			File selectedFile = fileChooser.showSaveDialog(primaryStage);

			if (selectedFile == null) {
				notificationLabel.setText("Please give any name..");
			}
			else
			{
				String dest;
				if(selectedFile.getAbsolutePath().endsWith(".pdf"))
					dest = selectedFile.getAbsolutePath();
				else
					dest = selectedFile.getAbsolutePath()+".pdf";

				FileFinder.fileFinder(sourceTextField.getText(), extension);
				System.out.println(FileFinder.stringArrayList);
				PDF.convertTextToPDF(FileFinder.stringArrayList, dest);
				notificationLabel.setText(dest + " created..");
				FileFinder.stringArrayList.clear();
			}
		});
/**/

/*
 * Nodes
 * */

		toggleButtonsPane.setAlignment(Pos.CENTER);
		toggleButtonsPane.getChildren().addAll(toggleButton1,toggleButton2,toggleButton3);

		sourceFieldPane.getChildren().addAll(sourceTextField,sourceDirectoryChooser);


		GridPane.setColumnSpan(createAssignmentButton,2);

		Style.setTextFieldCss(sourceTextField);
		Style.setButtonCss(sourceDirectoryChooser);
		Style.setLargeButtonCss(createAssignmentButton);
		Style.setToggleButtonCss(toggleButton1,toggleButton2,toggleButton3);
		Style.setToggleButtonCss(toggleButton3,toggleButton1,toggleButton2);
		Style.setToggleButtonCss(toggleButton2,toggleButton3,toggleButton1);

		content.setStyle("-fx-background-color : #212121;");
		content.add(toggleButtonsPane,0,0);
		content.add(sourceFieldPane,0,1);
		content.add(createAssignmentButton,0,2);
		content.add(notificationLabel,0,3);
	}

	private static String getSelectedToggleButtonText(ToggleGroup tg)
	{
		ToggleButton but = (ToggleButton)tg.getSelectedToggle();
		if(but != null)
			return but.getText();
		else
		{
			return "NULL";
		}
	}
}