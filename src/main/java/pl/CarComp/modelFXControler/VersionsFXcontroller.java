package pl.CarComp.modelFXControler;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.CarVersionDao;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.CarVersion;
import pl.CarComp.modelFX.VersionsFX;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.List;

public class VersionsFXcontroller {
    

    private ObservableList<VersionsFX> versionsList = FXCollections.observableArrayList();
    // single item of versions list
    private ObjectProperty<VersionsFX> versionsListItem = new SimpleObjectProperty<>();

    // method saving to database f.ex. after clicking button
    public void saveVersionInDatabase(String version) throws ApplicationException {
        CarVersionDao carVersionDao = new CarVersionDao(DBManager.getConnectionSource());
        // car Version from database models
        CarVersion carVersion = new CarVersion();
        carVersion.setVersion(version);
        carVersionDao.createOrUpdate(carVersion);
        DBManager.closeConnectionSource();
        initializeVersionsList();
        //debug
        System.out.println("Po zapisie w bazie, versionsfx controller "+carVersion.toString());
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

    public ObservableList<VersionsFX> getVersionsList() {
        return versionsList;
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
