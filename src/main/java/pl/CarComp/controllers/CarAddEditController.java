package pl.CarComp.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.CarComp.modelFX.*;
import pl.CarComp.modelFXControler.*;
import pl.CarComp.utils.DialogWindows;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.Optional;

public class CarAddEditController {


    private BrandsFXcontroller brandsFXcontroller;
    private ModelsFXcontroller modelsFXcontroller;
    private FuelsFXcontroller fuelsFXcontroller;
    private CapacitiesFXcontroller capacitiesFXcontroller;
    private VersionsFXcontroller versionsFXcontroller;
    private CarsCharacteristicsFXcontroller carsCharacteristicsFXcontroller;
    @FXML
    private ScrollPane carAddPane;
    @FXML
    private TextField brandNameTextF, modelNameTextF, fuelNameTextF, capacityNameTextF, versionNameTextF, priceTF, gearboxSizeTF, engineCapacityTF, engineTypeTF, cylindersTF, powerMaxTF, torqueMaxTF, cityFuelTF, roadFuelTF, fuelNormalTF, tankCapacityTF, accelerationTF, speedTF, lenghtTF, widthTF, heightTF, wheelbaseTF, trunkTF, doorsTF, weightTF;
    @FXML
    private RadioButton manualTransmissionRB, automaticTransmissionRB;
    @FXML
    private DatePicker datePickerField;
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
    private Button saveDataButton, deleteDataButton, addBrandButton, addModelButton, addFuelButton, addCapacityButton, addVersionButton, deleteBrandButton, deleteModelButton, deleteFuelButton, deleteCapacityButton, deleteVersionButton;


