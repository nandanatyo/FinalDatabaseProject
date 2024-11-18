package internal.controller;

import internal.config.database.DatabaseConnection;
import internal.model.Productivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductivityController {
    public List<Productivity> fetchProductivityData(String companyName) {
        List<Productivity> productivityDataList = new ArrayList<>();
        String query = "SELECT p.return_on_asset, p.operational_efficiency, p.sales_growth, c.year " +
                "FROM Productivity p " +
                "JOIN Companies c ON p.id = c.id " +
                "WHERE c.name = ? ORDER BY c.year DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productivityDataList.add(new Productivity(
                        rs.getDouble("return_on_asset"),
                        rs.getDouble("operational_efficiency"),
                        rs.getDouble("sales_growth"),
                        rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productivityDataList;
    }
}
