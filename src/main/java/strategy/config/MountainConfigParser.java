package strategy.config;

import org.json.JSONException;
import strategy.CriticalAppError;
import strategy.json.JSON;
import strategy.location.mountain.MountainConfig;
import strategy.producer.ProducerConfig;

public class MountainConfigParser implements ConfigParser<MountainConfig> {
    @Override
    public MountainConfig createConfig(JSON json) {
        try {
            ProducerConfig saltMinerConfig = createProducerConfig(json.getJSONObject("salt"));
            ProducerConfig ironOreMinerConfig = createProducerConfig(json.getJSONObject("iron_ore"));
            ProducerConfig sapphireMinerConfig = createProducerConfig(json.getJSONObject("sapphire"));
            ProducerConfig rubyMinerConfig = createProducerConfig(json.getJSONObject("ruby"));
            return new MountainConfig(saltMinerConfig, ironOreMinerConfig, rubyMinerConfig, sapphireMinerConfig);
        }
        catch (JSONException err) {
            throw new CriticalAppError("Something went wrong on creating mountain config. " + err.getMessage());
        }
    }

    private ProducerConfig createProducerConfig(JSON json) {
        ProducerConfigParser producerConfigParser = new ProducerConfigParser();
        return producerConfigParser.createConfig(json);
    }
}
