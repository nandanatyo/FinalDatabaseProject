package internal.controller;

import internal.config.database.DatabaseConnection;
import internal.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataController {

    public List<Employee> fetchEmployeeData(String companyName) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT e.number_employee FROM Employee e " +
                "JOIN Companies c ON e.id = c.id " +
                "WHERE c.name = ? ORDER BY c.year DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                employees.add(new Employee(rs.getInt("number_employee")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<FinanceDataAndAsset> fetchFinanceData(String companyName) {
        List<FinanceDataAndAsset> financeDataList = new ArrayList<>();
        String query = "SELECT f.total_asset, f.fixed_asset, f.cost_of_goods_sold, " +
                "f.operating_expense, f.general_administrative_expense " +
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
                        rs.getDouble("general_administrative_expense")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return financeDataList;
    }

    public List<IncomeAndProfit> fetchIncomeAndProfitData(String companyName) {
        List<IncomeAndProfit> incomeDataList = new ArrayList<>();
        String query = "SELECT i.sales_revenue, i.operating_profit_margin, i.operating_profit_margin_ratio " +
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
                        rs.getDouble("operating_profit_margin_ratio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeDataList;
    }

    public List<MarketingResearchExpenses> fetchMarketingResearchData(String companyName) {
        List<MarketingResearchExpenses> marketingDataList = new ArrayList<>();
        String query = "SELECT m.advertising_expenses, m.rnd_expenses " +
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
                        rs.getDouble("rnd_expenses")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marketingDataList;
    }

    public List<Productivity> fetchProductivityData(String companyName) {
        List<Productivity> productivityDataList = new ArrayList<>();
        String query = "SELECT p.return_on_asset, p.operational_efficiency, p.sales_growth " +
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
                        rs.getDouble("sales_growth")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productivityDataList;
    }
}
