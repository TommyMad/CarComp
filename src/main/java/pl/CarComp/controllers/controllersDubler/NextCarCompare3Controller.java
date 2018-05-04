package pl.CarComp.controllers.controllersDubler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import pl.CarComp.controllers.CarChooserController;
import pl.CarComp.controllers.CarCompareController;
import pl.CarComp.controllers.MainController;
import pl.CarComp.utils.DialogWindows;

public class NextCarCompare3Controller {
    @FXML
    private GridPane nextGridPane;
    @FXML
    private Label nextSelectedCarTitleLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private ListView<String> priceListViev3,engineListViev3,speedListViev3,dimensionListviev3,fuelListviev3;
    private int identifyColumnForDelete;
    private int identifyColumnForChangeCar;
    private int identifyColumnForEdit;

    private static CarCompareController foreignCarCompareController;
    private static CarChooserController foreignCarChooserController;
    private static MainController foreignMainController;

    public static void setForeignCarCompareController(CarCompareController carComContr) {
        NextCarCompare3Controller.foreignCarCompareController = carComContr;
    }

    public static void setForeignCarChooserController(CarChooserController carChooserContr) {
        NextCarCompare3Controller.foreignCarChooserController = carChooserContr;
    }

    public static void setForeignMainController(MainController mainContro) {
        NextCarCompare3Controller.foreignMainController = mainContro;
    }

    @FXML
    public void initialize() {
        CarCompareController.setForeignNextCarCompare3Controller(this);
        MainController.setForeignNextCarCompare3Controller(this);
        CarChooserController.setForeignNextCarCompare3Controller(this);
        setIdentifyColumnForChangeCar(0);
        //System.out.println("third Next change is "+getIdentifyColumnForChangeCar());
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

    public ListView<String> getPriceListViev3() {
        return priceListViev3;
    }

    public void setPriceListViev3(ListView<String> priceListViev3) {
        this.priceListViev3 = priceListViev3;
    }

    public ListView<String> getEngineListViev3() {
        return engineListViev3;
    }

    public void setEngineListViev3(ListView<String> engineListViev3) {
        this.engineListViev3 = engineListViev3;
    }

    public ListView<String> getSpeedListViev3() {
        return speedListViev3;
    }

    public void setSpeedListViev3(ListView<String> speedListViev3) {
        this.speedListViev3 = speedListViev3;
    }

    public ListView<String> getDimensionListviev3() {
        return dimensionListviev3;
    }

    public void setDimensionListviev3(ListView<String> dimensionListviev3) {
        this.dimensionListviev3 = dimensionListviev3;
    }

    public ListView<String> getFuelListviev3() {
        return fuelListviev3;
    }

    public void setFuelListviev3(ListView<String> fuelListviev3) {
        this.fuelListviev3 = fuelListviev3;
    }
}




