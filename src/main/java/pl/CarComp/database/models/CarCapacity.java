package pl.CarComp.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "capacity_table")
public class CarCapacity implements BaseModel {
    public CarCapacity() {

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "BRAND_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private CarBrand brand;

    @DatabaseField(columnName = "MODEL_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private CarModel model;

    @DatabaseField(columnName = "CAPACITY", canBeNull = false,unique = true)
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
}