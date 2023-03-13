package persistence;

import data.ConcertArtists;
import data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConArtDAO extends AbstractDAO<ConcertArtists> {
    public static final String CREATE_SQL = "INSERT INTO con_arts(ca_id,con_id,art_id) VALUES(?,?,?)";
    public static final String READ_SQL = "SELECT * FROM con_arts";
    public static final String UPDATE_SQL = "UPDATE con_arts  SET  con_id= ?,art_id=? WHERE ca_id = ?";
    public static final String DELETE_SQL = "DELETE FROM users WHERE user_id =?";

    public ConArtDAO(DBCon dbCon) {
        super(dbCon);
    }


    @Override
    // implementing the SELECT * from ARTISTS
    public List findAll() throws SQLException {
        List<ConcertArtists> listConArt = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int ca_id = resultSet.getInt("ca_id");
                int con_id = resultSet.getInt("con_id");
                int art_id = resultSet.getInt("art_id");
                ConcertArtists conArt = new ConcertArtists(ca_id,con_id,art_id);
                listConArt.add(conArt);
            }

        }
        return listConArt;
    }

    @Override
    public boolean update(ConcertArtists conArt) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_SQL)){
            statement.setInt(1,conArt.getConcertId());
            statement.setInt(2,conArt.getArtistId());
            statement.setInt(3,conArt.getConArtistsId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }

    @Override
    public boolean insert(ConcertArtists conArt) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(CREATE_SQL)){
            statement.setInt(1,conArt.getConArtistsId());
            statement.setInt(2,conArt.getConcertId());
            statement.setInt(3,conArt.getConcertId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }

    @Override
    public boolean delete(ConcertArtists conArt) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(DELETE_SQL)){
            statement.setInt(1,conArt.getConArtistsId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }
}
