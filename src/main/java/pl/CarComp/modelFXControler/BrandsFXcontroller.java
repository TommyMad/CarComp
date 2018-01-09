package pl.CarComp.modelFXControler;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.CarComp.database.dao.CarBrandDao;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.CarBrand;
import pl.CarComp.modelFX.BrandsFX;
import pl.CarComp.utils.converters.ConverterCommon;
import pl.CarComp.utils.exceptions.ApplicationException;

//service for logic between javaFX and database
//can be implements in package of application controllers

public class BrandsFXcontroller {

    // list of brands
    private ObservableList<BrandsFX> brandsList = FXCollections.observableArrayList();
    // single item of brands list
    private ObjectProperty<BrandsFX> brandsListItem = new SimpleObjectProperty<>();

    // method saving to database f.ex. after clicking button
    public void saveBrandInDatabase(String brand) throws ApplicationException {
        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        // car Brand from database models
        CarBrand carBrand = new CarBrand();
        carBrand.setBrand(brand);
        carBrandDao.createOrUpdate(carBrand);
        DBManager.closeConnectionSource();
        initializeBrandsList();
    }

    // initialize list of brands
    public void initializeBrandsList() throws ApplicationException {
        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        List<CarBrand> listOfBrands = carBrandDao.queryForAll(CarBrand.class);
        this.brandsList.clear();
        // loop
        listOfBrands.forEach((c) -> {
            BrandsFX brandsFX = ConverterCommon.convertToBrandsFX(c);
            this.brandsList.add(brandsFX);
        });
        DBManager.closeConnectionSource();
    }

    //updating data in
    public void updateDataInDatabase() throws ApplicationException {
        CarBrandDao carBrandDao = new CarBrandDao(DBManager.getConnectionSource());
        CarBrand tempCarBrand = carBrandDao.findById(CarBrand.class, getBrandsFX().getId());//brands fx was changed
        tempCarBrand.setBrand(getBrandsFX().getBrand());//brands fx getter was changed
        carBrandDao.createOrUpdate(tempCarBrand);
        DBManager.closeConnectionSource();
        initializeBrandsList();
    }

    public ObjectProperty<BrandsFX> getBrandsListItem() {
        return brandsListItem;
    }

    public void setBrandsListItem(ObjectProperty<BrandsFX> brandsListItem) {
        this.brandsListItem = brandsListItem;
    }

    public ObservableList<BrandsFX> getBrandsList() {
        return brandsList;
    }

    public void setBrandsList(ObservableList<BrandsFX> brandsList) {
        this.brandsList = brandsList;
    }

    public BrandsFX getBrandsFX() {
        return brandsListItem.get();
    }
}
