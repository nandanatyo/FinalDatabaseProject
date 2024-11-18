package cmd.app;

import internal.controller.*;
import internal.config.database.DatabaseConnection;
import internal.view.MainView;

import java.sql.Connection;

public class MainApp {
    public static void main(String[] args) {
        // Initialize controllers
        EmployeeController ec = new EmployeeController();
        FinanceDataController fc = new FinanceDataController();
        IncomeAndProfitDataController ipc = new IncomeAndProfitDataController();
        MarketingResearchController mrc = new MarketingResearchController();
        ProductivityController pc = new ProductivityController();

        // Initialize MainView with the controllers
        MainView view = new MainView(ec, fc, ipc, mrc, pc);

        // Test database connection
        Connection testConn = DatabaseConnection.connect();
        if (testConn != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Failed to connect!");
        }

        // Display the GUI
        view.setVisible(true);
    }
}
