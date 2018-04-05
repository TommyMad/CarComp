package pl.CarComp.modelFX;

import javafx.beans.property.*;

//same names as in database models
//Properties for using them in modelFX controller

public class BrandsFX {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty brand = new SimpleStringProperty();
    private ObjectProperty<BrandsFX> brandsFXObjectProperty = new SimpleObjectProperty<>();

    public String getBrand() {
        return brand.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return brand.getValue();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty brandProperty() {
        return brand;
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
