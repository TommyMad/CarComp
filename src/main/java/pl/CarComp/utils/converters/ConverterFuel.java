package pl.CarComp.utils.converters;

import pl.CarComp.database.models.CarFuel;
import pl.CarComp.modelFX.FuelsFX;

public class ConverterFuel {
    public static CarFuel convertToFuel(FuelsFX fuelsFX){
        CarFuel carFuel=new CarFuel();
        carFuel.setId(fuelsFX.getId());
        carFuel.setFuel(fuelsFX.getFuel());
        return carFuel;
    }
    public static FuelsFX convertToFuelFX(CarFuel carFuel){
        FuelsFX fuelsFX=new FuelsFX();
        fuelsFX.setId(carFuel.getId());
        fuelsFX.setFuel(carFuel.getFuel());
        fuelsFX.setModelsFXObjectProperty(ConverterModels.convertToModelFX(carFuel.getModel()));
        return fuelsFX;
    }
}
