package pl.CarComp.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import pl.CarComp.controllers.controllersDubler.NextCarCompare2Controller;
import pl.CarComp.controllers.controllersDubler.NextCarCompare3Controller;
import pl.CarComp.controllers.controllersDubler.NextCarCompareController;
import pl.CarComp.modelFX.*;
import pl.CarComp.modelFXControler.*;
import pl.CarComp.utils.DialogWindows;
import pl.CarComp.utils.exceptions.ApplicationException;

// dezaktywacja comboboxow
// set to default in combobox
public class CarChooserController {

    public static final String FXML_CAR_COMPARE_WINDOW_FXML = "/fxml/carCompareWindow.fxml";


    //list from fx controllers
    private BrandsFXcontroller brandsFXcontroller;

    @FXML
    private TabPane mainTabPane;
    @FXML
    private ComboBox<BrandsFX> brandChoiceComboBox;
    @FXML
    private ComboBox<ModelsFX> modelChoiceComboBox;
    @FXML
    private ComboBox<FuelsFX> fuelChoiceComboBox;
    @FXML
    private ComboBox<CapacitiesFX> capacityChoiceComboBox;
    @FXML
    private ComboBox<VersionsFX> versionChoiceComboBox;
    @FXML
    private Label brandLabel, modelLabel, fuelLabel, capacityLabel, versionLabel;
    @FXML
    private Button selectCarButton;
    @FXML
    private Button nextTabButton1, nextTabButton2, nextTabButton3;

    // controllers
    private static MainController foreignMainController;
    private static CarCompareController foreignCarCompareController;
    private static NextCarCompareController foreignNextCarCompareController;
    private static NextCarCompare2Controller foreignNextCarCompare2Controller;
    private static NextCarCompare3Controller foreignNextCarCompare3Controller;


    // add controller
    public static void setMainController(MainController mainController) {
        CarChooserController.foreignMainController = mainController;
    }

    public static void setForeignCarCompareController(CarCompareController carCompareController) {
        CarChooserController.foreignCarCompareController = carCompareController;
    }

    public static void setForeignNextCarCompareController(NextCarCompareController nextCarCompareController) {
        CarChooserController.foreignNextCarCompareController = nextCarCompareController;
    }

    public static void setForeignNextCarCompare2Controller(NextCarCompare2Controller nextCarCompare2Contr) {
        CarChooserController.foreignNextCarCompare2Controller = nextCarCompare2Contr;
    }

    public static void setForeignNextCarCompare3Controller(NextCarCompare3Controller nextCarCompare3Contr) {
        CarChooserController.foreignNextCarCompare3Controller = nextCarCompare3Contr;
    }

    // CLOSE car chooser window on cancel button and turn OFF chosen column
    @FXML
    private void cancelButton(ActionEvent e) {
        ((Node) (e.getSource())).getScene().getWindow().hide();

        foreignCarCompareController.setIdFirstColumn(0);
        if (foreignNextCarCompareController != null) {
            foreignNextCarCompareController.setIdentifyColumnForDelete(0);
        }
        System.out.println("metoda OFF");
    }

    // SELECT from brand combobox TAB 1
    @FXML
    public void selectBrandChoiceComboBox() {
        try {
            if (modelChoiceComboBox.getSelectionModel().isEmpty() &&
                    fuelChoiceComboBox.getSelectionModel().isEmpty() &&
                    capacityChoiceComboBox.getSelectionModel().isEmpty() &&
                    versionChoiceComboBox.getSelectionModel().isEmpty()) {
            } else {
                brandLabel.setText("");
                modelLabel.setText("");
                modelChoiceComboBox.getSelectionModel().clearSelection();
                fuelLabel.setText("");
                fuelChoiceComboBox.getSelectionModel().clearSelection();
                capacityLabel.setText("");
                capacityChoiceComboBox.getSelectionModel().clearSelection();
                versionLabel.setText("");
                versionChoiceComboBox.getSelectionModel().clearSelection();
            }

            brandLabel.setText(brandChoiceComboBox.getValue().toString());
            if (brandLabel.getText().equals("")) {
                nextTabButton1.setDisable(true);
            } else {
                nextTabButton1.setDisable(false);
            }

        } catch (Exception exc) {
            DialogWindows.errorDialog(exc.getMessage());
            System.out.println(exc);
        }
    }

