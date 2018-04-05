package pl.CarComp.modelFXControler;

import com.j256.ormlite.dao.GenericRawResults;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.CarBrandDao;
import pl.CarComp.database.dao.CarModelDao;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.CarBrand;
import pl.CarComp.database.models.CarModel;
import pl.CarComp.modelFX.BrandsFX;
import pl.CarComp.modelFX.ModelsFX;
import pl.CarComp.utils.converters.ConverterBrand;
import pl.CarComp.utils.converters.ConverterModels;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.List;

public class ModelsFXcontroller {
    private ObservableList<ModelsFX> modelsList = FXCollections.observableArrayList();
    // single item of models list
    private ObjectProperty<ModelsFX> modelsListItem = new SimpleObjectProperty<>();

    private ObjectProperty<ModelsFX> modelsFxObjectProperty = new SimpleObjectProperty<>(new ModelsFX());
    private ObjectProperty<BrandsFX> brandsFXObjectProperty= new SimpleObjectProperty<>();
    private ObservableList<BrandsFX> brandsFXObservableList = FXCollections.observableArrayList();

    // method saving to database f.ex. after clicking button
    public void saveModelInDatabase() throws ApplicationException {
        CarModel carModel = ConverterModels.convertToModel(this.getModelsFxObjectProperty());
        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());

        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        CarBrand tempCarBrand = carBrandDao.findById(CarBrand.class, this.getModelsFxObjectProperty().getBrandsFXObjectProperty().getId());
        //car Model from database models add foreign brand-tempCarBrand

        carModel.setBrand(tempCarBrand);
        carModel.setModel(getModelsFxObjectProperty().getModel());
        carModelDao.createOrUpdate(carModel);
        DBManager.closeConnectionSource();
        initializeModelsList();
    }

    // initialize list of models
    public void initializeModelsList() throws ApplicationException {
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
    public void initializeFilteredModelsList() throws ApplicationException {
        CarBrandDao carBrandDao=new CarBrandDao(DBManager.getConnectionSource());
        CarBrand tempCarBrand = carBrandDao.findById(CarBrand.class, this.getModelsFxObjectProperty().getBrandsFXObjectProperty().getId());
        System.out.println("id brand "+this.getModelsFxObjectProperty().getBrandsFXObjectProperty().getId());

        CarModelDao carModelDao = new CarModelDao(DBManager.getConnectionSource());
        List<CarModel> listOfFilteredModels = carModelDao.queryForMatching(CarModel.class,tempCarBrand);
        modelsList.clear();
        // loop
        listOfFilteredModels.forEach((c) -> {
            // create new object ModelsFX model
            ModelsFX modelsFX = ConverterModels.convertToModelFX(c);
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

    public ObservableList<BrandsFX> getBrandsFXObservableList() {
        return brandsFXObservableList;
    }

    public void setBrandsFXObservableList(ObservableList<BrandsFX> brandsFXObservableList) {
        this.brandsFXObservableList = brandsFXObservableList;
    }

    public ModelsFX getModelsFxObjectProperty() {
        return modelsFxObjectProperty.get();
    }

    public ObjectProperty<ModelsFX> modelsFxObjectPropertyProperty() {
        return modelsFxObjectProperty;
    }

    public void setModelsFxObjectProperty(ModelsFX modelsFxObjectProperty) {
        this.modelsFxObjectProperty.set(modelsFxObjectProperty);
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
}
