package pl.CarComp.modelFX;

import javafx.beans.property.*;

//same names as in database models
//Properties for using them in modelFX controller

public class ModelsFX {
    private ObjectProperty<ModelsFX>modelsFXObjectProperty=new SimpleObjectProperty<>();
    private ObjectProperty<BrandsFX>brandsFXObjectProperty=new SimpleObjectProperty<>();
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty model = new SimpleStringProperty();

    public StringProperty getModelPropoerty() {
        return model;
    }
    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty getIdProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return model.getValue();
    }

    public BrandsFX getBrandsFXObjectProperty() {
        return brandsFXObjectProperty.get();
    }

    public ObjectProperty<BrandsFX> brandsFXObjectPropertyProperty() {
        return brandsFXObjectProperty;
    }

    public void setBrandsFXObjectProperty(BrandsFX brandsFXObjectProperty) {
        this.brandsFXObjectProperty.set(brandsFXObjectProperty);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public StringProperty modelProperty() {
        return model;
    }

    public ModelsFX getModelsFXObjectProperty() {
        return modelsFXObjectProperty.get();
    }

    public ObjectProperty<ModelsFX> modelsFXObjectPropertyProperty() {
        return modelsFXObjectProperty;
    }

    public void setModelsFXObjectProperty(ModelsFX modelsFXObjectProperty) {
        this.modelsFXObjectProperty.set(modelsFXObjectProperty);
    }
}
