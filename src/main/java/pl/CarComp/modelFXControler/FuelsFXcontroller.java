package pl.CarComp.modelFXControler;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.CarBrandDao;
import pl.CarComp.database.dao.CarFuelDao;
import pl.CarComp.database.dao.CarModelDao;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.CarBrand;
import pl.CarComp.database.models.CarFuel;
import pl.CarComp.database.models.CarModel;
import pl.CarComp.modelFX.FuelsFX;
import pl.CarComp.utils.converters.ConverterFuel;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.List;

public class FuelsFXcontroller {
    private ObservableList<FuelsFX> fuelsList = FXCollections.observableArrayList();
    // single item of fuels list
    private ObjectProperty<FuelsFX> fuelsListItem = new SimpleObjectProperty<>();

    private ObjectProperty<FuelsFX> fuelsFXObjectProperty = new SimpleObjectProperty<>(new FuelsFX());

    // method saving to database f.ex. after clicking button
    public void saveFuelInDatabase() throws ApplicationException {
        CarFuel carFuel = ConverterFuel.convertToFuel(this.getFuelsFXObjectProperty());
        CarFuelDao carFuelDao = new CarFuelDao(DBManager.getConnectionSource());

        //initialize foreign dao to find match
        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        CarBrand tempCarBrand = carBrandDao.findById(CarBrand.class, this.getFuelsFXObjectProperty().getBrandsFXObjectProperty().getId());
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        CarModel tempCarModel = carModelDao.findById(CarModel.class, this.getFuelsFXObjectProperty().getModelsFXObjectProperty().getId());

        // set foreign and normal data
        carFuel.setBrand(tempCarBrand);
        carFuel.setModel(tempCarModel);
        carFuel.setFuel(getFuelsFXObjectProperty().getFuel());
        carFuelDao.createOrUpdate(carFuel);
        DBManager.closeConnectionSource();
        initializeFuelsList();
    }

    // initialize list of fuels
    public void initializeFuelsList() throws ApplicationException {
        CarFuelDao carFuelDao = new CarFuelDao(DBManager.getConnectionSource());
        List<CarFuel> listOfFuels = carFuelDao.queryForAll(CarFuel.class);
        fuelsList.clear();
        // loop
        listOfFuels.forEach((c) -> {
            // create new object FuelsFX model
            FuelsFX fuelsFX = new FuelsFX();
            fuelsFX.setId(c.getId());
            fuelsFX.setFuel(c.getFuel());
            this.fuelsList.add(fuelsFX);
        });
        DBManager.closeConnectionSource();

    }

    public ObservableList<FuelsFX> getFuelsList() {
        return fuelsList;
    }

    public void setFuelsList(ObservableList<FuelsFX> fuelsList) {
        this.fuelsList = fuelsList;
    }

    public FuelsFX getFuelsListItem() {
        return fuelsListItem.get();
    }

    public ObjectProperty<FuelsFX> fuelsListItemProperty() {
        return fuelsListItem;
    }

    public void setFuelsListItem(FuelsFX fuelsListItem) {
        this.fuelsListItem.set(fuelsListItem);
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
}
