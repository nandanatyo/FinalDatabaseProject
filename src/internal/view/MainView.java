package internal.view;

import internal.controller.*;
import internal.model.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainView extends JFrame {
    private JTextField companyNameField;
    private JButton employeeButton;
    private JButton financeDataButton;
    private JButton incomeProfitButton;
    private JButton marketingResearchButton;
    private JButton productivityButton;
    private JButton companyButton;
    private JButton financialHealthButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JPanel resultPanel;


    private EmployeeController ec;
    private FinanceDataController fc;
    private IncomeAndProfitDataController ipc;
    private MarketingResearchController mrc;
    private ProductivityController pc;
    private CompanyController cc;

    public MainView(EmployeeController ec, FinanceDataController fc, IncomeAndProfitDataController ipc,
                    MarketingResearchController mrc, ProductivityController pc) {
        this.ec = ec;
        this.fc = fc;
        this.ipc = ipc;
        this.mrc = mrc;
        this.pc = pc;

        // Set up frame properties
        setTitle("Manufactor Company Data Search");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // Center the window on screen

        // Input Panel for company name
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Search Criteria"));

        JLabel companyLabel = new JLabel("Company Name:");
        companyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        companyNameField = new JTextField(20);
        inputPanel.add(companyLabel);
        inputPanel.add(companyNameField);

        // Buttons Panel with consistent brown color scheme
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 25, 100));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Select Data Category"));
        buttonPanel.setBackground(new Color(245, 222, 179)); // Light brown background

        // Common brown color scheme for buttons
        Color buttonColor = new Color(139, 69, 19); // SaddleBrown
        Color textColor = Color.WHITE;

        // Creating buttons with consistent brown color scheme
        companyButton = createStyledButton("Companies", buttonColor, textColor);
        employeeButton = createStyledButton("Employee", buttonColor, textColor);
        financeDataButton = createStyledButton("Finance Data", buttonColor, textColor);
        incomeProfitButton = createStyledButton("Income & Profit", buttonColor, textColor);
        marketingResearchButton = createStyledButton("Marketing Research", buttonColor, textColor);
        productivityButton = createStyledButton("Productivity", buttonColor, textColor);
        financialHealthButton = createStyledButton("Financial Health Check", buttonColor, textColor);

        // Add buttons to panel
        buttonPanel.add(companyButton);
        buttonPanel.add(employeeButton);
        buttonPanel.add(financeDataButton);
        buttonPanel.add(incomeProfitButton);
        buttonPanel.add(marketingResearchButton);
        buttonPanel.add(productivityButton);
        buttonPanel.add(financialHealthButton);

        // Result Panel with titled border (to create a block-like appearance)
        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Results", 0, 0, new Font("Arial", Font.BOLD, 16)));

        // Result Table Setup
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.setFont(new Font("Arial", Font.PLAIN, 14));
        resultTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Keep column header styling
        resultTable.getTableHeader().setBackground(new Color(205, 133, 63)); // Peru color for header background
        resultTable.getTableHeader().setForeground(Color.WHITE); // White text for header

        // Add grid lines for better readability
        resultTable.setShowGrid(true);
        resultTable.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setPreferredSize(new Dimension(850, 300));
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Adding scroll pane to result panel
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        // Setting a gap for comfortable display
        setPaddingAndSpacing(inputPanel, buttonPanel, resultPanel);

        // Adding Action Listeners for each button
        addActionListeners();
    }

    private void addActionListeners() {
        employeeButton.addActionListener(e -> handleEmployeeButton());
        financeDataButton.addActionListener(e -> handleFinanceDataButton());
        incomeProfitButton.addActionListener(e -> handleIncomeProfitButton());
        marketingResearchButton.addActionListener(e -> handleMarketingResearchButton());
        productivityButton.addActionListener(e -> handleProductivityButton());
        companyButton.addActionListener(e -> handleCompanyButton());
        financialHealthButton.addActionListener(e -> handleFinancialHealthCheck());

    }

    private void handleEmployeeButton() {
        String companyName = getCompanyNameInput();
        List<Employee> employees = ec.fetchEmployeeData(companyName);
        if (employees.isEmpty()) {
            setTableData(new Object[0][0], new String[]{"Message"});
        } else {
            String[] columnNames = {"Year", "Number of Employees"};
            Object[][] data = new Object[employees.size()][2];
            for (int i = 0; i < employees.size(); i++) {
                data[i][0] = employees.get(i).getYear(); // Include year
                data[i][1] = employees.get(i).getNumberOfEmployees();
            }
            setTableData(data, columnNames);
        }
    }
    private void handleCompanyButton() {
        CompanyController cc = new CompanyController();
        List<Company> companies = cc.fetchCompanyData();
        if (companies.isEmpty()) {
            setTableData(new Object[0][0], new String[]{"Message"});
        } else {
            String[] columnNames = {"Company Name", "Year"};
            Object[][] data = new Object[companies.size()][2];
            for (int i = 0; i < companies.size(); i++) {
                data[i][0] = companies.get(i).getName();
                data[i][1] = companies.get(i).getYear();
            }
            setTableData(data, columnNames);
        }
    }

    private void handleFinancialHealthCheck() {
        String companyName = getCompanyNameInput();
        List<FinanceDataAndAsset> financeDataList = fc.fetchFinanceData(companyName); // Assuming `fc` is a FinanceDataController
        if (financeDataList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No financial data available for " + companyName, "Financial Health Check", JOptionPane.INFORMATION_MESSAGE);
        } else {
            FinancialHealthController fhc = new FinancialHealthController();
            String result = fhc.determineFinancialHealth(financeDataList);
            // Redirect to the new frame
            new FinancialHealthResultFrame(result);
        }
    }

    private void handleFinanceDataButton() {
        String companyName = getCompanyNameInput();
        List<FinanceDataAndAsset> financeData = fc.fetchFinanceData(companyName);
        if (financeData.isEmpty()) {
            setTableData(new Object[0][0], new String[]{"Message"});
        } else {
            String[] columnNames = {"Year", "Total Asset", "Fixed Asset", "Cost of Goods Sold", "Operating Expense", "General Administrative Expense"};
            Object[][] data = new Object[financeData.size()][6];
            for (int i = 0; i < financeData.size(); i++) {
                data[i][0] = financeData.get(i).getYear();
                data[i][1] = financeData.get(i).getTotalAsset();
                data[i][2] = financeData.get(i).getFixedAsset();
                data[i][3] = financeData.get(i).getCostOfGoodsSold();
                data[i][4] = financeData.get(i).getOperatingExpense();
                data[i][5] = financeData.get(i).getGeneralAdministrativeExpense();
            }
            setTableData(data, columnNames);
        }
    }

    private void handleIncomeProfitButton() {
        String companyName = getCompanyNameInput();
        List<IncomeAndProfit> incomeData = ipc.fetchIncomeAndProfitData(companyName);
        if (incomeData.isEmpty()) {
            setTableData(new Object[0][0], new String[]{"Message"});
        } else {
            String[] columnNames = {"Year", "Sales Revenue", "Operating Profit Margin", "Operating Profit Margin Ratio"};
            Object[][] data = new Object[incomeData.size()][4];
            for (int i = 0; i < incomeData.size(); i++) {
                data[i][0] = incomeData.get(i).getYear();
                data[i][1] = incomeData.get(i).getSalesRevenue();
                data[i][2] = incomeData.get(i).getOperatingProfitMargin();
                data[i][3] = incomeData.get(i).getOperatingProfitMarginRatio();
            }
            setTableData(data, columnNames);
        }
    }

    private void handleMarketingResearchButton() {
        String companyName = getCompanyNameInput();
        List<MarketingResearchExpenses> marketingData = mrc.fetchMarketingResearchData(companyName);
        if (marketingData.isEmpty()) {
            setTableData(new Object[0][0], new String[]{"Message"});
        } else {
            String[] columnNames = {"Year", "Advertising Expenses", "R&D Expenses"};
            Object[][] data = new Object[marketingData.size()][3];
            for (int i = 0; i < marketingData.size(); i++) {
                data[i][0] = marketingData.get(i).getYear();
                data[i][1] = marketingData.get(i).getAdvertisingExpenses();
                data[i][2] = marketingData.get(i).getRndExpenses();
            }
            setTableData(data, columnNames);
        }
    }

    private void handleProductivityButton() {
        String companyName = getCompanyNameInput();
        List<Productivity> productivityData = pc.fetchProductivityData(companyName);
        if (productivityData.isEmpty()) {
            setTableData(new Object[0][0], new String[]{"Message"});
        } else {
            String[] columnNames = {"Year", "Return on Asset", "Operational Efficiency", "Sales Growth"};
            Object[][] data = new Object[productivityData.size()][4];
            for (int i = 0; i < productivityData.size(); i++) {
                data[i][0] = productivityData.get(i).getYear(); // Include year
                data[i][1] = productivityData.get(i).getReturnOnAsset();
                data[i][2] = productivityData.get(i).getOperationalEfficiency();
                data[i][3] = productivityData.get(i).getSalesGrowth();
            }
            setTableData(data, columnNames);
        }
    }

    private JButton createStyledButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(textColor);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void setPaddingAndSpacing(JPanel... panels) {
        for (JPanel panel : panels) {
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
    }

    public String getCompanyNameInput() {
        return companyNameField.getText().trim();
    }

    public void setTableData(Object[][] data, String[] columnNames) {
        if (data.length == 0) {
            // Display a "No data found" message if data is empty
            tableModel.setDataVector(new Object[][]{{"No data found"}}, new String[]{"Message"});
        } else {
            // Set data normally
            tableModel.setDataVector(data, columnNames);
        }
    }
}

