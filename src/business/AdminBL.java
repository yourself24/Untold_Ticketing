package business;

import data.Artists;
import data.Concert;
import data.Tickets;
import data.User;
import persistence.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AdminBL {
    public AdminBL(User usr){
        //the admin will be able to
    }
    DBCon dbcon = new DBCon();
    UserDAO userDAO = new UserDAO(dbcon);
    // Part 1. CRUD operations on cashiers.
    //retrieving cashiers
    public List<User> retreiveCashiers() throws SQLException {
        List<User> listCashiers = null;
        try {
            listCashiers = userDAO.findAll();
            Iterator<User> it = listCashiers.iterator();
            while (it.hasNext()) {
                User usr = it.next();
                if (!usr.getUserType().equals("cashier")) {
                    it.remove();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listCashiers;
    }
    //creating a new cashier
    public boolean createCashier(String username,String password) throws SQLException {
        Random rand = new Random();
        int id = rand.nextInt(1000);
        User usr = new User(id,username,password,"cashier");
        try{
            userDAO.insert(usr);
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    //deleting a cashier
    public boolean deleteCashier(int id) throws SQLException {
        User usr = userDAO.findById(id);
        try{
            userDAO.delete(usr);
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    //updating a cashier
    public boolean updateCashier(int id,String username,String password) throws SQLException {
        User usr = userDAO.findById(id);
        usr.setUserName(username);
        usr.setUserPassword(password);
        try{
            userDAO.update(usr);
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    // Part 2. CRUD operations on concerts
    //retrieving concerts
    ConcertDAO concertDAO = new ConcertDAO(dbcon);
    List<Concert> retrieveConcerts() throws SQLException{
        List<Concert> listConcerts = null;
        try{
            listConcerts = concertDAO.findAll();
    }catch (SQLException ex){
        ex.printStackTrace();
    }
        return listConcerts;
    }
    //create a new concert
    public boolean createConcert(String concertName, String concertInfo, Timestamp conTime,int nrTickets){
        Random rand = new Random();
        int id = rand.nextInt(1000);
        Concert con = new Concert(id,concertName,concertInfo,conTime,nrTickets);
        try{
            concertDAO.insert(con);
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    //delete a concert
    public boolean deleteConcert(String concertName) throws SQLException{
        int conID = concertDAO.findByName(concertName);
        Concert con = concertDAO.retrieveByID(conID);
        try{
            concertDAO.delete(con);
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    //update a concert
    public boolean updateConcert(int concertId,String concertName,String concertInfo, Timestamp conTime,int nrTickets) throws SQLException{
        Concert con = concertDAO.retrieveByID(concertId);
        con.setConcertName(concertName);
        con.setConcertInfo(concertInfo);
        con.setConcertDate(conTime);
        con.setNoOfTickets(nrTickets);
        try{
            concertDAO.update(con);
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    //Part 3. CRUD operations on artists
    //retrieving artists
    ArtistDAO artistDAO = new ArtistDAO(dbcon);
    public List <Artists> getArtists(){
        List<Artists> listArtists = null;
        try {
            listArtists = artistDAO.findAll();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return listArtists;

    }
    //create a new artist
    public boolean createArtist(String artistName,String artistGenre){
        Random rand = new Random();
        int id = rand.nextInt(1000);
        Artists art = new Artists(id,artistName,artistGenre);
        try{
            artistDAO.insert(art);
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    //delete an artist
    public boolean deleteArtist(String artistName) throws SQLException{
        Artists art = artistDAO.findbyName(artistName);
        try{
            artistDAO.delete(art);
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    //update an artist
    public boolean updateArtist(int artId,String artistName,String artistGenre) throws SQLException{
        Artists artist = artistDAO.findbyId(artId);
        artist.setArtistName(artistName);
        artist.setArtistGenre(artistGenre);
        try{
            artistDAO.update(artist);
            return true;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
    }
    public List<Tickets> viewTickets(String concertName) throws SQLException {
        List<Tickets> tList = new ArrayList<>();
        ConcertDAO cdao = new ConcertDAO(dbcon);
        TicketsDAO tdao = new TicketsDAO(dbcon);
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

}
