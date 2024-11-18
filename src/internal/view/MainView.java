package internal.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class MainView extends JFrame {
    private JTextField companyNameField;
    private JButton employeeButton;
    private JButton financeDataButton;
    private JButton incomeProfitButton;
    private JButton marketingResearchButton;
    private JButton productivityButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public MainView() {
        // Set up frame properties
        setTitle("Company Data Search GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Input Panel for company name
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        inputPanel.add(new JLabel("Company Name:"));
        companyNameField = new JTextField(20);
        inputPanel.add(companyNameField);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 5, 5));

        employeeButton = new JButton("Employee");
        financeDataButton = new JButton("Finance Data");
        incomeProfitButton = new JButton("Income & Profit");
        marketingResearchButton = new JButton("Marketing Research");
        productivityButton = new JButton("Productivity");

        buttonPanel.add(employeeButton);
        buttonPanel.add(financeDataButton);
        buttonPanel.add(incomeProfitButton);
        buttonPanel.add(marketingResearchButton);
        buttonPanel.add(productivityButton);

        // Result Table Setup
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Results"));
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    public String getCompanyNameInput() {
        return companyNameField.getText().trim();
    }

    public void setTableData(Object[][] data, String[] columnNames) {
        tableModel.setDataVector(data, columnNames);
    }

    public void addListeners(ActionListener employeeListener,
                             ActionListener financeDataListener,
                             ActionListener incomeProfitListener,
                             ActionListener marketingResearchListener,
                             ActionListener productivityListener) {
        employeeButton.addActionListener(employeeListener);
        financeDataButton.addActionListener(financeDataListener);
        incomeProfitButton.addActionListener(incomeProfitListener);
        marketingResearchButton.addActionListener(marketingResearchListener);
        productivityButton.addActionListener(productivityListener);
    }
}
