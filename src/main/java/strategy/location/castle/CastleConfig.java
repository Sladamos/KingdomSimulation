package strategy.location.castle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.item.military.GeneralConfig;
import strategy.producer.ProducerConfig;

@AllArgsConstructor
@Getter
public class CastleConfig implements Config {

    private final ProducerConfig queenConfig;

    private final ProducerConfig princessConfig;

    private final ProducerConfig necklacePresentCraftsmanConfig;

    private final ProducerConfig ringPresentCraftsmanConfig;

    private final GeneralConfig warriorGeneralConfig;
}
