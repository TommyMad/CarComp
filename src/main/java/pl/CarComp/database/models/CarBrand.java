package pl.CarComp.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "brands_table")
public class CarBrand implements BaseModel {
    public CarBrand() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "BRAND", canBeNull = false, unique = true)
    private String brand;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


}
