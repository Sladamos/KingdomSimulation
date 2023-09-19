package strategy.simulation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.kingdom.KingdomConfig;

@Getter
@AllArgsConstructor
public class ManualSimulationConfigImpl implements ManualSimulationConfig {
    private final KingdomConfig firstKingdomConfig;

    private final KingdomConfig secondKingdomConfig;
}
