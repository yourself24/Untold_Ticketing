package business;

import data.Concert;
import data.Tickets;
import data.User;
import persistence.ConcertDAO;
import persistence.DBCon;
import persistence.TicketsDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CashierBL {
    Random rand = new Random();
    private User user;

    public CashierBL(User usr) {
        this.user = usr;
    }

    DBCon con = new DBCon();
    TicketsDAO tdao = new TicketsDAO(con);

    public String createTicket(String buyerName, int tickets, String concertName, float ticketPrice) throws SQLException {
        ConcertDAO cdao = new ConcertDAO(con);
        int concertId = cdao.findByName(concertName);
        Concert con1 = cdao.retrieveByID(cdao.findByName(concertName));
        if (con1.getNoOfTickets() < tickets) {
            System.err.println("Not enough tickets available!");
            return "Not enough tickets available!";
        }
        for(int i=0;i<tickets;i++){
        int ticketId = rand.nextInt(1000);
        Tickets tick1 = new Tickets(ticketId, ticketPrice, cdao.findByName(concertName), user.getUserId(), buyerName);
        tdao.insert(tick1);
        }
            con1.setNoOfTickets(con1.getNoOfTickets() - tickets);
            System.out.println(con1);
            if(cdao.update(con1)){
                return "Tickets bought successfully!";
            }
            else{
                return "Tickets not bought!";
            }
    }

    public boolean deleteTicket(int ticketId) throws SQLException {
        boolean succ = false;
        Tickets t1 = new Tickets(ticketId, 25.4F, 2, 23, "sad");
        try {
            if(tdao.delete(t1)){
                succ = true;
            }
        } catch (SQLException eq) {
            eq.printStackTrace();
        }
        return succ;
    }

    public List<Tickets> viewTickets(String concertName) throws SQLException {
        List<Tickets> tList = new ArrayList<>();
        ConcertDAO cdao = new ConcertDAO(con);
        int concertId = cdao.findByName(concertName);
        try {
            tList = tdao.findAll();
            Iterator<Tickets> it = tList.iterator();
            while (it.hasNext()) {
                Tickets t = it.next();
                if (t.getConcertId() != concertId) {
                    it.remove();
                }
            }
        } catch (SQLException eq) {
            eq.printStackTrace();
        }
        return tList;
    }
   public boolean updateTicket(int ticketId,String buyerName,String concertName,float ticketPrice) throws SQLException {
        ConcertDAO cdao = new ConcertDAO(con);
        int concertId = cdao.findByName(concertName);
        Tickets tick1 = new Tickets(ticketId, ticketPrice, cdao.findByName(concertName), user.getUserId(), buyerName);
        return tdao.update(tick1);
    }
}
