package strategy.config;

import org.json.JSONObject;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.KingdomTypes;

import java.util.HashMap;
import java.util.Map;

public class KingdomConfigParser {

	private final Map<String, KingdomTypes> kingdomTypes;

	public KingdomConfigParser() {
		kingdomTypes = new HashMap<>();
		kingdomTypes.put("Sarrax", KingdomTypes.SARRAX);
	}

	public KingdomConfig createKingdomConfig(JSONObject json) {
		String kingdomId = json.getString("id");
		long attackTime = json.getInt("attack_time") * 1000L;
		String kingdomTypeStr = json.getString("castle_type");
		KingdomTypes kingdomType = getKingdomType(kingdomTypeStr);
		return new KingdomConfig(kingdomId, attackTime, kingdomType);
	}

	private KingdomTypes getKingdomType(String strKingdomType) {
		return kingdomTypes.get(strKingdomType);
	}
}
