package pl.CarComp.utils.converters;

import pl.CarComp.database.models.CarVersion;
import pl.CarComp.modelFX.VersionsFX;

public class ConverterVersion {
    public static CarVersion convertToVersion(VersionsFX versionsFX){
        CarVersion carVersion=new CarVersion();
        carVersion.setId(versionsFX.getId());
        carVersion.setVersion(versionsFX.getVersion());
        return carVersion;
    }
    public static VersionsFX convertToversionsFX(CarVersion carVersion){
        VersionsFX versionsFX=new VersionsFX();
        versionsFX.setId(carVersion.getId());
        versionsFX.setVersion(carVersion.getVersion());
        versionsFX.setModelsFXObjectProperty(ConverterModels.convertToModelFX(carVersion.getModel()));
        return versionsFX;
    }
}
