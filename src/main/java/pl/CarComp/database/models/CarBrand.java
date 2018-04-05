package pl.CarComp.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "brands_table")
public class CarBrand implements BaseModel {
    public CarBrand() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "BRAND", canBeNull = false,unique = true)
    private String brand;

    @ForeignCollectionField()
    private ForeignCollection<CarModel>modelForeignCollection;
    

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

    public ForeignCollection<CarModel> getModelForeignCollection() {
        return modelForeignCollection;
    }

    public void setModelForeignCollection(ForeignCollection<CarModel> modelForeignCollection) {
        this.modelForeignCollection = modelForeignCollection;
    }

}
