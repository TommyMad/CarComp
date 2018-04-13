package pl.CarComp.modelFX;

import javafx.beans.property.*;

import java.time.LocalDate;

public class CarsCharacteristicsFX {
    private ObjectProperty<BrandsFX> brandsFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<ModelsFX> modelsFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<FuelsFX> fuelsFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CapacitiesFX> capacitiesFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<VersionsFX> versionsFXObjectProperty = new SimpleObjectProperty<>();
    private  ObjectProperty<CarsCharacteristicsFX> carsCharacteristicsFXObjectProperty=new SimpleObjectProperty<>();
    private IntegerProperty id = new SimpleIntegerProperty();

    private StringProperty price = new SimpleStringProperty();
    private ObjectProperty<LocalDate> priceDate = new SimpleObjectProperty();
    private StringProperty transmission = new SimpleStringProperty();
    private StringProperty engCapacity = new SimpleStringProperty();
    private StringProperty engType = new SimpleStringProperty();
    private StringProperty cylinders = new SimpleStringProperty();
    private StringProperty engPower = new SimpleStringProperty();
    private StringProperty torque = new SimpleStringProperty();
    private StringProperty fuelConsCity = new SimpleStringProperty();
    private StringProperty fuelConsRoute = new SimpleStringProperty();
    private StringProperty fuelConsMixed = new SimpleStringProperty();
    private StringProperty tankCapacity = new SimpleStringProperty();
    private StringProperty acceleration = new SimpleStringProperty();
    private StringProperty lenght = new SimpleStringProperty();
    private StringProperty width = new SimpleStringProperty();
    private StringProperty height = new SimpleStringProperty();
    private StringProperty wheeelbase = new SimpleStringProperty();
    private StringProperty trunk = new SimpleStringProperty();
    private StringProperty doors = new SimpleStringProperty();
    private StringProperty weight = new SimpleStringProperty();
    private StringProperty topSpeed = new SimpleStringProperty();
    private StringProperty gearboxSize = new SimpleStringProperty();




    public BrandsFX getBrandsFXObjectProperty() {
        return brandsFXObjectProperty.get();
    }

    public String getGearboxSize() {
        return gearboxSize.get();
    }

    public StringProperty gearboxSizeProperty() {
        return gearboxSize;
    }

    public void setGearboxSize(String gearboxSize) {
        this.gearboxSize.set(gearboxSize);
    }

    public ObjectProperty<BrandsFX> brandsFXObjectPropertyProperty() {
        return brandsFXObjectProperty;
    }

    public void setBrandsFXObjectProperty(BrandsFX brandsFXObjectProperty) {
        this.brandsFXObjectProperty.set(brandsFXObjectProperty);
    }

    public CarsCharacteristicsFX getCarsCharacteristicsFXObjectProperty() {
        return carsCharacteristicsFXObjectProperty.get();
    }

    public ObjectProperty<CarsCharacteristicsFX> carsCharacteristicsFXObjectPropertyProperty() {
        return carsCharacteristicsFXObjectProperty;
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

    public VersionsFX getVersionsFXObjectProperty() {
        return versionsFXObjectProperty.get();
    }

    public ObjectProperty<VersionsFX> versionsFXObjectPropertyProperty() {
        return versionsFXObjectProperty;
    }

    public void setVersionsFXObjectProperty(VersionsFX versionsFXObjectProperty) {
        this.versionsFXObjectProperty.set(versionsFXObjectProperty);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public LocalDate getPriceDate() {
        return priceDate.get();
    }

    public ObjectProperty<LocalDate> priceDateProperty() {
        return priceDate;
    }

    public void setPriceDate(LocalDate priceDate) {
        this.priceDate.set(priceDate);
    }

    public String getTransmission() {
        return transmission.get();
    }

    public StringProperty transmissionProperty() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission.set(transmission);
    }

    public String getEngCapacity() {
        return engCapacity.get();
    }

    public StringProperty engCapacityProperty() {
        return engCapacity;
    }

    public void setEngCapacity(String engCapacity) {
        this.engCapacity.set(engCapacity);
    }

    public String getEngType() {
        return engType.get();
    }

    public StringProperty engTypeProperty() {
        return engType;
    }

    public void setEngType(String engType) {
        this.engType.set(engType);
    }

    public String getCylinders() {
        return cylinders.get();
    }

    public StringProperty cylindersProperty() {
        return cylinders;
    }

    public void setCylinders(String cylinders) {
        this.cylinders.set(cylinders);
    }

    public String getEngPower() {
        return engPower.get();
    }

    public StringProperty engPowerProperty() {
        return engPower;
    }

    public void setEngPower(String engPower) {
        this.engPower.set(engPower);
    }

    public String getTorque() {
        return torque.get();
    }

    public StringProperty torqueProperty() {
        return torque;
    }

    public void setTorque(String torque) {
        this.torque.set(torque);
    }

    public String getFuelConsCity() {
        return fuelConsCity.get();
    }

    public StringProperty fuelConsCityProperty() {
        return fuelConsCity;
    }

    public void setFuelConsCity(String fuelConsCity) {
        this.fuelConsCity.set(fuelConsCity);
    }

    public String getFuelConsRoute() {
        return fuelConsRoute.get();
    }

    public StringProperty fuelConsRouteProperty() {
        return fuelConsRoute;
    }

    public void setFuelConsRoute(String fuelConsRoute) {
        this.fuelConsRoute.set(fuelConsRoute);
    }

    public String getFuelConsMixed() {
        return fuelConsMixed.get();
    }

    public StringProperty fuelConsMixedProperty() {
        return fuelConsMixed;
    }

    public void setFuelConsMixed(String fuelConsMixed) {
        this.fuelConsMixed.set(fuelConsMixed);
    }

    public String getTankCapacity() {
        return tankCapacity.get();
    }

    public StringProperty tankCapacityProperty() {
        return tankCapacity;
    }

    public void setTankCapacity(String tankCapacity) {
        this.tankCapacity.set(tankCapacity);
    }

    public String getAcceleration() {
        return acceleration.get();
    }

    public StringProperty accelerationProperty() {
        return acceleration;
    }

    public void setAcceleration(String acceleration) {
        this.acceleration.set(acceleration);
    }

    public String getLenght() {
        return lenght.get();
    }

    public StringProperty lenghtProperty() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght.set(lenght);
    }

    public String getWidth() {
        return width.get();
    }

    public StringProperty widthProperty() {
        return width;
    }

    public void setWidth(String width) {
        this.width.set(width);
    }

    public String getHeight() {
        return height.get();
    }

    public StringProperty heightProperty() {
        return height;
    }

    public void setHeight(String height) {
        this.height.set(height);
    }

    public String getWheeelbase() {
        return wheeelbase.get();
    }

    public StringProperty wheeelbaseProperty() {
        return wheeelbase;
    }

    public void setWheeelbase(String wheeelbase) {
        this.wheeelbase.set(wheeelbase);
    }

    public String getTrunk() {
        return trunk.get();
    }

    public StringProperty trunkProperty() {
        return trunk;
    }

    public void setTrunk(String trunk) {
        this.trunk.set(trunk);
    }

    public String getDoors() {
        return doors.get();
    }

    public StringProperty doorsProperty() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors.set(doors);
    }

    public String getWeight() {
        return weight.get();
    }

    public StringProperty weightProperty() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public String getTopSpeed() {
        return topSpeed.get();
    }

    public StringProperty topSpeedProperty() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed.set(topSpeed);
    }

}
