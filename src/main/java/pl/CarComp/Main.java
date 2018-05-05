package pl.CarComp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.utils.DatabasewithExampleData;
import pl.CarComp.utils.DialogWindows;
import pl.CarComp.utils.FXMLutils;

/**
 * author: Tomasz Madej
 */

public class Main extends Application {

    private static final String FXML_LOGIN_WINDOW = "/fxml/loginWindow.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane mainAnchorPane = FXMLutils.fxmlLoader(FXML_LOGIN_WINDOW);

            assert mainAnchorPane != null;
            Scene scene = new Scene(mainAnchorPane);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Witamy w CarCompare");
            primaryStage.setResizable(false);
            primaryStage.show();

            DBManager.initDatabase();

            //initialize example data for testing only
            //DatabasewithExampleData.fillwithExamples();

        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
        }
    }
}
