package strategy.config;

import org.json.JSONException;
import strategy.config.infantry.InitWarriorsConfigParser;
import strategy.json.JSON;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.KingdomTypes;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.CriticalAppError;
import strategy.location.castle.CastleConfig;
import strategy.location.mountain.MountainConfig;
import strategy.location.settlement.SettlementConfig;
import strategy.location.village.VillageConfig;
import util.Time;
import util.TimeImpl;

import java.util.HashMap;
import java.util.Map;

public class KingdomConfigParser implements ConfigParser<KingdomConfig> {

	private final Map<String, KingdomTypes> kingdomTypes;

	public KingdomConfigParser() {
		kingdomTypes = new HashMap<>();
		kingdomTypes.put("Sarrax", KingdomTypes.SARRAX);
	}

	@Override
	public KingdomConfig createConfig(JSON json) {
		try {
			String kingdomId = json.getString("id");
			Time attackTime = new TimeImpl(json.getInt("attack_time"));
			String kingdomTypeStr = json.getString("kingdom_type");
			KingdomTypes kingdomType = getKingdomType(kingdomTypeStr);
			InitWarriorsConfig warriorsConfig = createWarriorsConfig(json.getJSONObject("warriors"));
			VillageConfig villageConfig = createVillageConfig(json.getJSONObject("village"));
			MountainConfig mountainConfig = createMountainConfig(json.getJSONObject("mountain"));
			CastleConfig castleConfig = createCastleConfig(json.getJSONObject("castle"));
			SettlementConfig settlementConfig = createSettlementConfig(json.getJSONObject("settlement"));
			return new KingdomConfig(kingdomId, attackTime, kingdomType, warriorsConfig,
					villageConfig, mountainConfig, castleConfig, settlementConfig);
		}
		catch (JSONException err) {
			throw new CriticalAppError("Something went wrong on creating kingdom config. " + err.getMessage());
		}
	}

	private SettlementConfig createSettlementConfig(JSON settlement) {
		SettlementConfigParser settlementConfigParser = new SettlementConfigParser();
		return settlementConfigParser.createConfig(settlement);
	}

	private CastleConfig createCastleConfig(JSON json) {
		CastleConfigParser castleConfigParser = new CastleConfigParser();
		return castleConfigParser.createConfig(json);
	}

	private MountainConfig createMountainConfig(JSON json) {
		MountainConfigParser mountainConfigParser = new MountainConfigParser();
		return mountainConfigParser.createConfig(json);
	}

	private VillageConfig createVillageConfig(JSON json) {
		VillageConfigParser villageConfigParser = new VillageConfigParser();
		return villageConfigParser.createConfig(json);
	}

	private InitWarriorsConfig createWarriorsConfig(JSON json) {
		InitWarriorsConfigParser warriorsConfigParser  = new InitWarriorsConfigParser();
		return warriorsConfigParser.createConfig(json);
	}

	private KingdomTypes getKingdomType(String strKingdomType) {
		if(!kingdomTypes.containsKey(strKingdomType)) {
			throw new CriticalAppError("Incorrect kingdom type!");
		}
		return kingdomTypes.get(strKingdomType);
	}
}
