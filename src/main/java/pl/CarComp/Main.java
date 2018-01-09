package pl.CarComp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.utils.DialogWindows;
import pl.CarComp.utils.FXMLutils;

public class Main extends Application {

    public static final String FXML_LOGIN_WINDOW = "/fxml/loginWindow.fxml";

    public static void main(String[] args) {
	launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	try {
	    Pane mainAnchorPane=FXMLutils.fxmlLoader(FXML_LOGIN_WINDOW);
	    
	    Scene scene = new Scene(mainAnchorPane);

	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Witamy w CarCompare");
	    primaryStage.setResizable(false);
	    primaryStage.show();
	    
	    DBManager.initDatabase();


	} catch (Exception e) {
	    DialogWindows.errorDialog(e.getMessage());
	    System.out.println(e);
	    e.printStackTrace();
	}
	

    }
    

}
