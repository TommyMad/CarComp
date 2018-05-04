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
    private TextField brandNameTextF, modelNameTextF, fuelNameTextF, capacityNameTextF, versionNameTextF, priceTF, gearboxSizeTF, engineCapacityTF, engineTypeTF, cylindersTF, powerMaxTF, torqueMaxTF, cityFuelTF, roadFuelTF, fuelNormalTF, tankCapacityTF, accelerationTF, speedTF, lenghtTF, widthTF, heightTF, wheelbaseTF, trunkTF, doorsTF, weightTF, gearboxTypeTF;

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
        this.brandsFXcontroller = new BrandsFXcontroller();
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
           // this.carsCharacteristicsFXcontroller.initCarsCharacteristicFXcontroller();
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
                clearAllComboboxes();
                clearTextfields();
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
        clearAllComboboxes();
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
            clearTextfields();
        }
    }

    @FXML
    private void selectModelComboBox() {
        if (modelChoiceComboBox2.getValue() != null) {
            if (fuelChoiceComboBox2.getSelectionModel().isEmpty() &&
                    capacityChoiceComboBox2.getSelectionModel().isEmpty() &&
                    versionChoiceComboBox2.getSelectionModel().isEmpty()) {
                //filter capacity
                try {
                    this.capacitiesFXcontroller.initializeFilteredCapacitiesList();
                    this.versionsFXcontroller.initializeFilteredVersionsList();
                } catch (ApplicationException e) {
                    DialogWindows.errorDialog(e.getMessage());
                }
            } else {
                fuelChoiceComboBox2.getSelectionModel().clearSelection();
                capacityChoiceComboBox2.getSelectionModel().clearSelection();
                versionChoiceComboBox2.getSelectionModel().clearSelection();
                clearTextfields();
            }
        }
    }

    @FXML
    private void selectFuelComboBox() {
        if (fuelChoiceComboBox2.getValue() != null) {
            if (capacityChoiceComboBox2.getSelectionModel().isEmpty() &&
                    versionChoiceComboBox2.getSelectionModel().isEmpty()) {
                // errors when previous is not selected
            } else {
                capacityChoiceComboBox2.getSelectionModel().clearSelection();
                versionChoiceComboBox2.getSelectionModel().clearSelection();
                clearTextfields();
            }
        }
    }

    @FXML
    private void selectCapacityComboBox() {
        if (capacityChoiceComboBox2.getValue() != null) {
            if (versionChoiceComboBox2.getSelectionModel().isEmpty()) {
                try {
                    this.versionsFXcontroller.initializeFilteredVersionsList();
                } catch (ApplicationException e) {
                    DialogWindows.errorDialog(e.getMessage());
                }
            } else {
                versionChoiceComboBox2.getSelectionModel().clearSelection();
                clearTextfields();
            }
        }
    }

    @FXML
    private void selectVersionComboBox() {
        versionChoiceComboBox2.getValue();
        if (modelChoiceComboBox2.getSelectionModel().isEmpty() |
                fuelChoiceComboBox2.getSelectionModel().isEmpty() |
                capacityChoiceComboBox2.getSelectionModel().isEmpty()) {
        } else {
            try {
                this.carsCharacteristicsFXcontroller.initFilteredListCarsCharacteristic();
                    setTextFieldsCharactersitics();
            } catch (ApplicationException e) {
                DialogWindows.errorDialog(e.getMessage());
            }

        }
    }

    private void setTextFieldsCharactersitics() {
        int lastIndex = carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().size() - 1;
        if(lastIndex>0){
        priceTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getPrice());
        datePickerField.getEditor().setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getPriceDate().toString());
        gearboxTypeTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getTransmission());
        gearboxSizeTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getGearboxSize());
        engineCapacityTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getEngCapacity());
        engineTypeTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getEngType());
        cylindersTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getCylinders());
        powerMaxTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getEngPower());
        torqueMaxTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getTorque());
        cityFuelTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getFuelConsCity());
        roadFuelTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getFuelConsRoute());
        fuelNormalTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getFuelConsMixed());
        tankCapacityTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getTankCapacity());
        accelerationTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getAcceleration());
        speedTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getTopSpeed());
        lenghtTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getLenght());
        widthTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getWidth());
        heightTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getHeight());
        wheelbaseTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getWheeelbase());
        trunkTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getTrunk());
        doorsTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getDoors());
        weightTF.setText(carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex).getWeight());
    }}
    @FXML
    public void addNewBrand() {
        try {
            brandsFXcontroller.saveBrandInDatabase(brandNameTextF.getText());
            this.brandsFXcontroller.initializeBrandsList();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        brandNameTextF.clear();
    }

    @FXML
    public void addNewModel() {
        try {
            modelsFXcontroller.saveModelInDatabase();
            this.modelsFXcontroller.initializeFilteredModelsList();
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
            this.capacitiesFXcontroller.initializeFilteredCapacitiesList();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        capacityNameTextF.clear();
    }

    public void addNewVersion() {
        try {
            versionsFXcontroller.saveVersionInDatabase();
            this.versionsFXcontroller.initializeFilteredVersionsList();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        versionNameTextF.clear();
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
        gearboxTypeTF.textProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().transmissionProperty());
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
        gearboxTypeTF.clear();
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

    private void clearAllComboboxes() {
        versionChoiceComboBox2.getSelectionModel().clearSelection();
        brandChoiceComboBox2.getSelectionModel().clearSelection();
        modelChoiceComboBox2.getSelectionModel().clearSelection();
        fuelChoiceComboBox2.getSelectionModel().clearSelection();
        capacityChoiceComboBox2.getSelectionModel().clearSelection();
    }
}


