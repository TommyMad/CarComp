package pl.CarComp.utils;

import pl.CarComp.database.dao.CarsCharacteristicsDao;
import pl.CarComp.database.dbUtils.DBManager;
import pl.CarComp.database.models.*;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.util.Date;

public class DatabasewithExampleData {
    public static void fillwithExamples() {
        //first car Audi A1, benzyna, 1.0, sportback

        CarBrand carBrand1 = new CarBrand();
        carBrand1.setBrand("Audi");

        CarModel carModel1 = new CarModel();
        carModel1.setBrand(carBrand1);
        carModel1.setModel("A1");

        CarFuel carFuel1 = new CarFuel();
        carFuel1.setBrand(carBrand1);
        carFuel1.setModel(carModel1);
        carFuel1.setFuel("benzyna");

        CarCapacity carCapacity1 = new CarCapacity();
        carCapacity1.setBrand(carBrand1);
        carCapacity1.setModel(carModel1);
        carCapacity1.setFuel(carFuel1);
        carCapacity1.setCapacity("1.0");

        CarVersion carVersion1 = new CarVersion();
        carVersion1.setBrand(carBrand1);
        carVersion1.setModel(carModel1);
        carVersion1.setCapacity(carCapacity1);
        carVersion1.setFuel(carFuel1);
        carVersion1.setVersion("Sportback");

        CarsCharacteristic carsCharacteristic1 = new CarsCharacteristic();
        carsCharacteristic1.setBrand(carBrand1);
        carsCharacteristic1.setModel(carModel1);
        carsCharacteristic1.setFuel(carFuel1);
        carsCharacteristic1.setCapacity(carCapacity1);
        carsCharacteristic1.setVersion(carVersion1);

        carsCharacteristic1.setPrice("80 300");
        carsCharacteristic1.setPriceDate(new Date());
        carsCharacteristic1.setEngCapacity("999");
        carsCharacteristic1.setEngType("turbodoładowany TFSI");
        carsCharacteristic1.setCylinders("3");
        carsCharacteristic1.setTransmission("manualna");
        carsCharacteristic1.setGearboxSize("5");
        carsCharacteristic1.setEngPower("95/5000");
        carsCharacteristic1.setTorque("160/1500-3500");
        carsCharacteristic1.setWeight("1210");
        carsCharacteristic1.setLenght("3 973");
        carsCharacteristic1.setHeight("1 422");
        carsCharacteristic1.setWidth("1 746");
        carsCharacteristic1.setWheeelbase("2 469");
        carsCharacteristic1.setTrunk("270");
        carsCharacteristic1.setDoors("5");
        carsCharacteristic1.setFuelConsCity("5");
        carsCharacteristic1.setFuelConsRoute("3.7");
        carsCharacteristic1.setFuelConsMixed("4.2");
        carsCharacteristic1.setTankCapacity("45");
        carsCharacteristic1.setAcceleration("11.1");
        carsCharacteristic1.setTopSpeed("186");

        //second car Citroen C3, benzyna, 1.2, Live

        CarBrand carBrand2 = new CarBrand();
        carBrand2.setBrand("Citroen");

        CarModel carModel2 = new CarModel();
        carModel2.setBrand(carBrand2);
        carModel2.setModel("C3");

        CarFuel carFuel2 = new CarFuel();
        carFuel2.setBrand(carBrand2);
        carFuel2.setModel(carModel2);
        carFuel2.setFuel(carFuel1.getFuel());

        CarCapacity carCapacity2 = new CarCapacity();
        carCapacity2.setBrand(carBrand2);
        carCapacity2.setModel(carModel2);
        carCapacity2.setFuel(carFuel1);
        carCapacity2.setCapacity("1.2");

        CarVersion carVersion2 = new CarVersion();
        carVersion2.setBrand(carBrand2);
        carVersion2.setModel(carModel2);
        carVersion2.setCapacity(carCapacity2);
        carVersion2.setFuel(carFuel1);
        carVersion2.setVersion("Live");

        CarsCharacteristic carsCharacteristic2 = new CarsCharacteristic();
        carsCharacteristic2.setBrand(carBrand2);
        carsCharacteristic2.setModel(carModel2);
        carsCharacteristic2.setFuel(carFuel1);
        carsCharacteristic2.setCapacity(carCapacity2);
        carsCharacteristic2.setVersion(carVersion2);

        carsCharacteristic2.setPrice("39 900");
        carsCharacteristic2.setPriceDate(new Date());
        carsCharacteristic2.setEngCapacity("1 199");
        carsCharacteristic2.setEngType("wolnossący");
        carsCharacteristic2.setCylinders("3");
        carsCharacteristic2.setTransmission("manualna");
        carsCharacteristic2.setGearboxSize("5");
        carsCharacteristic2.setEngPower("68/6000");
        carsCharacteristic2.setTorque("95/3000");
        carsCharacteristic2.setWeight("1051");
        carsCharacteristic2.setLenght("3 996");
        carsCharacteristic2.setHeight("1 474");
        carsCharacteristic2.setWidth("1 749");
        carsCharacteristic2.setWheeelbase("2 540");
        carsCharacteristic2.setTrunk("300");
        carsCharacteristic2.setDoors("5");
        carsCharacteristic2.setFuelConsCity("5.7");
        carsCharacteristic2.setFuelConsRoute("4.1");
        carsCharacteristic2.setFuelConsMixed("4.7");
        carsCharacteristic2.setTankCapacity("45");
        carsCharacteristic2.setAcceleration("14");
        carsCharacteristic2.setTopSpeed("164");

        //third car Kia Rio, benzyna, 1.2, M

        CarBrand carBrand3 = new CarBrand();
        carBrand3.setBrand("Kia");

        CarModel carModel3 = new CarModel();
        carModel3.setBrand(carBrand3);
        carModel3.setModel("Rio");

        CarFuel carFuel3 = new CarFuel();
        carFuel3.setBrand(carBrand3);
        carFuel3.setModel(carModel3);
        carFuel3.setFuel(carFuel1.getFuel());

        CarCapacity carCapacity3 = new CarCapacity();
        carCapacity3.setBrand(carBrand3);
        carCapacity3.setModel(carModel3);
        carCapacity3.setFuel(carFuel1);
        //test
        carCapacity3.setCapacity("1.2");

        CarVersion carVersion3 = new CarVersion();
        carVersion3.setBrand(carBrand3);
        carVersion3.setModel(carModel3);
        carVersion3.setCapacity(carCapacity2);
        carVersion3.setFuel(carFuel1);
        carVersion3.setVersion("M");

        CarsCharacteristic carsCharacteristic3 = new CarsCharacteristic();
        carsCharacteristic3.setBrand(carBrand3);
        carsCharacteristic3.setModel(carModel3);
        carsCharacteristic3.setFuel(carFuel1);
        carsCharacteristic3.setCapacity(carCapacity2);
        carsCharacteristic3.setVersion(carVersion3);

        carsCharacteristic3.setPrice("47 990");
        carsCharacteristic3.setPriceDate(new Date());
        carsCharacteristic3.setEngCapacity("1 248");
        carsCharacteristic3.setEngType("wolnossący");
        carsCharacteristic3.setCylinders("4");
        carsCharacteristic3.setTransmission("manualna");
        carsCharacteristic3.setGearboxSize("5");
        carsCharacteristic3.setEngPower("84/6000");
        carsCharacteristic3.setTorque("122/4000");
        carsCharacteristic3.setWeight("1100");
        carsCharacteristic3.setLenght("4 065");
        carsCharacteristic3.setHeight("1 450");
        carsCharacteristic3.setWidth("1 725");
        carsCharacteristic3.setWheeelbase("2 580");
        carsCharacteristic3.setTrunk("325");
        carsCharacteristic3.setDoors("5");
        carsCharacteristic3.setFuelConsCity("6");
        carsCharacteristic3.setFuelConsRoute("4.1");
        carsCharacteristic3.setFuelConsMixed("4.8");
        carsCharacteristic3.setTankCapacity("45");
        carsCharacteristic3.setAcceleration("12.9");
        carsCharacteristic3.setTopSpeed("170");

        //fourth car Opel Corsa, olej napedowy, 1.4, XL

        CarBrand carBrand4 = new CarBrand();
        carBrand4.setBrand("Opel");

        CarModel carModel4 = new CarModel();
        carModel4.setBrand(carBrand4);
        carModel4.setModel("Corsa");

        CarFuel carFuel4 = new CarFuel();
        carFuel4.setBrand(carBrand4);
        carFuel4.setModel(carModel4);
        carFuel4.setFuel("olej napędowy");

        CarCapacity carCapacity4 = new CarCapacity();
        carCapacity4.setBrand(carBrand4);
        carCapacity4.setModel(carModel4);
        carCapacity4.setFuel(carFuel4);
        carCapacity4.setCapacity("1.4");

        CarVersion carVersion4 = new CarVersion();
        carVersion4.setBrand(carBrand4);
        carVersion4.setModel(carModel4);
        carVersion4.setFuel(carFuel4);
        carVersion4.setCapacity(carCapacity4);
        carVersion4.setVersion("Enjoy");

        CarsCharacteristic carsCharacteristic4 = new CarsCharacteristic();
        carsCharacteristic4.setBrand(carBrand4);
        carsCharacteristic4.setModel(carModel4);
        carsCharacteristic4.setFuel(carFuel4);
        carsCharacteristic4.setCapacity(carCapacity4);
        carsCharacteristic4.setVersion(carVersion4);

        carsCharacteristic4.setPrice("69 990");
        carsCharacteristic4.setPriceDate(new Date());
        carsCharacteristic4.setEngCapacity("1 396");
        carsCharacteristic4.setEngType("turbodoładowany");
        carsCharacteristic4.setCylinders("4");
        carsCharacteristic4.setTransmission("manualna");
        carsCharacteristic4.setGearboxSize("5");
        carsCharacteristic4.setEngPower("90/4000");
        carsCharacteristic4.setTorque("240/1500-2500");
        carsCharacteristic4.setWeight("1230");
        carsCharacteristic4.setLenght("4 065");
        carsCharacteristic4.setHeight("1 450");
        carsCharacteristic4.setWidth("1 725");
        carsCharacteristic4.setWheeelbase("2 580");
        carsCharacteristic4.setTrunk("325");
        carsCharacteristic4.setDoors("5");
        carsCharacteristic4.setFuelConsCity("4.3");
        carsCharacteristic4.setFuelConsRoute("3.4");
        carsCharacteristic4.setFuelConsMixed("3.8");
        carsCharacteristic4.setTankCapacity("45");
        carsCharacteristic4.setAcceleration("12");
        carsCharacteristic4.setTopSpeed("175");

        //fifth car Ford Fiesta, olej napędowy, 1.5, ST Line
        CarBrand carBrand5 = new CarBrand();
        carBrand5.setBrand("Ford");

        CarModel carModel5 = new CarModel();
        carModel5.setBrand(carBrand5);
        carModel5.setModel("Fiesta");

        CarFuel carFuel5 = new CarFuel();
        carFuel5.setBrand(carBrand5);
        carModel5.setModel(carModel5.getModel());
        carFuel5.setFuel(carFuel4.getFuel());

        CarCapacity carCapacity5 = new CarCapacity();
        carCapacity5.setBrand(carBrand5);
        carCapacity5.setModel(carModel5);
        carCapacity5.setFuel(carFuel4);
        carCapacity5.setCapacity("1.5");

        CarVersion carVersion5 = new CarVersion();
        carVersion5.setBrand(carBrand5);
        carVersion5.setModel(carModel5);
        carVersion5.setCapacity(carCapacity5);
        carVersion5.setFuel(carFuel4);
        carVersion5.setVersion("ST-Line");

        CarsCharacteristic carsCharacteristic5 = new CarsCharacteristic();
        carsCharacteristic5.setBrand(carBrand5);
        carsCharacteristic5.setModel(carModel5);
        carsCharacteristic5.setFuel(carFuel4);
        carsCharacteristic5.setCapacity(carCapacity5);
        carsCharacteristic5.setVersion(carVersion5);

        carsCharacteristic5.setPrice("69 200");
        carsCharacteristic5.setPriceDate(new Date());
        carsCharacteristic5.setEngCapacity("1 498");
        carsCharacteristic5.setEngType("turbodoładowany");
        carsCharacteristic5.setCylinders("4");
        carsCharacteristic5.setTransmission("manualna");
        carsCharacteristic5.setGearboxSize("6");
        carsCharacteristic5.setEngPower("120/3750");
        carsCharacteristic5.setTorque("270/1750-2750");
        carsCharacteristic5.setWeight("1188");
        carsCharacteristic5.setLenght("4 065");
        carsCharacteristic5.setHeight("1 466");
        carsCharacteristic5.setWidth("1 735");
        carsCharacteristic5.setWheeelbase("2 493");
        carsCharacteristic5.setTrunk("292");
        carsCharacteristic5.setDoors("5");
        carsCharacteristic5.setFuelConsCity("3.9");
        carsCharacteristic5.setFuelConsRoute("3.2");
        carsCharacteristic5.setFuelConsMixed("3.5");
        carsCharacteristic5.setTankCapacity("42");
        carsCharacteristic5.setAcceleration("9");
        carsCharacteristic5.setTopSpeed("195");

        CarsCharacteristicsDao carsCharacteristicsDao = new CarsCharacteristicsDao(DBManager.getConnectionSource());
        try {
            carsCharacteristicsDao.createOrUpdate(carsCharacteristic1);
            carsCharacteristicsDao.createOrUpdate(carsCharacteristic2);
            carsCharacteristicsDao.createOrUpdate(carsCharacteristic3);
            carsCharacteristicsDao.createOrUpdate(carsCharacteristic4);
            carsCharacteristicsDao.createOrUpdate(carsCharacteristic5);
        } catch (ApplicationException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        DBManager.closeConnectionSource();
    }
}