    @FXML
    public void initialize() {
        /*
         * init ** FX controllers and methods from it
         * */

        this.brandsFXcontroller = new BrandsFXcontroller();//dodac wyjatki
        this.modelsFXcontroller = new ModelsFXcontroller();
        this.fuelsFXcontroller = new FuelsFXcontroller();
        this.capacitiesFXcontroller = new CapacitiesFXcontroller();
        this.versionsFXcontroller = new VersionsFXcontroller();
        this.carsCharacteristicsFXcontroller = new CarsCharacteristicsFXcontroller();
        try {
            this.brandsFXcontroller.initializeBrandsList();
            this.modelsFXcontroller.initializeModelsList();
            this.fuelsFXcontroller.initializeFuelsList();
            this.capacitiesFXcontroller.initializeCapacitiesList();
            this.versionsFXcontroller.initializeVersionsList();
            this.carsCharacteristicsFXcontroller.initCarsCharacteristicFXcontroller();
            initBindings();
            initBindingsForMainCategories();
            propertyBindings();
            textFieldBindings();
            initBindingsForCarCharacteristics();
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

    @FXML
    public void deleteFromDatabase() {
        Optional<ButtonType> result = DialogWindows.confirmationOnremove();
        if (result.get() == ButtonType.OK) {
            try {
                carsCharacteristicsFXcontroller.deleteCar();
            } catch (ApplicationException e) {
                DialogWindows.errorDialog(e.getMessage());
            }
        }
    }

    @FXML
    public void saveInDatabase() {
        //add update
        try {
            /*brandsFXcontroller.saveBrandInDatabase(brandNameTextF.getText());
            modelsFXcontroller.saveModelInDatabase();
            fuelsFXcontroller.saveFuelInDatabase();
            capacitiesFXcontroller.saveCapacityInDatabase();
            versionsFXcontroller.saveVersionInDatabase();*/
            carsCharacteristicsFXcontroller.saveCarsCharacteristicsinDatabase();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        clearTextfields();
        clearCombobox();
    }

    @FXML
    private void selectBrandComboBox() {
        if (modelChoiceComboBox2.getSelectionModel().isEmpty() &&
                fuelChoiceComboBox2.getSelectionModel().isEmpty() &&
                capacityChoiceComboBox2.getSelectionModel().isEmpty() &&
                versionChoiceComboBox2.getSelectionModel().isEmpty()) {
            try {
                this.modelsFXcontroller.initializeFilteredModelsList();
            } catch (ApplicationException e) {
                DialogWindows.errorDialog(e.getMessage());
            }
        } else {
            modelChoiceComboBox2.getSelectionModel().clearSelection();
            fuelChoiceComboBox2.getSelectionModel().clearSelection();
            capacityChoiceComboBox2.getSelectionModel().clearSelection();
            versionChoiceComboBox2.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void selectModelComboBox() {
        if (modelChoiceComboBox2.getValue() != null) {
            if (fuelChoiceComboBox2.getSelectionModel().isEmpty() &&
                    capacityChoiceComboBox2.getSelectionModel().isEmpty() &&
                    versionChoiceComboBox2.getSelectionModel().isEmpty()) {
            } else {
                fuelChoiceComboBox2.getSelectionModel().clearSelection();
                capacityChoiceComboBox2.getSelectionModel().clearSelection();
                versionChoiceComboBox2.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    private void selectFuelComboBox() {
        if (fuelChoiceComboBox2.getValue() != null) {

            if (capacityChoiceComboBox2.getSelectionModel().isEmpty() &&
                    versionChoiceComboBox2.getSelectionModel().isEmpty()) {

            } else {
                capacityChoiceComboBox2.getSelectionModel().clearSelection();
                versionChoiceComboBox2.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    private void selectCapacityComboBox() {
        if (capacityChoiceComboBox2.getValue() != null) {
            if (versionChoiceComboBox2.getSelectionModel().isEmpty()) {

            } else {
                versionChoiceComboBox2.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    private void selectVersionComboBox() {
        versionChoiceComboBox2.getValue();
        //this.carsCharacteristicsFXcontroller.filterCharacteristics();

    }

    @FXML
    public void addNewBrand() {
        try {
            brandsFXcontroller.saveBrandInDatabase(brandNameTextF.getText());
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        brandNameTextF.clear();
    }

    @FXML
    public void addNewModel() {
        try {
            modelsFXcontroller.saveModelInDatabase();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        modelNameTextF.clear();
    }

    @FXML
    public void addNewFuel() {
        try {
            fuelsFXcontroller.saveFuelInDatabase();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        fuelNameTextF.clear();

    }

    public void addNewCapacity() {
        try {
            capacitiesFXcontroller.saveCapacityInDatabase();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        capacityNameTextF.clear();
    }

    public void addNewVersion() {
        try {
            versionsFXcontroller.saveVersionInDatabase();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        versionNameTextF.clear();
    }

    private void deleteSelectedBrand() {
    }

    private void deleteSelectedModel() {
    }

    private void deleteSelectedFuel() {
    }

    private void deleteSelectedCapacity() {
    }

    private void deleteSelectedVersion() {
    }


    //cannot save or delete new  car until choose main categories
    private void initBindings() {
        this.saveDataButton.disableProperty().bind(Bindings.and(brandNameTextF.textProperty().isEmpty(), brandChoiceComboBox2.valueProperty().isNull()));
        this.saveDataButton.disableProperty().bind(Bindings.and(modelNameTextF.textProperty().isEmpty(), modelChoiceComboBox2.valueProperty().isNull()));
        this.saveDataButton.disableProperty().bind(Bindings.and(fuelNameTextF.textProperty().isEmpty(), fuelChoiceComboBox2.valueProperty().isNull()));
        this.saveDataButton.disableProperty().bind(Bindings.and(capacityNameTextF.textProperty().isEmpty(), capacityChoiceComboBox2.valueProperty().isNull()));
        this.saveDataButton.disableProperty().bind(Bindings.and(versionNameTextF.textProperty().isEmpty(), versionChoiceComboBox2.valueProperty().isNull()));
        this.saveDataButton.disableProperty().bind(Bindings.or(datePickerField.valueProperty().isNull(), priceTF.textProperty().isEmpty()));

        this.deleteDataButton.disableProperty().bind(Bindings.and(brandNameTextF.textProperty().isEmpty(), brandChoiceComboBox2.valueProperty().isNull()));
        this.deleteDataButton.disableProperty().bind(Bindings.and(modelNameTextF.textProperty().isEmpty(), modelChoiceComboBox2.valueProperty().isNull()));
        this.deleteDataButton.disableProperty().bind(Bindings.and(fuelNameTextF.textProperty().isEmpty(), fuelChoiceComboBox2.valueProperty().isNull()));
        this.deleteDataButton.disableProperty().bind(Bindings.and(capacityNameTextF.textProperty().isEmpty(), capacityChoiceComboBox2.valueProperty().isNull()));
        this.deleteDataButton.disableProperty().bind(Bindings.and(versionNameTextF.textProperty().isEmpty(), versionChoiceComboBox2.valueProperty().isNull()));
        this.deleteDataButton.disableProperty().bind(Bindings.or(datePickerField.valueProperty().isNull(), priceTF.textProperty().isEmpty()));

    }

    //cannot save or delete new  main categories until fill main text fields
    private void initBindingsForMainCategories() {
        addBrandButton.disableProperty().bind(brandNameTextF.textProperty().isEmpty());
        addModelButton.disableProperty().bind(modelNameTextF.textProperty().isEmpty());
        addFuelButton.disableProperty().bind(fuelNameTextF.textProperty().isEmpty());
        addCapacityButton.disableProperty().bind(capacityNameTextF.textProperty().isEmpty());
        addVersionButton.disableProperty().bind(versionNameTextF.textProperty().isEmpty());

        deleteBrandButton.disableProperty().bind(brandNameTextF.textProperty().isEmpty());
        deleteModelButton.disableProperty().bind(modelNameTextF.textProperty().isEmpty());
        deleteFuelButton.disableProperty().bind(fuelNameTextF.textProperty().isEmpty());
        deleteCapacityButton.disableProperty().bind(capacityNameTextF.textProperty().isEmpty());
        deleteVersionButton.disableProperty().bind(versionNameTextF.textProperty().isEmpty());
    }

    private void propertyBindings() {
        this.brandChoiceComboBox2.valueProperty().bindBidirectional(this.brandsFXcontroller.getBrandsFxObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox2.valueProperty().bindBidirectional(this.modelsFXcontroller.getModelsFxObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox2.valueProperty().bindBidirectional(this.fuelsFXcontroller.getFuelsFXObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox2.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox2.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox2.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().brandsFXObjectPropertyProperty());

        this.modelChoiceComboBox2.valueProperty().bindBidirectional(this.modelsFXcontroller.getModelsFxObjectProperty().modelsFXObjectPropertyProperty());
        this.modelNameTextF.textProperty().bindBidirectional(this.modelsFXcontroller.getModelsFxObjectProperty().modelProperty());
        this.modelChoiceComboBox2.valueProperty().bindBidirectional(this.modelsFXcontroller.getModelsFxObjectProperty().modelsFXObjectPropertyProperty());
        this.modelChoiceComboBox2.valueProperty().bindBidirectional(this.fuelsFXcontroller.getFuelsFXObjectProperty().modelsFXObjectPropertyProperty());
        this.modelChoiceComboBox2.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().modelsFXObjectPropertyProperty());
        this.modelChoiceComboBox2.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().modelsFXObjectPropertyProperty());
        this.modelChoiceComboBox2.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().modelsFXObjectPropertyProperty());

        this.fuelChoiceComboBox2.valueProperty().bindBidirectional(this.fuelsFXcontroller.getFuelsFXObjectProperty().fuelsFXObjectPropertyProperty());
        this.fuelNameTextF.textProperty().bindBidirectional(this.fuelsFXcontroller.getFuelsFXObjectProperty().fuelProperty());
        this.fuelChoiceComboBox2.valueProperty().bindBidirectional(this.fuelsFXcontroller.getFuelsFXObjectProperty().fuelsFXObjectPropertyProperty());
        this.fuelChoiceComboBox2.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().fuelsFXObjectPropertyProperty());
        this.fuelChoiceComboBox2.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().fuelsFXObjectPropertyProperty());
        this.fuelChoiceComboBox2.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().fuelsFXObjectPropertyProperty());

        this.capacityChoiceComboBox2.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().capacitiesFXObjectPropertyProperty());
        this.capacityNameTextF.textProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().capacityProperty());
        this.capacityChoiceComboBox2.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().capacitiesFXObjectPropertyProperty());
        this.capacityChoiceComboBox2.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().capacitiesFXObjectPropertyProperty());
        this.capacityChoiceComboBox2.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().capacitiesFXObjectPropertyProperty());

        this.versionChoiceComboBox2.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().versionsFXObjectPropertyProperty());
        this.versionNameTextF.textProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().getVersionProperty());
        this.versionChoiceComboBox2.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().versionsFXObjectPropertyProperty());
        this.versionChoiceComboBox2.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().versionsFXObjectPropertyProperty());
    }

    //main text field is disable until select from combo box
    private void textFieldBindings() {
        this.modelNameTextF.disableProperty().bind(brandChoiceComboBox2.valueProperty().isNull());
        this.fuelNameTextF.disableProperty().bind(modelChoiceComboBox2.valueProperty().isNull());
        this.capacityNameTextF.disableProperty().bind(fuelChoiceComboBox2.valueProperty().isNull());
        this.versionNameTextF.disableProperty().bind(capacityChoiceComboBox2.valueProperty().isNull());
    }

    private void initBindingsForCarCharacteristics() {
        priceTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().priceProperty());
        datePickerField.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().priceDateProperty());
        automaticTransmissionRB.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().transmissionProperty());
        manualTransmissionRB.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().transmissionProperty());
        engineCapacityTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().engCapacityProperty());
        engineTypeTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().engTypeProperty());
        cylindersTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().cylindersProperty());
        powerMaxTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().engPowerProperty());
        torqueMaxTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().torqueProperty());
        cityFuelTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().fuelConsCityProperty());
        fuelNormalTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().fuelConsMixedProperty());
        roadFuelTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().fuelConsRouteProperty());
        tankCapacityTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().tankCapacityProperty());
        accelerationTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().accelerationProperty());
        speedTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().topSpeedProperty());
        lenghtTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().lenghtProperty());
        weightTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().weightProperty());
        heightTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().heightProperty());
        widthTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().widthProperty());
        wheelbaseTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().wheeelbaseProperty());
        trunkTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().trunkProperty());
        doorsTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().doorsProperty());
        gearboxSizeTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().gearboxSizeProperty());

    }

    private void clearTextfields() {
        brandNameTextF.clear();
        modelNameTextF.clear();
        fuelNameTextF.clear();
        capacityNameTextF.clear();
        versionNameTextF.clear();
        priceTF.clear();
        datePickerField.getEditor().clear();
        manualTransmissionRB.setSelected(false);
        automaticTransmissionRB.setSelected(false);
        engineCapacityTF.clear();
        engineTypeTF.clear();
        cylindersTF.clear();
        powerMaxTF.clear();
        torqueMaxTF.clear();
        fuelNormalTF.clear();
        roadFuelTF.clear();
        cityFuelTF.clear();
        tankCapacityTF.clear();
        accelerationTF.clear();
        lenghtTF.clear();
        widthTF.clear();
        weightTF.clear();
        heightTF.clear();
        wheelbaseTF.clear();
        trunkTF.clear();
        doorsTF.clear();
        speedTF.clear();
        gearboxSizeTF.clear();
    }

    private void clearCombobox() {
        brandChoiceComboBox2.getSelectionModel().clearSelection();
        modelChoiceComboBox2.getSelectionModel().clearSelection();
        fuelChoiceComboBox2.getSelectionModel().clearSelection();
        capacityChoiceComboBox2.getSelectionModel().clearSelection();
        versionChoiceComboBox2.getSelectionModel().clearSelection();
    }
}


