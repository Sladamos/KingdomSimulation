package strategy.message;

import lombok.Getter;
import strategy.error.BasicAppError;
import strategy.json.JSON;

public class JSONMessage implements Message<JSON> {

    @Getter
    private final JSON content;

    public JSONMessage(String message) {
        this.content = new JSON();
        content.put("message", message);
    }

    public JSONMessage(JSON content) {
        this.content = content;
    }

    public JSONMessage(JSONMessage message) {
        content = new JSON(message.content);
    }

    @Override
    public String toString() {
        try {
            return content.getString("message");
        } catch (Exception err) {
            throw new BasicAppError("Something went wrong");
        }
    }

    public void put(String key, String value) {
        content.put(key, value);
    }

    public void put(String key, int value) {
        content.put(key, value);
    }
}

