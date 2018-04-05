package pl.CarComp.modelFX;

import javafx.beans.property.*;

//same names as in database models
//Properties for using them in modelFX controller

public class FuelsFX {
    private ObjectProperty<BrandsFX> brandsFXObjectProperty=new SimpleObjectProperty<>();
    private ObjectProperty<ModelsFX>modelsFXObjectProperty=new SimpleObjectProperty<>();
    private ObjectProperty<CapacitiesFX>capacitiesFXObjectProperty=new SimpleObjectProperty<>();
    private ObjectProperty<FuelsFX>fuelsFXObjectProperty=new SimpleObjectProperty<>();
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty fuel = new SimpleStringProperty();

    public IntegerProperty getIdProperty() {
        return id;
    }

    public FuelsFX getFuelsFXObjectProperty() {
        return fuelsFXObjectProperty.get();
    }

    public ObjectProperty<FuelsFX> fuelsFXObjectPropertyProperty() {
        return fuelsFXObjectProperty;
    }

    public int getId(){
        return id.get();
    }
    public String getFuel() {
        return fuel.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public StringProperty getFuelProperty() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel.set(fuel);
    }

    @Override
    public String toString() {
        return fuel.getValue();
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

    public CapacitiesFX getCapacitiesFXObjectProperty() {
        return capacitiesFXObjectProperty.get();
    }

    public ObjectProperty<CapacitiesFX> capacitiesFXObjectPropertyProperty() {
        return capacitiesFXObjectProperty;
    }

    public void setCapacitiesFXObjectProperty(CapacitiesFX capacitiesFXObjectProperty) {
        this.capacitiesFXObjectProperty.set(capacitiesFXObjectProperty);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public StringProperty fuelProperty() {
        return fuel;
    }

}
