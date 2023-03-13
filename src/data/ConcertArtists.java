package data;

public class ConcertArtists {
    private int conArtistsId;

    private int concertId;

    private int artistId;

    public ConcertArtists(int conArtistsId, int concertId, int artistId) {
        this.conArtistsId = conArtistsId;
        this.concertId = concertId;
        this.artistId = artistId;
    }
    public String toString() {
        return "Concert Artits{" +
                "id=" + conArtistsId +
                ", related concert='" + concertId + '\'' +
                ", related artist='" + artistId + '\'' +"" +
                '}';
    }

    public int getConArtistsId() {
        return conArtistsId;
    }
    public void setConArtistsId(int conArtistsId) {
        this.conArtistsId = conArtistsId;
    }
    public int getConcertId() {
        return concertId;
    }
    public void setConcertId(int concertId) {
        this.concertId = concertId;
    }
    public int getArtistId() {
        return artistId;
    }
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
}
