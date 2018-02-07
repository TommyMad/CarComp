package pl.CarComp.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.CarComp.utils.DialogWindows;

import java.util.Optional;

public class MainController {

    public static final String FXML_CAR_ADD_WINDOW_FXML = "/fxml/carAddWindow.fxml";
    public static final String FXML_CAR_CHOOSE_WINDOW_FXML = "/fxml/carChooseWindow.fxml";
    @FXML
    private BorderPane mainBorderP;

    @FXML
    private LoginWindowController loginWelcomeWindowController;
    @FXML
    private CarChooserController mainTabPaneController;
    @FXML
    private CarAddController carAddController;
    @FXML
    private MenuBar mainMenuBar;

    @FXML
    public void openCarChooser() {
        // open car chooser window
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_CAR_CHOOSE_WINDOW_FXML));

            Stage stage = new Stage();

            BorderPane tabPaneChooser = loader.load();
            stage.setTitle("Wybierz pojazd");
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setScene(new Scene(tabPaneChooser));

            stage.show();

        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
            System.out.println(e);

        }
    }

    @FXML
    // shows About in Help menu bar
    private void showAboutInformation() {
        DialogWindows.dialogAboutApplication();
    }

    @FXML
    // confirmation when closing on exit X button
    private void closeAppMenuButton() {
        Optional<ButtonType> result = DialogWindows.confirmationOnClose();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    @FXML
    private void initialize() {
        CarChooserController.setMainController(this);

    }

    // method to add fxml to center
    public void setCenter(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = null;
        try {
            parent = loader.load();
            //set name of chosen car to compare
            CarCompareController carCompareController=loader.getController();
            carCompareController.setSelectedCarLabel("test");
        } catch (Exception ex) {
            DialogWindows.errorDialog(ex.getMessage());
            System.out.println(ex);
        }
        mainBorderP.setCenter(parent);
    }

    //opens new window
    public void openAddCarToBase() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_CAR_ADD_WINDOW_FXML));

            Stage stage = new Stage();

            ScrollPane carAddPane = (ScrollPane) loader.load();
            stage.setTitle("Dodaj lub edytuj dane");
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setScene(new Scene(carAddPane));

            stage.show();

        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
            System.out.println(e);

        }
    }

}

