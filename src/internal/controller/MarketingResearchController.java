package internal.controller;

import internal.config.database.DatabaseConnection;
import internal.model.MarketingResearchExpenses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarketingResearchController {
    public List<MarketingResearchExpenses> fetchMarketingResearchData(String companyName) {
        List<MarketingResearchExpenses> marketingDataList = new ArrayList<>();
        String query = "SELECT m.advertising_expenses, m.rnd_expenses, c.year " +
                "FROM MarketingResearchExpenses m " +
                "JOIN Companies c ON m.id = c.id " +
                "WHERE c.name = ? ORDER BY c.year DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                marketingDataList.add(new MarketingResearchExpenses(
                        rs.getDouble("advertising_expenses"),
                        rs.getDouble("rnd_expenses"),
                        rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marketingDataList;
    }
}
