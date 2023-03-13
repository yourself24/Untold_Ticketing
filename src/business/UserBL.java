package business;

import data.User;
import persistence.DBCon;
import persistence.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBL
{
    public boolean credentialsCheck(String username, String password) throws SQLException
    {
     boolean check = false;
        DBCon con = new DBCon();
        UserDAO udao = new UserDAO(con);
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
        DBCon con = new DBCon();
        UserDAO udao = new UserDAO(con);
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
}
