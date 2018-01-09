package pl.CarComp.utils;
// Test class not used


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CarChooserProperties {
    private BooleanProperty brandLabelProperty=new SimpleBooleanProperty();
    private BooleanProperty modelLabelProperty=new SimpleBooleanProperty();
    private BooleanProperty fuelLabelProperty=new SimpleBooleanProperty();
    private BooleanProperty capacityLabelProperty=new SimpleBooleanProperty();
    private BooleanProperty versionLabelProperty=new SimpleBooleanProperty();
    private BooleanProperty selectCarButtonProperty=new SimpleBooleanProperty(false);
    

    
    public CarChooserProperties() {
	selectCarButtonProperty.bind(brandLabelProperty.not());
	
	
	
	
    }
    
	
    public BooleanProperty getBrandLabelProperty() {
        return brandLabelProperty;
    }

    public void setBrandLabelProperty(BooleanProperty brandLabelProperty) {
        this.brandLabelProperty = brandLabelProperty;
    }

    public BooleanProperty getModelLabelProperty() {
        return modelLabelProperty;
    }

    public void setModelLabelProperty(BooleanProperty modelLabelProperty) {
        this.modelLabelProperty = modelLabelProperty;
    }

    public BooleanProperty getFuelLabelProperty() {
        return fuelLabelProperty;
    }

    public void setFuelLabelProperty(BooleanProperty fuelLabelProperty) {
        this.fuelLabelProperty = fuelLabelProperty;
    }

    public BooleanProperty getCapacityLabelProperty() {
        return capacityLabelProperty;
    }

    public void setCapacityLabelProperty(BooleanProperty capacityLabelProperty) {
        this.capacityLabelProperty = capacityLabelProperty;
    }

    public BooleanProperty getVersionLabelProperty() {
        return versionLabelProperty;
    }

    public void setVersionLabelProperty(BooleanProperty versionLabelProperty) {
        this.versionLabelProperty = versionLabelProperty;
    }

    public BooleanProperty getSelectCarButtonProperty() {
        return selectCarButtonProperty;
    }

    public void setSelectCarButtonProperty(BooleanProperty selectCarButtonProperty) {
        this.selectCarButtonProperty = selectCarButtonProperty;
    }

    

    
}
