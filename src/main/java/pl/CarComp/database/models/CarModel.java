package pl.CarComp.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "models_table")
public class CarModel implements BaseModel {
    private static final String FOREIGN_BRAND_ID = "FOREIGN_BRAND_ID";

    public CarModel() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = FOREIGN_BRAND_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true,canBeNull = false)
    private CarBrand brand;

    @DatabaseField(columnName = "MODEL", canBeNull = false,unique = true)
    private String model;
    @ForeignCollectionField()
    private ForeignCollection<CarFuel> fuelForeignCollection;
    @ForeignCollectionField()
    private ForeignCollection<CarCapacity>capacityForeignCollection;

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

    public ForeignCollection<CarFuel> getFuelForeignCollection() {
        return fuelForeignCollection;
    }

    public void setFuelForeignCollection(ForeignCollection<CarFuel> fuelForeignCollection) {
        this.fuelForeignCollection = fuelForeignCollection;
    }

    public ForeignCollection<CarCapacity> getCapacityForeignCollection() {
        return capacityForeignCollection;
    }

    public void setCapacityForeignCollection(ForeignCollection<CarCapacity> capacityForeignCollection) {
        this.capacityForeignCollection = capacityForeignCollection;
    }
}
