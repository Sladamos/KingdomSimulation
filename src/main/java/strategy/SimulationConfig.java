package strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.kingdom.KingdomConfig;

@Getter
@AllArgsConstructor
public class SimulationConfig implements Config {
	private KingdomConfig firstKingdomConfig;

	private KingdomConfig secondKingdomConfig;
}
