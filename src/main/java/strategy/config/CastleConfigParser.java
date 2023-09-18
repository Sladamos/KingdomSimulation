package strategy.config;

import org.json.JSONException;
import strategy.error.AppError;
import strategy.error.CriticalAppError;
import strategy.json.JSON;
import strategy.location.castle.CastleConfig;
import strategy.producer.ProducerConfig;

public class CastleConfigParser implements ConfigParser<CastleConfig> {
    @Override
    public CastleConfig createConfig(JSON json) {
        try {
            ProducerConfig queenConfig = createProducerConfig(json.getJSONObject("queen"));
            ProducerConfig princessConfig = createProducerConfig(json.getJSONObject("princess"));
            ProducerConfig necklacePresentCraftsmanConfig = createProducerConfig(json.getJSONObject("necklace_present_craftsman"));
            ProducerConfig ringPresentCraftsmanConfig = createProducerConfig(json.getJSONObject("ring_present_craftsman"));
            return new CastleConfig(queenConfig, princessConfig, necklacePresentCraftsmanConfig, ringPresentCraftsmanConfig);
        }
        catch (AppError err) {
            throw new CriticalAppError("Something went wrong on creating castle config. " + err.getMessage());
        }
    }
    private ProducerConfig createProducerConfig(JSON json) {
        ProducerConfigParser producerConfigParser = new ProducerConfigParser();
        return producerConfigParser.createConfig(json);
    }
}
