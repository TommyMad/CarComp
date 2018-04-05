package pl.CarComp.utils.converters;

import pl.CarComp.database.models.CarModel;
import pl.CarComp.modelFX.ModelsFX;

public class ConverterModels {
    public static CarModel convertToModel(ModelsFX modelsfx){
        CarModel carModel=new CarModel();
        carModel.setId(modelsfx.getId());
        carModel.setModel(modelsfx.getModel());
        return carModel;
    }
public static ModelsFX convertToModelFX(CarModel carModel){
        ModelsFX modelsFX=new ModelsFX();
        modelsFX.setId(carModel.getId());
        modelsFX.setModel(carModel.getModel());
        modelsFX.setBrandsFXObjectProperty(ConverterBrand.convertToBrandsFX(carModel.getBrand()));
        return modelsFX;
}
}
