package strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.kingdom.KingdomConfig;

@Getter
@AllArgsConstructor
public class SimulationConfig implements Config {
	private final KingdomConfig firstKingdomConfig;

	private final KingdomConfig secondKingdomConfig;
}
