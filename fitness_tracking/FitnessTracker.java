import java.io.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FitnessTracker {

    // Core Classes
    static class User implements Serializable  {
        private String name;
        private int age;
        private double weight;
        private double height;

        public User(String name, int age, double weight, double height) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }
        public double getHeight() { return height; }
        public void setHeight(double height) { this.height = height; }

        public double calculateBMI() {
            return weight / Math.pow(height / 100, 2);
        }

        public String getBMICategory() {
            double bmi = calculateBMI();
            if (bmi < 18.5) return "Underweight";
            if (bmi < 25) return "Normal";
            if (bmi < 30) return "Overweight";
            return "Obese";
        }

        @Override
        public String toString() {
            return String.format("Name: %s, Age: %d, Weight: %.1f kg, Height: %.1f cm, BMI: %.1f (%s)",
                name, age, weight, height, calculateBMI(), getBMICategory());
        }
    }

    static class Activity {
        private String type;
        private String category;
        private int duration;
        private double caloriesBurned;
        private LocalDate date;

        public Activity(String type, String category, int duration, double caloriesBurned) {
            this.type = type;
            this.category = category;
            this.duration = duration;
            this.caloriesBurned = caloriesBurned;
            this.date = LocalDate.now();
        }

        // Getters
        public String getType() { return type; }
        public String getCategory() { return category; }
        public int getDuration() { return duration; }
        public double getCaloriesBurned() { return caloriesBurned; }
        public LocalDate getDate() { return date; }

        @Override
        public String toString() {
            return String.format("%s (%s) - %d minutes - %.1f calories burned - %s",
                type, category, duration, caloriesBurned,
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
    }

    static class Goal {
        private String goalType;
        private double target;
        private double progress;
        private LocalDate startDate;
        private LocalDate endDate;

        public Goal(String goalType, double target, int durationDays) {
            this.goalType = goalType;
            this.target = target;
            this.progress = 0;
            this.startDate = LocalDate.now();
            this.endDate = startDate.plusDays(durationDays);
        }

        public void updateProgress(double value) {
            this.progress += value;
        }

        public boolean isAchieved() {
            return progress >= target;
        }

        public double getProgress() { return progress; }
        public double getTarget() { return target; }
        public String getGoalType() { return goalType; }
        public LocalDate getEndDate() { return endDate; }

        public int getProgressPercentage() {
            return (int) ((progress / target) * 100);
        }

        @Override
        public String toString() {
            return String.format("%s: %.1f/%.1f %s (%d%%) - Due: %s %s",
                goalType, progress, target,
                goalType.toLowerCase(),
                getProgressPercentage(),
                endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                isAchieved() ? "✓" : "");
        }
    }

    // Core Data
    private static User user;
    private static List<Activity> activities = new ArrayList<>();
    private static List<Goal> goals = new ArrayList<>();
    private static final String DATABASE_FILE = "fitness_tracker_data.txt";

    // GUI Components
    private static JFrame frame;
    private static JTextArea outputArea;
    private static JLabel statusLabel;
    private static JPanel userInfoPanel;
    private static JPanel goalsPanel;
    private static JTable activityTable;
    private static DefaultTableModel tableModel;

    // Activity Categories
    private static final Map<String, String[]> ACTIVITY_CATEGORIES = new HashMap<>() {{
        put("Cardio", new String[]{"Running", "Walking", "Cycling", "Swimming"});
        put("Strength", new String[]{"Weight Training", "Bodyweight Exercises", "Resistance Training"});
        put("Flexibility", new String[]{"Yoga", "Stretching", "Pilates"});
        put("Sports", new String[]{"Basketball", "Tennis", "Soccer", "Other Sports"});
    }};

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        loadFromFile();
        setupGUI();
    }

    private static void setupGUI() {
        frame = new JFrame("Fitness Tracker Pro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout(10, 10));

        // Create main panels
        JPanel sidePanel = createSidePanel();
        JPanel mainPanel = createMainPanel();
        
        // Add padding around the main components
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        
        // Status bar
        statusLabel = new JLabel(" Welcome to Fitness Tracker Pro");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel.setPreferredSize(new Dimension(frame.getWidth(), 25));
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidePanel.setPreferredSize(new Dimension(200, frame.getHeight()));

        // Create sections
        addSection(sidePanel, "Profile", new String[]{"Set User Profile"}, new String[]{"\u2630"});
        addSection(sidePanel, "Activities", new String[]{"Log Activity", "View Activities"}, new String[]{"\u270E", "\u2316"});
        addSection(sidePanel, "Goals", new String[]{"Set New Goal", "View Goals"}, new String[]{"\u2726", "\u2315"});
        addSection(sidePanel, "Reports", new String[]{"Weekly Report", "Export Data"}, new String[]{"\u2317", "\u2399"});
        
        sidePanel.add(Box.createVerticalGlue());
        
        // Exit button at bottom
        JButton exitButton = createStyledButton("Exit", "\u2717");
        exitButton.addActionListener(e -> {
            saveToFile();
            System.exit(0);
        });
        sidePanel.add(exitButton);

        return sidePanel;
    }

    private static void addSection(JPanel panel, String title, String[] buttonTexts, String[] symbols) {
        panel.add(new JSeparator());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        for (int i = 0; i < buttonTexts.length; i++) {
            JButton button = createStyledButton(buttonTexts[i], symbols[i]);
            setupButtonAction(button, buttonTexts[i]);
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
    }

    private static void setupButtonAction(JButton button, String action) {
        switch (action) {
            case "Set User Profile": button.addActionListener(e -> setUserProfile()); break;
            case "Log Activity": button.addActionListener(e -> logActivity()); break;
            case "View Activities": button.addActionListener(e -> viewActivities()); break;
            case "Set New Goal": button.addActionListener(e -> setGoal()); break;
            case "View Goals": button.addActionListener(e -> viewGoals()); break;
            case "Weekly Report": button.addActionListener(e -> generateWeeklyReport()); break;
            case "Export Data": button.addActionListener(e -> exportData()); break;
        }
    }

    private static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        // Top panel for user info and goals
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        
        // User info panel
        userInfoPanel = new JPanel(new BorderLayout());
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("User Information"));
        updateUserInfoPanel();
        topPanel.add(userInfoPanel);

        // Goals panel
        goalsPanel = new JPanel(new BorderLayout());
        goalsPanel.setBorder(BorderFactory.createTitledBorder("Active Goals"));
        updateGoalsPanel();
        topPanel.add(goalsPanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Activity table
        String[] columnNames = {"Date", "Type", "Category", "Duration (min)", "Calories"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        activityTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(activityTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Activity Log"));
        
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    // Implementation of new methods...
    private static void setGoal() {
        if (user == null) {
            JOptionPane.showMessageDialog(frame, "Please set up a user profile first.");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        String[] goalTypes = {"Calories Burned", "Activity Duration"};
        JComboBox<String> typeCombo = new JComboBox<>(goalTypes);
        JTextField targetField = new JTextField();
        JTextField daysField = new JTextField();

        panel.add(new JLabel("Goal Type:"));
        panel.add(typeCombo);
        panel.add(new JLabel("Target Value:"));
        panel.add(targetField);
        panel.add(new JLabel("Duration (days):"));
        panel.add(daysField);

        int result = JOptionPane.showConfirmDialog(frame, panel, 
            "Set New Goal", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String goalType = (String) typeCombo.getSelectedItem();
                double target = Double.parseDouble(targetField.getText());
                int days = Integer.parseInt(daysField.getText());

                Goal goal = new Goal(goalType, target, days);
                goals.add(goal);
                updateGoalsPanel();
                statusLabel.setText(" New goal set successfully");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers.");
            }
        }
    }

    private static void updateGoalsPanel() {
        goalsPanel.removeAll();
        
        if (goals.isEmpty()) {
            goalsPanel.add(new JLabel("No active goals", SwingConstants.CENTER));
        } else {
            JPanel goalsGrid = new JPanel(new GridLayout(0, 1, 5, 5));
            
            for (Goal goal : goals) {
                JPanel goalPanel = new JPanel(new BorderLayout(5, 0));
                goalPanel.add(new JLabel(goal.toString()), BorderLayout.CENTER);
                
                JProgressBar progressBar = new JProgressBar(0, 100);
                progressBar.setValue(goal.getProgressPercentage());
                progressBar.setStringPainted(true);
                progressBar.setPreferredSize(new Dimension(150, 20));
                goalPanel.add(progressBar, BorderLayout.EAST);
                
                goalsGrid.add(goalPanel);
            }
            
            goalsPanel.add(new JScrollPane(goalsGrid));
        }
        
        goalsPanel.revalidate();
        goalsPanel.repaint();
    }


    private static void updateUserInfoPanel() {
        userInfoPanel.removeAll();
        if (user != null) {
            JLabel userLabel = new JLabel(user.toString());
            userLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            userLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            userInfoPanel.add(userLabel, BorderLayout.CENTER);
        } else {
            JLabel noUserLabel = new JLabel("No user profile set");
            noUserLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            noUserLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
            userInfoPanel.add(noUserLabel, BorderLayout.CENTER);
        }
        userInfoPanel.revalidate();
        userInfoPanel.repaint();
    }

    private static void setUserProfile() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField heightField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Weight (kg):"));
        panel.add(weightField);
        panel.add(new JLabel("Height (cm):"));
        panel.add(heightField);

        // Pre-fill fields if user exists
        if (user != null) {
            nameField.setText(user.getName());
            ageField.setText(String.valueOf(user.getAge()));
            weightField.setText(String.valueOf(user.getWeight()));
            heightField.setText(String.valueOf(user.getHeight()));
        }

        int result = JOptionPane.showConfirmDialog(frame, panel, 
            "User Profile", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());
                double height = Double.parseDouble(heightField.getText().trim());

                user = new User(name, age, weight, height);
                updateUserInfoPanel();
                statusLabel.setText(" User profile updated successfully");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, 
                    "Please enter valid numbers for age, weight, and height.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void logActivity() {
        if (user == null) {
            JOptionPane.showMessageDialog(frame, "Please set up a user profile first.");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        // Category selection
        JComboBox<String> categoryCombo = new JComboBox<>(ACTIVITY_CATEGORIES.keySet().toArray(new String[0]));
        JComboBox<String> typeCombo = new JComboBox<>();
        
        // Update activity types when category changes
        categoryCombo.addActionListener(e -> {
            String category = (String) categoryCombo.getSelectedItem();
            typeCombo.removeAllItems();
            for (String type : ACTIVITY_CATEGORIES.get(category)) {
                typeCombo.addItem(type);
            }
        });
        
        // Trigger initial population of activities
        categoryCombo.setSelectedIndex(0);
        
        JTextField durationField = new JTextField();

        panel.add(new JLabel("Category:"));
        panel.add(categoryCombo);
        panel.add(new JLabel("Activity Type:"));
        panel.add(typeCombo);
        panel.add(new JLabel("Duration (minutes):"));
        panel.add(durationField);

        int result = JOptionPane.showConfirmDialog(frame, panel,
            "Log Activity", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String category = (String) categoryCombo.getSelectedItem();
                String type = (String) typeCombo.getSelectedItem();
                int duration = Integer.parseInt(durationField.getText().trim());
                double caloriesBurned = calculateCalories(type, duration);

                Activity activity = new Activity(type, category, duration, caloriesBurned);
                activities.add(activity);
                
                // Update activity table
                updateActivityTable();
                
                // Update goals
                updateGoalsWithActivity(activity);
                
                statusLabel.setText(" Activity logged successfully");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for duration.");
            }
        }
    }

    private static void updateActivityTable() {
        tableModel.setRowCount(0);
        for (Activity activity : activities) {
            tableModel.addRow(new Object[]{
                activity.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                activity.getType(),
                activity.getCategory(),
                activity.getDuration(),
                String.format("%.1f", activity.getCaloriesBurned())
            });
        }
    }

    private static void updateGoalsWithActivity(Activity activity) {
        for (Goal goal : goals) {
            if (goal.getGoalType().equals("Calories Burned")) {
                goal.updateProgress(activity.getCaloriesBurned());
            } else if (goal.getGoalType().equals("Activity Duration")) {
                goal.updateProgress(activity.getDuration());
            }
        }
        updateGoalsPanel();
    }

    private static void viewActivities() {
        updateActivityTable();
        statusLabel.setText(" Viewing all activities");
    }

    private static void viewGoals() {
        if (goals.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No goals set yet.");
            return;
        }

        StringBuilder report = new StringBuilder("Current Goals:\n\n");
        for (Goal goal : goals) {
            report.append(goal.toString()).append("\n");
        }

        JTextArea textArea = new JTextArea(report.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(frame, scrollPane, "Goals Overview", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void generateWeeklyReport() {
        if (activities.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No activities logged yet.");
            return;
        }

        LocalDate now = LocalDate.now();
        LocalDate weekAgo = now.minusDays(7);

        List<Activity> weeklyActivities = activities.stream()
            .filter(a -> !a.getDate().isBefore(weekAgo) && !a.getDate().isAfter(now))
            .toList();

        Map<String, Double> categoryCalories = new HashMap<>();
        Map<String, Integer> categoryDurations = new HashMap<>();
        double totalCalories = 0;
        int totalDuration = 0;

        for (Activity activity : weeklyActivities) {
            String category = activity.getCategory();
            categoryCalories.merge(category, activity.getCaloriesBurned(), Double::sum);
            categoryDurations.merge(category, activity.getDuration(), Integer::sum);
            totalCalories += activity.getCaloriesBurned();
            totalDuration += activity.getDuration();
        }

        StringBuilder report = new StringBuilder();
        report.append("Weekly Activity Report\n");
        report.append("Period: ").append(weekAgo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
              .append(" to ").append(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
              .append("\n\n");
        
        report.append("Summary:\n");
        report.append("Total Activities: ").append(weeklyActivities.size()).append("\n");
        report.append("Total Duration: ").append(totalDuration).append(" minutes\n");
        report.append("Total Calories Burned: ").append(String.format("%.1f", totalCalories)).append("\n\n");

        report.append("By Category:\n");
        for (String category : categoryCalories.keySet()) {
            report.append(category).append(":\n");
            report.append("  Duration: ").append(categoryDurations.get(category)).append(" minutes\n");
            report.append("  Calories: ").append(String.format("%.1f", categoryCalories.get(category))).append("\n");
        }

        JTextArea textArea = new JTextArea(report.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(frame, scrollPane, "Weekly Report", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void exportData() {
        if (activities.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No data to export.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export Data");
        fileChooser.setSelectedFile(new File("fitness_export.csv"));

        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                // Write header
                writer.println("Date,Type,Category,Duration,Calories");

                // Write activities
                for (Activity activity : activities) {
                    writer.printf("%s,%s,%s,%d,%.1f%n",
                        activity.getDate(),
                        activity.getType(),
                        activity.getCategory(),
                        activity.getDuration(),
                        activity.getCaloriesBurned());
                }

                statusLabel.setText(" Data exported successfully");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error exporting data: " + e.getMessage());
            }
        }
    }

    private static double calculateCalories(String type, int duration) {
        // Enhanced calorie calculation based on activity type
        return switch (type) {
            case "Running" -> duration * 11.5;
            case "Walking" -> duration * 5.0;
            case "Cycling" -> duration * 7.5;
            case "Swimming" -> duration * 8.0;
            case "Weight Training" -> duration * 6.0;
            case "Yoga" -> duration * 4.0;
            case "Stretching" -> duration * 2.5;
            case "Pilates" -> duration * 4.5;
            case "Basketball" -> duration * 9.0;
            case "Tennis" -> duration * 8.0;
            case "Soccer" -> duration * 10.0;
            default -> duration * 5.0;
        };
    }

    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATABASE_FILE))) {
            // Save user
            oos.writeObject(user);

            // Save activities
            oos.writeObject(new ArrayList<>(activities));

            // Save goals
            oos.writeObject(new ArrayList<>(goals));

            statusLabel.setText(" Data saved successfully");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {
        File file = new File(DATABASE_FILE);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // Load user
            user = (User) ois.readObject();

            // Load activities
            activities = new ArrayList<>((List<Activity>) ois.readObject());

            // Load goals
            goals = new ArrayList<>((List<Goal>) ois.readObject());

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Error loading data: " + e.getMessage());
        }
    }

    private static JButton createStyledButton(String text, String symbol) {
        JButton button = new JButton(symbol + " " + text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setMargin(new Insets(5, 10, 5, 10));
        return button;
    }
}