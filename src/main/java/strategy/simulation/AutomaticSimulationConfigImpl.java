package strategy.simulation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.kingdom.KingdomConfig;
import util.Time;

@Getter
@AllArgsConstructor
public class AutomaticSimulationConfigImpl implements AutomaticSimulationConfig {
    private final KingdomConfig firstKingdomConfig;

    private final KingdomConfig secondKingdomConfig;

    private final Time firstKingdomDevelopmentTime;

    private final Time secondKingdomDevelopmentTime;
}
