package strategy.kingdom;

import lombok.Getter;

public class KingdomConfig {
	@Getter
	private final String kingdomId;

	@Getter
	private final long attackTime;

	@Getter
	private final KingdomTypes kingdomType;

	public KingdomConfig(String kingdomId, long attackTime, KingdomTypes kingdomType) {
		this.kingdomId = kingdomId;
		this.attackTime = attackTime;
		this.kingdomType = kingdomType;
	}
}
