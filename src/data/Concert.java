package data;
import java.time.LocalDateTime;

public class Concert {
    private int concertId;
    private String concertName;
    private LocalDateTime concertDate;

    private String concertInfo;

    private int noOfTickets;

    public Concert(int concertId, String concertName, LocalDateTime concertDate, String concertInfo, int noOfTickets) {
        this.concertId = concertId;
        this.concertName = concertName;
        this.concertDate = concertDate;
        this.concertInfo = concertInfo;
        this.noOfTickets = noOfTickets;
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
    public LocalDateTime getConcertDate() {
        return concertDate;
    }
    public void setConcertDate(LocalDateTime concertDate) {
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
