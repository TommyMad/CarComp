package pl.CarComp.modelFXControler;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.*;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.*;
import pl.CarComp.modelFX.*;
import pl.CarComp.utils.converters.ConverterBrand;
import pl.CarComp.utils.converters.ConverterCarCharacteristics;
import pl.CarComp.utils.converters.ConverterModels;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CarsCharacteristicsFXcontroller {
    private ObjectProperty<CarsCharacteristicsFX> carsCharacteristicsFXObjectProperty = new SimpleObjectProperty<>(new CarsCharacteristicsFX());
    private ObjectProperty<BrandsFX> brandsFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<ModelsFX> modelsFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<FuelsFX> fuelsFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CapacitiesFX> capacitiesFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<VersionsFX> versionsFXObjectProperty = new SimpleObjectProperty<>();

    private ObservableList<BrandsFX> brandsFXObservableList = FXCollections.observableArrayList();
    private ObservableList<ModelsFX> modelsFXObservableList = FXCollections.observableArrayList();
    private ObservableList<FuelsFX> fuelsFXObservableList = FXCollections.observableArrayList();
    private ObservableList<CapacitiesFX> capacitiesFXObservableList = FXCollections.observableArrayList();
    private ObservableList<VersionsFX> versionsFXObservableList = FXCollections.observableArrayList();
    private ObservableList<CarsCharacteristicsFX> carsCharacteristicsFXObservableList = FXCollections.observableArrayList();


    public void initCarsCharacteristicFXcontroller() throws ApplicationException {
        initBrandsList();
        initModelsList();
        initFuelsList();
        initCapacitiesList();
        initVersionsList();
    }

    private void initBrandsList() throws ApplicationException {
        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        List<CarBrand> listOfBrands = carBrandDao.queryForAll(CarBrand.class);
        brandsFXObservableList.clear();
        listOfBrands.forEach(c -> {
            BrandsFX brandsFX = ConverterBrand.convertToBrandsFX(c);
            this.brandsFXObservableList.add(brandsFX);
        });
    }

    private void initModelsList() throws ApplicationException {
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        List<CarModel> listOfmodels = carModelDao.queryForAll(CarModel.class);
        modelsFXObservableList.clear();
        listOfmodels.forEach(c -> {
            ModelsFX modelsFX = ConverterModels.convertToModelFX(c);
            this.modelsFXObservableList.add(modelsFX);
        });
    }

    private void initVersionsList() throws ApplicationException {
        CarVersionDao carVersionDao = new CarVersionDao(DBManager.getConnectionSource());
        List<CarVersion> listOfVersions = carVersionDao.queryForAll(CarVersion.class);
        versionsFXObservableList.clear();
        // loop
        listOfVersions.forEach((c) -> {
            // create new object VersionsFX model
            VersionsFX versionsFX = new VersionsFX();
            versionsFX.setId(c.getId());
            versionsFX.setVersion(c.getVersion());
            this.versionsFXObservableList.add(versionsFX);
        });
        DBManager.closeConnectionSource();
    }

    private void initCapacitiesList() throws ApplicationException {
        CarCapacityDao carCapacityDao = new CarCapacityDao(DBManager.getConnectionSource());
        List<CarCapacity> listOfCapacities = carCapacityDao.queryForAll(CarCapacity.class);
        capacitiesFXObservableList.clear();

        listOfCapacities.forEach((c) -> {
            // create new object CapacitiesFX model
            CapacitiesFX capacitiesFX = new CapacitiesFX();
            capacitiesFX.setId(c.getId());
            capacitiesFX.setCapacity(c.getCapacity());
            this.capacitiesFXObservableList.add(capacitiesFX);
        });
        DBManager.closeConnectionSource();
    }

    private void initFuelsList() throws ApplicationException {
        CarFuelDao carFuelDao = new CarFuelDao(DBManager.getConnectionSource());
        List<CarFuel> listOfFuels = carFuelDao.queryForAll(CarFuel.class);
        fuelsFXObservableList.clear();
        // loop
        listOfFuels.forEach((c) -> {
            // create new object FuelsFX model
            FuelsFX fuelsFX = new FuelsFX();
            fuelsFX.setId(c.getId());
            fuelsFX.setFuel(c.getFuel());
            this.fuelsFXObservableList.add(fuelsFX);
        });
        DBManager.closeConnectionSource();
    }

    public void saveCarsCharacteristicsinDatabase() throws ApplicationException {
        CarsCharacteristic carsCharacteristic = ConverterCarCharacteristics.convertToCarsCharacteristic(this.getCarsCharacteristicsFXObjectProperty());
        CarsCharacteristicsDao carsCharacteristicsDao = new CarsCharacteristicsDao(DBManager.getConnectionSource());

        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        CarBrand tempCarBrand = carBrandDao.findById(CarBrand.class, this.getCarsCharacteristicsFXObjectProperty().getBrandsFXObjectProperty().getId());
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        CarModel tempCarModel = carModelDao.findById(CarModel.class, this.getCarsCharacteristicsFXObjectProperty().getModelsFXObjectProperty().getId());
        CarFuelDao carFuelDao = new CarFuelDao(DBManager.getConnectionSource());
        CarFuel tempCarFuel = carFuelDao.findById(CarFuel.class, this.getCarsCharacteristicsFXObjectProperty().getFuelsFXObjectProperty().getId());
        CarCapacityDao carCapacityDao = new CarCapacityDao(DBManager.getConnectionSource());
        CarCapacity tempCarCapacity = carCapacityDao.findById(CarCapacity.class, this.getCarsCharacteristicsFXObjectProperty().getCapacitiesFXObjectProperty().getId());
        CarVersionDao carVersionDao = new CarVersionDao(DBManager.getConnectionSource());
        CarVersion tempCarVersion = carVersionDao.findById(CarVersion.class, this.getCarsCharacteristicsFXObjectProperty().getVersionsFXObjectProperty().getId());

        carsCharacteristic.setBrand(tempCarBrand);
        carsCharacteristic.setModel(tempCarModel);
        carsCharacteristic.setFuel(tempCarFuel);
        carsCharacteristic.setCapacity(tempCarCapacity);
        carsCharacteristic.setVersion(tempCarVersion);

        carsCharacteristicsDao.createOrUpdate(carsCharacteristic);
        carsCharacteristicsDao.refresh(carsCharacteristic);

        DBManager.closeConnectionSource();
        //initializeCarsCharacteristicsList();
    }

    public void deleteCar() throws ApplicationException {
        CarsCharacteristicsDao carsCharacteristicsDao = new CarsCharacteristicsDao(DBManager.getConnectionSource());
        carsCharacteristicsDao.deleteById(CarsCharacteristic.class, this.getCarsCharacteristicsFXObjectProperty().getId());
        System.out.println("id "+this.getCarsCharacteristicsFXObjectProperty().getId());

        //CarVersionDao carVersionDao=new CarVersionDao(DBManager.getConnectionSource());
        //carVersionDao=carVersionDao.deleteById(CarVersion.class,);
        DBManager.closeConnectionSource();
    }

    public void initializeCarsCharacteristicsList() {
        CarsCharacteristicsDao carsCharakcteristicsDao = new CarsCharacteristicsDao(DBManager.getConnectionSource());
    }

    public void initFilteredListCarsCharacteristic() throws ApplicationException {
        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        CarBrand tempCarBrand = carBrandDao.findById(CarBrand.class, this.getCarsCharacteristicsFXObjectProperty().getBrandsFXObjectProperty().getId());
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        CarModel tempCarModel = carModelDao.findById(CarModel.class, this.getCarsCharacteristicsFXObjectProperty().getModelsFXObjectProperty().getId());
        CarVersionDao carVersionDao = new CarVersionDao(DBManager.getConnectionSource());
        CarVersion tempCarVersion = carVersionDao.findById(CarVersion.class, this.getCarsCharacteristicsFXObjectProperty().getVersionsFXObjectProperty().getId());

        CarsCharacteristicsDao carsCharacteristicsDao = new CarsCharacteristicsDao(DBManager.getConnectionSource());
        List<CarsCharacteristic> carsCharacteristicFilteredList = carsCharacteristicsDao.queryRaw(CarsCharacteristic.class, tempCarBrand, tempCarModel, tempCarVersion);
        carsCharacteristicsFXObservableList.clear();
        carsCharacteristicFilteredList.forEach((c)->{
            CarsCharacteristicsFX carsCharacteristicsFX=ConverterCarCharacteristics.convertToCarsCharacteristicFX(c);
            this.carsCharacteristicsFXObservableList.add(carsCharacteristicsFX);
        });
        DBManager.closeConnectionSource();


    }

    public CarsCharacteristicsFX getCarsCharacteristicsFXObjectProperty() {
        return carsCharacteristicsFXObjectProperty.get();
    }

    public ObjectProperty<CarsCharacteristicsFX> carsCharacteristicsFXObjectPropertyProperty() {
        return carsCharacteristicsFXObjectProperty;
    }

    public void setCarsCharacteristicsFXObjectProperty(CarsCharacteristicsFX carsCharacteristicsFXObjectProperty) {
        this.carsCharacteristicsFXObjectProperty.set(carsCharacteristicsFXObjectProperty);
    }

    public ObservableList<BrandsFX> getBrandsFXObservableList() {
        return brandsFXObservableList;
    }

    public void setBrandsFXObservableList(ObservableList<BrandsFX> brandsFXObservableList) {
        this.brandsFXObservableList = brandsFXObservableList;
    }

    public ObservableList<ModelsFX> getModelsFXObservableList() {
        return modelsFXObservableList;
    }

    public void setModelsFXObservableList(ObservableList<ModelsFX> modelsFXObservableList) {
        this.modelsFXObservableList = modelsFXObservableList;
    }

    public ObservableList<FuelsFX> getFuelsFXObservableList() {
        return fuelsFXObservableList;
    }

    public void setFuelsFXObservableList(ObservableList<FuelsFX> fuelsFXObservableList) {
        this.fuelsFXObservableList = fuelsFXObservableList;
    }

    public ObservableList<CapacitiesFX> getCapacitiesFXObservableList() {
        return capacitiesFXObservableList;
    }

    public void setCapacitiesFXObservableList(ObservableList<CapacitiesFX> capacitiesFXObservableList) {
        this.capacitiesFXObservableList = capacitiesFXObservableList;
    }

    public ObservableList<VersionsFX> getVersionsFXObservableList() {
        return versionsFXObservableList;
    }

    public void setVersionsFXObservableList(ObservableList<VersionsFX> versionsFXObservableList) {
        this.versionsFXObservableList = versionsFXObservableList;
    }

    public BrandsFX getBrandsFXObjectProperty() {
        return brandsFXObjectProperty.get();
    }

    public ObjectProperty<BrandsFX> brandsFXObjectPropertyProperty() {
        return brandsFXObjectProperty;
    }

    public void setBrandsFXObjectProperty(BrandsFX brandsFXObjectProperty) {
        this.brandsFXObjectProperty.set(brandsFXObjectProperty);
    }

    public ModelsFX getModelsFXObjectProperty() {
        return modelsFXObjectProperty.get();
    }

    public ObjectProperty<ModelsFX> modelsFXObjectPropertyProperty() {
        return modelsFXObjectProperty;
    }

    public void setModelsFXObjectProperty(ModelsFX modelsFXObjectProperty) {
        this.modelsFXObjectProperty.set(modelsFXObjectProperty);
    }

    public FuelsFX getFuelsFXObjectProperty() {
        return fuelsFXObjectProperty.get();
    }

    public ObjectProperty<FuelsFX> fuelsFXObjectPropertyProperty() {
        return fuelsFXObjectProperty;
    }

    public void setFuelsFXObjectProperty(FuelsFX fuelsFXObjectProperty) {
        this.fuelsFXObjectProperty.set(fuelsFXObjectProperty);
    }

    public CapacitiesFX getCapacitiesFXObjectProperty() {
        return capacitiesFXObjectProperty.get();
    }

    public ObjectProperty<CapacitiesFX> capacitiesFXObjectPropertyProperty() {
        return capacitiesFXObjectProperty;
    }

    public void setCapacitiesFXObjectProperty(CapacitiesFX capacitiesFXObjectProperty) {
        this.capacitiesFXObjectProperty.set(capacitiesFXObjectProperty);
    }

    public VersionsFX getVersionsFXObjectProperty() {
        return versionsFXObjectProperty.get();
    }

    public ObjectProperty<VersionsFX> versionsFXObjectPropertyProperty() {
        return versionsFXObjectProperty;
    }

    public void setVersionsFXObjectProperty(VersionsFX versionsFXObjectProperty) {
        this.versionsFXObjectProperty.set(versionsFXObjectProperty);
    }

    public ObservableList<CarsCharacteristicsFX> getCarsCharacteristicsFXObservableList() {
        return carsCharacteristicsFXObservableList;
    }

    public void setCarsCharacteristicsFXObservableList(ObservableList<CarsCharacteristicsFX> carsCharacteristicsFXObservableList) {
        this.carsCharacteristicsFXObservableList = carsCharacteristicsFXObservableList;
    }
}
