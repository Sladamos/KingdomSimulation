package strategy.config;

import org.json.JSONException;
import strategy.config.infantry.InitWarriorsConfigParser;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.json.JSON;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.KingdomType;
import strategy.location.castle.CastleConfig;
import strategy.location.mountain.MountainConfig;
import strategy.location.settlement.SettlementConfig;
import strategy.location.village.VillageConfig;
import strategy.util.Time;
import strategy.util.TimeImpl;

import java.util.HashMap;
import java.util.Map;

public class KingdomConfigParser implements ConfigParser<KingdomConfig> {

	private final Map<String, KingdomType> kingdomTypes;

	public KingdomConfigParser() {
		kingdomTypes = new HashMap<>();
		kingdomTypes.put("Sarrax", KingdomType.SARRAX);
	}

	@Override
	public KingdomConfig createConfig(JSON json) {
		try {
			Time attackTime = new TimeImpl(json.getInt("attack_time"));
			if(attackTime.getSeconds() < 0) {
				throw new BasicAppError("Attack time can't be less than 0.");
			}
			String kingdomId = json.getString("id");
			String kingdomTypeStr = json.getString("kingdom_type");
			KingdomType kingdomType = getKingdomType(kingdomTypeStr);
			InitWarriorsConfig warriorsConfig = createWarriorsConfig(json.getJSONObject("warriors"));
			VillageConfig villageConfig = createVillageConfig(json.getJSONObject("village"));
			MountainConfig mountainConfig = createMountainConfig(json.getJSONObject("mountain"));
			CastleConfig castleConfig = createCastleConfig(json.getJSONObject("castle"));
			SettlementConfig settlementConfig = createSettlementConfig(json.getJSONObject("settlement"));
			return new KingdomConfig(kingdomId, attackTime, kingdomType, warriorsConfig,
					villageConfig, mountainConfig, castleConfig, settlementConfig);
		}
		catch (JSONException | BasicAppError err) {
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

	private KingdomType getKingdomType(String strKingdomType) {
		if(!kingdomTypes.containsKey(strKingdomType)) {
			throw new BasicAppError("Incorrect kingdom type!");
		}
		return kingdomTypes.get(strKingdomType);
	}
}
