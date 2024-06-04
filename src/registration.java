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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class registration extends JFrame {

    // initializing all the variables
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints sysConstraints = new GridBagConstraints();
    JLabel welcome = new JLabel("Become a Muscle Flex Member!");
    JLabel emailLabel = new JLabel("Email Address: ");
    JLabel pwordLabel = new JLabel("Password: ");
    JLabel repwordLabel = new JLabel("Re-type Password: ");
    JLabel ageLabel = new JLabel("Age: ");
    JLabel weightLabel = new JLabel("Weight: ");

    JTextField emailField = new JTextField(20);
    JPasswordField pwordField = new JPasswordField(20);
    JPasswordField repwordField = new JPasswordField(20);
    JTextField ageField = new JTextField(20);
    JTextField weightField = new JTextField(20);

    JButton submitButton = new JButton("Submit");

    JLabel[] labels = { emailLabel, pwordLabel, repwordLabel, ageLabel, weightLabel };
    JTextField[] fields = { emailField, pwordField, repwordField, ageField, weightField };

    void config() {
        // Start by setting the layout of the frame
         setLayout(new GridBagLayout());
         setSize(new Dimension(700, 500));
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         getContentPane().setBackground(Color.BLACK);  // Background color of the frame
 
         panel.setBackground(Color.DARK_GRAY);  // Background color of the panel
        // Adding the welcome text

        // Styling the welcome text and Adding it to the frame
        welcome.setFont(new Font("SansSerif", Font.BOLD, 28));
        welcome.setForeground(Color.RED);
        sysConstraints.gridx = 0;
        sysConstraints.gridy = 0;
        sysConstraints.gridwidth = 2;
        sysConstraints.insets = new Insets(10, 0, 20, 0);
        add(welcome, sysConstraints);

        sysConstraints.gridwidth++;
        sysConstraints.gridy++;

        // Adding labels and text fields to the panel
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            labels[i].setFont(new Font("Verdana", Font.BOLD, 18));
            labels[i].setForeground(Color.WHITE);  // Text color of labels
            panel.add(labels[i], gbc);

            gbc.gridx = 1;
            fields[i].setFont(new Font("Arial", Font.PLAIN, 18));
            fields[i].setBackground(Color.LIGHT_GRAY);  // Background color of text fields
            fields[i].setForeground(Color.BLACK);  // Text color of text fields
            panel.add(fields[i], gbc);
        }


        sysConstraints.gridy++;
        sysConstraints.insets = new Insets(20, 0, 10, 0);
        add(panel, sysConstraints);
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
                String email = emailField.getText();
                String password = new String(pwordField.getPassword());
                String rePassword = new String(repwordField.getPassword());
                int age = Integer.parseInt(ageField.getText());
                float weight = Float.parseFloat(weightField.getText());

                if (password.equals(rePassword)) {
                    DatabaseConnector exConnector = new DatabaseConnector();
                   boolean registered =  exConnector.registerUser(email, password, age, weight);
                   if (registered) {
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    setVisible(false);
                    login exLogin = new login();
                    exLogin.start();
                   }else{
                    JOptionPane.showMessageDialog(null, "An error occured!");
                   }
                  
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                }
            }
        });





        add(submitButton, sysConstraints);
    }

    void start() {
        config();
        setVisible(true);
    }

}
