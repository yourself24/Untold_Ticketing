package persistence;

import data.ConcertArtists;
import data.Tickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsDAO extends AbstractDAO<Tickets> {
    public static final String CREATE_SQL = "INSERT INTO tickets(tid,price,con_id,user_ud,buyer_name) VALUES(?,?,?,?,?)";
    public static final String READ_SQL = "SELECT * FROM tickets";
    public static final String UPDATE_SQL = "UPDATE tickets  SET  con_id= ?,price=?,buyer_name=? WHERE tid = ?";
    public static final String DELETE_SQL = "DELETE FROM tickets WHERE tid =?";

    public TicketsDAO(DBCon dbCon) {
        super(dbCon);
    }

    public List findAll() throws SQLException {
        List<Tickets> ticketsListt= new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                int tid = resultSet.getInt("tid");
                float price = resultSet.getFloat("price");
                int con_id = resultSet.getInt("con_id");
                int user_ud = resultSet.getInt("user_ud");
                String buyer_name = resultSet.getString("buyer_name");
                Tickets tick = new Tickets(tid,price,con_id,user_ud,buyer_name);
                ticketsListt.add(tick);
            }

        }
        return ticketsListt;
    }
    public Tickets findbyId(int id) throws SQLException {
        Tickets tick = null;
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(READ_SQL)){
            statement.setInt(1,id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    int tid = resultSet.getInt("tid");
                    float price = resultSet.getFloat("price");
                    int con_id = resultSet.getInt("con_id");
                    int user_ud = resultSet.getInt("user_ud");
                    String buyer_name = resultSet.getString("buyer_name");
                    tick = new Tickets(tid,price,con_id,user_ud,buyer_name);
                }
            }
        }
        return tick;
    }

    @Override
    public boolean update(Tickets ticket) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(UPDATE_SQL)){
            statement.setInt(1,ticket.getConcertId());
            statement.setFloat(2,ticket.getTicketPrice());
            statement.setString(3,ticket.getUserName());
            statement.setInt(4,ticket.getTicketId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }

    @Override
    public boolean insert(Tickets ticket) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(CREATE_SQL)){
            statement.setInt(1,ticket.getTicketId());
            statement.setFloat(2,ticket.getTicketPrice());
            statement.setInt(3,ticket.getConcertId());
            statement.setInt(4,ticket.getUserId());
            statement.setString(5,ticket.getUserName());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }

    @Override
    public boolean delete(Tickets ticket) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(DELETE_SQL)){
            statement.setInt(1,ticket.getTicketId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        }

    }
}
