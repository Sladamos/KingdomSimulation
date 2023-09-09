package strategy.config;

import org.json.JSONException;
import strategy.CriticalAppError;
import strategy.item.military.GeneralConfig;
import strategy.json.JSON;

public class GeneralConfigParser implements ConfigParser<GeneralConfig> {
    @Override
    public GeneralConfig createConfig(JSON json) {
        try {
            int hapinessDamageModificator = json.getInt("hapiness_damage_modificator");
            if(hapinessDamageModificator <= 0) {
                throw new JSONException("Hapiness damage modificator must be greater than 0.");
            }
            return new GeneralConfig(hapinessDamageModificator);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating general config. " + err.getMessage());
        }
    }
}
