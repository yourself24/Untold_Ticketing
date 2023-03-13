package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T> {
    private DBCon dbCon;

    public AbstractDAO(DBCon dbCon) {
        this.dbCon = dbCon;
    }

    public abstract List<T> findAll() throws SQLException;


    public abstract boolean insert(T t) throws SQLException;

    public abstract boolean update(T t) throws SQLException;

    public abstract boolean delete(T t) throws SQLException;

    protected Connection getConnection() {
        return dbCon.getConnection();
    }
}
