package strategy.json;

import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import strategy.error.CriticalAppError;

@AllArgsConstructor
public class JSON {
    private final JSONObject jsonObject;

    public JSON() {
        jsonObject = new JSONObject();
    }

    public JSON(JSON json) {
        jsonObject = new JSONObject(json.jsonObject.toString());
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }

    public void put(String key, int value) {
        jsonObject.put(key, value);
    }

    public void put(String key, String value) {
        jsonObject.put(key, value);
    }

    public JSON getJSONObject(String key) {
        try {
            JSONObject newJsonObject = jsonObject.getJSONObject(key);
            return new JSON(newJsonObject);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Incorrect key.");
        }
    }

    public String getString(String key) {
        try {
            return jsonObject.getString(key);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Incorrect key.");
        }
    }

    public long getLong(String key) {
        try {
            return jsonObject.getLong(key);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Incorrect key.");
        }
    }

    public int getInt(String key) {
        try {
            return jsonObject.getInt(key);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Incorrect key.");
        }
    }
}
