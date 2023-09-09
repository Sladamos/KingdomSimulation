package strategy.config;

import org.json.JSONException;
import strategy.error.AppError;
import strategy.error.CriticalAppError;
import strategy.json.JSON;
import strategy.location.village.VillageConfig;
import strategy.producer.ProducerConfig;

public class VillageConfigParser implements ConfigParser<VillageConfig> {
    @Override
    public VillageConfig createConfig(JSON json) {
        try {
            ProducerConfig cowConfig = createProducerConfig(json.getJSONObject("cow"));
            ProducerConfig apiaryConfig = createProducerConfig(json.getJSONObject("apiary"));
            ProducerConfig farmConfig = createProducerConfig(json.getJSONObject("farm"));
            ProducerConfig lumberjackConfig = createProducerConfig(json.getJSONObject("lumberjack"));
            return new VillageConfig(cowConfig, apiaryConfig, farmConfig, lumberjackConfig);
        }
        catch (JSONException | AppError err) {
            throw new CriticalAppError("Something went wrong on creating village config. " + err.getMessage());
        }
    }

    private ProducerConfig createProducerConfig(JSON json) {
        ProducerConfigParser producerConfigParser = new ProducerConfigParser();
        return producerConfigParser.createConfig(json);
    }
}
