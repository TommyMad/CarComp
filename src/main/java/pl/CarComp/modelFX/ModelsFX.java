package pl.CarComp.modelFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//same names as in database models
//Properties for using them in modelFX controller

public class ModelsFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty model = new SimpleStringProperty();

    public StringProperty getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return model.getValue();
    }
}
