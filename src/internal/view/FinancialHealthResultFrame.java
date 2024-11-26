package internal.view;

import javax.swing.*;
import java.awt.*;

public class FinancialHealthResultFrame extends JFrame {

    public FinancialHealthResultFrame(String resultDetails) {
        // Set up frame properties
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Create a header panel with title label
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel Blue background
        JLabel headerLabel = new JLabel("Financial Health Analysis Summary");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);

        // Create a text area to display the results
        JTextArea resultTextArea = new JTextArea(resultDetails);
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setEditable(false);
        resultTextArea.setMargin(new Insets(10, 10, 10, 10));
        resultTextArea.setBackground(new Color(245, 245, 245));

        // Add scroll pane to make the text area scrollable
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add a button panel with close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.setBackground(new Color(255, 69, 0));
        closeButton.setForeground(Color.BLACK);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(closeButton);

        // Add components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
    }
}
