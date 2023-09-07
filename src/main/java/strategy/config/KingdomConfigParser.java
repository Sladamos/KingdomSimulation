package strategy.config;

import strategy.AppError;
import strategy.json.JSON;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.KingdomTypes;
import strategy.military.infantry.InitWarriorsConfig;

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
			long attackTime = json.getLong("attack_time") * 1000;
			String kingdomTypeStr = json.getString("kingdom_type");
			KingdomTypes kingdomType = getKingdomType(kingdomTypeStr);
			InitWarriorsConfig warriorsConfig = createWarriorsConfig(json.getJSONObject("warriors"));
			return new KingdomConfig(kingdomId, attackTime, kingdomType, warriorsConfig);
		}
		catch (Exception err) {
			throw new AppError("Something went wrong on creating kingdom config. " + err.getMessage());
		}
	}

	private InitWarriorsConfig createWarriorsConfig(JSON json) {
		InitWarriorsConfigParser warriorsConfigParser  = new InitWarriorsConfigParser();
		return warriorsConfigParser.createConfig(json);
	}

	private KingdomTypes getKingdomType(String strKingdomType) {
		if(!kingdomTypes.containsKey(strKingdomType)) {
			throw new AppError("Incorrect kingdom type!");
		}
		return kingdomTypes.get(strKingdomType);
	}
}