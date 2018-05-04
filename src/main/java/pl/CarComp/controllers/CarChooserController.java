package pl.CarComp.controllers;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class CarChooserController {

    //public static final String FXML_CAR_COMPARE_WINDOW_FXML = "/fxml/carCompareWindow.fxml";

    //list from fx controllers
    private BrandsFXcontroller brandsFXcontroller;
    private ModelsFXcontroller modelsFXcontroller;
    private FuelsFXcontroller fuelsFXcontroller;
    private CapacitiesFXcontroller capacitiesFXcontroller;
    private VersionsFXcontroller versionsFXcontroller;
    private CarsCharacteristicsFXcontroller carsCharacteristicsFXcontroller;
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
    // list of car characteristics for inserting into Listviev
    private ObservableList<String> carsDataListPrices = FXCollections.observableArrayList();
    private ObservableList<String> carsDataListEngine = FXCollections.observableArrayList();
    private ObservableList<String> carsDataListFuelConsumption = FXCollections.observableArrayList();
    private ObservableList<String> carsDataListSpeed = FXCollections.observableArrayList();
    private ObservableList<String> carsDataListDimensions = FXCollections.observableArrayList();


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

    @FXML
    public void initialize() {
        nextTabButton1.setDisable(true);
        nextTabButton2.setDisable(true);
        nextTabButton3.setDisable(true);
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
            propertyBindingsForComboboxes();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
        //setting item in combo box
        this.brandChoiceComboBox.setItems(brandsFXcontroller.getBrandsList());
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
                }
                return false;
            }
        };
        selectCarButton.disableProperty().bind(labelsAreSelected);
    }

    // CLOSE car chooser window on cancel button and turn OFF chosen column
    @FXML
    private void cancelButton(ActionEvent e) {
        ((Node) (e.getSource())).getScene().getWindow().hide();

        foreignCarCompareController.setIdFirstColumn(0);
        if (foreignNextCarCompareController != null) {
            foreignNextCarCompareController.setIdentifyColumnForDelete(0);
        }
        //metoda OFF");
    }

    // SELECT from brand combobox TAB 1
    @FXML
    public void selectBrandChoiceComboBox() {
        try {
            if (modelChoiceComboBox.getSelectionModel().isEmpty() &&
                    fuelChoiceComboBox.getSelectionModel().isEmpty() &&
                    capacityChoiceComboBox.getSelectionModel().isEmpty() &&
                    versionChoiceComboBox.getSelectionModel().isEmpty()) {
                try {
                    this.modelsFXcontroller.initializeFilteredModelsList();
                } catch (ApplicationException e) {
                    DialogWindows.errorDialog(e.getMessage());
                }

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
                    try {
                        this.fuelsFXcontroller.initializeFilteredFuelsList();
                       //this.capacitiesFXcontroller.initializeFilteredCapacitiesList();
                        this.versionsFXcontroller.initializeFilteredVersionsList();
                    } catch (ApplicationException e) {
                        DialogWindows.errorDialog(e.getMessage());
                    }
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
                    try {
                        this.capacitiesFXcontroller.initializeFilteredCapacitiesList();
                    } catch (ApplicationException e) {
                        DialogWindows.errorDialog(e.getMessage());
                    }
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
                    try {
                        this.versionsFXcontroller.initializeFilteredVersionsList();
                    } catch (ApplicationException e) {
                        DialogWindows.errorDialog(e.getMessage());
                    }
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

    // 'column 0' is column build from carCompareWindow.fxml
    private void fillListvievColumn0() {
        clearCharacteristicsList();
        try {
            initFilteredListForListvievs();
            // insert lists of characteristics into  ListViev node
            foreignCarCompareController.getPriceListViev0().setItems(carsDataListPrices);
            foreignCarCompareController.getDimensionListviev0().setItems(carsDataListDimensions);
            foreignCarCompareController.getEngineListViev0().setItems(carsDataListEngine);
            foreignCarCompareController.getFuelListviev0().setItems(carsDataListFuelConsumption);
            foreignCarCompareController.getSpeedListViev0().setItems(carsDataListSpeed);
            refreshListviev0();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
    }

    // 'column 1' is column build from nextCarCompare1Window.fxml
    private void fillListvievColumn1() {
        clearCharacteristicsList();
        try {
            initFilteredListForListvievs();
            // insert lists of characteristics into ListViev node
            foreignNextCarCompareController.getPriceListViev1().setItems(carsDataListPrices);
            foreignNextCarCompareController.getDimensionListviev1().setItems(carsDataListDimensions);
            foreignNextCarCompareController.getEngineListViev1().setItems(carsDataListEngine);
            foreignNextCarCompareController.getFuelListviev1().setItems(carsDataListFuelConsumption);
            foreignNextCarCompareController.getSpeedListViev1().setItems(carsDataListSpeed);
            refreshListViev1();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
    }

    // 'column 2' is column build from nextCarCompare2Window.fxml
    private void fillListvievColumn2() {
        clearCharacteristicsList();
        try {
            initFilteredListForListvievs();
            // insert lists of characteristics into ListViev node
            foreignNextCarCompare2Controller.getPriceListViev2().setItems(carsDataListPrices);
            foreignNextCarCompare2Controller.getDimensionListviev2().setItems(carsDataListDimensions);
            foreignNextCarCompare2Controller.getEngineListViev2().setItems(carsDataListEngine);
            foreignNextCarCompare2Controller.getFuelListviev2().setItems(carsDataListFuelConsumption);
            foreignNextCarCompare2Controller.getSpeedListViev2().setItems(carsDataListSpeed);
            refreshListViev2();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
    }

    // 'column 3' is column build from nextCarCompare3Window.fxml
    private void fillListvievColumn3() {
        clearCharacteristicsList();
        try {
            initFilteredListForListvievs();
            // insert lists of characteristics into ListViev node
            foreignNextCarCompare3Controller.getPriceListViev3().setItems(carsDataListPrices);
            foreignNextCarCompare3Controller.getDimensionListviev3().setItems(carsDataListDimensions);
            foreignNextCarCompare3Controller.getEngineListViev3().setItems(carsDataListEngine);
            foreignNextCarCompare3Controller.getFuelListviev3().setItems(carsDataListFuelConsumption);
            foreignNextCarCompare3Controller.getSpeedListViev3().setItems(carsDataListSpeed);
            refreshListViev3();
        } catch (ApplicationException e) {
            DialogWindows.errorDialog(e.getMessage());
        }
    }

    //initialize method from CommonDao for filtering data, set 'lastIndex' for searching only last item in list
    private void initFilteredListForListvievs() throws ApplicationException {
        carsCharacteristicsFXcontroller.initFilteredListCarsCharacteristic();
        // get last index of list
        int lastIndex = carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().size() - 1;
        if (lastIndex >= 0) {
            CarsCharacteristicsFX carsCharacteristicsFXGetLastItem = carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObservableList().get(lastIndex);
            insertDataIntoTempLists(carsCharacteristicsFXGetLastItem);
        }
    }

    //get data from database and add it to various temporary list
    private void insertDataIntoTempLists(CarsCharacteristicsFX carsCharacteristicsFXGetLastItem) {
        carsDataListPrices.add(carsCharacteristicsFXGetLastItem.getPrice());
        carsDataListPrices.add(carsCharacteristicsFXGetLastItem.getPriceDate().toString());

        carsDataListEngine.add(carsCharacteristicsFXGetLastItem.getEngCapacity());
        carsDataListEngine.add(carsCharacteristicsFXGetLastItem.getEngType());
        carsDataListEngine.add(carsCharacteristicsFXGetLastItem.getCylinders());
        carsDataListEngine.add(carsCharacteristicsFXGetLastItem.getTransmission());
        carsDataListEngine.add(carsCharacteristicsFXGetLastItem.getGearboxSize());
        carsDataListEngine.add(carsCharacteristicsFXGetLastItem.getEngPower());
        carsDataListEngine.add(carsCharacteristicsFXGetLastItem.getTorque());

        carsDataListDimensions.add(carsCharacteristicsFXGetLastItem.getWeight());
        carsDataListDimensions.add(carsCharacteristicsFXGetLastItem.getLenght());
        carsDataListDimensions.add(carsCharacteristicsFXGetLastItem.getHeight());
        carsDataListDimensions.add(carsCharacteristicsFXGetLastItem.getWidth());
        carsDataListDimensions.add(carsCharacteristicsFXGetLastItem.getWheeelbase());
        carsDataListDimensions.add(carsCharacteristicsFXGetLastItem.getTrunk());
        carsDataListDimensions.add(carsCharacteristicsFXGetLastItem.getDoors());

        carsDataListFuelConsumption.add(carsCharacteristicsFXGetLastItem.getFuelConsCity());
        carsDataListFuelConsumption.add(carsCharacteristicsFXGetLastItem.getFuelConsRoute());
        carsDataListFuelConsumption.add(carsCharacteristicsFXGetLastItem.getFuelConsMixed());
        carsDataListFuelConsumption.add(carsCharacteristicsFXGetLastItem.getTankCapacity());

        carsDataListSpeed.add(carsCharacteristicsFXGetLastItem.getAcceleration());
        carsDataListSpeed.add(carsCharacteristicsFXGetLastItem.getTopSpeed());
    }

    private void refreshListviev0() {
        foreignCarCompareController.getPriceListViev0().refresh();
        foreignCarCompareController.getDimensionListviev0().refresh();
        foreignCarCompareController.getEngineListViev0().refresh();
        foreignCarCompareController.getFuelListviev0().refresh();
        foreignCarCompareController.getSpeedListViev0().refresh();
    }

    private void refreshListViev1() {
        foreignNextCarCompareController.getPriceListViev1().refresh();
        foreignNextCarCompareController.getPriceListViev1().refresh();
        foreignNextCarCompareController.getPriceListViev1().refresh();
        foreignNextCarCompareController.getPriceListViev1().refresh();
        foreignNextCarCompareController.getPriceListViev1().refresh();
    }

    private void refreshListViev2() {
        foreignNextCarCompare2Controller.getPriceListViev2().refresh();
        foreignNextCarCompare2Controller.getPriceListViev2().refresh();
        foreignNextCarCompare2Controller.getPriceListViev2().refresh();
        foreignNextCarCompare2Controller.getPriceListViev2().refresh();
        foreignNextCarCompare2Controller.getPriceListViev2().refresh();
    }

    private void refreshListViev3() {
        foreignNextCarCompare3Controller.getPriceListViev3().refresh();
        foreignNextCarCompare3Controller.getPriceListViev3().refresh();
        foreignNextCarCompare3Controller.getPriceListViev3().refresh();
        foreignNextCarCompare3Controller.getPriceListViev3().refresh();
        foreignNextCarCompare3Controller.getPriceListViev3().refresh();
    }

    private void clearCharacteristicsList() {
        carsDataListPrices.clear();
        carsDataListEngine.clear();
        carsDataListSpeed.clear();
        carsDataListFuelConsumption.clear();
        carsDataListDimensions.clear();
    }

    /* activate button in main window and set data in GridPanes
    0 - column exist but is empty
    1 - column exist and it's not empty
    "-" - column don't exist
    
    */
    @FXML
    public void setSelected(ActionEvent e) {
        try {
            foreignMainController.activateAddCarButton();
            if ((foreignNextCarCompareController == null & foreignNextCarCompare2Controller == null & foreignNextCarCompare3Controller == null) || foreignCarCompareController.getIdFirstColumn() == 1) {//2,3,4 column don't exist
                //first columns checked
                foreignCarCompareController.setMainColumn();
                foreignCarCompareController.setIdFirstColumn(0);
                fillListvievColumn0();
            }//instructions if 2,3,4 column exist
            else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller == null & foreignNextCarCompare3Controller == null) {
                System.out.println("0,-,-");
                foreignNextCarCompareController.setNextColumn();
                foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                fillListvievColumn1();
            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller == null) {
                System.out.println("0,0,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
// testing
                } else {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller == null & foreignNextCarCompare3Controller == null) {
                System.out.println("1,-,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller == null) {
                System.out.println("1,1,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                }
            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller == null) {
                System.out.println("0,1,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller == null) {
                System.out.println("1,0,-");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                }
            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("0,0,0");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn3();
                } else {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("1,0,0");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn3();
                } else {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("1,1,0");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn3();
                } else {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn3();
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("1,1,1");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn3();
                }
            } else if (!foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("1,0,1");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn3();
                } else {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                }
            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("0,1,1");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn3();
                } else {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                }
            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("0,0,1");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn3();
                } else {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                }
            } else if (foreignNextCarCompareController.getNextSelectedCarTitleLabel().getText().isEmpty() & !foreignNextCarCompare2Controller.getNextSelectedCarTitleLabel().getText().isEmpty() & foreignNextCarCompare3Controller.getNextSelectedCarTitleLabel().getText().isEmpty()) {
                System.out.println("0,1,0");
                if (foreignNextCarCompareController.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                } else if (foreignNextCarCompare2Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare2Controller.setNextColumn();
                    foreignNextCarCompare2Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn2();
                } else if (foreignNextCarCompare3Controller.getIdentifyColumnForChangeCar() == 1) {
                    foreignNextCarCompare3Controller.setNextColumn();
                    foreignNextCarCompare3Controller.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn3();
                } else {
                    foreignNextCarCompareController.setNextColumn();
                    foreignNextCarCompareController.setIdentifyColumnForChangeCar(0);
                    fillListvievColumn1();
                }
            }
        } catch (
                Exception exc) {
            DialogWindows.errorDialog(exc.getMessage());
        }
        // hiding this window after click button
        ((Node) (e.getSource())).

                getScene().

                getWindow().

                hide();

    }

    private void propertyBindingsForComboboxes() {
        this.brandChoiceComboBox.valueProperty().bindBidirectional(this.brandsFXcontroller.getBrandsFxObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox.valueProperty().bindBidirectional(this.modelsFXcontroller.getModelsFxObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox.valueProperty().bindBidirectional(this.fuelsFXcontroller.getFuelsFXObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().brandsFXObjectPropertyProperty());
        this.brandChoiceComboBox.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().brandsFXObjectPropertyProperty());

        this.modelChoiceComboBox.valueProperty().bindBidirectional(this.modelsFXcontroller.getModelsFxObjectProperty().modelsFXObjectPropertyProperty());
        this.modelChoiceComboBox.valueProperty().bindBidirectional(this.modelsFXcontroller.getModelsFxObjectProperty().modelsFXObjectPropertyProperty());
        this.modelChoiceComboBox.valueProperty().bindBidirectional(this.fuelsFXcontroller.getFuelsFXObjectProperty().modelsFXObjectPropertyProperty());
        this.modelChoiceComboBox.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().modelsFXObjectPropertyProperty());
        this.modelChoiceComboBox.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().modelsFXObjectPropertyProperty());
        this.modelChoiceComboBox.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().modelsFXObjectPropertyProperty());

        this.fuelChoiceComboBox.valueProperty().bindBidirectional(this.fuelsFXcontroller.getFuelsFXObjectProperty().fuelsFXObjectPropertyProperty());
        this.fuelChoiceComboBox.valueProperty().bindBidirectional(this.fuelsFXcontroller.getFuelsFXObjectProperty().fuelsFXObjectPropertyProperty());
        this.fuelChoiceComboBox.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().fuelsFXObjectPropertyProperty());
        this.fuelChoiceComboBox.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().fuelsFXObjectPropertyProperty());
        this.fuelChoiceComboBox.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().fuelsFXObjectPropertyProperty());

        this.capacityChoiceComboBox.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().capacitiesFXObjectPropertyProperty());
        this.capacityChoiceComboBox.valueProperty().bindBidirectional(this.capacitiesFXcontroller.getCapacitiesFXObjectProperty().capacitiesFXObjectPropertyProperty());
        this.capacityChoiceComboBox.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().capacitiesFXObjectPropertyProperty());
        this.capacityChoiceComboBox.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().capacitiesFXObjectPropertyProperty());

        this.versionChoiceComboBox.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().versionsFXObjectPropertyProperty());
        this.versionChoiceComboBox.valueProperty().bindBidirectional(this.versionsFXcontroller.getVersionsObjectProperty().versionsFXObjectPropertyProperty());
        this.versionChoiceComboBox.valueProperty().bindBidirectional(this.carsCharacteristicsFXcontroller.getCarsCharacteristicsFXObjectProperty().versionsFXObjectPropertyProperty());
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
