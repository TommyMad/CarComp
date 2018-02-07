package pl.CarComp.database.dbUtils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import pl.CarComp.database.models.CarBrand;
import pl.CarComp.database.models.CarCapacity;
import pl.CarComp.database.models.CarFuel;
import pl.CarComp.database.models.CarModel;
import pl.CarComp.database.models.CarVersion;

import java.io.IOException;
import java.sql.SQLException;


public class DBManager {
    private static final String JDBC_DRIVER_SQLITE = "jdbc:sqlite:databaseCarsLite";
    private static final String JDBC_DRIVER_H2 = "jdbc:h2:./h2databaseCars";
    private final static String USER="admin";
    private final static String PASSWORD="admin";
    
    private static ConnectionSource connectionSource;

    public static void initDatabase() {
        createConnectionSource();
        //dropTable();
        createTable();
        closeConnectionSource();
    }

    // connect with database
    public static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_SQLITE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // connect if connection failed or null
    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // deleting all tables
    public static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, CarBrand.class, true);
            TableUtils.dropTable(connectionSource, CarModel.class, true);
            TableUtils.dropTable(connectionSource, CarCapacity.class, true);
            TableUtils.dropTable(connectionSource, CarFuel.class, true);
            TableUtils.dropTable(connectionSource, CarVersion.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //creating all tables
    public static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, CarBrand.class);
            TableUtils.createTableIfNotExists(connectionSource, CarModel.class);
            TableUtils.createTableIfNotExists(connectionSource, CarCapacity.class);
            TableUtils.createTableIfNotExists(connectionSource, CarFuel.class);
            TableUtils.createTableIfNotExists(connectionSource, CarVersion.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
