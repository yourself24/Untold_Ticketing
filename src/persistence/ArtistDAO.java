package persistence;
import data.Artists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO extends AbstractDAO<Artists> {
    public static final String CREATE_SQL = "INSERT INTO artists(art_id,name,genre) VALUES(?,?,?)";
    public static final String READ_SQL = "SELECT * FROM artists";
    public static final String UPDATE_SQL = "UPDATE artists SET name = ?, genre = ? WHERE art_id = ?";
    public static final String DELETE_SQL = "DELETE FROM artists WHERE art_id =?";

    public ArtistDAO(DBCon dbCon) {
        super(dbCon);
    }

    @Override
    // implementing the SELECT * from ARTISTS
    public List findAll() throws SQLException {
        List<Artists> listArt = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int art_id = resultSet.getInt("art_id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                Artists art = new Artists(art_id,name,genre);
                listArt.add(art);
            }


        } catch (SQLException excl){
            excl.printStackTrace();
        }

        return listArt;
    }

    @Override
    public boolean update(Artists artists) throws SQLException {
        try (Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(UPDATE_SQL)){
            statement.setString(1,artists.getArtistName());
            statement.setString(2, artists.getArtistGenre());
            statement.setInt(3,artists.getArtistId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }

    @Override
    public boolean insert(Artists artist) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(CREATE_SQL)){
            statement.setInt(1,artist.getArtistId());
            statement.setString(2,artist.getArtistName());
            statement.setString(3,artist.getArtistGenre());

            int rowsAffected = statement.executeUpdate();
            con.close();
            return rowsAffected == 1;
        }
        }



    @Override
    public boolean delete(Artists artist) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(DELETE_SQL)){
            statement.setInt(1,artist.getArtistId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }
    public Artists findbyName(String name) throws SQLException {
        Artists art = null;
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int art_id = resultSet.getInt("art_id");
                String name1 = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                if(name1.equals(name)){
                    art = new Artists(art_id,name1,genre);
                    break;
                }
            }

        }
        return art;
    }
    public Artists findbyId(int artId) throws SQLException {
        Artists art = null;
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int art_id = resultSet.getInt("art_id");
                String name1 = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                if(art_id == artId){
                    art = new Artists(art_id,name1,genre);
                    break;
                }
            }

        }
        return art;
    }
}
