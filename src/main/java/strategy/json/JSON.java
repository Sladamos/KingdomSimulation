package strategy.json;

import lombok.AllArgsConstructor;
import org.json.JSONObject;

@AllArgsConstructor
public class JSON {
    private final JSONObject jsonObject;

    public JSON getJSONObject(String key) {
        JSONObject newJsonObject = jsonObject.getJSONObject(key);
        return new JSON(newJsonObject);
    }

    public String getString(String key) {
        return jsonObject.getString(key);
    }

    public long getLong(String key) {
        return jsonObject.getLong(key);
    }
    public int getInt(String key) {
        return jsonObject.getInt(key);
    }
}
