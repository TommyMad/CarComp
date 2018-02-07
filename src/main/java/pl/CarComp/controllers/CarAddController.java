package pl.CarComp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import pl.CarComp.modelFX.*;
import pl.CarComp.modelFXControler.*;
import pl.CarComp.utils.DialogWindows;
import pl.CarComp.utils.exceptions.ApplicationException;

public class CarAddController {

    private BrandsFXcontroller brandsFXcontroller;
    private ModelsFXcontroller modelsFXcontroller;
    private FuelsFXcontroller fuelsFXcontroller;
    private CapacitiesFXcontroller capacitiesFXcontroller;
    private VersionsFXcontroller versionsFXcontroller;

    @FXML
    private TextField brandNameTextF,modelNameTextF,fuelNameTextF,capacityNameTextF,versionNameTextF;
    @FXML
    private ScrollPane scrollPaneCarAdd;
    @FXML
    private ComboBox<BrandsFX> brandChoiceComboBox2;
    @FXML
    private ComboBox<ModelsFX> modelChoiceComboBox2;
    @FXML
    private ComboBox<FuelsFX> fuelChoiceComboBox2;
    @FXML
    private ComboBox<CapacitiesFX> capacityChoiceComboBox2;
    @FXML
    private ComboBox<VersionsFX> versionChoiceComboBox2;
    @FXML
    private Button saveDataButton;
    @FXML
    private Button deleteDataButton;



    public void deleteFromDatabase() {
    }

    public void saveInDatabase() {
        try {
            brandsFXcontroller.saveBrandInDatabase(brandNameTextF.getText());
            modelsFXcontroller.saveModelInDatabase(modelNameTextF.getText());
            fuelsFXcontroller.saveFuelInDatabase(fuelNameTextF.getText());
            capacitiesFXcontroller.saveCapacityInDatabase(capacityNameTextF.getText());
            versionsFXcontroller.saveVersionInDatabase(versionNameTextF.getText());
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        brandNameTextF.clear();
        modelNameTextF.clear();
        fuelNameTextF.clear();
        capacityNameTextF.clear();
        versionNameTextF.clear();
    }


    @FXML
    public void initialize() {
        initBindings();
        /*
      * init ** FX controllers and methods from it
      * */
        //brands
        this.brandsFXcontroller = new BrandsFXcontroller();//dodac wyjatki
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
        this.brandChoiceComboBox2.setItems(this.brandsFXcontroller.getBrandsList());
        this.modelChoiceComboBox2.setItems(this.modelsFXcontroller.getModelsList());
        this.fuelChoiceComboBox2.setItems(this.fuelsFXcontroller.getFuelsList());
        this.capacityChoiceComboBox2.setItems(this.capacitiesFXcontroller.getCapacityList());
        this.versionChoiceComboBox2.setItems(this.versionsFXcontroller.getVersionsList());
    }
    // add more bindings
    private void initBindings(){
        this.saveDataButton.disableProperty().bind(brandNameTextF.textProperty().isEmpty());
        this.saveDataButton.disableProperty().bind(modelNameTextF.textProperty().isEmpty());
        this.saveDataButton.disableProperty().bind(fuelNameTextF.textProperty().isEmpty());
        this.saveDataButton.disableProperty().bind(capacityNameTextF.textProperty().isEmpty());
        this.saveDataButton.disableProperty().bind(versionNameTextF.textProperty().isEmpty());

        this.deleteDataButton.disableProperty().bind(brandNameTextF.textProperty().isEmpty());
        this.deleteDataButton.disableProperty().bind(modelNameTextF.textProperty().isEmpty());
        this.deleteDataButton.disableProperty().bind(fuelNameTextF.textProperty().isEmpty());
        this.deleteDataButton.disableProperty().bind(capacityNameTextF.textProperty().isEmpty());
        this.deleteDataButton.disableProperty().bind(versionNameTextF.textProperty().isEmpty());

    }
}
