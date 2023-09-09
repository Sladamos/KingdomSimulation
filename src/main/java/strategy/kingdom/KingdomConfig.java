package strategy.kingdom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import strategy.Config;
import strategy.item.military.InitMilitaryConfig;
import strategy.location.castle.CastleConfig;
import strategy.location.mountain.MountainConfig;
import strategy.location.settlement.SettlementConfig;
import strategy.location.village.VillageConfig;
import util.Time;

@Getter
@AllArgsConstructor
public class KingdomConfig implements Config {
	private final String kingdomId;

	private final Time attackTime;

	private final KingdomTypes kingdomType;

	private final InitMilitaryConfig militaryConfig;

	private final VillageConfig villageConfig;

	private final MountainConfig mountainConfig;

    private final CastleConfig castleConfig;

	private final SettlementConfig settlementConfig;
}
