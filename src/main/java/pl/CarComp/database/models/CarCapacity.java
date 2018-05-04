package pl.CarComp.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "capacity_table")
public class CarCapacity implements BaseModel {

    public static final String FOREIGN_BRAND_ID = "FOREIGN_BRAND_ID";
    public static final String FOREIGN_MODEL_ID = "FOREIGN_MODEL_ID";
    public static final String FOREIGN_FUEL_ID = "FOREIGN_FUEL_ID";

    public CarCapacity() {

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = FOREIGN_BRAND_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarBrand brand;
    @DatabaseField(columnName = FOREIGN_MODEL_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarModel model;
    @DatabaseField(columnName = FOREIGN_FUEL_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarFuel fuel;

    @DatabaseField(columnName = "CAPACITY", canBeNull = false)
    private String capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public CarFuel getFuel() {
        return fuel;
    }

    public void setFuel(CarFuel fuel) {
        this.fuel = fuel;
    }
}