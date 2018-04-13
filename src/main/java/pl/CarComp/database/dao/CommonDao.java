package pl.CarComp.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import pl.CarComp.database.models.BaseModel;
import pl.CarComp.utils.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

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
    public <T extends BaseModel, I> void update(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.update((T) baseModel);
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

    public <T extends BaseModel, I> List<T> queryRaw(Class<T> cls, Object value, Object value2, Object value3) throws ApplicationException {
        try {
            Dao<T,I> dao = getDao(cls);

            return dao.queryBuilder().where()
                    .eq("FOREIGN_BRAND_ID",value).and()
                    .eq("FOREIGN_MODEL_ID",value2).and()
                    .eq("FOREIGN_VERSION_ID",value3).query();
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem");
        }
    }

    public <T extends BaseModel, I> List<T> queryForEq(Class<T> cls, String fieldname, Object value) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);

            return dao.queryForEq(fieldname, value);
        } catch (SQLException e) {
            System.out.println(e.getCause().getMessage());
            throw new ApplicationException("Problem");
        }
    }

}
