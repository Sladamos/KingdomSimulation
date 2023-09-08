package strategy.producer.building.military.infantry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.item.military.infantry.InfantryConfig;
import strategy.producer.ProducerConfig;

@AllArgsConstructor
@Getter
public class BarracksConfig<T extends InfantryConfig> implements Config {

    private final ProducerConfig militaryProducerConfig;

    private final T infantryConfig;
}
