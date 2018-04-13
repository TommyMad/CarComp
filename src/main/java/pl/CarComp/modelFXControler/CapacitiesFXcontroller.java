package pl.CarComp.modelFXControler;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.CarBrandDao;
import pl.CarComp.database.dao.CarCapacityDao;
import pl.CarComp.database.dao.CarFuelDao;
import pl.CarComp.database.dao.CarModelDao;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.CarBrand;
import pl.CarComp.database.models.CarCapacity;
import pl.CarComp.database.models.CarFuel;
import pl.CarComp.database.models.CarModel;
import pl.CarComp.modelFX.CapacitiesFX;
import pl.CarComp.utils.converters.ConverterCapacity;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.List;

public class CapacitiesFXcontroller {
    private ObservableList<CapacitiesFX> capacityList = FXCollections.observableArrayList();
    private ObjectProperty<CapacitiesFX> capacityListItem = new SimpleObjectProperty<>();
    private ObjectProperty<CapacitiesFX>capacitiesFXObjectProperty=new SimpleObjectProperty<>(new CapacitiesFX());

    // method saving to database f.ex. after clicking button
    public void saveCapacityInDatabase() throws ApplicationException {
        CarCapacity carCapacity = ConverterCapacity.convertToCapacity(this.getCapacitiesFXObjectProperty());
        CarCapacityDao carCapacityDao = new CarCapacityDao(DBManager.getConnectionSource());

        //initialize foreign dao to find match
        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        CarBrand tempCarBrand = carBrandDao.findById(CarBrand.class, this.getCapacitiesFXObjectProperty().getBrandsFXObjectProperty().getId());
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        CarModel tempCarModel = carModelDao.findById(CarModel.class, this.getCapacitiesFXObjectProperty().getModelsFXObjectProperty().getId());
        CarFuelDao carFuelDao=new CarFuelDao(DBManager.getConnectionSource());
        CarFuel tempCarFuel=carFuelDao.findById(CarFuel.class, this.getCapacitiesFXObjectProperty().getFuelsFXObjectProperty().getId());

        // set foreign and normal data
        carCapacity.setBrand(tempCarBrand);
        carCapacity.setModel(tempCarModel);
        carCapacity.setFuel(tempCarFuel);
        carCapacity.setCapacity(getCapacitiesFXObjectProperty().getCapacity());
        carCapacityDao.createOrUpdate(carCapacity);
        DBManager.closeConnectionSource();
        initializeCapacitiesList();

    }

    // initialize list of brands
    public void initializeCapacitiesList() throws ApplicationException {
        CarCapacityDao carCapacityDao = new CarCapacityDao(DBManager.getConnectionSource());
        List<CarCapacity> listOfCapacities = carCapacityDao.queryForAll(CarCapacity.class);
        capacityList.clear();

        listOfCapacities.forEach((c) -> {
            // create new object CapacitiesFX model
            CapacitiesFX capacitiesFX = new CapacitiesFX();
            capacitiesFX.setId(c.getId());
            capacitiesFX.setCapacity(c.getCapacity());
            this.capacityList.add(capacitiesFX);
        });
        DBManager.closeConnectionSource();
    }
    public void initializeFilteredCapacitiesList() throws ApplicationException {
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        CarModel tempCarModel = carModelDao.findById(CarModel.class, this.getCapacitiesFXObjectProperty().getModelsFXObjectProperty().getId());

        CarCapacityDao carCapacityDao = new CarCapacityDao(DBManager.getConnectionSource());
        List<CarCapacity> listOfFilteredCapacities = carCapacityDao.queryForEq(CarCapacity.class,"FOREIGN_MODEL_ID",tempCarModel);
        capacityList.clear();

        listOfFilteredCapacities.forEach((c) -> {
            // create new object CapacitiesFX model
            CapacitiesFX capacitiesFX = ConverterCapacity.convertToCapacitiesFX(c);
            this.capacityList.add(capacitiesFX);
        });
        DBManager.closeConnectionSource();
    }
    public ObservableList<CapacitiesFX> getCapacityList() {
        return capacityList;
    }

    public void setCapacityList(ObservableList<CapacitiesFX> capacityList) {
        this.capacityList = capacityList;
    }

    public CapacitiesFX getCapacityListItem() {
        return capacityListItem.get();
    }

    public ObjectProperty<CapacitiesFX> capacityListItemProperty() {
        return capacityListItem;
    }

    public void setCapacityListItem(CapacitiesFX capacityListItem) {
        this.capacityListItem.set(capacityListItem);
    }

    public CapacitiesFX getCapacitiesFXObjectProperty() {
        return capacitiesFXObjectProperty.get();
    }

    public ObjectProperty<CapacitiesFX> capacitiesFXObjectPropertyProperty() {
        return capacitiesFXObjectProperty;
    }
}



