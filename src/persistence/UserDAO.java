package persistence;

import data.Artists;
import data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Base64;

public class UserDAO extends AbstractDAO<User> {

    Base64.Encoder encoder = Base64.getEncoder();
    Base64.Decoder decoder = Base64.getDecoder();

    public static final String CREATE_SQL = "INSERT INTO users(user_id,username,password,type) VALUES(?,?,?,?)";
    public static final String READ_SQL = "SELECT * FROM users";
    public static final String UPDATE_SQL = "UPDATE users  SET password = ?, username = ? WHERE user_id = ?";
    public static final String DELETE_SQL = "DELETE FROM users WHERE user_id =?";
    public UserDAO(DBCon dbCon) {
        super(dbCon);
    }

    @Override
    // implementing the SELECT * from ARTISTS
    public List findAll() throws SQLException {
        List<User> listUsr = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int uid = resultSet.getInt("user_id");
                String uname = resultSet.getString("username");
                String upass = new String(decoder.decode(resultSet.getString("password")));
                String utype = resultSet.getString("type");
                User usr = new User(uid,uname,upass,utype);
                listUsr.add(usr);
            }

        }
        return listUsr;
    }
    public User findUser(String username) throws SQLException {
        User usr = null;
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int uid = resultSet.getInt("user_id");
                String uname = resultSet.getString("username");
                String upass = new String(decoder.decode(resultSet.getString("password")));
                String utype = resultSet.getString("type");
                if(uname.equals(username)){
                    usr = new User(uid,uname,upass,utype);
                    break;
                }
            }

        }
        return usr;
    }

    @Override
    public boolean update(User usr) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_SQL)){
            statement.setString(1,usr.getUserName());
            statement.setString(2, encoder.encodeToString(usr.getUserPassword().getBytes()));
            statement.setInt(3,usr.getUserId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }

    @Override
    public boolean insert(User usr) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(CREATE_SQL)){
            statement.setInt(1,usr.getUserId());
            statement.setString(2,usr.getUserName());
            statement.setString(3,encoder.encodeToString(usr.getUserPassword().getBytes()));
            statement.setString(4,usr.getUserType());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }

    @Override
    public boolean delete(User usr) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(DELETE_SQL)){
            statement.setInt(1,usr.getUserId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }
    public User findById(int id) throws SQLException {
        User usr = null;
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int uid = resultSet.getInt("user_id");
                String uname = resultSet.getString("username");
                String upass = new String(decoder.decode(resultSet.getString("password")));
                String utype = resultSet.getString("type");
                if(uid == id){
                    usr = new User(uid,uname,upass,utype);
                    break;
                }
            }

        }
        return usr;
    }
}
