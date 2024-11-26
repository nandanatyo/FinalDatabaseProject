package internal.controller;

import internal.config.database.DatabaseConnection;
import internal.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyController {
    public List<Company> fetchCompanyData() {
        List<Company> companies = new ArrayList<>();
        String query = "SELECT name, year FROM Companies ORDER BY year DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                int year = rs.getInt("year");
                companies.add(new Company(name, year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
