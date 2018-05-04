package pl.CarComp.utils.converters;

import pl.CarComp.database.models.CarBrand;
import pl.CarComp.modelFX.BrandsFX;

public class ConverterBrand {
    public static BrandsFX convertToBrandsFX(CarBrand carBrand){
        BrandsFX brandsFX = new BrandsFX();
        brandsFX.setId(carBrand.getId());
        brandsFX.setBrand(carBrand.getBrand());
        return brandsFX;
    }
    public static CarBrand convertToCarBrand (BrandsFX brandsFX){
        CarBrand carBrand=new CarBrand();
        carBrand.setId(brandsFX.getId());
        carBrand.setBrand(brandsFX.getBrand());
        return carBrand;
    }
}
