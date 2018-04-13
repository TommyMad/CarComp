package pl.CarComp.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Date;
@DatabaseTable(tableName = "car_Characteristics_table")
public class CarsCharacteristic implements BaseModel{
    public static final String FOREIGN_BRAND_ID = "FOREIGN_BRAND_ID";
    public static final String FOREIGN_MODEL_ID = "FOREIGN_MODEL_ID";
    public static final String FOREIGN_CAPACITY_ID = "FOREIGN_CAPACITY_ID";
    public static final String FOREIGN_FUEL_ID = "FOREIGN_FUEL_ID";
    public static final String FOREIGN_VERSION_ID = "FOREIGN_VERSION_ID";
    @DatabaseField(generatedId = true,unique = true)
    private int id;
    @DatabaseField(columnName = FOREIGN_BRAND_ID,foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private CarBrand brand;
    @DatabaseField(columnName = FOREIGN_MODEL_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarModel model;
    @DatabaseField(columnName = FOREIGN_CAPACITY_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarCapacity capacity;
    @DatabaseField(columnName = FOREIGN_FUEL_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarFuel fuel;
    @DatabaseField(columnName = FOREIGN_VERSION_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarVersion version;

    @DatabaseField(columnName = "PRICE")//add can be null
    private String price;
    @DatabaseField(columnName = "PRICE_DATE")//add can be null
    private Date priceDate;
    @DatabaseField(columnName = "TRANSMISSION")
    private String transmission;
    @DatabaseField(columnName = "ENG_CAPACITY")
    private String engCapacity;
    @DatabaseField(columnName = "ENG_TYPE")
    private String engType;
    @DatabaseField(columnName = "CYLINDERS")
    private String cylinders;
    @DatabaseField(columnName = "ENG_POWER")
    private String engPower;
    @DatabaseField(columnName = "TORQUE")
    private String torque;
    @DatabaseField(columnName = "FUEL_CONS_CITY")
    private String fuelConsCity;
    @DatabaseField(columnName = "FUEL_CONS_ROUTE")
    private String fuelConsRoute;
    @DatabaseField(columnName = "FUEL_CONS_MIX")
    private String fuelConsMixed;
    @DatabaseField(columnName = "TANK_CAPACITY")
    private String tankCapacity;
    @DatabaseField(columnName = "ACCELERATION")
    private String acceleration;
    @DatabaseField(columnName = "TOP_SPEED")
    private String topSpeed;
    @DatabaseField(columnName = "LENGHT")
    private String lenght;
    @DatabaseField(columnName = "WIDTH")
    private String width;
    @DatabaseField(columnName = "HEIGHT")
    private String height;
    @DatabaseField(columnName = "WHEELBASE")
    private String wheeelbase;
    @DatabaseField(columnName = "TRUNK")
    private String trunk;
    @DatabaseField(columnName = "DOORS")
    private String doors;
    @DatabaseField(columnName = "WEIGHT")
    private String weight;
    @DatabaseField(columnName = "GEARBOX_SIZE")
    private String gearboxSize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGearboxSize() {
        return gearboxSize;
    }

    public void setGearboxSize(String gearboxSize) {
        this.gearboxSize = gearboxSize;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public CarCapacity getCapacity() {
        return capacity;
    }

    public void setCapacity(CarCapacity capacity) {
        this.capacity = capacity;
    }

    public CarFuel getFuel() {
        return fuel;
    }

    public void setFuel(CarFuel fuel) {
        this.fuel = fuel;
    }

    public CarVersion getVersion() {
        return version;
    }

    public void setVersion(CarVersion version) {
        this.version = version;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngCapacity() {
        return engCapacity;
    }

    public void setEngCapacity(String engCapacity) {
        this.engCapacity = engCapacity;
    }

    public String getEngType() {
        return engType;
    }

    public void setEngType(String engType) {
        this.engType = engType;
    }

    public String getCylinders() {
        return cylinders;
    }

    public void setCylinders(String cylinders) {
        this.cylinders = cylinders;
    }

    public String getEngPower() {
        return engPower;
    }

    public void setEngPower(String engPower) {
        this.engPower = engPower;
    }

    public String getTorque() {
        return torque;
    }

    public void setTorque(String torque) {
        this.torque = torque;
    }

    public String getFuelConsCity() {
        return fuelConsCity;
    }

    public void setFuelConsCity(String fuelConsCity) {
        this.fuelConsCity = fuelConsCity;
    }

    public String getFuelConsRoute() {
        return fuelConsRoute;
    }

    public void setFuelConsRoute(String fuelConsRoute) {
        this.fuelConsRoute = fuelConsRoute;
    }

    public String getFuelConsMixed() {
        return fuelConsMixed;
    }

    public void setFuelConsMixed(String fuelConsMixed) {
        this.fuelConsMixed = fuelConsMixed;
    }

    public String getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(String tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public String getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(String acceleration) {
        this.acceleration = acceleration;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWheeelbase() {
        return wheeelbase;
    }

    public void setWheeelbase(String wheeelbase) {
        this.wheeelbase = wheeelbase;
    }

    public String getTrunk() {
        return trunk;
    }

    public void setTrunk(String trunk) {
        this.trunk = trunk;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
