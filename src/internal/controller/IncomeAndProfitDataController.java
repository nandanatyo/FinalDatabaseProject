package internal.controller;

import internal.config.database.DatabaseConnection;
import internal.model.IncomeAndProfit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeAndProfitDataController {
    public List<IncomeAndProfit> fetchIncomeAndProfitData(String companyName) {
        List<IncomeAndProfit> incomeDataList = new ArrayList<>();
        String query = "SELECT i.sales_revenue, i.operating_profit_margin, i.operating_profit_margin_ratio, c.year " +
                "FROM IncomeAndProfit i " +
                "JOIN Companies c ON i.id = c.id " +
                "WHERE c.name = ? ORDER BY c.year DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                incomeDataList.add(new IncomeAndProfit(
                        rs.getDouble("sales_revenue"),
                        rs.getDouble("operating_profit_margin"),
                        rs.getDouble("operating_profit_margin_ratio"),
                        rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeDataList;
    }
}
