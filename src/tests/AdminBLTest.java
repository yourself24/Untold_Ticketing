package tests;

import business.AdminBL;
import data.Artists;
import data.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.ArtistDAO;
import persistence.DBCon;
import persistence.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminBLTest {

    private DBCon dbcon;
    private ArtistDAO artistDAO;
    private AdminBL adminBL;
    User testAdmin = new User(7856,"test_admin","test","admin");
    private UserDAO userDAO;

    @Before
    public void setUp() throws SQLException {
        // Set up test database connection and objects
        dbcon = new DBCon();
        artistDAO = new ArtistDAO(dbcon);
        adminBL = new AdminBL(testAdmin);
        userDAO = new UserDAO(dbcon);
    }

    @After
    public void tearDown() throws SQLException {
        // Close database connection
        //dbcon.close();
    }

    @Test
    public void testUpdateArtist() throws SQLException {
        // Insert a new artist into the database
        Artists artist = new Artists(220, "Test Artist", "Test Genre");
        artistDAO.insert(artist);


        // Update the artist's name and genre
        boolean result = adminBL.updateArtist(artist.getArtistId(), "New Artist Name", "New Artist Genre");

        // Verify that the artist was updated successfully
        assertTrue(result);

        Artists updatedArtist = artistDAO.findbyId(artist.getArtistId());
        assertEquals("New Artist Name", updatedArtist.getArtistName());
        assertEquals("New Artist Genre", updatedArtist.getArtistGenre());

        // Clean up test data
        artistDAO.delete(artist);
        userDAO.delete(testAdmin);
    }
}
