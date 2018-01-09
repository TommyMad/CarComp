package pl.CarComp.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "version_table")
public class CarVersion implements BaseModel {
    public CarVersion() {

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "BRAND_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private CarBrand brand;

    @DatabaseField(columnName = "MODEL_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private CarModel model;

    @DatabaseField(columnName = "CAPACITY_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private CarCapacity capacity;

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
}
