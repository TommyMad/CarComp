package pl.CarComp.modelFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//same names as in database models
//Properties for using them in modelFX controller

public class FuelsFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty fuel = new SimpleStringProperty();

    public IntegerProperty getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public StringProperty getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel.set(fuel);
    }

    @Override
    public String toString() {
        return fuel.getValue();
    }
}
