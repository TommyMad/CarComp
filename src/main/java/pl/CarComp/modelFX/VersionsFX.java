package pl.CarComp.modelFX;

import javafx.beans.property.*;

//same names as in database models
//Properties for using them in modelFX controller

public class VersionsFX {
    private ObjectProperty<BrandsFX> brandsFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<ModelsFX> modelsFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<FuelsFX> fuelsFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CapacitiesFX> capacitiesFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<VersionsFX>versionsFXObjectProperty=new SimpleObjectProperty<>();
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty version = new SimpleStringProperty();

    public StringProperty getVersionProperty() {
        return version;
    }

    public String getVersion() {
        return version.get();
    }

    public VersionsFX getVersionsFXObjectProperty() {
        return versionsFXObjectProperty.get();
    }

    public ObjectProperty<VersionsFX> versionsFXObjectPropertyProperty() {
        return versionsFXObjectProperty;
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public IntegerProperty getIdProperty() {
        return id;
    }
    public int getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
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

    public ModelsFX getModelsFXObjectProperty() {
        return modelsFXObjectProperty.get();
    }

    public ObjectProperty<ModelsFX> modelsFXObjectPropertyProperty() {
        return modelsFXObjectProperty;
    }

    public void setModelsFXObjectProperty(ModelsFX modelsFXObjectProperty) {
        this.modelsFXObjectProperty.set(modelsFXObjectProperty);
    }

    public FuelsFX getFuelsFXObjectProperty() {
        return fuelsFXObjectProperty.get();
    }

    public ObjectProperty<FuelsFX> fuelsFXObjectPropertyProperty() {
        return fuelsFXObjectProperty;
    }

    public void setFuelsFXObjectProperty(FuelsFX fuelsFXObjectProperty) {
        this.fuelsFXObjectProperty.set(fuelsFXObjectProperty);
    }

    public CapacitiesFX getCapacitiesFXObjectProperty() {
        return capacitiesFXObjectProperty.get();
    }

    public ObjectProperty<CapacitiesFX> capacitiesFXObjectPropertyProperty() {
        return capacitiesFXObjectProperty;
    }

    public void setCapacitiesFXObjectProperty(CapacitiesFX capacitiesFXObjectProperty) {
        this.capacitiesFXObjectProperty.set(capacitiesFXObjectProperty);
    }

    @Override
    public String toString() {
        return version.getValue();
    }
}
