package cmd.app;

import internal.controller.DataController;
import internal.config.database.DatabaseConnection;
import internal.model.*;
import internal.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        DataController dataController = new DataController();
        MainView view = new MainView();

        // Test database connection
        Connection testConn = DatabaseConnection.connect();
        if (testConn != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Failed to connect!");
        }

        // Add listeners to view
        view.addListeners(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String companyName = view.getCompanyNameInput();
                        List<Employee> employees = dataController.fetchEmployeeData(companyName);
                        String[] columnNames = {"Number of Employees"};
                        Object[][] data = new Object[employees.size()][1];
                        for (int i = 0; i < employees.size(); i++) {
                            data[i][0] = employees.get(i).getNumberOfEmployees();
                        }
                        view.setTableData(data, columnNames);
                    }
                },
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String companyName = view.getCompanyNameInput();
                        List<FinanceDataAndAsset> financeData = dataController.fetchFinanceData(companyName);
                        String[] columnNames = {"Total Asset", "Fixed Asset", "Cost of Goods Sold", "Operating Expense", "General Administrative Expense"};
                        Object[][] data = new Object[financeData.size()][5];
                        for (int i = 0; i < financeData.size(); i++) {
                            data[i][0] = financeData.get(i).getTotalAsset();
                            data[i][1] = financeData.get(i).getFixedAsset();
                            data[i][2] = financeData.get(i).getCostOfGoodsSold();
                            data[i][3] = financeData.get(i).getOperatingExpense();
                            data[i][4] = financeData.get(i).getGeneralAdministrativeExpense();
                        }
                        view.setTableData(data, columnNames);
                    }
                },
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String companyName = view.getCompanyNameInput();
                        List<IncomeAndProfit> incomeData = dataController.fetchIncomeAndProfitData(companyName);
                        String[] columnNames = {"Sales Revenue", "Operating Profit Margin", "Operating Profit Margin Ratio"};
                        Object[][] data = new Object[incomeData.size()][3];
                        for (int i = 0; i < incomeData.size(); i++) {
                            data[i][0] = incomeData.get(i).getSalesRevenue();
                            data[i][1] = incomeData.get(i).getOperatingProfitMargin();
                            data[i][2] = incomeData.get(i).getOperatingProfitMarginRatio();
                        }
                        view.setTableData(data, columnNames);
                    }
                },
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String companyName = view.getCompanyNameInput();
                        List<MarketingResearchExpenses> marketingData = dataController.fetchMarketingResearchData(companyName);
                        String[] columnNames = {"Advertising Expenses", "R&D Expenses"};
                        Object[][] data = new Object[marketingData.size()][2];
                        for (int i = 0; i < marketingData.size(); i++) {
                            data[i][0] = marketingData.get(i).getAdvertisingExpenses();
                            data[i][1] = marketingData.get(i).getRndExpenses();
                        }
                        view.setTableData(data, columnNames);
                    }
                },
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String companyName = view.getCompanyNameInput();
                        List<Productivity> productivityData = dataController.fetchProductivityData(companyName);
                        String[] columnNames = {"Return on Asset", "Operational Efficiency", "Sales Growth"};
                        Object[][] data = new Object[productivityData.size()][3];
                        for (int i = 0; i < productivityData.size(); i++) {
                            data[i][0] = productivityData.get(i).getReturnOnAsset();
                            data[i][1] = productivityData.get(i).getOperationalEfficiency();
                            data[i][2] = productivityData.get(i).getSalesGrowth();
                        }
                        view.setTableData(data, columnNames);
                    }
                }
        );

        // Display the GUI
        view.setVisible(true);
    }
}
