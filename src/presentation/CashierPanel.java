package presentation;

import business.CashierBL;
import data.Tickets;
import data.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class CashierPanel extends JFrame {
    User user;

    public CashierPanel(User user) {

        super("Cashier Panel");
        this.user = user;
        CashierBL cashierBL = new CashierBL(user);
        // Set the size and position of the frame
        setSize(400, 400);
        setLocationRelativeTo(null); // center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the components for the panel
        JLabel titleLabel = new JLabel("Cashier Panel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JButton sellTicketButton = new JButton("Sell Ticket");
        JButton showTicketsButton = new JButton("Show Tickets");
        JButton updateTicketButton = new JButton("Update Ticket");

        JButton cancelTicketButton = new JButton("Cancel Ticket");

        // Add the components to the panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(sellTicketButton, gbc);
        gbc.gridx = 1;
        panel.add(showTicketsButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(cancelTicketButton, gbc);
        gbc.gridx = 1;
        panel.add(updateTicketButton, gbc);

        // Add an action listener to the Sell Ticket button
        sellTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sellTicketButton) {
                    JTextField buyerNameField = new JTextField();
                    JTextField numTicketsField = new JTextField();
                    JTextField concertNameField = new JTextField();
                    JTextField ticketPriceField = new JTextField();
                    Object[] fields = {"Buyer Name:", buyerNameField, "Number of Tickets:", numTicketsField,
                            "Concert Name:", concertNameField, "Ticket Price:", ticketPriceField};
                    int result = JOptionPane.showConfirmDialog(null, fields, "Sell Ticket", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        String buyerName = buyerNameField.getText();
                        int numTickets = Integer.parseInt(numTicketsField.getText());
                        String concertName = concertNameField.getText();
                        float ticketPrice = Float.parseFloat(ticketPriceField.getText());

                        try {
                            cashierBL.createTicket(buyerName, numTickets, concertName, ticketPrice);
                            JOptionPane.showMessageDialog(null, "Ticket sold successfully.");
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error selling ticket: " + ex.getMessage());
                        }
                    }
                    // Code to sell ticket
                }
            }
        });
        JOptionPane showTicketsDialog = new JOptionPane();
        JTextField concertNameField = new JTextField();
        showTicketsDialog.setMessage(new Object[]{"Enter concert name:", concertNameField});
        showTicketsDialog.setOptionType(JOptionPane.OK_CANCEL_OPTION);
        // Add an action listener to the Show Tickets button
        showTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show the dialog box for entering concert name
                showTicketsDialog.createDialog(null, "Show Tickets").setVisible(true);

                // get the concert name entered by the user
                String concertName = concertNameField.getText();

                // query the database for tickets of the given concert
                List<Tickets> tickets = null;
                try {
                    tickets = cashierBL.viewTickets(concertName);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // create a table model for displaying the tickets
                String[] columnNames = {"Ticket ID", "Price", "Concert Name", "buyer name"};
                Object[][] rowData = new Object[tickets.size()][4];
                for (int i = 0; i < tickets.size(); i++) {
                    Tickets ticket = tickets.get(i);
                    rowData[i][0] = ticket.getTicketId();
                    rowData[i][1] = ticket.getTicketPrice();
                    rowData[i][2] = ticket.getConcertId();
                    rowData[i][3] = ticket.getUserName();
                }
                DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

                // create a table to display the tickets
                JTable ticketTable = new JTable(model);

                // create a scroll pane for the table
                JScrollPane scrollPane = new JScrollPane(ticketTable);

                // create a dialog box for displaying the tickets
                JOptionPane ticketDialog = new JOptionPane();
                ticketDialog.setMessage(scrollPane);
                ticketDialog.setOptionType(JOptionPane.DEFAULT_OPTION);
                ticketDialog.createDialog(null, "Tickets for " + concertName).setVisible(true);
                // Code to show tickets
            }
        });

        // Add an action listener to the Cancel Ticket button
        cancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to cancel ticket
                String input = JOptionPane.showInputDialog("Enter Ticket ID:");
                int ticketId = Integer.parseInt(input);
                // call your method to cancel the ticket with the given ID
                boolean success = false;
                try {
                    success = cashierBL.deleteTicket(ticketId);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if (success) {
                    JOptionPane.showMessageDialog(null, "Ticket with ID " + ticketId + " has been cancelled.");
                } else {
                    JOptionPane.showMessageDialog(null, "Unable to cancel ticket with ID " + ticketId + ".");
                }
            }
        });
        updateTicketButton.addActionListener(new ActionListener() {
            boolean succ = false;
            public void actionPerformed(ActionEvent e) {
            if (e.getSource() ==updateTicketButton){
                int ticketId = Integer.parseInt(JOptionPane.showInputDialog("Enter Ticket ID:"));
                String buyerName = JOptionPane.showInputDialog("Enter Buyer Name:");
                String concertName = JOptionPane.showInputDialog("Enter Concert Name:");
                float ticketPrice = Float.parseFloat(JOptionPane.showInputDialog("Enter Ticket Price:"));
                try {
                    succ = cashierBL.updateTicket(ticketId,buyerName,concertName,ticketPrice);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if(succ){
                    JOptionPane.showMessageDialog(null, "Ticket with ID " + ticketId + " has been updated.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Unable to update ticket with ID " + ticketId + ".");
                }
            }
            }
        });

        // Set the panel as the content pane of the frame
        setContentPane(panel);

        // Show the frame
        setVisible(true);
    }


}
