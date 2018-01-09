package pl.CarComp.modelFXControler;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.CarCapacityDao;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.CarCapacity;
import pl.CarComp.modelFX.CapacitiesFX;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.List;

public class CapacitiesFXcontroller {
    private ObservableList<CapacitiesFX> capacityList = FXCollections.observableArrayList();
    private ObjectProperty<CapacitiesFX> capacityListItem = new SimpleObjectProperty<>();

    // method saving to database f.ex. after clicking button
    public void saveCapacityInDatabase(String capacity) throws ApplicationException {
        CarCapacityDao carCapacityDao = new CarCapacityDao(DBManager.getConnectionSource());
        // car Brand from database models
        CarCapacity carCapacity = new CarCapacity();
        carCapacity.setCapacity(capacity);
        carCapacityDao.createOrUpdate(carCapacity);
        DBManager.closeConnectionSource();
        initializeCapacitiesList();
        //debug
        System.out.println("Po zapisie w bazie, capacityFX controller " + carCapacity.toString());
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
}



