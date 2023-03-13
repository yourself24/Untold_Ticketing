package persistence;

import data.Artists;
import data.Concert;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConcertDAO extends AbstractDAO<Concert> {
    public static final String CREATE_SQL = "INSERT INTO concert(con_id,con_name,con_info,date_time,ticket_no) VALUES(?,?,?,?,?)";
    public static final String READ_SQL = "SELECT * FROM concert";
    public static final String UPDATE_SQL = "UPDATE concert  SET  con_name= ?,con_info=?,date_time=?,ticket_no=? WHERE con_id = ?";
    public static final String DELETE_SQL = "DELETE FROM concert WHERE con_id =?";

    public ConcertDAO(DBCon dbCon) {
        super(dbCon);
    }

    @Override
    // implementing the SELECT * from ARTISTS
    public List findAll() throws SQLException {
        List<Concert> listCon = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int con_id = resultSet.getInt("con_id");
                String con_name = resultSet.getString("con_name");
                String con_info = resultSet.getString("con_info");
                Timestamp date_time = resultSet.getTimestamp("date_time");
                int ticket_no = resultSet.getInt("ticket_no");
                Concert concert = new Concert(con_id,con_name,con_info,date_time,ticket_no);
                listCon.add(concert);
            }

        }
        return listCon;
    }
    public String retTs() throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement stm = con.prepareStatement(READ_SQL);
             ResultSet rs = stm.executeQuery()) {
            if (rs.next()) { // move the cursor to the first row
                String tms = rs.getString("con_name"); // retrieve data from the "con_name" column
                return tms;
            }
        }
        return null; // return null if no data was retrieved
    }

    @Override
    public boolean update(Concert conn) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_SQL)){
            statement.setString(1,conn.getConcertName());
            statement.setString(2, conn.getConcertInfo());
            statement.setObject(3,conn.getConcertDate());
            statement.setInt(4,conn.getNoOfTickets());
            statement.setInt(5,conn.getConcertId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }

    @Override
    public boolean insert(Concert conn) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(CREATE_SQL)){
            statement.setInt(1,conn.getConcertId());
            statement.setString(2,conn.getConcertName());
            statement.setString(3,conn.getConcertInfo());
            statement.setObject(4,conn.getConcertDate());
            statement.setInt(5,conn.getNoOfTickets());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }

    @Override
    public boolean delete(Concert conn) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, conn.getConcertId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }
    }
        public int findByName(String conName) throws SQLException{
            int ticketId = 0;
            try(Connection con = getConnection();
                PreparedStatement statement = con.prepareStatement("SELECT con_id from concert WHERE con_name = ? ")){
                statement.setString(1,conName);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    ticketId = resultSet.getInt("con_id");
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return ticketId;

        }
        public Concert retrieveByID(int conID) throws SQLException{
            Concert concert = null;
            try(Connection con = getConnection();
                PreparedStatement statement = con.prepareStatement("SELECT * from concert WHERE con_id = ? ")){
                statement.setInt(1,conID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    int con_id = resultSet.getInt("con_id");
                    String con_name = resultSet.getString("con_name");
                    String con_info = resultSet.getString("con_info");
                    Timestamp date_time = resultSet.getTimestamp("date_time");
                    int ticket_no = resultSet.getInt("ticket_no");
                    concert = new Concert(con_id,con_name,con_info,date_time,ticket_no);
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return concert;
        }

    }


