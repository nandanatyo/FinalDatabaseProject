package internal.controller;

import internal.model.Company;
import internal.config.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    public List<Company> getLargestTotalAssetCompanies(String subsector) {
        List<Company> companies = new ArrayList<>();
        String query = "SELECT c.company_id, c.firm_name, c.subsector, MAX(fd.total_asset_idr) AS total_asset " +
                "FROM companies c JOIN financial_data fd ON c.company_id = fd.company_id " +
                "WHERE c.subsector = r? " +
                "GROUP BY c.company_id, c.firm_name, c.subsector " +
                "ORDER BY total_asset DESC " +
                "LIMIT 1";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, subsector);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                companies.add(new Company(
                        rs.getInt("company_id"),
                        rs.getString("firm_name"),
                        rs.getString("subsector"),
                        rs.getDouble("total_asset")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
