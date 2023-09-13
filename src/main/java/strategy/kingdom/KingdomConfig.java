package strategy.kingdom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.military.general.major.MajorGeneralConfig;
import strategy.military.infantry.warrior.InitWarriorsConfig;
import strategy.location.castle.CastleConfig;
import strategy.location.mountain.MountainConfig;
import strategy.location.settlement.SettlementConfig;
import strategy.location.village.VillageConfig;
import strategy.util.Time;

@Getter
@AllArgsConstructor
public class KingdomConfig implements Config {
	private final String kingdomId;

	private final Time attackTime;

	private final KingdomType kingdomType;

	private final InitWarriorsConfig warriorsConfig;

	private final VillageConfig villageConfig;

	private final MountainConfig mountainConfig;

    private final CastleConfig castleConfig;

	private final SettlementConfig settlementConfig;

	private final MajorGeneralConfig majorGeneralConfig;
}
