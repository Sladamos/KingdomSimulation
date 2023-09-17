package strategy.location.village;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.producer.ProducerConfig;

@AllArgsConstructor
@Getter
public class VillageConfig implements Config {
    private final ProducerConfig cowConfig;

    private final ProducerConfig apiaryConfig;

    private final ProducerConfig farmConfig;

    private final ProducerConfig lumberjackConfig;
}
