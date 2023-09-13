package strategy.config;

import org.json.JSONException;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.item.military.general.GeneralConfig;
import strategy.json.JSON;

public class GeneralConfigParser implements ConfigParser<GeneralConfig> {
    @Override
    public GeneralConfig createConfig(JSON json) {
        try {
            int hapinessDamageModifier = json.getInt("hapiness_damage_modifier");
            if(hapinessDamageModifier <= 0) {
                throw new BasicAppError("Hapiness damage modifier must be greater than 0.");
            }
            return new GeneralConfig(hapinessDamageModifier);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating general config. " + err.getMessage());
        }
    }
}
