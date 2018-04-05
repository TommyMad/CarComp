package pl.CarComp.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "version_table")
public class CarVersion implements BaseModel {

    public static final String FOREIGN_BRAND_ID = "FOREIGN_BRAND_ID";
    public static final String FOREIGN_MODEL_ID = "FOREIGN_MODEL_ID";
    public static final String FOREIGN_CAPACITY_ID = "FOREIGN_CAPACITY_ID";
    public static final String FOREIGN_FUEL_ID = "FOREIGN_FUEL_ID";

    public CarVersion() {

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = FOREIGN_BRAND_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarBrand brand;

    @DatabaseField(columnName = FOREIGN_MODEL_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarModel model;

    @DatabaseField(columnName = FOREIGN_CAPACITY_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarCapacity capacity;
    @DatabaseField(columnName = FOREIGN_FUEL_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarFuel fuel;

    @DatabaseField(columnName = "VERSION", canBeNull = false,unique = true)
    private String version;

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

    public CarCapacity getCapacity() {
        return capacity;
    }

    public void setCapacity(CarCapacity capacity) {
        this.capacity = capacity;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public CarFuel getFuel() {
        return fuel;
    }
    public void setFuel(CarFuel fuel) {
        this.fuel = fuel;
    }

}
