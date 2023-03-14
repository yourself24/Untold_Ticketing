package presentation;
import business.UserBL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JLabel titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        // set up the frame
        setTitle("Login Page");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the components
        titleLabel = new JLabel("Login to Untold Ticketing App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");

        // create the panel for the components
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        // add the components to the frame
        Container contentPane = getContentPane();
        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.CENTER);

        // add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                UserBL userBL = new UserBL();
                 try {
                     if (userBL.credentialsCheck(username, password)) {
                         String type = userBL.foundUser(username);
                         System.out.println(type);
                         if(type.equals("cashier")){
                                CashierPanel cashierFrame = new CashierPanel(userBL.returnUser(username));
                                cashierFrame.setVisible(true);
                                dispose();
                         }
                         else if(type.equals("admin")){
                             AdminPanel adminFrame = new AdminPanel(userBL.returnUser(username));
                                adminFrame.setVisible(true);
                                //dispose();
                         }

                     }
                 }catch (SQLException ex) {
                     ex.printStackTrace();
                 }
            }
        });
    }
}
