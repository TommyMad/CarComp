package pl.CarComp.modelFX;

import javafx.beans.property.*;

//same names as in database models
//Properties for using them in modelFX controller

public class CapacitiesFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty capacity = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCapacity() {
        return capacity.get();
    }

    public StringProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity.set(capacity);
    }

    @Override
    public String toString() {
        return capacity.getValue();
    }
}
