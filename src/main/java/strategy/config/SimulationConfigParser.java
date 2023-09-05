package strategy.config;

import org.json.JSONObject;
import strategy.SimulationConfig;
import strategy.json.JSON;
import strategy.kingdom.KingdomConfig;

public class SimulationConfigParser {
	public SimulationConfig createSimulationConfig(JSON json) {
		KingdomConfigParser kingdomConfigParser = new KingdomConfigParser();
		JSON firstKingdomJson = json.getJSONObject("first_kingdom");
		KingdomConfig firstKingdomConfig = kingdomConfigParser.createKingdomConfig(firstKingdomJson);
		JSON secondKingdomJson = json.getJSONObject("second_kingdom");
		KingdomConfig secondKingdomConfig = kingdomConfigParser.createKingdomConfig(secondKingdomJson);
		return new SimulationConfig(firstKingdomConfig, secondKingdomConfig);
	}
}
