import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class registration  {

    // initializing all the variables
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints sysConstraints = new GridBagConstraints();

    JLabel welcome = new JLabel("Become a Muscle Flex Member!");
    JLabel userlabel = new JLabel("Username: ");
    JLabel emailLabel = new JLabel("Email Address: ");
    JLabel fullnamelabel = new JLabel("Full Name: ");
    JLabel pwordLabel = new JLabel("Password: ");
    JLabel repwordLabel = new JLabel("Re-type Password: ");
    JLabel ageLabel = new JLabel("Age: ");
    JLabel weightLabel = new JLabel("Weight(kg): ");
    JLabel heightLabel = new JLabel("Height (cm): ");

    JTextField username = new JTextField(20);
    JTextField emailField = new JTextField(20);
    JTextField fullname = new JTextField(20);
    JPasswordField pwordField = new JPasswordField(20);
    JPasswordField repwordField = new JPasswordField(20);
    JTextField ageField = new JTextField(20);
    JTextField weightField = new JTextField(20);
    JTextField height = new JTextField(20);
    JComboBox gender = new JComboBox<>(new String[] {
            "Male", "Female"
    });

    JButton submitButton = new JButton("Submit");

    JLabel[] labels = { userlabel, emailLabel, fullnamelabel, pwordLabel, repwordLabel, ageLabel, weightLabel,
            heightLabel };
    JTextField[] fields = { username, emailField, fullname, pwordField, repwordField, ageField, weightField, height };
JFrame frame = new JFrame();
    void config() {
        // Start by setting the layout of the frame
        frame.setLayout(new GridBagLayout());
        frame.setSize(new Dimension(700, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK); // Background color of the frame

        panel.setBackground(Color.DARK_GRAY); // Background color of the panel
        // Adding the welcome text

        // Styling the welcome text and Adding it to the frame
        welcome.setFont(new Font("SansSerif", Font.BOLD, 28));
        welcome.setForeground(Color.RED);
        sysConstraints.gridx = 0;
        sysConstraints.gridy = 0;
        sysConstraints.gridwidth = 2;
        sysConstraints.insets = new Insets(10, 0, 20, 0);
       frame.add(welcome, sysConstraints);

        sysConstraints.gridwidth++;
        sysConstraints.gridy++;

        // Adding labels and text fields to the panel
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            labels[i].setFont(new Font("Verdana", Font.BOLD, 18));
            labels[i].setForeground(Color.WHITE); // Text color of labels
            panel.add(labels[i], gbc);

            gbc.gridx = 1;
            fields[i].setFont(new Font("Arial", Font.PLAIN, 18));
            fields[i].setBackground(Color.LIGHT_GRAY); // Background color of text fields
            fields[i].setForeground(Color.BLACK); // Text color of text fields
            panel.add(fields[i], gbc);
        }
        gbc.gridy++;
        gbc.gridx--;
        JLabel gendLabel = new JLabel("Gender: ");
        gendLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        gendLabel.setForeground(Color.WHITE);
        panel.add(gendLabel, gbc);
        gbc.gridx++;
        panel.add(gender, gbc);

        sysConstraints.gridy++;
        sysConstraints.insets = new Insets(20, 0, 10, 0);
       frame.add(panel, sysConstraints);
        sysConstraints.gridy++;

        // Styling the submit button
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitButton.setBackground(Color.RED);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        sysConstraints.insets = new Insets(10, 0, 10, 0);
        sysConstraints.gridwidth = panel.getWidth();
        sysConstraints.fill = GridBagConstraints.HORIZONTAL;
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp_User = username.getText();
                String email = emailField.getText();
                String temp_name = fullname.getText();
                String password = new String(pwordField.getPassword());
                String rePassword = new String(repwordField.getPassword());
                int age = Integer.parseInt(ageField.getText());
                float weight = Float.parseFloat(weightField.getText());
                float temp_height = Float.parseFloat(height.getText());

                if (password.equals(rePassword)) {
                    DatabaseConnector dbConnector = DatabaseConnector.getInstance();
                    boolean registered = dbConnector.registerUser(temp_User,email, temp_name,password, age, weight,temp_height,gender.getSelectedItem().toString());

                    if (registered) {
                        JOptionPane.showMessageDialog(null, "Registration successful!");
                        frame.setVisible(false);
                        login exLogin = new login();
                        exLogin.start();
                    } else {
                        JOptionPane.showMessageDialog(null, "An error occured!");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                }
            }
        });

        frame.add(submitButton, sysConstraints);
    }

    void start() {
        config();
        frame.setVisible(true);
    }

}
