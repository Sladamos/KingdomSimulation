package strategy.message;

import lombok.Getter;
import strategy.json.JSON;

public class JSONMessage implements Message<JSON> {

    @Getter
    private final JSON content;

    public JSONMessage(String message) {
        this();
        content.put("message", message);
    }

    public JSONMessage(JSON content) {
        this.content = content;
    }

    public JSONMessage(JSONMessage message) {
        content = new JSON(message.content);
    }

    public JSONMessage() {
        this.content = new JSON();
    }

    @Override
    public String toString() {
        try {
            return content.getString("message");
        } catch (Exception err) {
            return content.toString();
        }
    }

    public void put(String key, String value) {
        content.put(key, value);
    }

    public void put(String key, int value) {
        content.put(key, value);
    }
}

