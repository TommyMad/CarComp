package pl.CarComp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pl.CarComp.controllers.controllersDubler.NextCarCompare2Controller;
import pl.CarComp.controllers.controllersDubler.NextCarCompare3Controller;
import pl.CarComp.controllers.controllersDubler.NextCarCompareController;
import pl.CarComp.utils.DialogWindows;


public class CarCompareController {
    public static final String FXML_CAR_CHOOSE_WINDOW_FXML = "/fxml/carChooseWindow.fxml";
    public static final String FXML_CAR_COMPARE_WINDOW_FXML = "/fxml/CarCompareWindow.fxml";
    public static final String FXML_NEXT_CAR_COMPARE_WINDOW_FXML = "/fxml/nextCarCompareWindow.fxml";

    @FXML
    private Label selectedCarTitleLabel;
    @FXML
    private GridPane gridPaneCarCompare;
    @FXML
    private HBox mainHbox;
    private int idFirstColumn;
    private int idFirstColumnEdit;


    //add controllers
    private static NextCarCompareController foreignNextCarCompareController;
    private static CarChooserController foreignCarChooserController;
    private static MainController foreignMainController;
    //test
    private static NextCarCompare2Controller foreignNextCarCompare2Controller;
    private static NextCarCompare3Controller foreignNextCarCompare3Controller;

    public static void setForeignNextCarCompareController(NextCarCompareController nextCarComContr) {
        CarCompareController.foreignNextCarCompareController = nextCarComContr;
    }

    public static void setForeignCarChooserController(CarChooserController carChooserContr) {
        CarCompareController.foreignCarChooserController = carChooserContr;
    }

    public static void setForeignMainController(MainController mainContr) {
        CarCompareController.foreignMainController = mainContr;
    }
    public static void setForeignNextCarCompare2Controller (NextCarCompare2Controller nextCarCompare2Contr){
        CarCompareController.foreignNextCarCompare2Controller=nextCarCompare2Contr;
    }
    public static void setForeignNextCarCompare3Controller (NextCarCompare3Controller nextCarCompare3Contr){
        CarCompareController.foreignNextCarCompare3Controller=nextCarCompare3Contr;
    }

    @FXML
    public void initialize() {
        NextCarCompareController.setForeignCarCompareController(this);
        CarChooserController.setForeignCarCompareController(this);
        MainController.setForeign2CarCompareController(this);
        //test
        NextCarCompare2Controller.setForeignCarCompareController(this);
        NextCarCompare3Controller.setForeignCarCompareController(this);

        setIdFirstColumn(0);
    }

    //copy title from carChooser to label which is column title
    public void setMainColumn() {
        if (selectedCarTitleLabel.getText().isEmpty()) {
            setFirstColumnTitle();

        } else {
            selectedCarTitleLabel.setText("");
            setFirstColumnTitle();
        }
    }

    private void setFirstColumnTitle() {
        selectedCarTitleLabel.setText(foreignCarChooserController.getBrandLabel().getText() + " " + foreignCarChooserController.getModelLabel().getText() + " " + foreignCarChooserController.getCapacityLabel().getText() + " " + foreignCarChooserController.getFuelLabel().getText() + " " + foreignCarChooserController.getVersionLabel().getText());
    }

    //insert new gridpane into Hbox
    public void addNextColumnToCompare() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_NEXT_CAR_COMPARE_WINDOW_FXML));
        GridPane tempGrid = new GridPane();
        try {
            tempGrid = loader.load();

        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
            System.out.println(e);
        }
        mainHbox.getChildren().add(tempGrid);
    }

    @FXML
    public void changeCar() {
        foreignMainController.loadCarChooserWindow();
        //turn id ON
        setIdFirstColumn(1);
        System.out.println("wlaczenie pierwszej columny: "+idFirstColumn);
    }

    @FXML
    public void editCarData() {
    }

    public Label getSelectedCarTitleLabel() {
        return selectedCarTitleLabel;
    }

    public GridPane getGridPaneCarCompare() {
        return gridPaneCarCompare;
    }

    public int getIdFirstColumn() {
        return idFirstColumn;
    }

    public void setIdFirstColumn(int idFirstColumn) {
        this.idFirstColumn = idFirstColumn;
    }

    public HBox getMainHbox() {
        return mainHbox;
    }
}
