package pl.CarComp.modelFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//same names as in database models
//Properties for using them in modelFX controller

public class VersionsFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty version = new SimpleStringProperty();

    public StringProperty getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return version.getValue();
    }
}
