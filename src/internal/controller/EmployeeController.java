package internal.controller;

import internal.config.database.DatabaseConnection;
import internal.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    public List<Employee> fetchEmployeeData(String companyName) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT e.number_employee, c.year FROM Employee e " +
                "JOIN Companies c ON e.id = c.id " +
                "WHERE c.name = ? ORDER BY c.year DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, companyName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int numberOfEmployees = rs.getInt("number_employee");
                int year = rs.getInt("year");
                employees.add(new Employee(numberOfEmployees, year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
