package strategy.kingdom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;

@Getter
@AllArgsConstructor
public class KingdomConfig implements Config {
	private final String kingdomId;

	private final long attackTime;

	private final KingdomTypes kingdomType;

	private final InitWarriorsConfig warriorsConfig;
}
