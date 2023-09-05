package strategy.json;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonLoaderImpl implements JsonLoader {
    @Override
    public JSON loadJsonFromFile(String fileName) {
        try (InputStream inputStream = JsonLoaderImpl.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                byte[] bytes = inputStream.readAllBytes();
                String jsonContent = new String(bytes, StandardCharsets.UTF_8);
                JSONObject jsonObject = new JSONObject(jsonContent);
                return new JSON(jsonObject);
            } else {
                throw new IOException("Config file not found: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading config file: " + e.getMessage(), e);
        }
    }
}
