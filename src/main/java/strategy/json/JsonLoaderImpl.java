package strategy.json;

import org.json.JSONObject;
import strategy.CriticalAppError;

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
                throw new CriticalAppError("JSON file not found: " + fileName);
            }
        } catch (Exception e) {
            throw new CriticalAppError("Error loading JSON file: " + e.getMessage());
        }
    }
}
