import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Homepage extends JFrame {
    JPanel topPanel = new JPanel(new FlowLayout());
    JPanel mainPanel = new JPanel(new GridBagLayout());
    JPanel tasksPanel = new JPanel(new GridBagLayout());
    JPanel ChangingPanel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbcTT = new GridBagConstraints();
    JButton workoutButton = new JButton("Workout");
    JButton userProfileButton = new JButton("User Profile");
    JButton dashboardButton = new JButton("Dashboard");
    JButton[] buttons = { workoutButton, userProfileButton, dashboardButton };
    JButton createPlan = new JButton("Plan Creator");
    JLabel taskLabel1 = new JLabel("Lose 12KG - By 12th June");
    JLabel taskLabel2 = new JLabel("Be on the top of the Leaderboard");
    JLabel taskLabel3 = new JLabel("Bench 70kg 1 rep");
    JLabel timetableLabel = new JLabel("Timetable");
    JLabel[] labels = { taskLabel1, taskLabel2, taskLabel3 };

    JRadioButton task1Radio = new JRadioButton();
    JRadioButton task2Radio = new JRadioButton();
    JRadioButton task3Radio = new JRadioButton();
    JRadioButton[] radioButtons = { task1Radio, task2Radio, task3Radio };

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    GridBagConstraints syConstraints = new GridBagConstraints();

    public Homepage() {
        setTitle("Responsive Homepage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(screenWidth, screenHeight));
        setLayout(new BorderLayout());

        config();
    }

    public void config() {
        // Top panel configuration
        topPanel.setPreferredSize(new Dimension(screenWidth, 50));
        int buttonWidth = (topPanel.getPreferredSize().width / 3) - 20;
        int buttonHeight = 40;
        for (JButton button : buttons) {
            button.setBackground(new Color(59, 89, 182));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Tahoma", Font.BOLD, 12));
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            topPanel.add(button);
        }

        // Tasks panel configuration
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;
        for (int i = 0; i < radioButtons.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            tasksPanel.add(radioButtons[i], gbc);
            gbc.gridx = 1;
            tasksPanel.add(labels[i], gbc);
        }

        // Timetable panel configuration
        gbcTT.gridx = 0;
        gbcTT.gridy = 0;
        gbcTT.insets = new Insets(20, 0, 0, 0);
        int timetableWidth = buttonWidth * 2 + 40; // Width of two buttons plus some padding
        ChangingPanel.setPreferredSize(new Dimension(timetableWidth, 500));

        ChangingPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));

        // Main panel configuration
        syConstraints.insets = new Insets(15, 15, 15, 15);
        syConstraints.gridx = 0;
        syConstraints.gridy = 0;

        createPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkoutPlanCreator exCreator = new WorkoutPlanCreator();

                // Set GridBagConstraint for exPanel to fill the entire ChangingPanel
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.gridx = 0;
                c.gridy = 0;
                ChangingPanel.add(exCreator.getPanel(),c);
                // Repaint the ChangingPanel to show the added component
                ChangingPanel.revalidate();
                ChangingPanel.repaint();
            }
        });
        mainPanel.add(createPlan, syConstraints);
        syConstraints.gridy++;

        mainPanel.add(ChangingPanel, syConstraints);
        syConstraints.gridx++;
        mainPanel.add(tasksPanel, syConstraints);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Homepage::new);
    }
}
