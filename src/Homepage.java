import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Homepage extends JFrame {
    JPanel panel = new JPanel(new FlowLayout());
    JPanel Tasks = new JPanel(new GridBagLayout());
    JPanel Timetable = new JPanel(new GridBagLayout());
    JPanel final_Panel = new JPanel(new GridBagLayout());

    GridBagConstraints syConstraints = new GridBagConstraints();
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbc_tt = new GridBagConstraints();
    JButton home = new JButton("Home");
    JButton student = new JButton("Student");
    JButton grades = new JButton("Grades");
    JButton  dashboard = new JButton("Dashboard");
    JButton[] buttons = {home,student,grades,dashboard};


    JLabel Do_hw = new JLabel("Do HomeWork");
    JLabel ReadforCats =  new JLabel("Read for CATS");
    JLabel goTotheGym = new JLabel("Go the gym");
    JLabel TT = new JLabel("Timetable");
    JLabel[] labels = {Do_hw,ReadforCats,goTotheGym};
    JRadioButton task1 = new JRadioButton();
    JRadioButton task2 = new JRadioButton();
    JRadioButton task3 = new JRadioButton();


    JRadioButton[] radioButtons = {task1,task2,task3};

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
int screenWidth = screenSize.width;
int screenHeight = screenSize.height;


    public void config(){
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc_tt.gridx = 0;
        gbc_tt.gridy= 0;
        syConstraints.gridx = 0;  
        syConstraints.gridy =0;  
        gbc.insets = new Insets(15, 15, 15, 35);
        
        setLayout(new FlowLayout());
       

        setSize(new Dimension(screenWidth, screenHeight));
        panel.setSize(new Dimension(getWidth(),50));
        for (JButton button : buttons) {
            button.setBackground(new Color(59, 89, 182)); // Facebook blue RGB
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
            button.setPreferredSize(new Dimension(getWidth()/5,40));
            panel.add(button);
        
        }
        for (JLabel label : labels) {
            Tasks.add(label,gbc);
            gbc.gridy++;
        }
        gbc.gridx++;
        gbc.gridy =0;
       Tasks.setSize(200, screenHeight);
        for (JRadioButton radioButton : radioButtons) {
            Tasks.add(radioButton,gbc);
            gbc.gridy++;
        }
        final_Panel.add(panel,syConstraints);


        syConstraints.gridy++;
        syConstraints.anchor = GridBagConstraints.EAST;
        syConstraints.gridx++;
        final_Panel.add(Tasks,syConstraints);

        syConstraints.gridy++;
        syConstraints.gridx--;
        syConstraints.anchor = GridBagConstraints.CENTER;
        Timetable.setPreferredSize(new Dimension(800, 300));
        Timetable.add(TT,gbc_tt);
        Timetable.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 10,true));
        final_Panel.add(Timetable,syConstraints);
       
        add(final_Panel);
        setVisible(true);
    }

}
