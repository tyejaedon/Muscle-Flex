import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WorkoutPlanCreator extends JPanel {
    // Components for the form
    JComboBox exerciseField = new JComboBox<>(new String[]{
        "Squats", "Lunges", "Leg Press", "Deadlifts", "Leg Curls", "Calf Raises"
    });
    JTextArea exerciseList = new JTextArea(5, 20);
    JComboBox<String> dayComboBox = new JComboBox<>(
            new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" });
    JComboBox<String> TargetMuscleGrp = new JComboBox<>(
            new String[] { "Legs", "Arms", "Chest", "Back", "Full Body", "Push", "Pull" ,"Abs"});
    JTextField durationField = new JTextField(20);
    JTextField targetField = new JTextField(20);
    JButton addButton = new JButton("Add Exercise");
    JButton saveButton = new JButton("Save Plan");

    public WorkoutPlanCreator() {
        setSize(600, 400);
        setLayout(new BorderLayout());

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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Exercise:"), gbc);
        gbc.gridx = 1;
        formPanel.add(exerciseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Day:"), gbc);
        gbc.gridx = 1;
        formPanel.add(dayComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Muscle Group:"), gbc);
        gbc.gridx = 1;
        formPanel.add(TargetMuscleGrp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Duration (weeks):"), gbc);
        gbc.gridx = 1;
        formPanel.add(durationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Target:"), gbc);
        gbc.gridx = 1;
        formPanel.add(targetField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Exercises List:"), gbc);
        gbc.gridx = 1;
        formPanel.add(new JScrollPane(exerciseList), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
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
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        listeners();

        setVisible(true);
    }

    public WorkoutPlanCreator getPanel() {
        WorkoutPlanCreator panel = new WorkoutPlanCreator();
        return panel;
    }
    private void listeners(){
        TargetMuscleGrp.addActionListener(e -> exercises());
       addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addExercise();
            
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
       Map<String, StringBuilder> exerciseMap = new HashMap<>();

    private void addExercise() {
        
        String selectedExercise = (String) exerciseField.getSelectedItem();
        String selectedDay = (String) dayComboBox.getSelectedItem();
        
        if (!exerciseMap.containsKey(selectedDay)) {
           exerciseMap.put(selectedDay, new StringBuilder());
        }
        exerciseMap.get(selectedDay).append(selectedExercise).append("\t");

        if (selectedExercise != null && !selectedExercise.isEmpty()) {
            exerciseList.append(selectedDay + ": " + selectedExercise + "\n");
        }
        updateExerciseList();
    }
    private void updateExerciseList() {
        StringBuilder displayText = new StringBuilder();
        for (String day : exerciseMap.keySet()) {
            displayText.append(day).append(":\n");
            displayText.append(exerciseMap.get(day)).append("\n");
        }
        exerciseList.setText(displayText.toString());
    }
    

    private void savePlan() {
        String day = (String) dayComboBox.getSelectedItem();
        String workoutSplit = (String) TargetMuscleGrp.getSelectedItem();
        String duration = durationField.getText();
        String target = targetField.getText();
        String exercises = exerciseList.getText();

        if (duration.isEmpty() || target.isEmpty() || exercises.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String message = String.format(
                    "Workout Plan Saved!\n\nDay: %s\nWorkout Split: %s\nDuration: %s weeks\nTarget: %s\nExercises:\n%s",
                    day, workoutSplit, duration, target, exercises);
            JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
