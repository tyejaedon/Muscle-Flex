import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login extends JFrame implements ActionListener {

    JPanel panel_field = new JPanel();
    JPanel panel_label = new JPanel();
    JPanel panel_butt = new JPanel();


    JLabel login = new JLabel("Login:");
    JLabel password = new JLabel("Password:");
    JLabel welcome = new JLabel("Welcome to School AI");

    JTextField login_Field = new JTextField();
    JTextField pass_Field = new JTextField();


    GridBagConstraints gbc = new GridBagConstraints();
    JButton sign_in = new JButton("Login");
    JButton register = new JButton("Register");

    public void config() {
        gbc.insets = new Insets(15, 15, 15, 15);
        setSize(500,500);
        setMaximumSize(new Dimension(500,580));
        setLayout(new GridBagLayout());

        //Maxes a box layout for the panels that are aligned vertically
        panel_field.setLayout(new BoxLayout(panel_field, BoxLayout.Y_AXIS));
        panel_label.setLayout(new BoxLayout(panel_label, BoxLayout.Y_AXIS));
        JLabel[] labels = {login,password};
        JTextField[] fields = {pass_Field,login_Field};
    
        gbc.gridx = 0;
        gbc.gridy = 0;
  

        // Styling for welcome:
        welcome.setFont(new Font("Arial", ALLBITS, 30));
        welcome.setPreferredSize(new Dimension(400,30));
        
        welcome.setBackground(new Color(126,233,166));
        add(welcome,gbc);
        gbc.gridy++;


        
        // styling of the labels 

        for (JLabel label : labels) {

            label.setBounds(EXIT_ON_CLOSE, ABORT, 200, 30);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(new Font(getName(), ABORT, 20));
            
           panel_label.add(label);
           // creates an invisible elemenent for spacing 
           panel_label.add(Box.createRigidArea(new Dimension(5, 10)));
           

        }
        //Styling of the textfields 

        for (JTextField field : fields) {
            
            field.setPreferredSize(new Dimension(200,30));
            
            field.setHorizontalAlignment(JTextField.CENTER);
            panel_field.add(field);
            // INVISIBLE ELEMENT FOR SPACING
            panel_field.add(Box.createRigidArea(new Dimension(5, 10)));
        }

        
      
        // adding to the frame with gbc contrainsts 
    add(panel_label,gbc);
    gbc.gridx++;
    add(panel_field,gbc);
    panel_butt.add(sign_in);
    panel_butt.add(register);
    gbc.gridy++;
    add(panel_butt,gbc);
   


        setTitle("Login");
        
        
    
    }
  public void addActionEvent(){
    sign_in.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        setVisible(false);
        Homepage exHomepage = new Homepage();
        exHomepage.config();
        
        }
    });
  }
    public void start(){
        config();
        addActionEvent();
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}