    // Next button - go to next tab from TAB 1
    @FXML
    private void goToNextTab1() {
        mainTabPane.getSelectionModel().selectNext();
    }

    // select from model combobox TAB 2
    @FXML
    public void selectModelChoiceComboBox() {
        try {
            if (modelChoiceComboBox.getValue() != null) {

                if (fuelChoiceComboBox.getSelectionModel().isEmpty() &&
                        capacityChoiceComboBox.getSelectionModel().isEmpty() &&
                        versionChoiceComboBox.getSelectionModel().isEmpty()) {
                } else {
                    modelLabel.setText("");
                    fuelLabel.setText("");
                    fuelChoiceComboBox.getSelectionModel().clearSelection();
                    capacityLabel.setText("");
                    capacityChoiceComboBox.getSelectionModel().clearSelection();
                    versionLabel.setText("");
                    versionChoiceComboBox.getSelectionModel().clearSelection();
                }
                modelLabel.setText(modelChoiceComboBox.getValue().toString());
                if (modelLabel.getText().equals("")) {
                    nextTabButton2.setDisable(true);
                } else {
                    nextTabButton2.setDisable(false);
                }
            }
        } catch (Exception exc) {
            DialogWindows.errorDialog(exc.getMessage());
        }
    }

    // Next button - go to next tab from TAB 2
    @FXML
    private void goToNextTab2() {
        mainTabPane.getSelectionModel().selectNext();
        // add exception
    }

    // select from fuel combobox box TAB 3
    @FXML
    public void selectFuelChoiceComboBox() {
        try {
            if (fuelChoiceComboBox.getValue() != null) {

                if (capacityChoiceComboBox.getSelectionModel().isEmpty() &&
                        versionChoiceComboBox.getSelectionModel().isEmpty()) {

                } else {
                    fuelLabel.setText("");
                    capacityLabel.setText("");
                    capacityChoiceComboBox.getSelectionModel().clearSelection();
                    versionLabel.setText("");
                    versionChoiceComboBox.getSelectionModel().clearSelection();
                }

                fuelLabel.setText(fuelChoiceComboBox.getValue().toString());
                if (fuelLabel.getText().equals("")) {
                    nextTabButton3.setDisable(true);
                }
            }
        } catch (Exception exc) {
            DialogWindows.errorDialog(exc.getMessage());
        }
    }

    // select from capacity combobox TAB 3
    @FXML
    public void selectCapacityChoiceComboBox() {
        try {
            if (capacityChoiceComboBox.getValue() != null) {
                if (versionChoiceComboBox.getSelectionModel().isEmpty()) {

                } else {
                    capacityLabel.setText("");
                    versionLabel.setText("");
                    versionChoiceComboBox.getSelectionModel().clearSelection();
                }
                capacityLabel.setText(capacityChoiceComboBox.getValue().toString());
                if (capacityLabel.getText().equals("")) {
                    nextTabButton3.setDisable(true);
                } else {
                    nextTabButton3.setDisable(false);
                }
            }
        } catch (Exception exc) {
            DialogWindows.errorDialog(exc.getMessage());
        }
    }

    // Next button - go to next tab from TAB 3
    @FXML
    private void goToNextTab3() {
        mainTabPane.getSelectionModel().selectNext();
    }

    // select from version combobox TAB 4
    @FXML
    private void selectVersionChoiceComboBox() {
        versionLabel.setText("");
        try {
            if (versionChoiceComboBox.getValue() != null) {
                versionLabel.setText(versionChoiceComboBox.getValue().toString());
            }
        } catch (Exception exc) {
            DialogWindows.errorDialog(exc.getMessage());
        }
    }

