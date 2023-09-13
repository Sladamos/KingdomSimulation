package strategy.config;

import org.json.JSONException;
import strategy.error.AppError;
import strategy.error.CriticalAppError;
import strategy.config.infantry.WarriorConfigParser;
import strategy.military.infantry.warrior.WarriorConfig;
import strategy.json.JSON;
import strategy.location.settlement.SettlementConfig;
import strategy.producer.ProducerConfig;
import strategy.producer.building.military.infantry.BarracksConfig;

public class SettlementConfigParser implements ConfigParser<SettlementConfig> {
    @Override
    public SettlementConfig createConfig(JSON json) {
        try {
            ProducerConfig smelterConfig = createProducerConfig(json.getJSONObject("smelter"));
            ProducerConfig blacksmithConfig = createProducerConfig(json.getJSONObject("blacksmith"));
            BarracksConfig<WarriorConfig> barracksConfig = createBarracksConfig(json.getJSONObject("barracks"));
            ProducerConfig ironBucketArtisan = createProducerConfig(json.getJSONObject("iron_bucket_artisan"));
            ProducerConfig woodenBucketArtisan = createProducerConfig(json.getJSONObject("wooden_bucket_artisan"));
            ProducerConfig millConfig = createProducerConfig(json.getJSONObject("mill"));
            ProducerConfig bakeryConfig = createProducerConfig(json.getJSONObject("bakery"));
            ProducerConfig childHouseConfig = createProducerConfig(json.getJSONObject("child_house"));
            ProducerConfig alchemistConfig = createProducerConfig(json.getJSONObject("alchemist"));
            ProducerConfig waterWellConfig = createProducerConfig(json.getJSONObject("water_well"));
            ProducerConfig goldenCoinWellConfig = createProducerConfig(json.getJSONObject("golden_coin_well"));
            ProducerConfig ringJewellerConfig = createProducerConfig(json.getJSONObject("ring_jeweller"));
            ProducerConfig necklaceJewellerConfig = createProducerConfig(json.getJSONObject("necklace_jeweller"));
            return new SettlementConfig(smelterConfig, blacksmithConfig, barracksConfig, ironBucketArtisan,
                    woodenBucketArtisan, millConfig, bakeryConfig, childHouseConfig, alchemistConfig, waterWellConfig,
                    goldenCoinWellConfig, ringJewellerConfig, necklaceJewellerConfig);
        } catch (JSONException | AppError err) {
            throw new CriticalAppError("Something went wrong on creating settlement config. " + err.getMessage());
        }
    }

    private BarracksConfig<WarriorConfig> createBarracksConfig(JSON json) {
        BarracksConfigParser<WarriorConfig> barracksConfigParser = new BarracksConfigParser<>(new WarriorConfigParser());
        return barracksConfigParser.createConfig(json);
    }

    private ProducerConfig createProducerConfig(JSON json) {
        ProducerConfigParser producerConfigParser = new ProducerConfigParser();
        return producerConfigParser.createConfig(json);
    }
}
