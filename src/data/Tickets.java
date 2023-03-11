package data;

public class Tickets {
    private int ticketId;
    private int concertId;

    private String userName;

    private float ticketPrice;

    private int userId;

    public Tickets(int ticketId, int concertId, String userName, float ticketPrice, int userId) {
        this.ticketId = ticketId;
        this.concertId = concertId;
        this.userName = userName;
        this.ticketPrice = ticketPrice;
        this.userId = userId;
    }

    public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public int getConcertId() {
        return concertId;
    }
    public void setConcertId(int concertId) {
        this.concertId = concertId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
