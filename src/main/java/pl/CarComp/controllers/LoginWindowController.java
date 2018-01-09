package pl.CarComp.controllers;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.CarComp.utils.DialogWindows;

public class LoginWindowController {

    @FXML
    // open anon button
    public void openMainWindow(ActionEvent event) {
	try {
	    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/mainWindow.fxml"));

	    // hiding login window after click button
	    ((Node) (event.getSource())).getScene().getWindow().hide();

	    Stage stage = new Stage();

	    BorderPane mainBorderPane = (BorderPane) loader.load();
	    stage.setTitle("CarCompare");
	    stage.setScene(new Scene(mainBorderPane));
	    stage.show();
	    
	    // main window shows confirmation before closing with button X
	    stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
		e.consume();
		Optional<ButtonType> result = DialogWindows.confirmationOnClose();
		if (result.get() == ButtonType.OK) {
		    Platform.exit();
		    System.exit(0);
		}

	    });

	} catch (Exception e) {
	    DialogWindows.errorDialog(e.getMessage());
	    System.out.println("Nie mozna zaladowac");
	}
    }

}
