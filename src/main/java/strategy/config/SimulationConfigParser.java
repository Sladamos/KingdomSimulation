package strategy.config;

import org.json.JSONObject;
import strategy.SimulationConfig;
import strategy.kingdom.KingdomConfig;

public class SimulationConfigParser {
	public SimulationConfig createSimulationConfig(JSONObject json) {
		KingdomConfigParser kingdomConfigParser = new KingdomConfigParser();
		var firstKingdomJson = json.getJSONObject("first_kingdom");
		KingdomConfig firstKingdomConfig = kingdomConfigParser.createKingdomConfig(firstKingdomJson);
		var secondKingdomJson = json.getJSONObject("second_kingdom");
		KingdomConfig secondKingdomConfig = kingdomConfigParser.createKingdomConfig(secondKingdomJson);
		return new SimulationConfig(firstKingdomConfig, secondKingdomConfig);
	}
}
