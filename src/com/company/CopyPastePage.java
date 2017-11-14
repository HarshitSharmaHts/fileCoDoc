package com.company;

import hax.util.FileFinder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

import static hax.util.CopyThread.*;

/*!
 * A class to build a canvas page for CopyPastePage, so that user can get good GUI
 * mode to transfer multiple files of same extension or with specific keyword from
 * source directory to destination directory.
 *
 * @see CopyPastePage
 */

class CopyPastePage
{


	GridPane content;

	//! CopyPastePage Constructor

	/**
	 * This is a parametrized constructor which builds a
	 * GridPane content with two TextFields with respective Buttons.
	 *
	 * @param  primaryStage a Stage Class reference of Owner stage.
	 * */

	CopyPastePage(Stage primaryStage)
	{

		content = new GridPane();
		content.setAlignment(Pos.TOP_CENTER);
		content.setPadding(new Insets(50,25,10,25));
		content.setVgap(10);

		Label notificationLabelItself = new Label();
		notificationLabelItself.setTextFill(Color.WHITE);
		HBox sourceFieldPane = new HBox();

/*
 * sourceFieldPane Start
 * */

		TextField sourceTextField = new TextField();

		Style.setTextFieldCss(sourceTextField);

		sourceTextField.setPrefWidth(300);
		sourceTextField.setEditable(false);
		sourceTextField.setPromptText("--select source directory--");

		Button sourceDirectoryChooser = new Button("Source");

		Style.setButtonCss(sourceDirectoryChooser);
		sourceDirectoryChooser.setPrefWidth(100);
		sourceDirectoryChooser.setPrefHeight(27);

		sourceDirectoryChooser.setOnAction(e ->
		{
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Source Directory");
			File selectedDirectory = directoryChooser.showDialog(primaryStage);
			if(selectedDirectory == null){
			}
			else
			{
				sourceTextField.setText(selectedDirectory.getAbsolutePath());
			}
		});
		sourceFieldPane.getChildren().addAll(sourceTextField,sourceDirectoryChooser);

/*-*/

/*
 * searchFieldPane Start
 * */

		HBox searchFieldPane = new HBox();

		TextField searchTextField = new TextField();
		Style.setTextFieldCss(searchTextField);
		searchTextField.setPrefWidth(300);
		searchTextField.setPromptText("-- file name --");

		Button searchButton = new Button("Search");
		searchButton.setPrefWidth(100);
		Style.setButtonCss(searchButton);
		searchButton.setPrefHeight(27);

		searchButton.setOnAction(e ->
		{
			FileFinder.stringArrayList.clear();
			checkBoxes.clear();
			checkBoxPane.getChildren().clear();

			String sourcePath = sourceTextField.getText();

			if(sourcePath.equals("")) {
				notificationLabelItself.setText("Source Field Can't be empty..");
				return;
			}

			String fileName = searchTextField.getText();

			if(fileName.equals(""))
			{
				notificationLabelItself.setText("Please enter at least one word in search field..");
				return;
			}
			if(fileName.equals("*"))
				fileName = "";

			FileFinder.fileFinder(sourcePath,fileName);
			ArrayList<String> stringArrayList = FileFinder.stringArrayList;
			notificationLabel.setText(stringArrayList.size()+" files found.");
			Stage stage = addStage(stringArrayList);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage);
			stage.show();
			stringArrayList.clear();
		});

		searchFieldPane.getChildren().addAll(searchTextField,searchButton);
/*-*/
		content.setStyle("-fx-background-color : #212121;");
		content.add(sourceFieldPane,0,0);
		content.add(searchFieldPane,0,1);
		content.add(notificationLabelItself,0,2);
	}
}