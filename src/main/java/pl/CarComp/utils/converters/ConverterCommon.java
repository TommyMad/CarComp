package pl.CarComp.utils.converters;

import pl.CarComp.database.models.CarBrand;
import pl.CarComp.modelFX.BrandsFX;
import pl.CarComp.modelFXControler.BrandsFXcontroller;

public class ConverterCommon {
    public static BrandsFX convertToBrandsFX(CarBrand carBrand){
        BrandsFX brandsFX = new BrandsFX();
        brandsFX.setId(carBrand.getId());
        brandsFX.setBrand(carBrand.getBrand());
        return brandsFX;
    }
}
