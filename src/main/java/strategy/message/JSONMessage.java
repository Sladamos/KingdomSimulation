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

    @Override
    public String toString() {
        try {
            return content.getString("message");
        } catch (Exception err) {
            throw new BasicAppError("Something went wrong");
        }
    }
}

