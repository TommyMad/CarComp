package pl.CarComp.modelFXControler;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.*;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.*;
import pl.CarComp.modelFX.VersionsFX;
import pl.CarComp.utils.converters.ConverterVersion;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.List;

public class VersionsFXcontroller {
    

    private ObservableList<VersionsFX> versionsList = FXCollections.observableArrayList();
    // single item of versions list
    private ObjectProperty<VersionsFX> versionsListItem = new SimpleObjectProperty<>();
    private ObjectProperty<VersionsFX> versionsObjectProperty = new SimpleObjectProperty<>(new VersionsFX());

    // method saving to database f.ex. after clicking button
    public void saveVersionInDatabase() throws ApplicationException {
        CarVersion carVersion = ConverterVersion.convertToVersion(this.getVersionsObjectProperty());
        CarVersionDao carVersionDao = new CarVersionDao(DBManager.getConnectionSource());

        //initialize foreign dao to find match
        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        CarBrand tempCarBrand = carBrandDao.findById(CarBrand.class, this.getVersionsObjectProperty().getBrandsFXObjectProperty().getId());
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        CarModel tempCarModel = carModelDao.findById(CarModel.class, this.getVersionsObjectProperty().getModelsFXObjectProperty().getId());
        CarFuelDao carFuelDao=new CarFuelDao(DBManager.getConnectionSource());
        CarFuel tempCarFuel=carFuelDao.findById(CarFuel.class, this.getVersionsObjectProperty().getFuelsFXObjectProperty().getId());
        CarCapacityDao carCapacityDao=new CarCapacityDao(DBManager.getConnectionSource());
        CarCapacity tempCarCapacity=carCapacityDao.findById(CarCapacity.class,this.getVersionsObjectProperty().getCapacitiesFXObjectProperty().getId());
        carVersion.setBrand(tempCarBrand);
        carVersion.setModel(tempCarModel);
        carVersion.setFuel(tempCarFuel);
        carVersion.setCapacity(tempCarCapacity);
        carVersion.setVersion(this.getVersionsObjectProperty().getVersion());
        carVersionDao.createOrUpdate(carVersion);
        DBManager.closeConnectionSource();
        initializeVersionsList();
    }

    // initialize list of versions
    public void initializeVersionsList() throws ApplicationException {
        CarVersionDao carVersionDao = new CarVersionDao(DBManager.getConnectionSource());
        List<CarVersion> listOfVersions = carVersionDao.queryForAll(CarVersion.class);
        versionsList.clear();
        // loop
        listOfVersions.forEach((c) -> {
            // create new object VersionsFX model
            VersionsFX versionsFX = new VersionsFX();
            versionsFX.setId(c.getId());
            versionsFX.setVersion(c.getVersion());
            this.versionsList.add(versionsFX);
        });
        DBManager.closeConnectionSource();
    }
    public void initializeFilteredVersionsList() throws ApplicationException {
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        //CarCapacityDao carCapacityDao=new CarCapacityDao(DBManager.getConnectionSource());
        CarModel tempCarModel = carModelDao.findById(CarModel.class, this.getVersionsObjectProperty().getModelsFXObjectProperty().getId());
        //CarCapacity tempCarCapacity=carCapacityDao.findById(CarCapacity.class,this.getVersionsObjectProperty().getCapacitiesFXObjectProperty().getId());

        CarVersionDao carVersionDao = new CarVersionDao(DBManager.getConnectionSource());
        List<CarVersion> listOfFilteredVersions = carVersionDao.queryForEq(CarVersion.class,"FOREIGN_MODEL_ID",tempCarModel);
        versionsList.clear();
        // loop
        listOfFilteredVersions.forEach((c) -> {
            // create new object VersionsFX model
            VersionsFX versionsFX = ConverterVersion.convertToversionsFX(c);
            this.versionsList.add(versionsFX);
        });
        DBManager.closeConnectionSource();
    }

    public ObservableList<VersionsFX> getVersionsList() {
        return versionsList;
    }

    public VersionsFX getVersionsObjectProperty() {
        return versionsObjectProperty.get();
    }

    public ObjectProperty<VersionsFX> versionsObjectPropertyProperty() {
        return versionsObjectProperty;
    }

    public void setVersionsList(ObservableList<VersionsFX> versionsList) {
        this.versionsList = versionsList;
    }

    public VersionsFX getVersionsListItem() {
        return versionsListItem.get();
    }

    public ObjectProperty<VersionsFX> versionsListItemProperty() {
        return versionsListItem;
    }

    public void setVersionsListItem(VersionsFX versionsListItem) {
        this.versionsListItem.set(versionsListItem);
    }
}
