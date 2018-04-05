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
}
