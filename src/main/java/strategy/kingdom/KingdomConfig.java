package strategy.kingdom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.location.mountain.MountainConfig;
import strategy.location.village.VillageConfig;

@Getter
@AllArgsConstructor
public class KingdomConfig implements Config {
	private final String kingdomId;

	private final long attackTime;

	private final KingdomTypes kingdomType;

	private final InitWarriorsConfig warriorsConfig;

	private final VillageConfig villageConfig;

	private final MountainConfig mountainConfig;
}
