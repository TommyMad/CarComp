package pl.CarComp.controllers.controllersDubler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import pl.CarComp.controllers.CarChooserController;
import pl.CarComp.controllers.CarCompareController;
import pl.CarComp.controllers.MainController;
import pl.CarComp.utils.DialogWindows;

public class NextCarCompare2Controller {
    @FXML
    private GridPane nextGridPane;
    @FXML
    private Label nextSelectedCarTitleLabel;
    private int identifyColumnForDelete;
    private int identifyColumnForChangeCar;
    private int identifyColumnForEdit;

    // other controllers
    private static CarCompareController foreignCarCompareController;
    private static CarChooserController foreignCarChooserController;
    private static MainController foreignMainController;

    public static void setForeignCarCompareController(CarCompareController carComContr) {
        NextCarCompare2Controller.foreignCarCompareController = carComContr;
    }

    public static void setForeignCarChooserController(CarChooserController carChooserContr) {
        NextCarCompare2Controller.foreignCarChooserController = carChooserContr;
    }

    public static void setForeignMainController(MainController mainContro) {
        NextCarCompare2Controller.foreignMainController = mainContro;
    }

    @FXML
    public void initialize() {
        CarCompareController.setForeignNextCarCompare2Controller(this);
        MainController.setForeignNextCarCompare2Controller(this);
        CarChooserController.setForeignNextCarCompare2Controller(this);
        setIdentifyColumnForChangeCar(0);
        System.out.println("drugi Next change is "+getIdentifyColumnForChangeCar());
    }

    //delete one chosen column
    @FXML
    public void deleteColumn() {
        try {
            foreignCarCompareController.getMainHbox().getChildren().remove(nextGridPane);
            //nextGridPane.getChildren().clear();
            foreignMainController.getListOfGridPaneColumns().remove(nextGridPane);
            System.out.println("wielkosc po usunieciu: " + foreignMainController.getListOfGridPaneColumns().size());
            setIdentifyColumnForDelete(1);
        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
            System.out.println(e);
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
        System.out.println("wlacz 2 kolumne: " + identifyColumnForChangeCar);
    }

    public void editCarData() {
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
}



