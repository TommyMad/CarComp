package pl.CarComp.modelFXControler;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.CarFuelDao;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.CarFuel;
import pl.CarComp.modelFX.FuelsFX;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.List;

public class FuelsFXcontroller {
    private ObservableList<FuelsFX> fuelsList = FXCollections.observableArrayList();
    // single item of fuels list
    private ObjectProperty<FuelsFX> fuelsListItem = new SimpleObjectProperty<>();

    // method saving to database f.ex. after clicking button
    public void saveFuelInDatabase(String fuel) throws ApplicationException {
        CarFuelDao carFuelDao = new CarFuelDao(DBManager.getConnectionSource());
        // car Fuel from database models
        CarFuel carFuel = new CarFuel();
        carFuel.setFuel(fuel);
        carFuelDao.createOrUpdate(carFuel);
        DBManager.closeConnectionSource();
        initializeFuelsList();
        //debug
        System.out.println("Po zapisie w bazie, fuelsfx controller "+carFuel.toString());
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
}
