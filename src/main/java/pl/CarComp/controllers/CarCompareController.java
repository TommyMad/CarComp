package pl.CarComp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import pl.CarComp.controllers.controllersDubler.NextCarCompare2Controller;
import pl.CarComp.controllers.controllersDubler.NextCarCompare3Controller;
import pl.CarComp.controllers.controllersDubler.NextCarCompareController;
import pl.CarComp.modelFXControler.CarsCharacteristicsFXcontroller;
import pl.CarComp.utils.DialogWindows;


public class CarCompareController {
    public static final String FXML_CAR_CHOOSE_WINDOW_FXML = "/fxml/carChooseWindow.fxml";
    public static final String FXML_CAR_COMPARE_WINDOW_FXML = "/fxml/CarCompareWindow.fxml";
    private static final String FXML_NEXT_CAR_COMPARE_WINDOW_FXML = "/fxml/nextCarCompareWindow.fxml";


    private CarsCharacteristicsFXcontroller carsCharacteristicsFXcontroller;
    @FXML
    private Label selectedCarTitleLabel;
    @FXML
    private GridPane gridPaneCarCompare;
    @FXML
    private HBox mainHbox;
    @FXML
    private ListView <String> priceListViev0,engineListViev0,speedListViev0,dimensionListviev0,fuelListviev0;

    private int idFirstColumn;

    //add controllers
    private static NextCarCompareController foreignNextCarCompareController;
    private static CarChooserController foreignCarChooserController;
    private static MainController foreignMainController;
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

    public static void setForeignNextCarCompare2Controller(NextCarCompare2Controller nextCarCompare2Contr) {
        CarCompareController.foreignNextCarCompare2Controller = nextCarCompare2Contr;
    }

    public static void setForeignNextCarCompare3Controller(NextCarCompare3Controller nextCarCompare3Contr) {
        CarCompareController.foreignNextCarCompare3Controller = nextCarCompare3Contr;
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
        }
        mainHbox.getChildren().add(tempGrid);
    }

    @FXML
    public void changeCar() {
        foreignMainController.loadCarChooserWindow();
        //turn id ON
        setIdFirstColumn(1);
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

    public ListView<String> getPriceListViev0() {
        return priceListViev0;
    }

    public void setPriceListViev0(ListView<String> priceListViev0) {
        this.priceListViev0 = priceListViev0;
    }

    public ListView<String> getEngineListViev0() {
        return engineListViev0;
    }

    public void setEngineListViev0(ListView<String> engineListViev0) {
        this.engineListViev0 = engineListViev0;
    }

    public ListView<String> getSpeedListViev0() {
        return speedListViev0;
    }

    public void setSpeedListViev0(ListView<String> speedListViev0) {
        this.speedListViev0 = speedListViev0;
    }

    public ListView<String> getDimensionListviev0() {
        return dimensionListviev0;
    }

    public void setDimensionListviev0(ListView<String> dimensionListviev0) {
        this.dimensionListviev0 = dimensionListviev0;
    }

    public ListView<String> getFuelListviev0() {
        return fuelListviev0;
    }

    public void setFuelListviev0(ListView<String> fuelListviev0) {
        this.fuelListviev0 = fuelListviev0;
    }
}
