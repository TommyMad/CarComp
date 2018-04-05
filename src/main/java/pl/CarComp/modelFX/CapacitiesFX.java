package pl.CarComp.modelFX;

import javafx.beans.property.*;

//same names as in database models
//Properties for using them in modelFX controller

public class CapacitiesFX {
    private ObjectProperty<BrandsFX> brandsFXObjectProperty=new SimpleObjectProperty<>();
    private ObjectProperty<ModelsFX>modelsFXObjectProperty=new SimpleObjectProperty<>();
    private ObjectProperty<FuelsFX>fuelsFXObjectProperty=new SimpleObjectProperty<>();
    private ObjectProperty<CapacitiesFX>capacitiesFXObjectProperty=new SimpleObjectProperty<>();

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty capacity = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public CapacitiesFX getCapacitiesFXObjectProperty() {
        return capacitiesFXObjectProperty.get();
    }

    public ObjectProperty<CapacitiesFX> capacitiesFXObjectPropertyProperty() {
        return capacitiesFXObjectProperty;
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

    @Override
    public String toString() {
        return capacity.getValue();
    }
}
