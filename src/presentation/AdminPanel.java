package presentation;
import business.AdminBL;
import business.Exporter;
import business.ExporterFactory;
import data.Tickets;
import data.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminPanel extends JFrame {
    User user;

    private JButton cashierButton;
    private JButton concertButton;
    private JButton artistButton;
    private JButton exportButton;

    public AdminPanel(User user) {
        super("Admin Panel");
        JOptionPane.showMessageDialog(null, "Welcome " + user.getUserName() + "!");
        setLayout(new GridLayout(4, 1));

        cashierButton = new JButton("Manage Cashiers");
        concertButton = new JButton("Manage Concerts");
        artistButton = new JButton("Manage Artists");
        exportButton = new JButton("Export Tickets");
        this.user = user;
        AdminBL adminBL = new AdminBL(user);


        add(cashierButton);
        add(concertButton);
        add(artistButton);
        add(exportButton);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        exportButton.addActionListener(e -> {
            String concertName = JOptionPane.showInputDialog(this, "Enter concert name:");

            Exporter exporter = ExporterFactory.createExporter("csv");
            try {
                List<Tickets> tickets = adminBL.viewTickets(concertName);
                exporter.export(tickets, concertName + ".csv");
                JOptionPane.showMessageDialog(this, "Tickets exported successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting tickets: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error exporting tickets: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


}
