package strategy.config;

import org.json.JSONException;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.config.infantry.InfantryConfigParser;
import strategy.military.infantry.InfantryConfig;
import strategy.json.JSON;
import strategy.producer.ProducerConfig;
import strategy.producer.building.military.infantry.BarracksConfig;

public class BarracksConfigParser<T extends InfantryConfig> implements ConfigParser<BarracksConfig<T>> {

    private final InfantryConfigParser<T> infantryConfigParser;

    public BarracksConfigParser(InfantryConfigParser<T> infantryConfigParser) {
        this.infantryConfigParser = infantryConfigParser;
    }

    @Override
    public BarracksConfig<T> createConfig(JSON json) {
        try {
            ProducerConfig militaryProducerConfig = createProducerConfig(json.getJSONObject("military_producer"));
            T infantryConfig = infantryConfigParser.createConfig(json.getJSONObject("infantry"));
            return new BarracksConfig<>(militaryProducerConfig, infantryConfig);
        } catch (BasicAppError err) {
            throw new CriticalAppError("Something went wrong on creating barracks config. " + err.getMessage());
        }
    }

    private ProducerConfig createProducerConfig(JSON json) {
        ProducerConfigParser producerConfigParser = new ProducerConfigParser();
        return producerConfigParser.createConfig(json);
    }
}
