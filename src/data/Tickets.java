package data;

public class Tickets {
    private int ticketId;
    private int concertId;

    private String userName;

    private float ticketPrice;

    private int userId;

    public Tickets(int ticketId, float ticketPrice, int concertId, int userId,String userName) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.concertId = concertId;
        this.userId = userId;
        this.userName = userName;
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

    public float getTicketPrice() {
        return ticketPrice;
    }
    public String toString() {
        return "Ticket{" +
                "tid=" + ticketId +
                ", Price='" + ticketPrice + '\'' +
                ", concertID='" + concertId + '\'' +"" +
                ", cashierId='" + userId + '\'' +
                ", buyer='" + userName + '\'' +
                '}';
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
