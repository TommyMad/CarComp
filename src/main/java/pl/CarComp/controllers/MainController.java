package pl.CarComp.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.CarComp.controllers.controllersDubler.NextCarCompare2Controller;
import pl.CarComp.controllers.controllersDubler.NextCarCompare3Controller;
import pl.CarComp.controllers.controllersDubler.NextCarCompareController;
import pl.CarComp.utils.DialogWindows;

import java.util.Optional;

public class MainController {

    private static final String FXML_CAR_ADD_WINDOW_FXML = "/fxml/carAddWindow.fxml";
    private static final String FXML_CAR_CHOOSE_WINDOW_FXML = "/fxml/carChooseWindow.fxml";
    private static final String FXML_CAR_COMPARE_WINDOW_FXML = "/fxml/carCompareWindow.fxml";
    private static final String FXML_NEXT_CAR_COMPARE_WINDOW_FXML = "/fxml/nextCarCompareWindow.fxml";
    private static final String FXML_NEXT_CAR_COMPARE2_WINDOW_FXML = "/fxml/copiesOfNextCarCWindow/nextCarCompare2Window.fxml";
    private static final String FXML_NEXT_CAR_COMPARE3_WINDOW_FXML = "/fxml/copiesOfNextCarCWindow/nextCarCompare3Window.fxml";


    @FXML
    private BorderPane mainBorderP;
    @FXML
    private Button addNextCarButton;
    @FXML
    private AnchorPane centerPane;

    //listOfGridPaneColumns of nodes inside main window
    private ObservableList<Node> listOfGridPaneColumns = FXCollections.observableArrayList();
    //foreign controllers to get access to them
    private static CarCompareController foreign2CarCompareController;
    private static NextCarCompareController foreignNextCarCompareController;
    private static NextCarCompare2Controller foreignNextCarCompare2Controller;
    private static NextCarCompare3Controller foreignNextCarCompare3Controller;

    public static void setForeign2CarCompareController(CarCompareController carCompareController) {
        MainController.foreign2CarCompareController = carCompareController;
    }

    public static void setForeignNextCarCompareController(NextCarCompareController nextcarCompareController) {
        MainController.foreignNextCarCompareController = nextcarCompareController;
    }

    public static void setForeignNextCarCompare2Controller(NextCarCompare2Controller nextCarCompare2Contr) {
        MainController.foreignNextCarCompare2Controller = nextCarCompare2Contr;
    }

    public static void setForeignNextCarCompare3Controller(NextCarCompare3Controller nextCarCompare3Contr) {
        MainController.foreignNextCarCompare3Controller = nextCarCompare3Contr;
    }


    @FXML
    private void initialize() {
        CarChooserController.setMainController(this);
        CarCompareController.setForeignMainController(this);
        NextCarCompareController.setForeignMainController(this);
        NextCarCompare2Controller.setForeignMainController(this);
        NextCarCompare3Controller.setForeignMainController(this);

        addNextCarButton.setDisable(true);
        addNextCarButton.setVisible(false);
    }

    // open car chooser window
    @FXML
    public void openCarChooserWindow() {
        loadCarChooserWindow();
        setCenter(FXML_CAR_COMPARE_WINDOW_FXML);
        //add first grid pane to listOfGridPaneColumns
        listOfGridPaneColumns.add(foreign2CarCompareController.getGridPaneCarCompare());
    }

    // shows About in Help menu bar
    @FXML
    private void showAboutInformation() {
        DialogWindows.dialogAboutApplication();
    }

    // confirmation when closing on exit X button
    @FXML
    private void closeAppMenuButton() {
        Optional<ButtonType> result = DialogWindows.confirmationOnClose();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    // method to add fxml to center of BorderPane
    public void setCenter(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (Exception ex) {
            DialogWindows.errorDialog(ex.getMessage());
        }
        mainBorderP.setCenter(parent);
    }

    //open new window - Car editing and add to database
    @FXML
    public void openAddCarToBase() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_CAR_ADD_WINDOW_FXML));
            Stage stage = new Stage();
            ScrollPane carAddPane = (ScrollPane) loader.load();
            stage.setTitle("Dodaj lub edytuj dane");
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setScene(new Scene(carAddPane));
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
        }
    }

    //load nextCarCompare windows next load carChooseWindow and add nodes to list
    @FXML
    public void addNextCar() {
        if (listOfGridPaneColumns.size() <= 3) {
            if (foreignNextCarCompareController == null) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_NEXT_CAR_COMPARE_WINDOW_FXML));
                loadNewNextWindow(loader);
                listOfGridPaneColumns.add(foreignNextCarCompareController.getNextGridPane());
            } else if (foreignNextCarCompare2Controller == null) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_NEXT_CAR_COMPARE2_WINDOW_FXML));
                loadNewNextWindow(loader);
                listOfGridPaneColumns.add(foreignNextCarCompare2Controller.getNextGridPane());
            } else if (foreignNextCarCompare3Controller == null) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_NEXT_CAR_COMPARE3_WINDOW_FXML));
                loadNewNextWindow(loader);
                listOfGridPaneColumns.add(foreignNextCarCompare3Controller.getNextGridPane());
            } else if (foreignNextCarCompareController.getIdentifyColumnForDelete() == 1) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_NEXT_CAR_COMPARE_WINDOW_FXML));
                loadNewNextWindow(loader);
                listOfGridPaneColumns.add(foreignNextCarCompareController.getNextGridPane());
                foreignNextCarCompareController.setIdentifyColumnForDelete(0);
            } else if (foreignNextCarCompare2Controller.getIdentifyColumnForDelete() == 1) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_NEXT_CAR_COMPARE2_WINDOW_FXML));
                loadNewNextWindow(loader);
                listOfGridPaneColumns.add(foreignNextCarCompare2Controller.getNextGridPane());
                foreignNextCarCompare2Controller.setIdentifyColumnForDelete(0);
            } else if (foreignNextCarCompare3Controller.getIdentifyColumnForDelete() == 1) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_NEXT_CAR_COMPARE3_WINDOW_FXML));
                loadNewNextWindow(loader);
                listOfGridPaneColumns.add(foreignNextCarCompare3Controller.getNextGridPane());
                foreignNextCarCompare3Controller.setIdentifyColumnForDelete(0);
            }
        }

        //System.out.println("node list size: " + listOfGridPaneColumns.size());
    }

    private void loadNewNextWindow(FXMLLoader loader) {
        GridPane tempGrid = new GridPane();
        try {
            tempGrid = loader.load();

        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        foreign2CarCompareController.getMainHbox().getChildren().add(tempGrid);
        loadCarChooserWindow();
    }

    //activate add button after choosing car in CarChooser
    public void activateAddCarButton() {
        addNextCarButton.setDisable(false);
        addNextCarButton.setVisible(true);
    }

    //extracted method
    public void loadCarChooserWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_CAR_CHOOSE_WINDOW_FXML));
            Stage stage = new Stage();

            BorderPane tabPaneChooser = loader.load();
            stage.setTitle("Wybierz pojazd");
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setScene(new Scene(tabPaneChooser));
            stage.show();
            stage.setAlwaysOnTop(true);
            centerPane.getChildren().clear();

        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
        }
    }

    public ObservableList<Node> getListOfGridPaneColumns() {
        return listOfGridPaneColumns;
    }

    public void setListOfGridPaneColumns(ObservableList<Node> listOfGridPaneColumns) {
        this.listOfGridPaneColumns = listOfGridPaneColumns;
    }

    public void setAddNextCarButton(Button addNextCarButton) {
        this.addNextCarButton = addNextCarButton;
    }
}

