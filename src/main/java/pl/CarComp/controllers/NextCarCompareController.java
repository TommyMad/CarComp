package pl.CarComp.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import pl.CarComp.utils.DialogWindows;

public class NextCarCompareController {
    @FXML
    private GridPane nextGridPane;
    private static CarCompareController foreignCarCompareController;

    public static void setForeignCarCompareController(CarCompareController carComContr) {
        NextCarCompareController.foreignCarCompareController= carComContr;
    }
    @FXML
    public void initialize(){
        CarCompareController.setForeignNextCarCompareController(this);
    }


    public void deleteColumn() {
        try {

            System.out.println(nextGridPane.getColumnConstraints());
            nextGridPane.getChildren().clear();
            foreignCarCompareController.deleteMainColumn();


        } catch (Exception e) {
            DialogWindows.errorDialog(e.getMessage());
            System.out.println(e);
        }
    }

    public GridPane getNextGridPane() {
        return nextGridPane;
    }
}
