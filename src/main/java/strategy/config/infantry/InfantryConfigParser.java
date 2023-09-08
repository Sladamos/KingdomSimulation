package strategy.config.infantry;

import org.json.JSONException;
import strategy.CriticalAppError;
import strategy.config.ConfigParser;
import strategy.item.military.infantry.InfantryConfig;
import strategy.json.JSON;

public abstract class InfantryConfigParser<T extends InfantryConfig> implements ConfigParser<T> {
    public  T createConfig(JSON json) {
        try {
            return createInfantryConfig(json);
        } catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating barracks config. " + err.getMessage());
        }
    }

    protected abstract T createInfantryConfig(JSON json);
}