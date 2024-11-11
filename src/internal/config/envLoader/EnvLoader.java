package internal.config.envLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnvLoader {

    public static Map<String, String> loadEnv(String filePath) {
        Map<String, String> envVariables = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Ignore comments and blank lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split key-value pairs
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    envVariables.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return envVariables;
    }
}
