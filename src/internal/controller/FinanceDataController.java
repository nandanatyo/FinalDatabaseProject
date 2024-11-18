package internal.controller;

import internal.config.database.DatabaseConnection;
import internal.model.FinanceDataAndAsset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinanceDataController {
    public List<FinanceDataAndAsset> fetchFinanceData(String companyName) {
        List<FinanceDataAndAsset> financeDataList = new ArrayList<>();
        String query = "SELECT f.total_asset, f.fixed_asset, f.cost_of_goods_sold, " +
                "f.operating_expense, f.general_administrative_expense, c.year " +
                "FROM FinanceDataAndAsset f " +
                "JOIN Companies c ON f.id = c.id " +
                "WHERE c.name = ? ORDER BY c.year DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                financeDataList.add(new FinanceDataAndAsset(
                        rs.getDouble("total_asset"),
                        rs.getDouble("fixed_asset"),
                        rs.getDouble("cost_of_goods_sold"),
                        rs.getDouble("operating_expense"),
                        rs.getDouble("general_administrative_expense"),
                        rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return financeDataList;
    }
}
