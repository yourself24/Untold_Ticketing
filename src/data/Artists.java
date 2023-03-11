package data;

public class Artists {
    private int artistId;

    private String artistName;

    private String artistGenre;

    public Artists(int artistId, String artistName, String artistGenre) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }

    public int getArtistId() {
        return artistId;
    }
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public String getArtistGenre() {
        return artistGenre;
    }
    public void setArtistGenre(String artistGenre) {
        this.artistGenre = artistGenre;
    }

}
