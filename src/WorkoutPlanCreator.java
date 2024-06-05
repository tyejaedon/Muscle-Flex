import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.swing.event.ChangeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WorkoutPlanCreator  {
    // Components for the form
    JComboBox exerciseField = new JComboBox<>(new String[]{
        "Squats", "Lunges", "Leg Press", "Deadlifts", "Leg Curls", "Calf Raises"
    });
    JTextArea exerciseList = new JTextArea(5, 20);
    JComboBox<String> dayComboBox = new JComboBox<>(
            new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" });
    JComboBox<String> TargetMuscleGrp = new JComboBox<>(
            new String[] { "Legs", "Arms", "Chest", "Back", "Full Body", "Push", "Pull" ,"Abs"});
   
    SpinnerDateModel model = new SpinnerDateModel();
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    JSpinner dateSpinner = new JSpinner(model);
    JTextField targetField = new JTextField(20);
    JTextField plan_name = new JTextField(20);
    JButton addButton = new JButton("Add Exercise");
    JButton saveButton = new JButton("Save Plan");
    JPanel panel = new JPanel();

    public WorkoutPlanCreator() {
   
        panel.setSize(600, 400);
        panel.setLayout(new BorderLayout());

        // Top panel with title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(59, 89, 182));
        JLabel titleLabel = new JLabel("Create Your Workout Plan");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Main form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
       
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Exercise:"), gbc);
        gbc.gridx++;
        formPanel.add(exerciseField, gbc);

       gbc.gridx--;
        gbc.gridy++;
        formPanel.add(new JLabel("Day:"), gbc);
        gbc.gridx++;
        formPanel.add(dayComboBox, gbc);

        gbc.gridx--;
        gbc.gridy++;
        formPanel.add(new JLabel("Muscle Group:"), gbc);
        gbc.gridx++;
        formPanel.add(TargetMuscleGrp, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.gridwidth = 2;
        formPanel.add(addButton, gbc);

        gbc.gridwidth = 1;

    
        gbc.gridy++;
        formPanel.add(new JLabel("End Date:"), gbc);
        gbc.gridx++;
        formPanel.add(dateSpinner, gbc);
        gbc.gridwidth = 1;
        gbc.gridx--;
        gbc.gridy++;
        formPanel.add(new JLabel("Target:"), gbc);
        gbc.gridx++;
        formPanel.add(targetField, gbc);

        gbc.gridx--;
        gbc.gridy++;
        formPanel.add(new JLabel("Plan Name:"), gbc);
        gbc.gridx++;
        formPanel.add(plan_name, gbc);

       gbc.gridx--;
        gbc.gridy++;
        exerciseList.setEditable(false);
        formPanel.add(new JLabel("Exercises List:"), gbc);
        gbc.gridx++;
        formPanel.add(new JScrollPane(exerciseList), gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.gridwidth = 2;
        formPanel.add(saveButton, gbc);

        // Styling buttons
        addButton.setBackground(new Color(59, 89, 182));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Tahoma", Font.BOLD, 12));

        saveButton.setBackground(new Color(59, 89, 182));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 12));

        
       

        // Adding panels to the Jpanel
       panel.add(titlePanel, BorderLayout.NORTH);
       panel.add(formPanel, BorderLayout.CENTER);

        listeners();
        
        panel.setVisible(true);
    }

    public JPanel getPanel() {
        WorkoutPlanCreator panel_new = new WorkoutPlanCreator();
        return panel_new.panel;
    }
    private void listeners(){
        TargetMuscleGrp.addActionListener(e -> exercises());
       addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addExercise();
            
        }
       });
       saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
        String [][] sample_exercises = savePlan();
        String temp_planname = plan_name.getText();
        String temp_target = targetField.getText();
            Date temp_date = (Date) dateSpinner.getValue();
            Boolean success = false;
            if ( temp_planname != null&& temp_target!= null && temp_date != null && sample_exercises != null) {
                DatabaseConnector dbConnector = DatabaseConnector.getInstance();
                success = dbConnector.addPlan(sample_exercises, temp_planname, temp_target, temp_date);
            }
           
            if (success) {
                JOptionPane.showMessageDialog(saveButton,"Success");
                System.out.println("yea");
            }
            else{
                JOptionPane.showMessageDialog(saveButton,"Failure");
                System.out.println("NOOO");
            }
        } catch ( Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }      
        }
       });
      
    }



    @SuppressWarnings("unchecked")
    private void exercises(){
    
        String selectedSplit = (String) TargetMuscleGrp.getSelectedItem();
        switch (selectedSplit) {
            case "Legs":
                exerciseField.setModel(new DefaultComboBoxModel<>(new String[]{
                        "Squats", "Lunges", "Leg Press", "Deadlifts", "Leg Curls", "Calf Raises"
                }));
                break;
            case "Chest":
                exerciseField.setModel(new DefaultComboBoxModel<>(new String[]{
                        "Bench Press", "Incline Dumbbell Press", "Push-Ups", "Chest Flys", "Cable Crossovers", "Dips"
                }));
                break;
            case "Back":
                exerciseField.setModel(new DefaultComboBoxModel<>(new String[]{
                        "Pull-Ups", "Bent Over Rows", "Lat Pulldowns", "Deadlifts", "T-Bar Rows", "Seated Cable Rows"
                }));
                break;
            case "Arms":
                exerciseField.setModel(new DefaultComboBoxModel<>(new String[]{
                        "Bicep Curls", "Tricep Dips", "Hammer Curls", "Tricep Extensions", "Concentration Curls", "Skull Crushers"
                }));
                break;
            case "Push":
                exerciseField.setModel(new DefaultComboBoxModel<>(new String[]{
                        "Bench Press", "Overhead Press", "Push-Ups", "Dips", "Incline Press", "Tricep Pushdowns"
                }));
                break;
            case "Pull":
                exerciseField.setModel(new DefaultComboBoxModel<>(new String[]{
                        "Pull-Ups", "Bent Over Rows", "Lat Pulldowns", "Face Pulls", "Deadlifts", "Dumbbell Rows"
                }));
                break;
            case "Abs":
                exerciseField.setModel(new DefaultComboBoxModel<>(new String[]{
                        "Crunches", "Leg Raises", "Russian Twists", "Planks", "Bicycle Crunches", "Mountain Climbers"
                }));
                break;
            default:
                exerciseField.setModel(new DefaultComboBoxModel<>(new String[]{
                        "Squats", "Lunges", "Leg Press", "Deadlifts", "Leg Curls", "Calf Raises",
                        "Pull-Ups", "Bent Over Rows", "Lat Pulldowns", "Deadlifts", "T-Bar Rows", "Seated Cable Rows",
                        "Bicep Curls", "Tricep Dips", "Hammer Curls", "Tricep Extensions", "Concentration Curls", "Skull Crushers",
                        "Bench Press", "Incline Dumbbell Press", "Push-Ups", "Chest Flys", "Cable Crossovers", "Dips",
                        "Crunches", "Leg Raises", "Russian Twists", "Planks", "Bicycle Crunches", "Mountain Climbers",
                        "Overhead Press", "Face Pulls", "Dumbbell Rows", "Tricep Pushdowns"
                }));
                break;
            }
    }
       Map<String, ArrayList<String>> exerciseMap = new HashMap<>();
      

    private void addExercise() {
        
        String selectedExercise = (String) exerciseField.getSelectedItem();
        String selectedDay = (String) dayComboBox.getSelectedItem();
    

        
        
        if (!exerciseMap.containsKey(selectedDay)) {
           exerciseMap.put(selectedDay, new ArrayList<String>());
        }
        exerciseMap.get(selectedDay).add(selectedExercise);

       
        updateExerciseList();
    }
    private void updateExerciseList() {
      
        StringBuilder displayText = new StringBuilder();
        for (String day : exerciseMap.keySet()) {
            displayText.append(day).append(": ");
            displayText.append(exerciseMap.get(day)).append("\n");
        }
        exerciseList.setText(displayText.toString());
    }
    

        private String[][] savePlan() {
            String[][] savedexercises = new String[exerciseMap.size()][];
                for (String string : exerciseMap.keySet()) {
                    ArrayList<String> value = exerciseMap.get(string);

                    for (int i = 0; i < savedexercises.length; i++) {
                        savedexercises[i] = new String[value.size()+1];
                        savedexercises[i][0] = string;
                    
                        for (int j = 1; j < value.size(); j++) {
                        
                            savedexercises[i][j] = value.get(j);
                            
                        }
                    }
                }
              

            return savedexercises;
            }
        }
    


