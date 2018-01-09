package pl.CarComp.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "models_table")
public class CarModel implements BaseModel {
    public CarModel() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "BRAND_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private CarBrand brand;

    @DatabaseField(columnName = "MODEL", canBeNull = false,unique = true)
    private String model;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }
}
