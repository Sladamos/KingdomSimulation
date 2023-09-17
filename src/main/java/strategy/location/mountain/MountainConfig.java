package strategy.location.mountain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.producer.ProducerConfig;

@AllArgsConstructor
@Getter
public class MountainConfig implements Config {

    private final ProducerConfig saltMinerConfig;

    private final ProducerConfig ironOreMinerConfig;

    private final ProducerConfig rubyMinerConfig;

    private final ProducerConfig sapphireMinerConfig;
}
