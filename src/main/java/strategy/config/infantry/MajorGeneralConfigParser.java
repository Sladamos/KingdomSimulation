package strategy.config.infantry;

import org.json.JSONException;
import strategy.config.ConfigParser;
import strategy.error.CriticalAppError;
import strategy.json.JSON;
import strategy.military.general.GeneralConfig;
import strategy.military.general.major.MajorGeneralConfig;

public class MajorGeneralConfigParser implements ConfigParser<MajorGeneralConfig> {

    @Override
    public MajorGeneralConfig createConfig(JSON json) {
        try {
            GeneralConfig warriorsGeneralConfig = createGeneralConfig(json.getJSONObject("warriors"));
            return new MajorGeneralConfig(warriorsGeneralConfig);
        } catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating major general config. " + err.getMessage());
        }
    }

    private GeneralConfig createGeneralConfig(JSON json) {
        GeneralConfigParser generalConfigParser = new GeneralConfigParser();
        return generalConfigParser.createConfig(json);
    }
}