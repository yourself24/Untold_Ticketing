package business;

import data.User;
import persistence.DBCon;
import persistence.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBL
{
    DBCon con = new DBCon();
    UserDAO udao = new UserDAO(con);
    public boolean credentialsCheck(String username, String password) throws SQLException
    {
     boolean check = false;

        List<User> uList = udao.findAll();
        for (User u : uList)
        {
            if (u.getUserName().equals(username) && u.getUserPassword().equals(password))
            {
                check = true;
                break;
            }
        }
        return check;
    }
    public String foundUser(String username) throws SQLException
    {
        String type = "";
        List<User> uList = udao.findAll();
        for (User u : uList)
        {
            if (u.getUserName().equals(username))
            {
                type = u.getUserType();
                break;
            }
        }
        return type;
    }
    public User returnUser(String username) throws SQLException{
        User user = null;
        try {
            user = udao.findUser(username);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
            return user;


    }
}
