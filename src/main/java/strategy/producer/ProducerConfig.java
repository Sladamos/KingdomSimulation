package strategy.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProducerConfig {
    private final int numberOfItemsPerMinute;
}