    // activate button in main window and set data in GridPanes
    @FXML
    public void setSelected(ActionEvent e) {
        System.out.println("wielkosc listy: " + foreignMainController.getListOfGridPaneColumns().size());
        try {
            foreignMainController.activateAddCarButtton();
            if ((foreignNextCarCompareController == null & foreignNextCarCompare2Controller == null & foreignNextCarCompare3Controller == null) || foreignCarCompareController.getIdFirstColumn() == 1) { //jesli druga columna nie istnieje
                System.out.println("1.zaznaczono change w pierwszym");
                foreignCarCompareController.setMainColumn();
                foreignCarCompareController.setIdFirstColumn(0);
            }//jesli nastepne kolumny istnieją
            else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller == null & foreignNextCarCompare3Controller == null) {
                System.out.println("0,-,-");
                foreignNextCarCompareController.setNextColumn();
                foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);

            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller == null) {
                System.out.println("0,0,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller == null & foreignNextCarCompare3Controller == null) {
                System.out.println("1,-,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller == null) {
                System.out.println("1,1,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                }
            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller == null) {
                System.out.println("0,1,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                }else{//test
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller == null) {
                System.out.println("1,0,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                } else {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                }
            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("0,0,0");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                }else {//test
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("1,0,0");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                }else {//test
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("1,1,0");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                } else {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("1,1,1");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("1,0,1");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                }else {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                }
            }
            else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("0,1,1");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                } else {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                }
            }else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("0,0,1");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                }else{//test
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                }
            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("0,1,0");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                }else{//test
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                }
            }
        } catch (
                Exception exc)
        {
            DialogWindows.errorDialog(exc.getMessage());
            System.out.println(exc);
        }
        // hiding this window after click button
        ((Node) (e.getSource())).

                getScene().

                getWindow().

                hide();

    }

    private void turnOFFid() {
        foreignCarCompareController.setIdFirstColumn(0);
        foreignNextCarCompareController.setIdentifyColumnForDelete(0);
    }

    @FXML
    public void initialize() {
        nextTabButton1.setDisable(true);
        nextTabButton2.setDisable(true);
        nextTabButton3.setDisable(true);
        /*
      * init ** FX controllers and methods from it
      * */
        //brands
        this.brandsFXcontroller = new BrandsFXcontroller();
        //models
        ModelsFXcontroller modelsFXcontroller = new ModelsFXcontroller();
        //fuels
        FuelsFXcontroller fuelsFXcontroller = new FuelsFXcontroller();
        //capacities
        CapacitiesFXcontroller capacitiesFXcontroller = new CapacitiesFXcontroller();
        //versions
        VersionsFXcontroller versionsFXcontroller = new VersionsFXcontroller();
        try {
            this.brandsFXcontroller.initializeBrandsList();
            modelsFXcontroller.initializeModelsList();
            fuelsFXcontroller.initializeFuelsList();
            capacitiesFXcontroller.initializeCapacitiesList();
            versionsFXcontroller.initializeVersionsList();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }

        //setting item in combo box
        this.brandChoiceComboBox.setItems(this.brandsFXcontroller.getBrandsList());
        this.modelChoiceComboBox.setItems(modelsFXcontroller.getModelsList());
        this.fuelChoiceComboBox.setItems(fuelsFXcontroller.getFuelsList());
        this.capacityChoiceComboBox.setItems(capacitiesFXcontroller.getCapacityList());
        this.versionChoiceComboBox.setItems(versionsFXcontroller.getVersionsList());

        //activate Controllers class
        CarCompareController.setForeignCarChooserController(this);
        NextCarCompareController.setForeignCarChooserController(this);
        NextCarCompare2Controller.setForeignCarChooserController(this);
        NextCarCompare3Controller.setForeignCarChooserController(this);
        // bind every label of users choice
        BooleanBinding labelsAreSelected = new BooleanBinding() {
            {
                super.bind(brandLabel.textProperty(), modelLabel.textProperty(), fuelLabel.textProperty(),
                        capacityLabel.textProperty(), versionLabel.textProperty());
            }

            // check if every label is not empty
            @Override
            protected boolean computeValue() {
                try {
                    return brandLabel.getText().equals("") || modelLabel.getText().equals("")
                            || fuelLabel.getText().equals("") || capacityLabel.getText().equals("")
                            || versionLabel.getText().equals("");
                } catch (Exception e) {
                    DialogWindows.errorDialog(e.getMessage());
                    System.out.println(e);
                    // TO DO add window info or error
                }
                return false;
            }
        };
        selectCarButton.disableProperty().bind(labelsAreSelected);
    }

    public Label getBrandLabel() {
        return brandLabel;
    }

    public Label getModelLabel() {
        return modelLabel;
    }

    public Label getFuelLabel() {
        return fuelLabel;
    }

    public Label getCapacityLabel() {
        return capacityLabel;
    }

    public Label getVersionLabel() {
        return versionLabel;
    }


}
