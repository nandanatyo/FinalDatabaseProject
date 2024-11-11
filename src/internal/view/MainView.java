package internal.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JTextField subsectorField;
    private JButton searchButton;
    private JTextArea resultArea;

    public MainView() {
        // Set up frame properties
        setTitle("Company Search GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Subsector:"));
        subsectorField = new JTextField(15);
        inputPanel.add(subsectorField);

        searchButton = new JButton("Search");
        inputPanel.add(searchButton);

        // Result Display Area
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    public String getSubsectorInput() {
        return subsectorField.getText().trim();
    }

    public void setResults(String results) {
        resultArea.setText(results);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
}
