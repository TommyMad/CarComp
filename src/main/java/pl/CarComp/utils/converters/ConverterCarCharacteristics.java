package pl.CarComp.utils.converters;

import pl.CarComp.database.models.CarsCharacteristic;
import pl.CarComp.modelFX.CarsCharacteristicsFX;
import pl.CarComp.utils.DateUtils;


public class ConverterCarCharacteristics {

    public static CarsCharacteristic convertToCarsCharacteristic(CarsCharacteristicsFX carsCharacteristicsFX) {
        CarsCharacteristic carsCharacteristic = new CarsCharacteristic();

        carsCharacteristic.setId(carsCharacteristicsFX.getId());
        carsCharacteristic.setPrice(carsCharacteristicsFX.getPrice());
        carsCharacteristic.setPriceDate(DateUtils.convertToDate(carsCharacteristicsFX.getPriceDate()));
        carsCharacteristic.setTransmission(carsCharacteristicsFX.getTransmission());
        carsCharacteristic.setEngCapacity(carsCharacteristicsFX.getEngCapacity());
        carsCharacteristic.setEngType(carsCharacteristicsFX.getEngType());
        carsCharacteristic.setCylinders(carsCharacteristicsFX.getCylinders());
        carsCharacteristic.setEngType(carsCharacteristicsFX.getEngType());
        carsCharacteristic.setEngPower(carsCharacteristicsFX.getEngPower());
        carsCharacteristic.setTorque(carsCharacteristicsFX.getTorque());
        carsCharacteristic.setFuelConsCity(carsCharacteristicsFX.getFuelConsCity());
        carsCharacteristic.setFuelConsMixed(carsCharacteristicsFX.getFuelConsMixed());
        carsCharacteristic.setFuelConsRoute(carsCharacteristicsFX.getFuelConsRoute());
        carsCharacteristic.setTankCapacity(carsCharacteristicsFX.getTankCapacity());
        carsCharacteristic.setAcceleration(carsCharacteristicsFX.getAcceleration());
        carsCharacteristic.setTopSpeed(carsCharacteristicsFX.getTopSpeed());
        carsCharacteristic.setLenght(carsCharacteristicsFX.getLenght());
        carsCharacteristic.setWidth(carsCharacteristicsFX.getWidth());
        carsCharacteristic.setHeight(carsCharacteristicsFX.getHeight());
        carsCharacteristic.setWheeelbase(carsCharacteristicsFX.getWheeelbase());
        carsCharacteristic.setTrunk(carsCharacteristicsFX.getTrunk());
        carsCharacteristic.setDoors(carsCharacteristicsFX.getDoors());
        carsCharacteristic.setWeight(carsCharacteristicsFX.getWeight());
        carsCharacteristic.setGearboxSize(carsCharacteristicsFX.getGearboxSize());
        return carsCharacteristic;
    }

    public static CarsCharacteristicsFX convertToCarsCharacteristicFX(CarsCharacteristic carsCharacteristic) {
        CarsCharacteristicsFX carsCharacteristicsFX = new CarsCharacteristicsFX();
        carsCharacteristicsFX.setId(carsCharacteristic.getId());
        carsCharacteristicsFX.setPrice(carsCharacteristic.getPrice());
        carsCharacteristicsFX.setPriceDate(DateUtils.convertToLocalDate(carsCharacteristic.getPriceDate()));
        carsCharacteristicsFX.setTransmission(carsCharacteristic.getTransmission());
        carsCharacteristicsFX.setEngCapacity(carsCharacteristic.getEngCapacity());
        carsCharacteristicsFX.setEngType(carsCharacteristic.getEngType());
        carsCharacteristicsFX.setCylinders(carsCharacteristic.getCylinders());
        carsCharacteristicsFX.setEngType(carsCharacteristic.getEngType());
        carsCharacteristicsFX.setEngPower(carsCharacteristic.getEngPower());
        carsCharacteristicsFX.setTorque(carsCharacteristic.getTorque());
        carsCharacteristicsFX.setFuelConsCity(carsCharacteristic.getFuelConsCity());
        carsCharacteristicsFX.setFuelConsMixed(carsCharacteristic.getFuelConsMixed());
        carsCharacteristicsFX.setFuelConsRoute(carsCharacteristic.getFuelConsRoute());
        carsCharacteristicsFX.setTankCapacity(carsCharacteristic.getTankCapacity());
        carsCharacteristicsFX.setAcceleration(carsCharacteristic.getAcceleration());
        carsCharacteristicsFX.setTopSpeed(carsCharacteristic.getTopSpeed());
        carsCharacteristicsFX.setLenght(carsCharacteristic.getLenght());
        carsCharacteristicsFX.setWidth(carsCharacteristic.getWidth());
        carsCharacteristicsFX.setHeight(carsCharacteristic.getHeight());
        carsCharacteristicsFX.setWheeelbase(carsCharacteristic.getWheeelbase());
        carsCharacteristicsFX.setTrunk(carsCharacteristic.getTrunk());
        carsCharacteristicsFX.setDoors(carsCharacteristic.getDoors());
        carsCharacteristicsFX.setWeight(carsCharacteristic.getWeight());
        carsCharacteristicsFX.setGearboxSize(carsCharacteristic.getGearboxSize());

        carsCharacteristicsFX.setBrandsFXObjectProperty(ConverterBrand.convertToBrandsFX(carsCharacteristic.getBrand()));
        carsCharacteristicsFX.setModelsFXObjectProperty(ConverterModels.convertToModelFX(carsCharacteristic.getModel()));
        carsCharacteristicsFX.setFuelsFXObjectProperty(ConverterFuel.convertToFuelFX(carsCharacteristic.getFuel()));
        carsCharacteristicsFX.setCapacitiesFXObjectProperty(ConverterCapacity.convertToCapacitiesFX(carsCharacteristic.getCapacity()));
        carsCharacteristicsFX.setVersionsFXObjectProperty(ConverterVersion.convertToversionsFX(carsCharacteristic.getVersion()));
        return carsCharacteristicsFX;
    }

}
