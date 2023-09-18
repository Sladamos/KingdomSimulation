package json;

import org.junit.jupiter.api.Test;
import strategy.error.BasicAppError;
import strategy.json.JSON;

import static org.assertj.core.api.Assertions.*;

public class JsonTest {

    @Test
    public void throwBasicAppException_when_keyNotFoundInGetString() {
        JSON json = new JSON();
        assertThatThrownBy(() -> json.getString("random")).isInstanceOf(BasicAppError.class);
    }

    @Test
    public void throwBasicAppException_when_keyNotFoundInGetInt() {
        JSON json = new JSON();
        assertThatThrownBy(() -> json.getInt("random")).isInstanceOf(BasicAppError.class);
    }

    @Test
    public void throwBasicAppException_when_keyNotFoundInGetLong() {
        JSON json = new JSON();
        assertThatThrownBy(() -> json.getLong("random")).isInstanceOf(BasicAppError.class);
    }

    @Test
    public void throwBasicAppException_when_keyNotFoundInGetJson() {
        JSON json = new JSON();
        assertThatThrownBy(() -> json.getJSONObject("random")).isInstanceOf(BasicAppError.class);
    }

    @Test
    public void createContent_when_putNewKey() {
        JSON json = new JSON();
        json.put("key", "value");
        assertThat(json.getString("key")).isEqualTo("value");
    }

    @Test
    public void copyContent_when_createdFromAnotherJson() {
        JSON json = new JSON();
        json.put("key", "value");
        JSON testedJson = new JSON(json);
        assertThat(testedJson.getString("key")).isEqualTo("value");
    }

    @Test
    public void deepCopyContent_when_createdFromAnotherJson() {
        JSON json = new JSON();
        json.put("key", "value");
        JSON testedJson = new JSON(json);
        json.put("key", "valueSecond");
        assertThat(testedJson.getString("key")).isEqualTo("value");
    }

    @Test
    public void updateContent_when_putAlreadyExistingKeyWithSameContentType() {
        JSON json = new JSON();
        json.put("key", "value");
        json.put("key", "valueSecond");
        assertThat(json.getString("key")).isEqualTo("valueSecond");
    }

    @Test
    public void updateContent_when_putAlreadyExistingKeyWithDifferentContentType() {
        JSON json = new JSON();
        json.put("key", "value");
        json.put("key", 15);
        assertThat(json.getInt("key")).isEqualTo(15);
    }
}
