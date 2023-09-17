package strategy.json;

import lombok.Setter;
import org.json.JSONObject;
import strategy.error.CriticalAppError;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileJsonLoaderImpl implements FileJsonLoader {

    @Setter
    private String fileName;

    @Override
    public JSON loadJson() {
        checkIsFileNameSpecified();
        try (InputStream inputStream = FileJsonLoaderImpl.class.getClassLoader().getResourceAsStream(fileName)) {
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

    private void checkIsFileNameSpecified() {
        if(fileName == null) {
            throw new CriticalAppError("Specify file name!");
        }
    }
}
