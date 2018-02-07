package pl.CarComp.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import pl.CarComp.modelFX.*;
import pl.CarComp.modelFXControler.*;
import pl.CarComp.utils.DialogWindows;
import pl.CarComp.utils.exceptions.ApplicationException;

public class CarChooserController {

    public static final String FXML_CAR_COMPARE_WINDOW_FXML = "/fxml/carCompareWindow.fxml";

    private static MainController mainController2;

    //list from fx controllers

    private BrandsFXcontroller brandsFXcontroller;
    private ModelsFXcontroller modelsFXcontroller;
    private FuelsFXcontroller fuelsFXcontroller;
    private CapacitiesFXcontroller capacitiesFXcontroller;
    private VersionsFXcontroller versionsFXcontroller;


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


    // add controller
    public static void setMainController(MainController mainController) {
        CarChooserController.mainController2 = mainController;
    }

    // CLOSE car chooser window on cancel button
    @FXML
    private void cancelButton(ActionEvent e) {
        ((Node) (e.getSource())).getScene().getWindow().hide();
    }

    // SELECT from brand combobox TAB 1
    @FXML
    public void selectBrandChoiceComboBox() {
        brandLabel.setText(brandChoiceComboBox.getValue().toString());
        if (brandLabel == null) {
            nextTabButton1.setDisable(true);
        } else {
            nextTabButton1.setDisable(false);
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
        modelLabel.setText(modelChoiceComboBox.getValue().toString());
        if (modelLabel == null) {
            nextTabButton2.setDisable(true);
        } else {
            nextTabButton2.setDisable(false);
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
        fuelLabel.setText(fuelChoiceComboBox.getValue().toString());
        if (fuelLabel == null) {
            nextTabButton3.setDisable(true);
        }
    }

    // select from capacity combobox TAB 3
    @FXML
    public void selectCapacityChoiceComboBox() {
        capacityLabel.setText(capacityChoiceComboBox.getValue().toString());
        if (capacityLabel == null) {
            nextTabButton3.setDisable(true);
        } else {
            nextTabButton3.setDisable(false);
        }
    }

    // Next button - go to next tab from TAB 3
    @FXML
    private void goToNextTab3() {
        mainTabPane.getSelectionModel().selectNext();

    }

    // select from version combobox TAB 4
    @FXML
    private void setVersionChoice() {
        versionLabel.setText(versionChoiceComboBox.getValue().toString());

    }

    // button Select car
    @FXML
    public void setSelected(ActionEvent e) {
        try {
            mainController2.setCenter(FXML_CAR_COMPARE_WINDOW_FXML);
        } catch (Exception exc) {
            DialogWindows.errorDialog(exc.getMessage());
            System.out.println(exc);
        }
        // hiding this window after click button
        ((Node) (e.getSource())).getScene().getWindow().hide();
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
        this.modelsFXcontroller = new ModelsFXcontroller();
        //fuels
        this.fuelsFXcontroller = new FuelsFXcontroller();
        //capacities
        this.capacitiesFXcontroller = new CapacitiesFXcontroller();
        //versions
        this.versionsFXcontroller = new VersionsFXcontroller();
        try {
            this.brandsFXcontroller.initializeBrandsList();
            this.modelsFXcontroller.initializeModelsList();
            this.fuelsFXcontroller.initializeFuelsList();
            this.capacitiesFXcontroller.initializeCapacitiesList();
            this.versionsFXcontroller.initializeVersionsList();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }

        //setting item in combo box
        this.brandChoiceComboBox.setItems(this.brandsFXcontroller.getBrandsList());
        this.modelChoiceComboBox.setItems(this.modelsFXcontroller.getModelsList());
        this.fuelChoiceComboBox.setItems(this.fuelsFXcontroller.getFuelsList());
        this.capacityChoiceComboBox.setItems(this.capacitiesFXcontroller.getCapacityList());
        this.versionChoiceComboBox.setItems(this.versionsFXcontroller.getVersionsList());

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
                    return brandLabel.getText().isEmpty() || modelLabel.getText().isEmpty()
                            || fuelLabel.getText().isEmpty() || capacityLabel.getText().isEmpty()
                            || versionLabel.getText().isEmpty();
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

}
