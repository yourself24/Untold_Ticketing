package data;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Concert {
    private int concertId;
    private String concertName;
    private Timestamp concertDate;

    private String concertInfo;

    private int noOfTickets;

    public Concert(int concertId, String concertName, String concertInfo, Timestamp concertDate, int noOfTickets) {
        this.concertId = concertId;
        this.concertName = concertName;
        this.concertDate = concertDate;
        this.concertInfo = concertInfo;
        this.noOfTickets = noOfTickets;
    }
    public String toString() {
        return "Concert{" +
                "id=" + concertId +
                ", name='" + concertName + '\'' +
                ", info='" + concertInfo + '\'' +
                ", date='" + concertDate + '\'' +
                ", tickets='" + noOfTickets + '\'' +
                '}';
    }

    public int getConcertId() {
        return concertId;
    }
    public void setConcertId(int concertId) {
        this.concertId = concertId;
    }
    public String getConcertName() {
        return concertName;
    }
    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }
    public Timestamp getConcertDate() {
        return concertDate;
    }
    public void setConcertDate(Timestamp concertDate) {
        this.concertDate = concertDate;
    }
    public String getConcertInfo() {
        return concertInfo;
    }
    public void setConcertInfo(String concertInfo) {
        this.concertInfo = concertInfo;
    }
    public int getNoOfTickets() {
        return noOfTickets;
    }
    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }
}
