package pl.CarComp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import pl.CarComp.utils.DialogWindows;


public class CarCompareController {
    public static final String FXML_CAR_CHOOSE_WINDOW_FXML = "/fxml/carChooseWindow.fxml";
    public static final String FXML_NEXT_CAR_COMPARE_WINDOW_FXML = "/fxml/nextCarCompareWindow.fxml";

    @FXML
    private Label selectedCarLabel;
    @FXML
    private GridPane gridPaneCarCompare;
    @FXML
    private Button addCarButton;
    @FXML
    private AnchorPane addCarAnchorPane;
    @FXML
    private static NextCarCompareController foreignNextCarCompareController;
    @FXML
    private ColumnConstraints zero, first, second, third, fourth, fifth;

    public static void setForeignNextCarCompareController(NextCarCompareController nextCarComContr) {
        CarCompareController.foreignNextCarCompareController = nextCarComContr;
    }

    @FXML
    public void initialize() {
        NextCarCompareController.setForeignCarCompareController(this);
    }


    public void setSelectedCarLabel(String selectedCar) {
        selectedCarLabel.setText(selectedCar);
    }

    @FXML
    public void addNextToCompare() {
        //move anchorPane with button addNextCar one column to right
        if (GridPane.getColumnIndex(addCarAnchorPane) < 4) {
            GridPane.setColumnIndex(addCarAnchorPane, GridPane.getColumnIndex(addCarAnchorPane) + 1);


        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_NEXT_CAR_COMPARE_WINDOW_FXML));
        GridPane tempGridPane = new GridPane();
        try {
            tempGridPane = loader.load();

        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        //insert nextCarCompareWindow.fxml
        gridPaneCarCompare.add(tempGridPane, GridPane.getColumnIndex(addCarAnchorPane) - 1, 0, 1, 6);
        addCarAnchorPane.toFront();
        second.setPrefWidth(250);
        //debug
        System.out.println("children of gridpanecarcomp: " + gridPaneCarCompare.getChildren());
        System.out.println("tempgrid children: " + tempGridPane.getChildren());
        }
        else {
            addCarButton.setDisable(true);
            //DialogWindows.warningDialog();
        }

    }

    public void deleteMainColumn() {
        try {
            third.setPrefWidth(0);
            GridPane.setColumnIndex(addCarAnchorPane, GridPane.getColumnIndex(addCarAnchorPane) - 1);
            addCarAnchorPane.toFront();


        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
            System.out.println(e);
        }

    }
}
