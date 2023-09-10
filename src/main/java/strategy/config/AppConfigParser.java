package strategy.config;

import org.json.JSONException;
import strategy.error.CriticalAppError;
import strategy.app.AppConfig;
import strategy.json.JSON;

public class AppConfigParser implements ConfigParser<AppConfig> {
    @Override
    public AppConfig createConfig(JSON json) {
        try {
            String simulationType = json.getString("simulation_type");
            return new AppConfig(simulationType);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating app config. " + err.getMessage());
        }
    }
}
