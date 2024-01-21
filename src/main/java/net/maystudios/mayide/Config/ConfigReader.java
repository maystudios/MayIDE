package net.maystudios.mayide.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigReader {

    final static String CONFIG_FILE_PATH = "config.txt";

    public Map<String, Object> readConfig(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        Map<String, Object> config = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split("=", 2);
            if (parts.length >= 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (value.startsWith("[") && value.endsWith("]")) {
                    value = value.substring(1, value.length() - 1);
                    List<String> values = Arrays.asList(value.split(","));
                    config.put(key, values);
                } else if (value.startsWith("{") && value.endsWith("}")) {
                    value = value.substring(1, value.length() - 1);
                    Map<String, String> mapValue = Arrays.stream(value.split("\\],\\["))
                            .map(s -> s.replaceAll("\\[|\\]", "").split(","))
                            .collect(Collectors.toMap(a -> a[0], a -> a[1]));
                    config.put(key, mapValue);
                } else if (value.startsWith("\"") && value.endsWith("\"")) {
                    config.put(key, value.substring(1, value.length() - 1)); // It's a string
                } else if (value.contains(".")) {
                    try {
                        config.put(key, Double.parseDouble(value)); // It's a double
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid double value: " + value);
                    }
                } else {
                    try {
                        config.put(key, Integer.parseInt(value)); // It's an integer
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer value: " + value);
                    }
                }
            }
        }

        return config;
    }
}
