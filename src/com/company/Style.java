package com.company;

import javafx.scene.control.*;


/*!
 * A class to provide some static function that can be
 * used to change look and feel of some control node.
 */


public class Style
{

	/**
	 *
	 * static method returns void, that'll give better
	 * feel and look to Button with Red color.
	 *
	 * @param smallButton
	 * @see Button
	 */

	public static void setButtonCss(final Button smallButton)
	{
		String STANDARD_BUTTON_STYLE = "-fx-background-color: #D50000;" +
				                               "-fx-text-fill: white;" +
				                               "-fx-background-radius: 0px;";
		String HOVERED_BUTTON_STYLE  = "-fx-background-color: #B71C1C;" +
				                               "-fx-text-fill: white;" +
				                               "-fx-background-radius: 0px;";

		smallButton.setStyle(STANDARD_BUTTON_STYLE);
		smallButton.setOnMouseEntered(e ->

				smallButton.setStyle(HOVERED_BUTTON_STYLE)
		);
		smallButton.setOnMouseExited(e ->

				smallButton.setStyle(STANDARD_BUTTON_STYLE)
		);
	}

	/**
	 *
	 * static method returns void, that'll give better
	 * feel and look to Button with Blue color.
	 *
	 * @param largeButton
	 * @see Button
	 */
	public static void setLargeButtonCss(final Button largeButton)
	{
		String STANDARD_BUTTON_STYLE = "-fx-font : 14 arial ;" +
				                               "-fx-background-color: #1A237E;" +
				                               "-fx-text-fill: white;" +
				                               "-fx-background-radius: 0px;";
		String HOVERED_BUTTON_STYLE  = "-fx-font : 14 arial ;" +
				                               "-fx-background-color: #283593;" +
				                               "-fx-text-fill: white;" +
				                               "-fx-background-radius: 0px;";

		largeButton.setStyle(STANDARD_BUTTON_STYLE);

		largeButton.setOnMouseEntered(e ->
			largeButton.setStyle(HOVERED_BUTTON_STYLE)
		);
		largeButton.setOnMouseExited(e ->
			largeButton.setStyle(STANDARD_BUTTON_STYLE)
		);
	}

	/**
	 *
	 * static method returns void, that'll
	 * provide look and feel to TextField s.
	 *
	 * @param textField
	 * @see TextField
	 */
	public static void setTextFieldCss(final TextField textField)
	{

		String STANDARD_TEXTFIELD_STYLE = "-fx-background-color: #111111;" +
				                               "-fx-border-color: #D50000 ;" +
				                               "-fx-border-width: 1px ;" +
				                               "-fx-text-fill: white;" +
				                               "-fx-background-radius: 0px;";
		String HOVERED_TEXTFIELD_STYLE  = "-fx-background-color: #111111;" +
				                               "-fx-border-color: #B71C1C ;" +
				                               "-fx-border-width: 1px ;" +
				                               "-fx-text-fill: white;" +
				                               "-fx-background-radius: 0px;";

		textField.setStyle(STANDARD_TEXTFIELD_STYLE);
		textField.setOnMouseEntered(e ->
			textField.setStyle(HOVERED_TEXTFIELD_STYLE)
		);
		textField.setOnMouseExited(e ->
			textField.setStyle(STANDARD_TEXTFIELD_STYLE)
		);
	}

	/**
	 *
	 * static method returns void, that'll provide
	 * better look and fee to ProgressBar.
	 *
	 * @param progressBar
	 * @see ProgressBar
	 */
	public static void setProgressBarCss(final ProgressBar progressBar)
	{

		String STANDARD_PROGRESSBAR_STYLE = "-fx-background-color: #1A237E;" +
											   "-fx-accent:#D50000 ;"+
				                               "-fx-box-border: #1A237E;" +
				                               "-fx-box-radius : 0px;";
		progressBar.setStyle(STANDARD_PROGRESSBAR_STYLE);
	}

	/**
	 *
	 * static method returns void, that'll
	 * change focus properties of CheckBox.
	 *
	 * @param checkBox
	 * @see CheckBox
	 */
	public static void setCheckBoxCss(final CheckBox checkBox)
	{
		checkBox.setStyle("-fx-faint-focus-color: #ff000022;-fx-focus-color : #D50000");
	}

	/**
	 *
	 * static method returns void, that'll provide
	 * a gui look and feel to ToggleButtons.
	 *
	 * Action On any button will change the properties of others.
	 *
	 * @param toggleButton1
	 * @param toggleButton2
	 * @param toggleButton3
	 *
	 * @see ToggleButton
	 */
	static void setToggleButtonCss(final ToggleButton toggleButton1, final ToggleButton toggleButton2, final ToggleButton toggleButton3)
	{
		String COLOR_RED = "-fx-background-color: #D50000;" +
				                               "-fx-text-fill: white;" +
				                               "-fx-background-radius: 0px;" +
				                               "-fx-border-color : #212121;" +
				                               "-fx-border-width : 1px;";
		String COLOR_GRAY  = "-fx-background-color: #212121;" +
				                               "-fx-text-fill: white;" +
				                               "-fx-background-radius: 0px;" +
				                               "-fx-border-color : #D50000;" +
				                               "-fx-border-width : 1px;";
		ToggleButton t  = toggleButton1;t.setStyle(COLOR_RED);
		t.setOnAction(e ->
		{
			if(t.isSelected())
			{
				t.setStyle(COLOR_GRAY);
				toggleButton2.setStyle(COLOR_RED);
				toggleButton3.setStyle(COLOR_RED);
			}
			else
				t.setStyle(COLOR_RED);
		});
	}
}