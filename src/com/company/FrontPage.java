package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;

public class FrontPage
{
	VBox content;                                           /*!< A VBox content */

	/*!
	 * This is a constructor that builds a canvas which contains
	 * the short Description about software's modes.
	 * */

	FrontPage()
	{
		Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
		double width = screenSize.getWidth();

		content = new VBox(10);
		content.setAlignment(Pos.TOP_CENTER);
		content.setPadding(new Insets(40,25,10,25));
/**/
		Label mode1label =new Label("Mode 1 (copying mode) : ");
		mode1label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC,18));
		mode1label.setTextFill(Color.CYAN);
		mode1label.setTextAlignment(TextAlignment.JUSTIFY);

		Label mode1 = new Label();
		String mode1txt = "This mode can help the user to fetch all " +
				                  "same extension files from a particular directory to a " +
				                  "destination directory. You can also use this tool to find " +
				                  "files with a particular keyword (Local Machine Search).";
		mode1.setText(mode1txt);
		mode1.setWrapText(true);
		mode1.setPrefWidth(width * 0.45);
		mode1.setTextAlignment(TextAlignment.JUSTIFY);
		mode1.setLineSpacing(1);
		mode1.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC,14));
		mode1.setTextFill(Color.WHITE);
/**/
		Label mode2label = new Label("Mode 2 (Assignment mode) : ");
		mode2label.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC,18));
		mode2label.setTextFill(Color.CYAN);
		mode2label.setTextAlignment(TextAlignment.JUSTIFY);

		Label mode2 = new Label();
		String mode2txt = "Assignment mode can help students to " +
				                  "collect all files of a particular programming language " +
				                  "( e.g.. .c, .cpp, .java etc. ) this mode contains one extra " +
				                  "feature of exporting all files in one PDF file.";
		mode2.setText(mode2txt);
		mode2.setWrapText(true);
		mode2.setPrefWidth(width * 0.45);
		mode2.setTextAlignment(TextAlignment.JUSTIFY);
		mode2.setLineSpacing(1);
		mode2.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.ITALIC,14));
		mode2.setTextFill(Color.WHITE);
/**/
		content.setStyle("-fx-background-color:#212121;");
		content.getChildren().addAll(mode1label,mode1,mode2label,mode2);
	}
}