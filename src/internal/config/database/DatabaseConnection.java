package internal.config.database;

import internal.config.envLoader.EnvLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DatabaseConnection {
    private static final Map<String, String> env = EnvLoader.loadEnv(".env");

    private static final String URL = env.get("DB_URL");
    private static final String USER = env.get("DB_USER");
    private static final String PASSWORD = env.get("DB_PASS");

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
