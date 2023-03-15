import business.AdminBL;
import business.CashierBL;
import business.UserBL;
import data.*;
import persistence.*;
import presentation.AdminPanel;
import presentation.CashierPanel;
import presentation.LoginFrame;

import java.util.Base64;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Base64.Encoder encoding =  Base64.getEncoder();
        System.out.println("Hello world!");
        DBCon hatz = new DBCon();
        ArtistDAO adao = new ArtistDAO(hatz);
        ConcertDAO cdao = new ConcertDAO(hatz);
        UserDAO udao = new UserDAO(hatz);
        ConArtDAO cadao = new ConArtDAO(hatz);
        TicketsDAO tdao = new TicketsDAO(hatz);
        try{
            List<Concert> conList = cdao.findAll();
            List<Artists> artList = adao.findAll();
            //ConcertArtists ca1 = new ConcertArtists(2,conList.get(0).getConcertId(),artList.get(1).getArtistId());
//            System.out.println("before encoding"+conList.get(0).getConcertName());
//            String str = encoding.encodeToString(conList.get(0).getConcertName().getBytes());
            //System.out.println("Encoded String is : " + str);
            List<ConcertArtists> caList = cadao.findAll();
            UserBL ubl = new UserBL();

            for(ConcertArtists cal: caList){
                System.out.println(cal);
            }
            Tickets t1 = new Tickets(2, 36.8F,conList.get(0).getConcertId(),3,"Mihai");
            //tdao.insert(t1);
            tdao.delete(t1);
            List<Tickets> tList = tdao.findAll();
            for(Tickets tik: tList){
                System.out.println(tik);
            }
            User u3 = new User(3,"Luca","cox123","cashier");
            System.out.println(ubl.credentialsCheck(u3.getUserName(),u3.getUserPassword()));
            System.out.println(ubl.foundUser(u3.getUserName()));
            //udao.insert(u3);
            List <User> uList = udao.findAll();
            for(User u: uList){
                System.out.println(u);
            }
            CashierBL cashierBl = new CashierBL(u3);
            //cashierBl.createTicket("Luka",5,"arer",25.6F);
            List<Tickets> t2 = cashierBl.viewTickets("arer");
            for(Tickets tik: t2){
                System.out.println(tik);
            }
            Tickets t5 = t2.get(1);
            //cashierBl.updateTicket(t5.getTicketId(),t5.getUserName(),"Alchemy Stage",26.9F);
            //cashierBl.deleteTicket(49);
            //Concert con1 = new Concert(1,"Alchemy Stage", "2020-12-12", Timestamp.valueOf("2020-12-12 23:00:00"), 20);
            //cdao.update(con1);
            //cdao.insert(con1);
            User u1 = new User(5,"Lucas","1234toc","admin");
            AdminBL adminBl = new AdminBL(u1);
            List<User> cashierList = adminBl.retreiveCashiers();
            for (User u: cashierList){
                System.out.println(u);
            }
            //adminBl.createArtist("Stormzy","Drill");
            //adminBl.createCashier("Lucca","tom123");
            //adminBl.createConcert("DayDreaming Stage","minimale",Timestamp.valueOf("2023-03-30 22:30:00"),30);
            //adminBl.updateArtist(artList.get(0).getArtistId(),"Stormzzy","Rap");
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            //CashierPanel cashierPanel = new CashierPanel();
            ///cashierPanel.setVisible(true);
            //AdminPanel adminPanel = new AdminPanel(u1);
                    //udao.insert(u1);

        } catch (SQLException ex) {
           ex.printStackTrace();
        }



    }
}