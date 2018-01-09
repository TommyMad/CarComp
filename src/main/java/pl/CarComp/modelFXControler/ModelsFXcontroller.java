package pl.CarComp.modelFXControler;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.CarModelDao;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.CarModel;
import pl.CarComp.modelFX.ModelsFX;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.List;

public class ModelsFXcontroller {
    private ObservableList<ModelsFX> modelsList = FXCollections.observableArrayList();
    // single item of models list
    private ObjectProperty<ModelsFX> modelsListItem = new SimpleObjectProperty<>();

    // method saving to database f.ex. after clicking button
    public void saveModelInDatabase(String model) throws ApplicationException {
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        // car Model from database models
        CarModel carModel = new CarModel();
        carModel.setModel(model);
        carModelDao.createOrUpdate(carModel);
        DBManager.closeConnectionSource();
        initializeModelsList();
    }


    // initialize list of models
    public void initializeModelsList() throws ApplicationException {
        System.out.println("init listy modeli");
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        List<CarModel> listOfModels = carModelDao.queryForAll(CarModel.class);
        modelsList.clear();
        // loop
        listOfModels.forEach((c) -> {
            // create new object ModelsFX model
            ModelsFX modelsFX = new ModelsFX();
            modelsFX.setId(c.getId());
            modelsFX.setModel(c.getModel());
            this.modelsList.add(modelsFX);
        });
        DBManager.closeConnectionSource();
    }

    public ObservableList<ModelsFX> getModelsList() {
        return modelsList;
    }

    public void setModelsList(ObservableList<ModelsFX> modelsList) {
        this.modelsList = modelsList;
    }

    public ModelsFX getModelsListItem() {
        return modelsListItem.get();
    }

    public ObjectProperty<ModelsFX> modelsListItemProperty() {
        return modelsListItem;
    }

    public void setModelsListItem(ModelsFX modelsListItem) {
        this.modelsListItem.set(modelsListItem);
    }
}
