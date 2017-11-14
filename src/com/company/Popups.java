package com.company;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.*;


/*!
 * class to build popup window.
 */

public class Popups
{
	/**
	 *
	 * static method returns void, and used to display popup.
	 *
	 * @param stage a reference to Stage class
	 *              stage will be the owner stage's object.
	 *
	 * @see Stage
	 */
		static void display(Stage stage)
		{
			Stage popupwindow=new Stage();

			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.initOwner(stage);
			popupwindow.setTitle("About fileCoDoc");


			Label label1= new Label("fileCoDoc 0.0.1");
			label1.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR,28));
			label1.setTextFill(Color.WHITE);

			Label copyRight = new Label("Copyright \u00A9 2017-2018 haxer");
			copyRight.setTextFill(Color.WHITE);

			Button closeButton= new Button("Close");
			Button creditButton = new Button("Credits");

			closeButton.setPrefWidth(100);
			creditButton.setPrefWidth(100);

			Style.setButtonCss(closeButton);
			Style.setButtonCss(creditButton);

			closeButton.setOnAction(e -> popupwindow.close());


			HBox buttonsPane = new HBox(50);
			buttonsPane.setPadding(new Insets(30,10,10,10));
			buttonsPane.setAlignment(Pos.CENTER);
			VBox layout= new VBox(10);

			buttonsPane.getChildren().addAll(creditButton,closeButton);

			layout.setAlignment(Pos.CENTER);
			layout.getChildren().addAll(label1,copyRight,buttonsPane);

			layout.setPadding(new Insets(50,10,20,10));
			layout.setAlignment(Pos.TOP_CENTER);

			buttonsPane.setStyle("-fx-background-color : #212121;");
			layout.setStyle("-fx-background-color : #212121;");

			Scene scene1= new Scene(layout, 300, 250);

			popupwindow.setScene(scene1);
			popupwindow.setFullScreen(false);
			popupwindow.setResizable(false);
			popupwindow.showAndWait();

		}
}
