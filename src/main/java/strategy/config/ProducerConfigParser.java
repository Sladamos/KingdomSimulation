package strategy.config;

import org.json.JSONException;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.json.JSON;
import strategy.producer.ProducerConfig;

public class ProducerConfigParser implements ConfigParser<ProducerConfig> {

    @Override
    public ProducerConfig createConfig(JSON json) {
        try {
            int numberOfItemsPerMinute = json.getInt("items_per_minute");
            if(numberOfItemsPerMinute <= 0) {
                throw new BasicAppError("Number of items must be greater than 0");
            }
            return new ProducerConfig(numberOfItemsPerMinute);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating producer config. " + err.getMessage());
        }
    }
}
