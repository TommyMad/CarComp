package pl.CarComp.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

public class DialogWindows {

    public static void dialogAboutApplication() {
	// about information in help menu
	Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
	informationAlert.initStyle(StageStyle.UTILITY);
	informationAlert.setTitle("O aplikacji");
	informationAlert.setHeaderText(null);
	informationAlert.setContentText("Aplikacja Car Compare - porównaj samochody" + "\nPorównaj pojazdy z klasy B"
		+ "\nwersja 1.0" + "\nAutor: Tomasz Madej");
	informationAlert.showAndWait();
    }

    // add confirmation window when closing program
    public static Optional<ButtonType> confirmationOnClose() {
	Alert alertOnClose = new Alert(AlertType.CONFIRMATION);
	alertOnClose.initStyle(StageStyle.UTILITY);
	alertOnClose.setTitle("");
	alertOnClose.setHeaderText("Wyjście z programu");
	alertOnClose.setContentText("Czy na pewno chcesz wyjść?");
	Optional<ButtonType> result = alertOnClose.showAndWait();
	return result;
    }

    // setting error message window
    public static void errorDialog(String error) {
	Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	errorAlert.setTitle("Błąd");
	errorAlert.setHeaderText("Uwaga, gdzieś wystąpił błąd!");

	TextArea textAreaError = new TextArea(error);
	errorAlert.getDialogPane().setContent(textAreaError);
	textAreaError.setEditable(false);
	errorAlert.showAndWait();

    }
}
