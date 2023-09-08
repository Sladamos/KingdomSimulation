package strategy.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;

@AllArgsConstructor
@Getter
public class ProducerConfig implements Config {
    private final int numberOfItemsPerMinute;
}
