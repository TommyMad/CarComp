package pl.CarComp.controllers.controllersDubler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        System.out.println("trzeci Next change is "+getIdentifyColumnForChangeCar());
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
        System.out.println("wlacz 3 kolumne: " + identifyColumnForChangeCar);
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




