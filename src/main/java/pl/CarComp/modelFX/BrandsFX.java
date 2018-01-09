package pl.CarComp.modelFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//same names as in database models
//Properties for using them in modelFX controller

public class BrandsFX {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty brand = new SimpleStringProperty();

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


}
