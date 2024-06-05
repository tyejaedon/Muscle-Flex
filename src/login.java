import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class login  implements ActionListener {

    JPanel panelField = new JPanel();
    JPanel panelLabel = new JPanel();
    JPanel panelButt = new JPanel();

    JLabel login = new JLabel("Username:");
    JLabel password = new JLabel("Password:");
    JLabel welcome = new JLabel("Welcome to Muscle Flex");

    JTextField username_Field = new JTextField();
    JPasswordField passField = new JPasswordField();

    GridBagConstraints gbc = new GridBagConstraints();
    JButton signIn = new JButton("Login");
    JButton register = new JButton("Register");
    JFrame frame = new JFrame();
    public void config() {
        gbc.insets = new Insets(15, 15, 15, 15);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.BLACK);

        // Box layout for the panels that are aligned vertically
        panelField.setLayout(new BoxLayout(panelField, BoxLayout.Y_AXIS));
        panelLabel.setLayout(new BoxLayout(panelLabel, BoxLayout.Y_AXIS));
        panelField.setBackground(Color.DARK_GRAY);
        panelLabel.setBackground(Color.DARK_GRAY);
        panelButt.setBackground(Color.BLACK);

        JLabel[] labels = {login, password};
        JTextField[] fields = {username_Field, passField};

        // Styling for welcome label
        welcome.setFont(new Font("Arial", Font.BOLD, 30));
        welcome.setForeground(Color.RED);
        welcome.setHorizontalAlignment(JLabel.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(welcome, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;

        // Styling of the labels
        for (JLabel label : labels) {
            label.setFont(new Font("Verdana", Font.BOLD, 20));
            label.setForeground(Color.WHITE);
            label.setHorizontalAlignment(JLabel.CENTER);
            panelLabel.add(label);
            panelLabel.add(Box.createRigidArea(new Dimension(5, 10)));
        }

        // Styling of the text fields
        for (JTextField field : fields) {
            field.setPreferredSize(new Dimension(200, 30));
            field.setFont(new Font("Arial", Font.PLAIN, 18));
            field.setBackground(Color.LIGHT_GRAY);
            field.setForeground(Color.BLACK);
            field.setHorizontalAlignment(JTextField.CENTER);
            panelField.add(field);
            panelField.add(Box.createRigidArea(new Dimension(5, 10)));
        }

        // Adding to the frame with gbc constraints
        frame.add(panelLabel, gbc);
        gbc.gridx++;
        frame.add(panelField, gbc);

        // Styling buttons
        signIn.setFont(new Font("SansSerif", Font.BOLD, 18));
        signIn.setBackground(Color.RED);
        signIn.setForeground(Color.WHITE);
        signIn.setFocusPainted(false);
        signIn.setBorderPainted(false);

        register.setFont(new Font("SansSerif", Font.BOLD, 18));
        register.setBackground(Color.RED);
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);
        register.setBorderPainted(false);

        panelButt.add(signIn);
        panelButt.add(register);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        frame.add(panelButt, gbc);

        frame.setTitle("Login");
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }



    public void addActionEvent() {
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = username_Field.getText();
            String pword = new String(passField.getPassword());
            DatabaseConnector dbConnector = DatabaseConnector.getInstance();
            boolean isValidUser = dbConnector.loginUser(username, pword);

            if (isValidUser) {
                JOptionPane.showMessageDialog(login, "Login successful!");
                frame.setVisible(false);
                Homepage exHomepage = new Homepage();
                exHomepage.config();
            } else {
                JOptionPane.showMessageDialog(login, "Invalid email or password.");
            }
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
               frame.setVisible(false);
            registration exRegistration = new registration();
            exRegistration.start();
            }
        });
    }

    public void start() {
        config();
        addActionEvent();
        frame.setVisible(true);
    }

   

}
