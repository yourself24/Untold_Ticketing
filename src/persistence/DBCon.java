package persistence;
import com.sun.jdi.connect.Connector;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
// this class will be used to connect to the database written in PostgreSQL
public class DBCon {
    private static final Logger LOGGER = Logger.getLogger(DBCon.class.getName());
    private static final String DRIVER = "org.postgresql.Driver";

    private static final String DBURL = "jdbc:postgresql://localhost:5432/untolder";
    private static final String USER = "postgres";

    private static final String PASS = "tomita24";

    private static DBCon singleInstance = new DBCon();

    public DBCon() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DBURL, USER, PASS);
            if (con != null) {
                System.out.println("Connected to the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static Connection getCon() {
        return singleInstance.getConnection();
    }
    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"Error closing Connection");
        }
    }
    public static void close(Statement st) {
        try {
            st.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"Error closing Statement");
        }
    }
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"Error closing ResultSet");
        }
    }
}
