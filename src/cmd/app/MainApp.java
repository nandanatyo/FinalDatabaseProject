package cmd.app;

import internal.controller.CompanyDAO;
import internal.config.database.DatabaseConnection;
import internal.model.Company;
import internal.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Create instances of the DAO and View
        CompanyDAO companyDAO = new CompanyDAO();
        MainView view = new MainView();

        // Test the database connection
        Connection testConn = DatabaseConnection.connect();
        if (testConn != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Failed to connect!");
        }

        // Add a listener to the search button
        view.addSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subsector = view.getSubsectorInput();
                if (subsector.isEmpty()) {
                    view.setResults("Please enter a subsector.");
                    return;
                }

                List<Company> companies = companyDAO.getLargestTotalAssetCompanies(subsector);
                if (companies.isEmpty()) {
                    view.setResults("No companies found for the subsector: " + subsector);
                } else {
                    StringBuilder resultText = new StringBuilder();
                    for (Company company : companies) {
                        resultText.append(company).append("\n");
                    }
                    view.setResults(resultText.toString());
                }
            }
        });

        // Display the GUI
        view.setVisible(true);
    }
}
