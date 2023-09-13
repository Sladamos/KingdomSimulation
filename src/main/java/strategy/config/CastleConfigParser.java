package strategy.config;

import org.json.JSONException;
import strategy.error.AppError;
import strategy.error.CriticalAppError;
import strategy.military.general.GeneralConfig;
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
            GeneralConfig generalConfig = createGeneralConfig(json.getJSONObject("general"));
            return new CastleConfig(queenConfig, princessConfig, necklacePresentCraftsmanConfig, ringPresentCraftsmanConfig, generalConfig);
        }
        catch (JSONException | AppError err) {
            throw new CriticalAppError("Something went wrong on creating castle config. " + err.getMessage());
        }
    }

    private GeneralConfig createGeneralConfig(JSON json) {
        GeneralConfigParser generalConfigParser = new GeneralConfigParser();
        return generalConfigParser.createConfig(json);
    }

    private ProducerConfig createProducerConfig(JSON json) {
        ProducerConfigParser producerConfigParser = new ProducerConfigParser();
        return producerConfigParser.createConfig(json);
    }
}
