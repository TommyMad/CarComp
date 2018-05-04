package pl.CarComp.controllers.controllersDubler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import pl.CarComp.controllers.CarChooserController;
import pl.CarComp.controllers.CarCompareController;
import pl.CarComp.controllers.MainController;
import pl.CarComp.utils.DialogWindows;

public class NextCarCompareController {
    private static final String FXML_NEXT_CAR_COMPARE_WINDOW_FXML = "/fxml/nextCarCompareWindow.fxml";
    @FXML
    private GridPane nextGridPane;
    @FXML
    private Label nextSelectedCarTitleLabel;
    @FXML
    private ListView<String> priceListViev1,engineListViev1,speedListViev1,dimensionListviev1,fuelListviev1;

    private int identifyColumnForDelete;
    private int identifyColumnForChangeCar;
    private int identifyColumnForEdit;

    private static CarCompareController foreignCarCompareController;
    private static CarChooserController foreignCarChooserController;
    private static MainController foreignMainController;

    public static void setForeignCarCompareController(CarCompareController carComContr) {
        NextCarCompareController.foreignCarCompareController = carComContr;
    }

    public static void setForeignCarChooserController(CarChooserController carChooserContr) {
        NextCarCompareController.foreignCarChooserController = carChooserContr;
    }

    public static void setForeignMainController(MainController mainContro) {
        NextCarCompareController.foreignMainController = mainContro;
    }

    @FXML
    public void initialize() {
        CarCompareController.setForeignNextCarCompareController(this);
        MainController.setForeignNextCarCompareController(this);
        CarChooserController.setForeignNextCarCompareController(this);
        setIdentifyColumnForChangeCar(0);
        //System.out.println("first Next change "+getIdentifyColumnForChangeCar());
    }

    //delete one chosen column
    @FXML
    public void deleteColumn() {
        try {
            foreignCarCompareController.getMainHbox().getChildren().remove(nextGridPane);
            //nextGridPane.getChildren().clear();
            foreignMainController.getListOfGridPaneColumns().remove(nextGridPane);
            //System.out.println("size after removing: " + foreignMainController.getListOfGridPaneColumns().size());
            setIdentifyColumnForDelete(1);

        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
        }
    }

    //set the name of title label in column
    public void setNextColumn() {
        if (nextSelectedCarTitleLabel.getText().isEmpty()) {
            setNextColumnTitle();
        } else {
            nextSelectedCarTitleLabel.setText("");
            setNextColumnTitle();
        }

    }

    private void setNextColumnTitle() {
        nextSelectedCarTitleLabel.setText(foreignCarChooserController.getBrandLabel().getText() + " " + foreignCarChooserController.getModelLabel().getText() + " " + foreignCarChooserController.getCapacityLabel().getText() + " " + foreignCarChooserController.getFuelLabel().getText() + " " + foreignCarChooserController.getVersionLabel().getText());
    }

    public void changeCar() {
        foreignMainController.loadCarChooserWindow();
        setIdentifyColumnForChangeCar(1);
    }

    public Label getNextSelectedCarTitleLabel() {
        return nextSelectedCarTitleLabel;
    }

    public GridPane getNextGridPane() {
        return nextGridPane;
    }

    public int getIdentifyColumnForDelete() {
        return identifyColumnForDelete;
    }

    public void setIdentifyColumnForDelete(int identifyColumnForDelete) {
        this.identifyColumnForDelete = identifyColumnForDelete;
    }

    public int getIdentifyColumnForChangeCar() {
        return identifyColumnForChangeCar;
    }

    public void setIdentifyColumnForChangeCar(int identifyColumnForChangeCar) {
        this.identifyColumnForChangeCar = identifyColumnForChangeCar;
    }

    public ListView<String> getPriceListViev1() {
        return priceListViev1;
    }

    public void setPriceListViev1(ListView<String> priceListViev1) {
        this.priceListViev1 = priceListViev1;
    }

    public ListView<String> getEngineListViev1() {
        return engineListViev1;
    }

    public void setEngineListViev1(ListView<String> engineListViev1) {
        this.engineListViev1 = engineListViev1;
    }

    public ListView<String> getSpeedListViev1() {
        return speedListViev1;
    }

    public void setSpeedListViev1(ListView<String> speedListViev1) {
        this.speedListViev1 = speedListViev1;
    }

    public ListView<String> getDimensionListviev1() {
        return dimensionListviev1;
    }

    public void setDimensionListviev1(ListView<String> dimensionListviev1) {
        this.dimensionListviev1 = dimensionListviev1;
    }

    public ListView<String> getFuelListviev1() {
        return fuelListviev1;
    }

    public void setFuelListviev1(ListView<String> fuelListviev1) {
        this.fuelListviev1 = fuelListviev1;
    }
}
