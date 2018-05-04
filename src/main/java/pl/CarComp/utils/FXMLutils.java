package pl.CarComp.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FXMLutils {

    public static Pane fxmlLoader (String fxmlPath) {
	FXMLLoader loader = new FXMLLoader(FXMLutils.class.getResource(fxmlPath));
	try{
	    return loader.load();
	}catch (Exception e) {
		DialogWindows.errorDialog(e.getMessage());
	}
	return null;
    }
}
