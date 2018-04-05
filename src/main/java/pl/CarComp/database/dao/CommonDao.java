package pl.CarComp.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.ConnectionSource;

import javafx.beans.property.IntegerProperty;
import pl.CarComp.database.models.BaseModel;
import pl.CarComp.database.models.CarBrand;
import pl.CarComp.utils.exceptions.ApplicationException;

public abstract class CommonDao {
    protected final ConnectionSource connectionSource;

    public CommonDao(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }
    // methods that operate on Base Model Interface

    // create objects in database
    @SuppressWarnings("unchecked")
    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem z aktualizacją/tworzeniem wpisu w bazie. Dane nie mogą się powtarzać.");
        }

    }

    // refreshing database
    @SuppressWarnings("unchecked")
    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem z odświeżeniem danych w bazie.");
        }
    }

    // deleting
    @SuppressWarnings("unchecked")
    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem z usunięciem wpisu w bazie.");
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem z usunięciem wpisu w bazie.");
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Nie znaleziono wpisu w bazie.");
        }
    }

    // get DAO
    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationException {

        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem z połączeniem do DAO");
        }
    }//return null

    //query for all
    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem z pobraniem danych w bazie");
        }
        //return null;
    }

    //query
    public <T extends BaseModel, I> T queryForId(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem z wyszukaniem danych w bazie po numerze");
        }
        //return null;
    }

    //testing
    public <T extends BaseModel, I> List<T> queryForMatching(Class<T> cls, Object obj) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);

            return dao.queryForMatching((T) obj);
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem");
        }
    }
    /*public <T extends BaseModel, I> List<T> queryForEq(Class<T> cls,String string) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);

            return dao.queryForEq();
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem");
        }
    }*/
}
