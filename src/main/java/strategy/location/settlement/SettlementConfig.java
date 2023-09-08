package strategy.location.settlement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.item.military.infantry.warrior.WarriorConfig;
import strategy.producer.ProducerConfig;
import strategy.producer.building.military.infantry.BarracksConfig;

@AllArgsConstructor
@Getter
public class SettlementConfig implements Config {
    ProducerConfig smelterConfig;
    ProducerConfig blacksmithConfig;
    BarracksConfig<WarriorConfig> barracksConfig;
    ProducerConfig ironBucketArtisanConfig;
    ProducerConfig woodenBucketArtisanConfig;
    ProducerConfig millConfig;
    ProducerConfig bakeryConfig;
    ProducerConfig childHouseConfig;
    ProducerConfig alchemistConfig;
    ProducerConfig waterWellConfig;
    ProducerConfig goldenCoinWellConfig;
    ProducerConfig ringJewellerConfig;
    ProducerConfig necklaceJewellerConfig;
}
